package com.jiuzhe.app.hotel.entity;

import java.io.Serializable;

/**
 * @Description:默认地址的实体类
 */
public class HotelRegion {

    /**
     * 主键
     */
    private String id;

    /**
     * 城市名
     */
    private String cityName;
    /**
     *区域
     */
    private String areas;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }
}
