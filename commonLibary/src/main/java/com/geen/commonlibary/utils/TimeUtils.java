package com.geen.commonlibary.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期比对和日期解析类
 */
public class TimeUtils {

    public static String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static String PATTERN_DATETIME_DATE = "yyyy-MM-dd";
    public static String PATTERN_CHINA_DATE = "yyyy年MM月dd日";
    public static String PATTERN_SS_SS = "ss:SS";//格式15:00
    public static String PATTERN_S = "s";//格式15
    public static String PATTERN_mm_ss_SS = "mm:ss:SS";

    public static String PATTERN_HMS = "HH:mm:ss";

    public static String PATTERN_HM = "HH:mm";

    public static String PATTERN_MS = "mm:ss";

    public static String PATTERN_HMS_CHINESE = "HH小时mm分ss秒";

    public static String PATTERN_MS_CHINESE = "mm分ss秒";

    public static String PATTERN_DATE_DAY = "MM月dd日";

    public static String PATTERN_YMDHM = "yyyy-MM-dd HH:mm";

    private static String defaultPattern = PATTERN_DATETIME;

    /*-----------------时间格式的转换-----------------*/

    public static String format(Date date) {
        return format(date, defaultPattern);
    }

    public static String format(long millis) {
        return format(millis, defaultPattern);
    }

    public static String format(Calendar calendar) {
        return format(calendar, defaultPattern);
    }

    public static String getNowTime(){
        return format(new Date(),PATTERN_DATETIME);
    }

