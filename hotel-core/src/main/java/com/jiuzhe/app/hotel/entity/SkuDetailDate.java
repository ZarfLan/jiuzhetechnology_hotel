package com.jiuzhe.app.hotel.entity;

import java.time.LocalDate;

public class SkuDetailDate {
    /**
     * 价格(单价)
     */
    private Integer listingPrice;
    /**
     * 每天的日期
     */
    private LocalDate listDate;

    public Integer getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Integer listingPrice) {
        this.listingPrice = listingPrice;
    }

    public LocalDate getListDate() {
        return listDate;
    }

    public void setListDate(LocalDate listDate) {
        this.listDate = listDate;
    }
}
