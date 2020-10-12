package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.entity.HotelSku;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:房态
 */
@Mapper
@Repository
public interface HotelStateMapper {

    @Select("SELECT id,sku_name skuName FROM hotel_sku WHERE merchant_id = #{merchantId}")
    List<HotelSku> getSkuByMerchantId(@Param(value = "merchantId") String merchantId);

    @Select("SELECT id,sku_name skuName,room_no roomNo FROM hotel_sku WHERE store_id = #{storeId}")
    List<HotelSku> getSkuByStoreId(@Param(value = "storeId") String storeId);

    @Select("<script> " +
            "SELECT b.room_no,a.id,a.day_num,a.create_time,a.remark,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id," +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,b.address,b.room_no " +
            "from hotel_order a LEFT JOIN hotel_sku b on a.sku_id = b.id " +
            "where b.store_id = #{storeId} and a.order_status in (3,5,6,7,8)" +
            "and <![CDATA[ a.start_date >= #{beginDate} and a.start_date <= #{endDate} ]]>" +
            "ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "roomNo", column = "room_no"),
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "skuPrice", column = "sku_price"),
            @Result(property = "skuBond", column = "sku_bond"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "merchantId", column = "merchant_id"),
            @Result(property = "occupantName", column = "occupant_name"),
            @Result(property = "occupantCard", column = "occupant_id_card"),
            @Result(property = "occupantPhone", column = "occupant_phone"),
            @Result(property = "status", column = "order_status"),
            @Result(property = "skuName", column = "sku_name"),
            @Result(property = "paiedExpired", column = "paied_expired_time")
    })
    List<HotelOrder> getOrders(@Param(value = "storeId") String storeId,
                               @Param(value = "beginDate") LocalDate beginDate,
                               @Param(value = "endDate") LocalDate endDate);

}
