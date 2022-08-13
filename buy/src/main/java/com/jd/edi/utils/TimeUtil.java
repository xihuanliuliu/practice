package com.jd.edi.utils;


import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class TimeUtil {

	public static final String FORMAT_DATE_ONLY = "yyyy-MM-dd";

	public static final String FORMAT_TIME_ONLY = "HH:mm:ss";

	public static final String FORMAT_COMPACT = "yyyyMMddHHmmss";

	public static final String FORMAT_COMPACT_DATE = "yyyyMMdd";

	public static final String FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_DETAIL = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";

	public static final String FORMAT_NORMAL1 = "yyyy/MM/dd HH:mm";

	public static final String FORMAT_YEAR_MONTH = "yyyy-MM";

	public static final long DATE_SECOND = 86400;// 一天有86400秒

	public static final long DATE_MINUTE = 1440;// 一天有1440分

	public static final long SECOND_MS = 1000;// 一秒有1000毫秒
	public static final long MINUTE_SECOND = 60;// 一分有60秒
	public static final long HOUR_MINUTE = 60;// 一时有60分
	public static final long HOUR_SECOND = 3600;// 一时有3600秒
	public static final long DAY_HOUR = 24;// 一天有24小时
	public static final int YEAR_MONTH = 12;// 一年有12个月
	public static final int WEEK_DAY = 7;// 一周有7天
	public static final int FIVE = 5;
	public static final int TEN = 10;
	public static final int ELEVEN = 11;
	
	public static final String TIME_ZONE = "Asia/Shanghai";

	private TimeUtil() {
	}

	public static Date parse(String str, String format) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			sf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
			return sf.parse(str);
		} catch (ParseException e) {
			log.error("", e);
		}
		return null;
	}

	public static String format(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		sf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		return sf.format(date);
	}

	public static String format(Timestamp date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		sf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
		return sf.format(date);
	}

	public static boolean isExpire(String strTime, String strExpiredTime) {
		Date time = parse(strTime, FORMAT_NORMAL);
		Date expiredTime = parse(strExpiredTime, FORMAT_NORMAL);

		return time != null && (time.compareTo(expiredTime) >= 0);
	}

	public static String getNowDate() {
		return TimeUtil.format(new Date(), TimeUtil.FORMAT_DATE_ONLY);
	}

	public static String getNowTime() {
		return TimeUtil.format(new Date(), TimeUtil.FORMAT_NORMAL);
	}

	/**
	 * 生成制定日期的Date对象，从0点开始
	 * 
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 */
	public static Date createDate(int year, int month, int days) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, days, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 计算时间差
	 * 
	 * @param beginTime
	 *            开始时间，格式：yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            结束时间，格式：yyyy-MM-dd HH:mm:ss
	 * @return 从开始时间到结束时间之间的时间差（秒）
	 */
	public static long getTimeDifference(String beginTime, String endTime) {
		long between = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_ONLY);

		Date end = null;
		Date begin = null;
		try {
			end = sdf.parse(endTime);
			begin = sdf.parse(beginTime);
			between = (end.getTime() - begin.getTime()) / SECOND_MS;
			return between;
		} catch (ParseException e) {
			log.error("", e);
		}

		return 0;
	}

	/**
	 * 返回当前系统日期时间戳
	 * 
	 * @return
	 */
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 日期转换为时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp date2timestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE_ONLY);
		Date beginDate;
		Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (DAY_HOUR * HOUR_SECOND * SECOND_MS);
		} catch (ParseException e) {
			log.error("", e);
		}
		return day;
	}

	/**
	 * 计算两个日期相差的天数.不满24小时不算做一天
	 * 
	 * @date 2009-7-10 下午03:15:54
	 * @modifyNote
	 * @param fDate
	 *            日期1
	 * @param oDate
	 *            日期2
	 * @return 日期1 - 日期2 的差
	 */
	public static int getBetweenDays(Date fDate, Date sDate) {
		return (int) ((fDate.getTime() - sDate.getTime()) / (DAY_HOUR * HOUR_SECOND * SECOND_MS));
	}

	/**
	 * 日期相加指定年
	 * 
	 * @date 2009-9-10 上午10:26:22
	 * @modifyNote
	 * @param date
	 *            日期
	 * @param addYears
	 *            要添加的年数
	 * @return 相加后的日期
	 */
	public static Date addYears(Date date, int addYears) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.YEAR, addYears);
		return calender.getTime();
	}

	/**
	 * 加指定月
	 * 
	 * @date 2009-9-10 上午10:26:57
	 * @modifyNote
	 * @param date
	 *            日期
	 * @param addMonths
	 *            月数
	 * @return 相加后的日期
	 */
	public static Date addMonth(Date date, int addMonths) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.MONTH, addMonths);
		return calender.getTime();
	}

	/**
	 * 加指定天数
	 * 
	 * @date 2009-9-10 上午10:27:22
	 * @modifyNote
	 * @param date
	 *            日期
	 * @param addDays
	 *            天数
	 * @return 相加后的日期
	 */
	public static Date addDay(Date date, int addDays) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_YEAR, addDays);
		return calender.getTime();
	}

	/**
	 * 得到一年的第一天
	 * 
	 * @date 2009-9-10 上午11:14:23
	 * @modifyNote
	 * @param year
	 *            年
	 * @return 一年的第一天
	 */
	public static Date getFirstDateOfYear(int year) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, year);
		calender.set(Calendar.DAY_OF_YEAR, calender.getActualMinimum(Calendar.DAY_OF_YEAR));
		setStartTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 得到一年的最后一天
	 * 
	 * @date 2009-9-10 上午11:14:42
	 * @modifyNote
	 * @param year
	 *            年
	 * @return 一年的最后一天
	 */
	public static Date getLastDateOfYear(int year) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.YEAR, year);
		calender.set(Calendar.DAY_OF_YEAR, calender.getActualMaximum(Calendar.DAY_OF_YEAR));
		setEndTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 判断当前日期是否是所在月份的最后一天
	 * 
	 * @date 2009-9-10 上午10:54:36
	 * @modifyNote
	 * @param date
	 *            日期
	 * @return 是最后一天为 true
	 */
	public static boolean isLastDayOfMonth(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int day = calender.get(Calendar.DAY_OF_MONTH);
		int lastDay = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day == lastDay;
	}

	/**
	 * 得到指定月的最后一天
	 * 
	 * @date 2009-9-10 上午11:09:56
	 * @modifyNote
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar calender = Calendar.getInstance();
		calender.set(year, month - 1, 1);
		calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
		setEndTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 得到日期所在月的最后一天
	 * 
	 * @date 2009-9-10 上午10:54:25
	 * @modifyNote
	 * @param date
	 *            日期
	 * @return 所在月的最后一天
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.DAY_OF_MONTH, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
		setEndTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 设置到当前月的最后时刻
	 * 
	 * @date 2010-10-18 上午11:04:56
	 * @modifyNote
	 * @param calender
	 */
	private static void setEndTimeOfDay(Calendar calender) {
		calender.set(Calendar.HOUR_OF_DAY, calender.getActualMaximum(Calendar.HOUR_OF_DAY));
		calender.set(Calendar.MINUTE, calender.getActualMaximum(Calendar.MINUTE));
		calender.set(Calendar.SECOND, calender.getActualMaximum(Calendar.SECOND));
		calender.set(Calendar.MILLISECOND, calender.getActualMaximum(Calendar.MILLISECOND));
	}

	/**
	 * 得到指定月的第一天
	 * 
	 * @date 2009-9-10 上午11:09:56
	 * @modifyNote
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 第一天
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar calender = Calendar.getInstance();
		calender.set(year, month - 1, 1);
		calender.set(Calendar.DAY_OF_MONTH, calender.getActualMinimum(Calendar.DAY_OF_MONTH));
		setStartTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 得到指定日期所在月的第一天
	 * 
	 * @date 2009-9-10 上午11:09:56
	 * @modifyNote
	 * @param date
	 *            日期
	 * @return 第一天
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.DAY_OF_MONTH, calender.getActualMinimum(Calendar.DAY_OF_MONTH));
		setStartTimeOfDay(calender);
		return calender.getTime();
	}

	/**
	 * 设置到月份开始的时刻
	 * 
	 * @modifyNote
	 * @param calender
	 */
	private static void setStartTimeOfDay(Calendar calender) {
		calender.set(Calendar.HOUR_OF_DAY, calender.getActualMinimum(Calendar.HOUR_OF_DAY));
		calender.set(Calendar.MINUTE, calender.getActualMinimum(Calendar.MINUTE));
		calender.set(Calendar.SECOND, calender.getActualMinimum(Calendar.SECOND));
		calender.set(Calendar.MILLISECOND, calender.getActualMinimum(Calendar.MILLISECOND));
	}

	/**
	 * 获取日期在一周中是周几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekNumber(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		week = ((week + FIVE) % WEEK_DAY) + 1;
		return week;
	}

	/**
	 * 在月内的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 得到时间的年
	 * 
	 * @param date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 得到传入时间的月份
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到传入时间的day number
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期比较工具类
	 */
	public static final class DateCompare {

		private DateCompare() {
		}

		/**
		 * 比较是否在同一年同一月内的同一周
		 * 
		 * @param date
		 * @param date2
		 * @return
		 */
		public static boolean isSameWeek(Date date, Date date2) {
			return getWeekInMonth(date) == getWeekInMonth(date2) && isSameYear(date, date2) && isSameMonth(date, date2);
		}

		/**
		 * 比较两个日期是偶同年同月同日
		 * 
		 * @param date
		 * @param date2
		 * @return
		 */
		public static boolean isSameDay(Date date, Date date2) {
			return isSameYear(date, date2) && isSameMonth(date, date2) && getDay(date) == getDay(date2);
		}

		/**
		 * 比较两个日期是否同年同月
		 */
		public static boolean isSameMonth(Date date, Date date2) {
			return isSameYear(date, date2) && getMonth(date) == getMonth(date2);
		}

		/**
		 * 比较两个日期是否属于同一年度
		 * 
		 * @param date
		 * @param date2
		 * @return
		 */
		public static boolean isSameYear(Date date, Date date2) {
			return getYear(date) == getYear(date2);
		}
	}


	/**
	 * 计算两个日期之间相差的月份数 <br>
	 * 日期顺序不分先后不会返回负数 <br>
	 * 不足一个月不算做一个月
	 * 
	 * @author tangjiawei
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 月数
	 */
	public static int getBetweenMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		Calendar objCalendarDate1 = Calendar.getInstance();
		objCalendarDate1.setTime(date1);

		Calendar objCalendarDate2 = Calendar.getInstance();
		objCalendarDate2.setTime(date2);

		if (objCalendarDate2.equals(objCalendarDate1))
			return 0;
		if (objCalendarDate1.after(objCalendarDate2)) {
			Calendar temp = objCalendarDate1;
			objCalendarDate1 = objCalendarDate2;
			objCalendarDate2 = temp;
		}
		if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH))
			flag = 1;

		if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
			iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * YEAR_MONTH
					+ objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
		else
			iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

		return iMonth;
	}

	/**
	 * 计算两个日期之间相差的年份数 <br>
	 * 日期顺序不分先后不会返回负数 <br>
	 * 不足一个年不算做一个年
	 * 
	 * @author tangjiawei
	 * @modifyNote
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 年数
	 */
	public static int getBetweenYears(Date date1, Date date2) {
		return getBetweenMonths(date1, date2) / YEAR_MONTH;
	}

	public static Date getStartTimeOfDay(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		setStartTimeOfDay(calender);
		return calender.getTime();
	}

	public static Date getEndTimeOfDay(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		setEndTimeOfDay(calender);
		return calender.getTime();

	}

	public static long compareDate(Date date1, Date date2) {
		Date newDate1 = getStartTimeOfDay(date1);
		Date newDate2 = getStartTimeOfDay(date2);
		long quot = 0;
		quot = newDate1.getTime() - newDate2.getTime();
		quot = quot / SECOND_MS / HOUR_MINUTE / MINUTE_SECOND / DAY_HOUR;
		return quot;
	}

	public static long compareDate(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / SECOND_MS / HOUR_MINUTE / MINUTE_SECOND / DAY_HOUR;
		} catch (ParseException e) {
			log.error("", e);
		}
		return quot;
	}

	/**
	 * 获取中文描述
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDifferenceDesc(Date beginTime, Date endTime) {
		long difference = endTime.getTime() - beginTime.getTime();

		difference = difference / SECOND_MS;// 除以1000是为了转换成秒
		long date = difference / DATE_SECOND;
		long hour = (difference - date * DATE_SECOND) / HOUR_SECOND;
		long minute = (difference - date * DATE_SECOND - hour * HOUR_SECOND) / HOUR_MINUTE;
		long sec = (difference - date * DATE_SECOND - hour * HOUR_SECOND - minute * MINUTE_SECOND);
		if (date == 0) {
			return hour + "小时" + minute + "分钟" + sec + "秒";
		} else {
			return date + "天" + hour + "小时" + minute + "分钟" + sec + "秒";
		}
	}
	/**
	 * 获取中文描述
	 * 
	 * @param duration
	 * @return
	 */
	public static String getTimeDifferenceDesc(long duration) {
		long duration1 = duration / SECOND_MS;// 除以1000是为了转换成秒
		long date = duration1 / DATE_SECOND;
		long hour = (duration1 - date * DATE_SECOND) / HOUR_SECOND;
		long minute = (duration1 - date * DATE_SECOND - hour * HOUR_SECOND) / HOUR_MINUTE;
		long sec = (duration1 - date * DATE_SECOND - hour * HOUR_SECOND - minute * MINUTE_SECOND);
		if (date == 0) {
			return hour + "小时" + minute + "分钟" + sec + "秒";
		} else {
			return date + "天" + hour + "小时" + minute + "分钟" + sec + "秒";
		}
	}

	public static String formartTimeAsChinese(long ms){
		NumberFormat ddf= NumberFormat.getNumberInstance() ;
		ddf.setMaximumFractionDigits(1);
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		double second = (ms - day * dd - hour * hh - minute * mi) / 1000.00;
		
		StringBuffer sb = new StringBuffer();
		sb.append(day != 0 ? day + "天" : "");
		sb.append(hour != 0 ? hour + "小时" : "");
		sb.append(minute != 0 ? minute + "分钟" : "");
		sb.append((int)second != 0 ?(second > 1 ? (int)second : ddf.format(second)) + "秒" : (ms + "毫秒"));
		return sb.toString();
	}
	
	/**
     * 获取当天0时时间，格式为2017-07-14T00:00:00.000
     *
     * @return
     */
    public static String getDayBeginLikeYYYYMMDDTHHMMSS(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_NORMAL);
        String since = simpleDateFormat.format(date);
        since = since.substring(0, TEN) + "T" + since.substring(ELEVEN, since.length()) + ".000";
        return since;
    }

    public static String getDayEndLikeYYYYMMDDTHHMMSS(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_NORMAL);
        String since = simpleDateFormat.format(date);
        since = since.substring(0, TEN) + "T" + since.substring(ELEVEN, since.length()) + ".999";
        return since;
    }

    /**
     * 获取当天0时
     *
     * @return
     */
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastDayBegin(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

//    public static Date getLastDayEnd(Date day) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(day);
//        calendar.add(Calendar.DATE, -1);
//        calendar.set(Calendar.HOUR_OF_DAY, Constants.NUMBER_23);
//        calendar.set(Calendar.SECOND, Constants.NUMBER_59);
//        calendar.set(Calendar.MINUTE, Constants.NUMBER_59);
//        return calendar.getTime();
//    }

    public static Date getDateBegin(Date date){
        String dateBeginStr = format(date, FORMAT_DATE_ONLY) + " 00:00:00";
        return parse(dateBeginStr, FORMAT_NORMAL);
    }
    
    public static Date getDateEnd(Date date){
    	String dateEndStr = format(date, FORMAT_DATE_ONLY) + " 23:59:59";
    	return parse(dateEndStr, FORMAT_NORMAL);
    }
    /**
     * Thu May 28 18:23:17 CST 2015 转换日期
     * @param dateStr
     * @return
     * @throws ParseException 
     */
    public static Date parseDate(String dateCSTStr){
    	try {
    		SimpleDateFormat sdf= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
			return sdf.parse(dateCSTStr);
		} catch (ParseException e) {
			log.error("解析CST日期异常", e);
		}
    	return null;
    }
    
}
