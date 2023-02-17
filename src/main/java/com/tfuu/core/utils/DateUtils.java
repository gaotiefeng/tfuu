package com.tfuu.core.utils;

import com.tfuu.core.exception.Inquisitor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Copyright (c) 2006 by BEA Systems.All Rights Reserved<br/>
 *         描述：日期工具类<br/>
 */
@SuppressWarnings("all")
public final class DateUtils {

    /**
     * 描述：格式 年-月-日<br/>
     * yyyy-MM-dd<br/>
     * */
    public static final String defaultFormat = "yyyy-MM-dd";
    /**
     * 描述：格式 年-月-日 小时:分钟:秒<br/>
     * yyyy-MM-dd HH:mm:ss<br/>
     * */
    public static final String defaultFormatFull = "yyyy-MM-dd HH:mm:ss";
    /**
     * 描述：格式 年/月/日<br/>
     * yyyy/MM/dd<br/>
     * */
    public static final String defaultFormatOther = "yyyy/MM/dd";
    /**
     * 描述：格式 年/月/日 小时:分钟:秒<br/>
     * yyyy/MM/dd HH:mm:ss<br/>
     * */
    public static final String defaultFormatOtherFull = "yyyy/MM/dd HH:mm:ss";
    /**
     * 描述：格式 小时分钟 <br/>
     * HHmm<br/>
     * */
    public static final String timeFormatNoSplit = "HHmm";
    /**
     * 描述：格式 小时:分钟 <br/>
     * HH:mm<br/>
     * */
    public static final String timeFormat = "HH:mm";
    /**
     * 描述：格式 小时:分钟:秒<br/>
     * HH:mm:ss<br/>
     * */
    public static final String timeFormatFull = "HH:mm:ss";
    /**
     * 描述：格式 小时分钟秒<br/>
     * HHmmss<br/>
     * */
    public static final String timeFormatFullNoSplit = "HHmmss";
    /**
     * 描述：格式 年月日<br/>
     * yyyyMMdd<br/>
     * */
    public static final String defaultFormatNoSplit = "yyyyMMdd";
    /**
     * 描述：格式 年月日时分秒<br/>
     * yyyyMMddHHmmss<br/>
     * */
    public static final String defaultFormatNoSplitFull = "yyyyMMddHHmmss";

    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private DateUtils() {
    }

    public static final Date getDate(int year, int month, int day, int hour,
                                     int minute, int second) {
        return Date.from(LocalDateTime
                .of(year, month, day, hour, minute, second).atZone(ZONE_ID)
                .toInstant());
    }

    public static final Date getDate(int year, int month, int day, int hour,
                                     int minute) {
        return Date.from(LocalDateTime.of(year, month, day, hour, minute)
                .atZone(ZONE_ID).toInstant());
    }

    public static final Date getDate(int year, int month, int day) {
        return Date.from(LocalDateTime.of(year, month, day, 0, 0)
                .atZone(ZONE_ID).toInstant());
    }

    public static final Date getDateByWeek(int year, int week, int dayOfWeek) {
        return getDateByWeek(year, week, dayOfWeek, 0, 0, 0);
    }

    public static final Date getDateByWeek(int year, int week, int dayOfWeek,
                                           int hour, int minute, int second) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static final Date getDate(String text) throws Exception {
        Inquisitor.notEmpty(text);
        Date r = null;
        text = text.replaceAll("[-|/|:|\\.|,|\\s]", "");
        Inquisitor.checkArgument(text.length() == 8 || text.length() == 14,
                "please enter a valid date.");
        if (text.length() == 8)
            text += "000000";
        r = getDate(text, DateUtils.defaultFormatNoSplitFull);
        return Inquisitor.notNull(r);
    }

    public static Date getDate(String text, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return Date.from(LocalDateTime.parse(text, formatter).atZone(ZONE_ID)
                .toInstant());
    }

