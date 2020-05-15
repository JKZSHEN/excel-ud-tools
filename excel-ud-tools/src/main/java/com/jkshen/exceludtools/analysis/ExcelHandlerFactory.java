package com.jkshen.exceludtools.analysis;


import com.jkshen.exceludtools.utils.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author zhangZaiShen
 * @Description excel文件处理
 **/
public class ExcelHandlerFactory {

    /**
     * 通过输入流传入
     * @param in
     * @param fileName
     * @param excelClass
     * @param ignores
     */
    public static void resolve(InputStream in,String fileName,Class<?> excelClass,String... ignores){
        resolve(in,fileName,excelClass,ParamResolve.resolveInstance(excelClass),ignores);
    }

    /**
     * 通过MultipartFile传入
     * @param file
     * @param excelClass
     * @param ignores
     */
    public static void resolve(MultipartFile file,Class<?> excelClass,String... ignores){
        try {
            resolve(file.getInputStream(),file.getOriginalFilename(),excelClass,ParamResolve.resolveInstance(excelClass),ignores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析文件，并活得解析后的对象
     * @param in
     * @param fileName
     * @param excelClass
     * @param ignores
     */
    public static void resolve(InputStream in, String fileName, Class<?> excelClass, List<Field> fieldList, String... ignores){
        //创建工作薄
        Workbook workbook = CreateWorkBook.create(in);
        //读取页
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            //页
            Sheet sheet = CreateWorkBook.readSheet(workbook, i);
            int rowNum = sheet.getLastRowNum()+1;
            for (int j = 0; j < rowNum; j++) {
                int nullCellNumb = 0;
                //行
                Row row = CreateWorkBook.readRow(sheet, j);
                int cellNum = row.getLastCellNum();
                for (int k = 0; k < cellNum; k++) {
                    //获取当前行的某一列
                    Cell cell = row.getCell(k);
                    if(StringUtil.cellEmpty(cell,nullCellNumb)){
                        //检查到连续十列为空跳出当前行，执行下一行
                        if(nullCellNumb > 10){
                            break;
                        }
                    }
                    //验证自动是否符合规则
                    ParamResolve.validtor(fieldList.get(k),cell);
                    //获取新实例
                    Class aClass = CreateWorkBook.newInstance(excelClass);
                    //列不为空的时候写入对象
                    MappingField.reflexField(aClass,cell,fieldList.get(k));


                }

            }


        }




    }


}
