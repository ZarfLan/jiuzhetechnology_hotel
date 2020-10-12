package com.jiuzhe.app.hotel.entity;

import com.jiuzhe.app.hotel.constants.OrderStatusEnum;
import com.jiuzhe.app.hotel.module.HotelOrderQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description:酒店订单模型
 */
public class HotelOrder {
    /**
     * id
     */
    private String id;
    /**
     * 版本
     */
    private Integer version = 1;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 修改人
     */
    private String editor;
    /**
     * 修改时间
     */
    private LocalDateTime editTime = LocalDateTime.now();
    /**
     * 备注
     */
    private String remark;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 房间id
     */
    private String skuId;
    /**
     * 房间名称
     */
    private String skuName;
    /**
     * 房间地址
     */
    private String address;
    /**
     * 房间总价
     */
    private Integer skuPrice;
    /**
     * 房间定金
     */
    private Integer skuBond;
    /**
     * 入住日期
     */
    private LocalDate startDate;
    /**
     * 退房日期
     */
    private LocalDate endDate;
    /**
     * 商户id
     */
    private String merchantId;
    /**
     * 入住人姓名
     */
    private String occupantName;
    /**
     * 入住人身份证
     */
    private String occupantCard;
    /**
     * 入住人电话
     */
    private String occupantPhone;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 支付过期时间
     */
    private LocalDateTime paiedExpired;

    /**
     * 订单取消时间点
     */
    private LocalDateTime paidCancelTime;

    /**
     * 入住天数
     */
    private Integer dayNum;

    private String roomNo;

    private Integer roomStatus;

    private Integer platformFee;

    private Integer score;

    private String skuImg;

    private Integer vipLevel;

    private String imgurls;

    private String storeName;

    private String piclist;

    private Integer onLine;

    public static HotelOrder make(HotelOrderQuery query) {
        HotelOrder order = new HotelOrder();
        order.setVipLevel(query.getVipLevel());
        order.setDayNum(query.getDayNum());
        order.setId(query.getId());
        order.setUserId(query.getUserId());
        order.setSkuId(query.getSkuId());
        order.setSkuPrice(query.getSkuPrice());
        order.setSkuBond(query.getSkuBond());
        order.setStartDate(query.getStartDate());
        order.setEndDate(query.getEndDate());
        order.setOccupantName(query.getOccupantName());
        order.setOccupantCard(query.getOccupantCard());
        order.setOccupantPhone(query.getOccupantPhone());
        if (null == query.getOnLine() || 1 == query.getOnLine()) {
            order.setStatus(OrderStatusEnum.UNPAID.getIndex());
        }
        if (null != query.getOnLine() && 0 == query.getOnLine()) {
            if (query.getStartDate().equals(LocalDate.now())) {
                order.setStatus(OrderStatusEnum.LIVED.getIndex());
            } else {
                order.setStatus(OrderStatusEnum.PAID.getIndex());
            }
        }
        // 填充通用信息
        order.setCreater(query.getUserId());
        order.setEditor(query.getUserId());
        if (null == query.getOnLine() || 1 == query.getOnLine()) {
            order.setOnLine(1);
        } else {
            order.setOnLine(0);
        }
        return order;
    }

    public Integer getOnLine() {
        return onLine;
    }

    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPiclist() {
        return piclist;
    }

    public void setPiclist(String piclist) {
        this.piclist = piclist;
    }

    public String getImgurls() {
        return imgurls;
    }

    public void setImgurls(String imgurls) {
        this.imgurls = imgurls;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Integer platformFee) {
        this.platformFee = platformFee;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public LocalDateTime getPaidCancelTime() {
        return paidCancelTime;
    }

    public void setPaidCancelTime(LocalDateTime paidCancelTime) {
        this.paidCancelTime = paidCancelTime;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Integer skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getSkuBond() {
        return skuBond;
    }

    public void setSkuBond(Integer skuBond) {
        this.skuBond = skuBond;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getOccupantCard() {
        return occupantCard;
    }

    public void setOccupantCard(String occupantCard) {
        this.occupantCard = occupantCard;
    }

    public String getOccupantPhone() {
        return occupantPhone;
    }

    public void setOccupantPhone(String occupantPhone) {
        this.occupantPhone = occupantPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getPaiedExpired() {
        return paiedExpired;
    }

    public void setPaiedExpired(LocalDateTime paiedExpired) {
        this.paiedExpired = paiedExpired;
    }
}
