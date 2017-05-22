package com.mall.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.dao.InvoiceDao;
import com.mall.entity.Invoice;

@Repository
public class InvoiceDaoImpl extends BaseDaoImpl<Long, Invoice> implements InvoiceDao {

}
