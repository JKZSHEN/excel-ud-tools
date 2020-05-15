package com.jkshen.exceludtools;

import com.jkshen.exceludtools.annotation.Excel;
import com.jkshen.exceludtools.annotation.FieldExcel;
import lombok.Data;

/**
 * @Author zhangZaiShen
 * @Description //TODO $
 * @Date $ $
 **/
@Excel
@Data
public class TestBean  extends SuperBean{
    @FieldExcel(maxLenth = 100,require = true,minLenth = 10)
    private String name;
    @FieldExcel(minLenth = 2,require = true,maxLenth = 200)
    private String addr;

    private Integer age;

}
