package com.mall.pager;

/**
 * 
 * @author ChenMingcai
 *
 */

public interface ListFilter
{
	/**
	 * 元素object是否应该被过滤
	 * @param object
	 * @return true, 应该被过滤; 否则, 返回false
	 */
	boolean filter(Object object);
}
