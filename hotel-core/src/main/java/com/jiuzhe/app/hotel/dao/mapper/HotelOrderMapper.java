package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.dto.OrderDto;
import com.jiuzhe.app.hotel.entity.*;
import com.jiuzhe.app.hotel.module.EvaluateQuery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description:订单DAO
 */
@Mapper
@Repository
public interface HotelOrderMapper {
    @Insert("INSERT INTO hotel_order " +
            "(id,version,creater,create_time,editor,edit_time,remark,user_id,sku_id,sku_price," +
            "sku_bond,start_date,end_date,merchant_id,occupant_name,occupant_id_card,occupant_phone,order_status,paied_expired_time,day_num,platform_fee,vip_level,on_line) " +
            "VALUES " +
            "(#{id},#{version},#{creater},#{createTime},#{editor},#{editTime},#{remark}," +
            "#{userId},#{skuId},#{skuPrice},#{skuBond},#{startDate},#{endDate},#{merchantId},#{occupantName}," +
            "#{occupantCard},#{occupantPhone},#{status},#{paiedExpired},#{dayNum},#{platformFee},#{vipLevel},#{onLine})")
    void createHotelOrder(HotelOrder order);

    @Select("SELECT discount FROM level_discount WHERE id = #{vipLevel}")
    Integer getDiscount(Integer vipLevel);

    @Select("SELECT COUNT(id) FROM hotel_order where user_id = #{userId} AND order_status = #{status}")
    int getOrderNumByStatus(@Param(value = "userId") String userId, @Param(value = "status") Integer status);

