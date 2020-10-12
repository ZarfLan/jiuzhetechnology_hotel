package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;

/**
 * @Description:前台二次传输的参数
 */
@ApiModel(value="searchQuery",description="查询所需要的属性条件")
public class SearchQuery {
    /**
     * 经纬度范围
     */
    private Integer beginLng;
    private Integer endLng;
    private Integer beginLat;
    private Integer endLat;
    /**
     * 日期范围
     */
    private String startDate;
    private String endDate;
    /**
     * 价格范围
     */
    private Integer beginPrice;
    private Integer endPrice;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Integer getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(Integer beginPrice) {
        this.beginPrice = beginPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

}
