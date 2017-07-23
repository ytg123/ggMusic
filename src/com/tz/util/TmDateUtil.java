package com.tz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * TmDateUtil<BR>
 * 创建人:潭州学院-keke <BR>
 * 时间：2014年11月11日-下午10:29:09 <BR>
 * @version 1.0.0
 *
 */
public class TmDateUtil {

	/**
	 * 日期转换
	 * 方法名：dateToString<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:28:41 <BR>
	 * @param time
	 * @return Date<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Date dateToString(String time){
		Date startTime = null;
		try {
			 startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	
	
	/**
	 * 
	 * 方法名：getTimeFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:28:21 <BR>
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String getTimeFormat(String startTime){
		return getTimeFormat(dateToString(startTime));
	}
	
	/**
	 * 获取日期几分钟前，几年前
	 * 方法名：getTimeFormat<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:27:54 <BR>
	 * @param startTime
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String getTimeFormat(Date startTime){
		try{
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills)/1000;//秒
			long day_diff  = (long) Math.floor(diff/86400);//天
			StringBuffer buffer = new StringBuffer();
			if(day_diff<0){
				return "[error],时间越界...";
			}else{
				if(day_diff==0 && diff<60){
					if(diff==0)diff=1;
					buffer.append(diff+"秒前");
				}else if(day_diff==0 && diff<120){
					buffer.append("1 分钟前");
				}else if(day_diff==0 && diff<3600){
					buffer.append(Math.round(Math.floor(diff/60))+"分钟以前");
				}else if(day_diff==0 && diff<7200){
					buffer.append("1小时前");
				}else if(day_diff==0 && diff<86400){
					buffer.append(Math.round(Math.floor(diff/3600))+"小时前");
				}else if(day_diff==1){
					buffer.append("1天前");
				}else if(day_diff<7){
					buffer.append(day_diff+"天前");
				}else if(day_diff <30){
					buffer.append(Math.round(Math.ceil( day_diff / 7 )) + " 星期前");
				}else if(day_diff >=30 && day_diff<=179 ){
					buffer.append(Math.round(Math.ceil( day_diff / 30 )) + "月前");
				}else if(day_diff >=180 && day_diff<365){
					buffer.append("半年前");
				}else if(day_diff>=365){
					buffer.append(Math.round(Math.ceil( day_diff /30/12))+"年前");
				}
			}
			return buffer.toString();
		}catch(Exception ex){
			return "";
		}
	}
}
