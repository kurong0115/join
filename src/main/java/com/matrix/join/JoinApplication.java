package com.matrix.join;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Administrator
 */
@EnableAsync
@EnableScheduling
@MapperScan("com.matrix.join.mapper")
@SpringBootApplication
public class JoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoinApplication.class, args);
	}

}
