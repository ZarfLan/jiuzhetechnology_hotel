package com.jiuzhe.app.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:启动入口
 * @author:张磊
 * @date: 2018/3/26
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jiuzhe.app.hotel.dao")
@EnableScheduling
@EnableTransactionManagement
public class HotelCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelCoreApplication.class, args);
    }

}
