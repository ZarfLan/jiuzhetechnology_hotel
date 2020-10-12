package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.CountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CountDao {

    @Autowired
    CountMapper mapper;

    public Map<String,String> getOrderNumIncome(String merchantId,String storeId) {
        return mapper.getOrderNumIncome(merchantId,storeId);
    }

    public Map<String,String> getDirtyNum(String merchantId,String storeId) {
        return mapper.getDirtyNum(merchantId,storeId);
    }

    public Map getManageCountInfo(String storeId) {
        return mapper.getManageCountInfo(storeId);
    }

    public Map getDirtyRoom(String storeId) {
        return mapper.getDirtyRoom(storeId);
    }
}
