package com.roncoo.education.common.polyv.live;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 * polyv 直播签名工具类
 *
 * @author: thomas
 **/

public class LiveSignUtil {

    private static final Logger log = LoggerFactory.getLogger(LiveSignUtil.class);

    public static final String ALGORITHM_MD5 = "MD5";

    public static final String ALGORITHM_SHA256 = "SHA-256";

    private LiveSignUtil() {
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
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
    public static String getSHA256Sign(Map<String, String> params, String appSecret)
            throws NoSuchAlgorithmException {
        // 处理参数，计算SHA256哈希值
        log.debug("参与签名参数：{}", params);
        String concatStr = concatParams(params);
        String plain = appSecret + concatStr + appSecret;
        log.debug("签名原始字符串：{}", plain);
        String encrypted = sha256Hex(plain).toUpperCase();
        log.debug("签名结果： {}", encrypted);
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
    public static String getSign(Map<String, String> params, String appSecret)
            throws NoSuchAlgorithmException {
        return getMD5Sign(params, appSecret);
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
    public static String getMD5Sign(Map<String, String> params, String appSecret)
            throws NoSuchAlgorithmException {
        // 处理参数，计算MD5哈希值
        log.debug("参与签名参数：{}", params);
        String concatStr = concatParams(params);
        String plain = appSecret + concatStr + appSecret;
        log.debug("签名原始字符串：{}", plain);
        String encrypted = md5Hex(plain).toUpperCase();
        log.debug("签名结果： {}", encrypted);
        // 32位大写MD5值
        return encrypted;
    }

    /**
     * 把数组所有元素排序，并按照“参数参数值”的模式成字符串，用于计算MD5哈希值
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String concatParams(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (value == null || "".equals(value.trim())) {
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
    public static String md5Hex(String text) throws NoSuchAlgorithmException {
        return digestHex(text, ALGORITHM_MD5);
    }

    /**
     * 对字符串做MD5加密，返回加密后的字符串。
     *
     * @param text 待加密的字符串。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException     签名异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String sha256Hex(String text) throws NoSuchAlgorithmException {
        return digestHex(text, ALGORITHM_SHA256);
    }

    /**
     * 对字符串做加密，返回加密后的字符串。
     *
     * @param text      待加密的字符串。
     * @param algorithm 算法类型。
     * @return 加密后的字符串。
     * @throws NoSuchAlgorithmException 签名异常
     */
    private static String digestHex(String text, String algorithm)
            throws NoSuchAlgorithmException {

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
    public static String byteArrayToHex(byte[] byteArray) {
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


}
