package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.constants.OrderStatusEnum;
import com.jiuzhe.app.hotel.constants.RoomStatusEnum;
import com.jiuzhe.app.hotel.dao.HotelOrderDao;
import com.jiuzhe.app.hotel.dao.SchedulerDao;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.service.SchedulerService;
import com.jiuzhe.app.hotel.service.aliyun.AcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:定时器任务
 * @author:郑鹏宇
 * @date: 2018/5/2
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {


    @Autowired
    SchedulerDao dao;

    @Autowired
    AcsService service;

    @Autowired
    HotelOrderDao hotelOrderDao;

    @Autowired
    RestTemplate restTemplate;

    /**
     * @Description:每天11:30发短信提醒房客退房
     * @author:郑鹏宇
     * @date:2018/5/2
     */
    @Override
    public void msgUserGo() {
        //查出需要提醒的房客的订单
        LocalDate localDate = LocalDate.now();
        Integer orderStatus = OrderStatusEnum.LIVED.getIndex();
        List<HotelOrder> dtoList = dao.getMsgUserGo(localDate, orderStatus);
        if (dtoList != null) {
            for (HotelOrder hotelOrder : dtoList) {
                boolean rst = service.sendValidateCode(hotelOrder.getOccupantPhone(), hotelOrder.getOccupantName(), hotelOrder.getId());
            }
        }
    }

    /**
     * @Description:将当天的已支付订单变成已入住，同时将结束定单自动发起推押金
     * @author:郑鹏宇
     * @date:2018/5/3
     */
    @Override
    public void changStatsuLivedAndApply() {
        LocalDate localDate = LocalDate.now();
        //每天12自动将当天已付款订单修改为已入住(paid >> lived 3>>5)
        dao.changPaidToLived();
        //每天12点自动将离店日期小于当天的，已入住订单自动申请退押金状态
        dao.changLivedToApply();
    }

    /**
     * @Description:将同时将结束定单自动发起推押金
     * @author: 郑鹏宇
     * @date 2018/7/3/003
     */
    public void changLivedToApplyByUserId(String userId) {
        dao.changLivedToApplyByUserId(userId);
    }

}
