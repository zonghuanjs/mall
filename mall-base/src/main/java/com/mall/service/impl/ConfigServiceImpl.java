package com.mall.service.impl;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.dao.ConfigDao;
import com.mall.service.ConfigService;

import cn.tekism.mall.util.ConfigUtils;

/**
 * 系统配置服务实现
 * @author zonghuan
 *
 */

@Service
public class ConfigServiceImpl implements ConfigService
{
	@Resource
	private ConfigDao dao;

	@Override
	public Object getConfig(Class<?> clazz)
	{
		Object object = null;
		
		if(clazz != null)
		{
			try
			{
				object = clazz.newInstance();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			if(object != null)
			{
				Field[] fields = clazz.getDeclaredFields();
				for(Field field : fields)
				{
					field.setAccessible(true);
					String key = ConfigUtils.makeFieldKey(clazz, field);
					Object value = dao.read(key, field.getType());
					if(value != null)
					{
						try
						{
							field.set(object, value);
						}
						catch (IllegalArgumentException | IllegalAccessException ex)
						{
							ex.printStackTrace();
						}
					}
					field.setAccessible(false);
				}
			}			
		}
		
		return object;
	}

	@Override
	public void saveConfig(Object obj)
	{
		if(obj == null)
		{
			return;
		}
		
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields)
		{
			field.setAccessible(true);
			
			try
			{
				Object value = field.get(obj);
				if(value != null)
				{
					String key = ConfigUtils.makeFieldKey(clazz, field);
					dao.write(key, value);
				}
			}
			catch(Exception ex)
			{
				
			}
			
			field.setAccessible(false);
		}
		
	}
	
}
