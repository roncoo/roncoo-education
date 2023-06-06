package com.roncoo.education.common.config;

import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.upload.impl.LocalUploadImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LYQ
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(LocalUploadImpl.PATH_IMAGES + "/**").addResourceLocations("file:" + LocalUploadImpl.LOCALPATH_IMAGES, LocalUploadImpl.PATH_DOCS + "/**").addResourceLocations("file:" + LocalUploadImpl.LOCALPATH_DOCS);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorConfig());
    }

    static class InterceptorConfig implements AsyncHandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            ThreadContext.setUserId(request.getHeader(Constants.USER_ID));
            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            ThreadContext.removeUserId();
        }
    }
}


