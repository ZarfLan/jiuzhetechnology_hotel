package com.jiuzhe.app.hotel.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description:每天状态的实体类
 */
public class SkuDailyStatus {

    /**
     * 房间id
     */
    private String skuId;

    /**
     * 时间
     */
    private LocalDate specDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 订单超超时时间
     */
    private LocalDateTime expired;

    /**
     * 解锁人
     */
    private String lockedby;

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public LocalDate getSpecDate() {
        return specDate;
    }

    public void setSpecDate(LocalDate specDate) {
        this.specDate = specDate;
    }
}
