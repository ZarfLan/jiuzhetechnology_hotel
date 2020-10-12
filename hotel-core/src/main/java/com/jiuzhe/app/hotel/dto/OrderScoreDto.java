package com.jiuzhe.app.hotel.dto;

/**
 * @Description:订单评分
 */
public class OrderScoreDto {

    private String skuName;

    private String roomNo;

    private String orderId;

    private String pic;

    private String StartDate;

    private String endDate;

    private String layoutName;

    private Integer price;

    private Integer score;

    private String address;

    private String name;

    private String phone;

    private String occupant_id_card;

    //酒店评分
    private Integer skuScore;
    //卫生评分
    private Integer cleanScore;
    //房间问题
    private String  skuProblem;
    //卫生问题
    private String cleanProblem;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupant_id_card() {
        return occupant_id_card;
    }

    public void setOccupant_id_card(String occupant_id_card) {
        this.occupant_id_card = occupant_id_card;
    }

    public Integer getSkuScore() {
        return skuScore;
    }

    public void setSkuScore(Integer skuScore) {
        this.skuScore = skuScore;
    }

    public Integer getCleanScore() {
        return cleanScore;
    }

    public void setCleanScore(Integer cleanScore) {
        this.cleanScore = cleanScore;
    }

    public String getSkuProblem() {
        return skuProblem;
    }

    public void setSkuProblem(String skuProblem) {
        this.skuProblem = skuProblem;
    }

    public String getCleanProblem() {
        return cleanProblem;
    }

    public void setCleanProblem(String cleanProblem) {
        this.cleanProblem = cleanProblem;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
