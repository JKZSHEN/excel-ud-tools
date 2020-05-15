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
    @FieldExcel(maxLenth =100,minLenth =2,require = true)
    @FieldRegExp(value = "^[0|+][0-9]{2,3}-[0-9]{7,8}$")
    private String sex;

}
