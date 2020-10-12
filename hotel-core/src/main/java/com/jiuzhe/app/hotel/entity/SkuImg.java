package com.jiuzhe.app.hotel.entity;

import com.jiuzhe.app.hotel.module.ImgQuery;
import com.jiuzhe.app.hotel.module.SkuQuery;
import com.jiuzhe.app.hotel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:酒店图片
 */
public class SkuImg {
    /**
     * id
     */
    private String id = StringUtil.get32UUID();
    /**
     * 酒店id
     */
    private String skuId;
    /**
     * 图片地址
     */
    private String imgUrl;

    //将地址的url集合转化为skuImg的集合
    public static List<SkuImg> make(ImgQuery query) {
        List<SkuImg> skuImgs = new ArrayList<>();
        for (String str : query.getImgUrlList()) {
            SkuImg skuImg = new SkuImg();
            skuImg.setImgUrl(str);
            skuImg.setSkuId(query.getSkuId());
            skuImgs.add(skuImg);
        }
        return skuImgs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
