package com.jiuzhe.app.hotel.config;

import com.jiuzhe.app.hotel.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    SchedulerService schedulerService;

    /**
     * @Description:每天11：30提醒用户离开
     */
    @Scheduled(cron = "00 30 11 * * ?")
    public void msgUserGo() {
        schedulerService.msgUserGo();
    }

    /**
     * @Description:自动发起退押金申请，同时也是在这个点同时将当天的订单变成已入住
     */
    @Scheduled(cron = "00 00 12 * * ?")
    public void changStatsuLivedAndApply() {
        schedulerService.changStatsuLivedAndApply();
    }

}