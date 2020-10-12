package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.constants.RoomStatusEnum;
import com.jiuzhe.app.hotel.dao.SkuManageDao;
import com.jiuzhe.app.hotel.dao.SkuSearchQueryDao;
import com.jiuzhe.app.hotel.dto.PriceBondDto;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.SkuDailyPrice;
import com.jiuzhe.app.hotel.entity.SkuSaveQuery;
import com.jiuzhe.app.hotel.module.*;
import com.jiuzhe.app.hotel.service.SkuManageService;
import com.jiuzhe.app.hotel.utils.DatePriceUtil;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:房东端房间管理
 * @author:郑鹏宇
 * @date: 2018/5/9
 */
@Service
public class SkuManageServiceImpl implements SkuManageService {

    @Autowired
    SkuManageDao dao;
    @Autowired
    SkuSearchQueryDao skuSearchQueryDao;

    /**
     * @Description:根据经纬度条件查询商户的房源
     * @author:郑鹏宇
     * @date:2018/5/10
     */
    public List<HotelSku> getSearchSkuHotels(MannageSearchSkuQuery query) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setId(null);
        if (!StringUtil.isEmptyOrNull(query.getMerchantId())) {
            hotelSku.setMerchantId(query.getMerchantId());
        }
        if (null != query.getBeginLng()) {
            hotelSku.setBeginLng(query.getBeginLng());
        }
        if (null != query.getEndLng()) {
            hotelSku.setEndLng(query.getEndLng());
        }
        if (null != query.getBeginLat()) {
            hotelSku.setBeginLat(query.getBeginLat());
        }
        if (null != query.getEndLat()) {
            hotelSku.setEndLat(query.getEndLat());
        }
        return dao.getSkuHotels(hotelSku);
    }

    /**
     * @Description:根据条件查询商户的房源
     * @author:郑鹏宇
     * @date:2018/5/10
     */
    public List<HotelSku> getSkuHotels(MannageSkuQuery query) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setId(null);
        if (!StringUtil.isEmptyOrNull(query.getMerchantId())) {
            hotelSku.setMerchantId(query.getMerchantId());
        }
        if (!StringUtil.isEmptyOrNull(query.getCityName())) {
            hotelSku.setCityName(query.getCityName());
        }
        if (!StringUtil.isEmptyOrNull(query.getArea())) {
            hotelSku.setArea(query.getArea());
        }
        return dao.getSkuHotels(hotelSku);
    }


    @Override
    public boolean checkSkuHotelNum(String skuId) {
        if (dao.checkSkuHotelNum(skuId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description:添加房源信息（需要添加房子，照片，和设施）,同时lay表中count数要变化
     * @author:郑鹏宇
     * @date:2018/5/10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSku(SkuSaveQuery query) {
//        保存信息到hotel_sku表
        List<HotelSku> skuList = HotelSku.make(query);
        dao.upCount(query.getRoomNos().size(), query.getLayoutId());
        dao.saveMoreSku(skuList);
    }


    /**
     * @Description:修改房源(都是先删除在添加)
     * @author:郑鹏宇
     * @date:2018/5/11
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upSku(SkuQuery query) {
        //部分信息在类HotelSku
        HotelSku sku = HotelSku.make(query);
        dao.upSkuHotel(sku);
    }

    /**
     * @Description:删除房源
     * @author:郑鹏宇
     * @date:2018/5/11
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSku(String skuId,String layId) {
        //同时删除该房间所有的放盘价信息
        dao.deletePriceBySkuId(skuId);
        //房间数减一
        dao.upCount(-1,layId);
        dao.deleteSku(skuId);
    }
    @Override
    public PriceBondDto getPriceAndBond(String skuId) {
        return  dao.getPriceAndBond(skuId);
    }

    /**
     * @Description:通过skuId获取详细房间信息
     * @author:郑鹏宇
     * @date:2018/5/16
     */
    public List<HotelSku> getSkuHotelBySkuId(String skuId) {
        HotelSku hotelSku = new HotelSku();
        hotelSku.setId(skuId);
        return dao.getSkuHotels(hotelSku);
    }

    /**
     * @Description:获取房间的列表
     * @author:郑鹏宇
     * @date:2018/5/16
     */
    @Override
    public List<HotelSku> getSkuDetails(MannageSearchSkuQuery query) {
        HotelSku hotelSku = HotelSku.make(query);
//        PageHelper.startPage(query.getCurrentPage(), query.getNumber());
        return dao.getSkuHotels(hotelSku);
    }

    /**
     * @Description:批量修改放盘价，押金，房间状态
     * @author:郑鹏宇
     * @date:2018/5/19
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upAllSkuPrice(UpAllSkuPriceQuery query) {
        //获取预定房间范围的所有日期(同时做成保存放盘价格的参数)
        LocalDate begin = LocalDate.parse(query.getStartDate());
        LocalDate end = LocalDate.parse(query.getEndDate());
        Period p = Period.between(begin, end);
        int dayNum = p.getDays();
        List<SkuDailyPrice> dailyPrices = new ArrayList<>();
        for (int index = 0; index <=dayNum; index++) {
            SkuDailyPrice dailyPrice = new SkuDailyPrice();
            dailyPrice.setSkuId(query.getSkuId());
            dailyPrice.setListDate(begin.plusDays(index));
            dailyPrice.setListingPrice(query.getListingPrice());
            dailyPrices.add(dailyPrice);
        }
        //修改放盘价格表(先删除再修改)
        int deleteListingPrice = dao.deleteDailyPrice(query.getSkuId(), begin, end);
        int saveListingPrice = dao.saveListingPrice(dailyPrices);

    }

    public List getSkuPriceBySkuId(String skuId) {
        List<Map> map = skuSearchQueryDao.getDailyPriceBySkuId(skuId);
        //放盘价格日期价格对应的转换
        List priceLiset = DatePriceUtil.getHolidayPrice(map);
        return priceLiset;
    }


    /**
     * @Description:删除放盘价格
     * @author:郑鹏宇
     * @date:2018/6/8
     */
    @Override
    public void deleteSkuPrice(DailyPriceQuery query) {
        //获取预定房间范围的所有日期(同时做成保存放盘价格的参数)
        LocalDate begin = LocalDate.parse(query.getStartDate());
        LocalDate end = null;
        if (StringUtil.isEmptyOrNull(query.getEndDate())) {
            end = begin;
        } else {
            end = LocalDate.parse(query.getEndDate());
        }
        Period p = Period.between(begin, end);
        //删除放盘价格
        int deleteListingPrice = dao.deleteDailyPrice(query.getSkuId(), begin, end);
    }

    /**
     * @Description:修改脏房
     * @author:郑鹏宇
     * @date:2018/6/6
     */
    @Override
    public void upDurtyToSale(String skuId) {
        dao.upRoomStatus(skuId, RoomStatusEnum.ON_SALE.getIndex());
    }

    /**
     * @Description:获取房间状态
     * @author:郑鹏宇
     * @date:2018/6/6
     */
    @Override
    public Integer getRoomStatus(String skuId) {
        return dao.getRoomStaus(skuId);
    }
}
