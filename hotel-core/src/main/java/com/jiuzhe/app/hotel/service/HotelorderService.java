package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dto.OrderSuccessDto;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.module.EvaluateQuery;
import com.jiuzhe.app.hotel.module.HotelOrderQuery;
import com.jiuzhe.app.hotel.module.OrderCancelQuery;

import java.util.List;
import java.util.Map;

public interface HotelorderService {
    OrderSuccessDto creatHotelOrder(HotelOrderQuery query);

    Map getReservation(String skuId, String beginDate, String endDate);

    void setReservation(String skuId, String beginDate, String endDate);

    void unsetReservation(String skuId, String beginDate, String endDate);

    Map sendDelay15Min(String orderId, String skuId, String beginDt, String endDt);

    Integer getOrderNumByStatus(String userId, Integer status);

    HotelOrder getOrderById(String id);

    int changeStatus(String id, Integer status);

    int cancelOrder(OrderCancelQuery query);

    List<HotelOrder> getOrderbyUserId(String userId);

    List<HotelOrder> getOrderbyMerchantId(String merchantId);

    List<HotelOrder> getBondOrderbyMerchantId(String merchantId);

    List<HotelOrder> getApplyOrderbyMerchantId(String merchantId, Integer currentPage, Integer number);

    Integer getCountDown(String id);

    HotelSku getLngLatBySkuId(String skuId);

    void saveOrderScore(String skuId, String orderId, Integer score);

    void  evaluate(EvaluateQuery query);

}