    /**
     * 根据日期获得格式化的字符串显示
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) return "";
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        String result = formater.format(date);
        return result;
    }

    public static String format(long millis, String pattern) {
        Date date = new Date();
        date.setTime(millis);
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        String result = formater.format(date);
        return result;
    }


    public static String formatBeijing(long millis, String pattern) {
        Date date = new Date();
        date.setTime(millis);
        SimpleDateFormat formater = new SimpleDateFormat(pattern, Locale.getDefault());
        formater.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String result = formater.format(date);
        return result;
    }

    public static String formatLocal(long millis, String pattern) {
        Date date = new Date();
        date.setTime(millis);
        SimpleDateFormat formater = new SimpleDateFormat(pattern, Locale.getDefault());
        formater.setTimeZone(TimeZone.getDefault());
        String result = formater.format(date);
        return result;
    }


    public static String format(Calendar calendar, String pattern) {
        Date date = calendar.getTime();
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        String result = formater.format(date);
        return result;
    }

    public static Date parseDate(String strDate) {
        try {
            Date date = parseDate(strDate, defaultPattern);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据日期字符串解析到date
     *
     * @param strDate
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String strDate, String pattern) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        Date result = formater.parse(strDate);
        return result;
    }

    /**
     * 根据年月获取当前月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int calculateDaysInMonth(int year, int month) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] bigMonths = {"1", "3", "5", "7", "8", "10", "12"};
        String[] littleMonths = {"4", "6", "9", "11"};
        List<String> bigList = Arrays.asList(bigMonths);
        List<String> littleList = Arrays.asList(littleMonths);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (bigList.contains(String.valueOf(month))) {
            return 31;
        } else if (littleList.contains(String.valueOf(month))) {
            return 30;
        } else {
            if (year <= 0) {
                return 29;
            }
            // 是否闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        }
    }

    /**
     * 功能：判断日期是否和当前date对象在同一天。
     *
     * @param date 比较的日期
     * @return boolean 如果在返回true，否则返回false。
     */
    public static boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("date is null");
        }
        Calendar nowCalendar = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        return compareYearToDay(nowCalendar.getTime(), newCalendar.getTime()) == 0;
    }


    /**
     * 比较年份
     *
     * @param dateOne
     * @param dateTwo
     * @return 1 第一个参数比第二个参数大 0 相等 -1 小于
     */
    public static int compareYear(Date dateOne, Date dateTwo) {
        return compareField(dateOne, dateTwo, Calendar.YEAR);
    }

    /**
     * 比较日期的年月是否相等
     *
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public static int compareYearToMonth(Date dateOne, Date dateTwo) {
        int result = compareField(dateOne, dateTwo, Calendar.YEAR);
        if (result != 0) {
            return result;
        } else {
            return compareField(dateOne, dateTwo, Calendar.MONTH);
        }
    }

    /**
     * 比较date的年月日是否相等
     *
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public static int compareYearToDay(Date dateOne, Date dateTwo) {
        int result = compareField(dateOne, dateTwo, Calendar.YEAR);
        if (result != 0) {
            return result;
        } else {
            return compareField(dateOne, dateTwo, Calendar.DAY_OF_YEAR);
        }
    }

    /**
     * 比较date是否相等
     *
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public static int compareDate(Date dateOne, Date dateTwo) {
        if (dateOne == null) {
            throw new IllegalArgumentException("The dateOne must not be null");
        }
        if (dateTwo == null) {
            throw new IllegalArgumentException("The dateTwo must not be null");
        }
        long result = dateOne.getTime() - dateTwo.getTime();
        if (result > 0) {
            return 1;
        }
        if (result < 0) {
            return -1;
        }
        return 0;
    }


    private static Calendar compareCalendarOne = Calendar.getInstance();
    private static Calendar compareCalendarTwo = Calendar.getInstance();

    private static int compareField(Date dateOne, Date dateTwo, int calendarField) {
        if (dateOne == null) {
            throw new IllegalArgumentException("The dateOne must not be null");
        }
        if (dateTwo == null) {
            throw new IllegalArgumentException("The dateTwo must not be null");
        }
        compareCalendarOne.setTime(dateOne);
        compareCalendarTwo.setTime(dateTwo);
        int result = compareCalendarOne.get(calendarField) - compareCalendarTwo.get(calendarField);
        if (result > 0) {
            return 1;
        }
        if (result < 0) {
            return -1;
        }
        return 0;
    }


    /**
     * 如果与当前时间在同一天，且小于60分钟，则显示几分钟前，如果大于60分钟，显示几小时前
     * 如果发生时间在昨天，显示昨天
     * 如果发生时间在前天，显示前天
     * 其余情况下，显示几月几号
     *
     * @param rawDataString
     * @return
     */
    public static String getFormatTime(String rawDataString) {
        try {
            Date date = parseDate(rawDataString, defaultPattern);
            Date curDate = new Date();
            if (curDate.before(date)) {
                return "";
            }
            Calendar curCalendar = Calendar.getInstance();
            curCalendar.setTime(curDate);

            Calendar srcCalendar = Calendar.getInstance();
            srcCalendar.setTime(date);
            //今天
            if (srcCalendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR)
                    && srcCalendar.get(Calendar.MONTH) == curCalendar.get(Calendar.MONTH)
                    && srcCalendar.get(Calendar.DAY_OF_MONTH) == curCalendar.get(Calendar.DAY_OF_MONTH)) {
                long timeDiff = curDate.getTime() - date.getTime();
                if (timeDiff >= MILLS_IN_MINUTE * 60) {
                    int hourBefore = (int) (timeDiff / (MILLS_IN_MINUTE * 60));
                    return String.format(Locale.getDefault(), "%d小时前", hourBefore);
                } else {
                    int minuteBefore = (int) (timeDiff / MILLS_IN_MINUTE);
                    return String.format(Locale.getDefault(), "%d分钟前", minuteBefore);
                }
            }
            //昨天
            srcCalendar.add(Calendar.DAY_OF_MONTH, 1);

            if (srcCalendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR)
                    && srcCalendar.get(Calendar.MONTH) == curCalendar.get(Calendar.MONTH)
                    && srcCalendar.get(Calendar.DAY_OF_MONTH) == curCalendar.get(Calendar.DAY_OF_MONTH)) {
                return "昨天";
            }

            srcCalendar.add(Calendar.DAY_OF_MONTH, -1);
            //前天
            srcCalendar.add(Calendar.DAY_OF_MONTH, 2);
            if (srcCalendar.get(Calendar.YEAR) == curCalendar.get(Calendar.YEAR)
                    && srcCalendar.get(Calendar.MONTH) == curCalendar.get(Calendar.MONTH)
                    && srcCalendar.get(Calendar.DAY_OF_MONTH) == curCalendar.get(Calendar.DAY_OF_MONTH)) {
                return "前天";
            }
            srcCalendar.add(Calendar.DAY_OF_MONTH, -2);


            return String.format(Locale.getDefault(), "%2d月%02d日",
                    srcCalendar.get(Calendar.MONTH) + 1,
                    srcCalendar.get(Calendar.DAY_OF_MONTH));

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将差值毫秒转换为时分秒
     *
     * @param ms
     * @return
     */
    public static String formatLongToTimeStr(Long ms) {
        int hour = 0;
        int minute = 0;
        int second = 0;

        second = ms.intValue() / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + ":" + getTwoLength(minute) + ":" + getTwoLength(second));
    }

    /**
     * 将差值秒转换为时分秒
     *
     * @param ms
     * @return
     */
    public static String formatSecondToTimeStr(Long ms) {
        int hour = 0;
        int minute = 0;
        int second = ms.intValue();

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(hour) + ":" + getTwoLength(minute) + ":" + getTwoLength(second));
    }

    /**
     * 将秒转换为时分
     *
     * @param ms
     * @return
     */
    public static String formatHourToMinuteStr(Long ms) {
        int hour = 0;
        int minute = 0;
        int second = ms.intValue();

        if (second == 0) {
            return "00:00";
        } else if (second <= 60) {
            return "00:01";
        }

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        return getTwoLength(hour) + ":" + getTwoLength(minute);
    }

    /**
     * 将秒转换为分秒
     *
     * @param ms
     * @return
     */
    public static String formatSecondToMinuteStr(Long ms) {
        int minute = 0;
        int second = ms.intValue();

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        return getTwoLength(minute) + ":" + getTwoLength(second);
    }

    private static String getTwoLength(final int data) {
        if (data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }


    private static final int MILLS_IN_MINUTE = 1000 * 60;

    /**
     * 将北京时间转换为手机系统时区时间
     * 部分地区时间会差一个小时，待优化
     */
    public static String formatBJConvertDefault(String time) {
        String dataTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(PATTERN_YMDHM);
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMDHM);
            // CST(北京时间)转换为GMT
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String gmtDate = format.format(parseDate(time, PATTERN_YMDHM));
            Date date = sdf.parse(gmtDate);
            dataTime = formatGMTUnixTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataTime;
    }

    /**
     * 将GMT日期格式化为系统默认时区的日期字符串表达形式
     *
     * @param gmtUnixTime GTM时间戳
     * @return 日期字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime) {
        return formatGMTUnixTime(gmtUnixTime, PATTERN_YMDHM);
    }

    /**
     * 将GMT日期格式化为系统默认时区的日期字符串表达形式
     *
     * @param gmtUnixTime GTM时间戳
     * @param format      格式化字符串
     * @return 日期字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(gmtUnixTime + TimeZone.getDefault().getRawOffset());
    }

    /**
     * 获取时区信息
     */
    public static TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * 获取更改时区后的时间
     *
     * @param time 时间
     * @return 时间
     */
    public static String changeTimeZone(String time, String pattern) {
        if (time == null || TextUtils.isEmpty(time)) return "";
        TimeZone oldZone = TimeZone.getTimeZone("GMT+8");
        TimeZone newZone = TimeZone.getDefault();

        Date date = null;
        Date dateTmp = null;
        try {
            date = parseDate(time, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return format(dateTmp, pattern);
    }
    /***
     * 获取星期几
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
