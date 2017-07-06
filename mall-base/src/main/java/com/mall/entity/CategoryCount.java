package com.mall.entity;

public class CategoryCount {

	private ProductCategory category;
	private int count;

	public CategoryCount() {

	}

	public CategoryCount(ProductCategory c, int count) {
		this.category = c;
		this.count = count;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
