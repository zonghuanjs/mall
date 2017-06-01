package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.Role;
import com.mall.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Long, Role> implements RoleService
{
	@Override
	public boolean add(Role model)
	{
		if(model != null)
		{
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}
	
	@Override
	public boolean update(Role model)
	{
		if(model != null)
		{
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}
}
