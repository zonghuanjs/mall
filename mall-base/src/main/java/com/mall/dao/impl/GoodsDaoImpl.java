package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.GoodsDao;
import com.mall.entity.Goods;

@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Long, Goods> implements GoodsDao
{

}
