package com.jiuzhe.app.hotel.entity;

import com.jiuzhe.app.hotel.constants.RoomStatusEnum;
import com.jiuzhe.app.hotel.module.MannageSearchSkuQuery;
import com.jiuzhe.app.hotel.module.SkuQuery;
import com.jiuzhe.app.hotel.utils.MD5Util;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:酒店属性表（不变的属性）
 */
public class HotelSku extends BaseEntity {
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
    private String imgUrls;

    /**
     * 房间价格（默认价格）
     */
    private Integer roomPrice;

    /**
     * 放盘价格
     */
    private Integer listingPrice;

    /**
     * 今天的日期
     */
    private LocalDate todayDate = LocalDate.now();

    /**
     * 房间状态
     */
    private Integer roomStatus;
    /**
     * 押金
     */
    private Integer roomBond;

    /**
     * 门店照片
     */
    private String storePic;
    /**
     * 前台照片
     */
    private String receptionPic;

    private Integer beginLng;

    private Integer endLng;

    private Integer beginLat;

    private Integer endLat;

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

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 区域
     */
    private String area;

    private Integer weight;

    private Integer score;

    private String storeId;

    private String layoutId;

    private String layName;

    private Integer layWifi;

    private Integer layBedroom;

    private Integer layBed;

    private Integer layToilet;

    private String laypicList;

    private Integer laycount;

    private Integer storeLng;

    private Integer storeLat;

    private String storeName;

    private String storeAddress;

    public static List<HotelSku> make(SkuSaveQuery query) {
        List<HotelSku> list = new ArrayList<>();
        //进行md5拼接
        String mdstr = query.getMerchantId() + query.getStoreId() + query.getLayoutId();
        if (null != query.getRoomNos()) {
            for (String str : query.getRoomNos()) {
                HotelSku hotelSku = new HotelSku();
                String md = mdstr + str;
                hotelSku.setId(MD5Util.MD5(md));
                hotelSku.setRoomNo(str);
                //当前时间（修改）
                hotelSku.setEditor(query.getMerchantId());
                hotelSku.setCreater(query.getMerchantId());
                hotelSku.setMerchantId(query.getMerchantId());
                hotelSku.setRoomPrice(query.getRoomPrice());
                hotelSku.setRoomBond(query.getRoomBond());
                hotelSku.setStoreId(query.getStoreId());
                hotelSku.setLayoutId(query.getLayoutId());
                hotelSku.setRoomStatus(RoomStatusEnum.ON_SALE.getIndex());
                list.add(hotelSku);
            }
        }
        return list;
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

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(Integer storeLng) {
        this.storeLng = storeLng;
    }

    public Integer getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(Integer storeLat) {
        this.storeLat = storeLat;
    }

    public static HotelSku make(SkuQuery query) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setId(query.getSkuId());
        hotelSku.setCreater(query.getHrId());
        hotelSku.setEditor(query.getHrId());
        //当前时间（修改）
        hotelSku.setEditTime(LocalDateTime.now());
        hotelSku.setId(query.getSkuId());
        hotelSku.setSkuName(query.getSkuName());
        hotelSku.setMerchantId(query.getMerchantId());
        hotelSku.setAddress(query.getAddress());
        hotelSku.setLng(query.getLng());
        hotelSku.setLat(query.getLat());
        hotelSku.setRoomPrice(query.getRoomPrice());
        hotelSku.setRoomBond(query.getRoomBond());
        hotelSku.setRoomStatus(query.getRoomStatus());
        hotelSku.setRoomType(query.getRoomType());
        hotelSku.setWifi(query.getWifi());
        hotelSku.setBedroomNum(query.getBedroomNum());
        hotelSku.setToiletNum(query.getToiletNum());
        hotelSku.setRoomNo(query.getRoomNo());
        hotelSku.setBedNum(query.getBedNum());
        hotelSku.setRemark(query.getRemark());
        hotelSku.setCityName(query.getCityName());
        hotelSku.setArea(query.getArea());
        hotelSku.setWeight(query.getWeight());
        hotelSku.setImgUrls(StringUtil.strArray2str(query.getImgs()));
        hotelSku.setStoreId(query.getStoreId());
        hotelSku.setLayoutId(query.getLayoutId());
        return hotelSku;
    }

    //查询条件
    public static HotelSku make(MannageSearchSkuQuery query) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setId(null);
        hotelSku.setId(query.getSkuId());
        hotelSku.setSkuName(query.getSkuName());
        hotelSku.setMerchantId(query.getMerchantId());
        hotelSku.setAddress(query.getAddress());
        hotelSku.setRoomType(query.getRoomType());
        hotelSku.setRoomNo(query.getRoomNo());
        hotelSku.setCityName(query.getCityName());
        hotelSku.setArea(query.getArea());
        hotelSku.setStoreId(query.getStoreId());
        hotelSku.setLayoutId(query.getLayoutId());
        hotelSku.setBeginLng(query.getBeginLng());
        hotelSku.setEndLng(query.getEndLng());
        hotelSku.setBeginLat(query.getBeginLat());
        hotelSku.setEndLat(query.getEndLat());
        return hotelSku;
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

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public Integer getBeginLng() {
        return beginLng;
    }

    public void setBeginLng(Integer beginLng) {
        this.beginLng = beginLng;
    }

    public Integer getEndLng() {
        return endLng;
    }

    public void setEndLng(Integer endLng) {
        this.endLng = endLng;
    }

    public Integer getBeginLat() {
        return beginLat;
    }

    public void setBeginLat(Integer beginLat) {
        this.beginLat = beginLat;
    }

    public Integer getEndLat() {
        return endLat;
    }

    public void setEndLat(Integer endLat) {
        this.endLat = endLat;
    }

    public Integer getRoomBond() {
        return roomBond;
    }

    public void setRoomBond(Integer roomBond) {
        this.roomBond = roomBond;
    }


    public Integer getListingPrice() {
        return listingPrice;
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


    public void setListingPrice(Integer listingPrice) {
        this.listingPrice = listingPrice;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }
}
