package com.matrix.join;

import com.matrix.join.service.MailService;
import com.matrix.join.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;

@SpringBootTest
class JoinApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	MailService mailService;

	@Test
	void contextLoads() {
		System.out.println(dataSource);
	}

	@Test
	void connectionTest() {
//	    redisUtil.set("18374935971", "123456");
//		String s = redisUtil.get("18374935971");
//		System.out.println(s);
		mailService.sendSimpleMessage("1181209156@qq.com");
	}

	@Test
	void test() {
		String host = "r-wz96eeac5d24f3b4pd.redis.rds.aliyuncs.com";//控制台显示访问地址
		int port = 6379;
		Jedis jedis = new Jedis(host, port);
		//鉴权信息
		jedis.auth("admin:Rp829dlwa");//password
		String key = "redis";
		String value = "aliyun-redis";
		//select db默认为0
		jedis.select(1);
		//set一个key
		jedis.set(key, value);
		System.out.println("Set Key " + key + " Value: " + value);
		//get 设置进去的key
		String getvalue = jedis.get(key);
		System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
		jedis.quit();
		jedis.close();
	}
}
