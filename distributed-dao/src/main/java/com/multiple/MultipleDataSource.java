package com.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by lenovo on 2019/4/19.
 * Title MultipleDataSource
 * Package  com.Multiple
 * Description
 *
 * @Version V1.0
 */
public class MultipleDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return MultipleDataSourceHolder.getDataSourceType();
    }
}
