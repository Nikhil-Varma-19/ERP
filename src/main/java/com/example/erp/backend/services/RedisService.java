package com.example.erp.backend.services;

import java.util.concurrent.TimeUnit;

public interface RedisService {

    Object getKey(String key);

    Object setAndGetKeyWithTime(String key, Object value, long ttl, TimeUnit timeUnit);

    void setKey(String key, Object value, long ttl, TimeUnit timeUnit);

}