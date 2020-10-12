package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.entity.*;
import com.jiuzhe.app.hotel.module.SearchQuery;
import com.jiuzhe.app.hotel.module.SkuDetailQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Description:前台第二次查询走数据库的dao
 */
@Mapper
@Repository
public interface SkuSearchQueryMapper {
    @Select("SELECT merchant_id FROM hotel_sku WHERE id=#{id}")
    String getMerchantById(String id);

    @Select("SELECT count(1) FROM hotel_sku WHERE id=#{skuId}")
    Integer getNumById(String skuId);

    @SelectProvider(type = SkuSearchQueryProvider.class, method = "getHotels")
    List<Search> getHotels(SearchQuery searchQuery);

    @SelectProvider(type = SkuSearchQueryProvider.class,method = "getRecommendHotel")
    List<HotelSku> getRecommendHotel(HotelSku hotelSku,Integer gist);
    /**
     * @Description:单独获取每日状态的方法
     */
    @Select("<script> " +
            "SELECT spec_date specDate,status FROM hotel_sku_daily_status " +
            "<![CDATA[ WHERE sku_id = #{skuId} AND spec_date >=#{begin} AND spec_date <#{end}]]>" +
            "</script>")
    List<SkuDailyStatus> getHotelstatus(@Param(value = "skuId") String skuId, @Param(value = "begin") LocalDate begin, @Param(value = "end") LocalDate end);

    /**
     * @Description:点击图标返回给前台的酒店详情(固定属性)
     */
    @Select("<script> " +
            "SELECT a.score,a.room_status, a.id,a.room_bond, s.storePic storePic,s.ReceptionPic receptionPic, s.name sku_name,a.address,a.lng,a.remark," +
            "a.lat,a.merchant_id,a.room_price,b.name room_type,a.wifi,a.bedroom_num,a.city_name,a.area," +
            "a.toilet_num,a.room_no,a.bed_num,a.imgurls urls," +
            "b.name layName,b.wifi layWifi,b.bedroom layBedroom,b.bed layBed,b.toilet layToilet," +
            "b.piclist  laypicList,b.count laycount " +
            "from hotel_sku a left join hotel_sku_layout b on a.layout_id = b.id left join store s on b.store_id = s.id WHERE a.id = #{id}" +
            "</script>")
    @Results(value = {
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "roomNo", column = "room_no"),
            @Result(property = "bedNum", column = "bed_num"),
            @Result(property = "roomType", column = "room_type"),
            @Result(property = "bedroomNum", column = "bedroom_num"),
            @Result(property = "toiletNum", column = "toilet_num"),
            @Result(property = "defStatus", column = "room_status"),
            @Result(property = "skuName", column = "sku_name"),
            @Result(property = "defPrice", column = "room_price"),
            @Result(property = "merchantId", column = "merchant_id"),
            @Result(property = "roomBond", column = "room_bond")
    })
    SkuDetail getBaseFacility(SkuDetailQuery skuDetailQuery);


    @Select("SELECT listing_date date,listing_price price from hotel_sku_listing_price where sku_id=#{skuId} " +
            "AND listing_date >= CURRENT_DATE() ORDER BY listing_date ASC")
    List<Map> getDailyPriceBySkuId(String skuId);

    /**
     *
     * @Description:点击图标返回给前台的酒店详情(放盘价格及日期)
     */
    @Select("<script> " +
            "SELECT listing_date,listing_price FROM hotel_sku_listing_price " +
            "<![CDATA[ WHERE sku_id = #{id} AND listing_date >=#{startDate} AND listing_date <#{endDate}]]>" +
            "</script>")
    @Results(value = {
            @Result(property = "listDate", column = "listing_date"),
            @Result(property = "listingPrice", column = "listing_price")
    })
    List<SkuDetailDate> getDatePrice(SkuDetailQuery skuDetailQuery);

    @Select("SELECT imgurls from hotel_sku")
    List<String> getAllimgsFromDb();

    @Select("SELECT phone from hotel_sku a left join store s on a.store_id = s.id where a.id = #{skuId}")
    String getPhoneBySkuId(String skuId);
}
