package com.mall.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.entity.Product;
import com.mall.entity.SpecificationValue;
import com.mall.service.ProductService;
import com.mall.service.SpecificationValueService;

@Service
public class SpecificationValueServiceImpl extends BaseServiceImpl<Long, SpecificationValue>
		implements SpecificationValueService {

	@Resource
	private ProductService productService;

	@Override
	public boolean delete(Long id) {
		if (id != null) {
			this.preRemove(id);
		}
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids)
			preRemove(id);
		return super.delete(ids);
	}

	/**
	 * 规格值预删除
	 * 
	 * @param id
	 *            规格值ID
	 */
	private void preRemove(Long id) {
		SpecificationValue model = this.get(id);
		if (model != null) {
			if (!model.getProducts().isEmpty()) {
				Iterator<Product> iter = model.getProducts().iterator();
				while (iter.hasNext()) {
					Product product = iter.next();
					product.getSpecificationValues().remove(model);
					productService.update(product);
				}
			}
		}
	}
}
