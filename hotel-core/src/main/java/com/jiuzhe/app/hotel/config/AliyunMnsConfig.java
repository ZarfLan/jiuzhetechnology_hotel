package com.jiuzhe.app.hotel.config;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunMnsConfig {
    @Value("${aliyun.mns.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.mns.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.mns.end-point}")
    private String endpoint;

    @Bean(destroyMethod="close")
    public MNSClient MnsClient() {
        // 在程序中，CloudAccount以及MNSClient单例实现即可，多线程安全
        CloudAccount account = new CloudAccount(accessKeyId, accessKeySecret, endpoint);
        return account.getMNSClient();
    }
}
