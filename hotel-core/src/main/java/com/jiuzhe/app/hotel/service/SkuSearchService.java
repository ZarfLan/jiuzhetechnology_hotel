package com.jiuzhe.app.hotel.service;

import com.jiuzhe.app.hotel.dto.SkuDetailDto;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.Search;
import com.jiuzhe.app.hotel.entity.SkuDetail;
import com.jiuzhe.app.hotel.module.RecommetQuery;
import com.jiuzhe.app.hotel.module.SearchQuery;
import com.jiuzhe.app.hotel.module.SkuDetailQuery;

import java.util.List;

public interface SkuSearchService {

    /**
     * @Description:判定该id是否存在
     */
    Boolean checkHotelNum(String skuId);

    /**
     * @Description:二次走数据库取酒店信息
     */
    List<Search> getHotelItems(SearchQuery searchQuery);

    /**
     * @Description:点击地图图标展示预定酒店详情
     */
    SkuDetailDto getSkuFacilitys(SkuDetailQuery skuDetailQuery);

    /**
     * @Description:通过skuId获取房间的所有信息
     */
    SkuDetail getSkuDetailBySkuId(String skuId);

    /**
     * @Description:根据全中找房子
     */
    List<HotelSku> getRecommendHotel(RecommetQuery query);

    /**
     * @Description:从数据库总获取所有的图
     */
    List<String> getAllimgsFromDb();

    String getPhoneBySkuId(String skuId);
}
