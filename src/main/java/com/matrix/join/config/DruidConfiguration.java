package com.matrix.join.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DruidConfiguration
 * @Description Druid数据源配置类
 * @Author Administrator
 * @Date 2019/11/29 11:25
 * @Version 1.0
 */
@Configuration
public class DruidConfiguration {

	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource(){
		return new DruidDataSource();
	}
}
