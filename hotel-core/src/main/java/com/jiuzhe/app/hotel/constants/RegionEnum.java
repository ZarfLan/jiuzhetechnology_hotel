package com.jiuzhe.app.hotel.constants;

public enum RegionEnum {
    PROVINCE("省", 1), CITY("市", 2), AREA("区域", 3), PART("地方", 4);

    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private RegionEnum(String desc, int index) {
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
