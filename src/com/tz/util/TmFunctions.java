package com.tz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tz.util.ip.TmIpUtil;


/**
 * 
 * JSP函数自定义标签
 * TmFunctions<BR>
 * 创建人:潭州学院-keke <BR>
 * @version 1.0.0
 *
 */
public class TmFunctions {
	
	/**
	 * 日期具体的几秒钟以前
	 * 方法名：dateString<BR>
	 * 创建人：潭州学院-keke <BR>
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String dateToString(String startTime){
		return TmDateUtil.getTimeFormat(startTime);
	}
	
	/**
	 *  日期具体的几秒钟以前
	 * 方法名：dateToString2<BR>
	 * 创建人：潭州学院-keke <BR>
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String dateToString2(Date startTime){
		return TmDateUtil.getTimeFormat(startTime);
	}

	
	/**
	 * 通过ip地址获取真实地址
	 * 方法名：ipAddress
	 * @param ip
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String ipAddress(String ip){
		return TmIpUtil.ipLocation(ip);
	}
	
	/**
	 * 格式化日期的自定义函数
	 * 方法名：dateFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * @param dateString
	 * @param format
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 * System.out.println(dateFormat("2014-12-14 12:12:12", "yyyy"));年份
		System.out.println(dateFormat("2014-12-14 12:12:12", "MM"));月份
		System.out.println(dateFormat("2014-12-14 12:12:12", "dd"));天
		System.out.println(dateFormat("2014-12-14 12:12:12", "HH:mm:ss"));24小时制
		System.out.println(dateFormat("2014-12-14 12:12:12", "hh:mm:ss"));12小时制
		System.out.println(dateFormat("2014-12-14 12:12:12", "yyyy-MM-dd"));年月日
		System.out.println(dateFormat("2014-12-14 12:12:12", "yyyy-MM-dd HH:mm"));年月日 时分
		System.out.println(dateFormat("2014-12-14 12:12:12", "yyyy-MM-dd HH:mm:ss"));年月日 时分秒
	 */
	public static String dateFormat(String dateString,String format){
		if(TmStringUtils.isEmpty(dateString))return "";
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			String ds =  new SimpleDateFormat(format).format(date);
			return ds;
		} catch (ParseException e) {
			return "";
		}
	}
	
	/**
	 * 
	 * 方法名：formateDate<BR>
	 * 创建人：潭州学院-keke <BR>
	 * @param date
	 * @param format
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String formateDate(Date date,String format){
		if(date==null)return "";
		String ds =  new SimpleDateFormat(format).format(date);
		return ds;
	}
	
	/**
	 * 根据视频时长获取分：秒
	 * 方法名：mpstime
	 * @param timeline
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String mpstime(int timeline){
		int minute = timeline / 60;
		int second = timeline % 60;
		return (minute<10?"0"+minute:minute+"")+":"+(second<10?"0"+second:second+"");
	}
}
