package com.roncoo.education.common.core.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NacosConfig {

    @Value(value = "${jwt.token.secret:123456789}")
    private String secret;

}