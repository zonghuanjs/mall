package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.PaymentDao;
import com.mall.entity.Payment;

@Repository
public class PaymentDaoImpl extends BaseDaoImpl<Long, Payment> implements PaymentDao {

}
