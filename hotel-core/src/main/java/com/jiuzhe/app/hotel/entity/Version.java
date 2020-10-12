package com.jiuzhe.app.hotel.entity;

public class Version {
    /**
     * id
     */
    private Integer id;
    /**
     * 设备类型: 1:IOS; 2:安装
     */
    private Integer devType;
    /**
     * 内部版本号
     */
    private Long innerVer;
    /**
     * 对外版本号
     */
    private String outVer;
    /**
     * 更新内容
     */
    private String context;
    /**
     * 地址
     */
    private String appUrl;
    /**
     * 是否强制更新
     */
    private Boolean isForce;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }

    public Long getInnerVer() {
        return innerVer;
    }

    public void setInnerVer(Long innerVer) {
        this.innerVer = innerVer;
    }

    public String getOutVer() {
        return outVer;
    }

    public void setOutVer(String outVer) {
        this.outVer = outVer;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Boolean getIsForce() {
        return isForce;
    }

    public void setIsForce(Boolean isForce) {
        this.isForce = isForce;
    }
}
