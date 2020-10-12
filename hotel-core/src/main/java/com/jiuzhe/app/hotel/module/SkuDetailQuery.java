package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:地图框点击出现弹框的要求
 */
@ApiModel(value="SkuDetailQuery",description="SkuDetailQuery")
public class SkuDetailQuery {
    /**
     * 酒店id
     */
    @ApiModelProperty(value="酒店id",example="ssssss")
    private String id;
    /**
     * 日期下限
     */
    @ApiModelProperty(value="日期下限",example="2016-06-01")
    private String startDate;
    /**
     * 日期上限
     */
    @ApiModelProperty(value="日期上限",example="2016-06-02")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}