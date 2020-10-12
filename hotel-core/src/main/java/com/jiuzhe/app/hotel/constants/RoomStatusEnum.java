package com.jiuzhe.app.hotel.constants;

public enum RoomStatusEnum {
    ON_SALE("可预定房", 1), SALED("已售出房", 2), DIRTY("脏房", 3);
    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private RoomStatusEnum(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
