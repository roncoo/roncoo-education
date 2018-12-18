/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密，使用UTF-8编码
 *
 * @author wujing
 */
public final class MD5Util {

    private MD5Util() {
    }

    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 对字符串进行MD5加密， 默认使用UTF-8
     * 
     * @param text
     *            明文
     * @return 密文
     */
    public static String sign(String... text) {
        if (text.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String t : text) {
                sb.append(t);

            }
            return MD5(sb.toString());
        }
        throw new IllegalStateException("text is null");
    }

    /**
     * 对字符串进行MD5加密， 默认使用UTF-8
     * 
     * @param text
     *            明文
     * @return 密文
     */
    public static String MD5(String text) {
        return MD5(text, "UTF-8");
    }

    /**
     * 对字符串进行MD5加密
     * 
     * @param text
     *            明文
     * @param charsetName
     *            指定编码
     * @return 密文
     */
    public static String MD5(String text, String charsetName) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try {
            msgDigest.update(text.getBytes(charsetName)); // 注意是按照指定编码形式签名
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        byte[] bytes = msgDigest.digest();
        return new String(encodeHex(bytes));
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

}
