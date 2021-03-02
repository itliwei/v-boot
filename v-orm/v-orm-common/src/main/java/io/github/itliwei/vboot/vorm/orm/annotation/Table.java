package io.github.itliwei.vboot.vorm.orm.annotation;

import java.lang.annotation.*;

/**
 * 实体表映射
 * Created by admin on 2019/4/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

    /**
     * 表名
     */
    String value() default "";

    /**
     * 注释
     */
    String comment() default "";

    /**
     * ID序列
     */
    String sequence() default "";
}
