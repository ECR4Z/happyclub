package com.ming.happyclub.generate.code.mbg.enums;

/**
 * @version 1.0
 * @description: TODO
 * @date 2023/11/21
 * @Replenishment: TODO
 */
public enum MBGEnum {
    TABLENAME("tableName"),
    TABLE_NAME("table_name"),

    COLUMNS("columns"),
    DATETIME("datetime"),
    PACKAGENAME("packageName"),
    MODULENAME("moduleName"),
    COMMENT("comment"),
    COLUMN_NAME("column_name"),
    DATA_TYPE("data_type"),
    JAVALOWERATTRNAME("javaLowerAttrName"),
    JAVAATTRTYPE("javaAttrType"),
    COLUMN_COMMENT("column_comment"),
    UPPERCLASSNAME("upperClassName"),
    LOWERCLASSNAME("lowerClassName");

    private String key;

    MBGEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
