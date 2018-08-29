package com.bjsxt.test;

import com.bjsxt.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * redis 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *向redis添加键值对
     */
    @Test
    public void test() {
        //redisTemplate.opsForValue().set( "key","test" );
        redisTemplate.opsForValue().set( "key1","test1" );
    }

    /**
     *获取redis中的数据
     */
    @Test
    public void test1() {
        String key = (String) redisTemplate.opsForValue().get( "key" );
        System.out.println( "key的值" + key );
    }

    /**
     *添加user
     */
    @Test
    public void test2() {
        User user = new User( 1, "张三", 30 );
        redisTemplate.setValueSerializer( new JdkSerializationRedisSerializer());
        redisTemplate.opsForValue().set( "user",user );
    }

    /**
     *获取user
     */
    @Test
    public void test3() {
        redisTemplate.setValueSerializer( new JdkSerializationRedisSerializer() );
        User user = (User) redisTemplate.opsForValue().get( "user" );
        System.out.println( user );
    }

    /**
     *json
     */
    @Test
    public void test4() {
        User user = new User( 2, "李四", 23 );
        this.redisTemplate.setValueSerializer( new Jackson2JsonRedisSerializer<>( User.class ) );
        this.redisTemplate.opsForValue().set( "userjson",user );
    }

    /**
     *获取json
     */
    @Test
    public void test5() {
        this.redisTemplate.setValueSerializer( new Jackson2JsonRedisSerializer<>( User.class ) );
        User userjson = (User) this.redisTemplate.opsForValue().get( "userjson" );
        System.out.println( userjson );
    }
}
