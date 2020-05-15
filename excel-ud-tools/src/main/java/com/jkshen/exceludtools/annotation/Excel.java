package com.jkshen.exceludtools.annotation;

import com.jkshen.exceludtools.enums.ExcelTypeEnum;

import java.lang.annotation.*;

/**
 * 标记这个类和excel关联
 * 字段顺序与excel顺序一致
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Excel {
    /**
     * excel文件名
     * @return
     */
    String value() default "";

    /**
     * 文件类型，默认2007
     * @return
     */
    ExcelTypeEnum type() default ExcelTypeEnum.XLSX;

}
