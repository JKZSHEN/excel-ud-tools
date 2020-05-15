package com.jkshen.exceludtools.validtor;


import com.jkshen.exceludtools.utils.StringUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段通过正则表达式验证
 */
public class Regex {

    public static boolean isValid(String value, String regexp) {
        if(StringUtil.isNotEmpty(value)){
            Pattern p = Pattern.compile(value);
            Matcher matcher = p.matcher(regexp);
            boolean matches = matcher.matches();
            if(matches){
                return true;
            }
        }
        return false;
    }
}
