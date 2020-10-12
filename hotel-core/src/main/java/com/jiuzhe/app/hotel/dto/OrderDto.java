package com.jiuzhe.app.hotel.dto;

import com.jiuzhe.app.hotel.entity.HotelOrder;
import com.jiuzhe.app.hotel.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value="订单模型",description="订单模型DTO")
public class OrderDto implements Serializable {

    private static final long serialVersionUID = -6294998792341256146L;
    @ApiModelProperty(value="订单id",example="87d9fe4238f74d84be15d62984fdfe65")
    private String id;
    @ApiModelProperty(value="用户id",example="87d9fe4238f74d84be15d62984fdfe65")
    private String userId;
    @ApiModelProperty(value="房间id",example="87d9fe4238f74d84be15d62984fdfe66")
    private String skuId;
    @ApiModelProperty(value="房间总价",example="5096")
    private Integer skuPrice;
    @ApiModelProperty(value="押金",example="200")
    private Integer skuBond;
    @ApiModelProperty(value="开始日期",example="2018-05-01")
    private String startDate;
    @ApiModelProperty(value="结束日期",example="2018-05-03")
    private String endDate;
    @ApiModelProperty(value="入住人姓名",example="张三")
    private String occupantName;
    @ApiModelProperty(value="入住人身份证",example="420104***********************")
    private String occupantCard;
    @ApiModelProperty(value="入住人电话",example="13711111111")
    private String occupantPhone;
    @ApiModelProperty(value="房间状态",example="4")
    private Integer roomStatus;
    @ApiModelProperty(value="房间名称",example="*****")
    private String skuName;
    @ApiModelProperty(value="房间地址",example="湖北省***")
    private String address;
    @ApiModelProperty(value="商户id",example="12345")
    private String merchantId;
    @ApiModelProperty(value="订单状态",example="2")
    private Integer orderStatus;
    @ApiModelProperty(value="创建时间",example=" ")
    private String createTime;
    @ApiModelProperty(value="入住时间",example="2")
    private Integer dayNum;
    @ApiModelProperty(value="平台费",example="2")
    private Integer platformFee;
    @ApiModelProperty(value="分数",example="2")
    private Integer score;

    private String roomNo;

    private String skuImg;
    
   private Integer onLine;

    public Integer getOnLine() {
        return onLine;
    }

    public void setOnLine(Integer onLine) {
        this.onLine = onLine;
    }



    /**
     * 本来叫roomStatus，但是此字段已经被使用，不好改
     */
    private Integer statusForDurty;


    public static OrderDto make(HotelOrder order) {
        if (null == order) {
            return null ;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        OrderDto dto = new OrderDto();
        dto.setDayNum(order.getDayNum());
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setSkuId(order.getSkuId());
        dto.setSkuPrice(order.getSkuPrice());
        dto.setSkuBond(order.getSkuBond());
        dto.setStartDate(order.getStartDate().toString());
        dto.setEndDate(order.getEndDate().toString());
        dto.setOccupantName(order.getOccupantName());
        dto.setOccupantCard(order.getOccupantCard());
        dto.setOccupantPhone(order.getOccupantPhone());
        dto.setRoomStatus(order.getStatus());
        dto.setOnLine(order.getOnLine());
        //名字没有用门店的
        if (null != order.getStoreName()) {
            dto.setSkuName(order.getStoreName());
        }
        if (null != order.getSkuName()) {
            dto.setSkuName(order.getSkuName());
        }
        dto.setAddress(order.getAddress());
        dto.setMerchantId(order.getMerchantId());
        dto.setOrderStatus(order.getStatus());
        dto.setCreateTime(df.format(order.getCreateTime()));
        dto.setStatusForDurty(order.getRoomStatus());
        dto.setPlatformFee(order.getPlatformFee());
        dto.setScore(order.getScore());
        dto.setSkuImg(order.getSkuImg());
        dto.setRoomNo(order.getRoomNo());
        //没有用门店的
        if (null !=order.getPiclist()) {
            List<String> imgs = StringUtil.stringToList(order.getPiclist());
            dto.setSkuImg(imgs.get(0));
        }
        if (null != order.getImgurls()) {
            List<String> imgs = StringUtil.stringToList(order.getImgurls());
            dto.setSkuImg(imgs.get(0));
        }
        return dto;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public Integer getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(Integer platformFee) {
        this.platformFee = platformFee;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


    public Integer getStatusForDurty() {
        return statusForDurty;
    }

    public void setStatusForDurty(Integer statusForDurty) {
        this.statusForDurty = statusForDurty;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
