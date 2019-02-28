package com.fast.family.generator.model;

import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class ColumnInfo {

    private String columnName;

    private String columnJavaName;

    private String ordinalPosition;

    private String isNullable;

    private String columnDefault;

    private String columnType;

    private String columnKey;

    private String extra;

    private String columnComment;

    private String dataType;

    private String length;


    public String getDataType() {
        String type = columnType.toLowerCase();
        String propertyType = null;
        if (type.startsWith("int")) {
            propertyType = "Integer";
        } else if (type.startsWith("tinyint")) { // int
            propertyType = "Integer";
        } else if (type.startsWith("bigint")) { // long
            propertyType = "Long";
        } else if (type.startsWith("double")) { // double
            propertyType = "double";
        } else if (type.startsWith("float")) { // float
            propertyType = "float";
        } else if (type.startsWith("varchar")) { // String
            propertyType = "String";
        } else if (type.startsWith("char")) { // String
            propertyType = "String";
        } else if (type.startsWith("text")) { // String
            propertyType = "String";
        } else if (type.startsWith("date")) { // date
            propertyType = "Date";
        } else if (type.startsWith("datetime")) { // date
            propertyType = "Date";
        } else if (type.startsWith("timestamp")) { // date
            propertyType = "Date";
        } else if (type.startsWith("decimal")) {
            propertyType = "BigDecimal";
        } else {
            System.out.println("==类型[" + type + "]解析尚不支持==");
        }
        return propertyType;
    }
}
