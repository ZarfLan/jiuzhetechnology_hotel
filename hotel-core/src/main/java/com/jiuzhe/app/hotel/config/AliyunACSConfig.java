package com.jiuzhe.app.hotel.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AliyunACSConfig {
    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    private String product = "Dysmsapi";
    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * accessKeyId
     */
    @Value("${aliyun.asc.access-key-id}")
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    @Value("${aliyun.asc.access-key-secret}")
    private String accessKeySecret;


    @Bean
    public IAcsClient ascClient() throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }
}
