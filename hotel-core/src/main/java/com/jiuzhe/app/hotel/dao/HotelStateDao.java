package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.HotelStateMapper;
import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.entity.HotelSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description:房态
 */
@Repository
public class HotelStateDao {

    @Autowired
    HotelStateMapper mapper;

    public List<HotelOrder> getOrders(String storeId, LocalDate beginDate, LocalDate endDate) {
        return mapper.getOrders(storeId, beginDate, endDate);
    }



    public List<HotelSku> getSkuByMerchantId (String merchantId) {
        return mapper.getSkuByMerchantId(merchantId);
    }

    public List<HotelSku> getSkuByStoreId (String storeId) {
        return mapper.getSkuByStoreId(storeId);
    }


}