    public static String getDateFormatString(Date date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date.toInstant().atZone(ZONE_ID));
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, intToCalendarMonth(month), 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int getWeeksOfYear(int year) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, 1, 1);
        return cal.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }

    public static Date getDateOfWeekFirstDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // Sunday
        return cal.getTime();
    }

    public static Date getDateOfWeekLastDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 6); // Saturday
        return cal.getTime();
    }

    public static String toString(Date date) {
        if (date == null)
            return "";
        return getDayAsString(date) + "." + getMonthNumberAsString(date) + "."
                + getYear(date);
    }

    public static String toDateString() {
        Date date = getCurrent();
        if (date == null)
            return "";
        return getYear(date) + "-" + getMonthNumberAsString(date) + "-"
                + getDayAsString(date);
    }

    public static String toStringDetailed() {
        Date date = getCurrent();
        return getYear(date) + "-" + getMonthNumberAsString(date) + "-"
                + getDayAsString(date) + " " + getHourAsString(date) + ":"
                + getMinuteAsString(date) + ":" + getSecondAsString(date);
    }

    public static String toStringNoFormat() {
        Date date = getCurrent();
        if (date == null)
            return "";
        return getYear(date) + getMonthNumberAsString(date)
                + getDayAsString(date) + "-" + getHourAsString(date)
                + getMinuteAsString(date) + getSecondAsString(date);
    }

    public static String toStringNoFormat(Date date) {
        if (date == null)
            return "";
        return getYear(date) + getMonthNumberAsString(date)
                + getDayAsString(date) + "-" + getHourAsString(date)
                + getMinuteAsString(date) + getSecondAsString(date);
    }

    public static String toStringSlashed() {
        Date date = getCurrent();
        if (date == null)
            return "";
        return getDayAsString(date) + "/" + getMonthNumberAsString(date) + "/"
                + getYear(date) + " " + getHour(date) + ":" + getMinute(date)
                + ":" + getSecond(date);
    }

    public static String toStringNoFormatForCurrentDate() {
        Date date = getCurrent();
        return getYear(date) + getMonthNumberAsString(date)
                + getDayAsString(date) + getHourAsString(date)
                + getMinuteAsString(date) + getSecondAsString(date);
    }

    public static String toStringDetailed(Date date) {
        if (date == null)
            return "";
        return getDayAsString(date) + "." + getMonthNumberAsString(date) + "."
                + getYear(date) + " " + getHourAsString(date) + ":"
                + getMinuteAsString(date) + ":" + getSecondAsString(date);
    }

    public static String toStringSlashed(Date date) {
        if (date == null)
            return "";
        return getYear(date) + "-" + getMonthNumberAsString(date) + "-"
                + getDayAsString(date) + " " + getHourAsString(date) + ":"
                + getMinuteAsString(date) + ":" + getSecondAsString(date);
    }

    public static final Date addSubstractDays(Date target, int days,
                                              boolean isAdd) {
        long msPerDay = 1000 * 60 * 60 * 24;
        long msTarget = target.getTime();
        long msSum = 0;
        if (isAdd)
            msSum = msTarget + (msPerDay * days);
        else
            msSum = msTarget - (msPerDay * days);
        Date result = new Date(msSum);
        return result;
    }

    public static final Date addDays(Date target, int days) {
        return addSubstractDays(target, days, true);
    }

    public static final Date substractDays(Date target, int days) {
        return addSubstractDays(target, days, false);
    }

    public static Date addSeconds(Date dt, long seconds) {
        return new Date(dt.getTime() + seconds * 1000);
    }

    public static Date addMinutes(Date dt, long minutes) {
        return addSeconds(dt, minutes * 60);
    }

    public static Date addHours(Date dt, long hours) {
        return addMinutes(dt, hours * 60);
    }

    public static Date addMonth(Date date, int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static Date addWeek(Date date, int week) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, week);
        return calendar.getTime();
    }

    public static int dayDiff(Date first, Date second) {
        long msPerDay = 1000 * 60 * 60 * 24;
        // /需要去除 24小时，因为在不同 机器上 计算结果有差异
        first = getDate(first.getYear(), first.getMonth(), first.getDay());
        second = getDate(second.getYear(), second.getMonth(), second.getDay());
        long diff = (first.getTime() - second.getTime()) / msPerDay;
        // System.out.println("f=" + first.getTime() + "&s=" + second.getTime()
        // + "|" + (first.getTime() - second.getTime()) + "|" + 86400000);
        Long convertLong = new Long(diff);
        return convertLong.intValue();
    }

    public static double hourDiff(Date first, Date second) {
        double msPerHour = 1000 * 60 * 60;
        double diff = ((double) first.getTime() / msPerHour)
                - ((double) second.getTime() / msPerHour);
        return diff;
    }

    public static String hourDiffAsString(Date first, Date second) {
        double diff = hourDiff(first, second);
        int hour = (int) diff;
        int minute = (int) ((diff - (double) hour) * 60);
        return hour + " saat " + minute + " dakika";
    }

    public static Date getMax(Date dt1, Date dt2) {
        if (dt1 == null || dt2 == null)
            return null;

        if (dt1.compareTo(dt2) >= 0)
            return dt1;
        else
            return dt2;
    }

    public static Date getMin(Date dt1, Date dt2) {
        if (dt1 == null || dt2 == null)
            return null;

        if (dt1.compareTo(dt2) <= 0)
            return dt1;
        else
            return dt2;
    }

    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return calendarMonthToInt(cal.get(Calendar.MONTH));
    }

    public static int getWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getMonthNumberAsString(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int month = calendarMonthToInt(cal.get(Calendar.MONTH));
        if (month < 10)
            return "0" + month;
        return "" + month;
    }

    public static String getMonthName(Date date) {
        String monthName = null;
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int month = calendarMonthToInt(cal.get(Calendar.MONTH));
        if (month == 1)
            monthName = "January";
        else if (month == 2)
            monthName = "February";
        else if (month == 3)
            monthName = "March";
        else if (month == 4)
            monthName = "April";
        else if (month == 5)
            monthName = "May";
        else if (month == 6)
            monthName = "June";
        else if (month == 7)
            monthName = "July";
        else if (month == 8)
            monthName = "August";
        else if (month == 9)
            monthName = "September";
        else if (month == 10)
            monthName = "October";
        else if (month == 11)
            monthName = "November";
        else if (month == 12)
            monthName = "December";
        return monthName;
    }

    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    static public String getDayAsString(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < 10)
            return "0" + day;
        return "" + day;
    }

    public static int getHour(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static String getHourAsString(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            return "0" + hour;
        }
        return "" + hour;
    }

    public static int getMinute(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static String getMinuteAsString(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int minute = cal.get(Calendar.MINUTE);
        if (minute < 10) {
            return "0" + minute;
        }
        return "" + minute;
    }

    public static int getSecond(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    public static String getSecondAsString(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int second = cal.get(Calendar.SECOND);
        if (second < 10) {
            return "0" + second;
        }
        return "" + second;
    }

    public static int getMillisecond(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.MILLISECOND);
    }

    private static int calendarMonthToInt(int calendarMonth) {
        if (calendarMonth == Calendar.JANUARY)
            return 1;
        else if (calendarMonth == Calendar.FEBRUARY)
            return 2;
        else if (calendarMonth == Calendar.MARCH)
            return 3;
        else if (calendarMonth == Calendar.APRIL)
            return 4;
        else if (calendarMonth == Calendar.MAY)
            return 5;
        else if (calendarMonth == Calendar.JUNE)
            return 6;
        else if (calendarMonth == Calendar.JULY)
            return 7;
        else if (calendarMonth == Calendar.AUGUST)
            return 8;
        else if (calendarMonth == Calendar.SEPTEMBER)
            return 9;
        else if (calendarMonth == Calendar.OCTOBER)
            return 10;
        else if (calendarMonth == Calendar.NOVEMBER)
            return 11;
        else if (calendarMonth == Calendar.DECEMBER)
            return 12;
        else
            return 1;
    }

    public static String format(Date date, String pattern) {
        return getDateFormatString(date, pattern);
    }

    private static int intToCalendarMonth(int month) {
        if (month == 1)
            return Calendar.JANUARY;
        else if (month == 2)
            return Calendar.FEBRUARY;
        else if (month == 3)
            return Calendar.MARCH;
        else if (month == 4)
            return Calendar.APRIL;
        else if (month == 5)
            return Calendar.MAY;
        else if (month == 6)
            return Calendar.JUNE;
        else if (month == 7)
            return Calendar.JULY;
        else if (month == 8)
            return Calendar.AUGUST;
        else if (month == 9)
            return Calendar.SEPTEMBER;
        else if (month == 10)
            return Calendar.OCTOBER;
        else if (month == 11)
            return Calendar.NOVEMBER;
        else if (month == 12)
            return Calendar.DECEMBER;
        else
            return Calendar.JANUARY;
    }

    public static java.sql.Date getSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static Date getDate(java.sql.Date sqlDate) {
        return new Date(sqlDate.getTime());
    }

    public static Date getCurrent() {
        return getCurrent(true);
    }

    public static Date getCurrent(boolean useMilliseconds) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(System.currentTimeMillis()));
        if (!useMilliseconds) // ���Ժ���
            cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static boolean isInSameDay(Date first, Date second) {
        boolean flag = false;
        Calendar firstCal = new GregorianCalendar();
        Calendar secondCal = new GregorianCalendar();
        firstCal.setTime(first);
        secondCal.setTime(second);
        if (firstCal.get(Calendar.YEAR) == secondCal.get(Calendar.YEAR)
                && firstCal.get(Calendar.MONTH) == secondCal
                .get(Calendar.MONTH)
                && firstCal.get(Calendar.DAY_OF_MONTH) == secondCal
                .get(Calendar.DAY_OF_MONTH)) {
            flag = true;
        }
        return flag;
    }

    public static boolean isInSameDayOrAfter(Date first, Date second) {
        boolean flag = false;
        Calendar firstCal = new GregorianCalendar();
        Calendar secondCal = new GregorianCalendar();
        firstCal.setTime(first);
        secondCal.setTime(second);
        if (first.after(second)) {
            flag = true;
        } else if (firstCal.get(Calendar.YEAR) == secondCal.get(Calendar.YEAR)
                && firstCal.get(Calendar.MONTH) == secondCal
                .get(Calendar.MONTH)
                && firstCal.get(Calendar.DAY_OF_MONTH) == secondCal
                .get(Calendar.DAY_OF_MONTH)) {
            flag = true;
        }
        return flag;
    }

    public static boolean isMonday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public static boolean isTuesday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
    }

    public static boolean isWednesday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
    }

    public static boolean isThursday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
    }

    public static boolean isFriday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    public static boolean isSaturday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }

    public static boolean isSunday(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static int getCurrentYear() {
        return getYear(getCurrent());
    }

    public static int getCurrentMonth() {
        return getMonth(getCurrent());
    }

    public static String getCurrentMonthNumberAsString() {
        int month = getCurrentMonth();
        if (month < 10) {
            return "0" + month;
        }
        return "" + month;
    }

    public static String getCurrentMonthName() {
        return getMonthName(getCurrent());
    }

    public static int getCurrentDay() {
        return getDay(getCurrent());
    }

    public static String getCurrentDayNumberAsString() {
        int day = getCurrentDay();
        if (day < 10) {
            return "0" + day;
        }
        return "" + day;
    }

    public static int getIntervalDays(Date startday, Date endday) {
        if (startday.after(endday)) {
            Date cal = startday;
            startday = endday;
            endday = cal;
        }
        long sl = startday.getTime();
        long el = endday.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    public static String getCurrentDateFormat(String dateFormat) {
        return getDateFormatString(new Date(), dateFormat);
    }

    public static Date getNextMonth() {
        return Date.from(LocalDateTime.now().plusMonths(1).atZone(ZONE_ID)
                .toInstant());
    }

    public static long getTimeStamp() {
        return getTime() / 1000;
    }

    public static long getTime() {
        return System.currentTimeMillis();
    }

    /** 描述：获取剩余的秒数（今天） */
    public static int getRemainSecondsOfToday() {
        final LocalDateTime now = LocalDateTime.now();
        return (int) (86400 - (now.getHour() * 3600 + now.getMinute() * 60 + now
                .getSecond()));
    }

    /** 获取本月的开始时间 */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /** 获取本月的结束时间 */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getCurrentYear(), getCurrentMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /** 获取上月的开始时间 */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /** 获取上月的结束时间 */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getCurrentYear(), getCurrentMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getCurrentYear(), getCurrentMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    /** 获取某个日期的开始时间 */
    public static Date getDayStartTime(Date startTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != startTime)
            calendar.setTime(startTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    /** 获取某个日期的结束时间 */
    public static Date getDayEndTime(Date endTime) {
        Calendar calendar = Calendar.getInstance();
        if (null != endTime)
            calendar.setTime(endTime);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    /** 当前季度的开始时间 */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            c.set(getCurrentYear(), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        } catch (Exception e) {
        }
        return c.getTime();
    }

    /** 当前季度的结束时间 */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            c.set(getCurrentYear(), c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        } catch (Exception e) {
        }
        return c.getTime();
    }

    /** 取过去几个月时间 */
    public static Date getLastMonths(int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(getCurrent());
        c.add(Calendar.MONTH, -months);
        return c.getTime();
    }
}
