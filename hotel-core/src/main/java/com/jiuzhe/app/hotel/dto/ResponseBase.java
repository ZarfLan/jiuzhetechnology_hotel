package com.jiuzhe.app.hotel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="基本返回对象",description="请求返回基本对象ResponseInfo")
public class ResponseBase<T> {
    @ApiModelProperty(value="狀態值，0表示成功，1标识成功",example="0")
    private Integer status;
    @ApiModelProperty(value="错误描述",example="31943")
    private String message;
    @ApiModelProperty(value="返回数据")
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