    @Select("SELECT b.score,a.platform_fee,a.id,CONCAT(s.name , b.room_no) storeName,s.address address,b.address,b.room_type roomType,b.room_status roomStatus," +
            "a.remark,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id,a.on_line," +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) as order_status,a.paied_expired_time " +
            "from hotel_order a left join hotel_sku b on a.sku_id = b.id left join store s on s.id = b.store_id " +
            " where a.id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
            @Result(property = "skuPrice", column = "sku_price"),
            @Result(property = "skuBond", column = "sku_bond"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "merchantId", column = "merchant_id"),
            @Result(property = "occupantName", column = "occupant_name"),
            @Result(property = "occupantCard", column = "occupant_id_card"),
            @Result(property = "occupantPhone", column = "occupant_phone"),
            @Result(property = "status", column = "order_status"),
            @Result(property = "platformFee", column = "platform_fee"),
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "onLine", column = "on_line")
    })
    HotelOrder getOrderById(String id);

    @Select("SELECT imgurls from hotel_sku where id = #{skuId}")
    Map<String, String> getImgsBySkuId(String skuId);

    /**
     * @Description:获取订单列表
     * @author:郑鹏宇
     * @date:2018/4/26
     */
    @Select("<script> " +
            "SELECT b.score,a.id,a.day_num,a.create_time,a.remark, CONCAT(s.name , b.room_no) storeName,s.address address,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id,b.room_no roomNo, " +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,b.address,a.platform_fee platformFee,a.on_line " +
            "from hotel_order a left join hotel_sku b on a.sku_id = b.id left join store s on s.id = b.store_id " +
            "where a.user_id = #{userId}  ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
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
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "onLine", column = "on_line"),
            @Result(property = "roomNo", column = "roomNo")
    })
    List<HotelOrder> getOrdersByUserId(@Param(value = "userId") String userId);
    /**
     * @Description:获取订单列表通过电话号吗
     * @author:郑鹏宇
     * @date:2018/4/26
     */
    @Select("<script> " +
            "SELECT b.score,a.id,a.day_num,a.create_time,a.remark, CONCAT(s.name , b.room_no) storeName,s.address address,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id,b.room_no roomNo, " +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,b.address,a.platform_fee platformFee,a.on_line " +
            "from hotel_order a left join hotel_sku b on a.sku_id = b.id left join store s on s.id = b.store_id " +
            "where a.occupant_phone = #{phone}  ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
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
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "roomNo", column = "roomNo"),
            @Result(property = "onLine", column = "on_line")
    })
    List<HotelOrder> getOrdersByUserPhone(@Param(value = "phone") String phone);
    /**
     * @Description:根据merchantid获取订单列表
     * @author:郑鹏宇
     * @date:2018/4/26
     */
    @Select("<script> " +
            "SELECT a.id,a.day_num,a.create_time,a.remark,a.user_id, CONCAT(s.name , b.room_no) storeName,s.address address,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id," +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,b.room_no roomNo,b.imgurls,a.on_line, " +
            "from hotel_order a left join hotel_sku b on a.sku_id = b.id left join store s on s.id = b.store_id " +
            "where a.order_status in (1,3,5,6,7,8) and a.merchant_id = #{merchantId}  ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
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
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "roomNo", column = "roomNo"),
            @Result(property = "onLine", column = "on_line")
    })
    List<HotelOrder> getOrderbyMerchantId(@Param(value = "merchantId") String merchantId);

    @Select("<script> " +
            "SELECT a.id,a.day_num,a.create_time,a.remark,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id," +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,c.address address,b.room_no roomNo,b.imgurls,a.on_line, " +
            "CONCAT(c.name , b.room_no) storeName,d.piclist piclist " +
            "from hotel_order a LEFT JOIN hotel_sku b on a.sku_id = b.id " +
            "left join store c on b.store_id = c.id " +
            "left join hotel_sku_layout d on b.layout_id = d.id " +
            "where a.order_status =6 and a.merchant_id = #{merchantId}  ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
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
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "roomNo", column = "roomNo"),
            @Result(property = "onLine", column = "on_line")
    })
    List<HotelOrder> getBondOrderbyMerchantId(@Param(value = "merchantId") String merchantId);


    /**
     * @Description:获取商户所有的押金退还待审核订单
     * @author: 郑鹏宇
     * @date 2018/7/3/003
     */
    @Select("<script> " +
            "SELECT a.id,a.day_num,a.create_time,a.remark,a.user_id,a.sku_id,a.sku_price,a.sku_bond,a.start_date,a.end_date,a.merchant_id, " +
            "a.occupant_name,a.occupant_id_card,a.occupant_phone,cast(a.order_status as unsigned) order_status,a.paied_expired_time,b.sku_name,c.address address,b.room_no roomNo,b.imgurls,a.on_line,  " +
            "CONCAT(s.name , b.room_no) storeName,d.piclist piclist " +
            "from hotel_order a LEFT JOIN hotel_sku b on a.sku_id = b.id " +
            "left join store c on b.store_id = c.id " +
            "left join hotel_sku_layout d on b.layout_id = d.id " +
            "where a.order_status = 6 and a.merchant_id = #{merchantId}  ORDER BY a.create_time DESC" +
            "</script>")
    @Results({
            @Result(property = "dayNum", column = "day_num"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "skuId", column = "sku_id"),
            @Result(property = "storeName", column = "storeName"),
            @Result(property = "address", column = "address"),
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
            @Result(property = "paiedExpired", column = "paied_expired_time"),
            @Result(property = "roomNo", column = "roomNo"),
            @Result(property = "onLine", column = "on_line")
    })
    List<HotelOrder> getApplyOrderbyMerchantId(@Param(value = "merchantId") String merchantId);

    /**
     * @Description:修改订单状态
     * @author:郑鹏宇
     * @date:2018/4/16
     */
    @Update("update hotel_order set order_status = #{status},paid_cancel_time = #{paidCancelTime} where id =#{id}")
    int upOrderStatusById(@Param(value = "id") String id, @Param(value = "status") Integer status, @Param(value = "paidCancelTime") LocalDateTime paidCancelTime);

    /**
     * @Description:查询房间状态
     * @author:郑鹏宇
     * @date:2018/4/16
     */
    @Select("SELECT room_status FROM hotel_sku WHERE id = #{skuId} FOR UPDATE")
    Integer getRoomStatusForUp(String skuId);

    /**
     * @Description:修改房间状态
     * @author:郑鹏宇
     * @date:2018/4/16
     */
    @Update("update hotel_sku set room_status = #{roomStatus} where id = #{skuId}")
    int upRoomStatus(@Param(value = "skuId") String skuId, @Param(value = "roomStatus") Integer roomStatus);

    /**
     * @Description:新建一条每日状态
     * @author:郑鹏宇
     * @date:2018/4/25
     */
    @Insert("<script>" +
            "INSERT INTO hotel_sku_daily_status(sku_id,spec_date,status,expired,lockedby) VALUES " +
            "<foreach collection='list' item='item'  separator=','>" +
            "(#{item.skuId},#{item.specDate},#{item.status},#{item.expired},#{item.lockedby})" +
            "</foreach>" +
            "</script>")
    int saveDailyStatus(List<SkuDailyStatus> list);

    /**
     * @Description:获取过期的订单（需要id,skuId,startDate,endDate）
     * @author:郑鹏宇
     * @date:2018/4/25
     */
    @Select("select id,sku_id skuId,start_date startDate,end_date endDate from hotel_order " +
            " where paied_expired_time < #{time} and order_status=#{orderStatus}")
    List<OrderDto> getExpriedOrder(@Param(value = "time") LocalDateTime time, @Param(value = "orderStatus") Integer orderStatus);

    /**
     * @Description:释放房间
     * @author:郑鹏宇
     * @date:2018/4/25
     */
    @Update("<script> " +
            "update hotel_sku set room_status = #{status} where id in " +
            "<foreach collection='ids' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    int upSkuStatusByScheduler(@Param(value = "status") Integer status, @Param(value = "ids") List<String> ids);

    /**
     * @Description:倒计时
     * @author:郑鹏宇
     * @date:2018/4/26
     */
    @Select("SELECT paied_expired_time from hotel_order where id = #{id}")
    LocalDateTime getCountDown(String id);

    /**
     * @Description:每天十二点刷新订单，已付款变成已经入住
     * @author:郑鹏宇
     * @date:2018/4/26
     */
    @Update("update hotel_order set order_status = #{livedStatus} where order_status = #{paidStatus} " +
            "and start_date = #{startDate}")
    int upOrderStatusLived(@Param(value = "startDate") LocalDate startDate, @Param(value = "paidStatus") Integer paidStatus, @Param(value = "livedStatus") Integer livedStatus);

    /**
     * @Description:判定订单是否已经付款
     * @author:郑鹏宇
     * @date:2018/5/4
     */
    @Select("SELECT id,sku_price skuPrice,merchant_id merchantId, sku_bond skuBond,user_id userId,paid_cancel_time paidCancelTime from hotel_order WHERE id = #{id} AND order_status = #{orderStastus}")
    HotelOrder haveOrserPaid(@Param(value = "id") String id, @Param(value = "orderStastus") Integer orderStastus);

    /**
     * @Description:根据酒店id获取经纬度
     * @author:郑鹏宇
     * @date:2018/5/15
     */
    @Select("select a.id id, s.lng lng,s.lat lat from hotel_sku a left join store s on a.store_id = s.id where a.id = #{skuId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "lng", column = "lng"),
            @Result(property = "lat", column = "lat")
    })
    HotelSku getLngLatBySkuId(@Param(value = "skuId") String skuId);

    /**
     * @Description:用户评分
     * @author: 郑鹏宇
     * @date 2018/6/26/026
     */
    @Update("update hotel_order set score = #{score} where id = #{orderId}")
    int saveOrderScore(@Param(value = "orderId") String orderId, @Param(value = "score") Integer score);

    @Update("update hotel_sku set score = #{score} where id = #{skuId}")
    int saveSkuScore(@Param(value = "skuId") String skuId, @Param(value = "score") Integer score);

    @Update("update hotel_sku set clean_score = #{score} where id = #{skuId}")
    int saveSkuCleanScore(@Param(value = "skuId") String skuId, @Param(value = "score") Integer score);

    @Update("update hotel_order set sku_score=#{skuScore},clean_score=#{cleanScore},sku_problem = #{skuProblem}," +
            "clean_problem=#{cleanProblem} where id = #{orderId}")
    int evaluate(EvaluateQuery query);

    @Select("SELECT score skuScore, clean_score cleanScore  FROM hotel_sku WHERE id = #{skuId}")
    Evaluate getSkuScore(String skuId);

    @UpdateProvider(type = HotelOrderProvider.class, method = "changePaidTolived")
    void changePaidTolived(PaidToLived paidToLived);
}


