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
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunInfoResp;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunPlayTokenRes;
import com.roncoo.education.common.video.impl.priyun.resp.PrivateYunVideoInfoResp;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 私有云点播工具类
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

    /**
     * 查询视频云信息
     *
     * @param url         点播私有云域名路径
     * @param accessKeyId 私有云配置accessKeyId
     * @param secretKey   私有云配置accessKeyIdsecretKey
     * @return
     */
    public static PrivateYunInfoResp getInfo(String url, String accessKeyId, String secretKey) {
        String requestUrl = url + "v1/video/info";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap));

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSONUtil.toBean(resultJson.getStr("data"), PrivateYunInfoResp.class);
        }

        log.error("私有云--查询账号信息失败：{}", result);
        return null;
    }

    public static PrivateYunConfigResp getUploadConfig(String vcloudAccessKeyId, String vcloudAccessKeySecret, String url, String callbackUrl, String state) {
        PrivateYunConfigResp privateVodConfig = new PrivateYunConfigResp();
        privateVodConfig.setAccessKeyId(vcloudAccessKeyId);
        privateVodConfig.setUploadUrl(url + "v1/upload/");
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
     * @param url         点播私有云域名路径
     * @param accessKeyId 私有云配置accessKeyId
     * @param secretKey   私有云配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @return
     */
    public static PrivateYunVideoInfoResp getVideoInfo(String url, String accessKeyId, String secretKey, String videoVid, String state) {
        String requestUrl = url + "v1/video/detail";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        paramMap.put("videoVid", videoVid);
        paramMap.put("state", state);
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap), 60000);

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSONUtil.toBean(resultJson.getStr("data"), PrivateYunVideoInfoResp.class);
        }

        log.error("私有云--查询视频信息失败：{}", result);
        return null;
    }

    /**
     * 删除视频
     *
     * @param url         点播私有云域名路径
     * @param accessKeyId 私有云配置accessKeyId
     * @param secretKey   私有云配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @return
     */
    public static String delete(String url, String accessKeyId, String secretKey, String videoVid, String state) {
        String requestUrl = url + "v1/video/delete";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accessKeyId", accessKeyId);
        paramMap.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        // 签名
        paramMap.put("sign", getSign(paramMap, secretKey));
        paramMap.put("videoVid", videoVid);
        paramMap.put("state", state);
        // 请求
        String result = HttpUtil.post(requestUrl, JSONUtil.toJsonStr(paramMap));

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return resultJson.getStr("msg");
        }

        log.error("私有云--删除视频响应结果失败：{}", result);
        return resultJson.getStr("data");
    }


    /**
     * token接口
     *
     * @param url         点播私有云域名路径
     * @param authUrl     授权地址，请求播放时调用该接口
     * @param accessKeyId 私有云配置accessKeyId
     * @param secretKey   私有云配置accessKeyIdsecretKey
     * @param videoVid    视频vid
     * @param state       自定义字段（企业ID）
     * @param expires     过期时间，单位：S（默认0，不过期）
     * @param viewId      观看者id，建议不同的观看者使用不同的id
     * @return
     */
    public static PrivateYunPlayTokenRes token(String url, String authUrl, String accessKeyId, String secretKey, String videoVid, String state, String expires, String viewId) {
        String requestUrl = url + "v1/video/token";
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

        JSONObject resultJson = JSONUtil.parseObj(result);
        if (SUCCESS_CODE.equals(resultJson.getInt("code"))) {
            return JSONUtil.toBean(resultJson.getStr("data"), PrivateYunPlayTokenRes.class);
        }

        log.error("私有云--获取token响应结果：{}", result);
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
