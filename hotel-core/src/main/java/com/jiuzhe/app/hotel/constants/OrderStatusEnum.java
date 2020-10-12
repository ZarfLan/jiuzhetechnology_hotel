package com.jiuzhe.app.hotel.constants;

public enum OrderStatusEnum {
    UNPAID("未付款", 1), EXPRIED("支付超时", 2), PAID("已付款", 3), CANCEL("取消订单", 4),
    LIVED("已入住", 5),APPLY("退押金申请中",6),APPLYSUCCESS("退押金申请通过",7), END("订单完成", 8);

    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private OrderStatusEnum(String desc, int index) {
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
