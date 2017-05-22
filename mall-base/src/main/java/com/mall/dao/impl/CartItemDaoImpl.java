package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.CartItemDao;
import com.mall.entity.CartItem;


@Repository
public class CartItemDaoImpl extends BaseDaoImpl<Long, CartItem> implements CartItemDao
{

}
