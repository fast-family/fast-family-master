package com.fast.family.commons.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/13-10:17
 */
public class DateTimeUtils {

    private static final DateTimeFormatter DATE_TIME_FORMAT = TimeFormat.YYYY_MM_DD_HH_MM_SS.formatter;

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String now() {
        return LocalDateTime.now().format(DATE_TIME_FORMAT);
    }

    /**
     * 获取当前时间
     *
     * @param pattern 日期格式
     * @return
     */
    public static String now(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     *
     * @param timeFormat
     * @return
     */
    public static String now(TimeFormat timeFormat) {
        return LocalDateTime.now().format(timeFormat.formatter);
    }

    /**
     * 转换对应时间
     *
     * @param localDateTime
     * @param timeFormat
     * @return
     */
    public static String now(LocalDateTime localDateTime, TimeFormat timeFormat) {
        return localDateTime.format(timeFormat.formatter);
    }


    /**
     * 转换对应的时间
     *
     * @param localDate
     * @param timeFormat
     * @return
     */
    public static String now(LocalDate localDate, TimeFormat timeFormat) {
        return localDate.format(timeFormat.formatter);
    }


    /**
     * 字符串转换日期类型
     *
     * @return
     */
    public static LocalDateTime parserDateTime() {
        return parserDateTime(now());
    }


    /**
     * 字符串转化日期类型
     *
     * @param timeStr 日期字符串
     * @return
     */
    public static LocalDateTime parserDateTime(String timeStr) {
        return LocalDateTime.parse(timeStr, DATE_TIME_FORMAT);
    }

    /**
     * 字符串转换日期类型
     *
     * @param timeStr    日期字符串
     * @param timeFormat 日期格式
     * @return
     */
    public static LocalDateTime parserDateTime(String timeStr, TimeFormat timeFormat) {
        return LocalDateTime.parse(timeStr, timeFormat.formatter);
    }

    /**
     * 字符串转换日期
     *
     * @param timeStr    日期字符串
     * @param timeFormat 日期格式
     * @return
     */
    public static LocalDate parserDate(String timeStr, TimeFormat timeFormat) {
        return LocalDate.parse(timeStr, timeFormat.formatter);
    }


    /**
     * 获取当前日期
     *
     * @return
     */
    public static LocalDate convertLDTToLocalDate() {
        return LocalDateTime.now().toLocalDate();
    }

    /**
     * 时间戳转换LocalDate
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static LocalDate convertLDTToLocalDate(long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 时间戳转换LocalDateTime
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static LocalDateTime convertLDTToLocalDateTime(long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
    }


    /**
     * LocalDateTime类型转换Date类型
     *
     * @return
     */
    public static Date convertLDTToDate() {
        return Date.from(parserDateTime().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime类型转换Date类型
     *
     * @return
     */
    public static Date convertLDTToDate(String dateTime) {
        return Date.from(parserDateTime(dateTime).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * long转换类型Date
     *
     * @param dateTime
     * @return
     */
    public static Date convertLDTToDate(Long dateTime) {
        return Date.from(convertLDTToLocalDateTime(dateTime).atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * long转换类型date格式字符串
     * @param dateTime
     * @return
     */
    public static String convertTimeToString(Long dateTime, TimeFormat timeFormat){
        return now(convertLDTToLocalDateTime(dateTime),timeFormat);
    }


    /**
     * LocalDateTime类型转换Date类型
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate类型转换Date类型
     *
     * @param date
     * @return
     */
    public static Date convertLDTToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前时间LONG类型
     *
     * @return
     */
    public static Long convertLDTToLong() {
        return convertLDTToDate().getTime();
    }


    /**
     * 获取当前时间LONG类型
     *
     * @param dateTime 时间字符串
     * @return
     */
    public static Long convertLDTToLong(String dateTime) {
        return convertLDTToDate(dateTime).getTime();
    }

    /**
     * 转换LocalDateTime为时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Long convertLDTToLong(LocalDateTime localDateTime) {
        String dateTime = DateTimeUtils.now(localDateTime, TimeFormat.YYYY_MM_DD_HH_MM_SS);
        Long dateTimeLong = DateTimeUtils.convertLDTToLong(dateTime);
        return dateTimeLong;
    }

    /**
     * 转换LocalDateTime为时间戳
     *
     * @param localDateTime
     * @param timeFormat    时间格式
     * @return
     */
    public static Long convertLDTToLong(LocalDateTime localDateTime, TimeFormat timeFormat) {
        String dateTime = DateTimeUtils.now(localDateTime, timeFormat);
        Long dateTimeLong = DateTimeUtils.convertLDTToLong(dateTime);
        return dateTimeLong;
    }

    /**
     * 获取某个小时的时间戳
     *
     * @param withHour DateTimeUtils.withHourToLong(14) 两点
     * @return 1511330400000
     */
    public static Long withTimePointToLong(int withHour) {
        LocalDateTime localDateTime = DateTimeUtils.parserDateTime()
                .withHour(withHour).withMinute(0).withSecond(0);
        return convertLDTToLong(localDateTime);
    }

    /**
     * 获取多少天、小时后的时间戳
     *
     * @param days  多少天后
     * @param hours 小时
     * @return
     */
    public static Long plusTimePointToLong(long days, long hours) {
        LocalDateTime localDateTime = DateTimeUtils.parserDateTime()
                .plusDays(days).withHour(0).withMinute(0).withSecond(0).plusHours(hours);
        return convertLDTToLong(localDateTime);

    }

    /**
     * 获取某天多少个小时后的时间戳
     *
     * @param localDateTime 天
     * @param hours         小时
     * @return
     */
    public static Long plusTimePointToLong(LocalDateTime localDateTime, long hours) {
        return convertLDTToLong(localDateTime.withHour(0).withMinute(0).withSecond(0).plusHours(hours));
    }

    /**
     * 获取多少天之前时间戳
     *
     * @param days  天
     * @param hours 小时
     * @return
     */
    public static Long minusTimePointToLong(int days, int hours) {
        LocalDateTime localDateTime = DateTimeUtils.parserDateTime()
                .minusDays(days).withHour(0).withMinute(0).withSecond(0).plusHours(hours);
        return convertLDTToLong(localDateTime);

    }

    /**
     * 字符串转时间戳
     *
     * @param date
     * @return
     */
    public static Long parserTimestamp(String date) {
        LocalDateTime localDateTime = parserDateTime(date);
        return convertLDTToLong(localDateTime);
    }


    /**
     * 获取每月第一天
     *
     * @return
     */
    public static LocalDate lastDay(LocalDate toDay) {
        return LocalDate.of(toDay.getYear(), toDay.getMonth(), 1);
    }

    /**
     * 获取每月最后一天
     *
     * @return
     */
    public static LocalDate firstDay(LocalDate toDay) {
        return toDay.with(TemporalAdjusters.lastDayOfMonth());
    }


    public enum TimeFormat {
        MM_DD("MM-dd"),
        YYYY_MM("yyyy-MM"),
        YYYY_MM_DD("yyyy-MM-dd"),
        MM_DD_HH_MM("MM-dd HH:mm"),
        MM_DD_HH_MM_SS("MM-dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

        MM_DD_EN("MM/dd"),
        YYYY_MM_EN("yyyy/MM"),
        YYYY_MM_DD_EN("yyyy/MM/dd"),
        MM_DD_HH_MM_EN("MM/dd HH:mm"),
        MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm"),
        YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss"),

        YYYYMMDDHHMMSS_EN("yyyyMMddHHmmss"),
        YYYYMMDDHHMMSSSSS_EN("yyyyMMddHHmmssSSS"),
        YYMMDDHHMM_EN("yyMMddHHmm"),

        MM_DD_CN("MM月dd日"),
        YYYY_MM_CN("yyyy年MM月"),
        YYYY_MM_DD_CN("yyyy年MM月dd日"),
        MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
        MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss"),
        YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
        YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss"),

        YYYYMMDD("yyyyMMdd"),

        HH_MM("HH:mm"),
        HH_MM_SS("HH:mm:ss");


        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

    }
}
