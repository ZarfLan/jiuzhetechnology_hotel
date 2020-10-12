package com.jiuzhe.app.hotel.dao;

import javax.validation.constraints.NotNull;

public class SkuLayoutDao {
    @NotNull
    String store_id;

    @NotNull
    String name;

    int wifi;
    int bedroom;
    int bed;
    int toilet;

    @NotNull
    String[] piclist;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getToilet() {
        return toilet;
    }

    public void setToilet(int toilet) {
        this.toilet = toilet;
    }

    public String[] getPiclist() {
        return piclist;
    }

    public void setPiclist(String[] piclist) {
        this.piclist = piclist;
    }
}
