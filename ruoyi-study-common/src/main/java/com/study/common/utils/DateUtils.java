package com.study.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 * <p>
 * 注意日期时间和日期和时间的分别
 *
 * @author fjding
 * @date 2021/9/4
 */
public class DateUtils {

    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String HH_MM_SS = "HH:mm:ss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd" , "yyyy-MM-dd HH:mm:ss" , "yyyy-MM-dd HH:mm" , "yyyy-MM" ,
            "yyyy/MM/dd" , "yyyy/MM/dd HH:mm:ss" , "yyyy/MM/dd HH:mm" , "yyyy/MM" ,
            "yyyy.MM.dd" , "yyyy.MM.dd HH:mm:ss" , "yyyy.MM.dd HH:mm" , "yyyy.MM"};

    /**
     * 获得当前日期时间
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获得当前日期时间Str
     *
     * @return
     */
    public static String getNowStr() {
        return getNowStr(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获得当前日期Str
     *
     * @return
     */
    public static String getNowDateStr() {
        return getNowStr(YYYY_MM_DD);
    }

    /**
     * 获得当前时间Str
     *
     * @return
     */
    public static String getNowTimeStr() {
        return getNowStr(HH_MM_SS);
    }


    /**
     * 获得当前日期格式化的字符串
     *
     * @param format
     * @return
     */
    public static String getNowStr(final String format) {
        return dateToStr(new Date(), format);
    }

    /**
     * 获得格式化后的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(final Date date, final String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 解析日期字符串
     *
     * @param str
     * @param format
     * @return
     */
    public static Date strToDate(final String str, final String format) {
        try {
            return new SimpleDateFormat(format).parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析日期字符串
     *
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str, parsePatterns);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得当前日期的日期路径 如2018/08/08
     *
     * @return
     */
    public static String datePath() {
        return datePath(getNow());
    }

    /**
     * 获得日期路径 如2018/08/08
     *
     * @param date
     * @return
     */
    public static String datePath(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }

    /**
     * 获得服务器启动时间
     *
     * @return
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 获得服务器运行的毫秒数
     *
     * @return
     */
    public static long getServerRunTime() {
        return ManagementFactory.getRuntimeMXBean().getUptime();
    }


    /**
     * 计算两个时间差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDatePoor(Date startDate, Date endDate) {
        // 一天的毫秒数
        long nd = 1000 * 60 * 60 * 24;
        // 一小时毫秒数
        long hd = 1000 * 60 * 60;
        // 一分钟毫秒数
        long md = 1000 * 60;
        long sd = 1000;
        // 计算两个时间的毫秒差
        long diff = endDate.getTime() - startDate.getTime();
        long day = diff / nd;
        long hour = diff % nd / hd;
        long min = diff % nd % hd / md;
        long sec = diff % nd % hd % md /sd;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

}
