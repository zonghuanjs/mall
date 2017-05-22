package com.mall.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.entity.Attribute;
import com.mall.entity.ParameterGroup;
import com.mall.entity.Product;
import com.mall.entity.ProductCategory;
import com.mall.service.AttributeService;
import com.mall.service.ParameterGroupService;
import com.mall.service.ProductCategoryService;
import com.mall.service.ProductService;

@Repository
public class ProductCategoryServiceImpl extends BaseServiceImpl<Long, ProductCategory>
		implements ProductCategoryService {
	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);

	/**
	 * 商品服务
	 */
	@Autowired
	private ProductService productService;

	/**
	 * 参数组服务
	 */
	@Autowired
	private ParameterGroupService groupService;

	/**
	 * 商品属性服务
	 */
	@Autowired
	private AttributeService attributeService;

	@Override
	public boolean delete(Long id) {
		List<Product> products = this.productService.getListFromProperty("productCategory", this.get(id));
		for (Product product : products) {
			this.productService.delete(product.getId());
		}
		List<ParameterGroup> groups = this.groupService.getListFromProperty("productCategory", id);
		for (ParameterGroup group : groups) {
			this.groupService.delete(group.getId());
		}
		List<Attribute> attributes = this.attributeService.getListFromProperty("productCategory", id);
		for (Attribute attribute : attributes) {
			this.attributeService.delete(attribute.getId());
		}
		return super.delete(id);

	}

	@Override
	public boolean delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				List<Product> products = this.productService.getListFromProperty("productCategory", this.get(id));
				for (Product product : products) {
					this.productService.delete(product.getId());
				}
				List<ParameterGroup> groups = this.groupService.getListFromProperty("productCategory", id);
				for (ParameterGroup group : groups) {
					this.groupService.delete(group.getId());
				}
				List<Attribute> attributes = this.attributeService.getListFromProperty("productCategory", id);
				for (Attribute attribute : attributes) {
					this.attributeService.delete(attribute.getId());
				}
			}
		}
		return super.delete(ids);
	}

	@Override
	public Set<ProductCategory> getChildren(ProductCategory parent) {
		Set<ProductCategory> children = new HashSet<>();
		Queue<ProductCategory> queue = new LinkedList<>();
		if (parent != null) {
			queue.addAll(parent.getChildren());
		}

		while (!queue.isEmpty()) {
			ProductCategory category = queue.poll();
			if (children.contains(category)) {
				logger.warn("商品分类({}:{})的子类({}:{})出现了环", parent.getId(), parent.getName(), category.getId(),
						category.getName());
				continue;
			}
			if (category != null && !category.getChildren().isEmpty()) {
				queue.addAll(category.getChildren());
			}
			children.add(category);
		}
		return children;
	}

}
