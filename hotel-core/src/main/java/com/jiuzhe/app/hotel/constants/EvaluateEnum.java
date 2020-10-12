package com.jiuzhe.app.hotel.constants;

public enum EvaluateEnum {
    KTWT("空调问题",1),SSWT("设施问题",2),CSWT("厕所问题",3),WIFI("wifi问题",4),CGBH("采光问题",5),SSBQ("设施不全",6),
    QMZ("墙面脏",7),CDYW("床单问题",8),FXTL("发现螳螂",9),FJYW("房间异味",10),SYYW("水有异味",11),TFCL("头发残留",12);
    // 成员变量
    private String desc;
    private int index;

    // 构造方法
    private EvaluateEnum(String desc, int index) {
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

}
