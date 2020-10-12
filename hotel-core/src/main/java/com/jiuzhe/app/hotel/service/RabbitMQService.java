package com.jiuzhe.app.hotel.service;

import java.util.Map;

public interface RabbitMQService {

    public Map sendDelay15Min(String msg);
    public Map sendDelay15Min(String userId, String skuId, String beginDt);
    public Map sendDelay15Min(String userId, String skuId, String beginDt, String endDt);
}
