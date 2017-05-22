package com.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.dao.ProductParameterValueDao;
import com.mall.entity.ProductParameterValue;
import com.mall.service.ProductParameterValueService;

@Repository
public class ProductParameterValueServiceImpl extends BaseServiceImpl<Long, ProductParameterValue>
		implements ProductParameterValueService {

	private ProductParameterValueDao dao;

	@Autowired
	public void setDao(ProductParameterValueDao dao) {
		this.dao = dao;
	}

	public ProductParameterValueDao getDao() {
		return this.dao;
	}

	@Override
	public ProductParameterValue get(Long products, Long parameters) {
		return dao.get(products, parameters);
	}

}
