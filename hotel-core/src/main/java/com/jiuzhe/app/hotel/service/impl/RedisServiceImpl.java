package com.jiuzhe.app.hotel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiuzhe.app.hotel.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate rt;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getResult(String redisKey, String sql) {
        String result = null;
        String resultJson = null;
        boolean redisUp = true;
        try {
            result = rt.opsForValue().get(redisKey);
        } catch (Exception e) {
            redisUp = false;
        }

        if (null == result) {
            List<Map<String, Object>> records = jdbcTemplate.queryForList(sql);
            ObjectMapper mapper = new ObjectMapper();
            try {
                resultJson = mapper.writeValueAsString(records);
                result = resultJson;
            } catch (Exception e) {
                return "toJsonError";
            }
            if (redisUp)
                rt.opsForValue().set(redisKey, resultJson);
        }
        return result;
    }
}
