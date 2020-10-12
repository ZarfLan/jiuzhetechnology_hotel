package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:获取推荐房的参数
 */
@ApiModel(value = "RecommetQuery", description = "RecommetQuery")
public class RecommetQuery {

    @ApiModelProperty(value = "城市名", example = "武汉市")
    private String cityName;
    @ApiModelProperty(value = "区域名", example = "洪山区")
    private String area;
    @ApiModelProperty(value = "需要多少条数据", example = "10")
    private Integer num;
    @ApiModelProperty(value = "评分依据", example = "0:weight;1:score")
    private Integer gist;

    public Integer getGist() {
        return gist;
    }

    public void setGist(Integer gist) {
        this.gist = gist;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
