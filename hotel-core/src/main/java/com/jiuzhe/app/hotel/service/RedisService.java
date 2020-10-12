package com.jiuzhe.app.hotel.service;

import java.util.List;

public interface RedisService {

    public String getResult(String redisKey, String sql);
}
