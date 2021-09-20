package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 * @author fjding
 * @date 2021/9/11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan(basePackages = "com.study.**.mapper")
public class RuoyiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiApplication.class,args);
    }
}
