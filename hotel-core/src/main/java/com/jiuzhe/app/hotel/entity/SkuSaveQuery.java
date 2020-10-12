package com.jiuzhe.app.hotel.entity;

import java.util.List;

/**
 * @Description:批量添加房源
 */
public class SkuSaveQuery {

    private String merchantId;

    private String storeId;

    private String layoutId;

    private Integer roomPrice;

    private Integer roomBond;

    private List<String> roomNos;

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomBond() {
        return roomBond;
    }

    public void setRoomBond(Integer roomBond) {
        this.roomBond = roomBond;
    }


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public List<String> getRoomNos() {
        return roomNos;
    }

    public void setRoomNos(List<String> roomNos) {
        this.roomNos = roomNos;
    }
}
