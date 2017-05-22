package com.mall.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Invoice;
import com.mall.entity.Order;
import com.mall.service.InvoiceService;
import com.mall.service.OrderService;

@Service
public class InvoiceServiceImpl extends BaseServiceImpl<Long, Invoice> implements InvoiceService {
	@Autowired
	private OrderService orderService;

	@Override
	public boolean add(Invoice model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			Order order = model.getOrders();
			if (order != null) {
				order.setIsInvoice(true);
				if (model.getType() == 0)
					order.setInvoiceTitle(model.getTitle());
				else
					order.setInvoiceTitle(model.getCompany());
				this.orderService.update(order);
			}
		}
		return super.add(model);
	}

	@Override
	public boolean update(Invoice model) {
		if (model != null) {
			model.setModifyDate(new Date());
			Order order = model.getOrders();
			if (order != null) {
				if (model.getType() == 0)
					order.setInvoiceTitle(model.getTitle());
				else
					order.setInvoiceTitle(model.getCompany());
				this.orderService.update(order);
			}
		}
		return super.update(model);
	}
}
