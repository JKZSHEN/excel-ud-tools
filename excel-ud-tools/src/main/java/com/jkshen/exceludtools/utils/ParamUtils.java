package com.jkshen.exceludtools.utils;

import org.springframework.util.Assert;

import java.util.List;

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
        for (Object oj : o) {
            if(oj == p || oj.equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字段上是否有指定注解
     * @param o
     * @return
     */
    public static boolean classAnnot(Object o){
        if(null == o){
          return false;
        }
        return true;
    }

    /**
     * 验证最大长度
     * @param specifyMax 注解上规定最大长度
     * @param targetMax excel表实际上传长度
     * @return
     */
    public static boolean max(long specifyMax,long targetMax){
        Assert.notNull(specifyMax, "specifyMax is not null");
        Assert.notNull(targetMax, "targetMax is not null");
        if(specifyMax == 0 || specifyMax < targetMax){
            throw new IllegalArgumentException("最大长度不能等于0并且不能小于");
        }
        return true;
    }

    /**
     * 验证最小长度
     * @param specifyMin 注解上规定最小长度
     * @param targetMin  excel表格实际上传长度
     * @return
     */
    public static boolean min(long specifyMin,long targetMin){
        Assert.notNull(specifyMin, "specifyMin is not null");
        Assert.notNull(targetMin, "targetMin is not null");
        if(specifyMin > targetMin){
            throw new IllegalArgumentException("最小长度不能等于或小于规定长度");
        }
        return true;
    }


    /**
     * 验证必填字段是否为非空
     * @param fieldsIsNonNulls
     * @param fieldsNames
     * @param cnum
     * @return
     */
    public static boolean isNonNull(List<String> fieldsIsNonNulls, List<String> fieldsNames, int cnum){
        for (int x=0;x<fieldsIsNonNulls.size();x++){
            if(fieldsNames.size()-1<cnum){
                break;
            }
            if(fieldsIsNonNulls.get(x).equals(fieldsNames.get(cnum))){
                return true;
            }
        }
        return false;
    }

}
