package com.jiuzhe.app.hotel.dto;

/**
 * @Description:订单成功后返回的数据
 */
public class OrderSuccessDto {

    /**
     * 房间id
     */
    private String skuId;

    /**
     * 订单id
     */
    private String OrderId;

    /**
     * 商户id
     */
    private  String merchantId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
