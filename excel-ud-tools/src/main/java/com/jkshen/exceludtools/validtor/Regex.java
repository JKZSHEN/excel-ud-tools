package com.jkshen.exceludtools.validtor;


import com.jkshen.exceludtools.annotation.FieldRegExp;
import com.jkshen.exceludtools.utils.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段通过正则表达式验证
 */
public class Regex implements ConstraintValidator<FieldRegExp,String> {


    @Override
    public void initialize(FieldRegExp constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        FieldRegExp unwrap = constraintValidatorContext.unwrap(FieldRegExp.class);
        if(StringUtil.isNotEmpty(value)){
            Pattern p = Pattern.compile(value);
            Matcher matcher = p.matcher(value);
            boolean matches = matcher.matches();
            if(matches){
                return true;
            }
        }
        return false;
    }
}
