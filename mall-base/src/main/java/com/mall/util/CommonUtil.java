package com.mall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;
/**
 * 
 * @author zonghuan
 *
 */

public class CommonUtil
{
	/**
	 * 获取无中划线的UUID
	 * @return
	 */
	public static String getUUID()
	{
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	/**
	 * 获取随机字符串长度
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length)
	{
		if(length <= 0)
		{
			return "";
		}
		
		char[] randomChar = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
				'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
				'x', 'c', 'v', 'b', 'n', 'm'
		};
		
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < length; i++)
		{
			int idx = Math.abs(random.nextInt() % randomChar.length);
			buffer.append(randomChar[idx]);
		}
		return buffer.toString();
	}
	
	
	public static String getComputeString(){
		char[] numberChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		char[] algorithmChar = {'+','-','×'};
		
		StringBuilder str = new StringBuilder();
		Random random = new Random();
		int a = random.nextInt(numberChar.length);
		str.append(numberChar[a]);
		
		int b = random.nextInt(algorithmChar.length);
		str.append(algorithmChar[b]);
		
		int c = random.nextInt(numberChar.length);
		str.append(numberChar[c]);
		
		str.append('=');
		
		return str.toString();
	}
	
	/**
	 * 生成app校验码  
	 * 
	 * @param  length 指定生成字符串的长度
	 * @return 返回阿拉伯数字的格式字符串
	 */
	public static String getAppIntString(int length)
	{
		if(length <= 0){
			return "";
		}
		
		char[] randomChar = {'0','1','2','3','4','5','6','7','8','9'};
		
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for(int i = 0 ;i < length;i++){
			int idx = Math.abs(random.nextInt() % randomChar.length);
			buffer.append(randomChar[idx]);
		}
		
		return buffer.toString();
	}	
	
	/**
	 * 将字符串转进行MD5转换
	 * @param text
	 * @return
	 */
	public static String toMD5(String text)
	{
		MessageDigest md5 = null;
		StringBuffer builder = new StringBuffer();
		try
		{
			md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes());
			byte[] bytes = md5.digest();
			for(int i = 0; i < bytes.length; i++)
			{
				int k = bytes[i];
				if(k < 0)
					k += 256;
				if(k < 16)
					builder.append("0");
				builder.append(Integer.toHexString(k));
			}
		}
		catch(NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}
		return builder.toString().toUpperCase();
	}
	
	/**
	 * 用时间生成随机文件名
	 * @param ext
	 * @return
	 */
	public static String createFileName(String ext)
	{
		StringBuilder builder = new StringBuilder("File_");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		builder.append(df.format(new Date()));
		return builder.toString() + "." + ext;
	}
	
	/**
	 * 字符数组连接
	 * @param strArray
	 * @param seperator
	 * @return
	 */
	public static String toString(String[] strArray, String seperator)
	{
		StringBuffer buffer = new StringBuffer();
		for(String str : strArray)
		{
			buffer.append(seperator + str);
		}
		buffer.deleteCharAt(0);
		return buffer.toString();
	}
	
	/**
	 * 将字符串转换成Date
	 * @param dateStr
	 * @return
	 */
	public static Date fromDateString(String dateStr)
	{
		Date date = null;
		Format format = new SimpleDateFormat("yyyy-MM-dd");
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
	 * @return 格式化时间字符串(yyyy-MM-dd hh:mm:ss)
	 */
	public static String formatDateTime(Date date)
	{
		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 将时间对象格式化为时间字符串
	 * @param date 时间对象
	 * @return 格式化时间字符串(yyyy-MM-dd)
	 */
	public static String formatDate(Date date)
	{
		Format format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	/**
	 * 将时间对象格式化为时间字符串
	 * @param date 时间对象
	 * @return 格式化时间字符串(yyyyMMdd)
	 */
	public static String formatDateStr(Date date)
	{
		Format format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}
	
	/**
	 * 将字符串转换成Date
	 * @param dateStr
	 * @return
	 */
	public static Date fromDateTimeString(String dateStr)
	{
		Date date = null;
		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
	 * 生成商品编号
	 * @return
	 */
	public static String getProductSn()
	{
		StringBuilder builder = new StringBuilder("PD");
		Format format = new SimpleDateFormat("yyyyMMddhhmmss");
		builder.append(format.format(new Date()));
		builder.append(getRandomString(4).toUpperCase());
		return builder.toString();
	}
	
	/**
	 * 生成订单编号
	 * @returnrpm
	 */
	public static synchronized String createOrderSN()
	{
		StringBuilder builder = new StringBuilder("OD");
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(4).toUpperCase());
		format = new SimpleDateFormat("hhmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 创建退款编号
	 * @return
	 */
	public static synchronized String createRefundSN()
	{
		StringBuilder builder = new StringBuilder();
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(2).toUpperCase());
		format = new SimpleDateFormat("hhmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 创建发货流水号
	 * @return
	 */
	public static synchronized String createShippingSN()
	{
		StringBuilder builder = new StringBuilder("SP");
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(4).toUpperCase());
		format = new SimpleDateFormat("hhmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 创建用户退货流水号
	 * @return
	 */
	public static synchronized String createReturnSn()
	{
		StringBuilder builder = new StringBuilder("RE");
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(4).toUpperCase());
		format = new SimpleDateFormat("hhmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 创建公司寄回给用户流水号
	 * @return
	 */
	public static synchronized String createBackSn()
	{
		StringBuilder builder = new StringBuilder("BA");
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(4).toUpperCase());
		format = new SimpleDateFormat("HHmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 生成唯一的数字字符串
	 * @return
	 */
	public static synchronized String createRandomCode()
	{
		Format format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		Date date = new Date();
		return format.format(date);
	}
	
	/**
	 * 创建退货和维修申请
	 * @return
	 */
	public static synchronized String createApplySN()
	{
		StringBuilder builder = new StringBuilder("APP");
		Format format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		builder.append(format.format(date));
		builder.append(getRandomString(4).toUpperCase());
		format = new SimpleDateFormat("HHmmssSSS");
		builder.append(format.format(date));
		return builder.toString();
	}
	
	
	/**
	 * 短信发送流水号
	 * @return
	 */
	public static synchronized String createSmsSn()
	{
		StringBuilder builder = new StringBuilder("");
		Format format = new SimpleDateFormat("yyyyMMddhhmmssSSSSSS");
		Date date = new Date();
		builder.append(format.format(date));
		return builder.toString();
	}
	
	/**
	 * 将html代码转换为纯文本
	 * @param html
	 * @return
	 */
	public static String htmlToPlainText(String html)
	{
		if(StringUtils.isEmpty(html))
			return "";
		String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
		String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式    
        String regEx_html1 = "<[^>]+";
        
        Pattern  pScript = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(html);
        String retStr = mScript.replaceAll("");
        
        Pattern pStyle = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(retStr);
        retStr = mStyle.replaceAll("");
        
        Pattern pHtml = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(retStr);
        retStr = mHtml.replaceAll("");
        
        Pattern pHtml1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
		Matcher mHtml1 = pHtml1.matcher(retStr);
		retStr = mHtml1.replaceAll("");
		//过滤空白
		//去除空白 
		Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
		Matcher matcher = pattern.matcher(retStr);
		retStr = matcher.replaceAll("");
		return retStr;
	}

}
