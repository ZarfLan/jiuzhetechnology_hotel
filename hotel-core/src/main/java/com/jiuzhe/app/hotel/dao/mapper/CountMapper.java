package com.jiuzhe.app.hotel.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description:统计
 */
@Mapper
@Repository
public interface CountMapper {
    @Select("SELECT COUNT(a.id) orderNum,SUM(a.sku_price) income FROM hotel_order a\n" +
            "LEFT JOIN hotel_sku b ON a.sku_id = b.id\n" +
            "WHERE a.merchant_id= #{merchantId} AND a.order_status IN (3,5)\n" +
            "AND DATE_FORMAT(a.create_time,'%Y-%m-%d')=CURRENT_DATE()\n" +
            "AND b.store_id=#{storeId}")
    Map<String, String> getOrderNumIncome(@Param(value = "merchantId") String merchantId, @Param(value = "storeId") String storeId);

    @Select("SELECT count(1) dirtyNum FROM hotel_sku WHERE merchant_id = #{merchantId} AND store_id = #{storeId} AND room_status = 3")
    Map<String, String> getDirtyNum(@Param(value = "merchantId") String merchantId, @Param(value = "storeId") String storeId);

    @Select("SELECT SUM(a.sku_price ) income,\n" +
            "COUNT(a.id) orderNum,\n" +
            "COUNT(CASE WHEN a.order_status = 6 THEN 1 ELSE NULL END ) applyBondNum\n" +
            "FROM hotel_order a\n" +
            "LEFT JOIN hotel_sku b ON a.sku_id = b.id\n" +
            "WHERE a.order_status NOT in(1,2,4)\n" +
            "AND date_format(a.create_time,'%Y-%m-%d')= date_format(now(),'%Y-%m-%d')\n" +
            "AND b.store_id = #{storeId}\n")
    Map getManageCountInfo(String storeId);

    @Select("SELECT COUNT(1) dirtyRoomNum FROM `hotel_sku` WHERE `store_id` =#{storeId} AND  `room_status` =3")
    Map getDirtyRoom(String storeId);
}
