package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Attribute;
import com.mall.entity.AttributeOptions;
import com.mall.service.AttributeOptionsService;
import com.mall.service.AttributeService;

@Service
public class AttributeServiceImpl extends BaseServiceImpl<Long, Attribute> implements AttributeService {
	@Autowired
	private AttributeOptionsService optionService;

	@Override
	public boolean delete(Long id) {
		List<AttributeOptions> list = this.optionService.getListFromProperty("attribute", this.get(id));
		for (AttributeOptions ao : list) {
			this.optionService.delete(ao.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids) {
			Attribute a = this.get(id);
			List<AttributeOptions> list = this.optionService.getListFromProperty("attribute", a);
			for (AttributeOptions ao : list) {
				this.optionService.delete(ao.getId());
			}
		}
		return super.delete(ids);
	}

}
