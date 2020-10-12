package com.jiuzhe.app.hotel.service;

import java.util.Map;

/**
 * @Description:统计功能
 */
public interface CountService {
    Map<String, String> getIndexCount(String merchantId, String storeId);

    Map getManageCountInfo(String storeId);

}
