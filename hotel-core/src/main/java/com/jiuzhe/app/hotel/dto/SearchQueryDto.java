package com.jiuzhe.app.hotel.dto;

import com.google.common.base.Splitter;
import com.jiuzhe.app.hotel.entity.Search;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:根据日期和id获取的属性
 */
public class SearchQueryDto {
    /**
     * 酒店id
     */
    private String id;
    /**
     * 酒店状态
     */
    private Integer status;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 进度
     */
    private Integer lng;

    /**
     * 维度
     */
    private Integer lat;
    /**
     * 第一张照片
     */
    private String img;
    /**
     * 评分
     */
    private Integer score;

    /**
     * 地址
     */
    private String address;
    /**
     * 房间名
     */
    private String skuName;
    /**
     * 房号
     */
    private String roomNo;
    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 门店照片
     */
    private String storePic;
    /**
     * 前台照片
     */
    private String receptionPic;


    public static SearchQueryDto make(Search search) {
        SearchQueryDto dto = new SearchQueryDto();
        dto.setPrice(search.getPrice());
        dto.setStatus(search.getStatus());
        dto.setId(search.getId());
        dto.setLng(search.getLng());
        dto.setLat(search.getLat());
        dto.setAddress(search.getAddress());
        dto.setRoomNo(search.getRoomNo());
        dto.setScore(search.getScore());
        dto.setSkuName(search.getSkuName());
        dto.setRoomType(search.getRoomType());
        if(null != search.getStorePic()){
            dto.setStorePic(search.getStorePic());
        }else if (null != search.getImgs()) {
            dto.setImg(StringUtil.stringToList(search.getImgs()).get(0));
        } else {
            if (null != search.getLaypicList()) {
                dto.setImg(StringUtil.stringToList(search.getLaypicList()).get(0));
            }
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SearchQueryDto{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
