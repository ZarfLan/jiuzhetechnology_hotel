package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.HotelSku;

/**
 * @Description:获取经纬度信息
 */
public class LatLngDto {

    /**
     * 房间id
     */
    private String skuId;
    /**
     * 经度
     */
    private Integer lng;
    /**
     * 维度
     */
    private Integer lat;

    public static LatLngDto make(HotelSku hotelSku) {
        LatLngDto dto = new LatLngDto();
        dto.setSkuId(hotelSku.getId());
        dto.setLng(hotelSku.getLng());
        dto.setLat(hotelSku.getLat());
        return dto;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }
}
