package com.jiuzhe.app.hotel.config;

import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunOpenSearchConfig {
    @Value("${aliyun.open-search.access-key-id}")
    private String accesskey;

    @Value("${aliyun.open-search.access-key-secret}")
    private String secret;

    @Value("${aliyun.open-search.host}")
    private String host;

    @Bean
    public OpenSearchClient openSearchClient() {
        //创建并构造OpenSearch对象
        OpenSearch openSearch = new OpenSearch(accesskey, secret, host);

        //创建OpenSearchClient对象，并以OpenSearch对象作为构造参数
        OpenSearchClient serviceClient = new OpenSearchClient(openSearch);

        return serviceClient;
    }
}
