package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.AddressDao;
import com.mall.entity.Address;

@Repository
public class AddressDaoImpl extends BaseDaoImpl<Long, Address> implements AddressDao {

}
