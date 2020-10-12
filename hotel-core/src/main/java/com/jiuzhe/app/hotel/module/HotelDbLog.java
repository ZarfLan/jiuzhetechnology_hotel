package com.jiuzhe.app.hotel.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description:数据库操作日志模型
 */
public class HotelDbLog implements Serializable {
    private static final long serialVersionUID = 2960073705455281974L;
    @Autowired
    private Environment env;
    /**
     * 表名
     */
    @JsonProperty("tbl_name")
    private String tblName;
    /**
     * 用户id
     */
    @JsonProperty("ops_id")
    private String opsId;
    /**
     * 数据库修改时间
     */
    @JsonProperty("ops_time")
    private LocalDateTime opsTime;
    /**
     * 修改的数据
     */
    @JsonProperty("ops_data")
    private String opsData;
    /**
     * 说明
     */
    @JsonProperty("ops_msg")
    private String opsMsg;
    /**
     * 数据库操作结果
     */
    @JsonProperty("ops_rst")
    private Boolean opsRst;
    /**
     * 微服务名称
     */
    @JsonProperty("serv_name")
    private String servName;
    /**
     * 微服务IP
     */
    @JsonProperty("serv_ip")
    private String servIp;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getOpsId() {
        return opsId;
    }

    public void setOpsId(String opsId) {
        this.opsId = opsId;
    }

    public LocalDateTime getOpsTime() {
        return opsTime;
    }

    public void setOpsTime(LocalDateTime opsTime) {
        this.opsTime = opsTime;
    }

    public String getOpsData() {
        return opsData;
    }

    public void setOpsData(String opsData) {
        this.opsData = opsData;
    }

    public String getOpsMsg() {
        return opsMsg;
    }

    public void setOpsMsg(String opsMsg) {
        this.opsMsg = opsMsg;
    }

    public Boolean getOpsRst() {
        return opsRst;
    }

    public void setOpsRst(Boolean opsRst) {
        this.opsRst = opsRst;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public String getServIp() {
        return servIp;
    }

    public void setServIp(String servIp) {
        this.servIp = servIp;
    }
}
