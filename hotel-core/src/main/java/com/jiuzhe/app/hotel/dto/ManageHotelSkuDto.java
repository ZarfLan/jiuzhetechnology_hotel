package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.HotelSku;

public class ManageHotelSkuDto {
    /**
     * 酒店id
     */
    private String skuId;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 酒店位置经度
     */
    private Integer lng;
    /**
     * 酒店位置维度
     */
    private Integer lat;

    /**
     * 房间状态
     */
    private Integer roomStatus;

    /**
     * 房间价格
     */
    private Integer price;

    public static ManageHotelSkuDto make(HotelSku hotelSku) {
        ManageHotelSkuDto dto = new ManageHotelSkuDto();
        dto.setSkuId(hotelSku.getId());
        dto.setLng(hotelSku.getLng());
        dto.setLat(hotelSku.getLat());
        dto.setRoomStatus(hotelSku.getRoomStatus());
        dto.setMerchantId(hotelSku.getMerchantId());
        //对价格进行处理（放盘价格优先）
        if (null != hotelSku.getListingPrice()) {
            dto.setPrice(hotelSku.getListingPrice());
        } else {
            dto.setPrice(hotelSku.getRoomPrice());
        }
        return dto;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ManageHotelSkuDto{" +
                "skuId='" + skuId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                '}';
    }
}
