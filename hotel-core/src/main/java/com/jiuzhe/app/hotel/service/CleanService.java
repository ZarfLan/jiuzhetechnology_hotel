package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.module.CleanQuery;

import java.util.List;
import java.util.Map;

public interface CleanService {
    Map getCleanInfo(List <String> storeIds);
    Map changRoomStaus(CleanQuery query);
}
