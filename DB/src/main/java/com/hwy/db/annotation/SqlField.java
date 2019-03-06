package com.hwy.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者: hewenyu
 * 日期: 2019/3/6 10:00
 * 说明: 数据库字段标记注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlField {

    String columnName() default "";

    String columnType() default "";

}
