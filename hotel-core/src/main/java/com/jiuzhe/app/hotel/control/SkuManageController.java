package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.constants.RoomStatusEnum;
import com.jiuzhe.app.hotel.dto.HotelSkuDto;
import com.jiuzhe.app.hotel.dto.ManageHotelSkuDto;
import com.jiuzhe.app.hotel.dto.PriceBondDto;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.entity.HotelSku;
import com.jiuzhe.app.hotel.entity.SkuSaveQuery;
import com.jiuzhe.app.hotel.module.*;
import com.jiuzhe.app.hotel.service.SkuManageService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 */
@Api(tags = "房东端房源的管理")
@RestController
@RequestMapping("/skumanage")
public class SkuManageController {

    private static final Logger logger = LoggerFactory.getLogger(SkuManageController.class);

    @Autowired
    SkuManageService service;

    /**
     * @Description:按照条件查询sku信息
     */
    @ApiOperation(value = "按照条件查询sku信息", notes = "查询")
    @PostMapping("/getsku")
    public ResponseBase<List<HotelSkuDto>> getSkuDetails(@RequestBody MannageSearchSkuQuery query) {
        ResponseBase<List<HotelSkuDto>> responseBase = new ResponseBase<>();
        try {
            List<HotelSku> skuHotels = service.getSkuDetails(query);
            List<HotelSkuDto> dtos = skuHotels.stream()
                    .map(hotelSku -> HotelSkuDto.make(hotelSku))
                    .collect(Collectors.toList());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        } catch (Exception e) {
            logger.error(" merchant get all getSkuDetails!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("展示商户id最详细的列表信息异常！");
        }
        return responseBase;
    }


    /**
     * @Description:添加房源
     */
    @ApiOperation(value = "添加房源", notes = "添加房源")
    @PostMapping("/skusaving")
    public ResponseBase<String> saveSku(@RequestBody SkuSaveQuery query) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        try {
            service.saveSku(query);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData("SUCCESS");
        } catch (DuplicateKeyException e) {
            responseBase.setStatus(CommonConstant.EXISTED);
            responseBase.setData("重复添加");
        } catch (Exception e) {
            logger.error(" insert hotelSku failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("添加房源异常！");
        }
        return responseBase;
    }


    /**
     * @Description:修改房源
     */
    @ApiOperation(value = "修改房源", notes = "修改房源")
    @PostMapping("/skuupdating")
    public ResponseBase<String> upSku(@RequestBody SkuQuery query) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        if (service.checkSkuHotelNum(query.getSkuId())) {
            try {
                service.upSku(query);
                responseBase.setStatus(CommonConstant.SUCCESS);
                responseBase.setMessage("SUCCESS");
            } catch (Exception e) {
                logger.error(" update hotelSku failed!", e);
                responseBase.setStatus(CommonConstant.FAIL);
                responseBase.setMessage("修改房源异常！");
            }
        } else {
            responseBase.setStatus(CommonConstant.UNEXISTED);
            responseBase.setMessage("该数据不存在！");
        }
        return responseBase;
    }

    /**
     * @Description:脏房变为可预订
     */
    @ApiOperation(value = "脏房变为可预订", notes = "脏房变为可预订")
    @PostMapping("/durtyupdating")
    public ResponseBase<String> upDurtyToSale(@RequestBody Map<String, String> map) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        String skuId = map.get("skuId");
        if (!service.checkSkuHotelNum(skuId)) {
            responseBase.setStatus(CommonConstant.UNEXISTED);
            responseBase.setMessage("房间不存在！");
            return responseBase;
        }
        if (service.getRoomStatus(skuId) != RoomStatusEnum.DIRTY.getIndex()) {
            responseBase.setStatus(CommonConstant.ERR_STATUS);
            responseBase.setMessage("不是脏房！");
            return responseBase;
        }
        try {
            service.upDurtyToSale(skuId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setMessage("SUCCESS");
        } catch (Exception e) {
            logger.error(" update durtySku failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
            responseBase.setMessage("脏房变为可预订！");
        }
        return responseBase;
    }


    /**
     * @Description:删除房源
     */
    @ApiOperation(value = "删除房源", notes = "删除房源")
    @PostMapping("/skudelete")
    public ResponseBase<String> deleSku(@RequestBody Map<String, String> map) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        String skuId = map.get("skuId");
        String layId = map.get("layId");
        if (!service.checkSkuHotelNum(skuId)) {
            responseBase.setStatus(CommonConstant.UNEXISTED);
            responseBase.setMessage("房间不存在！");
            return responseBase;
        }
        try {
            service.deleteSku(skuId,layId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setMessage("SUCCESS");
        } catch (Exception e) {
            logger.error(" delete hotelSku failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:增加修改放盘价格
     */
    @ApiOperation(value = "增加修改放盘价格", notes = "增加修改放盘价格")
    @PostMapping("/uplistprice")
    public ResponseBase<String> upAllSkuListPrice(@RequestBody UpAllSkuPriceQuery query) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        try {
            service.upAllSkuPrice(query);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setMessage("SUCCESS");
        } catch (Exception e) {
            logger.error(" delete hotelSku failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:获取房间的放盘价格
     */
    @PostMapping("/skulistprice")
    public ResponseBase getSkuPriceBySkuId(@RequestBody Map<String, String> map) {
        ResponseBase responseBase = new ResponseBase();
        String skuId = map.get("skuId");
        try {
            Map dailyMap = new HashMap();
            List dailyPricelist = service.getSkuPriceBySkuId(skuId);
            if (null == dailyPricelist) {
                dailyPricelist = new ArrayList();
            }
            dailyMap.put("dailyPrice", dailyPricelist);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dailyMap);
        } catch (Exception e) {
            logger.error(e.toString());
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }

    /**
     * @Description:单独获取价格押金
     */
    @PostMapping("/getpricebond")
    public ResponseBase<PriceBondDto> getPriceAndBond(@RequestBody Map<String, String> map) {
        String skuId = map.get("skuId");
        ResponseBase<PriceBondDto> responseBase = new ResponseBase();
        try {
            PriceBondDto dto = service.getPriceAndBond(skuId);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dto);
        } catch (Exception e) {
            logger.error(e.toString());
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }


    /**
     * @Description:删除放盘价格
     */
    @PostMapping("/deleteskuprice")
    public ResponseBase<String> deleteSkuPrice(@RequestBody DailyPriceQuery query) {
        ResponseBase<String> responseBase = new ResponseBase<>();
        try {
            service.deleteSkuPrice(query);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setMessage("SUCCESS");
        } catch (Exception e) {
            logger.error(" delete hotelSku failed!", e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }


}
