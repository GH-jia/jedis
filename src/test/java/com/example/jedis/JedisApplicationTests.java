package com.example.jedis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.example.jedis.cache.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class JedisApplicationTests {

//    @Value("${spring.redis.host}")
//    String host;
//
//    @Value("${spring.redis.port}")
//    int port;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JedisPool jedisPool;

    @Test
    void setPerson(){
        Person person = new Person(10001,"hejia");
        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        String key = "person:" + person.getId();
        byte[] bytes = ProtostuffIOUtil.toByteArray(person,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        Jedis jedis = jedisPool.getResource();
        jedis.set(key.getBytes(),bytes);
        System.out.println(key.getBytes().toString());
    }
    @Test
    void getPerson(){
        Person person = new Person(10001,"hejia");
        String key = "person:"+person.getId();
        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.get(key.getBytes());
        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        if (bytes != null){
            Person person1 = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes,person1,schema);
        }

    }

    @Test
    void contextLoads() {
        Jedis jedis = new Jedis();
        jedis.set("aa","aaaa");
        String value = jedis.get("aa");
        System.out.println(value);
    }

    @Autowired
    TestBean testBean;

    @Test
    void jedisTest(){
//        Jedis jedis = jedisPool.getResource();
//        jedis.set("aa","aaaa");
//        String value = jedis.get("aa");
//        System.out.println(value);
    }

}
