package com.fast.family.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableInfo {

    private String tableName;

    private String schemaName;

    private String comment;

    private List<ColumnInfo> columnInfoList;
}
