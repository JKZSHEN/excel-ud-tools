package com.jkshen.exceludtools;

import com.jkshen.exceludtools.annotation.FieldExcel;
import com.jkshen.exceludtools.annotation.FieldRegExp;
import lombok.Data;

/**
 * @Author zhangZaiShen
 * @Description //TODO $
 * @Date $ $
 **/

@Data
public class SuperBean {
    @FieldExcel(maxLenth =100,minLenth =0,require = true)
    @FieldRegExp(value = "^[0-9]*$")
    private Integer sex;

}
