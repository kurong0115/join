package com.matrix.join.service.impl;

import com.matrix.join.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/21 23:14
 * @Version 1.0
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 3/1 * * ?")
    @Override
    public void flushDB() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }
}
