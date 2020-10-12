package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dto.PriceBondDto;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.SkuSaveQuery;
import com.jiuzhe.app.hotel.module.*;

import java.util.List;

/**
 * @Description:房东端房间管理
 */
public interface SkuManageService {

    List<HotelSku> getSkuDetails(MannageSearchSkuQuery query);

    void saveSku(SkuSaveQuery query);

    void upSku(SkuQuery query);

    PriceBondDto getPriceAndBond(String skuId);

    void deleteSku(String skuId,String layId);

    boolean checkSkuHotelNum(String skuId);

    void upAllSkuPrice(UpAllSkuPriceQuery query);

    List getSkuPriceBySkuId(String skuId);

    void deleteSkuPrice(DailyPriceQuery query);


    void upDurtyToSale(String skuId);

    Integer getRoomStatus(String skuId);
}
