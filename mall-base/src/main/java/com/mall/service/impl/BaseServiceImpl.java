package com.mall.service.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.BaseDao;
import com.mall.pager.Pager;
import com.mall.service.BaseService;


@Service
public class BaseServiceImpl<PK extends Serializable, T> implements BaseService<PK, T>
{
	private BaseDao<PK, T> dao;
	
	@Autowired
	public void setDao(BaseDao<PK, T> dao)
	{
		this.dao = dao;
	}
	
	public BaseDao<PK, T> getDao()
	{
		return this.dao;
	}

	@Override
	public boolean add(T model)
	{
		// TODO Auto-generated method stub
		return dao.add(model);
	}

	@Override
	public boolean delete(PK id)
	{
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public boolean delete(PK[] ids)
	{
		// TODO Auto-generated method stub
		return dao.delete(ids);
	}

	@Override
	public T get(PK id)
	{
		// TODO Auto-generated method stub
		if(id != null)
			return dao.get(id);
		else
			return null;
	}

	@Override
	public List<T> get(PK[] ids)
	{
		// TODO Auto-generated method stub
		List<T> list = new LinkedList<>();
		if(ids != null && ids.length > 0)
			list.addAll(dao.get(ids));
		return list;
	}

	@Override
	public List<T> getAll()
	{
		// TODO Auto-generated method stub
		return dao.getAll();
	}
	
	@Override
	public List<T> getAll(String orderby)
	{
		return dao.getAll(orderby);
	}

	@Override
	public List<T> getListFromProperty(String propertyName, Object value)
	{
		// TODO Auto-generated method stub
		return dao.getListFromProperty(propertyName, value);
	}
	
	@Override
	public List<T> getList(String propertyName, Object value, String orderby)
	{
		return dao.getList(propertyName, value, orderby);
	}

	@Override
	public boolean update(T model)
	{
		// TODO Auto-generated method stub
		return dao.update(model);
	}

	@Override
	public long getCount()
	{
		// TODO Auto-generated method stub
		return dao.getCount();
	}

	@Override
	public long getCount(String propertyName, Object value)
	{
		// TODO Auto-generated method stub
		return dao.getCount(propertyName, value);
	}

	@Override
	public List<T> getList(Map<String, Object> filter)
	{
		// TODO Auto-generated method stub
		return dao.getList(filter);
	}

	@Override
	public List<T> getList(Map<String, Object> filter, String orderby)
	{
		// TODO Auto-generated method stub
		return dao.getList(filter, orderby);
	}

	@Override
	public void refreshObject(T model)
	{
		if(model != null)
			dao.refreshObject(model);
	}

	@Override
	public List<T> findByPager(Pager<T> pager)
	{
		// TODO Auto-generated method stub
		return dao.findByPager(pager);
	}
	
}
