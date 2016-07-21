package com.kaishengit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisStringTestCase {

    private Jedis jedis = null;

    @Before
    public void setUp() {
        jedis = new Jedis("192.168.163.130");
    }

    @After
    public void close() {
        if(jedis != null) {
            jedis.close();
        }
    }


    @Test
    public void testSet() {
        jedis.set("jedis","张三");
    }

    @Test
    public void testGet() {
        String value = jedis.get("jedis");
        System.out.println(value);
        Assert.assertEquals("张三",value);
    }

    @Test
    public void testExists() {
        if(!jedis.exists("jedis")) {
            jedis.set("jedis","version-2");
        }
    }

    @Test
    public void testIncr() {
        String key = "post:1:viewnum";
        jedis.incr(key);
        System.out.println(jedis.get("post:1:viewnum"));
    }

    @Test
    public void testIncrBy() {
        String key = "post:1:viewnum";
        jedis.incrBy(key,10);
        System.out.println(jedis.get("post:1:viewnum"));
    }

    @Test
    public void testDecr() {
        String key = "post:1:viewnum";
        jedis.decr(key);
        System.out.println(jedis.get("post:1:viewnum"));
    }

    @Test
    public void testIncrByFloat() {
        String key = "user:1:money";
        System.out.println(jedis.get(key));

        jedis.incrByFloat(key,4.5F);

        System.out.println(jedis.get(key));
    }

    @Test
    public void testAppend() {
        jedis.append("jedis","丰");
        System.out.println(jedis.get("jedis"));
    }

    @Test
    public void testStrLen() {
        System.out.println(jedis.strlen("jedis"));
    }

    @Test
    public void testMset() {
        jedis.mset("k1","v1","k2","v2");
    }

    @Test
    public void testMget() {
        List<String> lists = jedis.mget("k1","k2");
        for(String str : lists) {
            System.out.println(str);
        }
    }



}
