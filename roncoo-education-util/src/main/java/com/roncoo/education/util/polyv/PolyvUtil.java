/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.aliyun.oas.utils.StringUtil;
import com.ning.http.util.Base64;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.util.tools.MD5Util;
import com.roncoo.education.util.tools.SHA1Util;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.http.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 保利威视工具类
 *
 * @author wujing
 */
public final class PolyvUtil {

    private static final Logger logger = LoggerFactory.getLogger(PolyvUtil.class);

    private static final String KEY = "roncoo-cloud"; // 长度为12
    private static final String CHARSET_UTF_8 = "UTF-8";

    private PolyvUtil() {
    }

    /**
     * 获取code
     *
     * @param polyvCode
     * @return
     */
    public static String getPolyvCode(PolyvCode polyvCode) {
        try {
            return HttpUtil.encode(
                    Base64.encode(SecureUtil.des(Base64.decode(KEY)).encrypt(JSONUtil.toJSONString(polyvCode))),
                    CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error("保利威视，加密出错", e);
            return "";
        }
    }

    /**
     * code解密
     *
     * @param code
     * @return
     */
    public static PolyvCode decode(String code) {
        try {
            return JSONUtil.parseObject(new String(
                            SecureUtil.des(Base64.decode(KEY)).decrypt(Base64.decode(HttpUtil.decode(code, CHARSET_UTF_8)))),
                    PolyvCode.class);
        } catch (Exception e) {
            logger.error("保利威视，解密出错", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static PolyvSignResult getSignForH5(PolyvSign bo, String useid, String secretkey) {
        // 根据时间戳、vid、密钥生成sign值
        String ts = String.valueOf(System.currentTimeMillis());
        // 获取播放token
        Map<String, Object> map = new HashedMap<>();
        map.put("userId", useid);
        map.put("videoId", bo.getVid());
        map.put("ts", ts);
        map.put("viewerIp", "127.0.0.1");
        map.put("viewerName", bo.getUserNo());
        map.put("extraParams", "HTML5");
        map.put("viewerId", bo.getUserNo());
        logger.info("保利威视，map：map={}", map);
        String concated = "extraParams" + map.get("extraParams") + "ts" + map.get("ts") + "userId" + map.get("userId") + "videoId" + map.get("videoId") + "viewerId" + map.get("viewerId") + "viewerIp" + map.get("viewerIp") + "viewerName" + map.get("viewerName");
        map.put("sign", MD5Util.MD5(secretkey + concated + secretkey).toUpperCase());
        String result = post(SystemUtil.POLYV_GETTOKEN, map);
        logger.info("保利威视，获取token接口：result={}", result);
        Map<String, Object> resultMap = JSONUtil.parseObject(result, HashMap.class);
        int code = Integer.valueOf(resultMap.get("code").toString()).intValue();
        if (code != 200) {
            return null;
        }
        Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
        if (data == null || data.isEmpty()) {
            return null;
        }
        PolyvSignResult dto = new PolyvSignResult();
        dto.setSign(MD5Util.MD5(secretkey + bo.getVid() + ts));
        dto.setTs(ts);
        dto.setToken(data.get("token").toString());
        return dto;
    }

    public static UploadFileResult getVideo(String vid, String secretkey, String useid) {
        Map<String, Object> param = new TreeMap<String, Object>();
        param.put("vid", vid);
        param.put("format", "json");
        param.put("ptime", String.valueOf(System.currentTimeMillis()));// 当前13位毫秒级时间戳
        StringBuilder signStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            signStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        signStr = signStr.deleteCharAt(0);
        signStr.append(secretkey);
        param.put("sign", SHA1Util.getSign(signStr.toString()));
        String s = post("http://api.polyv.net/v2/video/{userid}/get-video-msg".replace("{userid}", useid), param);
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> m = JSONUtil.parseObject(s, HashMap.class);
            if (m.get("code").toString().equals("200")) {
                // 成功
                return JSONUtil.parseArray(JSONUtil.toJSONString(m.get("data")), UploadFileResult.class).get(0);
            }
            logger.error("获取视频失败，原因为：{}", s);
        } catch (Exception e) {
            logger.error("获取视频失败异常，结果={}，参数={}", s, param);
        }
        return null;
    }

    /**
     * 上传视频 http://dev.polyv.net/2014/videoproduct/v-api/v-api-upload/uploadfile/
     *
     * @param file
     * @return
     */
    public static UploadFileResult uploadFile(File file, UploadFile uploadFile, String writetoken) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("writetoken", writetoken);
        param.put("JSONRPC", "{\"title\": \"" + uploadFile.getTitle() + "\", \"tag\": \"" + uploadFile.getTag()
                + "\", \"desc\": \"" + uploadFile.getDesc() + "\"}");
        param.put("cataid", uploadFile.getCataid());
        if (StringUtil.isNotBlank(uploadFile.getWatermark())) {
            param.put("watermark", uploadFile.getWatermark());
        }
        String result = postFile(SystemUtil.POLYV_UPLOADVIDEO, param, file);
        try {
            JSONObject json = JSONObject.fromObject(result);
            if ("0".equals(json.getString("error"))) {
                return JSONUtil.parseArray(json.getString("data"), UploadFileResult.class).get(0);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        logger.error("保利威视，上传视频失败，原因={}", result);
        return null;
    }

    /**
     * 删除视频
     */
    public static String deleteFile(String vid, String useid, String secretkey) {
        SortedMap<String, String> paramMap = new TreeMap<>();
        paramMap.put("userid", useid);// 用户ID
        paramMap.put("vid", vid);// 视频ID
        paramMap.put("ptime", String.valueOf(System.currentTimeMillis()));// 当前13位毫秒级时间戳
        StringBuilder signStr = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            signStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        signStr = signStr.deleteCharAt(0);
        signStr.append(secretkey);
        String sign = SHA1Util.getSign(signStr.toString());
        paramMap.put("sign", sign);
        String url = SystemUtil.POLYV_DELETEVIDEO.replace("{userid}", useid);
        HttpPost httpPost = new HttpPost(url.trim());
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
                .setConnectionRequestTimeout(3600000).setSocketTimeout(3600000).build();
        httpPost.setConfig(requestConfig);
        List<BasicNameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        StringEntity se = null;
        try {
            se = new UrlEncodedFormEntity(nvps, CHARSET_UTF_8);
            httpPost.setEntity(se);
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error("保利威视，删除视频失败");
        }
        return null;
    }

    /**
     * 上传问题接口
     */
    public static QuestionResult uploadQuestion(Question question, String writetoken) {
        try {
            String url = SystemUtil.POLYV_QUESTION;
            HttpPost httpPost = new HttpPost(url.trim());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
                    .setConnectionRequestTimeout(3600000).setSocketTimeout(3600000).build();
            httpPost.setConfig(requestConfig);
            JSONArray choices = new JSONArray();
            for (String value : question.getAnswerList()) {
                JSONObject answer = new JSONObject();
                answer.put("answer", value);
                choices.add(answer);
            }
            JSONObject righeAnswer = new JSONObject();
            righeAnswer.put("answer", question.getRightAnswer());
            righeAnswer.put("right_answer", question.getRight());
            choices.add(righeAnswer);
            List<BasicNameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("method", "saveExam"));
            nvps.add(new BasicNameValuePair("writetoken", writetoken));
            nvps.add(new BasicNameValuePair("vid", question.getVid()));
            nvps.add(new BasicNameValuePair("examId", question.getExamId()));
            nvps.add(new BasicNameValuePair("seconds", String.valueOf(question.getSeconds())));
            nvps.add(new BasicNameValuePair("question", question.getQuestion()));
            nvps.add(new BasicNameValuePair("choices", choices.toString()));
            nvps.add(new BasicNameValuePair("skip", String.valueOf(question.isSkip())));
            nvps.add(new BasicNameValuePair("answer", question.getAnswer()));
            nvps.add(new BasicNameValuePair("wrongShow", String.valueOf(question.getWrongShow())));
            nvps.add(new BasicNameValuePair("wrongTime", String.valueOf(question.getWrongTime())));
            StringEntity se = new UrlEncodedFormEntity(nvps, CHARSET_UTF_8);
            httpPost.setEntity(se);
            HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
            String resultStr = EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
            return (QuestionResult) JSONObject.toBean(JSONObject.fromObject(resultStr), QuestionResult.class);
        } catch (Exception e) {
            logger.error("添加问题失败！");
        }
        return null;
    }

    /**
     * 获取sign
     *
     * @param paramMap
     * @return
     */
    public static String getSign(Map<String, Object> paramMap, String secretkey) {
        if (paramMap.isEmpty()) {
            return "";
        }
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            String value = String.valueOf(m.getValue());
            if (StringUtils.hasText(value)) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        String argPreSign = stringBuffer.append("&paySecret=").append(secretkey).toString();
        return MD5Util.MD5(argPreSign).toUpperCase();
    }

    /**
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, Map<String, Object> param) {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
                    .setConnectionRequestTimeout(3600000).setSocketTimeout(3600000).build();
            httpPost.setConfig(requestConfig);
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, Object> m : param.entrySet()) {
                list.add(new BasicNameValuePair(m.getKey(), m.getValue().toString()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
            }
        }
        return "";
    }

    private static String postFile(String url, Map<String, Object> param, File file) {
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        try {
            HttpPost httpPost = new HttpPost(url.trim());
            httpPost.setConfig(RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(3600000)
                    .setSocketTimeout(3600000).build());
            MultipartEntityBuilder entity = MultipartEntityBuilder.create();
            entity.addPart("Filedata", new FileBody(file));
            ContentType contentType = ContentType.create("text/plain", Charset.forName(CHARSET_UTF_8));
            for (Map.Entry<String, Object> m : param.entrySet()) {
                entity.addPart(m.getKey(), new StringBody(m.getValue().toString(), contentType));
            }
            httpPost.setEntity(entity.build());
            return EntityUtils.toString(closeableHttpClient.execute(httpPost).getEntity(), CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error("上传到保利威视失败，url={},param={}", url, param, e);
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
            }
        }
        return "";
    }

}
