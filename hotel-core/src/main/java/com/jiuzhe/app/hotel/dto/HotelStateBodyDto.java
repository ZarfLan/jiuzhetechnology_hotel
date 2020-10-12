package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.HotelOrder;

/**
 * @Description:房态的返回体包括（房间id，房间名，住户名，订单id，房间状态）
 */
public class HotelStateBodyDto {

    /**
     *房间id
     */
    private String skuId;
    /**
     *房间名
     */
    private String skuName;
    /**
     *住户名
     */
    private String userName;
    /**
     *订单id
     */
    private String orderId;
    /**
     *房间状态
     */
    private Integer orderStatus;


    private String roomNo;

    private String date;

    public static HotelStateBodyDto make(HotelOrder order) {
        HotelStateBodyDto dto = new HotelStateBodyDto();
        dto.setSkuId(order.getSkuId());
        dto.setOrderId(order.getId());
        dto.setSkuName(order.getSkuName());
        dto.setUserName(order.getOccupantName());
        dto.setOrderStatus(order.getStatus());
        dto.setRoomNo(order.getRoomNo());
        return dto;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
