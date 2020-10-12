package com.jiuzhe.app.hotel.dao.mapper;

import com.jiuzhe.app.hotel.entity.HotelOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:定时器任务
 */
@Mapper
@Repository
public interface SchedulerMapper {

    /**
     * @Description:获取需要发送退房提醒的用户订单
     */
    @Select("SELECT id," +
            "remark,user_id,sku_id,sku_price,sku_bond,start_date,end_date,merchant_id," +
            "occupant_name,occupant_id_card,occupant_phone,cast(order_status as unsigned),paied_expired_time " +
            "from hotel_order where end_date = #{endDate} and order_status = #{orderStatus}")
    @Results({
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
            @Result(property = "paiedExpired", column = "paied_expired_time")
    })
    List<HotelOrder> getMsgUserGo(@Param(value = "endDate") LocalDate endDate, @Param(value = "orderStatus") Integer orderStatus);

    /**
     * @Description:每天12自动将当天已付款订单修改为已入住(3>>5, )
     */
    @Update("UPDATE hotel_order SET order_status = #{lived} WHERE start_date = CURRENT_DATE() AND order_status = #{paied} ")
    void changPaidToLived(@Param(value = "paied") Integer paied, @Param(value = "lived") Integer lived);

    /**
     * @Description:每天12自动将当天已入住订单修改为申请退押金(5>>6, )
     * @author: 郑鹏宇
     * @date 2018/7/2/002
     */
    @Update("UPDATE hotel_order SET order_status = #{apply} WHERE end_date = CURRENT_DATE() AND order_status = #{lived}")
    void changLivedToApply(@Param(value = "lived") Integer lived, @Param(value = "apply") Integer apply);

    @Update("UPDATE hotel_order SET order_status = #{apply} WHERE end_date = CURRENT_DATE() AND order_status = #{lived} AND user_id = #{userId}")
    void changLivedToApplyByUserId(@Param(value = "lived") Integer lived, @Param(value = "apply") Integer apply,@Param(value = "userId") String userId);
}