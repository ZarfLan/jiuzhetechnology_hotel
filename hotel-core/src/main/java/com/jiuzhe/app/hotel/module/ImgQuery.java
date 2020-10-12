package com.jiuzhe.app.hotel.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @Description：照片的请求参数
 */
@ApiModel(value = "ImgQuery", description = "添加照片")
public class ImgQuery {

    @ApiModelProperty(value = "酒店id", example = "9999")
    private String skuId;
    @ApiModelProperty(value = "照片集合", example = "{12,2}")
    private List<String> imgUrlList;
    @ApiModelProperty(value = "照片", example = "123")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }
}
