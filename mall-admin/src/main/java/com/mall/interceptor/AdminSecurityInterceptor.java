package com.mall.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mall.entity.Admin;
import com.mall.service.AdminService;
import com.mall.util.AuthoritiesUtils;

import cn.tekism.mall.bean.SystemConfig;
import cn.tekism.mall.util.RequestUtil;

/**
 * 
 * @author ChenMingcai
 * 2014-10-10
 *
 */

@Component
public class AdminSecurityInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger logger = LoggerFactory.getLogger(AdminSecurityInterceptor.class);
	
	/**
	 * 登录URI
	 */
	private static final String LOGIN_URL = "/login.do";
	
	/**
	 * 管理服务
	 */
	@Resource
	private AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hander)
	{
		HttpSession session = request.getSession();
		Long adminId = (Long)session.getAttribute(SystemConfig.ADMIN_LOGIN_KEY);
		
		if(adminId == null)
		{
			//未登录, 执行拦截
			try
			{
				response.sendRedirect(RequestUtil.getBasePath(request) + LOGIN_URL);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return false;
		}
		else
		{
			String base = request.getContextPath();
			String uri = request.getRequestURI().substring(base.length());
			
			//管理员已登录
			Admin admin = adminService.get(adminId);
			if(admin != null)
			{
				boolean intercept = AuthoritiesUtils.checkAuthorities(uri, admin.getRoles());
				if(!intercept)
				{
					try
					{
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("非法访问");
						out.close();
					}
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
					logger.warn("管理员({})越权访问URI: {}", admin.getUsername(), uri);
				}
				
				return intercept;				
			}
		}
		
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
	{		
		if(mv != null && mv.isReference())
		{
			String view = mv.getViewName().toLowerCase();
			if(!view.startsWith("redirect:"))
			{
				//非重定向页面添加权限数据
				HttpSession session = request.getSession();
				Long adminId = (Long)session.getAttribute(SystemConfig.ADMIN_LOGIN_KEY);
				if(adminId != null)
				{
					Admin admin = adminService.get(adminId);
					if(admin != null)
					{
						mv.addObject("authorities", admin.getAuthorities());
					}								
				}
			}			
		}
	}
}
