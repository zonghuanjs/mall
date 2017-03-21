package cn.tekism.mall.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 时间操作工具类
 * @author ChenMingcai
 *
 */

public final class TimeUtils
{
	/**
	 * 将字符串转换成Date
	 * @param timeFormat	时间字符串格式[yyyy-MM-dd hh:mm:ss]
	 * @param dateStr		时间字符串
	 * @return	Date对象; 否则返回null
	 */
	public static Date fromDateTimeString(String timeFormat, String dateStr)
	{
		Date date = null;
		Format format = new SimpleDateFormat(timeFormat);
		try
		{
			date = (Date)format.parseObject(dateStr);
		}
		catch(Exception ex)
		{

		}
		return date;
	}
	
	/**
	 * 将时间对象格式化为时间字符串
	 * @param date 时间对象
	 * @return 格式化时间字符串[yyyy-MM-dd hh:mm:ss]
	 */
	public static String formatDate(String dateFormat, Date date)
	{
		String dateStr = "";
		if(StringUtils.isNotEmpty(dateFormat) && date != null)
		{
			Format format = new SimpleDateFormat(dateFormat);
			dateStr = format.format(date);
		}
		
		return dateStr;
	}
	
	/**
	 * 将时间对象date延迟
	 * @param date		时间对象
	 * @param minutes	延迟分钟数
	 * @return
	 */
	public static Date delayTime(Date date, int minutes)
	{
		Date targetDate = null;
		if(date != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE, minutes);
			targetDate = cal.getTime();
		}
		return targetDate;
	}
	
	/**
	 * 获取时间字段
	 * @param date
	 * @param field
	 * @return
	 */
	public static int getField(Date date, int field)
	{
		int value = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch(field)
		{
			case Calendar.MONTH:
			{
				value = cal.get(Calendar.MONTH) + 1;
				break;
			}
			
			case Calendar.DAY_OF_WEEK:
			{
				value = cal.get(Calendar.DAY_OF_WEEK) - 1;
				break;
			}
			
			default:
			{
				value = cal.get(field);
			}
		}
		return value;
	}
}
