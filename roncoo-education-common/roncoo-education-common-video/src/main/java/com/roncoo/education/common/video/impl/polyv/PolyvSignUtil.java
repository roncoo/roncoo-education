package com.roncoo.education.common.video.impl.polyv;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * polyv 直播签名工具类
 *
 * @author: thomas
 **/
@Slf4j
public class PolyvSignUtil {

    public static final String ALGORITHM_MD5 = "MD5";
    public static final String ALGORITHM_SHA256 = "SHA-256";

    private PolyvSignUtil() {
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

    public static String getSha1Sign(Map<String, Object> params, String secretKey) {
        //log.debug("参与签名参数：{}", JSONUtil.toJsonStr(params));
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        // 拼接参数
        StringBuilder param = new StringBuilder();
        for (String key : keys) {
            if (null != params.get(key)) {
                param.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        if (StrUtil.isNotBlank(param.toString())) {
            param = new StringBuilder(param.substring(0, param.length() - 1));
        }
        param.append(secretKey);
        //log.debug("签名原始字符串：{}", param);

        // 签名加密
        String sign = SecureUtil.sha1(param.toString()).toUpperCase();
        //log.debug("签名结果：{}", sign);
        return sign;
    }

    /**
     * 获取直播加密字符串，并且假如到参数params中
     *
     * @param params    加密参数
     * @param appSecret 保利威用户签名密钥
     * @return SHA256签名字符串
     * @throws NoSuchAlgorithmException     签名异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String getSHA256Sign(Map<String, Object> params, String appSecret) {
        // 处理参数，计算SHA256哈希值
        // log.debug("参与签名参数：{}", params);
        String concatStr = concatParams(params);
        String plain = appSecret + concatStr + appSecret;
        //log.debug("签名原始字符串：{}", plain);
        String encrypted = sha256Hex(plain).toUpperCase();
        // log.debug("签名结果： {}", encrypted);
        // 大写SHA256值
        return encrypted;
    }

    /**
     * 获取直播加密字符串，并且假如到参数params中
     *
     * @param params    加密参数
     * @param appSecret 保利威用户签名密钥
     * @return MD5签名字符串
     * @throws NoSuchAlgorithmException     签名异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String getMd5Sign(Map<String, Object> params, String appSecret) {
        // 处理参数，计算MD5哈希值
        String concatStr = concatParams(params);
        String plain = appSecret + concatStr + appSecret;
        String encrypted = md5Hex(plain).toUpperCase();
        // 32位大写MD5值
        return encrypted;
    }

    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式成字符串，用于计算MD5哈希值
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String concatParams(Map<String, Object> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            Object value = params.get(key);
            if (value == null || "".equals(value.toString())) {
                continue;
            }
            sb.append(key).append(value);
        }
        return sb.toString();
    }

    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     *
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException     签名异常
     * @throws UnsupportedEncodingException 编码异常
     */
    private static String md5Hex(String text) {
        try {
            return digestHex(text, ALGORITHM_MD5);
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5加密出错", e);
            throw new RuntimeException("MD5加密出错");
        }
    }

    /**
     * 对字符串做sha256加密，返回加密后的字符串。
     *
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException     签名异常
     * @throws UnsupportedEncodingException 编码异常
     */
    private static String sha256Hex(String text) {
        try {
            return digestHex(text, ALGORITHM_SHA256);
        } catch (NoSuchAlgorithmException e) {
            log.error("sha256加密出错", e);
            throw new RuntimeException("sha256加密出错");
        }
    }

    /**
     * 对字符串做加密，返回加密后的字符串。
     *
     * @param text      待加密的字符串。
     * @param algorithm 算法类型。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException 签名异常
     */
    private static String digestHex(String text, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] inputByteArray = text.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(inputByteArray);
        byte[] resultByteArray = messageDigest.digest();
        return byteArrayToHex(resultByteArray).toLowerCase();

    }

    /**
     * 将字节数组换成成16进制的字符串
     *
     * @param byteArray 字节
     * @return 字符串
     */
    private static String byteArrayToHex(byte[] byteArray) {
        // 初始化一个字符数组用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    /**
     * code解密
     *
     * @param code 播放COde
     * @return 解码结果
     */
    public static VodPlayConfigReq.VodAuthCode decodeForPlay(String code) {
        try {
            code = replace(code);
            return JSUtil.parseObject(new String(SecureUtil.des(Base64.decode(PolyvHttpUtil.KEY)).decrypt(Base64.decode(URLDecoder.decode(code, StandardCharsets.UTF_8.name())))), VodPlayConfigReq.VodAuthCode.class);
        } catch (Exception e) {
            log.error("保利威视，解密出错", e);
            return null;
        }
    }

    private static String replace(String param) {
        param = param.replaceAll(" ", "+");
        param = param.replaceAll("\\+", "%2B");
        return param.replaceAll("/", "%2F");
    }
}
