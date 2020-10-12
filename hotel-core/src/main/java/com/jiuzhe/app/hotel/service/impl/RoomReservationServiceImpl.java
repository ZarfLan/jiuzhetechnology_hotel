package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.service.RoomReservationService;
import com.jiuzhe.app.hotel.utils.TimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {
    private static Log logger = LogFactory.getLog(RoomReservationServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${maxreservationday}")
    private int maxReservationDays;

    private Map check(String startDt, String beginDate, String endDate) {
        int startCheckDays = TimeUtil.daysBetween(startDt, beginDate);
        if (startCheckDays > maxReservationDays)
            return rtCodeConstant.getResult("4", "入住时间超出可预订最大期限");

        int endCheckDays = TimeUtil.daysBetween(startDt, endDate);
        if (endCheckDays > maxReservationDays)
            return rtCodeConstant.getResult("4", "离店时间超出可预订最大期限");

        int checkDays = TimeUtil.daysBetween(beginDate, endDate);
        if (checkDays < 0)
            return rtCodeConstant.getResult("4", "离店时间小于入住时间");

        return rtCodeConstant.getResult("0", startCheckDays + "", endCheckDays + "", checkDays + "");
    }

    public Map getReservation(String skuId, String beginDate) {
        List<Map<String, Object>> reservations = jdbcTemplate.queryForList(String.format("SELECT reservation  &  (1 << datediff('%s',start_dt)) reservation  from hotel_sku where id = '%s'", beginDate, skuId));
        if (reservations.size() == 0)
            return rtCodeConstant.getResult("3");

        Map reservationMap = reservations.get(0);
        long reservation = Long.parseLong(reservationMap.get("reservation").toString());
        String canBeReserved = "1";
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        Map dtReserved = new HashMap<String, String>();

        dtReserved.put("date", beginDate);
        if (reservation != 0) {
            canBeReserved = "2";
            dtReserved.put("reserved", "1");
        } else {
            dtReserved.put("reserved", "0");
        }
        data.add(dtReserved);
        return rtCodeConstant.getResult("0", "canBeReserved", canBeReserved, data);
    }

    public Map getReservation(String skuId, String beginDate, String endDate) {
        List<Map<String, Object>> reservations = jdbcTemplate.queryForList(String.format("select reservation + 0 reservation,start_dt from hotel_sku where id = '%s'", skuId));
        if (reservations.size() == 0)
            return rtCodeConstant.getResult("3");

        Map reservationMap = reservations.get(0);
        long reservation = Long.parseLong(reservationMap.get("reservation").toString());
        String startDt = reservationMap.get("start_dt").toString().substring(0, 10);
        Map checkrs = check(startDt, beginDate, endDate);
        if (!checkrs.get("status").toString().equals("0"))
            return checkrs;
        List<String> checkdata = (List<String>) checkrs.get("data");
        int startCheckDays = Integer.parseInt(checkdata.get(0));
        int endCheckDays = Integer.parseInt(checkdata.get(1));

        String canBeReserved = "1";
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        for (int i = startCheckDays; i <= endCheckDays; i++) {
            long reserved = reservation & (long) Math.pow(2, i);
            String dt = TimeUtil.getNextDay(startDt, i);
            Map dtReserved = new HashMap<String, String>();
            dtReserved.put("date", dt);
            if (reserved != 0) {
                canBeReserved = "2";
                dtReserved.put("reserved", "1");
            } else {
                dtReserved.put("reserved", "0");
            }
            data.add(dtReserved);
        }

        return rtCodeConstant.getResult("0", "canBeReserved", canBeReserved, data);
    }

    public Map setReservation(String skuId, String beginDate) {
        jdbcTemplate.update(String.format("update hotel_sku set reservation = reservation | (1 << datediff('%s' ,start_dt)) where id = '%s'", beginDate, skuId));
        return rtCodeConstant.getResult("0");
    }

    public Map unsetReservation(String skuId, String beginDate) {
        jdbcTemplate.update(String.format("update hotel_sku set reservation = reservation & ~(1 << datediff('%s' ,start_dt)) where id = '%s'", beginDate, skuId));
        return rtCodeConstant.getResult("0");
    }

    @Transactional
    public Map setReservation(String skuId, String beginDate, String endDate) {
        List<Map<String, Object>> reservations = jdbcTemplate.queryForList(String.format("select reservation + 0 reservation,start_dt from hotel_sku where id = '%s' for update", skuId));
        if (reservations.size() == 0)
            return rtCodeConstant.getResult("3");

        Map reservationMap = reservations.get(0);
        long reservation = Long.parseLong(reservationMap.get("reservation").toString());
        String startDt = reservationMap.get("start_dt").toString().substring(0, 10);
        Map checkrs = check(startDt, beginDate, endDate);
        if (!checkrs.get("status").toString().equals("0"))
            return checkrs;
        List<String> checkdata = (List<String>) checkrs.get("data");
        int startCheckDays = Integer.parseInt(checkdata.get(0));
        int endCheckDays = Integer.parseInt(checkdata.get(1));

        for (int i = startCheckDays; i <= endCheckDays; i++) {
            reservation = reservation | (long) 1 << i;
        }

        jdbcTemplate.update(String.format("update hotel_sku set reservation = %d where id = '%s'", reservation, skuId));
        return rtCodeConstant.getResult("0");
    }

    @Transactional
    public Map unsetReservation(String skuId, String beginDate, String endDate) {
        List<Map<String, Object>> reservations = jdbcTemplate.queryForList(String.format("select reservation + 0 reservation,start_dt from hotel_sku where id = '%s' for update", skuId));
        if (reservations.size() == 0)
            return rtCodeConstant.getResult("3");

        Map reservationMap = reservations.get(0);
        long reservation = Long.parseLong(reservationMap.get("reservation").toString());
        String startDt = reservationMap.get("start_dt").toString().substring(0, 10);
        Map checkrs = check(startDt, beginDate, endDate);
        if (!checkrs.get("status").toString().equals("0"))
            return checkrs;
        List<String> checkdata = (List<String>) checkrs.get("data");
        int startCheckDays = Integer.parseInt(checkdata.get(0));
        int endCheckDays = Integer.parseInt(checkdata.get(1));

        for (int i = startCheckDays; i <= endCheckDays; i++) {
            reservation = reservation & (long) ~(1 << i);
        }

        jdbcTemplate.update(String.format("update hotel_sku set reservation = %d where id = '%s'", reservation, skuId));
        return rtCodeConstant.getResult("0");
    }
}
