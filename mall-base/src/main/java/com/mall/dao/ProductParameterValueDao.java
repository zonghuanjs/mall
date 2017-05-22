package com.mall.dao;

import com.mall.entity.ProductParameterValue;

public interface ProductParameterValueDao extends BaseDao<Long, ProductParameterValue>
{
	ProductParameterValue get(Long products, Long parameters);
}
