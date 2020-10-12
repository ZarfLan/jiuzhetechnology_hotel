package com.jiuzhe.app.hotel.utils;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StringUtil {
    /**
     * 获取去掉横线的长度为32的UUID串.
     *
     * @return uuid.
     * @author WuShuicheng.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description:判定String是否是null或者""
     * @author:郑鹏宇
     * @date:2018/3/30
     */
    public static boolean isEmptyOrNull(String str) {
        return null == str || str.isEmpty();
    }

    public static String strArray2str(String[] strarray) {
        if (null == strarray) {
            return null;
        }
        String rs = "";
        for (int i = 0; i < strarray.length; i++) {
            if (i == strarray.length - 1) {
                rs += strarray[i];
                break;
            }
            rs += strarray[i] + ",";
        }

        return rs;
    }

    /**
     * @Description:将按照“，”切割的String转化为list
     * @author: 郑鹏宇
     * @date 2018/8/11/011
     */
    public static List<String> stringToList(String str) {
        if (StringUtil.isEmptyOrNull(str)) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        list = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
        System.out.println(list);
        String a = list.get(0);
        return list;
    }

    /**
     * @Description:将["a","b","c"]变成（'a','b'）
     * @author: 郑鹏宇
     * @date 2018/8/13/013
     */
    public static String ListToSqlStr(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("'"+list.get(i)+"'").append(",");
        }
        String a = list.isEmpty()?"":sb.toString().substring(0, sb.toString().length() - 1);
        return "("+a+")";
    }
}
