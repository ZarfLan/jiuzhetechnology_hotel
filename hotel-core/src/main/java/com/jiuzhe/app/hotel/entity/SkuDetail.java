package com.jiuzhe.app.hotel.entity;

import java.util.List;
import java.util.Map;

/**
 * @Description:点击图标返回给前台的酒店详情
 */
public class SkuDetail {
    /**
     * 酒店id
     */
    private String id;
    /**
     * 酒店名称
     */
    private String skuName;
    /**
     * 日期价格对应表
     */
    private Map<String, Integer> datePrices;
    /**
     * 照片集合
     */
    private String urls;
    /**
     * 经度
     */
    private Integer lng;
    /**
     * 维度
     */
    private Integer lat;
    /**
     * 地址
     */
    private String address;
    /**
     * 默认价格(单价)
     */
    private Integer defPrice;

    /**
     * 默认状态
     */
    private Integer defStatus;
    /**
     * 押金
     */
    private Integer roomBond;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 无线
     */
    private Integer wifi;

    /**
     * 卧室数
     */
    private Integer bedroomNum;

    /**
     * 洗手间数
     */
    private Integer toiletNum;


    /**
     * 房号
     */
    private String roomNo;

    /**
     * 床位数
     */
    private Integer bedNum;

    /**
     * 详情
     */
    private String remark;

    /**
     * 价格日期（段）
     */
    private List DatePriceList;

    /**
     * 照片合集
     */
    private List<String> imgs;

    /**
     * 城市名
     */
    private String cityName;
    /**
     * 区
     */
    private String area;

    private Integer score;

    private String storeId;

    private String layoutId;

    private String layName;

    private Integer layWifi;

    private Integer layBedroom;

    private Integer layBed;

    private Integer layToilet;

    private String  laypicList;

    private Integer laycount;

    /**
     * 门店照片
     */
    private String storePic;

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

    /**
     * 前台照片
     */

    private String receptionPic;


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

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public List getDatePriceList() {
        return DatePriceList;
    }

    public void setDatePriceList(List datePriceList) {
        DatePriceList = datePriceList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDefStatus() {
        return defStatus;
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

    public void setDefStatus(Integer defStatus) {
        this.defStatus = defStatus;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getRoomBond() {
        return roomBond;
    }

    public void setRoomBond(Integer roomBond) {
        this.roomBond = roomBond;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Map<String, Integer> getDatePrices() {
        return datePrices;
    }

    public void setDatePrices(Map<String, Integer> datePrices) {
        this.datePrices = datePrices;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getDefPrice() {
        return defPrice;
    }

    public void setDefPrice(Integer defPrice) {
        this.defPrice = defPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
