package com.roncoo.education.common.polyv.live;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.polyv.PolyvHttpUtil;
import com.roncoo.education.common.polyv.live.request.ChannelCreateRequest;
import com.roncoo.education.common.polyv.live.request.ChannelInfoUpdateRequest;
import com.roncoo.education.common.polyv.live.request.ChannelViewLogPageRequest;
import com.roncoo.education.common.polyv.live.response.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 保利威直播工具类
 *
 * @author wujing
 * @see <a href="https://help.polyv.net/index.html#/live/java/">帮助文档</a>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PolyvLiveUtil {

    /**
     * 直播地址
     */
    public static final String POLYV_LIVE_URL = "{domain}/watch/{channelId}?userid={userid}&ts={ts}&sign={sign}";


    private static final String SUCCESS_STATUS = "success";

    private static final Integer SUCCESS_CODE = 200;

    /**
     * 创建频道号信息
     *
     * @param request   请求参数
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 频道号信息
     * @see <a href="https://help.polyv.net/index.html#/live/api/v4/channel/create">创建频道号信息</a>
     */
    public static ChannelCreateResponse createChannel(ChannelCreateRequest request, String appId, String appSecret) {
        String url = "http://api.polyv.net/live/v4/channel/create";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(getRequestUrl(url, paramMap), JSONUtil.toJsonStr(request));
        log.info("保利威视--创建单个频道响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_STATUS.equals(resultJson.getStr("status"))) {
            return resultJson.get("data", ChannelCreateResponse.class);
        }

        log.error("保利威视--创建单个频道失败：{}", result);
        return null;
    }

    /**
     * 修改直播状态改变回调设置
     *
     * @param userId      账号用户ID
     * @param appId       账号appId
     * @param appSecret   账号appSecret
     * @param callbackUrl 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头
     * @see <a href="https://help.polyv.net/index.html#/live/api/account/set_stream_callback">修改直播状态改变回调设置</a>
     */
    public static void setStreamCallback(String userId, String appId, String appSecret, String callbackUrl) {
        String url = "http://api.polyv.net/live/v2/user/{userId}/set-stream-callback".replace("{userId}", userId);
        Map<String, Object> map = new HashedMap<>();
        map.put("appId", appId);
        map.put("timestamp", System.currentTimeMillis());
        map.put("url", callbackUrl);
        map.put("sign", getSign(appSecret, map));
        JSONObject js = JSUtil.parseObject(HttpUtil.post(url, map), JSONObject.class);
        log.info("设置直播状态回调通知url={}", js);
    }

    /**
     * 修改转存成功回调设置
     *
     * @param userId      账号用户ID
     * @param appId       账号appId
     * @param appSecret   账号appSecret
     * @param callbackUrl 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头
     * @see <a href="https://help.polyv.net/index.html#/live/api/account/set_playback_callback">修改转存成功回调设置</a>
     */
    public static void setPlaybackCallback(String userId, String appId, String appSecret, String callbackUrl) {
        String url = "http://api.polyv.net/live/v2/user/{userId}/set-playback-callback".replace("{userId}", userId);
        Map<String, Object> map = new HashedMap<>();
        map.put("appId", appId);
        map.put("timestamp", System.currentTimeMillis());
        map.put("url", callbackUrl);
        map.put("sign", getSign(appSecret, map));
        JSONObject js = JSUtil.parseObject(HttpUtil.post(url, map), JSONObject.class);
        log.info("设置转存成功回调通知url={}", js);
    }

    /**
     * 修改录制生成回调设置
     *
     * @param userId      账号appId
     * @param appId       账号appId
     * @param appSecret   账号appSecret
     * @param callbackUrl 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头
     * @see <a href="https://help.polyv.net/index.html#/live/api/account/set_record_callback">修改录制生成回调设置</a>
     */
    public static void setRecordCallback(String userId, String appId, String appSecret, String callbackUrl) {
        String url = "http://api.polyv.net/live/v2/user/{userId}/set-record-callback".replace("{userId}", userId);
        Map<String, Object> map = new HashedMap<>();
        map.put("appId", appId);
        map.put("timestamp", System.currentTimeMillis());
        map.put("url", callbackUrl);
        map.put("sign", getSign(appSecret, map));
        JSONObject js = JSUtil.parseObject(HttpUtil.post(url, map), JSONObject.class);
        log.info("设置录制回调通知url={}", js);
    }

    /**
     * 修改全局回调设置
     *
     * @param appId                         账号appId
     * @param appSecret                     账号appSecret
     * @param recordCallbackUrl             录制生成回调URL
     * @param recordFileCallBackType        回调录制文件内容
     *                                      all：全部回放视频
     *                                      last：最终回放视频
     * @param recordCallbackVideoType       回调文件类型
     *                                      m3u8：m3u8文件
     *                                      mp4：mp4文件
     *                                      m3u8,mp4：m3u8和mp4文件
     * @param playbackCallbackUrl           转存成功回调URL
     * @param rebirthVodCallbackEnabled     重制课件转存点播回调开关
     *                                      Y：开启
     *                                      N：关闭
     * @param pptRecordCallbackUrl          课件重制成功回调URL
     * @param streamCallbackUrl             直播状态改变回调URL
     * @param channelBasicUpdateCallbackUrl 频道直播间信息修改回调URL
     * @param liveScanCallbackUrl           直播内容审核不通过回调URL
     * @param playbackCacheCallbackUrl      直播回放缓存生成回调通知URL
     * @see <a href="https://help.polyv.net/index.html#/live/api/v4/user/update_callback">修改全局回调设置</a>
     */
    public static void globalSettingCallbackUpdate(String appId, String appSecret, String recordCallbackUrl, String recordFileCallBackType, String recordCallbackVideoType, String playbackCallbackUrl, String rebirthVodCallbackEnabled, String pptRecordCallbackUrl, String streamCallbackUrl, String channelBasicUpdateCallbackUrl, String liveScanCallbackUrl, String playbackCacheCallbackUrl) {
        String url = "http://api.polyv.net/live/v4/user/global-setting/callback/update";
        Map<String, String> map2 = new HashedMap<>();
        map2.put("appId", appId);
        map2.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map2.put("sign", getSign2(appSecret, map2));

        Map<String, String> map = new HashedMap<>();
        if (StrUtil.isNotBlank(recordCallbackUrl)) {
            map.put("recordCallbackUrl", recordCallbackUrl);
        }
        if (StrUtil.isNotBlank(recordFileCallBackType)) {
            map.put("recordFileCallBackType", recordFileCallBackType);
        }
        if (StrUtil.isNotBlank(recordCallbackVideoType)) {
            map.put("recordCallbackVideoType", recordCallbackVideoType);
        }

        if (StrUtil.isNotBlank(playbackCallbackUrl)) {
            map.put("playbackCallbackUrl", playbackCallbackUrl);
        }
        if (StrUtil.isNotBlank(rebirthVodCallbackEnabled)) {
            map.put("rebirthVodCallbackEnabled", rebirthVodCallbackEnabled);
        }
        if (StrUtil.isNotBlank(pptRecordCallbackUrl)) {
            map.put("pptRecordCallbackUrl", pptRecordCallbackUrl);
        }
        if (StrUtil.isNotBlank(streamCallbackUrl)) {
            map.put("streamCallbackUrl", streamCallbackUrl);
        }
        if (StrUtil.isNotBlank(channelBasicUpdateCallbackUrl)) {
            map.put("channelBasicUpdateCallbackUrl", channelBasicUpdateCallbackUrl);
        }
        if (StrUtil.isNotBlank(liveScanCallbackUrl)) {
            map.put("liveScanCallbackUrl", liveScanCallbackUrl);
        }
        if (StrUtil.isNotBlank(playbackCacheCallbackUrl)) {
            map.put("playbackCacheCallbackUrl", playbackCacheCallbackUrl);
        }

        try {
            url = PolyvHttpUtil.appendUrl(url, map2);
        } catch (UnsupportedEncodingException e) {
            log.error("保利威-修改全局回调设置失败！", e);
            throw new BaseException("polyv edit global callback setting fail!!!");
        }

        String response;
        try {
            response = PolyvHttpUtil.postJsonBody(url, JSUtil.toJsonString(map), null);
        } catch (IOException e) {
            log.error("保利威-修改全局回调设置失败！", e);
            throw new BaseException("polyv edit global callback setting fail!!!");
        }
        log.info("保利威视--修改全局回调设置：{}", response);
    }

    /**
     * 修改频道信息
     *
     * @param request   修改信息
     * @param channelId 频道号
     * @param appId     应用ID
     * @param appSecret 应用秘钥
     * @return 修改结果
     */
    public static Boolean updateChannelInfo(ChannelInfoUpdateRequest request, String channelId, String appId, String appSecret) {
        String url = "http://api.polyv.net/live/v3/channel/basic/update";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(getRequestUrl(url, paramMap), JSONUtil.toJsonStr(request));
        log.info("保利威视--修改频道信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        return SUCCESS_CODE.equals(resultJson.getInt("code"));
    }


    /**
     * 修改频道密码
     *
     * @param channelId 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改
     * @param passwd    修改的密码
     * @param userId    账号ID
     * @param appId     应用ID
     * @param appSecret 应用秘钥
     * @return 修改结果
     */
    public static Boolean updateChannelPassword(String channelId, String passwd, String userId, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/channels/%s/passwdSetting", userId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("passwd", passwd);
        paramMap.put("channelId", channelId);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--修改频道密码响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_STATUS.equals(resultJson.getStr("status"))) {
            return resultJson.getBool("data");
        }

        log.error("保利威视--修改频道密码失败：{}", result);
        return null;
    }

    /**
     * 根据fileId查询录制视频信息
     *
     * @param channelId 频道ID
     * @param fileId    文件ID
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 录制视频信息
     * @see <a href="https://help.polyv.net/index.html#/live/api/channel/playback/get_record_file">根据fileId查询录制视频信息</a>
     */
    public static ChannelRecordGetResponse getChannelRecordVideoInfo(String channelId, String fileId, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/record/get";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("fileId", fileId);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--根据fileId查询录制视频信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.get("data", ChannelRecordGetResponse.class);
        }

        log.error("保利威视--根据fileId查询录制视频信息失败：{}", result);
        return null;
    }

    /**
     * 根据频道号删除频道
     *
     * @param channelId 频道号
     * @param userId    账号ID
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 删除结果
     */
    public static Boolean deleteChannel(String channelId, String userId, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/channels/%s/delete", channelId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("userId", userId);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--删除单个频道响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getBool("data");
        }

        log.error("保利威视--删除单个频道失败：{}", result);
        return null;
    }

    /**
     * 查询批量频道状态
     *
     * @param channelIdList 频道号集合
     * @param appId         应用ID
     * @param appSecret     应用密匙
     * @return 频道状态信息
     */
    public static List<ChannelLiveStatusResponse> getLiveStatus(List<String> channelIdList, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v2/channels/live-status";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelIds", String.join(",", channelIdList));
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询批量频道状态响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseArray(resultJson.getStr("data"), ChannelLiveStatusResponse.class);
        }

        log.error("保利威视--查询批量频道状态失败：{}", result);
        return null;
    }

    /**
     * 创建重制课件任务
     *
     * @param channelId 频道号 - 必填
     * @param videoId   回放视频id - 必填
     * @param appId     账号appId - 必填
     * @param appSecret 应用密匙 - 必填
     * @return 创建结果
     * @see <a href="https://help.polyv.net/index.html#/live/api/channel/operate/add_record_task">创建重制课件任务</a>
     */
    public static Boolean createCoursewareRefashionTask(String channelId, String videoId, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/pptRecord/addRecordTask";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("videoId", videoId);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        log.warn("{}", JSUtil.toJsonString(paramMap));
        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--创建重制课件任务响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        return SUCCESS_CODE.equals(resultJson.getInt("code"));
    }


    /**
     * 查询重制课件任务列表
     *
     * @param channelId 频道号 - 必填
     * @param sessionId 重制课件模块中的场次id - 非必填
     * @param status    状态值（waiting：等待处理、process：处理中、success：重制成功、fail：重制失败、uploaded：上传点播成功、uploadFailed：上传点播失败） - 非必填
     * @param startTime 直播开始时间开始区间，格式为yyyyMMddHHmmss - 非必填
     * @param endTime   直播开始时间结束区间，格式为yyyyMMddHHmmss - 非必填
     * @param page      分页页码 - 非必填
     * @param pageSize  每页数据大小 - 非必填
     * @param appId     账号appId - 必填
     * @param appSecret 应用密匙 - 必填
     * @return 重制课件任务
     * @see <a href="https://help.polyv.net/index.html#/live/api/channel/operate/pptrecord_list">查询重制课件任务列表</a>
     */
    public static CoursewareRefashionTaskResponse getCoursewareRefashionTaskList(String channelId, String sessionId, String status, String startTime, String endTime, Integer page, Integer pageSize, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/pptRecord/list";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        if (StrUtil.isNotBlank(sessionId)) {
            paramMap.put("sessionId", sessionId);
        }
        if (StrUtil.isNotBlank(status)) {
            paramMap.put("status", status);
        }
        if (StrUtil.isNotBlank(startTime)) {
            paramMap.put("startTime", startTime);
        }
        if (StrUtil.isNotBlank(endTime)) {
            paramMap.put("endTime", endTime);
        }
        if (ObjectUtil.isNotNull(page)) {
            paramMap.put("page", String.valueOf(page));
        }
        if (ObjectUtil.isNotNull(pageSize)) {
            paramMap.put("pageSize", String.valueOf(pageSize));
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询重制课件任务列表响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseObject(resultJson.getStr("data"), CoursewareRefashionTaskResponse.class);
        }

        log.error("保利威视--查询重制课件任务列表失败：{}", result);
        return null;
    }

    /**
     * 批量转存录制视频到云点播
     *
     * @param channelId   频道号 - 必填
     * @param fileIds     要转存的录制视频文件ID，多个ID用英文逗号,分隔 - 必填
     * @param fileName    转存后的文件名，目前暂不支持传多个文件名 - 非必填
     * @param cataId      转存到点播的目录ID，默认为点播的根目录ID - 非必填
     * @param callbackUrl 转存成功时候回调通知的url - 非必填
     * @param appId       账号appId - 必填
     * @param appSecret   应用密匙 - 必填
     * @return 转存发起结果
     * @see <a href="https://help.polyv.net/#/live/api/channel/playback/record_convert_async">批量转存录制视频到云点播</a>
     */
    public static Boolean recordConvert(String channelId, String fileIds, String fileName, String cataId, String callbackUrl, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/record/convert";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("fileIds", fileIds);
        if (StrUtil.isNotBlank(fileName)) {
            paramMap.put("fileName", fileName);
        }
        if (StrUtil.isNotBlank(cataId)) {
            paramMap.put("cataId", cataId);
        }
        if (StrUtil.isNotBlank(callbackUrl)) {
            paramMap.put("callbackUrl", callbackUrl);
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询批量转存录制视频到云点播响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--批量转存录制视频到云点播失败：{}", result);
        return false;
    }

    /**
     * 清空频道聊天记录
     *
     * @param channelId 频道号
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 清空结果
     */
    public static Boolean cleanChat(String channelId, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/chat/%s/cleanChat", channelId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--清空频道聊天记录响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getBool("data");
        }

        log.error("保利威视--清空频道聊天记录失败：{}", result);
        return null;
    }

    /**
     * 设置频道单点登录token
     *
     * @param channelId 频道号
     * @param token     单点登录token
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 设置结果
     */
    public static Boolean setToken(String channelId, String token, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/channels/%s/set-token", channelId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("token", token);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--设置频道单点登录token响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--设置频道单点登录token失败：{}", result);
        return false;
    }

    /**
     * 查询账号信息
     *
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 账号信息
     */
    public static AccountInfoResponse getLiveAccountInfo(String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/user/get-info";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询账号信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.get("data", AccountInfoResponse.class);
        }

        log.error("保利威视--查询账号信息失败：{}", result);
        return null;
    }

    /**
     * 查询账号可用直播分钟数
     *
     * @param appId     应用ID
     * @param appSecret 应用密匙
     * @return 账号可用直播分钟数
     */
    public static AccountUserDurationResponse getLiveAccountUserDurations(String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v2/user/get-user-durations";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询账号可用直播分钟数响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.get("data", AccountUserDurationResponse.class);
        }

        log.error("保利威视--查询账号可用直播分钟数失败：{}", result);
        return null;
    }

    /**
     * 查询频道直播观看详情数据
     *
     * @param channelId  频道号--必填
     * @param currentDay 查询日期，格式：yyyy-MM-dd--必填
     * @param userId     直播账号ID--必填
     * @param param1     观看用户ID，默认与直播账号ID一致
     * @param appId      账号appid--必填
     * @param appSecret  账号appSecret--必填
     * @return 观看详情数据
     * @see <a href="https://help.polyv.net/index.html#/live/api/channel/viewdata/viewlog_2">查询频道直播观看详情数据</a>
     */
    public static List<ChannelViewLogResponse> channelViewLogList(String channelId, String currentDay, String userId, String param1, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v1/statistics/%s/viewlog", channelId);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("currentDay", currentDay);
        paramMap.put("userId", userId);
        if (StrUtil.isNotBlank(param1)) {
            paramMap.put("param1", param1);
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--查询频道直播观看详情数据响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_STATUS.equals(resultJson.getStr("status"))) {
            return JSUtil.parseArray(resultJson.getStr("result"), ChannelViewLogResponse.class);
        }

        log.error("保利威视--查询频道直播观看详情数据失败：{}", result);
        return null;
    }

    /**
     * 分页查询频道直播观看详情数据
     *
     * @param request   查询参数--必填
     * @param channelId 频道号--必填
     * @param appId     账号appid--必填
     * @param appSecret 账号appSecret--必填
     * @return 观看详情数据
     * @see <a href="https://help.polyv.net/index.html#/live/api/channel/viewdata/viewlog_page">分页查询频道直播观看详情数据</a>
     */
    public static ChannelViewLogPageResponse channelViewLogPage(ChannelViewLogPageRequest request, String channelId, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/statistics/%s/viewlog", channelId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        if (ObjectUtil.isNotNull(request.getPage())) {
            paramMap.put("page", String.valueOf(request.getPage()));
        }
        if (StrUtil.isNotBlank(request.getCurrentDay())) {
            paramMap.put("currentDay", request.getCurrentDay());
        }
        if (ObjectUtil.isNotNull(request.getPageSize())) {
            paramMap.put("pageSize", String.valueOf(request.getPageSize()));
        }
        if (ObjectUtil.isNotNull(request.getStartTime())) {
            paramMap.put("startTime", String.valueOf(request.getStartTime()));
        }
        if (ObjectUtil.isNotNull(request.getEndTime())) {
            paramMap.put("endTime", String.valueOf(request.getEndTime()));
        }
        if (StrUtil.isNotBlank(request.getParam1())) {
            paramMap.put("param1", request.getParam1());
        }
        if (StrUtil.isNotBlank(request.getParam2())) {
            paramMap.put("param2", request.getParam2());
        }
        if (StrUtil.isNotBlank(request.getParam3())) {
            paramMap.put("param3", request.getParam3());
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--分页查询频道直播观看详情数据响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_STATUS.equals(resultJson.getStr("status"))) {
            return resultJson.get("result", ChannelViewLogPageResponse.class);
        }

        log.error("保利威视--分页查询频道直播观看详情数据失败：{}", result);
        return null;
    }


    /**
     * 获取频道观看条件
     *
     * @param channelId 频道号，不传为全局查看
     * @param appId     账号appId-必填
     * @param appSecret 账号appSecret--必填
     * @return
     */
    public static List<ChannelWatchConditionResponse> getWatchCondition(String channelId, String appId, String appSecret) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/auth/get";
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appId", appId);
        requestMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        if (StrUtil.isNotBlank(channelId)) {
            requestMap.put("channelId", channelId);
        }
        requestMap.put("sign", getMd5Sign(requestMap, appSecret));

        String result = HttpUtil.get(requestUrl, new HashMap<>(requestMap));
        log.info("保利威视--获取频道观看条件响应结果：{}", result);
        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSUtil.parseArray(resultJson.getStr("data"), ChannelWatchConditionResponse.class);
        }
        log.info("保利威视--获取频道观看条件失败：{}", result);
        return null;
    }

    /**
     * 设置频道防录屏信息 https://help.polyv.net/index.html#/live/api/player/anti_record_setting
     * 1、通过频道号，设置频道防录屏信息
     * 2、接口支持https协议
     *
     * @param channelId      频道号
     * @param appId          账号appId-必填
     * @param appSecret      账号appSecret--必填
     * @param antiRecordType 防录屏方式  marquee：跑马灯 watermark：水印
     * @param modelType      防录屏类型，水印方式时设置自定义URL无效 fixed：固定值 nickname：登录用户名  diyurl：URL自定义跑马灯
     * @param content        固定值时为设置内容 URL自定义设置时为网址，需要携带 http:// 或 https://  防录屏类型为登录用户名时可不传
     * @param fontSize       字体大小 防录屏方式为跑马灯时：设置数值，范围1-256   防录屏方式为水印时 small：小  middle：中 large：大
     * @param opacity        透明度，不传默认为80，范围0-100
     * @return
     */
    public static Boolean channelRecordSetting(String channelId, String appId, String appSecret, String antiRecordType, String modelType, String content, String fontSize, String opacity) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/anti/record/setting";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));

        paramMap.put("channelId", channelId);
        paramMap.put("antiRecordType", antiRecordType);
        paramMap.put("modelType", modelType);
        if (StrUtil.isNotBlank(content)) {
            paramMap.put("content", content);
        }
        paramMap.put("fontSize", fontSize);
        if (StrUtil.isNotBlank(opacity)) {
            paramMap.put("opacity", opacity);
        }
        // 签名
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));
        // 请求
        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--上传水印/跑马灯响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        return SUCCESS_CODE.equals(resultJson.getInt("code"));
    }

    /**
     * 根据频道号获取频道信息
     *
     * @param channelId 频道号--必填
     * @param appId     账号appid--必填
     * @param appSecret 账号appSecret--必填
     * @return
     */
    public static PLChannelGetResult getChannelByChannelId(String channelId, String appId, String appSecret) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/channels/%s/get", channelId);
        Map<String, Object> map = new HashedMap<>();
        map.put("appId", appId);
        map.put("timestamp", System.currentTimeMillis());
        map.put("sign", getSign(appSecret, map));
        String result = HttpUtil.get(requestUrl, new HashMap<>(map));
        JSONObject resultJson = JSONUtil.parseObj(result);
        if ("success".equalsIgnoreCase(resultJson.getStr("status"))) {
            log.info("获取成功，报文={}", resultJson);
            return resultJson.get("data", PLChannelGetResult.class);
        }
        log.error("获取失败，原因={}", resultJson);
        return null;
    }


    /**
     * 裁剪录制文件
     *
     * @param channelId       频道号 - 必填
     * @param fileId          文件ID - 必填
     * @param deleteTimeFrame 要裁剪移除的时间区间 - 必填
     *                        传入格式要求：
     *                        （1）start、end值是单位为秒的数值，格式为：[{"start":xx, "end":xx},{"start":xx, "end":xx}]
     *                        （2）多个区间请按照时间顺序从小到大排列
     *                        （3）时间区间不允许重叠
     *                        （4）时间区间不得超过100个限制
     *                        【示例：要裁剪移除的是第1s到第14s，以及第25s到第30s的区间，则传入的字符串为 [{"start":1, "end":14},{"start":25, "end":30}] 】
     *                        误差说明：
     *                        （1）裁剪区间存在一定的时间误差
     *                        （2）误差原因：回放默认为m3u8格式裁剪，由于m3u8的视频裁剪是精确到每片ts，所以裁剪时会判断裁剪要移除的时间区间所包含的ts片，进行裁剪移除，如需更精准的裁剪，可联系保利威客服人员开通mp4裁剪 - 非必填
     * @param appId           账号appId - 必填
     * @param appSecret       应用密匙 - 必填
     * @param callbackUrl     裁剪成功或失败回调的url - 非必填
     * @param fileName        裁剪后文件名 - 非必填
     * @return 转存发起结果
     * @see <a href="https://help.polyv.net/#/live/api/channel/playback/clip_record_file">裁剪录制文件</a>
     */
    public static Boolean recordClip(String channelId, String fileId, String deleteTimeFrame, String appId, String appSecret, String callbackUrl, String fileName) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/record/clip";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("fileId", fileId);
        paramMap.put("deleteTimeFrame", deleteTimeFrame);
        // 是否自动转存回放到点播 默认为N
