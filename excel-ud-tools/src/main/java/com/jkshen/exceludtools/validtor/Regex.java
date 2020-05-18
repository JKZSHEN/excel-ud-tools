package com.jkshen.exceludtools.validtor;


import com.jkshen.exceludtools.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字段通过正则表达式验证
 */

public class Regex {
    private static Logger log = LoggerFactory.getLogger(Regex.class);

    public static boolean isValid(String value, String regexp) {
        log.info("=======================  正则验证值为："+value+"  ==========================");
        if(StringUtil.isNotEmpty(value)){
            Pattern p = Pattern.compile(regexp);
            Matcher matcher = p.matcher(value);
            boolean matches = matcher.matches();
            if(matches){
                return true;
            }
        }
        return false;
    }
}
