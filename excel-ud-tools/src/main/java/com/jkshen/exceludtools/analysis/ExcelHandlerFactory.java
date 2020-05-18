package com.jkshen.exceludtools.analysis;


import com.jkshen.exceludtools.TestBean;
import com.jkshen.exceludtools.utils.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
     * @param entityClass
     * @param ignores
     */
    public static <T> List<T> resolve(InputStream in,String fileName,Class<T> entityClass,String... ignores){
        return resolve(in,fileName,entityClass,ParamResolve.resolveInstance(entityClass),ignores);
    }

    /**
     * 通过MultipartFile传入
     * @param file
     * @param entityClass
     * @param ignores
     */
    public static <T> List<T> resolve(MultipartFile file,Class<T> entityClass,String... ignores){
        try {
            return resolve(file.getInputStream(),file.getOriginalFilename(),entityClass,ParamResolve.resolveInstance(entityClass),ignores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析文件，并活得解析后的对象
     * @param in
     * @param fileName
     * @param entityClass
     * @param ignores
     */
    public static <T> List<T> resolve(InputStream in, String fileName,Class<T> entityClass, List<Field> fieldList, String... ignores){
        //存放实例集合
        List<T> list = new ArrayList<T>();
        //创建工作薄
        Workbook workbook = CreateWorkBook.create(in);
        try {
            //读取页
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                //页
                Sheet sheet = CreateWorkBook.readSheet(workbook, i);
                int rowNum = sheet.getLastRowNum();
                for (int j = 1; j <= rowNum; j++) {
                    //获取新实例
                    T aClass = CreateWorkBook.newInstance(entityClass);
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
                        //列不为空的时候写入对象
                        MappingField.reflexField(aClass,cell,fieldList.get(k));
                    }
                    list.add(aClass);
                }
            }
        }finally {
            //关闭
            CreateWorkBook.close(workbook);
        }
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\study\\文件解析.xlsx");
        InputStream in = new FileInputStream(file);
        List<TestBean> resolve = resolve(in, "文件解析.xlsx", TestBean.class);
    }


}
