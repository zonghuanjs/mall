package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.CartDao;
import com.mall.entity.Cart;


@Repository
public class CartDaoImpl extends BaseDaoImpl<Long, Cart> implements CartDao
{

}
