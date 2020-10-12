package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.RegionMapper;
import com.jiuzhe.app.hotel.entity.HotelRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 */
@Repository
public class RegionDao {

    @Autowired
    RegionMapper mapper;

    /**
     * @Description:获取所有的城市
     * @author:郑鹏宇
     * @date:2018/6/13
     */
    public List<HotelRegion> getAllRegion() {
        return mapper.getAllRegion();
    }
}
