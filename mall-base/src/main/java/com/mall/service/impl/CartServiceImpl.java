package com.mall.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Cart;
import com.mall.entity.CartItem;
import com.mall.service.CartItemService;
import com.mall.service.CartService;
import com.mall.util.CommonUtil;

@Service
public class CartServiceImpl extends BaseServiceImpl<Long, Cart> implements CartService {
	@Autowired
	private CartItemService itemService;

	@Override
	public boolean add(Cart model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			model.setCartKey(CommonUtil.getUUID());
		}
		return super.add(model);
	}

	@Override
	public boolean update(Cart model) {
		if (model != null) {
			model.setModifyDate(new Date());
			model.update();
		}
		return super.update(model);
	}

	@Override
	public boolean delete(Long id) {
		Cart cart = super.get(id);
		if (cart != null) {
			Iterator<CartItem> iter = cart.getItems().iterator();
			while (iter.hasNext()) {
				CartItem item = iter.next();
				itemService.delete(item.getId());
			}
		}
		return super.delete(id);
	}

	@Override
	public void updateStatus(Cart model) {
		if (model != null) {
			super.refreshObject(model);
			for (CartItem item : model.getItems()) {
				// 移除已下架商品
				if (!item.getProduct().isMarketable())
					this.itemService.delete(item.getId());
			}
			model.update();
		}
		update(model);
	}

	@Override
	public void clear(Cart model, Long[] selecteditems) {
		if (model != null && selecteditems != null) {
			Set<CartItem> items = model.getItems();
			Set<Long> selectItemIds = new HashSet<>(Arrays.asList(selecteditems));

			Iterator<CartItem> iter = items.iterator();
			while (iter.hasNext()) {
				CartItem item = iter.next();
				if (selectItemIds.contains(item.getId())) {
					itemService.delete(item.getId());
				}
			}
			items.clear();
			this.updateStatus(model);
		}

	}

	@Override
	public Cart getFromKey(String key) {
		List<Cart> list = this.getListFromProperty("cartKey", key);
		Cart cart = list.isEmpty() ? null : list.iterator().next();
		return cart;
	}

}
