package com.example.erp.backend.services.impls;

import com.example.erp.backend.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Object getKey(String key) {
        return redisTemplate.opsForValue().get(key) ;
    }

    @Override
    public Object setAndGetKeyWithTime(String key, Object value, long ttl, TimeUnit timeUnit) {
           Boolean success=redisTemplate.opsForValue().setIfAbsent(key,value,ttl,timeUnit);
           if(Boolean.TRUE.equals(success)) return  value;
           return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setKey(String key, Object value, long ttl, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }



}
