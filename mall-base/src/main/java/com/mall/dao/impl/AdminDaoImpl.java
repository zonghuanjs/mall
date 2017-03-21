package com.mall.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mall.dao.AdminDao;
import com.mall.entity.Admin;


@Repository
public class AdminDaoImpl extends BaseDaoImpl<Long, Admin> implements AdminDao
{

	@Override
	public Long maxId()
	{
		// TODO Auto-generated method stub
		Long id = -1L;
		Session session = null;
		try
		{
			session = this.currentSession();
			Query query = session.createQuery("select max(id) from Admin");
			id = ((Number)query.uniqueResult()).longValue();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return id;
	}


}
