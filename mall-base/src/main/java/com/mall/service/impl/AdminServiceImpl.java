package com.mall.service.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.AdminDao;
import com.mall.entity.Admin;
import com.mall.service.AdminService;


/**
 * 
 * @author zonghuan
 *
 */

@Repository
public class AdminServiceImpl extends BaseServiceImpl<Long, Admin> implements AdminService
{
	@Override
	public Long maxId()
	{
		// TODO Auto-generated method stub
		return ((AdminDao)this.getDao()).maxId();
	}
	
	@Override
	public boolean delete(Long id)
	{
		return super.delete(id);
	}
	
	@Override
	public boolean delete(Long[] ids)
	{
		return super.delete(ids);
	}
}
