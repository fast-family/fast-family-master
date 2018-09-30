package com.fast.family.generator.db;


import com.fast.family.generator.config.GeneratorConfig;
import com.fast.family.generator.model.ColumnInfo;
import com.fast.family.generator.model.TableInfo;
import com.fast.family.generator.utils.WordUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/29-0:10
 */
public class AnalysisDB {

    public static final List<TableInfo> getAllTableInfoList(GeneratorConfig dbConfig){
        List<TableInfo> list = new ArrayList<>();
        String sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.tables WHERE table_schema ='"+dbConfig.getDbName()+"'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DBHandler.createConnection(dbConfig).prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TableInfo tm = new TableInfo();
                tm.setSchemaName(dbConfig.getDbName());
                tm.setTableName(rs.getString("TABLE_NAME"));
                tm.setComment(rs.getString("TABLE_COMMENT"));
                list.add(tm);
            }
        } catch (SQLException e) {
            throw new RuntimeException("sql执行失败！");
        } finally {
            try {
                rs.close();
                ps.close();
                DBHandler.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        list.forEach(tableInfo -> {
            fillTableColInfo(tableInfo,dbConfig);
        });
        return list;
    }

    public static final TableInfo getTableInfoByName(String tableName,GeneratorConfig dbConfig){
        String sql = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.tables WHERE table_schema = " +
                "'"+dbConfig.getDbName()+"' AND TABLE_NAME = '"+tableName+"'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableInfo tableInfo = new TableInfo();
        try {
            ps = DBHandler.createConnection(dbConfig).prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                tableInfo.setSchemaName(dbConfig.getDbName());
                tableInfo.setTableName(rs.getString("TABLE_NAME"));//表名
                tableInfo.setComment(rs.getString("TABLE_COMMENT"));//描述
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                DBHandler.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        fillTableColInfo(tableInfo,dbConfig);
        return tableInfo;
    }

    private static final void fillTableColInfo(TableInfo table,GeneratorConfig dbConfig){
        List<ColumnInfo> list = new ArrayList<>();
        String sql = "SELECT DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,COLUMN_NAME,ORDINAL_POSITION,IS_NULLABLE,COLUMN_DEFAULT,COLUMN_TYPE,COLUMN_KEY,EXTRA,COLUMN_COMMENT" +
                " FROM information_schema.columns WHERE table_schema = '"+dbConfig.getDbName()+"' AND table_name = '"+table.getTableName()+"'";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBHandler.createConnection(dbConfig).prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                ColumnInfo fm = new ColumnInfo();
                String columnName = rs.getString("COLUMN_NAME");
                String columnJavaName = StringUtils.uncapitalize(WordUtils.columnToJava(columnName));
                if (!"createTime".equals(columnJavaName) && !"lastUpTime".equals(columnJavaName)){
                    String dataType = rs.getString("DATA_TYPE");
                    String columnType = rs.getString("COLUMN_TYPE");
                    String length = rs.getString("CHARACTER_MAXIMUM_LENGTH");
                    if (StringUtils.isBlank(length)){
                        if (columnType.indexOf("(") != -1){
                            length = columnType.substring(columnType.indexOf("(") + 1,columnType.indexOf(")"));
                        }
                    }
                    fm.setLength(length);
                    fm.setDataType(dataType);
                    fm.setColumnComment(rs.getString("COLUMN_COMMENT"));
                    fm.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
                    fm.setColumnKey(rs.getString("COLUMN_KEY"));
                    fm.setColumnName(columnName);
                    fm.setColumnJavaName(columnJavaName);
                    fm.setColumnType(rs.getString("COLUMN_TYPE"));
                    fm.setExtra(rs.getString("EXTRA"));
                    fm.setIsNullable(rs.getString("IS_NULLABLE"));
                    fm.setOrdinalPosition(rs.getString("COLUMN_COMMENT"));
                    list.add(fm);
                }

            }
            table.setColumnInfoList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
