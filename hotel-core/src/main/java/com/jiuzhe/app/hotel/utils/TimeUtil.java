package com.jiuzhe.app.hotel.utils;

import com.aliyun.opensearch.sdk.dependencies.com.google.common.base.Joiner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Description:时间处理的工具类
 * @author:郑鹏宇
 * @date: 2018/5/31
 */
public class TimeUtil {
    private static Log logger = LogFactory.getLog(TimeUtil.class);

    /**
     * @Description:用于返回周几（一）
     * @author:郑鹏宇
     * @date:2018/5/31
     */
    public static String getDayOfWeek(LocalDate date) {
        String weekDay = String.valueOf(date.getDayOfWeek());
        if (weekDay.equals("SUNDAY")) {
            return "七";
        }
        if (weekDay.equals("MONDAY")) {
            return "一";
        }
        if (weekDay.equals("TUESDAY")) {
            return "二";
        }
        if (weekDay.equals("WEDNESDAY")) {
            return "三";
        }
        if (weekDay.equals("THURSDAY")) {
            return "四";
        }
        if (weekDay.equals("FRIDAY")) {
            return "五";
        }
        if (weekDay.equals("SATURDAY")) {
            return "六";
        }
        return null;
    }

    /**
     * @Description:获取月份-日（5-3）
     * @author:郑鹏宇
     * @date:2018/5/31
     */
    public static String getMonthDay(LocalDate date) {
        Integer day = date.getDayOfMonth();
        Integer month = date.getMonthValue();
        return month + "-" + day;
    }

    /**
     * @Description:获取两个日期之间的所有日期（包括首尾）
     * @author:郑鹏宇
     * @date:2018/5/31
     */
    public static List<LocalDate> getBetweenDateAll(LocalDate begin, LocalDate end) {
        Period p = Period.between(begin, end);
        List<LocalDate> dates = new ArrayList<>();
        int dayNum = p.getDays();
        for (int index = 0; index <= dayNum; index++) {
            dates.add(begin.plusDays(index));
        }
        return dates;
    }

    /**
     * @Description:判定日期是否在规定的范围内
     * @author:郑鹏宇
     * @date:2018/5/31
     */
    public static boolean isBetweenDate(LocalDate date, LocalDate begin, LocalDate end) {
        long p1 = ChronoUnit.DAYS.between(begin, end);
        long p2 = ChronoUnit.DAYS.between(begin, date);
        if (p2 < 0 || p1 - p2 < 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @Description:String ===>LocalDateTime
     * @author: 郑鹏宇
     * @date 2018/6/27/027
     */
    public static LocalDateTime StringToLocalDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(time, df);
        return ldt;
    }

    /**
     * @Description:判定两个日期之间的天数
     * @author: 郑鹏宇
     * @date 2018/8/14/014
     */
    public static int daysBetween(String begin, String end) {
        LocalDate beginDt = LocalDate.parse(begin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDt = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return (int) ChronoUnit.DAYS.between(beginDt, endDt);
    }

    public static String getNextDay(String begin, int days) {
        LocalDate beginDt = LocalDate.parse(begin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate returnDt = beginDt.plusDays(days);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return returnDt.format(f);
    }
}
