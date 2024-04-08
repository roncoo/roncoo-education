package com.roncoo.education.system.feign.interfaces.config;

import cn.hutool.core.thread.ExecutorBuilder;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.IPUtil;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.core.tools.ObjMapUtil;
import com.roncoo.education.system.feign.interfaces.IFeignSysLog;
import com.roncoo.education.system.feign.interfaces.qo.FeignSysLogQO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 系统日志，切面处理类
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AspectSysLog {
    /**
     * 核心进程1个
     * 最大线程池100个
     * 队列100个
     */
    private static final ExecutorService CALLBACK_EXECUTOR = ExecutorBuilder.create().setCorePoolSize(1).setMaxPoolSize(100).setWorkQueue(new LinkedBlockingQueue<>(100)).build();

    /**
     * 记录最大程度
     */
    private static final int MAX_LENGTH = 5000;

    @NotNull
    private final CacheRedis cacheRedis;
    @NotNull
    private final IFeignSysLog feignSysLog;

    @Pointcut("@annotation(com.roncoo.education.common.annotation.SysLog)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        try {
            FeignSysLogQO qo = getSysLog();
            // 记录日志，异步执行
            CALLBACK_EXECUTOR.execute(() -> {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                com.roncoo.education.common.annotation.SysLog syslog = signature.getMethod().getAnnotation(com.roncoo.education.common.annotation.SysLog.class);
                qo.setOperation(syslog.value());
                qo.setContent(JSUtil.toJsonString(joinPoint.getArgs()));
                if (StringUtils.hasText(qo.getContent())) {
                    if (syslog.isUpdate()) {
                        // 如果是修改或者编辑，比对变化

                        // 修改后的值
                        Map<String, Object> map1 = JSUtil.parseObject(qo.getContent(), Map.class);
                        String redisKey = syslog.key() + qo.getUserId() + map1.get("id").toString();
                        if (cacheRedis.hasKey(redisKey)) {
                            // 修改前的值
                            Map<String, Object> map2 = cacheRedis.get(redisKey, Map.class);
                            // 删除缓存
                            cacheRedis.delete(redisKey);
                            qo.setContent(ObjMapUtil.contrast(map1, map2)); //进行对比
                        }
                    }
                    if (qo.getContent().length() > MAX_LENGTH) {
                        log.warn("操作日志：{}", JSUtil.toJsonString(qo));
                        qo.setContent(qo.getContent().substring(0, MAX_LENGTH));
                    }
                }
                feignSysLog.save(qo);

            });
        } catch (Exception e) {
            log.error("保存操作日志异常", e);
        }
    }

    private FeignSysLogQO getSysLog() {
        FeignSysLogQO qo = new FeignSysLogQO();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        qo.setUserId(Long.valueOf(request.getHeader(Constants.USER_ID)));
        qo.setIp(IPUtil.getIpAddress(request));
        qo.setPath(request.getServletPath());
        qo.setMethod(request.getMethod());
        return qo;
    }

}
