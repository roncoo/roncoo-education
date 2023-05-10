package com.roncoo.education.common.video.impl.polyv;

import com.roncoo.education.common.core.tools.JSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author: sadboy
 **/
@Slf4j
public class PolyvHttpUtil {

    public static final String KEY = "rc-education";
    public static final String SUCCESS_STATUS = "success";
    public static final Integer SUCCESS_CODE = 200;

    private static final String UTF8 = "UTF-8";
    private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    private static final String TEXT_PLAIN = "text/plain; charset=UTF-8";
    private static final String TEXT_HTML = "text/html; charset=UTF-8";
    private static final String APPLICATION_XML = "application/xml; charset=UTF-8";

    private PolyvHttpUtil() {
    }


    /**
     * 向url发送get请求
     *
     * @param url      请求url
     * @param paramMap 需要拼接的参数
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String get(String url, Map<String, Object> paramMap) {
        try {
            url = appendUrl(url, paramMap);
            return get(url, UTF8, EntityUtils::toString);
        } catch (Exception e) {
            log.error("请求错误，url={}", url, e);
            return "";
        }
    }

    /**
     * HTTP GET 内部公共请求处理逻辑
     *
     * @param url       请求地址
     * @param encoding  编码字符集， 默认为 utf-8
     * @param dataParse 返回数据反序列化逻辑实现类
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    private static <T> T get(String url, String encoding, DataParse<T> dataParse) throws IOException {
        log.debug("http get url: {}", url);
        T result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", APPLICATION_JSON);
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = sendRequestAndGetResult(url, httpClient, httpGet);
        // 获取结果实体
        if (null != response) {
            result = dataParse.parseData(response.getEntity(), encoding);
            if (!(result instanceof byte[])) {
                log.debug("http 请求结果: {}", result);
            } else {
                Header[] headers = response.getHeaders("Content-Type");
                for (Header responseHead : headers) {
                    String headStr = responseHead.getValue();
                    if (headStr.startsWith("application/json")) {
                        String json = new String((byte[]) result);
                        response.close();
                        throw new RuntimeException(json);
                    }
                }
            }
        }
        try {
            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 向url发送post请求表单提交数据
     *
     * @param url      请求url
     * @param paramMap 表单数据
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String post(String url, Map<String, Object> paramMap) {
        log.debug("http 请求 url: {} , 请求参数: {}", url, appendUrl("", paramMap).replace("?", ""));
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (paramMap != null) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                String value = entry.getValue().toString();
                //去掉如下判断会造成String类型的value为null时
                if (value != null) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        // 设置参数到请求对象中
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, UTF8));
        } catch (UnsupportedEncodingException e) {
            log.error("请求异常，url={}, paramMap={}", url, JSUtil.toJsonString(paramMap), e);
        }
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", APPLICATION_FORM_URLENCODED);
        return post(url, httpPost, UTF8, EntityUtils::toString);
    }

    /**
     * 向url发送post请求发送json
     *
     * @param url 请求url
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postForJson(String url, Map<String, Object> paramMap) {
        return postForJson(url, paramMap, "");
    }

    /**
     * 向url发送post请求发送json
     *
     * @param url  请求url
     * @param json json字符串
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postForJson(String url, Map<String, Object> paramMap, String json) {
        // 创建post方式请求对象
        url = appendUrl(url, paramMap);
        HttpPost httpPost = new HttpPost(url);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        //  Constant.UTF8
        stringEntity.setContentEncoding(UTF8);
        httpPost.setEntity(stringEntity);
        return post(url, httpPost, UTF8, EntityUtils::toString);
    }

    /**
     * 向url发送post请求
     *
     * @param url      请求url
     * @param httpPost httpClient
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static <T> T post(String url, HttpPost httpPost, String encoding, DataParse<T> dataParse) {
        log.debug("http post url: {} , 请求参数: {}", url, JSUtil.toJsonString(httpPost));

        T result = null;
        CloseableHttpResponse response;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            // 执行请求操作，并拿到结果（同步阻塞）
            response = sendRequestAndGetResult(url, httpClient, httpPost);
            // 获取结果实体
            // 判断网络连接状态码是否正常(0--200都数正常)
            if (null != response) {
                result = dataParse.parseData(response.getEntity(), encoding);
                log.debug("http 请求结果: {}", result);
            }

            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 设置http头，发送http请求，打印请求耗时
     *
     * @param url            请求url
     * @param httpClient     httpClient
     * @param httpUriRequest httpUriRequest
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static CloseableHttpResponse sendRequestAndGetResult(String url, CloseableHttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        long endTime = System.currentTimeMillis();
        collectAPISpendTime(url, startTime, endTime);
        return response;
    }


    /**
     * 向url发送post请求上传单文件
     *
     * @param url      请求url
     * @param paramMap 需要表单提交的参数
     * @param fileMap  需要上传的文件
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postFile(String url, Map<String, Object> paramMap, Map<String, File> fileMap, String encoding)
            throws IOException {
        if (fileMap != null) {
            Map<String, List<File>> fileListMap = new HashMap<>();
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                fileListMap.put(entry.getKey(), fileList);
            }
            return postMultipleFile(url, paramMap, fileListMap, encoding);
        }
        return postMultipleFile(url, paramMap, null, encoding);
    }

    /**
     * 向url发送post请求上传多文件
     * 向url发送post请求上传单文件
     *
     * @param url         请求url
     * @param paramMap    需要表单提交的参数
     * @param fileListMap 需要上传的文件
     * @param encoding    编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postMultipleFile(String url, Map<String, Object> paramMap, Map<String, List<File>> fileListMap,
                                          String encoding) throws IOException {
        return postFileBody(url, paramMap, fileListMap, encoding, EntityUtils::toString);
    }

    /**
     * 向url发送post请求上传多文件
     * 向url发送post请求上传单文件
     *
     * @param url         请求url
     * @param paramMap    需要表单提交的参数
     * @param fileListMap 需要上传的文件
     * @param encoding    编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static <T> T postFileBody(String url, Map<String, Object> paramMap, Map<String, List<File>> fileListMap,
                                      String encoding, DataParse<T> dataParse) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, appendUrl("", paramMap).replace("?", ""));
        encoding = encoding == null ? UTF8 : encoding;
        T result = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

        ContentType contentType = ContentType.create("text/plain", Charset.forName(encoding));
        if (null != paramMap) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue().toString(), contentType);
            }
        }

        if (null != fileListMap) {
            for (Map.Entry<String, List<File>> entry : fileListMap.entrySet()) {
                String key = entry.getKey();
                List<File> fileList = entry.getValue();
                for (File file : fileList) {
                    FileBody fileBody = new FileBody(file);
                    entityBuilder.addPart(key, fileBody);
                }
            }
        }

        HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = sendRequestAndGetResult(url, httpClient, httpPost);
        if (null != response) {
            result = dataParse.parseData(response.getEntity(), encoding);
            log.debug("http 请求结果: {}", result);
        }
        try {
            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 将url与map拼接成HTTP查询字符串
     *
     * @param url      请求url
     * @param paramMap 需要拼装的map
     * @return 拼装好的url
     */
    public static String appendUrl(String url, Map<String, Object> paramMap) {
        if (paramMap == null) {
            return url;
        }
        StringBuffer paramStringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, Object>> mapIterator = paramMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Object> next = mapIterator.next();
            try {
                paramStringBuffer.append(next.getKey()).append("=").append(URLEncoder.encode(next.getValue().toString(), UTF8)).append("&");
            } catch (UnsupportedEncodingException e) {
                log.error("错误", e);
            }
        }
        String paramStr = paramStringBuffer.toString();
        if (paramStr != null && !"".equals(paramStr)) {
            if (url.indexOf("?") > 0) {
                if (url.endsWith("&")) {
                    url += paramStr.substring(0, paramStr.length() - 1);
                } else {
                    url += "&" + paramStr.substring(0, paramStr.length() - 1);
                }
            } else {
                url += "?" + paramStr.substring(0, paramStr.length() - 1);
            }
        }
        return url;
    }

    /**
     * 把二进制写入文件
     *
     * @param bytes
     * @param path
     * @throws IOException
     */
    private static void writeFile(byte[] bytes, String path) throws IOException {
        OutputStream os = null;
        try {
            // 根据绝对路径初始化文件
            File localFile = new File(path);
            if (!localFile.exists()) {
                boolean newFile = localFile.createNewFile();
                if (!newFile) {
                    throw new RuntimeException("创建文件异常，路径：" + path);
                }
            }
            // 输出流
            os = new FileOutputStream(localFile);
            os.write(bytes);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * 公共数据解析接口
     *
     * @param <T>
     */
    private interface DataParse<T> {
        /**
         * 解析返回数据
         *
         * @param httpEntity 返回实体
         * @param encoding   编码
         * @return 实际解析返回内容
         * @throws IOException io异常
         */
        T parseData(HttpEntity httpEntity, String encoding) throws IOException;

    }

    /**
     * 打印请求信息
     *
     * @param url       请求url
     * @param startTime 请求开始时间
     * @param endTime   请求结束时间
     */
    private static void collectAPISpendTime(String url, long startTime, long endTime) {
        log.debug("HTTP请求耗时分析，请求URL: {} ， 耗时: {} ms", url, endTime - startTime);
    }


}

