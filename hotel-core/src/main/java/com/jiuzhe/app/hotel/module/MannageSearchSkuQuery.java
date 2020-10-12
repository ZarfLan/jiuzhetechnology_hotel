package com.jiuzhe.app.hotel.module;

/**
 * @Description:查询商户房间的条件
 */
public class MannageSearchSkuQuery {
    private String skuId;
    private String merchantId;
    //精度范围
    private Integer beginLng;
    private Integer endLng;
    //唯独范围
    private Integer beginLat;
    private Integer endLat;
    //城市名
    private String cityName;
    //区域
    private String area;
    //酒店名
    private String skuName;
    //房间号
    private String roomNo;
    //酒店类型
    private String roomType;
    //门店id
    private String storeId;
    //属性表id
    private String layoutId;
    //地址
    private String address;
    //当前页
    private Integer currentPage;
    //每页展示的数量
    private Integer number;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getBeginLng() {
        return beginLng;
    }

    public void setBeginLng(Integer beginLng) {
        this.beginLng = beginLng;
    }

    public Integer getEndLng() {
        return endLng;
    }

    public void setEndLng(Integer endLng) {
        this.endLng = endLng;
    }

    public Integer getBeginLat() {
        return beginLat;
    }

    public void setBeginLat(Integer beginLat) {
        this.beginLat = beginLat;
    }

    public Integer getEndLat() {
        return endLat;
    }

    public void setEndLat(Integer endLat) {
        this.endLat = endLat;
    }

}
