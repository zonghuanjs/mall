package com.mall.util;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import com.mall.bean.SystemConfig;


public class ValidatorUtils
{
	/**
	 * 用户账号格式定义
	 */
	private static final String USER_NAME_EXPRESSION = "^([a-zA-Z0-9]|[_]|[\u4e00-\u9fa5])+$";
	
	/**
	 * 手机号格式定义
	 */
	private static final String MOBILE_EXPRESSION = "^(1[3|4|5|7|8]\\d{9})$";
	
	/**
	 * email格式定义
	 */
	private static final String EMAIL_EXPRESSION = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	/**
	 * 判断是否为合法用户名：此处仅从格式及系统设置方面进行验证
	 * @param name	用户名
	 * @return	返回验证结果
	 */
	public static boolean isValidUsername(String name)
	{
		if(name == null || "".equals(name))
		{
			return false;
		}
		
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		
		//检测长度
		int length = name.length();
		if(length < config.getUserNameMinLength() || length > config.getUserNameMaxLength())
		{
			return false;
		}
		
		//检测是否禁用
		Set<String> nameSet = new HashSet<String>();
		String disableNameString = config.getDisabledUsername();
		if(disableNameString != null && !"".equals(disableNameString))
		{
			Collections.addAll(nameSet, disableNameString.split(","));
		}
		
		if(nameSet.contains(name))
		{
			return false;
		}
		
		//检测账号格式
		if(!Pattern.matches(USER_NAME_EXPRESSION, name))
		{
			return false;
		}
		
		return true;
	}

	/**
	 * 检测是否为有效密码
	 * @param passwd	密码
	 * @return	验证结果
	 */
	public static boolean isValidPasswd(String passwd)
	{
		if(passwd == null || "".equals(passwd))
		{
			return false;
		}
		
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		int length = passwd.length();
		if(length < config.getPasswordMinLength() || length > config.getPasswordMaxLength())
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断手机号是否合法：此处仅从格式上进行验证
	 * @param mobile	手机号
	 * @return	返回验证结果
	 */
	public static boolean isValidMobile(String mobile)
	{
		if(mobile == null || "".equals(mobile))
		{
			return false;
		}
		
		if(!Pattern.matches(MOBILE_EXPRESSION, mobile))
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断email地址是否合法: 此处仅从格式上验证
	 * @param email	邮箱
	 * @return	返回验证结果
	 */
	public static boolean isValidEmail(String email)
	{
		if(email == null || "".equals(email))
		{
			return false;
		}
		
		if(!Pattern.matches(EMAIL_EXPRESSION, email))
		{
			return false;
		}
		
		return true;
	}
}
