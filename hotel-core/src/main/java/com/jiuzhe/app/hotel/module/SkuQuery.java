package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description:添加房源给请求参数
 */
@ApiModel(value = "SkuQuery", description = "添加房源给请求参数")
public class SkuQuery {

    @ApiModelProperty(value = "酒店id", example = "2221")
    private String skuId;

    @ApiModelProperty(value = "房间id", example = "2221")
    private String skuName;

    @ApiModelProperty(value = "商户id", example = "2221")
    private String merchantId;

    @ApiModelProperty(value = "酒店地址", example = "2221")
    private String address;

    @ApiModelProperty(value = "城市名", example = "武汉")
    private String cityName;

    @ApiModelProperty(value = "区域名", example = "武昌区")
    private String area;

    @ApiModelProperty(value = "酒店位置经度", example = "2221")
    private Integer lng;

    @ApiModelProperty(value = "酒店位置维度", example = "2221")
    private Integer lat;

    @ApiModelProperty(value = "房间价格（默认价格）", example = "2221")
    private Integer roomPrice;

    @ApiModelProperty(value = "房间状态", example = "2221")
    private Integer roomStatus;

    @ApiModelProperty(value = "押金", example = "2221")
    private Integer roomBond;

    @ApiModelProperty(value = "hrId,登陆人id", example = "2221")
    private String hrId;

    @ApiModelProperty(value = "房间类型（大床，豪华）", example = "2")
    private String roomType;

    @ApiModelProperty(value = "是否有无线", example = "1")
    private Integer wifi;

    @ApiModelProperty(value = "卧室数量", example = "1")
    private Integer bedroomNum;

    @ApiModelProperty(value = "卫生间数量", example = "1")
    private Integer toiletNum;

    @ApiModelProperty(value = "房号", example = "K2012")
    private String roomNo;

    @ApiModelProperty(value = "床位数", example = "2")
    private Integer bedNum;

    @ApiModelProperty(value = "描述", example = "这个房子很棒")
    private String remark;

    @ApiModelProperty(value = "权重", example = "5.0")
    private Integer weight;

    @ApiModelProperty(value = "打分", example = "30")
    private Integer score;

    @ApiModelProperty(value = "照片数组", example = "30")
    private String[] imgs;

    private String storeId;

    private String layoutId;


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

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer score) {
        this.weight = weight;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getBedroomNum() {
        return bedroomNum;
    }


    public void setBedroomNum(Integer bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public Integer getToiletNum() {
        return toiletNum;
    }

    public void setToiletNum(Integer toiletNum) {
        this.toiletNum = toiletNum;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getRoomBond() {
        return roomBond;
    }

    public void setRoomBond(Integer roomBond) {
        this.roomBond = roomBond;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getBedNum() {
        return bedNum;
    }

    public void setBedNum(Integer bedNum) {
        this.bedNum = bedNum;
    }

}
