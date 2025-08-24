package com.roncoo.education.common.config;

import com.roncoo.education.common.base.ThreadContext;
import com.roncoo.education.common.core.base.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LYQ
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorConfig());
    }

    static class InterceptorConfig implements AsyncHandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
            ThreadContext.setUserId(request.getHeader(Constants.USER_ID));
            return true;
        }

        @Override
        public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
            ThreadContext.removeUserId();
        }
    }
}
