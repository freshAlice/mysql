package com.test.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 8/14/2018.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
    String name() default "";
    String[] columns();
    String[] sqlColumns();
}
