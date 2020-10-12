package com.jiuzhe.app.hotel.dao;

import com.jiuzhe.app.hotel.dao.mapper.SkuSearchQueryMapper;
import com.jiuzhe.app.hotel.dto.SearchQueryDto;
import com.jiuzhe.app.hotel.entity.*;
import com.jiuzhe.app.hotel.module.SearchQuery;
import com.jiuzhe.app.hotel.module.SkuDetailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Description:前台第二次查询走数据库的dao
 */
@Repository
public class SkuSearchQueryDao {
    @Autowired
    SkuSearchQueryMapper skuSearchQueryMapper;

    public Integer getNumById(String skuId) {
        return skuSearchQueryMapper.getNumById(skuId);
    }

    public List<Search> getHotels(SearchQuery query) {
        return skuSearchQueryMapper.getHotels(query);
    }

    public String getMerchantById(String id) {
        return skuSearchQueryMapper.getMerchantById(id);
    }

    public SkuDetail getBaseFacility(SkuDetailQuery skuDetailQuery) {
        return skuSearchQueryMapper.getBaseFacility(skuDetailQuery);
    }

    public List<SkuDetailDate> getDatePrice(SkuDetailQuery skuDetailQuery) {
        return skuSearchQueryMapper.getDatePrice(skuDetailQuery);
    }

    /**
     * @Description:单独获取每日房间状态的方法
     */
    public List<SkuDailyStatus> getHotelstatus(String skuId, LocalDate begin, LocalDate end) {
        return skuSearchQueryMapper.getHotelstatus(skuId, begin, end);
    }

    /**
     * @Description:通过skuId获取该房间今日以后的放盘价
     */
    public List<Map> getDailyPriceBySkuId(String skuId) {
        return skuSearchQueryMapper.getDailyPriceBySkuId(skuId);
    }

    /**
     * @Description:根据权重来找房子
     */
    public List<HotelSku> getRecommendHotel(HotelSku hotelSku,Integer gist) {
        return skuSearchQueryMapper.getRecommendHotel(hotelSku,gist);
    }

    public List<String> getAllimgsFromDb(){
        return skuSearchQueryMapper.getAllimgsFromDb();
    }

    public String getPhoneBySkuId(String skuId) {
        return skuSearchQueryMapper.getPhoneBySkuId(skuId);
    }
}
