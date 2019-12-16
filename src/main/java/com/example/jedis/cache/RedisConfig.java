package com.example.jedis.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdLe;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(maxIdLe);
        jedisPoolConfig.setMaxIdle(maxIdLe);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port);
        return jedisPool;
    }
}
