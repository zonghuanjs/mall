package com.mall.service;

import com.mall.entity.ProductParameterValue;

public interface ProductParameterValueService extends BaseService<Long, ProductParameterValue> {
	ProductParameterValue get(Long products, Long parameters);
}
