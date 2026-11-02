package com.roncoo.education.common.core.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Getter
@Component
public class NacosConfig {
    private final String secret;

    public NacosConfig(@Value("${jwt.token.secret}") String secret) {
        if (secret == null || secret.isBlank() || secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("jwt.token.secret must contain at least 32 UTF-8 bytes; use a cryptographically random deployment secret");
        }
        this.secret = secret;
    }
}
