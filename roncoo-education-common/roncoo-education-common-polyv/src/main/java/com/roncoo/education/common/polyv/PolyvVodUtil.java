/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.polyv;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.polyv.callback.CallbackVodAuthCode;
import com.roncoo.education.common.polyv.vod.PolyvSign;
import com.roncoo.education.common.polyv.vod.PolyvSignResponse;
import com.roncoo.education.common.polyv.vod.request.VideoInfoAsyncBatchUploadRequest;
import com.roncoo.education.common.polyv.vod.response.CategoryAddResponse;
import com.roncoo.education.common.polyv.vod.response.CategoryViewResponse;
import com.roncoo.education.common.polyv.vod.response.PolyvGetKeyframeResponse;
import com.roncoo.education.common.polyv.vod.response.VideoInfoResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 保利威点播工具类
 *
 * @author wujing
 * @see <a href="https://help.polyv.net/index.html#/vod/java/">...</a>
 */
@Slf4j
public final class PolyvVodUtil {


    private static final String POLYV_GET_TOKEN = "https://hls.videocc.net/service/v1/token";

    private static final String KEY = "rc-education";

    /**
     * 成功编码
     */
    private static final Integer SUCCESS_CODE = 200;

    private PolyvVodUtil() {
    }

    public static void main(String[] args) {
        System.out.println(getUserMain("75ae5d5bf7", "e3ZbH9lv0M"));
    }

