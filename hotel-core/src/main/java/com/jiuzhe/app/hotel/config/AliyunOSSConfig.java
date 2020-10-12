package com.jiuzhe.app.hotel.config;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AliyunOSSConfig {
    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.end-point}")
    private String endpoint;

    @Bean(destroyMethod="shutdown")
    public OSSClient openSearchClient() {
        // 创建ClientConfiguration实例
        ClientConfiguration conf = new ClientConfiguration();

        // 创建OSSClient实例
        return new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
    }
}
