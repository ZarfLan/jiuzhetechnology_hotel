package com.jiuzhe.app.hotel.service.impl;

import com.jiuzhe.app.hotel.constants.rtCodeConstant;
import com.jiuzhe.app.hotel.module.CleanQuery;
import com.jiuzhe.app.hotel.service.CleanService;
import com.jiuzhe.app.hotel.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author:郑鹏宇
 * @date 2018/8/13/013
 */
@Service
public class CleanServiceImpl implements CleanService {

    @Autowired
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Map getCleanInfo(List<String> storeIds) {
        String ids = StringUtil.ListToSqlStr(storeIds);
        List crr = jdbcTemplate.queryForList(String.format("SELECT b.city city,b.id storeId," +
                "b.name storeName,a.sku_name skuName,a.id skuId,a.room_no roomNo, substring_index(c.piclist,',',1) pic," +
                "a.room_type roomType,a.room_status roomStatus \n" +
                "FROM hotel_sku a \n" +
                "LEFT JOIN hotel_sku_layout c on a.layout_id = c.id\n" +
                "LEFT JOIN store b ON a.store_id = b.id\n" +
                "WHERE b.id in %s  and a.room_status = 3", ids));
        return rtCodeConstant.getResult("0", crr);
    }

    @Transactional
    public Map changRoomStaus(CleanQuery query) {
        String id = query.getId();
        String skuId = query.getSkuId();
        String merchantId = query.getMerchantId();
        String hrId = query.getHrId();
        String cleannerName = query.getCleannerName();
        //清洁完了改状态
        int a = jdbcTemplate.update(String.format("UPDATE hotel_sku SET room_status = 1 where id = '%s' AND room_status = 3 ",skuId));
        if (0 == a) {
            return rtCodeConstant.getResult("2");
        }
        //写入清洁日志
//        jdbcTemplate.update(String.format("INSERT INTO clean_record(id,merchant_id,hr_id,sku_id,cleaner_name)VALUES('%s','%s','%s','%s','%s')", id, merchantId, hrId, skuId, cleannerName));
        return rtCodeConstant.getResult("0");
    }
}
