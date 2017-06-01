package com.mall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.entity.Specification;
import com.mall.entity.SpecificationValue;
import com.mall.service.SpecificationService;
import com.mall.service.SpecificationValueService;

@Service
public class SpecificationServiceImpl extends BaseServiceImpl<Long, Specification> implements SpecificationService {

	@Resource
	private SpecificationValueService specificationValueService;

	@Override
	public boolean delete(Long id) {
		List<SpecificationValue> specificationValues = this.specificationValueService
				.getListFromProperty("specification", this.get(id));
		for (SpecificationValue specificationValue : specificationValues) {
			this.specificationValueService.delete(specificationValue.getId());
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		if (ids != null) {
			if (ids != null) {
				for (Long id : ids) {
					List<SpecificationValue> specificationValues = this.specificationValueService
							.getListFromProperty("specification", this.get(id));
					for (SpecificationValue specificationValue : specificationValues) {
						this.specificationValueService.delete(specificationValue.getId());
					}

				}
			}
		}
		return super.delete(ids);
	}

}
