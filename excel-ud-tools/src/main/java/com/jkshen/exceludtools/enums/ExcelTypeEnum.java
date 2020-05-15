package com.jkshen.exceludtools.enums;

/**
 * 文件类型
 */

public enum ExcelTypeEnum {
    XLSX("XLSX","2007"),
    XLS("XLS","2003");

    ExcelTypeEnum(String keys,String des) {
    }

    private String keys;

    private String des;

    public String getKeys() {
        return keys;
    }
}
