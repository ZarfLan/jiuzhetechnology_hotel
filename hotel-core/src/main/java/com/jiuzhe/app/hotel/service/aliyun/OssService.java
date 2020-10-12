package com.jiuzhe.app.hotel.service.aliyun;

import java.io.InputStream;
import java.util.List;

public interface OssService {
    void updateObject(String bucketName, String objKey, InputStream inputStream);

    void deleteHotelImg(List<String> keys);

    List<String> getAllHotelImg();
}
