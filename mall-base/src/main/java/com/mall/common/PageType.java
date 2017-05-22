package com.mall.common;

/**
 * 页面类型
 * @author ChenMingcai
 * 2016-12-14
 *
 */

public enum PageType
{
	/**
	 * PC端首页
	 */
	pcHome(1),
	/**
	 * 移动端首页
	 */
	mobileHome(2);
	
	private final int type;
	
	private PageType(int type)
	{
		this.type = type;
	}
	
	public int type()
	{
		return this.type;
	}
	
	/**
	 * 将页面类型值解析为页面类型
	 * @param type	类型值
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static PageType forType(int type) throws IllegalArgumentException
	{
		PageType target = null;
		for(PageType page : PageType.values())
		{
			if(page.type == type)
			{
				target = page;
				break;
			}
		}
		
		if(target == null)
		{
			throw new IllegalArgumentException("非法PageType value: " + type);
		}
		
		return target;
	}
}
