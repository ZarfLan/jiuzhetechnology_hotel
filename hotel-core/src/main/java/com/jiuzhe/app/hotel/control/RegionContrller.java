package com.jiuzhe.app.hotel.control;

import com.jiuzhe.app.hotel.constants.CommonConstant;
import com.jiuzhe.app.hotel.dto.HotelRegionDto;
import com.jiuzhe.app.hotel.dto.ResponseBase;
import com.jiuzhe.app.hotel.entity.HotelRegion;
import com.jiuzhe.app.hotel.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 */
@Api(tags = "城市区域")
@RestController
@RequestMapping("/region")
public class RegionContrller {

    private static final Logger logger = LoggerFactory.getLogger(RegionContrller.class);

    @Autowired
    RegionService service;

    @ApiOperation(value = "获取所有的城市区域", notes = "test:武汉,洪山区")
    @GetMapping
    public ResponseBase<List<HotelRegionDto>> getAllRegion() {
        ResponseBase<List<HotelRegionDto>> responseBase = new ResponseBase<>();
        try {
            List<HotelRegion> hotelRegions = service.getAllRegion();
            List<HotelRegionDto> dtos =  hotelRegions.stream()
                    .map(hotelRegion -> HotelRegionDto.make(hotelRegion))
                    .collect(Collectors.toList());
            responseBase.setStatus(CommonConstant.SUCCESS);
            responseBase.setData(dtos);
        }catch (Exception e) {
            logger.error("getAllRegion failed",e);
            responseBase.setStatus(CommonConstant.FAIL);
        }
        return responseBase;
    }
}
