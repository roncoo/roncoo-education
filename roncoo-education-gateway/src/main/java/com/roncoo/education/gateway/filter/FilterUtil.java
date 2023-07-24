package com.roncoo.education.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;

/**
 * 过滤器工具类
 *
 * @author Admin
 */
@Slf4j
public final class FilterUtil {

    private FilterUtil() {
    }

    /**
     * 技术文档
     */
    public static final String API_V3 = "/v3";
    /**
     * 图片
     */
    public static final String IMAGES = "/images";
    /**
     * Api路径前缀
     */
    public static final String API_URL_PREFIX = "/api";
    /**
     * Boss路径前缀
     */
    public static final String CALLBACK_URL_PREFIX = "/callback";
    /**
     * Admin路径前缀
     */
    public static final String ADMIN_URL_PREFIX = "/admin";

    /**
     * 校验uri里面的第二个斜杠的字符串
     *
     * @param uri 请求URL
     * @param key 需要校验的字符串
     * @return 校验结果
     */
    public static boolean checkUri(String uri, String key) {
        String result = uri.substring(uri.indexOf("/", uri.indexOf("/") + 1));
        return result.startsWith(key);
    }

    public static boolean startsWith(String uri, String key) {
        return uri.startsWith(key);
    }

    /**
     * 获取JWT解码信息
     *
     * @param request 访问请求
     * @return JWT解码信息
     */
    public static DecodedJWT getDecodedJWT(ServerHttpRequest request) {
        // 头部
        String token = request.getHeaders().getFirst(Constants.TOKEN);
        // 路径
        if (!StringUtils.hasText(token)) {
            token = request.getQueryParams().getFirst(Constants.TOKEN);
        }
        // 检验token
        if (!StringUtils.hasText(token)) {
            throw new BaseException(ResultEnum.TOKEN_PAST);
        }

        // 解析 token
        try {
            return JWTUtil.verify(token);
        } catch (Exception e) {
            log.error("token异常，token={}", token, e);
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }
    }
}
