package com.mall.controller.base;

import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import cn.tekism.mall.util.RequestUtil;

/**
 * 
 * @author ChenMingcai
 * 2014-09-26
 *
 */

public class BaseController implements ServletContextAware
{
	/**
	 * 邮件验证码KEY
	 */
	public static final String EMAIL_KEY = "email_code";
	
	/**
	 * 邮件有效时间KEY
	 */
	public static final String EMAIL_EXPIRE = "email_expire";
	
	/**
	 * 邮件有效时间
	 */
	public static final int EMAIL_VALID = 120000;
	
	/**
	 * 手机验证码KEY
	 */
	public static final String MOBIlE_KEY = "mobile_key";
	
	/**
	 * 手机验证码过期时间
	 */
	public static final String MOBILE_EXPIRE = "mobile_expire";
	
	/**
	 * 手机验证码有效时间
	 */
	public static final int MOBILE_VALID = 300000;
	
	/**
	 * 手机验证码长度
	 */
	public static final int MOBILE_CODE_LENGTH = 4;
	
	private ServletContext servletContext;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void setServletContext(ServletContext servletContext)
	{
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
	
	/**
	 * 获取Servlet上下文
	 * @return
	 */
	public ServletContext getServletContext()
	{
		return this.servletContext;
	}
	
	/**
	 * 获取应用程序路径
	 * @return
	 */
	public String getBasePath()
	{
		return RequestUtil.getBasePath(getRequest());
	}
	
	/**
	 * 获取Session
	 * @return
	 */
	public HttpSession getSession()
	{
		return this.session;
	}
	
	/**
	 * 获取请求
	 * @return
	 */
	public HttpServletRequest getRequest()
	{
		return this.request;
	}
	
	/**
	 * 获取参数值
	 * @param name
	 * @return
	 */
	public String getParameter(String name)
	{
		return this.request.getParameter(name);
	}
	
	/**
	 * 获取UTF8格式参数
	 * @param name
	 * @return
	 */
	public String getUTF8Parameter(String name)
	{
		return RequestUtil.getUTF8Parameter(this.getRequest(), name);
	}
	
	/**
	 * 获取参数值数组
	 * @param name
	 * @return
	 */
	public String[] getParameters(String name)
	{
		return this.request.getParameterValues(name);
	}
	
	/**
	 * 获取Integer[]类型参数值数组
	 * @param name
	 * @return
	 */
	public Integer[] getIntegerParameters(String name)
	{
		return RequestUtil.getIntegerParameters(this.getRequest(), name);
	}
	
	/**
	 * 获取Long[]类型参数值数组
	 * @param name
	 * @return
	 */
	public Long[] getLongParameters(String name)
	{		
		return RequestUtil.getLongParameters(this.getRequest(), name);
	}
	
	/**
	 * 获取Double[]类型参数值数组
	 * @param name
	 * @return
	 */
	public Double[] getDoubleParameters(String name)
	{		
		return RequestUtil.getDoubleParameters(this.getRequest(), name);
	}
	
	/**
	 * 获取Boolean[]类型参数值数组
	 * @param name
	 * @return
	 */
	public Boolean[] getBooleanParameters(String name)
	{		
		return RequestUtil.getBooleanParameters(this.getRequest(), name);
	}
	
	/**
	 * 获取整型参数值
	 * @param name
	 * @return
	 */
	public Integer getIntParameter(String name)
	{		
		return RequestUtil.getIntParameter(this.getRequest(), name);
	}
	
	/**
	 * 获取Long类型参数
	 * @param name
	 * @return
	 */
	public Long getLongParameter(String name)
	{		
		return RequestUtil.getLongParameter(request, name);
	}
	/**
	 * 获取Double类型参数值
	 * @param name
	 * @return
	 */
	public Double getDoubleParameter(String name)
	{
		return RequestUtil.getDoubleParameter(request, name);
	}
	
	/**
	 * 获取Float类型参数
	 * @param name
	 * @return
	 */
	public Float getFloatParameter(String name)
	{		
		return RequestUtil.getFloatParameter(request, name);
	}
	
	/**
	 * 获取Boolean类型参数值
	 * @param name
	 * @return
	 */
	public Boolean getBooleanParameter(String name)
	{
		return RequestUtil.getBooleanParameter(request, name);
	}
	
	/**
	 * 获取客户端访问IP
	 * @return
	 */
	public String getRemoteIp()
	{
		return RequestUtil.getRemoteIpAddress(request);
	}
	/**
	 * 检验验证码
	 * @param code
	 * @return
	 */
	public boolean checkValidateCode(String code)
	{
		String s = (String) this.session.getAttribute("validate_code");
		this.session.removeAttribute("validate_code");
		
		if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(s))
		{
			if(code.equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证手机校验码
	 * 商城APP mobile
	 * 
	 * @param code 
	 * @return   0:成功  1：超时  2 ：失败 验证码错误 
	 */
	public int checkMobileCaptcha(String code)
	{
		int errorCode = 0;
		
		if(StringUtils.isNotEmpty(code))
		{
			Object targetCode = this.getSession().getAttribute(MOBIlE_KEY);
			if(targetCode == null)
			{
				return 1;
			}
			
			String captcha = targetCode.toString();
			
			if(captcha !=null)
			{
				if(StringUtils.equalsIgnoreCase(code, captcha))
				{
					
					long expire = (long)this.getSession().getAttribute(MOBILE_EXPIRE);
					Calendar calendarOther = Calendar.getInstance();
					calendarOther.setTimeInMillis(expire);
					
					Calendar calendar = Calendar.getInstance();
					if(calendar.compareTo(calendarOther) > 0)
					{
						errorCode = 1; //超时
					}
				}else
				{
					errorCode = 2; //输入验证码错误
				}
			}else
			{
				errorCode = 2;  //session不存在
			}
		}else{
			errorCode = 2;
		}

		return errorCode;
	}
	
		
	/**
	 * 验证邮箱校验码
	 * 
	 * @param code 
	 * @return   0:成功  1：超时  2 ：失败 验证码错误 
	 */
	public int checkEmailCaptcha(String code)
	{
		int errorCode = 0;
		
		if(StringUtils.isNotEmpty(code))
		{
			Object targetCode = this.getSession().getAttribute(EMAIL_KEY);
			if(targetCode == null)
			{
				return 1;
			}
			
			String captcha = targetCode.toString();
			
			if(captcha !=null)
			{
				if(StringUtils.equalsIgnoreCase(code, captcha))
				{
					
					long expire = (long)this.getSession().getAttribute(EMAIL_EXPIRE);
					Calendar calendarOther = Calendar.getInstance();
					calendarOther.setTimeInMillis(expire);
					
					Calendar calendar = Calendar.getInstance();
					if(calendar.compareTo(calendarOther) > 0)
					{
						errorCode = 1; //超时
					}
				}else
				{
					errorCode = 2; //输入验证码错误
				}
			}else
			{
				errorCode = 2;  //session不存在
			}
		}else{
			errorCode = 2;
		}

		return errorCode;
	}
	
}
