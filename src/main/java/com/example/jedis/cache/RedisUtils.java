package com.example.jedis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {

    @Autowired
    JedisPool jedisPool;

    public String set(String key,String value){
        Jedis jedis = jedisPool.getResource();
        String str ="";
        try {
            str = jedis.set(key,value);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            jedis.close();
        }
        return str;
    }

    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        String str ="";
        try {
            str = jedis.get(key);
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            jedis.close();
        }
        return str;
    }
}
