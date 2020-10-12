package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Description:房源的Dto
 */
public class HotelSkuDto {

    /**
     * 酒店id
     */
    private String skuId;
    /**
     * 酒店名称
     */
    private String skuName;
    /**
     * 商户id
     */
    private String merchantId;
    /**
     * 酒店地址
     */
    private String address;
    /**
     * 酒店位置经度
     */
    private Integer lng;
    /**
     * 酒店位置维度
     */
    private Integer lat;
    /**
     * 图片地址
     */
    private List<String> imgUrls;

    /**
     * 房间价格
     */
    private Integer price;

    /**
     * 押金
     */
    private Integer roomBond;
    /**
     * 房间状态
     */
    private Integer roomStatus;

    /**
     * 城市名
     */
    private String cityName;
    /**
     * 区域
     */
    private String area;
    /**
     * 房间类型（大床，豪华）
     */
    private String roomType;

    /**
     * 是否有无线
     */
    private Integer wifi;

    /**
     * 卧室数量
     */
    private Integer bedroomNum;

    /**
     * 卫生间数量
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

    public static HotelSkuDto make(HotelSku sku) {
        HotelSkuDto dto = new HotelSkuDto();
        dto.setSkuId(sku.getId());
        dto.setAddress(sku.getAddress());
        dto.setCityName(sku.getCityName());
        dto.setArea(sku.getArea());
        if (null != sku.getWifi()) {
            dto.setWifi(sku.getWifi());
        } else {
            dto.setWifi(sku.getLayWifi());
        }
        if (null != sku.getLng()) {
            dto.setLng(sku.getLng());
        } else {
            dto.setLng(sku.getStoreLng());
        }
        if (null != sku.getLat()) {
            dto.setLat(sku.getLat());
        } else {
            dto.setLat(sku.getStoreLat());
        }
        dto.setSkuName(sku.getSkuName());
        dto.setMerchantId(sku.getMerchantId());
        dto.setRoomStatus(sku.getRoomStatus());
        dto.setRoomBond(sku.getRoomBond());
        dto.setRoomType(sku.getRoomType());
        if (null != sku.getBedroomNum()) {
            dto.setBedroomNum(sku.getBedroomNum());
        } else {
            dto.setBedroomNum(sku.getLayBedroom());
        }
        if (null != sku.getToiletNum()) {
            dto.setToiletNum(sku.getToiletNum());
        } else {
            dto.setToiletNum(sku.getLayToilet());
        }
        if (null != sku.getBedNum()) {
            dto.setBedNum(sku.getBedNum());
        } else {
            dto.setBedNum(sku.getLayBed());
        }

        if (null != sku.getAddress()) {
            dto.setAddress(sku.getAddress());
        } else {
            dto.setAddress(sku.getStoreAddress());
        }
        dto.setRoomNo(sku.getRoomNo());
        //对获取图片地址进行处理String ---> list
        if (null != sku.getImgUrls()) {
            dto.setImgUrls(StringUtil.stringToList(sku.getImgUrls()));
        } else {
            dto.setImgUrls(StringUtil.stringToList(sku.getLaypicList()));
        }
        //对价格进行处理（放盘价格优先）
        if (null != sku.getListingPrice()) {
            dto.setPrice(sku.getListingPrice());
        } else {
            dto.setPrice(sku.getRoomPrice());
        }
        return dto;
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

    public Integer getRoomBond() {
        return roomBond;
    }

    public void setRoomBond(Integer roomBond) {
        this.roomBond = roomBond;
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

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }
}