    /**
     * 获取用户空间及流量情况
     *
     * @param userid
     * @param secretKey
     * @return
     */
    public static JSONObject getUserMain(String userid, String secretKey) {
        String requestUrl = String.format("http://api.polyv.net/v2/user/%s/main", userid);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));
        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getJSONObject("data");
        }
        return null;
    }

    /**
     * 异步批量上传视频
     *
     * @param param     异步批量上传参数
     * @param userId    保利威点播账户id - 必填
     * @param secretKey 保利威点播签名秘钥 - 必填
     * @return 发起结果
     * @see <a href="https://help.polyv.net/#/vod/api/video_upload/url_batch_upload">异步批量上传视频</a>
     */
    public static Boolean asyncBatchUpload(VideoInfoAsyncBatchUploadRequest param, String userId, String secretKey) {
        String requestUrl = String.format("http://api.polyv.net/v2/video/grab/%s/upload/multi", userId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        if (StrUtil.isNotBlank(param.getCataid())) {
            paramMap.put("cataid", param.getCataid());
        }
        if (ObjectUtil.isNotNull(param.getLuping())) {
            paramMap.put("luping", String.valueOf(param.getLuping()));
        }
        if (StrUtil.isNotBlank(param.getWatermark())) {
            paramMap.put("watermark", param.getWatermark());
        }
        if (StrUtil.isNotBlank(param.getWatermarkLocation())) {
            paramMap.put("watermarkLocation", param.getWatermarkLocation());
        }
        paramMap.put("uploadInfos", JSUtil.toJsonString(param.getUploadInfos()));
        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--异步批量上传视频：{}", result);
        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--异步批量上传视频失败：{}", result);
        return false;
    }

    /**
     * 查询视频信息
     *
     * @param vidList   视频id（可以支持多个，用英文逗号隔开）
     * @param userId    保利威点播账户id--必填
     * @param secretKey 保利威点播签名秘钥--必填
     * @return
     */
    public static List<VideoInfoResponse> getVideoInfo(List<String> vidList, String userId, String secretKey) {
        String requestUrl = String.format("http://api.polyv.net/v2/video/%s/get-video-info", userId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("vid", String.join(",", vidList));
        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询视频信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseArray(resultJson.getStr("data"), VideoInfoResponse.class);
        }

        log.info("保利威视--查询视频信息失败：{}", result);
        return null;
    }

    /**
     * 上传视频
     *
     * @param file       视频文件--必传
     * @param title      标题
     * @param tag        标签
     * @param desc       描述
     * @param categoryId 分类ID
     * @param luping     是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0
     * @param state      用户自定义数据，如果提交了该字段
     * @param writeToken 用户的上传钥匙 -- 必传
     * @return 视频VID
     */
    public static String uploadVideo(File file, String title, String tag, String desc, String categoryId, Integer luping, String state, String writeToken) {
        String requestUrl = "http://v.polyv.net/uc/services/rest?method=uploadfile";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("writetoken", writeToken);

        JSONObject rpcJson = new JSONObject();
        rpcJson.set("title", title);
        rpcJson.set("tag", tag);
        rpcJson.set("desc", desc);
        paramMap.put("JSONRPC", rpcJson.toString());

        paramMap.put("Filedata", file);
        if (StrUtil.isNotBlank(categoryId)) {
            paramMap.put("cataid", categoryId);
        }
        if (ObjectUtil.isNotNull(luping)) {
            paramMap.put("luping", luping);
        }
        if (StrUtil.isNotBlank(state)) {
            paramMap.put("state", state);
        }

        String result = HttpUtil.post(requestUrl, paramMap);
        log.info("保利威视--上传视频响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (0 == resultJson.getInt("error")) {
            return resultJson.getJSONArray("data").getJSONObject(0).getStr("vid");
        }
        return null;
    }

    /**
     * 批量删除视频
     *
     * @param vidList   视频id集合--必填
     * @param userId    保利威点播账户id--必填
     * @param secretKey 保利威点播签名秘钥--必填
     * @return 删除结果
     */
    public static Boolean delete(List<String> vidList, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/del-videos";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("vids", String.join(",", vidList));
        // 删除方式，默认为1 1：删除到回收站 2：彻底删除
        // paramMap.put("deleteType", "1");

        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.info("保利威视--删除视频响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        return SUCCESS_CODE.equals(resultJson.getInt("code"));
    }

    /**
     * 修改账号的加密设置
     *
     * @param userid
     * @param secretkey
     * @param encrypt   是否开启加密（仅对新上传视频有效） 1：开启 0：关闭
     * @param hlslevel  open: 非加密授权 web: WEB授权 app: APP授权 wxa_app：小程序授权
     * @return
     */
    public static PolyvGetKeyframeResponse setPlaysafe(String userid, String secretkey, String encrypt, String hlslevel) {
        //业务参数
        String url = String.format("http://api.polyv.net/v2/setting/%s/set-playsafe", userid);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userid", userid);
        requestMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        requestMap.put("encrypt", encrypt);
        requestMap.put("hlslevel", hlslevel);
        requestMap.put("sign", getSha1Sign(requestMap, secretkey));

        String response;
        try {
            response = PolyvHttpUtil.postFormBody(url, requestMap);
        } catch (IOException e) {
            log.error("保利威-修改账号加密设置失败！", e);
            throw new BaseException("polyv set playsafe fail!!!");
        }
        log.debug("设置账号加密设置，{}", response);
        JSONObject js = JSONUtil.parseObj(response);
        if ("success".equalsIgnoreCase(js.getStr("status"))) {
            log.info("获取成功，报文={}", js);
            return js.getJSONObject("data").toBean(PolyvGetKeyframeResponse.class);
        }
        log.error("获取失败，原因={}", js);
        return null;
    }


    /**
     * 创建分类
     *
     * @param categoryName 分类名称，不超过40个字符--必填
     * @param parentId     新建的分类目录的上一级目录，值为1时表示根目录--必填
     * @param userId       保利威点播账户id--必填
     * @param secretKey    保利威点播签名秘钥--必填
     * @return 创建分类响应对象
     */
    public static CategoryAddResponse categoryAdd(String categoryName, String parentId, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/%s/addCata";

        // 参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("cataname", categoryName);
        paramMap.put("parentid", parentId);

        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.info("保利威视--创建分类响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.get("data", CategoryAddResponse.class);
        }
        return null;
    }

    /**
     * 获取分类及子分类信息
     *
     * @param categoryId 分类id，默认为1根目录
     * @param userId     保利威点播账户id--必填
     * @param secretKey  保利威点播签名秘钥--必填
     * @return 分类信息
     */
    public static List<CategoryViewResponse> categoryView(String categoryId, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/%s/cataJson";

        // 参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        if (StrUtil.isNotBlank(categoryId)) {
            paramMap.put("cataid", categoryId);
        }

        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.info("保利威视--获取分类及子分类响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseArray(resultJson.getStr("data"), CategoryViewResponse.class);
        }
        return null;
    }

    /**
     * 修改分类名称
     *
     * @param categoryId   视频分类id--必填
     * @param categoryName 分类名称--必填
     * @param userId       保利威点播账户id--必填
     * @param secretKey    保利威点播签名秘钥--必填
     * @return 修改结果
     */
    public static Boolean categoryUpdateName(String categoryId, String categoryName, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/%s/updateCata";

        // 参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("cataid", categoryId);
        paramMap.put("cataname", categoryName);

        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.info("保利威视--修改分类名称响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getBool("data");
        }
        return false;
    }

    /**
     * 删除分类
     *
     * @param categoryId 分类id--必填
     * @param userId     保利威点播账户id--必填
     * @param secretKey  保利威点播签名秘钥--必填
     * @return 删除结果
     */
    public static Boolean categoryDelete(String categoryId, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/%s/deleteCata";

        // 参数
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userid", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        if (StrUtil.isNotBlank(categoryId)) {
            paramMap.put("cataid", categoryId);
        }

        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));

        // 请求
        String result = HttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.info("保利威视--删除分类响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getBool("data");
        }
        return false;
    }


    /**
     * 上传水印(只支持png格式)
     * 1、设置一级分类下或用户账号下所有视频的水印，设置成功后对新上传的视频生效
     * 2、接口URL中的{userid}为点播账号userid，具体参考菜单【使用须知】->【获取密钥】
     * 3、接口支持https协议
     *
     * @param cataid            分类id，仅给一级分类的视频设置水印。不传为设置用户级别视频的水印
     * @param userId            保利威点播账户id，可以参考【获取密匙】获取，获取路径：官网->登录->点播（API接口） --必填
     * @param image             上传的图片 --必填
     * @param watermarkLocation 水印显示的位置，不传默认为0，1：左上角  2：右上角 3：左下角  4：右下角 0：不显示水印
     * @param secretKey         保利威点播签名秘钥--必填
     * @return
     */
    @SneakyThrows
    public static Boolean watermarkSetting(String cataid, String userId, String image, String watermarkLocation, String secretKey) {
        String requestUrl = String.format("http://api.polyv.net/v2/video/%s/watermarkSetting", userId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        if (StrUtil.isNotBlank(cataid)) {
            paramMap.put("cataid", cataid);
        }
        if (StrUtil.isNotBlank(watermarkLocation)) {
            paramMap.put("watermarkLocation", watermarkLocation);
        }
        Map<String, File> fileMap = new HashMap<>();
        // 文件下载到服务器临时文件
        File file = HttpUtil.downloadFileFromUrl(image, "/tmp");
        fileMap.put("image", file);
        // 签名
        paramMap.put("sign", getSha1Sign(paramMap, secretKey));
        // 请求
        String result = com.roncoo.education.common.polyv.HttpUtil.postFile(requestUrl, paramMap, fileMap, "utf-8");
        log.info("保利威视--上传水印响应结果：{}", result);

        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        return SUCCESS_CODE.equals(resultJson.getInt("code"));
    }

    /**
     * 获取code
     *
     * @param authCode 请求参数
     * @return 获取结果
     */
    public static String getPolyvCode(CallbackVodAuthCode authCode) {
        try {
            return URLUtil.encodeQuery(Base64.encode(SecureUtil.des(Base64.decode(KEY)).encrypt(JSUtil.toJsonString(authCode))), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("保利威视，加密出错", e);
            return "";
        }
    }

    public static PolyvSignResponse getSignForH5(PolyvSign bo, String userId, String secretKey) {
        // 根据时间戳、vid、密钥生成sign值
        String ts = String.valueOf(System.currentTimeMillis());

        // 获取播放token
        Map<String, Object> map = new HashedMap<>();
        map.put("userId", userId);
        map.put("videoId", bo.getVid());
        map.put("ts", ts);
        map.put("viewerIp", bo.getIp());
        map.put("viewerName", bo.getUserNo());
        map.put("extraParams", "HTML5");
        map.put("viewerId", bo.getUserNo());
        // 签名
        String signParam = "extraParams" + map.get("extraParams") + "ts" + map.get("ts") + "userId" + map.get("userId") + "videoId" + map.get("videoId") + "viewerId" + map.get("viewerId") + "viewerIp" + map.get("viewerIp") + "viewerName" + map.get("viewerName");
        map.put("sign", MD5Util.md5(secretKey + signParam + secretKey).toUpperCase());
        String result = HttpUtil.post(POLYV_GET_TOKEN, map);
        cn.hutool.json.JSONObject resultJson = JSONUtil.parseObj(result);
        if (resultJson.getInt("code") != 200) {
            log.error("获取保利威播放sign值失败，请求参数={}，返回参数={}", map, result);
            return null;
        }
        cn.hutool.json.JSONObject dataJson = resultJson.getJSONObject("data");
        if (ObjectUtil.isNull(dataJson)) {
            log.error("获取保利威播放sign值失败，返回参数={}", result);
            return null;
        }
        PolyvSignResponse dto = new PolyvSignResponse();
        dto.setSign(MD5Util.md5(secretKey + bo.getVid() + ts));
        dto.setTs(ts);
        dto.setToken(dataJson.getStr("token"));
        return dto;
    }

    /**
     * code解密
     *
     * @param code 播放COde
     * @return 解码结果
     */
    public static CallbackVodAuthCode decode(String code) {
        try {
            return JSUtil.parseObject(new String(SecureUtil.des(Base64.decode(KEY)).decrypt(Base64.decode(URLDecoder.decode(code, StandardCharsets.UTF_8.name())))), CallbackVodAuthCode.class);
        } catch (Exception e) {
            log.error("保利威视，解密出错", e);
            return null;
        }
    }

    /**
     * 获取回调签名
     *
     * @param paramMap  回调参数
     * @param secretKey 签名秘钥
     * @return 签名结果
     */
    public static String getCallbackSign(Map<String, String> paramMap, String secretKey) {
        if (CollectionUtil.isEmpty(paramMap)) {
            return null;
        }

        String type = paramMap.get("type");
        if (StrUtil.isBlank(type)) {
            type = paramMap.get("eventType");
        }

        StringBuilder sb = new StringBuilder();
        if ("upload".equals(type) || "videoSyncedFail".equals(type) || "TranscodeComplete".equals(type)) {
            sb.append(type).append(paramMap.get("vid"));
        } else if ("async_upload".equals(type)) {
            sb.append(type).append(paramMap.get("fileUrl"));
        } else if ("pass".equals(type) || "nopass".equals(type)) {
            sb.append("manage").append(type).append(paramMap.get("vid"));
        } else if ("encode".equals(type) || "encode_failed".equals(type)) {
            sb.append(type).append(paramMap.get("format")).append(paramMap.get("vid")).append(paramMap.get("df"));
        } else {
            sb.append(type);
        }
        sb.append(secretKey);
        return DigestUtil.md5Hex(sb.toString());
    }

    public static String getSha1Sign(Map<String, String> params, String secretKey) {
        log.debug("参与签名参数：{}", JSONUtil.toJsonStr(params));
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        // 拼接参数
        StringBuilder param = new StringBuilder();
        for (String key : keys) {
            if (null != params.get(key) && params.get(key).length() > 0) {
                param.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        if (StrUtil.isNotBlank(param.toString())) {
            param = new StringBuilder(param.substring(0, param.length() - 1));
        }
        param.append(secretKey);
        log.debug("签名原始字符串：{}", param);

        // 签名加密
        String sign = SecureUtil.sha1(param.toString()).toUpperCase();
        log.debug("签名结果：{}", sign);
        return sign;
    }

    public static String getSign(String secretKey, String userId, Long ts) {
        String s = secretKey + userId + secretKey + ts;
        return MD5Util.md5(s);
    }
}
