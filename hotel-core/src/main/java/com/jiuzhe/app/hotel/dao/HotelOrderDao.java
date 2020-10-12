package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.HotelOrderMapper;
import com.jiuzhe.app.hotel.dto.OrderDto;
import com.jiuzhe.app.hotel.entity.*;
import com.jiuzhe.app.hotel.module.EvaluateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description:订单DAO
 */

@Repository
public class HotelOrderDao {
    @Autowired
    HotelOrderMapper hotelOrderMapper;

    public void createHotelOrder(HotelOrder order) {
        hotelOrderMapper.createHotelOrder(order);
    }

    public Integer getDiscount(Integer vipLevel) {
        return hotelOrderMapper.getDiscount(vipLevel);
    }

    public int getOrderNumByStatus(String userId, Integer status) {
        return hotelOrderMapper.getOrderNumByStatus(userId, status);
    }

    public HotelOrder getOrderById(String id) {
        return hotelOrderMapper.getOrderById(id);
    }

    public List<HotelOrder> getOrdersByUserId(String userId) {
//       PageHelper.startPage(2, 3);
        return hotelOrderMapper.getOrdersByUserId(userId);
    }

    public List<HotelOrder> getOrdersByUserPhone(String phone) {
//       PageHelper.startPage(2, 3);
        return hotelOrderMapper.getOrdersByUserPhone(phone);
    }

    public Map<String, String> getImgsBySkuId(String skuId) {
        return hotelOrderMapper.getImgsBySkuId(skuId);
    }

    public List<HotelOrder> getOrderbyMerchantId(String merchantId) {
//       PageHelper.startPage(2, 3);
        return hotelOrderMapper.getOrderbyMerchantId(merchantId);
    }

    public List<HotelOrder> getBondOrderbyMerchantId(String merchantId) {
//       PageHelper.startPage(2, 3);
        return hotelOrderMapper.getBondOrderbyMerchantId(merchantId);
    }

    public List<HotelOrder> getApplyOrderbyMerchantId(String merchantId) {
        return hotelOrderMapper.getApplyOrderbyMerchantId(merchantId);
    }

    public int upOrderStatusById(String id, Integer status, LocalDateTime paidCancelTime) {
        return hotelOrderMapper.upOrderStatusById(id, status, paidCancelTime);
    }

    public Integer getRoomStatusForUp(String skuId) {
        return hotelOrderMapper.getRoomStatusForUp(skuId);
    }

    public int upRoomStatus(String skuId, Integer roomStatus) {
        return hotelOrderMapper.upRoomStatus(skuId, roomStatus);
    }

    public int saveDailyStatus(List<SkuDailyStatus> list) {
        return hotelOrderMapper.saveDailyStatus(list);
    }

    public List<OrderDto> getExpriedOrder(LocalDateTime time, Integer orderStatus) {
        return hotelOrderMapper.getExpriedOrder(time, orderStatus);
    }


    public LocalDateTime getCountDown(String id) {
        return hotelOrderMapper.getCountDown(id);
    }

    public int upOrderStatusLived(LocalDate startDate, Integer paidStatus, Integer livedStatus) {
        return hotelOrderMapper.upOrderStatusLived(startDate, paidStatus, livedStatus);
    }

    public HotelOrder haveOrserPaid(String id, Integer orderStastus) {
        return hotelOrderMapper.haveOrserPaid(id, orderStastus);
    }

    public HotelSku getLngLatBySkuId(String skuId) {
        return hotelOrderMapper.getLngLatBySkuId(skuId);
    }

    public int saveOrderScore(String orderId, Integer score) {
        return hotelOrderMapper.saveOrderScore(orderId, score);
    }

    public int saveSkuScore(String skuId, Integer score) {
        return hotelOrderMapper.saveSkuScore(skuId, score);
    }

    public int saveSkuCleanScore(String skuId, Integer score) {
        return hotelOrderMapper.saveSkuCleanScore(skuId, score);
    }

    public Evaluate getSkuScore(String skuId) {
        return hotelOrderMapper.getSkuScore(skuId);
    }

    public int evaluate(EvaluateQuery query) {
        return hotelOrderMapper.evaluate(query);
    }

    public void changePaidTolived(PaidToLived paidToLived) {
        hotelOrderMapper.changePaidTolived(paidToLived);
    }

}


