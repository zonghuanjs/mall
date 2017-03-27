package cn.tekism.mall.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 队列处理工具类
 * @author zonghuan
 *
 */
@WebListener
public class SpringUtil implements ServletContextListener
{
	private static ApplicationContext applicationContext;
	
	
	/**
	 * 获取context
	 * @return
	 */
	public static ApplicationContext getApplicationContext()
	{
		return SpringUtil.applicationContext;
	}
	
	/**
	 * 获取Bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name)
	{
		Assert.hasText(name);
		Object object = null;
		try
		{
			object = applicationContext.getBean(name);
		}
		catch(Exception ex)
		{
			
		}
		return object;
	}
	
	/**
	 * 获取Bean
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static Object getBean(String name, Class<?> clazz)
	{
		Object object = null;
		try
		{
			object = applicationContext.getBean(name, clazz);
		}
		catch(Exception ex)
		{
			
		}
		return object;
	}

	@Override
	public void contextDestroyed(ServletContextEvent event)
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
	{
		if(applicationContext == null)
		{
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		}
	}
	
}
