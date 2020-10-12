package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:查询商户房间的条件
 */
@ApiModel(value = "MannageSkuQuery", description = "询商户房间的条件")
public class MannageSkuQuery {

    @ApiModelProperty(value = "商户id", example = "2")
    private String merchantId;
    @ApiModelProperty(value = "城市名", example = "武汉")
    private String cityName;
    @ApiModelProperty(value = "区域", example = "武昌")
    private String area;

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

}
