package cn.tekism.mall.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 队列处理工具类
 * @author zonghuan
 *
 */

public class RequestUtil
{
	/**
	 * 从HTTP请求中获取对象
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static void createObjectfromRequest(HttpServletRequest request, Object model)
	{
		if(!ObjectUtils.equals(model, null))
		{
			Field[] fields = model.getClass().getDeclaredFields();
			BeanWrapper wrapper = new BeanWrapperImpl(model);
			for(Field field : fields)
			{
				if(field.getModifiers() != 25)
				{
					String name = field.getName();
					String value = request.getParameter(name);
					if(StringUtils.isNotEmpty(value))
					{
						PropertyValue property = new PropertyValue(name, value);
						wrapper.setPropertyValue(property);
					}
				}
			}
		}
	}
	
	/**
	 * 获取请求端IP地址
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddress(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.isEmpty() || ip.equalsIgnoreCase("unknown"))
		{
			ip = request.getHeader("X-Real-IP");
			if(ip == null || ip.isEmpty() || ip.equalsIgnoreCase("unknown"))
			{
				ip = request.getHeader("WL-Proxy-Client-IP");
				if(ip == null || ip.isEmpty() || ip.equalsIgnoreCase("unknown"))
				{
					ip = request.getRemoteAddr();
				}
			}
		}
		return ip;
	}
	
	/**
	 * 获取UTF8格式参数
	 * @param name
	 * @return
	 */
	public static String getUTF8Parameter(HttpServletRequest request, String name)
	{
		String value = null;
		try
		{
			value = request.getParameter(name);
			value = new String(value != null ? value.getBytes("ISO-8859-1"): ("").getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取参数值数组
	 * @param name
	 * @return
	 */
	public static String[] getParameters(HttpServletRequest request, String name)
	{
		return request.getParameterValues(name);
	}
	
	/**
	 * 获取Integer[]类型参数值数组
	 * @param name
	 * @return
	 */
	public static Integer[] getIntegerParameters(HttpServletRequest request, String name)
	{
		List<Integer> parameterValues = new LinkedList<>();
		String[] values = request.getParameterValues(name);
		if(values != null)
		{
			for(String value : values)
			{
				try
				{
					parameterValues.add(Integer.valueOf(value));
				}
				catch(Exception ex)
				{
					
				}
			}
		}
		return parameterValues.toArray(new Integer[parameterValues.size()]);
	}
	
	/**
	 * 获取Long[]类型参数值数组
	 * @param name
	 * @return
	 */
	public static Long[] getLongParameters(HttpServletRequest request, String name)
	{
		List<Long> parameterValues = new LinkedList<>();
		String[] values = request.getParameterValues(name);
		if(values != null)
		{
			for(String value : values)
			{
				try
				{
					parameterValues.add(Long.valueOf(value));
				}
				catch(Exception ex)
				{
					
				}
			}
		}
		return parameterValues.toArray(new Long[parameterValues.size()]);
	}
	
	/**
	 * 获取Double[]类型参数值数组
	 * @param name
	 * @return
	 */
	public static Double[] getDoubleParameters(HttpServletRequest request, String name)
	{
		List<Double> parameterValues = new LinkedList<>();
		String[] values = request.getParameterValues(name);
		if(values != null)
		{
			for(String value : values)
			{
				try
				{
					parameterValues.add(Double.valueOf(value));
				}
				catch(Exception ex)
				{
					
				}
			}
		}
		return parameterValues.toArray(new Double[parameterValues.size()]);
	}
	
	/**
	 * 获取Boolean[]类型参数值数组
	 * @param name
	 * @return
	 */
	public static Boolean[] getBooleanParameters(HttpServletRequest request, String name)
	{
		List<Boolean> parameterValues = new LinkedList<>();
		String[] values = request.getParameterValues(name);
		if(values != null)
		{
			for(String value : values)
			{
				try
				{
					parameterValues.add(Boolean.valueOf(value));
				}
				catch(Exception ex)
				{
					
				}
			}
		}
		return parameterValues.toArray(new Boolean[parameterValues.size()]);
	}
	
	/**
	 * 获取整型参数值
	 * @param name
	 * @return
	 */
	public static Integer getIntParameter(HttpServletRequest request, String name)
	{
		Integer value = null;
		try
		{
			String parameterValue = request.getParameter(name);
			value = Integer.valueOf(parameterValue);
		}
		catch(Exception ex)
		{
			
		}
		return value;
	}
	
	/**
	 * 获取Long类型参数
	 * @param name
	 * @return
	 */
	public static Long getLongParameter(HttpServletRequest request, String name)
	{
		Long value = null;
		try
		{
			String parameterValue = request.getParameter(name);
			if(StringUtils.isNotEmpty(parameterValue))
				value = Long.valueOf(parameterValue);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	/**
	 * 获取Double类型参数值
	 * @param name
	 * @return
	 */
	public static Double getDoubleParameter(HttpServletRequest request, String name)
	{
		Double value = null;
		try
		{
			String parameterValue = request.getParameter(name);
			if(StringUtils.isNotEmpty(parameterValue))
				value = Double.valueOf(parameterValue);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取Float类型参数
	 * @param name
	 * @return
	 */
	public static Float getFloatParameter(HttpServletRequest request, String name)
	{
		Float value = null;
		try
		{
			String parameterValue = request.getParameter(name);
			if(StringUtils.isNotEmpty(parameterValue))
				value = Float.valueOf(parameterValue);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取Boolean类型参数值
	 * @param name
	 * @return
	 */
	public static Boolean getBooleanParameter(HttpServletRequest request, String name)
	{
		Boolean value = false;
		try
		{
			String parameterValue = request.getParameter(name);
			if(StringUtils.isNotEmpty(parameterValue))
				value = Boolean.valueOf(parameterValue);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 获取请求
	 * @return
	 */
	public static HttpServletRequest getRequest()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获取基本路径
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(request.getScheme() + "://");
		builder.append(request.getServerName());
		int port = request.getServerPort();
		if(port != 80)
			builder.append(":" + port);
		builder.append(request.getContextPath());
		return builder.toString();
	}
	
	/**
	 * 获取除模式之外的基本路径
	 * @param request
	 * @return
	 */
	public static String basePathWithoutSechme(HttpServletRequest request)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(request.getServerName());
		int port = request.getServerPort();
		if(port != 80 && port != 443)
		{
			builder.append(":" + port);
		}
		builder.append(request.getContextPath());
		return builder.toString();
	}
	
	/**
	 * 获取Session
	 * @return
	 */
	public static HttpSession getSession()
	{
		HttpSession session = null;
		try
		{
			session = getRequest().getSession();
		}
		catch(Exception ex)
		{
			
		}
		return session;
	}
}
