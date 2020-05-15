package com.jkshen.exceludtools.analysis;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author zhangZaiShen
 * @Description 创建文件类型
 * @Date $ $
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

}
