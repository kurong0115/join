package com.matrix.join;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.join.service.DeliveryRecordService;
import com.matrix.join.service.MailService;
import com.matrix.join.util.RedisUtil;
import com.matrix.join.vo.DeliveryRecordVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigInteger;

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

	@Autowired
	DeliveryRecordService deliveryRecordService;

	@Test
	void test() {
        IPage<DeliveryRecordVO> deliveryRecordVOIPage = deliveryRecordService.listDeliveryRecord(null, new BigInteger("1585883529684"), null, null, null, new Page<>(1, 1));
        System.out.println(deliveryRecordVOIPage.getRecords());
    }
}
