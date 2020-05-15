package com.jkshen.exceludtools.analysis;

import com.jkshen.exceludtools.utils.StringUtil;

import java.io.InputStream;
import java.util.List;

/**
 * 获取文件类型
 **/
public class ExcelSuffix {
    private static final String POINT = ".";

    /**
     * 获取文件后缀名
     * @param path
     * @return
     */
    public static String getpostfix(String path){
        if(StringUtil.isEmpty(path.trim()) || !path.contains(POINT)){
            throw new NullPointerException("File name format error");
        }
        return path.substring(path.lastIndexOf(POINT)+1,path.length());
    }

}
