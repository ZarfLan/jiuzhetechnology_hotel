package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:批量修改放盘价，押金，房间状态
 */
@ApiModel(value = "SkuQuery", description = "批量修改放盘价，押金，房间状态")
public class UpAllSkuPriceQuery {
    @ApiModelProperty(value = "酒店id集合", example = "1,2,3")
    public String skuId;
    @ApiModelProperty(value = "开始日期", example = "2018-05-01")
    public String startDate;
    @ApiModelProperty(value = "结束日期", example = "2018-05-10")
    public String endDate;
    @ApiModelProperty(value = "放盘价格", example = "500")
    public Integer listingPrice;


    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
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

    public Integer getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Integer listingPrice) {
        this.listingPrice = listingPrice;
    }
}
