package com.zyh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author zhangyanghui
 * @Title DateFormatUtil
 * @Description 日期格式化工具类
 * @date 2018/7/20 0:52
 */
public class DateFormatUtil {

    private static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

    public static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    public static final String shortDatePattern = "yyyy-MM-dd";
    public static final String simpleDatePattern = "yyyyMMdd";
    public static final String simpleHourDatePattern = "yyyyMMddHH";
    public static final String simpleMinuteDatePattern = "yyyyMMddHHmm";
    public static final String simpleSecDatePattern = "yyyyMMddHHmmss";
    public static final String simpleMSDatePattern = "yyyyMMddHHmmssSSS";
    public static final String shortDateToPattern = "yyyy-MM-dd HH:mm";
    public static final String defaultDate = "yyyy年MM月dd日 HH:mm";
    public static final String shortDate = "yyyy年MM月dd日";
    public static final String defaultHourMinuteDate = "HH:mm";
    public static final String minutes = "minutes";
    public static final String hours = "hours";
    public static final String days = "days";
    public static final String before = "before";
    public static final String after = "after";

    /**
     * 获取时间
     *
     * @return
     */
    public static Date getDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * String转Date
     * yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date getStringToDate(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(DateFormatUtil.defaultDatePattern);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("date error:", e);
        }

