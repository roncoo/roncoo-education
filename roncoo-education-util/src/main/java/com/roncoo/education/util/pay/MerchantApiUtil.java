/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.pay;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.springframework.util.StringUtils;
import com.roncoo.education.util.tools.MD5Util;

/**
 * 
 * @author forest
 */
public final class MerchantApiUtil {

    private MerchantApiUtil() {
    }

    /**
     * 获取参数签名
     * 
     * @param paramMap
     *            签名参数
     * @param paySecret
     *            签名密钥
     * @return
     */
    public static String getSign(Map<String, Object> paramMap, String paySecret) {
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
        String argPreSign = stringBuffer.append("&paySecret=").append(paySecret).toString();
        String signStr = MD5Util.MD5(argPreSign).toUpperCase();
        return signStr;

    }

    /**
     * 获取参数拼接串
     * 
     * @param paramMap
     * @return
     */
    public static String getParamStr(Map<String, String> paramMap) {
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            stringBuffer.append(m.getKey()).append("=").append(m.getValue()).append("&");
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        return stringBuffer.toString();
    }

    /**
     * 验证商户签名
     * 
     * @param paramMap
     *            签名参数
     * @param paySecret
     *            签名私钥
     * @param signStr
     *            原始签名密文
     * @return
     */
    public static boolean isRightSign(Map<String, Object> paramMap, String paySecret, String signStr) {
        if (!StringUtils.hasText(signStr)) {
            return false;
        }
        String sign = getSign(paramMap, paySecret);
        if (signStr.equals(sign)) {
            return true;
        } else {
            return false;
        }
    }

}
