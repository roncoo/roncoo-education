package com.roncoo.education.common.log;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * <p>
 * 注意：注解必须要在@ApiOperation下面，否则不生效
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogCache {
    /**
     * 存入缓存的key前缀，避免key冲突
     *
     * @return
     */
    String key() default "";
}
