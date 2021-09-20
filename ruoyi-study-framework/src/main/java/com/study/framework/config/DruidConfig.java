package com.study.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.study.framework.config.properties.DruidProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * druid 配置多数据源
 *
 * @author fjding
 * @date 2021/9/11
 */
@Configuration
public class DruidConfig {

    /**
     * 配置主连接池
     *
     * @param druidProperties
     * @return
     */
    @Bean
    /**
     * 为什么要有ConfigurationProperties？
     * 相当于为生成的DataSource中url/username/password属性赋值
     * 该注解不仅仅可以用于类上，用于方法上也相当于为返回的对象中属性赋值
     */
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.setDataSourceProp(dataSource);
    }
}
