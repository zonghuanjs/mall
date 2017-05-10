package com.mall.service;

import com.mall.entity.Area;

/**
 * 
 * @author huan.zong
 *
 */

public interface AreaService extends BaseService<Long, Area>
{
	/**
	 * 通过地区全名获取区域
	 * @param fullName
	 * @return
	 */
	Area getFromFullName(String fullName);
	
	/**
	 * 获取顶层区域对象
	 * @param area
	 * @return
	 */
	Area findTopArea(Area area);
}
