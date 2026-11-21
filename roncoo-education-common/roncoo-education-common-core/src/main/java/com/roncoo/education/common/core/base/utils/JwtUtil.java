/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.roncoo.education.common.core.base.NacosConfig;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public final class JwtUtil {
    private static final String ISSUER = "RONCOO";
    public static final String USERID = "userId";
    public static final Long DATE = 30 * 24 * 3600 * 1000L;

    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtUtil(NacosConfig config) {
        algorithm = Algorithm.HMAC256(config.getSecret());
        verifier = JWT.require(algorithm).withIssuer(ISSUER)
                .withClaimPresence("jti").withClaimPresence(USERID)
                .withClaimPresence("exp").build();
    }

    public String create(Long userId, Long date) {
        if (userId == null || userId <= 0 || date == null || date <= 0) {
            throw new IllegalArgumentException("JWT userId and lifetime must be positive");
        }
        return JWT.create().withIssuer(ISSUER).withClaim(USERID, userId.toString())
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(new Date(Math.addExact(System.currentTimeMillis(), date)))
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        DecodedJWT jwt = verifier.verify(token);
        if (jwt.getId() == null || jwt.getId().isBlank() || getUserId(jwt) <= 0) {
            throw new IllegalArgumentException("Invalid JWT session claims");
        }
        return jwt;
    }

    public static Long getUserId(DecodedJWT decodedJWT) {
        return Long.valueOf(decodedJWT.getClaim(USERID).asString());
    }
}
