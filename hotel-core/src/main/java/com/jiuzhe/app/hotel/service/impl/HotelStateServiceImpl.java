package com.jiuzhe.app.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.jiuzhe.app.hotel.dao.HotelStateDao;
import com.jiuzhe.app.hotel.dto.HotelStateBodyDto;
import com.jiuzhe.app.hotel.dto.HotelStateDto;
import com.jiuzhe.app.hotel.dto.HotelStateHeadDto;
import com.jiuzhe.app.hotel.dto.HotelStateLeftDto;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.module.HotelStataQuery;
import com.jiuzhe.app.hotel.service.HotelStateService;
import com.jiuzhe.app.hotel.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * @Description:房态
 * @author:郑鹏宇
 * @date: 2018/5/30
 */
@Service
public class HotelStateServiceImpl implements HotelStateService {
    @Autowired
    HotelStateDao dao;

    @Override
    public HotelStateDto getHotelState(HotelStataQuery query) {
        LocalDate begin = LocalDate.parse(query.getBeginDate());
        LocalDate end = LocalDate.parse(query.getEndDate());
        //获取商户房间的id和name
        List<HotelSku> hotelSkus = dao.getSkuByStoreId(query.getStoreId());
        PageHelper.startPage(query.getCurrentPage(),query.getNumber());
        List<HotelSku> hotelSkusPage = dao.getSkuByStoreId(query.getStoreId());
        //获取商户房间总数
        Integer skuNumAll = hotelSkus.size();
        //获取该所有的订单信息
        List<HotelOrder> orderList = dao.getOrders(query.getStoreId(), begin, end);
        //获取表头(遍历日期，然后获取每天有多少个订单，然后用总房间数减获取剩余数量)
        List<HotelStateHeadDto> headDtos = new ArrayList<>();
        Period p = Period.between(begin, end);
        int dayNum = p.getDays();
        for (int index = 0; index <= dayNum; index++) {
            LocalDate date = begin.plusDays(index);
            Integer num = 0;
            for (HotelOrder order : orderList) {
                if (TimeUtil.isBetweenDate(date, order.getStartDate(), order.getEndDate())) {
                    num += 1;
                }
            }
            HotelStateHeadDto dto = HotelStateHeadDto.make(date, skuNumAll - num);
            headDtos.add(dto);
        }
        //获取左边
        List<HotelStateLeftDto> leftDtos = new ArrayList<>();
        //获取表身(先获取单个房间的每天入住情况，再将单个的汇集成集合)
        List<HotelStateBodyDto> bodyList = new ArrayList<>();
        for (HotelSku hotelSku : hotelSkusPage) {
            List<HotelStateBodyDto> bodyDtos = new ArrayList<>();
            for (int index = 0; index <= dayNum; index++) {
                HotelStateBodyDto dto = new HotelStateBodyDto();
                LocalDate date = begin.plusDays(index);
                for (HotelOrder order : orderList) {
                    //需要判定日期和房间同时对应
                    if (TimeUtil.isBetweenDate(date, order.getStartDate(), order.getEndDate())
                            && order.getSkuId().equals(hotelSku.getId())) {
                        dto.setDate(date.toString());
                        dto.setSkuName(order.getSkuName());
                        dto.setUserName(order.getOccupantName());
                        //给前台标红
                        //dto.setOrderStatus(6);
                        dto.setOrderStatus(order.getStatus());
                        dto.setOrderId(order.getId());
                        dto.setSkuId(order.getSkuId());
                        dto.setRoomNo(order.getRoomNo());
                        bodyDtos.add(dto);
                    }
                }
            }
            //当body内容为空时，left不能为空
            if (bodyDtos.size()>0) {
                HotelStateLeftDto leftDto = new HotelStateLeftDto();
                leftDto.setSkuId(bodyDtos.get(0).getSkuId());
                leftDto.setRoomNo(bodyDtos.get(0).getRoomNo());
                leftDto.setSkuName(bodyDtos.get(0).getSkuName());
                leftDtos.add(leftDto);
                bodyList.addAll(bodyDtos);
            }else {
                HotelStateLeftDto leftDto = new HotelStateLeftDto();
                leftDto.setSkuId(hotelSku.getId());
                leftDto.setSkuName(hotelSku.getSkuName());
                leftDto.setRoomNo(hotelSku.getRoomNo());
                leftDtos.add(leftDto);
            }

        }
        HotelStateDto hotelStateDto = new HotelStateDto();
        hotelStateDto.setHead(headDtos);
        hotelStateDto.setBody(bodyList);
        hotelStateDto.setLeft(leftDtos);
        return hotelStateDto;
    }
}
