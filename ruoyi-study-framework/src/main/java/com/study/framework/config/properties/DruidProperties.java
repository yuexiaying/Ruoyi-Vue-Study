package com.study.framework.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Configuration;

/**
 * druid配置属性
 *
 * @author fjding
 * @date 2021/9/11
 */
@Configuration
public class DruidProperties {

    // todo 一些配置属性，目前先不做

    /**
     * 设置连接池的属性
     *
     * @param dataSource
     * @return
     */
    public DruidDataSource setDataSourceProp(DruidDataSource dataSource) {
        return dataSource;
    }

}
