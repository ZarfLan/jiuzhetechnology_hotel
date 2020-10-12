package com.jiuzhe.app.hotel.dto;

import java.util.List;
import java.util.Set;

/**
 * @Description:房态的返回值
 */
public class HotelStateDto {

    /**
     * 返回的房态表头
     */
    List<HotelStateHeadDto> head;
    /**
     * 返回的房态内容
     */
    List<HotelStateBodyDto> body;


    List<HotelStateLeftDto> left;

    public List<HotelStateLeftDto> getLeft() {
        return left;
    }

    public void setLeft(List<HotelStateLeftDto> left) {
        this.left = left;
    }

    public List<HotelStateHeadDto> getHead() {
        return head;
    }

    public void setHead(List<HotelStateHeadDto> head) {
        this.head = head;
    }

    public List<HotelStateBodyDto> getBody() {
        return body;
    }

    public void setBody(List<HotelStateBodyDto> body) {
        this.body = body;
    }
}
