package com.roncoo.education.common.log;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author fengyw
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "操作日志";

    /**
     * 存入缓存的key前缀
     *
     * @return
     */
    String key() default "";

    /**
     * 是否进行更新操作，会和@LogCache的数据进行对比更新
     *
     * @return
     */
    boolean isUpdate() default false;
}
