package com.fast.family.datasource;

import java.util.Map;

/**
 * @author 张顺
 * @version 1.0
 */
public interface DynamicDataSource {


    Map<Object, Object> loadDataSource();

}
