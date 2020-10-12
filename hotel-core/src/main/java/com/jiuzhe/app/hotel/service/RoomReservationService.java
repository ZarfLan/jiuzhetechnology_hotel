package com.jiuzhe.app.hotel.service;

import java.util.Map;

public interface RoomReservationService {

    public Map getReservation(String skuId, String beginDate, String endDate);
    public Map getReservation(String skuId, String beginDate);
    public Map setReservation(String skuId, String beginDate, String endDate);
    public Map setReservation(String skuId, String beginDate);
    public Map unsetReservation(String skuId, String beginDate, String endDate);
    public Map unsetReservation(String skuId, String beginDate);

}
