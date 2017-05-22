package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.entity.Parameter;
import com.mall.entity.ParameterGroup;
import com.mall.service.ParameterGroupService;
import com.mall.service.ParameterService;

@Repository
public class ParameterGroupServiceImpl extends BaseServiceImpl<Long, ParameterGroup> implements ParameterGroupService {

	@Autowired
	private ParameterService service;

	@Override
	public boolean delete(Long id) {
		List<Parameter> params = this.service.getListFromProperty("parameterGroup", id);
		for (Parameter p : params) {
			this.service.delete(p.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids) {
			List<Parameter> params = this.service.getListFromProperty("parameterGroup", id);
			for (Parameter p : params) {
				this.service.delete(p.getId());
			}
		}
		return super.delete(ids);
	}

}
