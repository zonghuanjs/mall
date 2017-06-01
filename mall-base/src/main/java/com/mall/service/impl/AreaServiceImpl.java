package com.mall.service.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mall.entity.Area;
import com.mall.service.AreaService;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Long, Area> implements AreaService
{

	@Override
	public Area getFromFullName(String fullName)
	{
		List<Area> list = this.getListFromProperty("fullName", fullName);
		Area area = list.isEmpty() ? null : list.iterator().next();
		return area;
	}

	@Override
	public Area findTopArea(Area area)
	{
		Area objArea = area == null ? null : area;
		if(area != null && StringUtils.isNotEmpty(area.getTreePath()))
		{			
			try
			{
				String idStr = area.getTreePath().split(",")[1];
				Long topAreaId = new Long(idStr);
				objArea = this.get(topAreaId);
			}
			catch(Exception ex)
			{
				
			}
		}
		return objArea;
	}

}
