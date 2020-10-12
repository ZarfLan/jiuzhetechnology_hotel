package com.jiuzhe.app.hotel.constants;

import java.util.*;

public class rtCodeConstant {

    public final static Map<String, String> rtcodeMessage = new HashMap<String, String>() {
        {
            put("-1", "服务器异常");
            put("0", "成功");
            put("1", "参数错误");
            put("2", "数据已存在");
            put("3", "房间不存在");
            put("4", "预订时间错误");
            put("5", "消息队列服务异常");
        }
    };

    public static Map getResult(String rtcode, String... values) {
        Map result = new HashMap();
        result.put("status", rtcode);
        result.put("message", rtcodeMessage.get(rtcode));
        List data = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            data.add(values[i]);
        }
        result.put("data",data);
        return result;
    }

    public static Map getResult(String rtcode, List value) {
        Map result = new HashMap();
        result.put("status", rtcode);
        result.put("message", rtcodeMessage.get(rtcode));
        result.put("data",value);
        return result;
    }

    public static Map getResult(String rtcode, String key, String value, List data) {
        Map result = new HashMap();
        result.put("status", rtcode);
        result.put("message", rtcodeMessage.get(rtcode));
        result.put(key,value);
        result.put("data",data);
        return result;
    }
}  