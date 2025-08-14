package com.roncoo.education.system.feign.interfaces.config;

import com.roncoo.education.common.annotation.SysLogCache;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.tools.ObjMapUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AspectSysLogCache {

    @NotNull
    private final CacheRedis cacheRedis;

    @Pointcut("@annotation(com.roncoo.education.common.annotation.SysLogCache)")
    public void cacheLogPointCut() {

    }

    @AfterReturning(value = "cacheLogPointCut()", returning = "obj")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        Result<Object> result = (Result) obj;
        if (result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
            Map<String, Object> map = ObjMapUtil.obj2Map(result.getData());
            if (map.get("id") != null) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                SysLogCache sysLogCache = signature.getMethod().getAnnotation(com.roncoo.education.common.annotation.SysLogCache.class);
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                cacheRedis.set(sysLogCache.key() + request.getHeader(Constants.USER_ID) + map.get("id").toString(), map, 60, TimeUnit.MINUTES);
            }
        }
    }

}
