package com.mall.util;

/**
 * 可配置接口
 */

public interface Configable
{
	/**
	 * 读取配置的值
	 * @param key			配置Key
	 * @param clazz			配置的数据类型
	 * @return	如果存储配置Key, 则返回配置的值; 否则返回null
	 */
	public Object read(String key, Class<?> clazz);
	
	/**
	 * 将配置的值存储
	 * @param key			配置key
	 * @param value			配置值
	 */
	public void write(String key, Object value);
}
