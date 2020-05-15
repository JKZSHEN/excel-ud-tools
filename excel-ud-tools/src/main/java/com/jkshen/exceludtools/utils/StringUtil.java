package com.jkshen.exceludtools.utils;


/**
 * 验证工具
 */
public class StringUtil {
    /**
     * 非空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(String obj){
        if( null == obj || obj.trim().length()==0){
            return false;
        }
        return true;
    }
    /**
     * 空
     * @param obj
     * @return
     */
    public static boolean isEmpty(String obj){
        if( null == obj || obj.trim().length()==0){
            return true;
        }
        return false;
    }
}
