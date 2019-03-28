package com.nevercome.shiroweb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getRsouece() {
        return jedisPool.getResource();
    }

    public void set(byte[] key, byte[] value) {
        Jedis jedis = getRsouece();
        try {
            jedis.set(key,value);
        } finally {
            jedis.close();
        }
    }

    public void expire(byte[] key, int i) {
        Jedis jedis = getRsouece();
        try {
            jedis.expire(key,i);
        } finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getRsouece();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public void delete(byte[] key) {
        Jedis jedis = getRsouece();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefix) {
        Jedis jedis = getRsouece();
        try {
            return jedis.keys((shiro_session_prefix + "*").getBytes());
        } finally {
            jedis.close();
        }
    }
}
