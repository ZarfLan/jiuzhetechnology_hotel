package com.jiuzhe.app.hotel.service.aliyun;

public interface MnsService {
    void sendMessage(String msgBody) throws Exception;
}
