package com.jiuzhe.app.hotel.dto;

import com.google.common.base.Splitter;
import com.jiuzhe.app.hotel.entity.HotelRegion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:默认地址的实体类
 */
@ApiModel(value = "地址的模型")
public class HotelRegionDto {

    /**
     * 地址id
     */
    @ApiModelProperty(value = "城市id",example = "1")
    private String id;
    /**
     * 城市名
     */
    @ApiModelProperty(value = "城市名称",example = "包头")
    private String cityName;
    /**
     * 区域集合
     */
    @ApiModelProperty(value = "区域名称",example = "[昆都仑区,东河区,青山区]")
    private List<String> areaList;

    public static HotelRegionDto make(HotelRegion hotelRegion) {
        HotelRegionDto dto = new HotelRegionDto();
        List<String> areaList = new ArrayList<>();
        areaList = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(hotelRegion.getAreas());
        dto.setId(hotelRegion.getId());
        dto.setCityName(hotelRegion.getCityName());
        dto.setAreaList(areaList);
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<String> areaList) {
        this.areaList = areaList;
    }
}
