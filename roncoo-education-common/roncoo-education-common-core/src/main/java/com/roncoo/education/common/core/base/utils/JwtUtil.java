/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.base.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.roncoo.education.common.core.base.NacosConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wujing
 */
@Slf4j
public final class JwtUtil {

    private static final Map<String, String> secretMap = new HashMap<>();
    private static final String ISSUER = "RONCOO";
    public static final String USERID = "userId";
    public static final Long DATE = 30 * 24 * 3600 * 1000L; // 1个月

    /**
     * @param userId
     * @param date
     * @return
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     * @throws UnsupportedEncodingException
     */
    public static String create(Long userId, Long date) {
        try {
            return JWT.create().withIssuer(ISSUER).withClaim(USERID, userId.toString()).withExpiresAt(new Date(System.currentTimeMillis() + date)).sign(Algorithm.HMAC256(getSecret()));
        } catch (Exception e) {
            log.error("JWT生成失败", e);
            return "";
        }
    }

    /**
     * @param token
     * @return
     * @throws JWTVerificationException
     * @throws IllegalArgumentException
     * @throws UnsupportedEncodingException
     */
    public static DecodedJWT verify(String token) throws JWTVerificationException, IllegalArgumentException {
        return JWT.require(Algorithm.HMAC256(getSecret())).withIssuer(ISSUER).build().verify(token);
    }

    /**
     * @return
     */
    public static Long getUserId(DecodedJWT decodedJWT) {
        return Long.valueOf(decodedJWT.getClaim(USERID).asString());
    }

    /**
     * @return
     */
    private static String getSecret() {
        String secret = secretMap.get(ISSUER);
        if (secret == null) {
            try {
                secret = SpringUtil.getBean(NacosConfig.class).getSecret();
            } catch (Exception e) {
                log.warn("请在app.properties配置：jwt.token.secret", e);
                secret = "JhbGciOiJIUzI1NiJ9ey";
            }
            secretMap.put(ISSUER, secret);
        }
        return secret;
    }
}
