package com.mall.common;

/**
 * 页面模块规则
 * @author ChenMingcai
 * 2016-12-14
 *
 */

public enum ModuleRule
{
	/**
	 * 背景
	 */
	background(1),
	/**
	 * 内容
	 */
	content(2),
	/**
	 * 标题
	 */
	title(3);
	
	private final int rule;
	
	private ModuleRule(int value)
	{
		this.rule = value;
	}
	
	public int rule()
	{
		return this.rule;
	}
	
	public static ModuleRule forRule(int value)
	{
		ModuleRule target = null;
		for(ModuleRule rl : values())
		{
			if(rl.rule == value)
			{
				target = rl;
				break;
			}
		}
		
		if(target == null)
		{
			throw new IllegalArgumentException("非法规则类型:" + value);
		}
		
		return target;
	}
}
