package com.fast.family.datasource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/1-21:00
 */
public interface DynamicDataSource {


    Map<String,DataSource> loadDataSource();

}
