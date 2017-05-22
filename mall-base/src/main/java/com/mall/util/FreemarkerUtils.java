package com.mall.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;


public final class FreemarkerUtils
{
	private static Configuration configuration;
	
	private static final ConvertUtilsBean convertUtil;
	static
	{
		convertUtil = new ConvertUtilsBean();
	}
	
	/**
	 * 处理模板字符串
	 * @param template
	 * @param model
	 * @return
	 */
	public static String process(String template, Map<String, Object> model)
	{
		ApplicationContext applicationContext = SpringUtil.getApplicationContext();
		if(applicationContext != null && configuration == null)
		{
			try
			{
				FreeMarkerConfigurer freemarkerConfigurer = (FreeMarkerConfigurer)SpringUtil.getBean("freemarkerConfig", FreeMarkerConfigurer.class);
				if(freemarkerConfigurer != null)
				{
					configuration = freemarkerConfigurer.getConfiguration();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		if(configuration == null)
		{
			configuration = createFreemarkerConfiguration();
		}
		
		return process(template, model, configuration);
	}
	
	/**
	 * 处理字符串模板
	 * @param template
	 * @param model
	 * @param configuration
	 * @return
	 */
	public static String process(String template, Map<String, Object> model, Configuration configuration)
	{
		//check
		if(template == null)
			return null;
		if(configuration == null)
			configuration = createFreemarkerConfiguration();		
		
		StringWriter writer = new StringWriter();
		try
		{
			(new Template("template", new StringReader(template), configuration)).process(model, writer);
		}
		catch(TemplateException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return writer.toString();
	}
	
	/**
	 * 获取参数值
	 * @param name
	 * @param clazz
	 * @param params
	 * @return
	 * @throws TemplateModelException
	 */
	public static Object getParameter(String name, Class<?> clazz, Map<String, Object> params) throws TemplateModelException
	{
		Assert.hasText(name);
		Assert.notNull(clazz);
		Assert.notNull(params);
		TemplateModel templateModel = (TemplateModel)params.get(name);
		if(templateModel == null)
			return null;
		else
		{
			Object object = DeepUnwrap.unwrap(templateModel);
			return convertUtil.convert(object, clazz);
		}
	}
	
	/**
	 * 获取变量值
	 * @param name
	 * @param env
	 * @return
	 * @throws TemplateModelException
	 */
	public static TemplateModel getVariable(String name, Environment env) throws TemplateModelException
	{
		Assert.hasText(name);
		Assert.notNull(env);
		return env.getVariable(name);
	}
	
	/**
	 * 设置变量值
	 * @param variables
	 * @param env
	 * @throws TemplateModelException
	 */
	public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateModelException
    {
        Assert.notNull(variables);
        Assert.notNull(env);
        
        BeansWrapperBuilder wrapperBuilder = new BeansWrapperBuilder(Configuration.VERSION_2_3_21);
        ObjectWrapper objectWrapper = wrapperBuilder.build();
        
        for(Iterator<String> iterator = variables.keySet().iterator(); iterator.hasNext();)
        {
            String key = iterator.next();
            Object obj = variables.get(key);
            if(obj instanceof TemplateModel)
                env.setVariable(key, (TemplateModel)obj);
            else
            {
            	env.setVariable(key, objectWrapper.wrap(obj));
            } 
        }

    }
	
	/**
	 * 创建Freemarker配置
	 * @return  返回Freemarker配置对象
	 */
	public static Configuration createFreemarkerConfiguration()
	{
		Configuration config = new Configuration(Configuration.VERSION_2_3_21);
		BeansWrapperBuilder wrapperBuilder = new BeansWrapperBuilder(Configuration.VERSION_2_3_21);
		
		config.setObjectWrapper(wrapperBuilder.build());
		config.setDefaultEncoding("utf-8");
		config.setOutputEncoding("utf-8");
		config.setNumberFormat("#.####");
		config.setDateFormat("yyyy-MM-dd");
		config.setTimeFormat("HH:mm:ss");
		config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		config.setLocale(Locale.SIMPLIFIED_CHINESE);
		
		return config;
	}
}
