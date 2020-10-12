package com.jiuzhe.app.hotel.constants;

public enum ProgressEnum {
    BASIC("基本信息", 1), IMG("照片", 2);

    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private ProgressEnum(String desc, int index) {
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
