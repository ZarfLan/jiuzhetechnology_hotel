package com.jiuzhe.app.hotel.entity;

import com.jiuzhe.app.hotel.dto.UserDto;

public class UserEntity {
    private Integer status;
    private String message;
    private UserDto data;

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

    public UserDto getData() {
        return data;
    }

    public void setData(UserDto data) {
        this.data = data;
    }
}
