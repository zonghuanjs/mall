package com.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.entity.Specification;
import com.mall.entity.SpecificationValue;
import com.mall.service.SpecificationService;
import com.mall.service.SpecificationValueService;

@Repository
public class SpecificationServiceImpl extends BaseServiceImpl<Long, Specification> implements SpecificationService {

	@Autowired
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
