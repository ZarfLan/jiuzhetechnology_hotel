package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.dao.CountDao;
import com.jiuzhe.app.hotel.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description:统计功能
 * @author:郑鹏宇
 * @date: 2018/5/22
 */
@Service
public class CountServiceImpl implements CountService {

    @Autowired
    CountDao dao;

    @Override
    public Map<String, String> getIndexCount(String merchantId, String storeId) {
        Map<String, String> map = dao.getOrderNumIncome(merchantId, storeId);
        Map<String, String> map1 = dao.getDirtyNum(merchantId, storeId);
        map.put("dirtyNum", map1.get("dirtyNum"));
        return map;
    }

    @Override
    public Map getManageCountInfo(String storeId) {

        Map map=  dao.getManageCountInfo(storeId);
        Map mapDirty = dao.getDirtyRoom(storeId);
        System.out.println(mapDirty +"========================");
        map.putAll(mapDirty);
        return map;
    }

}
