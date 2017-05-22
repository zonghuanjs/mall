package com.mall.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.mall.bean.SystemConfig;
import com.mall.entity.Admin;
import com.mall.entity.Log;
import com.mall.log.annotation.LogMethod;
import com.mall.service.AdminService;
import com.mall.service.LogService;
import com.mall.util.RequestUtil;

/**
 * 系统日志处理程序
 * @author zonghuan
 *
 */

@Component
@Aspect
public class LogBussiness
{
	/**
	 * 日志服务
	 */
	@Resource
	private LogService service;
	/**
	 * 管理员服务
	 */
	@Resource
	private AdminService adminService;
	
	/**
	 * 系统日志切点
	 */
	@Pointcut("@annotation(com.mall.log.annotation.LogMethod)")
	public void logMethod()
	{
		
	}
	
	/**
	 * 日志处理程序
	 * @param point		日志切点
	 * @return
	 */
	@Around("logMethod()")
	public Object processLogMessage(ProceedingJoinPoint point)
	{
		Object object = null;
		
		LogMethod logMethod = getLogMethod(point);
		
		try
		{
			object = point.proceed();
		}
		catch(Throwable tx)
		{
			tx.printStackTrace();
		}
		
		doLogTransaction(logMethod);
		
		return object;
	}
	
	/**
	 * 执行具体的日志记录处理
	 * @param method		日志注解实例
	 */
	public void doLogTransaction(LogMethod method)
	{
		if(method != null)
		{
			HttpServletRequest request = RequestUtil.getRequest();
			if(request == null)
			{
				return;
			}
						
			HttpSession session = request.getSession();
			Long adminId = (Long)session.getAttribute(SystemConfig.ADMIN_LOGIN_KEY);
			Admin admin = null;
			
			Log log = new Log();			
			log.setCreateDate(new Date());
			log.setModifyDate(new Date());
			log.setIp(RequestUtil.getRemoteIpAddress(request));
			log.setOperation(method.message());
			if(adminId != null)
			{
				admin = adminService.get(adminId);
				if(admin != null)
				{
					log.setOperator(admin.getUsername());
				}
			}
			
			if(method.parameter().length > 0)
			{
				StringBuffer builder = new StringBuffer();
				if(method.type() != OperationType.delete)
				{
					for(String name : method.parameter())
					{
						builder.append(",").append(name).append("=");
						builder.append(request.getParameter(name));
					}
				}
				else
				{
					for(String name : method.parameter())
					{
						builder.append(",").append(name).append("={");
						for(String value : request.getParameterValues(name))
						{
							builder.append(value).append(",");
						}
						builder.deleteCharAt(builder.length() - 1);
						builder.append("}");
					}
				}
				
				builder.deleteCharAt(0);
				log.setParameter(builder.toString());
			}
			
			switch(method.type())
			{
				case login:
				{
					StringBuffer builder = new StringBuffer("账号");
					builder.append(request.getParameter("username")).append("尝试登录,");
					if(adminId != null)
					{
						builder.append("登录成功!");
					}
					else
					{
						builder.append("登录失败!");
					}
					log.setContent(builder.toString());
					break;
				}
				
				case logout:
				{
					StringBuffer builder = new StringBuffer("管理员");
					if(admin != null)
					{
						builder.append(admin.getUsername());
						log.setOperator(admin.getUsername());
					}
					builder.append("离开系统");
					log.setContent(builder.toString());
					break;
				}
				
				default:
				{
					log.setContent(method.message());
				}
			}
			
			service.add(log);
		}
	}
	
	/**
	 * 获取注解实例对象
	 * @param point
	 * @return
	 */
 	private LogMethod getLogMethod(ProceedingJoinPoint point)
	{
		LogMethod logMethod = null;
		
		if(point != null)
		{
			String targetName = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			Object[] arguments = point.getArgs();
			
			try
			{
				Class<?> targetClass = Class.forName(targetName);
				Method[] methods = targetClass.getMethods();
				
				for(Method method : methods)
				{
					if(method.getName().equals(methodName))
					{
						Class<?>[] paramClass = method.getParameterTypes();
						if(paramClass.length == arguments.length)
						{
							logMethod = method.getAnnotation(LogMethod.class);
						}
					}
				}
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
		}
		
		return logMethod;
	}
}
