package com.jobportal.util;

import redis.clients.jedis.Jedis;

public class RedisTest {

    public static void main(String[] args) {

        try (Jedis jedis = RedisConnection.getConnection()) {

            jedis.set("test", "hello");

            String value = jedis.get("test");

            System.out.println("Value from Redis: " + value);
        }
    }
}