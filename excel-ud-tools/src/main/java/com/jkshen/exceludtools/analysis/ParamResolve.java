package com.jkshen.exceludtools.analysis;

import com.jkshen.exceludtools.annotation.Excel;
import com.jkshen.exceludtools.annotation.FieldExcel;
import com.jkshen.exceludtools.annotation.FieldRegExp;
import com.jkshen.exceludtools.utils.ParamUtils;
import com.jkshen.exceludtools.validtor.Regex;
import org.apache.poi.ss.usermodel.Cell;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zhangZaiShen
 * @Description 获取对象有注解的属性
 * @Date $ $
 **/
public class ParamResolve {
    /**
     * 解析文件
     * @param excelClass 和excel对应的实体类对象
     * @param ignores 过滤字段（实体类有注解excel没有字段需要过滤）
     * @return
     */
    public static List<Field> resolveInstance( Class<?> excelClass, String... ignores){
        Excel annotation = excelClass.getAnnotation(Excel.class);
        Objects.requireNonNull(annotation, "not found instance... entity require use @Excel annotation");
        //获取到所有对象属性
        Field[] declaredField = excelClass.getDeclaredFields();
        //找到所有带@FieldExcel注解并且不过滤的属性
        List<Field> hasExcelFieldList = Arrays.stream(declaredField)
                .filter(e->e.isAnnotationPresent(FieldExcel.class))
                .filter(e-> !ParamUtils.contains(ignores,e.getName())).collect(Collectors.toList());
        //检查是否有父类，有则将父类的属性也加入
        Class<?> superclass = excelClass.getSuperclass();
        if(null != superclass){
            Field[] superFields = superclass.getDeclaredFields();
            hasExcelFieldList.addAll(Arrays.stream(superFields)
                    .filter(s->s.isAnnotationPresent(FieldExcel.class))
                    .filter(s->!ParamUtils.contains(ignores, s.getName())).collect(Collectors.toList()));
        }
        return hasExcelFieldList;
    }

    /**
     * 验证excel上传字段值是否符合规则
     * @param field
     * @param cell
     */
    public static boolean validtor(Field field, Cell cell){
        //字段最大长度、最小长度、是否必填
        FieldExcel fieldAnnotation = field.getAnnotation(FieldExcel.class);
        //正则表达式验证
        FieldRegExp fieldRegExp = field.getAnnotation(FieldRegExp.class);
        if(null != fieldAnnotation){
            //如果是必传字段则验证字段的最大长度和最小长度
            if(fieldAnnotation.require()){
                //验证设定的最大程度
                ParamUtils.max(fieldAnnotation.maxLenth(), cell.toString().trim().length());
                //验证设定的最小长度
                ParamUtils.min(fieldAnnotation.minLenth(), cell.toString().trim().length());
            }
        }
        if(null != fieldRegExp){
            if(!Regex.isValid(fieldRegExp.value(),cell.toString().trim())){
                throw new IllegalArgumentException(field.getName() + " 字段值=== " + cell.toString().trim() + "不符合正则规则");
            }
        }
        return true;
    }


}
