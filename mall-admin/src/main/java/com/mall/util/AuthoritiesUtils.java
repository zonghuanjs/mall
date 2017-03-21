package com.mall.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mall.entity.Role;


/**
 * 后台管理权限检测工具集
 * @author ChenMingcai
 * 2015-08-08
 *
 */

public class AuthoritiesUtils
{
	private static final Logger logger = LoggerFactory.getLogger(AuthoritiesUtils.class);
	
	/**
	 * 权限配置文件
	 */
	private static final String configFile = "auth.xml";
	
	/**
	 * 权限表
	 */
	private static final Map<String, String> authorities = new HashMap<>();
	
	
	static
	{
		loadConfig();
	}
	
	/**
	 * 加载系统权限配置
	 */
	private static void loadConfig()
	{
		try
		{
			InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile);
			if(ins != null)
			{
				xmlToMap(ins);
				ins.close();
				logger.info("权限配置文件加载成功: {}", configFile);
			}
		}
		catch(Exception ex)
		{
			
		}
	}
	
	/**
	 * 将权限配置文件读入map
	 * @param file		权限配置文件
	 */
	private static void xmlToMap(InputStream ins)
	{
		if(ins == null)
		{
			return;
		}
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(ins);
			
			NodeList list = document.getElementsByTagName("authority");
			for(int i = 0; i < list.getLength(); i++)
			{
				Node authorityNode = list.item(i);
				if(authorityNode instanceof Element)
				{
					Element authority = (Element)authorityNode;
					NodeList nodes = authority.getChildNodes();
					String name = "";
					Set<String> uris = new HashSet<String>();
					
					for(int k = 0; k < nodes.getLength(); k++)
					{
						Node node = nodes.item(k);
						if(node instanceof Element)
						{
							Element elem = (Element)node;
							if("name".equals(elem.getNodeName()))
							{
								name = elem.getFirstChild().getNodeValue();
							}
							else if("uris".equals(elem.getNodeName()))
							{
								NodeList urls = elem.getChildNodes();
								for(int j = 0; j < urls.getLength(); j++)
								{
									Node url = urls.item(j);
									if(url instanceof Element && "uri".equals(url.getNodeName()))
									{
										uris.add(url.getFirstChild().getNodeValue());
									}
								}
							}
						}
					}
					
					if(!"".equals(name) && !uris.isEmpty())
					{
						for(String uri : uris)
						{
							authorities.put(uri, name);
						}
						logger.debug("权限({})配置完毕, 关联{}条uri", name, uris.size());
					}
				}
			}
			
		}
		catch(Exception ex)
		{
			
		}
		
	}
	
	/**
	 * 检测uri是否在role的权限之内
	 * @param uri			页面URI
	 * @param role			管理角色
	 * @return	权限验证通过返回true; 否则, 返回false
	 */
	public static boolean checkAuthorities(String uri, Role role)
	{
		if(role == null)
		{
			return false;
		}
		
		boolean checked = true;
		
		String authName = authorities.get(uri);
		if(authName != null && !"".equals(authName))
		{
			if(!role.getAuthorities().contains(authName))
			{
				checked = false;
			}
		}
		
		return checked;
	}
	
	/**
	 * 检测页面URI是否在roles权限之内
	 * @param uri			页面URI
	 * @param roles			管理角色集合
	 * @return	权限验证通过返回true; 否则, 返回false
	 */
	public static boolean checkAuthorities(String uri, Set<Role> roles)
	{
		if(uri == null || roles == null)
		{
			return false;
		}
		
		boolean checked = false;
		for(Role role : roles)
		{
			if(checkAuthorities(uri, role))
			{
				checked = true;
				break;
			}
		}
		
		return checked;
	}
	
	/**
	 * 权限整合
	 * @author depeng.cao
	 * 2015-08-12
	 * 
	 */
	public static Set<String> authorities(Set<Role> set)
	{
		Iterator<Role> it = set.iterator();
		Set <String> authorities = new HashSet<String>();
		while(it.hasNext())
		{
			Role role = it.next();
			String authority = role.getAuthorities();
			if(authority != null && !"".equals(authority))
			{
				String[] auths = authority.split(",");
				authorities.addAll(Arrays.asList(auths));
			}
			
		}
		return authorities;
	}
}
