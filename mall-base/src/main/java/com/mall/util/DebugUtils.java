package com.mall.util;

import org.jboss.logging.Logger;

import com.mall.bean.SystemConfig;


public final class DebugUtils
{
	private static Logger logger;
	
	static
	{
		logger = Logger.getLogger(DebugUtils.class);
	}
	
	/**
	 * 输出调试信息
	 * @param caller  	位置
	 * @param info		调试信息
	 */
	public static void info(Class<?> caller, String info)
	{
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		if(config.isDebugable())
		{
			logger.info("(" + caller.getName() + "):[" + info + "]");			
		}
	}
	
	/**
	 * 输出重要调试信息
	 * @param caller
	 * @param info
	 */
	public static void fatal(Class<?> caller, String info)
	{
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		if(config.isDebugable())
		{
			logger.fatal("(" + caller.getName() + "):[" + info + "]");
		}
	}
}
