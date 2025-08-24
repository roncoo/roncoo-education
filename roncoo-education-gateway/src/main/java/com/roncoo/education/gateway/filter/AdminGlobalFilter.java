package com.roncoo.education.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.JwtUtil;
import com.roncoo.education.gateway.common.FilterUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wujing
 */
@Slf4j
@Component
public class AdminGlobalFilter implements GlobalFilter, Ordered {

    /**
     * admin不需要token校验的接口
     */
    private static final List<String> EXCLUDE_TOKEN_URL = List.of(
            "/system/admin/login/password"
    );

    /**
     * admin不需要权限校验的接口
     */
    private static final List<String> EXCLUDE_URL = List.of(
            "/system/admin/sys/user/current"
    );

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 优先级，order越大，优先级越低
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getPath().value();
        if (FilterUtil.checkUri(uri, FilterUtil.CALLBACK_URL_PREFIX)) {
            // 路径存在关键词：/callback，不鉴权
            return chain.filter(exchange);
        }
        if (FilterUtil.checkUri(uri, FilterUtil.API_URL_PREFIX)) {
            // 路径存在关键词：/api，不鉴权
            return chain.filter(exchange);
        }
        if (FilterUtil.checkUri(uri, FilterUtil.API_V3)) {
            // 路径存在关键词：/v3，不鉴权
            return chain.filter(exchange);
        }
        if (FilterUtil.checkUri(uri, FilterUtil.IMAGES)) {
            // 路径存在关键词：/images
            return chain.filter(exchange);
        }
        // 额外不需要token认证的接口
        if (EXCLUDE_TOKEN_URL.contains(uri)) {
            return chain.filter(exchange);
        }

        Long userId = getUserId(request);
        request.mutate().header(Constants.USER_ID, String.valueOf(userId));

        if (FilterUtil.checkUri(uri, FilterUtil.ADMIN_URL_PREFIX)) {
            // admin校验
            if (!stringRedisTemplate.hasKey(Constants.RedisPre.ADMIN_APIS.concat(userId.toString()))) {
                throw new BaseException(ResultEnum.MENU_PAST);
            }
            String tk = stringRedisTemplate.opsForValue().get(Constants.RedisPre.ADMIN_APIS.concat(userId.toString()));
            // 校验接口是否有权限
            if (!checkUri(uri, tk)) {
                throw new BaseException(ResultEnum.MENU_NO);
            }
            // 更新时间，使用户菜单不过期
            stringRedisTemplate.expire(Constants.RedisPre.ADMIN_APIS.concat(userId.toString()), Constants.SESSIONTIME, TimeUnit.MINUTES);
        }
        return chain.filter(exchange);
    }

    // 校验用户是否有权限
    private static Boolean checkUri(String uri, String tk) {
        if (StringUtils.hasText(uri) && uri.endsWith("/")) {
            uri = uri.substring(0, uri.length() - 1);
        }
        // 额外不需要权限校验的接口
        if (EXCLUDE_URL.contains(uri)) {
            return true;
        }
        // 权限校验
        if (tk.contains(uri)) {
            return true;
        }
        log.error("用户没该权限点，{}", uri);
        return false;
    }

    private Long getUserId(ServerHttpRequest request) {
        // 头部
        String token = request.getHeaders().getFirst(Constants.TOKEN);
        if (!StringUtils.hasText(token)) {
            throw new BaseException("token不存在，请重新登录");
        }

        if (!stringRedisTemplate.hasKey(token)) {
            throw new BaseException(ResultEnum.TOKEN_PAST);
        }

        // 解析 token
        DecodedJWT jwt = null;
        try {
            jwt = JwtUtil.verify(token);
        } catch (Exception e) {
            log.error("token异常，token={}", token);
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }

        // 校验token
        if (null == jwt) {
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }
        Long userId = JwtUtil.getUserId(jwt);
        if (userId <= 0) {
            throw new BaseException(ResultEnum.TOKEN_ERROR);
        }

        // 更新时间，使token不过期
        stringRedisTemplate.expire(token, Constants.SESSIONTIME, TimeUnit.MINUTES);
        return userId;
    }

}
