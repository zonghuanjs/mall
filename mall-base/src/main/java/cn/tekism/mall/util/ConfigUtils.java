package cn.tekism.mall.util;

import java.lang.reflect.Field;

/**
 * 系统配置工具类
 * @author zonghuan
 *
 */

public final class ConfigUtils
{
	/**
	 * 生成指定对象指定字段的KEY
	 * @param clazz			配置对象
	 * @param field			配置字段
	 * @return 返回整合了类名和字段名的存储KEY
	 */
	public static String makeFieldKey(Class<?> clazz, Field field)
	{
		StringBuffer keyBuilder = new StringBuffer();
		if(clazz != null && field != null)
		{
			keyBuilder.append(clazz.getSimpleName()).append(".").append(field.getName());
		}
		return keyBuilder.toString();
	}
	
	/**
	 * 读取配置值到指定对象
	 * @param obj			存储配置对象
	 * @param configable	配置访问接口
	 */
	public static void readConfig(Object obj, Configable configable)
	{
		if(obj == null || configable == null)
		{
			return;
		}
		
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields)
		{
			String key = makeFieldKey(clazz, field);
			Object value = configable.read(key, field.getType());
			if(!"".equals(key) && value != null)
			{
				field.setAccessible(true);
				try
				{
					field.set(obj, value);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				field.setAccessible(false);
			}
		}
	}
	
	/**
	 * 将配置存储到配置访问接口中
	 * @param obj			配置对象
	 * @param configable	配置访问接口
	 */
	public static void saveConfig(Object obj, Configable configable)
	{
		if(obj == null || configable == null)
		{
			return;
		}
		
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields)
		{
			try
			{
				String key = makeFieldKey(clazz, field);
				Object value = field.get(obj);
				if(!"".equals(key) && value != null)
				{
					configable.write(key, value);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
