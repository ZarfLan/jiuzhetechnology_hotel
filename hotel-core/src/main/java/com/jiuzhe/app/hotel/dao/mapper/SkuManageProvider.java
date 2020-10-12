package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.SkuImg;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Description:房东端的SQL

 */
public class SkuManageProvider {

    /**
     * @Description:根据条件接收房源信息
     */
    public String getSkuHotels(HotelSku hotelSku) {
        return new SQL() {{
            SELECT("a.id,a.room_bond roomBond, a.sku_name skuName,a.merchant_id merchantId,a.address,a.lng,a.lat,a.city_name cityName,a.area, " +
                    "a.room_price roomPrice,a.room_status roomStatus,a.imgurls," +
                    "a.room_type roomType,a.wifi,a.bedroom_num bedroomNum,a.toilet_num toiletNum,a.room_no roomNo,a.bed_num bedNum," +
                    "b.id layoutId,b.store_id storeId,b.name layName ,b.wifi layWifi,b.bedroom layBedroom,b.bed layBed,b.toilet layToilet,b.piclist laypicList,b.count laycount," +
                    "c.name storeName,c.lng storeLng,c.lat storeLat,c.address storeAddress");
            FROM("hotel_sku a ");
            LEFT_OUTER_JOIN("hotel_sku_layout b on (a.layout_id = b.id)");
            LEFT_OUTER_JOIN("store c on a.store_id = c.id");
            if (!StringUtil.isEmptyOrNull(hotelSku.getMerchantId())) {
                WHERE("a.merchant_id = #{merchantId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getId())) {
                WHERE("a.id = #{id}");
            }
            if (null != hotelSku.getBeginLng()) {
                WHERE("c.lng > #{beginLng}");
            }
            if (null != hotelSku.getEndLng()) {
                WHERE("c.lng < #{endLng}");
            }
            if (null != hotelSku.getBeginLat()) {
                WHERE("c.lat > #{beginLat}");
            }
            if (null != hotelSku.getEndLat()) {
                WHERE("c.lat < #{endLat}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getCityName())) {
                WHERE("a.city_name = #{cityName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getArea())) {
                WHERE("a.area = #{area}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getSkuName())) {
                WHERE("a.sku_name = #{skuName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getMerchantId())) {
                WHERE("a.merchant_id = #{merchantId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getLayoutId())) {
                WHERE("a.layout_id = #{layoutId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getStoreId())) {
                WHERE("a.store_id = #{storeId}");
            }
        }}.toString();
    }


    /**
     * @Description:创建新的房源信息
     */
    public String saveSkuHotel(HotelSku hotelSku) {
        return new SQL() {{
            INSERT_INTO("hotel_sku");
            if (!StringUtil.isEmptyOrNull(hotelSku.getId())) {
                VALUES("id", "#{id}");
            }
            if (null != hotelSku.getVersion()) {
                VALUES("version", "#{version}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getCreater())) {
                VALUES("creater", "#{creater}");
            }
            if (null != hotelSku.getCreateTime()) {
                VALUES("create_time", "#{createTime}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getEditor())) {
                VALUES("editor", "#{editor}");
            }
            if (null != hotelSku.getEditTime()) {
                VALUES("edit_time", "#{editTime}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getRemark())) {
                VALUES("remark", "#{remark}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getSkuName())) {
                VALUES("sku_name", "#{skuName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getMerchantId())) {
                VALUES("merchant_id", "#{merchantId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getAddress())) {
                VALUES("address", "#{address}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getCityName())) {
                VALUES("city_name", "#{cityName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getArea())) {
                VALUES("area", "#{area}");
            }
            if (null != hotelSku.getLng()) {
                VALUES("lng", "#{lng}");
            }
            if (null != hotelSku.getLat()) {
                VALUES("lat", "#{lat}");
            }
            if (null != hotelSku.getRoomPrice()) {
                VALUES("room_price", "#{roomPrice}");
            }
            if (null != hotelSku.getRoomStatus()) {
                VALUES("room_status", "#{roomStatus}");
            }
            if (null != hotelSku.getRoomBond()) {
                VALUES("room_bond", "#{roomBond}");
            }
            if (null != hotelSku.getRoomType()) {
                VALUES("room_type", "#{roomType}");
            }
            if (null != hotelSku.getWifi()) {
                VALUES("wifi", "#{wifi}");
            }
            if (null != hotelSku.getBedroomNum()) {
                VALUES("bedroom_num", "#{bedroomNum}");
            }
            if (null != hotelSku.getToiletNum()) {
                VALUES("toilet_num", "#{toiletNum}");
            }
            if (null != hotelSku.getBedNum()) {
                VALUES("bed_num", "#{bedNum}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getRoomNo())) {
                VALUES("room_no", "#{roomNo}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getStoreId())) {
                VALUES("store_id", "#{storeId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getLayoutId())) {
                VALUES("layout_id", "#{layoutId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getImgUrls())) {
                VALUES("imgurls", "#{imgUrls}");
            }
        }}.toString();
    }


    /**
     * @Description:修改房源
     */
    public String upSkuHotel(HotelSku hotelSku) {
        return new SQL() {{
            UPDATE("hotel_sku");
            if (!StringUtil.isEmptyOrNull(hotelSku.getEditor())) {
                SET("editor = #{editor}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getCreater())) {
                SET("creater = #{creater}");
            }
            if (null != hotelSku.getEditTime()) {
                SET("edit_time = #{editTime}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getRemark())) {
                SET("remark = #{remark}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getSkuName())) {
                SET("sku_name = #{skuName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getMerchantId())) {
                SET("merchant_id = #{merchantId}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getAddress())) {
                SET("address = #{address}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getCityName())) {
                SET("city_name = #{cityName}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getArea())) {
                SET("area = #{area}");
            }
            if (null != hotelSku.getLng()) {
                SET("lng = #{lng}");
            }
            if (null != hotelSku.getLat()) {
                SET("lat = #{lat}");
            }
            if (null != hotelSku.getRoomPrice()) {
                SET("room_price = #{roomPrice}");
            }
            if (null != hotelSku.getRoomBond()) {
                SET("room_bond = #{roomBond}");
            }
            if (null != hotelSku.getRoomStatus()) {
                SET("room_status = #{roomStatus}");
            }
            if (null != hotelSku.getRoomType()) {
                SET("room_type = #{roomType}");
            }
            if (null != hotelSku.getWifi()) {
                SET("wifi = #{wifi}");
            }
            if (null != hotelSku.getBedroomNum()) {
                SET("bedroom_num = #{bedroomNum}");
            }
            if (null != hotelSku.getToiletNum()) {
                SET("toilet_num = #{toiletNum}");
            }
            if (null != hotelSku.getBedNum()) {
                SET("bed_num = #{bedNum}");
            }
            if (!StringUtil.isEmptyOrNull(hotelSku.getRoomNo())) {
                SET("room_no = #{roomNo}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

}
