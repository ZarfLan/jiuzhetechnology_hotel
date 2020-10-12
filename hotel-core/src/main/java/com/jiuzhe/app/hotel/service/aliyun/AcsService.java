package com.jiuzhe.app.hotel.service.aliyun;

public interface AcsService {
    boolean sendValidateCode(String phone, String name, String id);
}
