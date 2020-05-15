package com.jkshen.exceludtools.analysis;

import com.jkshen.exceludtools.annotation.Excel;
import com.jkshen.exceludtools.annotation.FieldExcel;
import com.jkshen.exceludtools.utils.ParamUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zhangZaiShen
 * @Description excel文件处理
 **/
public class ExcelHandlerFactory {
    /**
     * 解析文件
     * @param in
     * @param excelClass 和excel对应的实体类对象
     * @param ignores 过滤字段（实体类有注解excel没有字段需要过滤）
     * @param <T>
     * @return
     */
    public static <T> T resolve(InputStream in,Class<?> excelClass,String... ignores){
        Excel annotation = excelClass.getAnnotation(Excel.class);
        Objects.requireNonNull(annotation, "not found instance... entity require use @Excel annotation");
        //获取到所有对象属性
        Field[] declaredField = excelClass.getDeclaredFields();
        //找到所有带@FieldExcel注解并且不过滤的属性
         List<Field> hasExcelFieldList = Arrays.stream(declaredField)
                 .filter(e->e.isAnnotationPresent(FieldExcel.class))
                 .filter(e-> ParamUtils.contains(ignores,e.getName())).collect(Collectors.toList());
        //检查是否有父类，有则将父类的属性也加入
        Class<?> superclass = excelClass.getSuperclass();
        if(null != superclass){
            Field[] superFields = superclass.getDeclaredFields();
            hasExcelFieldList.addAll(Arrays.stream(superFields)
                .filter(s->s.isAnnotationPresent(FieldExcel.class))
                .filter(s->ParamUtils.contains(ignores, s.getName())).collect(Collectors.toList()));
        }
        return null;
    }

}
