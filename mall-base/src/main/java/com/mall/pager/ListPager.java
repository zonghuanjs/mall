package com.mall.pager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author ChenMingcai
 *
 */

public class ListPager<T>
{
	private List<T> mPageData;//待处理数据
	
	public ListPager(List<T> data)
	{
		this.mPageData = data;
	}
	
	/**
	 * 获取数据总条数
	 * @return
	 */
	public int getCount()
	{
		return this.mPageData == null ? 0 : this.mPageData.size();
	}
	
	/**
	 * 取得指定页面的列表数据
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<T> getPage(int pageIndex, int pageSize)
	{
		List<T> pageData = new LinkedList<>();
		int first = 0;
		if(pageIndex > 0 && pageSize > 0)
		{
			first = pageSize * (pageIndex - 1);
			int idx = 0;
			if(this.mPageData != null)
			{
				Iterator<T> iter = this.mPageData.iterator();
				while(iter.hasNext())
				{
					T object = iter.next();
					if(idx >= first && idx - first < pageSize)
					{
						pageData.add(object);
					}
					
					if(idx - first >= pageSize)
					{
						break;
					}
					
					idx++;
				}
			}
		}
		
		return pageData;
	}
	
	/**
	 * 过滤列表中的数据
	 * @param listFilter 过滤器
	 */
	public void filter(ListFilter listFilter)
	{
		if(listFilter != null && this.mPageData != null)
		{
			Iterator<?> iter = this.mPageData.iterator();
			while(iter.hasNext())
			{
				Object object = iter.next();
				if(listFilter.filter(object))
				{
					iter.remove();
				}
			}
		}
	}
}
