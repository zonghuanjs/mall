package com.mall.service;

import java.util.Set;

import com.mall.entity.ProductCategory;

public interface ProductCategoryService extends BaseService<Long, ProductCategory> {
	/**
	 * 获取分类的所有子类
	 * 
	 * @param parent
	 * @return
	 */
	Set<ProductCategory> getChildren(ProductCategory parent);
}
