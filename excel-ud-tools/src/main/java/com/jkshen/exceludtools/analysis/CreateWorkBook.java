package com.jkshen.exceludtools.analysis;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author zhangZaiShen
 * @Description 创建文件类型
 **/
public class CreateWorkBook {
    /**
     * 创建文件
     * @param in
     * @return
     */
    public static Workbook create(InputStream in){
        try {
           return WorkbookFactory.create(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭文件流，就算出现异常也要关闭文件流
     * @param workbook
     */
    public static void close(Workbook workbook){
        try {
            if(null != workbook){
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取到页
     * @param wb
     * @param num
     * @return
     */
    public static Sheet readSheet(Workbook wb, int num){
        Sheet sheet = wb.getSheetAt(num);
        if(sheet == null){
            throw new NullPointerException("Sheet is null");
        }
        return sheet;
    }

    /**
     * 获取行
     * @param sheet
     * @param num
     * @return
     */
    public static Row readRow(Sheet sheet, int num){
        Row row = sheet.getRow(num);
        if(row.getLastCellNum()<1){
            throw new NullPointerException("Row is null");
        }
        return row;
    }

    /**
     * 获取列
     * @param row
     * @param num
     * @return
     */
    public static Cell readCell(Row row, int num){
        Cell cell = row.getCell(num);
        return cell;
    }

    /**
     * 获取新实例
     * @param c
     * @return
     */
    public static Class newInstance(Class c){
        try {
            return (Class)c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
