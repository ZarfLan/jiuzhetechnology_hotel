package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.entity.HotelRegion;

import java.util.List;

public interface RegionService {

    /**
     * @Description:获取所有的城市
     */
    List<HotelRegion> getAllRegion();
}
