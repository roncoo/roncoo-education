/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.base;

import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.base.page.PageUtil;
import com.roncoo.education.common.tools.IpUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基础类
 *
 * @author wujing
 */
@Component
public class BaseBiz extends Base {

    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";
    public static final String PASS = "pass";

    @Resource
    protected CacheRedis cacheRedis;

    @Resource
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int sort(List<SortReq> sorts, String tableName) {
        tableName = PageUtil.camelToUnderscore(tableName);
        String sql = "update TABLENAME set sort = :sort where id = :id".replace("TABLENAME", tableName);
        return this.namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(sorts)).length;
    }

    protected IpUtil.IpInfo getIpInfo(String ip) {
        IpUtil.IpInfo ipInfo = cacheRedis.get(ip, IpUtil.IpInfo.class);
        if (ipInfo == null) {
            ipInfo = IpUtil.getIpInfo(ip);
            if (ipInfo != null) {
                cacheRedis.set(ip, ipInfo, 1, TimeUnit.DAYS);
            }
        }
        return ipInfo;
    }

    protected Map<String, String> getHeaderParam(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        // 获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String val = request.getHeader(name);
            headerMap.put(name, val);
        }
        return headerMap;
    }

    protected Map<String, String> getQueryParam(HttpServletRequest request) {
        Map<String, String> queryParamMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String val = request.getParameter(name);
            queryParamMap.put(name, val);
        }
        return queryParamMap;
    }

    /**
     * 获取HttpServletRequest请求body参数
     *
     * @param request 回调请求
     * @return body参数
     */
    protected String getBodyParam(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            StringBuilder sb = new StringBuilder();
            br = request.getReader();
            if (br != null) {
                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
            }
            return sb.toString();
        } catch (IOException e) {
            log.error("HttpServletRequest-获取body参数失败", e);
            return null;
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("HttpServletRequest--获取body参数关闭流失败", e);
                }
            }
        }
    }

}
