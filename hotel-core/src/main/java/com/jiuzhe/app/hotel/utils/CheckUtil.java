package com.jiuzhe.app.hotel.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @Description:校验相关的工具类
 */
public class CheckUtil {

    /**
     * @Description:手机号码的校验
     */
    public static boolean isPhone(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = compile("^[1][3,4,5,7,8][0-9]{9}$");
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * @Description:判断日期大小a<b返回true
     */
    public static Boolean compare_date(String a, String b) {
        Boolean check = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(a);
            Date dt2 = df.parse(b);
            if (dt1.getTime() < dt2.getTime()) {
                check = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return check;
    }

    /**
     * @Description:比较两个数的大小b>a返回true
     */
    public static Boolean compare_price(Integer a, Integer b) {
        Boolean check = false;
        if (b > a) {
            check = true;
        }
        return check;
    }

    /**
     * @Description:和当前时间比较，大则返回true,小的话返回false
     */
    public static Boolean checkWithNow(LocalDateTime localDateTime) {
        Duration duration = Duration.between(LocalDateTime.now(), localDateTime);
        if (duration.toMillis() < 0) {
            return false;
        } else {
            return true;
        }
    }

}
