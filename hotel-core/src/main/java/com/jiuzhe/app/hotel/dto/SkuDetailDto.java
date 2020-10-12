package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.SkuDetail;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.util.List;

/**
 * @Description:用于有日期的
 */
public class SkuDetailDto {
    /**
     * 酒店id
     */
    private String id;
    /**
     * 酒店名称
     */
    private String skuName;
    /**
     * 日期合集
     */
    private List<String> localDates;
    /**
     * 价格合集
     */
    private List<Integer> prices;
    /**
     * 图片路径
     */
    private List<String> urls;
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
     * 押金
     */
    private Integer roomBond;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 状态
     */
    private Integer status;
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
     * 描述
     */
    private String remark;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区域
     */
    private String area;

    private Integer score;

    /**
     * 门店照片
     */
    private String storePic;
    /**
     * 前台照片
     */
    private String receptionPic;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static SkuDetailDto make(SkuDetail skuDetail) {
        SkuDetailDto skuDetailDto = new SkuDetailDto();
        skuDetailDto.setScore(skuDetail.getScore());
        skuDetailDto.setRoomBond(skuDetail.getRoomBond());
        skuDetailDto.setId(skuDetail.getId());
        skuDetailDto.setSkuName(skuDetail.getSkuName());
        skuDetailDto.setAddress(skuDetail.getAddress());
        skuDetailDto.setRoomType(skuDetail.getRoomType());
        if (null != skuDetail.getWifi()) {
            skuDetailDto.setWifi(skuDetail.getWifi());
        } else {
            skuDetailDto.setWifi(skuDetail.getLayWifi());
        }
        if (null != skuDetail.getBedroomNum()) {
            skuDetailDto.setBedroomNum(skuDetail.getBedroomNum());
        } else {
            skuDetailDto.setBedroomNum(skuDetail.getLayBedroom());
        }
        if (null != skuDetail.getToiletNum()) {
            skuDetailDto.setToiletNum(skuDetail.getToiletNum());
        } else {
            skuDetailDto.setToiletNum(skuDetail.getLayToilet());
        }
        if (null != skuDetail.getBedNum()) {
            skuDetailDto.setBedNum(skuDetail.getBedNum());
        } else {
            skuDetailDto.setBedNum(skuDetail.getLayBed());
        }
        if(null != skuDetail.getStorePic()){
            skuDetailDto.setStorePic(skuDetail.getStorePic());
        }else if (null != skuDetail.getUrls()) {
            skuDetailDto.setUrls(StringUtil.stringToList(skuDetail.getUrls()));
        } else {
            skuDetailDto.setUrls(StringUtil.stringToList(skuDetail.getLaypicList()));
        }
        skuDetailDto.setRoomNo(skuDetail.getRoomNo());
        skuDetailDto.setRemark(skuDetail.getRemark());
        skuDetailDto.setLng(skuDetail.getLng());
        skuDetailDto.setLat(skuDetail.getLat());
        skuDetailDto.setMerchantId(skuDetail.getMerchantId());
        skuDetailDto.setCityName(skuDetail.getCityName());
        skuDetailDto.setArea(skuDetail.getArea());
        return skuDetailDto;
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

    public List<String> getLocalDates() {
        return localDates;
    }

    public void setLocalDates(List<String> localDates) {
        this.localDates = localDates;
    }

    public List<Integer> getPrices() {
        return prices;
    }

    public void setPrices(List<Integer> prices) {
        this.prices = prices;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
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

}
