package com.annotation;

import java.lang.annotation.*;

/**
 * Created by lenovo on 2019/4/19.
 * Title DataSourceChose
 * Package  com.annotation
 * Description
 *
 * @Version V1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceChoseAnnotation {
    String dataSource() default "";
}
