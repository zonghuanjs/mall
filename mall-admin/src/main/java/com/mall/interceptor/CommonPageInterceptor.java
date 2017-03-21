package com.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.tekism.mall.util.RequestUtil;

/**
 * 所有页面的拦截器,为所有页面添加通用数据,如base
 * @author zonghuan
 *
 */

@Component
public class CommonPageInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
	{
		if(mv != null && mv.isReference())
		{
			String view = mv.getViewName().toLowerCase();
			if(!view.startsWith("redirect:"))
			{
				mv.addObject("base", RequestUtil.getBasePath(request));
			}			
		}
	}	
}
