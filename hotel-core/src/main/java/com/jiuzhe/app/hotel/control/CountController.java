package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.module.IndexCountQuery;
import com.jiuzhe.app.hotel.service.CountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:统计功能的controller
 */
@Api(tags = "首页统计功能")
@RestController
@RequestMapping("/merchant")
public class CountController {

    private static final Logger logger = LoggerFactory.getLogger(CountController.class);
    @Autowired
    CountService service;

    @ApiOperation(value = "统计功能", notes = "统计功能")
    @PostMapping("/indexcount")
    public ResponseBase getCount(@RequestBody IndexCountQuery query) {
        ResponseBase responseBase = new ResponseBase<>();
        try {
            Map<String, String> map = service.getIndexCount(query.getMerchantId(), query.getStoreId());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(map);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:商户端统计首页
     * @author: 郑鹏宇
     * @date 2018/8/29/029
     */
    @PostMapping("/managecountinfo")
    public ResponseBase getManageCountInfo(@RequestBody Map<String, String> map) {
        ResponseBase responseBase = new ResponseBase<>();
        String storeId = map.get("storeId");
        try {
            responseBase.setStatus(CommonConstant.SUCCESS);
            Map map1 = service.getManageCountInfo(storeId);
            if (!map1.containsKey("income")){
                map1.put("income",0);
            }
            responseBase.setData(map1);
        } catch (Exception e) {
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

}
