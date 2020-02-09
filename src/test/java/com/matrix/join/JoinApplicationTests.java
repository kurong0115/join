package com.matrix.join;

import com.matrix.join.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class JoinApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	RedisUtil redisUtil;

	@Test
	void contextLoads() {
		System.out.println(dataSource);
	}

	@Test
	void connectionTest() {
	    redisUtil.set("18374935971", "123456");
		String s = redisUtil.get("18374935971");
		System.out.println(s);
	}
}
