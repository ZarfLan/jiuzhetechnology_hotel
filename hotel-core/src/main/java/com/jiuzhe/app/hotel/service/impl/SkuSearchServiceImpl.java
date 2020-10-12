package com.jiuzhe.app.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.jiuzhe.app.hotel.constants.RoomStatusEnum;
import com.jiuzhe.app.hotel.dao.SkuSearchQueryDao;
import com.jiuzhe.app.hotel.dto.SkuDetailDto;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.Search;
import com.jiuzhe.app.hotel.entity.SkuDetail;
import com.jiuzhe.app.hotel.entity.SkuDetailDate;
import com.jiuzhe.app.hotel.module.RecommetQuery;
import com.jiuzhe.app.hotel.module.SearchQuery;
import com.jiuzhe.app.hotel.module.SkuDetailQuery;
import com.jiuzhe.app.hotel.service.HotelorderService;
import com.jiuzhe.app.hotel.service.SkuSearchService;
import com.jiuzhe.app.hotel.utils.DatePriceUtil;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @author:郑鹏宇
 * @date: 2018/4/6
 */
@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    SkuSearchQueryDao skuSearchQueryDao;
    @Autowired
    HotelorderService hotelorderService;

    /**
     * @Description:
     * @author:郑鹏宇
     * @date:2018/6/11
     */
    @Override
    public Boolean checkHotelNum(String skuId) {
        Integer num = skuSearchQueryDao.getNumById(skuId);
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description:查詢房間拿数据(此处需要对价格，状态，价格区间做筛选)
     * @author:郑鹏宇
     * @date:2018/4/7
     */
    @Override
    public List<Search> getHotelItems(SearchQuery searchQuery) {
        List<Search> hotelItems = skuSearchQueryDao.getHotels(searchQuery);
        System.out.println(hotelItems + "++++++++++++++++++++====");
        List<Search> forDelete = new ArrayList<>();
        for (Search dto : hotelItems) {
            //判定房间状态
            dto.setStatus(dto.getDefStatus());
            Map map = hotelorderService.getReservation(dto.getId(), searchQuery.getStartDate(), searchQuery.getEndDate());
            //TODO 过滤掉已经被订的
            if ("0".equals(map.get("status"))) {
                if (!"1".equals(map.get("canBeReserved"))) {
                    dto.setStatus(RoomStatusEnum.SALED.getIndex());
                }
            } else {
                throw new RuntimeException();
            }
            // 判断价格是使用默认价格还是放盘价格
            if (null == dto.getPrice() && null != dto.getDefPrice()) {
                dto.setPrice(dto.getDefPrice());
            }
            // 过滤掉价格范围外掉酒店
            if (null != searchQuery.getBeginPrice() && null != searchQuery.getEndPrice()) {
                if (dto.getPrice() > searchQuery.getEndPrice() ||
                        dto.getPrice() < searchQuery.getBeginPrice()) {
                    forDelete.add(dto);
                }
            }
        }
        hotelItems.removeAll(forDelete);
        return hotelItems;
    }

    /**
     * @Description:查询点击地图图标展示预定酒店详情
     * @author:郑鹏宇
     * @date:2018/4/10
     */
    @Override
    public SkuDetailDto getSkuFacilitys(SkuDetailQuery skuDetailQuery) {
        LocalDate begin = LocalDate.parse(skuDetailQuery.getStartDate());
        LocalDate end = LocalDate.parse(skuDetailQuery.getEndDate());
        int dayNum = end.getDayOfYear() - begin.getDayOfYear();
        // 从后台查询房间具体信息
        SkuDetail skuDetail = skuSearchQueryDao.getBaseFacility(skuDetailQuery);
        List<SkuDetailDate> skuDetails = skuSearchQueryDao.getDatePrice(skuDetailQuery);
        Map<LocalDate, Integer> datePrices = new HashMap<>();
        skuDetails.stream()
                .forEach(elem -> datePrices.put(elem.getListDate(), elem.getListingPrice()));

        // 生成日期价格列表(状态列表)
        List<String> days = new ArrayList<>();
        List<Integer> prices = new ArrayList<>();
        LocalDate dateTemp;
        for (int index = 0; index < dayNum; index++) {
            dateTemp = begin.plusDays(index);
            days.add(dateTemp.toString());

            // 有放盘价格用放盘价格，没有放盘价格用默认价格
            if (!datePrices.containsKey(dateTemp)) {
                prices.add(skuDetail.getDefPrice());
            } else {
                prices.add(datePrices.get(dateTemp));
            }
        }
        // 生成dto模型
        //需要查询一下当日房态
        Map map = hotelorderService.getReservation(skuDetailQuery.getId(), skuDetailQuery.getStartDate(), skuDetailQuery.getEndDate());
        Integer status = Integer.parseInt(map.get("status").toString());
        SkuDetailDto skuDetailDto = SkuDetailDto.make(skuDetail);
        skuDetailDto.setStatus(status);
        skuDetailDto.setLocalDates(days);
        skuDetailDto.setPrices(prices);
        skuDetailDto.setStatus(RoomStatusEnum.ON_SALE.getIndex());
        return skuDetailDto;
    }

    /**
     * @Description:通过skuId获取房间的所有信息
     * @author:郑鹏宇
     * @date:2018/6/8
     */
    @Override
    public SkuDetail getSkuDetailBySkuId(String skuId) {
        SkuDetailQuery query = new SkuDetailQuery();
        query.setId(skuId);
        SkuDetail skuDetail = skuSearchQueryDao.getBaseFacility(query);
        skuDetail.setImgs(StringUtil.stringToList(skuDetail.getUrls()));
        List<Map> map = skuSearchQueryDao.getDailyPriceBySkuId(skuId);
        //放盘价格日期价格对应的转换
        List priceLiset = DatePriceUtil.getHolidayPrice(map);
        if (null == priceLiset) {
            priceLiset = new ArrayList();
        }
        skuDetail.setDatePriceList(priceLiset);
        return skuDetail;
    }

    /**
     * @Description:根据权重后去房子
     * @author: 郑鹏宇
     * @date 2018/6/20/020
     */
    @Override
    public List<HotelSku> getRecommendHotel(RecommetQuery query) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setArea(query.getArea());
        hotelSku.setCityName(query.getCityName());
        PageHelper.startPage(1, query.getNum());
        List<HotelSku> hotelSkus = skuSearchQueryDao.getRecommendHotel(hotelSku, query.getGist());
        return hotelSkus;
    }

    @Override
    public List<String> getAllimgsFromDb() {
        List<String> imgStrList = skuSearchQueryDao.getAllimgsFromDb();
        List<String> imgs = new ArrayList<>();
        for (String str : imgStrList) {
            if (null != str) {
                List<String> imsStr = StringUtil.stringToList(str);
                imgs.addAll(imsStr);
            }
        }
        return imgs;
    }

    @Override
    public String getPhoneBySkuId(String skuId) {
        return skuSearchQueryDao.getPhoneBySkuId(skuId);
    }
}
