package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Parameter;
import com.mall.entity.ProductParameterValue;
import com.mall.service.ParameterService;
import com.mall.service.ProductParameterValueService;

@Service
public class ParameterServiceImpl extends BaseServiceImpl<Long, Parameter> implements ParameterService {

	@Autowired
	private ProductParameterValueService valueService;

	@Override
	public boolean delete(Long id) {
		List<ProductParameterValue> list = this.valueService.getListFromProperty("parameters", id);
		for (ProductParameterValue pv : list) {
			this.valueService.delete(pv.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids) {
			List<ProductParameterValue> list = this.valueService.getListFromProperty("parameters", id);
			for (ProductParameterValue pv : list) {
				this.valueService.delete(pv.getId());
			}
		}
		return super.delete(ids);
	}

}
