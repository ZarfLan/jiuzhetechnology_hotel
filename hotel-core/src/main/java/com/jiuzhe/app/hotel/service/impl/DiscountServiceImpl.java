package com.jiuzhe.app.hotel.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.service.DiscountService;
import com.jiuzhe.app.hotel.service.RedisService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description:获取折扣价
 * @author:郑鹏宇
 * @date 2018/6/25/025
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    RedisService redisService;

    @Override
    public Integer getDiscount(Integer vipLevel) {
        String sql = "SELECT * FROM level_discount";
        String result = redisService.getResult("level_discount", sql);
        List<Map<String,Object>> list = null;
        try {
             list = new ObjectMapper().readValue(result, List.class);
        } catch (IOException e) {
            return -1;
        }
        for (Map<String,Object> map:list) {
            if (map.get("id").toString().equals(vipLevel.toString())) {
                return  Integer.parseInt(map.get("discount").toString());
            }
        }
        return -1;
    }
}
