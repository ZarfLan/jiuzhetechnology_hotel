package com.jiuzhe.app.hotel.entity;

import com.google.common.base.Splitter;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Search {
    /**
     * 酒店id
     */
    private String id;
    /**
     * 酒店状态
     */
    private Integer status;
    /**
     * 默认状态
     */
    private Integer defStatus;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 默认价格
     */
    private Integer defPrice;

    /**
     * 进度
     */
    private Integer lng;

    /**
     * 维度
     */
    private Integer lat;
    /**
     * 照片合集String
     */
    private String imgs;

    /**
     * 门店照片
     */
    private String storePic;
    /**
     * 前台照片
     */
    private String receptionPic;

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

    private String storeId;

    private String layoutId;

    private String layName;

    private Integer layWifi;

    private Integer layBedroom;

    private Integer layBed;

    private Integer layToilet;

    private String  laypicList;

    private Integer laycount;

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

    public String getLayName() {
        return layName;
    }

    public void setLayName(String layName) {
        this.layName = layName;
    }

    public Integer getLayWifi() {
        return layWifi;
    }

    public void setLayWifi(Integer layWifi) {
        this.layWifi = layWifi;
    }

    public Integer getLayBedroom() {
        return layBedroom;
    }

    public void setLayBedroom(Integer layBedroom) {
        this.layBedroom = layBedroom;
    }

    public Integer getLayBed() {
        return layBed;
    }

    public void setLayBed(Integer layBed) {
        this.layBed = layBed;
    }

    public Integer getLayToilet() {
        return layToilet;
    }

    public void setLayToilet(Integer layToilet) {
        this.layToilet = layToilet;
    }

    public String getLaypicList() {
        return laypicList;
    }

    public void setLaypicList(String laypicList) {
        this.laypicList = laypicList;
    }

    public Integer getLaycount() {
        return laycount;
    }

    public void setLaycount(Integer laycount) {
        this.laycount = laycount;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public Integer getDefStatus() {
        return defStatus;
    }

    public void setDefStatus(Integer defStatus) {
        this.defStatus = defStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDefPrice() {
        return defPrice;
    }

    public void setDefPrice(Integer defPrice) {
        this.defPrice = defPrice;
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

    @Override
    public String toString() {
        return "Search{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", defStatus=" + defStatus +
                ", price=" + price +
                ", defPrice=" + defPrice +
                ", lng=" + lng +
                ", lat=" + lat +
                ", imgs='" + imgs + '\'' +
                ", score=" + score +
                ", address='" + address + '\'' +
                ", skuName='" + skuName + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", roomType=" + roomType +
                ", storeId='" + storeId + '\'' +
                ", layoutId='" + layoutId + '\'' +
                ", layName='" + layName + '\'' +
                ", layWifi=" + layWifi +
                ", layBedroom=" + layBedroom +
                ", layBed=" + layBed +
                ", layToilet=" + layToilet +
                ", laypicList='" + laypicList + '\'' +
                ", laycount=" + laycount +
                '}';
    }
}
