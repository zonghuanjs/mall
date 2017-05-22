package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.ProductCategoryDao;
import com.mall.entity.ProductCategory;

@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<Long, ProductCategory> implements ProductCategoryDao {

}
