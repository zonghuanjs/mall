package com.mall.service.impl;

import org.springframework.stereotype.Service;

import com.mall.entity.Model;
import com.mall.service.TestService;

@Service
public class TestServiceImpl extends BaseServiceImpl<Long, Model> implements TestService{

	/*@Resource
	private TestDao dao;
	
	@Override
	public Model getObject(Long id) {
		// TODO Auto-generated method stub
		return dao.getObject(id);
	}

	@Override
	public boolean add(Model model) {
		// TODO Auto-generated method stub
		return dao.add(model);
	}*/

}
