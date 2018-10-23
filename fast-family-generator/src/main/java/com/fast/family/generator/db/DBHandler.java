package com.fast.family.generator.db;

import com.fast.family.generator.config.GeneratorConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 张顺
 * @version 1.0
 */
public class DBHandler {

    private static Connection connection;

    public static final Connection createConnection(GeneratorConfig dbConfig)  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ dbConfig.getDbHost()+":"+dbConfig.getPort()
                    + "/" + dbConfig.getDbName(),dbConfig.getDbUser(),dbConfig.getDbPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接池创建失败");
        }
        return connection;
    }

    public static final void close(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
