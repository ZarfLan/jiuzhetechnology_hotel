package com.jiuzhe.app.hotel.dto;

import com.google.common.base.Splitter;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.jiuzhe.app.hotel.constants.HotelTypeEnum;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:前台广告
 */
@ApiModel(value = "广告", description = "广告DTO")
public class RecommentDto {
    @ApiModelProperty(value = "房间ID", example = "1")
    private String skuId;
    @ApiModelProperty(value = "房间名", example = "1")
    private String skuName;
    @ApiModelProperty(value = "房号", example = "1")
    private String roomNo;
    @ApiModelProperty(value = "地址", example = "1")
    private String address;
    @ApiModelProperty(value = "房间价格", example = "1")
    private Integer price;
    @ApiModelProperty(value = "照片", example = "1")
    private List<String> imgList;
    @ApiModelProperty(value = "权重", example = "5.0")
    private Integer score;
    @ApiModelProperty(value = "权重", example = "5.0")
    private Integer weight;
    @ApiModelProperty(value = "房间类型", example = "1")
    private String roomType;
    @ApiModelProperty(value = "城市", example = "武汉市")
    private String city_name;
    @ApiModelProperty(value = "区域", example = "洪山区")
    private String area;
    private String merchantId;

    /**
     * 门店照片
     */
    private String storePic;
    /**
     * 前台照片
     */
    private String receptionPic;

    public static RecommentDto make(HotelSku hotelSku) {
        RecommentDto dto = new RecommentDto();
        dto.setSkuId(hotelSku.getId());
        dto.setAddress(hotelSku.getAddress());
        dto.setSkuName(hotelSku.getSkuName());
        dto.setPrice(hotelSku.getRoomPrice());
        dto.setRoomNo(hotelSku.getRoomNo());
        dto.setSkuName(hotelSku.getSkuName());
        dto.setScore(hotelSku.getScore());
        dto.setWeight(hotelSku.getWeight());
        dto.setRoomType(hotelSku.getRoomType());
        dto.setCityName(hotelSku.getCityName());
        dto.setArea(hotelSku.getArea());
        dto.setMerchantId(hotelSku.getMerchantId());
        if (null != hotelSku.getImgUrls()) {
            dto.setImgList(StringUtil.stringToList(hotelSku.getImgUrls()));
        } else {
            List<String> picList = StringUtil.stringToList(hotelSku.getLaypicList());
            List<String> list = new ArrayList<>();
            list.addAll(picList);
            if (null != hotelSku.getStorePic()) {
                list.add(0,hotelSku.getStorePic());
            }
            dto.setImgList(list);
        }
        return dto;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getReceptionPic() {
        return receptionPic;
    }

    public void setReceptionPic(String receptionPic) {
        this.receptionPic = receptionPic;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCityName() {
        return city_name;
    }

    public void setCityName(String city_name) {
        this.city_name = city_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
