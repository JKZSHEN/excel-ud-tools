package com.jkshen.exceludtools.utils;


import org.apache.poi.ss.usermodel.Cell;

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

    /**
     * 判断列是否为空
     * @param cell
     * @return
     */
    public static boolean cellEmpty(Cell cell,int nullCellNumb){
        if(cell ==  null || "".equals(cell.toString().trim())){
            nullCellNumb = nullCellNumb+1;
            return true;
        }
        return false;
    }

}
