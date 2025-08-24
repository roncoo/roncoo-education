package com.roncoo.education.gateway.common;

import com.github.xiaoymin.knife4j.spring.gateway.Knife4jGatewayHttpBasic;
import com.github.xiaoymin.knife4j.spring.gateway.Knife4jGatewayProperties;
import com.github.xiaoymin.knife4j.spring.gateway.discover.ServiceDiscoverHandler;
import com.github.xiaoymin.knife4j.spring.gateway.spec.v2.OpenAPI2Resource;
import com.github.xiaoymin.knife4j.spring.gateway.utils.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author fengyw
 */
@Slf4j
@EnableConfigurationProperties({Knife4jGatewayProperties.class, Knife4jGatewayHttpBasic.class})
@Configuration
public class SwaggerConfig {

    @Bean
    @ConditionalOnMissingBean
    public ServiceDiscoverHandler serviceDiscoverHandler(Knife4jGatewayProperties knife4jGatewayProperties) {
        return new EduServiceDiscoverHandler(knife4jGatewayProperties);

    }

    public static class EduServiceDiscoverHandler extends ServiceDiscoverHandler {

        public EduServiceDiscoverHandler(Knife4jGatewayProperties knife4jGatewayProperties) {
            super(knife4jGatewayProperties);
        }

        public List<OpenAPI2Resource> getResources(String forwardPath) {
            List<OpenAPI2Resource> resourceList = new ArrayList<>();
            Set<OpenAPI2Resource> resources = getGatewayResources();
            if (!CollectionUtils.isEmpty(resources)) {
                for (OpenAPI2Resource resource : resources) {
                    OpenAPI2Resource copy = resource.copy();
                    copy.setContextPath("");
                    copy.setUrl(PathUtils.append(forwardPath, copy.getUrl()));
                    // 添加
                    resourceList.add(copy);
                }
                return resourceList;
            }
            return resourceList;
        }
    }
}
