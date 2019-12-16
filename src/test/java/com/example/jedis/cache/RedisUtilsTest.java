package com.example.jedis.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RedisUtilsTest {

    @Autowired
    RedisUtils redisUtils;

    @Test
    void set() {
        String str = redisUtils.set("a","aaaa");
        System.out.println(str);
    }

    @Test
    void get() {
    }
}