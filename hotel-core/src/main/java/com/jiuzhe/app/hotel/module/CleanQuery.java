package com.jiuzhe.app.hotel.module;

import com.jiuzhe.app.hotel.utils.StringUtil;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description:录入清洁信息
 */
public class CleanQuery {
    private String id = StringUtil.get32UUID();
    @NotNull
    private String merchantId;
    @NotNull
    private String hrId;
    @NotNull
    private String skuId;

    private String cleannerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getHrId() {
        return hrId;
    }

    public void setHrId(String hrId) {
        this.hrId = hrId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }


    public String getCleannerName() {
        return cleannerName;
    }

    public void setCleannerName(String cleannerName) {
        this.cleannerName = cleannerName;
    }
}
