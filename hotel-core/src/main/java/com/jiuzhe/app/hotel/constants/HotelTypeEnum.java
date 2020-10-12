package com.jiuzhe.app.hotel.constants;

public enum HotelTypeEnum {
    APARTMENT("公寓", 0), HOTEL("酒店", 1), HOUSE("住宅", 2);

    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private HotelTypeEnum(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    public static String getName(int index) {
        for (HotelTypeEnum c : HotelTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.getDesc();  
            }
        }  
        return null;  
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
