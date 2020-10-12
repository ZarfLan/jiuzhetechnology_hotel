package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.dto.PriceBondDto;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.SkuDailyPrice;
import com.jiuzhe.app.hotel.entity.SkuImg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:房东端管理
 */
@Mapper
@Repository
public interface SkuManageMapper {

    @SelectProvider(type = SkuManageProvider.class, method = "getSkuHotels")
    List<HotelSku> getSkuHotels(HotelSku hotelSku);

    @InsertProvider(type = SkuManageProvider.class, method = "saveSkuHotel")
    int saveSkuHotel(HotelSku hotelSku);

    @Insert("<script> " +
            "insert into hotel_sku (id,room_no,creater,create_time,editor,edit_time,merchant_id,room_price,room_status,room_bond,store_id,layout_id) values " +
            "<foreach collection='skuList' item='item'  separator=',' >" +
            "(#{item.id},#{item.roomNo},#{item.creater},#{item.createTime},#{item.editor},#{item.editTime},#{item.merchantId},#{item.roomPrice},#{item.roomStatus},#{item.roomBond},#{item.storeId},#{item.layoutId})" +
            "</foreach>" +
            "</script>")
    int saveMoreSku(@Param(value = "skuList") List<HotelSku> skuList);

    @Update("UPDATE hotel_sku_layout SET count = count+#{num} where id = #{layId}")
    int upCount(@Param(value = "num") int num,@Param(value = "layId") String layId);


    @Select("select count(1) from hotel_sku where id = #{skuId}")
    int checkSkuHotelNum(@Param(value = "skuId") String skuId);

    @Delete("delete from hotel_sku where id = #{skuId}")
    int deleteSku(String skuId);

    @Select("SELECT id skuId,room_bond roomBond,room_price roomPrice FROM hotel_sku WHERE id = #{skuId}")
    PriceBondDto getPriceAndBond(String skuId);


    @UpdateProvider(type = SkuManageProvider.class, method = "upSkuHotel")
    int upSkuHotel(HotelSku hotelSku);

    @Update("<script> " +
            "UPDATE hotel_sku set room_bond = #{roomBond}, room_status = #{roomStatus},room_price = #{roomPrice} " +
            "where id in " +
            "<foreach collection='skuIds' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int upAllSkuPrice(@Param(value = "roomBond") Integer roomBond, @Param(value = "roomPrice") Integer roomPrice, @Param(value = "roomStatus") Integer roomStatus, @Param(value = "skuIds") List<String> skuIds);

    @Delete("<script> " +
            "DELETE FROM hotel_sku_listing_price  where sku_id in " +
            "<foreach collection='skuIds' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            " and <![CDATA[ listing_date >= #{startDate} and listing_date <= #{endDate} ]]>" +
            "</script> ")
    int deleteListingPrice(@Param(value = "skuIds") List<String> skuIds, @Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    @Delete("<script> " +
            "DELETE FROM hotel_sku_listing_price  where sku_id  = #{skuId} " +
            " and <![CDATA[ listing_date >= #{startDate} and listing_date <= #{endDate} ]]>" +
            "</script> ")
    int deleteDailyPrice(@Param(value = "skuId") String skuId, @Param(value = "startDate") LocalDate startDate, @Param(value = "endDate") LocalDate endDate);

    @Delete("DELETE FROM hotel_sku_listing_price  where sku_id  = #{skuId}")
    int deleteListingPriceBySkuId( String skuId);

    @Insert("<script> " +
            "insert into hotel_sku_listing_price (id,sku_id,listing_date,listing_price) values " +
            "<foreach collection='dailyPrices' item='item'  separator=',' >" +
            "(#{item.id},#{item.skuId},#{item.listDate},#{item.listingPrice})" +
            "</foreach>" +
            "</script>")
    int saveListingPrice(@Param(value = "dailyPrices") List<SkuDailyPrice> dailyPrices);

    @Select("select room_status from hotel_sku where id = #{skuId}")
    Integer getRoomStatus(@Param(value = "skuId") String skuId);

    @Update("UPDATE hotel_sku set room_status = #{roomStatus} where id = #{skuId}")
    Integer upRoomStatus(@Param(value = "skuId") String skuId, @Param(value = "roomStatus") Integer roomStatus);
}
