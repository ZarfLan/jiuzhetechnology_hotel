package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:取消订单需要的条件
 */
@ApiModel(value="OrderCancelQuery",description="取消订单需要的条件")
public class OrderCancelQuery {
    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",example = "2")
    private String id;

    /**
     * 房间id
     */
    @ApiModelProperty(value="房间id",example = "215")
    private String skuId;

    /**
     * 订单开始时间
     */
    @ApiModelProperty(value="订单显示的开始时间",example = "2018-05-25")
    private String startDate;
    /**
     * 订单结束时间
     */
    @ApiModelProperty(value="订单显示的接结束时间",example = "2018-05-26")
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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
