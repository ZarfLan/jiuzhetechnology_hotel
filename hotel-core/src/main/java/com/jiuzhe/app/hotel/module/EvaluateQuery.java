package com.jiuzhe.app.hotel.module;

/**
 * @Description:用户评价
 */
public class EvaluateQuery {
    //订单id
    private String orderId;
    //酒店评分
    private Integer skuScore;
    //卫生评分
    private Integer cleanScore;
    //房间问题
    private String  skuProblem;
    //卫生问题
    private String cleanProblem;
    private String skuId;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSkuScore() {
        return skuScore;
    }

    public void setSkuScore(Integer skuScore) {
        this.skuScore = skuScore;
    }

    public String getSkuProblem() {
        return skuProblem;
    }

    public void setSkuProblem(String skuProblem) {
        this.skuProblem = skuProblem;
    }

    public Integer getCleanScore() {
        return cleanScore;
    }

    public void setCleanScore(Integer cleanScore) {
        this.cleanScore = cleanScore;
    }



    public String getCleanProblem() {
        return cleanProblem;
    }

    public void setCleanProblem(String cleanProblem) {
        this.cleanProblem = cleanProblem;
    }
}