//        paramMap.put("autoConvert", "Y");
        if (StrUtil.isNotBlank(fileName)) {
            paramMap.put("fileName", fileName);
        }
        if (StrUtil.isNotBlank(callbackUrl)) {
            paramMap.put("callbackUrl", callbackUrl);
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--裁剪录制文件响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--裁剪录制文件失败：{}", result);
        return false;
    }

    /**
     * 合并直播录制
     *
     * @param channelId   频道号 - 必填
     * @param fileIds     要合并的录制视频文件ID，多个id用英文逗号, 分隔 - 必填
     * @param appId       账号appId - 必填
     * @param appSecret   应用密匙 - 必填
     * @param callbackUrl 合并成功或失败回调的url，可以带上自定义参数 - 非必填
     * @param fileName    裁剪后文件名 - 非必填
     * @return 转存发起结果
     * @see <a href="https://help.polyv.net/#/live/api/channel/playback/record_file_merge_async">合并直播录制</a>
     */
    public static Boolean recordMerge(String channelId, String fileIds, String appId, String appSecret, String callbackUrl, String fileName) {
        String requestUrl = "http://api.polyv.net/live/v3/channel/record/merge";

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("channelId", channelId);
        paramMap.put("fileIds", fileIds);
        // 是否自动转存回放到点播 默认为N
//        paramMap.put("autoConvert", "Y");
        // 合并后文件类型，默认为N （Y：合并为MP4文件 ,N：合并为m3u8文件）
//        paramMap.put("mergeMp4", "Y");
        if (StrUtil.isNotBlank(fileName)) {
            paramMap.put("fileName", fileName);
        }
        if (StrUtil.isNotBlank(callbackUrl)) {
            paramMap.put("callbackUrl", callbackUrl);
        }
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--合并直播录制响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--合并直播录制失败：{}", result);
        return false;
    }

    public static Boolean setDiyurlMarquee(String channelId, String appId, String appSecret, String marqueeRestrict) {
        String requestUrl = String.format("http://api.polyv.net/live/v2/channelRestrict/%s/set-diyurl-marquee", channelId);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appId", appId);
        paramMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
        paramMap.put("marqueeRestrict", marqueeRestrict);
        paramMap.put("sign", getMd5Sign(paramMap, appSecret));

        String result = HttpUtil.post(requestUrl, new HashMap<>(paramMap));
        log.info("保利威视--跑马灯设置响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return true;
        }

        log.error("保利威视--跑马灯设置失败：{}", result);
        return null;
    }


    /**
     * 获取保利威直播回调签名
     *
     * @param timestamp 回调时间戳
     * @param appSecret 签名秘钥
     * @return 签名结果
     */
    public static String getCallbackSign(Long timestamp, String appSecret) {
        return DigestUtil.md5Hex(appSecret + timestamp);
    }

    /**
     * 获取外部授权地址
     *
     * @param secretKey 签名秘钥
     * @param userId    用户ID
     * @param ts        当前时间戳
     * @return 观看签名
     */
    public static String getExternalAuthSign(String secretKey, String userId, Long ts) {
        return MD5Util.md5(secretKey + userId + secretKey + ts);
    }

    /**
     * 获取参数签名
     *
     * @param appSecret 账号appSecret
     * @param paramMap  签名参数
     * @return 签名信息
     */
    public static String getSign(String appSecret, Map<String, Object> paramMap) {
        if (paramMap.isEmpty()) {
            return "";
        }
        SortedMap<String, Object> requestMap = new TreeMap<>(paramMap);
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(appSecret);
        for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
            if (entry.getValue() != null) {
                stringBuffer.append(entry.getKey()).append(entry.getValue());
            }
        }
        stringBuffer.append(appSecret);
        return MD5Util.md5(stringBuffer.toString()).toUpperCase();
    }

    /**
     * 获取参数签名
     *
     * @param appSecret 账号appSecret
     * @param paramMap  签名参数
     * @return 签名信息
     */
    public static String getSign2(String appSecret, Map<String, String> paramMap) {
        if (paramMap.isEmpty()) {
            return "";
        }
        SortedMap<String, Object> requestMap = new TreeMap<>(paramMap);
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(appSecret);
        for (Map.Entry<String, Object> entry : requestMap.entrySet()) {
            if (entry.getValue() != null) {
                stringBuffer.append(entry.getKey()).append(entry.getValue());
            }
        }
        stringBuffer.append(appSecret);
        return MD5Util.md5(stringBuffer.toString()).toUpperCase();
    }

    /**
     * 获取MD5签名
     *
     * @param paramMap  签名参数
     * @param appSecret 签名秘钥
     * @return 签名
     */
    private static String getMd5Sign(Map<String, String> paramMap, String appSecret) {
        // 参数名字典顺序排列
        List<String> keyList = new ArrayList<>(paramMap.keySet());
        Collections.sort(keyList);

        StringBuilder param = new StringBuilder();
        param.append(appSecret);

        for (String key : keyList) {
            String val = paramMap.get(key);
            if (StrUtil.isNotBlank(val)) {
                param.append(key).append(val);
            }
        }
        param.append(appSecret);
        log.debug("签名原始字符串：{}", param);

        String sign = SecureUtil.md5(param.toString()).toUpperCase();
        log.debug("签名结果：{}", sign);
        return sign;
    }

    /**
     * 获取请求地址
     *
     * @param url      接口地址
     * @param paramMap 请求参数
     * @return 请求地址
     */
    private static String getRequestUrl(String url, Map<String, String> paramMap) {
        StringBuilder requestUrl = new StringBuilder(url);

        boolean checkResult = url.endsWith("?");
        if (!checkResult) {
            requestUrl.append("?");
        }


        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            requestUrl.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        requestUrl.deleteCharAt(requestUrl.length() - 1);
        return requestUrl.toString();
    }
}
