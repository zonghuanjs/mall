package cn.tekism.mall.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 
 * @author zonghuan
 *
 */

public final class CookieUtils
{
	/**
	 * 添加Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param path
	 * @param domain
	 * @param secure
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure)
	{
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try
		{
			name = URLEncoder.encode(name, "utf-8");
			value = URLEncoder.encode(value, "utf-8");
			Cookie cookie = new Cookie(name, value);
			if(maxAge != null)
				cookie.setMaxAge(maxAge.intValue());
			if(StringUtils.isNotEmpty(path))
				cookie.setPath(path);
			if(StringUtils.isNotEmpty(domain))
				cookie.setDomain(domain);
			if(secure != null)
				cookie.setSecure(secure.booleanValue());
			response.addCookie(cookie);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 添加Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge)
	{
		addCookie(request, response, name, value, maxAge, "/", null, null);
	}
	
	/**
	 * 添加Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
	{
		addCookie(request, response, name, value, null, "/", null, null);
	}
	
	/**
	 * 获取Cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name)
	{
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if(cookies == null)
			return null;
		String value = null;
		try
		{
			name = URLEncoder.encode(name, "utf-8");
			for (Cookie cookie : cookies)
			{
				if(StringUtils.equals(name, cookie.getName()))
				{
					value = URLDecoder.decode(cookie.getValue(), "utf-8");
					break;
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 移除Cookie
	 * @param request
	 * @param response
	 * @param name
	 * @param path
	 * @param domain
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain)
	{
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try
		{
			name = URLEncoder.encode(name, "utf-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if(StringUtils.isNotEmpty(path))
				cookie.setPath(path);
			if(StringUtils.isNotEmpty(domain))
				cookie.setDomain(domain);
			response.addCookie(cookie);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 移除Cookie
	 * @param request
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name)
	{
		removeCookie(request, response, name, "/", null);
	}
}
