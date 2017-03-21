package com.mall.service;

/**
 * 系统配置服务接口
 * @author ChenMingcai
 * 2015-08-04
 * 
 */

public interface ConfigService
{
	/**
	 * 获取指定系统配置类的配置信息
	 * @param clazz		系统配置类
	 * @return	
	 */
	Object getConfig(Class<?> clazz);
	
	/**
	 * 存储系统配置信息
	 * @param obj
	 */
	void saveConfig(Object obj);
}
