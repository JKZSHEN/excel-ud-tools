package com.jkshen.exceludtools.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FieldExcel {
    /**
     * 字段最大长度
     * @return
     */
    int maxLenth();

    /**
     * 字段最小长度
     * @return
     */
    int minLenth();

    /**
     * 字段是否为必填
     * @return
     */
    boolean require() default  false;

}
