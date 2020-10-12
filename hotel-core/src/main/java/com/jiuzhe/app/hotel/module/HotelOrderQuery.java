package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class HotelOrderQuery implements Serializable {
    private static final long serialVersionUID = -7735766356188951273L;
    @ApiModelProperty(value="订单id",example="87d9fe4238f74d84be15d62984fdfe65")
    private String id;
    @ApiModelProperty(value="用户id",example="87d9fe4238f74d84be15d62984fdfe65")
    private String userId;
    @ApiModelProperty(value="房间id",example="87d9fe4238f74d84be15d62984fdfe66")
    private String skuId;
    @ApiModelProperty(value="房间总价",example="5096")
    private Integer skuPrice;
    @ApiModelProperty(value="押金",example="200")
    private Integer skuBond;
    @ApiModelProperty(value="开始日期",example="2018-05-01")
    private LocalDate startDate;
    @ApiModelProperty(value="结束日期",example="2018-05-03")
    private LocalDate endDate;
    @ApiModelProperty(value="入住人姓名",example="张三")
    private String occupantName;
    @ApiModelProperty(value="入住人身份证",example="420104***********************")
    private String occupantCard;
    @ApiModelProperty(value="入住人电话",example="13711111111")
    private String occupantPhone;
    @ApiModelProperty(value="入住天数",example="2")
    private Integer dayNum;
    @ApiModelProperty(value="vip级别",example="2")
    private Integer vipLevel;
    private Integer onLine;


    public Integer getOnLine() {
        return onLine;
    }

    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Integer skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getSkuBond() {
        return skuBond;
    }

    public void setSkuBond(Integer skuBond) {
        this.skuBond = skuBond;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getOccupantCard() {
        return occupantCard;
    }

    public void setOccupantCard(String occupantCard) {
        this.occupantCard = occupantCard;
    }

    public String getOccupantPhone() {
        return occupantPhone;
    }

    public void setOccupantPhone(String occupantPhone) {
        this.occupantPhone = occupantPhone;
    }

    @Override
    public String toString() {
        return "HotelOrderDto{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuPrice=" + skuPrice +
                ", skuBond=" + skuBond +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", occupantName='" + occupantName + '\'' +
                ", occupantCard='" + occupantCard + '\'' +
                ", occupantPhone='" + occupantPhone + '\'' +
                ", dayNum=" + dayNum +
                ", vipLevel=" + vipLevel +
                '}';
    }
}
