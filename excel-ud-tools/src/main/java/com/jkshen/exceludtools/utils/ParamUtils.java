package com.jkshen.exceludtools.utils;

import org.springframework.util.Assert;

/**
 * @Author zhangZaiShen
 * @Description 验证属性字段
 **/
public class ParamUtils {
    /**
     * 验证过滤字段
     * @param o
     * @param p
     * @return
     */
    public static boolean contains(Object[] o,Object p){
        Assert.notEmpty(o,"array is not null");
        Assert.notNull(p, "param is not null");
        for (Object oj : o) {
            if(oj == p || oj.equals(p)){
                return true;
            }
        }
        return false;
    }

}
