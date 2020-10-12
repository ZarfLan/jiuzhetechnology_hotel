package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dto.HotelStateDto;
import com.jiuzhe.app.hotel.dto.OrderSuccessDto;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.module.HotelStataQuery;
import com.jiuzhe.app.hotel.service.HotelStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:房态的controller
 */
@Api(tags = "房态")
@RestController
@RequestMapping("/hotelstate")
public class HotelStateController {

    private static final Logger logger = LoggerFactory.getLogger(HotelStateController.class);

    @Autowired
    HotelStateService service;

    @ApiOperation(value = "获取房态", notes = "获取房态")
    @PostMapping
    public ResponseBase<HotelStateDto> getHotelState(@RequestBody HotelStataQuery query) {
        ResponseBase<HotelStateDto> responseBase = new ResponseBase<>();
        try {
            HotelStateDto dto = service.getHotelState(query);
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dto);
        } catch (Exception e) {
            logger.error("getHotelState failed!",e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }
}
