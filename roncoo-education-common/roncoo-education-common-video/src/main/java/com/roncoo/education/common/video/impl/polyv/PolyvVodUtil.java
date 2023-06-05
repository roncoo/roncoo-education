/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.video.impl.polyv;

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
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvGetKeyframeResp;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvPlayResponse;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvVideoInfoResp;
import com.roncoo.education.common.video.impl.polyv.vod.resp.PolyvVodUploadConfig;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保利威点播工具类
 *
 * @author wujing
 * @see <a href="https://help.polyv.net/index.html#/vod/java/">...</a>
 */
@Slf4j
public final class PolyvVodUtil {

    private PolyvVodUtil() {
    }

    /**
     * 修改回调设置
     *
     * @param appId
     * @param callbackUrl
     * @return
     */
    public static void setCallback(String appId, String appSecret, String callbackUrl) {
        //业务参数
        String url = "http://api.polyv.net/vod/v4/user/setting/callback/update";
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("appId", appId);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("callbackUrl", callbackUrl);
        requestMap.put("sign", PolyvSignUtil.getMd5Sign(requestMap, appSecret));
        String response = PolyvHttpUtil.postForJson(url, requestMap);
        log.info("保利威-修改回调设置-{}", response);
    }

    /**
     * 修改授权播放和防录屏跑马灯接口
     *
     * @param appId
     * @param marqueeUrl
     * @return
     */
    public static void setMarquee(String appId, String appSecret, String marqueeUrl) {
        //业务参数
        String url = "http://api.polyv.net/vod/v4/user/setting/marquee/update";
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("appId", appId);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestMap.put("marqueeUrl", marqueeUrl);
        requestMap.put("sign", PolyvSignUtil.getMd5Sign(requestMap, appSecret));
        String response = PolyvHttpUtil.postForJson(url, requestMap);
        log.info("保利威-修改授权播放和防录屏跑马灯接口-{}", response);
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
    public static PolyvGetKeyframeResp setPlaysafe(String userid, String secretkey, String encrypt, String hlslevel) {
        //业务参数
        String url = String.format("http://api.polyv.net/v2/setting/%s/set-playsafe", userid);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("userid", userid);
        requestMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        requestMap.put("encrypt", encrypt);
        requestMap.put("hlslevel", hlslevel);
        requestMap.put("sign", PolyvSignUtil.getSha1Sign(requestMap, secretkey));
        String response = PolyvHttpUtil.post(url, requestMap);
        log.debug("设置账号加密设置，{}", response);
        JSONObject js = JSONUtil.parseObj(response);
        if (PolyvHttpUtil.SUCCESS_STATUS.equalsIgnoreCase(js.getStr("status"))) {
            log.debug("获取成功，报文={}", js);
            return js.getJSONObject("data").toBean(PolyvGetKeyframeResp.class);
        }
        log.error("获取失败，原因={}", js);
        return null;
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
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", PolyvSignUtil.getSha1Sign(paramMap, secretKey));
        String result = PolyvHttpUtil.post(requestUrl, paramMap);
        JSONObject resultJson = JSONUtil.parseObj(result);
        if (PolyvHttpUtil.SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getJSONObject("data");
        }
        return null;
    }

    public static PolyvVodUploadConfig getUploadConfig(String userid, String key, String token) {
        PolyvVodUploadConfig config = new PolyvVodUploadConfig();
        config.setUserid(userid);
        config.setPtime(System.currentTimeMillis());
        config.setSign(MD5Util.md5(key + config.getPtime()));
        config.setHash(MD5Util.md5(config.getPtime() + token));
        config.setCategoryId("1");
        return config;
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
    private static String uploadVideo(File file, String title, String tag, String desc, String categoryId, Integer luping, String state, String writeToken) {
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

        String result = PolyvHttpUtil.post(requestUrl, paramMap);
        log.debug("保利威视--上传视频响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (0 == resultJson.getInt("error")) {
            return resultJson.getJSONArray("data").getJSONObject(0).getStr("vid");
        }
        return null;
    }


    /**
     * 查询视频信息
     *
     * @param vidList   视频id（可以支持多个，用英文逗号隔开）
     * @param userId    保利威点播账户id--必填
     * @param secretKey 保利威点播签名秘钥--必填
     * @return
     */
    public static List<PolyvVideoInfoResp> getVideoInfo(List<String> vidList, String userId, String secretKey) {
        String requestUrl = String.format("http://api.polyv.net/v2/video/%s/get-video-info", userId);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("vid", String.join(",", vidList));
        // 签名
        paramMap.put("sign", PolyvSignUtil.getSha1Sign(paramMap, secretKey));

        // 请求
        String result = PolyvHttpUtil.post(requestUrl, paramMap);
        log.debug("保利威视--查询视频信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (PolyvHttpUtil.SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseArray(resultJson.getStr("data"), PolyvVideoInfoResp.class);
        }
        return null;
    }

    /**
     * 批量删除视频
     *
     * @param vid       视频id集合--必填
     * @param userId    保利威点播账户id--必填
     * @param secretKey 保利威点播签名秘钥--必填
     * @return 删除结果
     */
    public static Boolean delete(String vid, String userId, String secretKey) {
        String requestUrl = "http://api.polyv.net/v2/video/del-videos";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));
        paramMap.put("vids", vid);
        // 删除方式，默认为1 1：删除到回收站 2：彻底删除
        // paramMap.put("deleteType", "1");

        // 签名
        paramMap.put("sign", PolyvSignUtil.getSha1Sign(paramMap, secretKey));

        // 请求
        String result = PolyvHttpUtil.post(String.format(requestUrl, userId), new HashMap<>(paramMap));
        log.debug("保利威视--删除视频响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        return PolyvHttpUtil.SUCCESS_CODE.equals(resultJson.getInt("code"));
    }

    public static PolyvPlayResponse getSignForPlay(String userId, String secretKey, VodPlayConfigReq playConfigReq) {
        // 根据时间戳、vid、密钥生成sign值
        String ts = String.valueOf(System.currentTimeMillis());
        // 获取播放token
        Map<String, Object> map = new HashedMap<>();
        map.put("userId", userId);
        map.put("videoId", playConfigReq.getVid());
        map.put("ts", ts);
        map.put("viewerId", playConfigReq.getViewerId());
        map.put("viewerIp", playConfigReq.getViewerIp());
        map.put("viewerName", playConfigReq.getViewerName());
        map.put("expires", playConfigReq.getExpiresIn());
        map.put("iswxa", 0);
        if ("5".equals(playConfigReq.getTerminal())) {
            map.put("iswxa", 1);
        }
        map.put("extraParams", playConfigReq.getExtraParams());
        map.put("sign", PolyvSignUtil.getMd5Sign(map, secretKey));

        String result = HttpUtil.post("https://hls.videocc.net/service/v1/token", map);
        JSONObject resultJson = JSONUtil.parseObj(result);
        if (resultJson.getInt("code") != 200) {
            log.error("获取保利威播放sign值失败，返回参数={}", result);
            return null;
        }
        PolyvPlayResponse dto = new PolyvPlayResponse();
        dto.setVid(map.get("videoId").toString());
        dto.setSign(MD5Util.md5(secretKey + map.get("videoId") + ts));
        dto.setTs(ts);
        dto.setToken(resultJson.getJSONObject("data").getStr("token"));
        dto.setCode(encodeForPlay(playConfigReq.getVodAuthCode()));
        return dto;
    }

    /**
     * 获取code
     *
     * @param authCode 请求参数
     * @return 获取结果
     */
    private static String encodeForPlay(VodPlayConfigReq.VodAuthCode authCode) {
        log.debug("获取codePolyvCode={}", JSONUtil.toJsonStr(authCode));
        try {
            return URLUtil.encodeQuery(Base64.encode(SecureUtil.des(Base64.decode(PolyvHttpUtil.KEY)).encrypt(JSUtil.toJsonString(authCode))), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("保利威视，加密出错", e);
            return "";
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
}
