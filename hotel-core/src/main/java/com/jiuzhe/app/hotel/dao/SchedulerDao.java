package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.constants.OrderStatusEnum;
import com.jiuzhe.app.hotel.dao.mapper.SchedulerMapper;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:定时器任务
 */

@Repository
public class SchedulerDao {

    @Autowired
    SchedulerMapper schedulerMapper;

    public List<HotelOrder> getMsgUserGo(LocalDate endDate, Integer orderStatus) {
        return schedulerMapper.getMsgUserGo(endDate, orderStatus);
    }

    public void changPaidToLived() {
        schedulerMapper.changPaidToLived(OrderStatusEnum.PAID.getIndex(), OrderStatusEnum.LIVED.getIndex());
    }

    public void changLivedToApply() {
        schedulerMapper.changLivedToApply(OrderStatusEnum.LIVED.getIndex(),OrderStatusEnum.APPLY.getIndex());
    }

    public void changLivedToApplyByUserId(String userId) {
        schedulerMapper.changLivedToApplyByUserId(OrderStatusEnum.LIVED.getIndex(),OrderStatusEnum.APPLY.getIndex(),userId);
    }

}
