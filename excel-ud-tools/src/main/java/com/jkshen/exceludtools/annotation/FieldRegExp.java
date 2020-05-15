package com.jkshen.exceludtools.annotation;

import com.jkshen.exceludtools.validtor.Regex;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FieldRegExp {
    /**
     * 正则验证表达式
     * @return
     */
    String value();

    /**
     * 返回信息给开发人员
     * @return
     */
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
