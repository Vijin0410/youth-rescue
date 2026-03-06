package com.rescue.framework.annotation;


import java.lang.annotation.*;

/**
 * 防重提交注解
 *
 * @author lenvo
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PreventDuplicateResubmit {

    /**
     * 防重提交锁过期时间(秒)
     * <p>
     * 默认5秒内不允许重复提交
     */
    int expire() default 5;

}
