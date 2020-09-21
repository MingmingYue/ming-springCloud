package com.service.user.permission;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Permission {
    /**
     * 权限名字，全局唯一，英文格式
     *
     * @return String
     */
    String name();

    /**
     * 权限标签
     *
     * @return String
     */
    String label();

    /**
     * 权限描述
     *
     * @return String
     */
    String description() default "";
}