        return date;
    }

    /**
     * String转Date
     * yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date stringToDate(String strDate, String format) {
        if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(format)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("date error:", e);
        }

        return date;
    }

    public static String getDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DateFormatUtil.shortDateToPattern);
        return dateFormat.format(date);
    }

    /**
     * 从Date中只取日期，时间清零
     *
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        if (date == null) {
            return date;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            logger.error("getDate error ", e);
        }
        return returnDate;
    }

    /**
     * 通过日期差获取响应的日期
     *
     * @param date
     * @param dValue 日期差
     * @return
     */
    public static Date addDateByDvalue(Date date, int dValue) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dValue);
        return calendar.getTime();
    }

    /**
     * 通过日期差获取响应的日期
     *
     * @param date
     * @param dValue 日期差
     * @return
     */
    public static Date addHourByDvalue(Date date, int dValue) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, dValue);
        return calendar.getTime();
    }

    /**
     * 通过日期差获取响应的日期
     *
     * @param date
     * @param dValue 日期差（例如明天为1，昨天为-1）
     * @return
     */
    public static Date addMinuteByDvalue(Date date, int dValue) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, dValue);
        return calendar.getTime();
    }


    /**
     * 通过日期差获取响应的日期
     *
     * @param date
     * @param dValue 日期差（例如明天为1，昨天为-1）
     * @return
     */
    public static Date addSecondByDvalue(Date date, int dValue) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, dValue);
        return calendar.getTime();
    }

    /**
     * 将日期转化成指定格式 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String parseDateToShortDateString(Date date) {
        return parseDateToString(date, shortDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseDateDefaultDateString(Date date) {
        return parseDateToString(date, defaultDatePattern);
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseShortDateStringToDate(String dateString) {
        return parseStringToDate(dateString, shortDatePattern);
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseSimpleMinuteStringToDate(String dateString) {
        return parseStringToDate(dateString, simpleMinuteDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String parseDateToSimpleDateString(Date date) {
        return parseDateToString(date, simpleDatePattern);
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseSimpleDateStringToDate(String dateString) {
        return parseStringToDate(dateString, simpleDatePattern);
    }

    /**
     * @param dateString
     * @return
     */
    public static Date parseDefaultDateStringToDate(String dateString) {
        return parseStringToDate(dateString, defaultDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyyMMddHH
     *
     * @param date
     * @return
     */
    public static String parseDateToSimpleHourString(Date date) {
        return parseDateToString(date, simpleHourDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyyMMddHHmm
     *
     * @param date
     * @return
     */
    public static String parseDateToSimpleMinuteString(Date date) {
        return parseDateToString(date, simpleMinuteDatePattern);
    }


    /**
     * 将日期转化成指定格式 yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String parseDateToSimpleSecString(Date date) {
        return parseDateToString(date, simpleSecDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyyMMddHHmmssSSS
     *
     * @param date
     * @return
     */
    public static String parseDateToSimpleMsString(Date date) {
        return parseDateToString(date, simpleMSDatePattern);
    }
    /**
     * 将yyyyMMddHHmmss的字符串转化成日期
     *
     * @param str yyyyMMddHHmmss
     * @return
     */
    public static Date parseSimpleSecStringToDate(String str) {
        return parseStringToDate(str, simpleSecDatePattern);
    }

    /**
     * 将日期转化成指定格式 yyyy年MM月dd日 HH:mm
     *
     * @param date
     * @return
     */
    public static String parseDateToDefaultString(Date date) {
        return parseDateToString(date, defaultDate);
    }

    public static String parseShortDateToDefaultString(Date date) {
        return parseDateToString(date, shortDate);
    }

    /**
     * 将日期转化成指定格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String parseDateToDefaultDateString(Date date) {
        return parseDateToString(date, defaultDatePattern);
    }

    /**
     * 将日期转化成指定格式
     * create by wangxiaolong 2017/03/29
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String parseDateToString(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        if (date != null) {
            return format.format(date);
        }
        return null;
    }

    /**
     * date与XMLGregorianCalendar 的格式转换
     *
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        return convertToXMLCalendar(date, null);
    }

    /**
     * date与XMLGregorianCalendar 的格式转换 精确到小时
     *
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertToHourXMLCalendar(Date date) {
        return convertToXMLCalendar(date, simpleHourDatePattern);
    }

    /**
     * date与XMLGregorianCalendar 的格式转换 精确到秒
     *
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertToSecondXMLCalendar(Date date) {
        return convertToXMLCalendar(date, simpleSecDatePattern);
    }

    /**
     * date与XMLGregorianCalendar 的格式转换
     *
     * @param date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertToXMLCalendar(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        GregorianCalendar cal = new GregorianCalendar();
        if (!StringUtils.isEmpty(dateFormat)) {
            DateFormat format = new SimpleDateFormat(dateFormat);
            try {
                cal.setTime(format.parse(format.format(date)));
            } catch (ParseException e) {
                logger.error("convertToXMLCalendar error ", e);
            }
        } else {
            cal.setTime(date);
        }
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            logger.error("convertToXMLCalendar error ", e);
        }
        return gc;
    }

    /***
     * XMLGregorianCalendar 改date
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(XMLGregorianCalendar cal) {
        if (cal != null) {
            GregorianCalendar ca = cal.toGregorianCalendar();
            return ca.getTime();
        } else {
            return null;
        }


    }

    public static Date parseStringToDate(String dateString, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        if (!StringUtils.isEmpty(dateString)) {
            try {
                return format.parse(dateString);
            } catch (ParseException e) {
                logger.error("parseStringToDate error ", e);
            }
        }
        return null;
    }


    public static Date parseStringToDateExclusiveByPhotoName(String dateString) {
        DateFormat format = new SimpleDateFormat(simpleDatePattern);
        if (!StringUtils.isEmpty(dateString)) {
            try {
                return format.parse(dateString);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 计算两日期月差多余天数不算一个月（采用a-b方式）
     *
     * @param a 大日期 如：2014年7月16
     * @param b 小日期 如：2013年7月16
     * @return 计算出月差为12个月即返回12
     */
    public static int getMonthsBetween(Date a, Date b) {
        if (a == null || b == null) {
            return 0;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        int year = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
        int month = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
        return year * 12 + month;
    }

    /**
     * 计算两日期差天数（采用a-b方式）
     *
     * @param a
     * @param b
     * @return
     */
    public static int getDaysBetween(Date a, Date b) {
        if (a == null || b == null) {
            return 0;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        int days = (int) (mSeconds / (1000L * 60L * 60L * 24L));
        return days;
    }

    /**
     * 计算两日期差天数,超一天,多算一天（采用a-b方式）
     *
     * @param a
     * @param b
     * @return
     */
    public static int getDaysBetweenPreciseHour(Date a, Date b) {
        if (a == null || b == null) {
            return 0;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        int days = (int) (mSeconds / (1000L * 60L * 60L * 24L));
        // 超一天,多算一天
        if ((mSeconds % (1000L * 60L * 60L * 24L)) != 0) {
            return days + 1;
        }
        return days;
    }

    /**
     * 判断两个时间是否为同一天
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameTime(Date a, Date b, int field) {
        if (a == null || b == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(field) == calendar2.get(field)) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个时间的大小，如果date1不小于date2则返回true，否则返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        if (date2 == null) {
            return true;
        }
        if (date1 == null) {
            return false;
        }
        if (date1.after(date2)) {
            return true;
        }
        return false;
    }

    /**
     * 判断两个时间是否不超过一个时间阀值
     * 例如：不超过一个小时 field为、amount为1
     *
     * @param a
     * @param b
     * @param field
     * @param amount
     * @return 不超过返回true，超过返回false
     */
    public static boolean isNotPass(Date a, Date b, int field, int amount) {
        if (a == null || b == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(a);
        calendar2.setTime(b);
        if (calendar1.getTimeInMillis() <= calendar2.getTimeInMillis()) {
            calendar1.add(field, amount);
            if (calendar1.getTimeInMillis() >= calendar2.getTimeInMillis()) {
                return true;
            }
        }
        if (calendar1.getTimeInMillis() >= calendar2.getTimeInMillis()) {
            calendar2.add(field, amount);
            if (calendar1.getTimeInMillis() <= calendar2.getTimeInMillis()) {
                return true;
            }
        }
        return false;
    }

    public static String toLongStr(Calendar c) {
        if (c == null)
            return "";

        String s = toShortStr(c);

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        s += " ";
        s += paddingZero(hour);
        s += ":";
        s += paddingZero(minute);
        s += ":";
        s += paddingZero(second);

        return s;
    }

    public static String toShortStr(Calendar c) {
        if (c == null)
            return "";

        StringBuilder sb = new StringBuilder();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DAY_OF_MONTH);

        sb.append(year)
                .append("-")
                .append(paddingZero(month))
                .append("-")
                .append(paddingZero(date));

        return sb.toString();
    }

    private static String paddingZero(int num) {
        if (num < 10)
            return "0" + num;

        return "" + num;
    }

    /**
     * 把精确到天的日期设置成该天的23:59:59
     *
     * @param date
     * @return
     */
    public static Date setDayTime(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 23, 59, 59);
        date = calendar.getTime();
        return date;
    }

    /***
     * 日期加减操作
     * @param date
     * amount为正则往后,为负则往前
    field取1加1年,取2加半年,取3加一季度,取4加一周
    取5加一天
     */
    public static Date handlerDate(Date date, int field, int amount) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, amount);
        return gc.getTime();
    }

    /**
     * 计算车龄 = 出险时间-登记日期(不满1个月不计算)
     *
     * @param accidentDate
     * @param vehicleRegisterDate
     * @return
     */
    public static Integer calculateVehicleMonth(Date accidentDate, Date vehicleRegisterDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(accidentDate);
        c2.setTime(vehicleRegisterDate);
        Integer yearOfGap = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        Integer monthOfGap = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        Integer months = yearOfGap * 12 + monthOfGap; //相差的总共的月份
        if ((c1.get(Calendar.DATE) - c2.get(Calendar.DATE)) < 0) {
            months = months - 1; //如果天数比登记日期小, 相差的月份-1
        }
        return months;
    }

    /**
     * 计算车龄 = 出险时间-登记日期(不满1个月不计算)
     */
    public static Integer gainAge(Date today, Date birthday) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(today);
        c2.setTime(birthday);
        Integer year = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        Integer month = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        if (month < 0) {
            year -= 1;
        }
        return year;
    }

    /**
     * 把精确到天的日期设置成该天的00:00:00
     *
     * @param date
     * @return
     */
    public static Date setLittleDayTime(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 0, 0, 0);
        date = calendar.getTime();
        return date;
    }

    /**
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000L * 24 * 60 * 60;
        long nh = 1000L * 60 * 60;
        long nm = 1000L * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 时间在昨天和明天之间
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        boolean flag = false;
        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, today.get(Calendar.YEAR));
        today.set(Calendar.MONTH, today.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) - 1);
        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        Calendar tomorrow = Calendar.getInstance();    //明天

        tomorrow.set(Calendar.YEAR, today.get(Calendar.YEAR));
        tomorrow.set(Calendar.MONTH, today.get(Calendar.MONTH));
        tomorrow.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH) + 1);
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        if (calendar.after(today) && calendar.before(tomorrow)) {
            flag = true;
        } else {
            return flag;
        }
        return flag;
    }

    /**
     * 两时间相减，得到分钟，小时，或者天数
     *
     * @param date1    时间1
     * @param date2    时间2
     * @param dateType 类型（分钟，小时，天数）
     * @return
     */
    public static Long getDayOrHourOrMin(Date date1, Date date2, String dateType) {
        Long diff = date2.getTime() - date1.getTime();
        Long dhm = 0L;
        switch (dateType) {
            case minutes:
                dhm = diff / (1000L * 60);
                break;
            case hours:
                dhm = diff / (1000L * 60 * 60);
                break;
            case days:
                dhm = diff / (1000L * 60 * 60 * 24);
                break;
        }
        return dhm;
    }

    /**
     * 如果type=before,表示当前date的前diff天; type=after,表示当前date的后diff天
     *
     * @param date 时间
     * @param diff 天数
     * @param type before:前几天  after:后几天
     * @return
     */
    public static Date getDate(Date date, Integer diff, String type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (before.equals(type)) {
            diff = -diff;
        }
        calendar.add(Calendar.DAY_OF_MONTH, diff);
        date = calendar.getTime();
        return date;
    }

    /**
     * 返回指定时间
     * @param date 时间
     * @param m 分钟
     * @param s 秒
     * @return
     */
    public static String addDateMinute(Date date, int m, int s) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(0!=m) {
            cal.add(Calendar.MINUTE, m);
        } else if(0 !=s) {
            cal.add(Calendar.SECOND, s);
        }
        String afterDate = parseDateToSimpleSecString(cal.getTime());
        return afterDate;
    }
}
