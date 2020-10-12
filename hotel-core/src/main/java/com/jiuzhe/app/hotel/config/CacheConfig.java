package com.jiuzhe.app.hotel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisClusterConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

  @Value("${spring.redis.host}")
  String host;

  @Value("${spring.redis.port}")
  int port;

  @Value("${spring.redis.timeout}")
  int timeout;

  @Value("${spring.redis.cluster.nodes}")
  String nodes;

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory cf;
    List<String> nodeList = Arrays.asList(nodes.split(","));
    
    cf = new JedisConnectionFactory(new RedisClusterConfiguration(nodeList));
    // cf.setHostName(host);
    // cf.setPort(port);
    cf.setTimeout(timeout);

    return cf;
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    return cacheManager;
  }


}
