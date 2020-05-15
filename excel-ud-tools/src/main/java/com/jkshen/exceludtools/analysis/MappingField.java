package com.jkshen.exceludtools.analysis;

import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 映射属性
 */
public class MappingField {
    /**
     * 设值
     * @param excelClass
     * @param cell
     * @param field
     */
    public static void reflexField(Class<?> excelClass, Cell cell, Field field){
        //属性字段名
        String name = field.getName();
        String names = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
        field.setAccessible(true);
        //根据属性类型赋值
        if( field.getType().getName().equals("java.lang.String") ){
            Method method = getMethod(excelClass, names, String.class);
            setInvoke(method,excelClass,cell.toString());
        }else if( field.getType().getName().equals("java.lang.Double") ){
            Method method = getMethod(excelClass, names, Double.class);
            setInvoke(method,excelClass,cell.getStringCellValue());
        }else if( field.getType().getName().equals("java.lang.Integer") ){
            Method method = getMethod(excelClass, names, Integer.class);
            setInvoke(method,excelClass,cell.getStringCellValue());
        }else if( field.getType().getName().equals("java.math.BigDecimal") ){
            Method method = getMethod(excelClass, names, BigDecimal.class);
            setInvoke(method,excelClass,cell.getStringCellValue());
        }
    }


    public static Method getMethod(Class<?> excelClass,String fieldName,Class<?> clazz){
        try {
            return excelClass.getMethod("set" + fieldName, clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setInvoke(Method method,Class<?> excelClass,String value){
        try {
            method.invoke(excelClass,value.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
