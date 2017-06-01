package com.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.ChangeDao;
import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.service.ProductService;
import com.mall.service.ChangeType;
import com.mall.service.ChangeService;

@Service
public class ChangeServiceImpl implements ChangeService {
	@Autowired
	private ProductService service;

	@Autowired
	private ChangeDao changeDao;

	@Override
	public boolean checkStock(Product product, int quantity) {
		if (product == null || quantity < 0) {
			return false;
		} else {
			return product.getStock() >= quantity;
		}
	}

	@Override
	public boolean lockStock(Product product, int quantity) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if (product != null && quantity > 0) {
			ret = changeDao.changeStock(product, quantity, ChangeType.substract);
		}
		return ret;
	}

	@Override
	public boolean recoveryStock(Product product, int quantity) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if (product != null && quantity > 0) {
			ret = changeDao.changeStock(product, quantity, ChangeType.add);
		}
		return ret;
	}

	@Override
	public boolean doStockOperation(Product product, int quantity, ChangeType ot) {
		// TODO Auto-generated method stub
		boolean ret = false;
		if (product != null) {
			ret = changeDao.changeStock(product, quantity, ot);
		}
		return ret;
	}

	@Override
	public boolean changeMemberPoint(Member member, int quantity, ChangeType ct) {
		boolean ret = false;
		if (member != null && quantity > 0) {
			ret = changeDao.changeMemberPoint(member, quantity, ct);
		}
		return ret;
	}

	@Override
	public boolean changeMemberAmount(Member member, double quantity, ChangeType ct) {
		boolean ret = false;
		if (member != null && quantity > 0) {
			ret = changeDao.changeMemberAmount(member, quantity, ct);
		}
		return ret;
	}

	@Override
	public boolean changeSales(Product product, int quantity, ChangeType ct) {
		boolean ret = false;
		if (product != null && quantity > 0) {
			ret = changeDao.changeSales(product, quantity, ct);
		}
		return ret;
	}
}
