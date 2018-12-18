/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.xiaoleilu.hutool.date.DateField;
import com.xiaoleilu.hutool.date.DateTime;

/**
 * 日期处理工具类
 * 
 * @author wujing
 */
public final class DateUtil {

	/**
	 * 此类不需要实例化
	 */
	private DateUtil() {
	}

	/**
	 * 时间转换：长整型转换为日期字符型
	 * 
	 * @param format
	 *            格式化类型：yyyy-MM-dd
	 * @param time
	 *            13位有效数字：1380123456789
	 * 
	 * @return 格式化结果 (yyyy-MM-dd)
	 */
	public static String formatToString(String format, long time) {
		if (time == 0) {
			return "";
		}
		return new SimpleDateFormat(format).format(new Date(time));
	}

	/**
	 * 时间转换：日期字符型转换为长整型
	 * 
	 * @param format
	 *            格式化类型：yyyy-MM-dd
	 * 
	 * @return 13位有效数字 (1380123456789)
	 */
	public static long formatToLong(String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return Timestamp.valueOf(f.format(new Date())).getTime();
	}

	/**
	 * 获取当前年份
	 * 
	 * @return yyyy (2016)
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return MM (06)
	 */
	public static String getMonth() {
		Calendar cal = Calendar.getInstance();
		return new DecimalFormat("00").format(cal.get(Calendar.MONTH));
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			String dt = dateStr.replaceAll("-", "/");
			dt = dateStr;
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", "0");
			}
			Date date = dateFormat.parse(dt);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能描述：
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：返回年份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期 yyyy-MM-dd 格式
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时间 HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时间 yyyy-MM-dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 当前日期加上年
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYear(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相加
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 * @throws ParseException
	 */
	public static String add(String date, int day) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		long d = df.parse(date).getTime();
		long millis = d + ((long) day) * 24 * 3600 * 1000;
		return df.format(new Date(millis));
	}

	/**
	 * 功能描述：日期相减
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		Date date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的最后一天
	 * 
	 * @param strdate
	 *            String 字符型日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate) {
		Date date = parseDate(getMonthBegin(strdate));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 功能描述：常用的格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @return String 日期字符串 yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 计算日期之间的天数
	 * 
	 * @param beginDate
	 *            开始日期 yyy-MM-dd
	 * @param endDate
	 *            结束日期 yyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static int getDay(String beginDate, String endDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long to = df.parse(endDate).getTime();
		long from = df.parse(beginDate).getTime();
		return (int) ((to - from) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算日期之间的年数
	 * 
	 * @param startYear
	 *            开始日期 yyy-MM-dd
	 * @param endYear
	 *            结束日期 yyy-MM-dd
	 * @return
	 */
	public static int yearDateDiff(String startYear, String endYear) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		try {
			startDate.setTime(sdf.parse(startYear));
			endDate.setTime(sdf.parse(endYear));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
	}

	/**
	 * 上个月
	 * 
	 * @return 上个月
	 */
	public static DateTime lastMonth() {
		return offsetMonth(new DateTime(), -1);
	}

	/**
	 * 偏移月
	 * 
	 * @param date
	 *            日期
	 * @param offset
	 *            偏移月数，正数向未来偏移，负数向历史偏移
	 * @return 偏移后的日期
	 */
	public static DateTime offsetMonth(Date date, int offset) {
		return offset(date, DateField.MONTH, offset);
	}

	public static DateTime offset(Date date, DateField dateField, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(dateField.getValue(), offset);
		return new DateTime(cal.getTime());
	}

}
