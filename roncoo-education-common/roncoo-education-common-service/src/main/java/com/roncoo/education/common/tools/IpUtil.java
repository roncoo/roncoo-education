package com.roncoo.education.common.tools;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * IP工具类
 *
 * @author LYQ
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IpUtil {
    private static final String UNKNOWN = "unknown";

    /**
     * 获取请求IP
     *
     * @param request 访问请求
     * @return 访问IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && !ip.isEmpty() && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(StrPool.COMMA)) {
                ip = ip.split(StrPool.COMMA)[0];
            }
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取IP信息
     *
     * @param ip IP地址
     * @return IP信息
     */
    public static IpInfo getIpInfo(String ip) {
        if (StrUtil.isBlank(ip)) {
            return null;
        }
        try {
            String result = HttpUtil.get("https://whois.pconline.com.cn/ipJson.jsp?json=true&ip=" + ip, 3000);
            return JSONUtil.toBean(result, IpInfo.class);
        } catch (Exception e) {
            log.error("通过ip获取地址异常，{}", e.getMessage());
            return null;
        }
    }

    /**
     * IP信息
     */
    @Data
    public static class IpInfo {

        /**
         * 区域
         */
        private String region;

        /**
         * 区域编码
         */
        private String regionCode;

        /**
         * 区域名称
         */
        private String regionNames;

        /**
         * 省份
         */
        private String pro;

        /**
         * 省份编码
         */
        private String proCode;

        /**
         * 城市
         */
        private String city;

        /**
         * 城市编码
         */
        private String cityCode;

        /**
         * IP地址
         */
        private String ip;

        /**
         * 地址信息
         */
        private String addr;

    }
}
