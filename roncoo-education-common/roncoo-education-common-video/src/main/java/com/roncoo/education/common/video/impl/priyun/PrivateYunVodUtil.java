/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.video.impl.priyun;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunConfigResp;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunPlayTokenRes;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoInfoResp;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 私有化点播工具类
 *
 * @author wujing
 */
@Slf4j
public final class PrivateYunVodUtil {

    /**
     * 成功编码
     */
    private static final Integer SUCCESS_CODE = 200;

    private PrivateYunVodUtil() {
    }

    public static PrivateYunConfigResp getUploadConfig(String vcloudAccessKeyId, String vcloudAccessKeySecret, String vcloudUrl, String callbackUrl, String state) {
        PrivateYunConfigResp privateVodConfig = new PrivateYunConfigResp();
        privateVodConfig.setAccessKeyId(vcloudAccessKeyId);
        privateVodConfig.setUploadUrl(vcloudUrl + "v1/upload/");
        privateVodConfig.setCallbackUrl(callbackUrl);
        privateVodConfig.setState(state);
        privateVodConfig.setSignatureNonce(String.valueOf(System.currentTimeMillis()));
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", privateVodConfig.getAccessKeyId());
        paramMap.put("signatureNonce", privateVodConfig.getSignatureNonce());
        privateVodConfig.setSign(getSign(paramMap, vcloudAccessKeySecret));
        return privateVodConfig;
    }


    /**
     * 查询视频信息
     *
     * @param vcloudUrl   点播私有化域名路径
     * @param accessKeyId 私有化配置accessKeyId
     * @param secretKey   私有化配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @return
     */
    public static PrivateYunVideoInfoResp getVideoInfo(String vcloudUrl, String accessKeyId, String secretKey, String videoVid, String state) {
        String requestUrl = vcloudUrl + "v1/video/detail";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        paramMap.put("videoVid", videoVid);
        paramMap.put("state", state);
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap));
        log.info("私有化视--查询视频信息响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSONUtil.toBean(resultJson.getStr("data"), PrivateYunVideoInfoResp.class);
        }

        log.info("私有化视--查询视频信息失败：{}", result);
        return null;
    }

    /**
     * 删除视频
     *
     * @param vcloudUrl   点播私有化域名路径
     * @param accessKeyId 私有化配置accessKeyId
     * @param secretKey   私有化配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @return
     */
    public static String delete(String vcloudUrl, String accessKeyId, String secretKey, String videoVid, String state) {
        String requestUrl = vcloudUrl + "v1/video/delete";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        paramMap.put("videoVid", videoVid);
        paramMap.put("state", state);
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap));
        log.info("私有化视--删除视频响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getStr("msg");
        }

        log.info("私有化视--删除视频响应结果失败：{}", result);
        return resultJson.getStr("data");
    }


    /**
     * token接口
     *
     * @param vcloudUrl   点播私有化域名路径
     * @param authUrl     授权地址，请求播放时调用该接口
     * @param accessKeyId 私有化配置accessKeyId
     * @param secretKey   私有化配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @param expires     过期时间，单位：S（默认0，不过期）
     * @param viewId      观看者id，建议不同的观看者使用不同的id
     * @return
     */
    public static PrivateYunPlayTokenRes token(String vcloudUrl, String authUrl, String accessKeyId, String secretKey, String videoVid, String state, String expires, String viewId) {
        String requestUrl = vcloudUrl + "v1/video/token";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        paramMap.put("videoVid", videoVid);
        paramMap.put("state", state);
        paramMap.put("expires", expires);
        paramMap.put("viewId", viewId);
        paramMap.put("authUrl", authUrl);
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap));
        log.info("私有化视--获取token响应结果：{}", result);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSONUtil.toBean(resultJson.getStr("data"), PrivateYunPlayTokenRes.class);
        }

        log.info("私有化视--获取token响应结果：{}", result);
        return null;
    }

    private static String getSign(Object param, String key) {
        // 排序
        Map<String, Object> paramMap = JSONUtil.parseObj(JSONUtil.toJsonStr(param));
        SortedMap<String, Object> treeMap = new TreeMap<>(paramMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            if (!"sign".equals(entry.getKey()) && StrUtil.isNotBlank(String.valueOf(entry.getValue()))) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        sb.append("key=").append(key);
        return SecureUtil.md5(sb.toString());
    }

}
