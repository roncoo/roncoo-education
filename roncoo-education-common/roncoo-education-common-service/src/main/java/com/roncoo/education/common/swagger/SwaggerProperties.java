package com.roncoo.education.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fengyw
 */
@Data
@Component
@ConfigurationProperties(value = "swagger")
public class SwaggerProperties {
    /**
     * 是否开启
     */
    private boolean enable = false;
    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";
}
