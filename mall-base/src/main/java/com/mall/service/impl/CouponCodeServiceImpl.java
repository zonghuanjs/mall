package com.mall.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.view.CartView;

import com.mall.dao.CouponCodeDao;
import com.mall.entity.CartItem;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.pager.Pager;
import com.mall.service.CouponCodeService;
import com.mall.service.ProductService;

@Service
public class CouponCodeServiceImpl extends BaseServiceImpl<Long, CouponCode> implements CouponCodeService {

	@Autowired
	private ProductService productService;

	@Override
	public boolean add(CouponCode model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public List<CouponCode> findValidCodes(Member member, Pager<CouponCode> pager) {
		List<CouponCode> list = new LinkedList<>();
		if (member != null) {
			CouponCodeDao dao = (CouponCodeDao) this.getDao();
			list.addAll(dao.findValidCodes(member, pager));
		}
		return list;
	}

	@Override
	public List<CouponCode> findValidCodes(Member member, List<Product> products, double totalPrice) {
		List<CouponCode> list = new LinkedList<>();
		if (member != null) {
			List<CouponCode> codes = findValidCodes(member, null);

			boolean b = false;
			for (CouponCode code : codes) {
				b = false;
				// 无限制商品
				if (code.getCoupon().getProducts().size() == 0) {
					b = true;
				} else {
					for (Product product : products) {
						if (code.getCoupon().getProducts().contains(product)) {
							b = true;
							break;
						}
					}
				}

				if (code.getCoupon().getStartPrice() <= totalPrice && b) {
					list.add(code);
				}
			}
		}
		return list;
	}

	@Override
	public CouponCode findByCode(String code) {
		CouponCode model = null;
		if (StringUtils.isNotEmpty(code)) {
			List<CouponCode> list = this.getList("code", code, null);
			model = list.isEmpty() ? null : list.iterator().next();
		}
		return model;
	}

	@Override
	public boolean enableCouponCode(CartView view) {
		if (view == null) {
			return false;
		}

		boolean enabled = true;

		for (CartItem item : view.getSelectedItems()) {
			Promotion promotion = item.getProduct().getCurrentPromotion();
			if (promotion != null && !promotion.isCouponAllowed()) {
				enabled = false;
				break;
			}
		}

		return enabled;
	}

	@Override
	public List<CouponCode> findByPager(Pager<CouponCode> pager) {
		// TODO Auto-generated method stub
		CouponCodeDao dao = (CouponCodeDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public List<CouponCode> findByPager(Member member, Pager<CouponCode> pager, Map<String, Object> geFilter,
			Map<String, Object> ltFilter) {
		// TODO Auto-generated method stub
		List<CouponCode> list = new LinkedList<>();
		if (member != null) {
			CouponCodeDao dao = (CouponCodeDao) this.getDao();
			list.addAll(dao.findByPager(member, pager, geFilter, ltFilter));
		}
		return list;
	}

	@Override
	public long getCount(Member member, Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter) {
		// TODO Auto-generated method stub
		long count = 0;
		if (member != null) {
			CouponCodeDao dao = (CouponCodeDao) this.getDao();
			count = dao.getCount(member, eqFilter, geFilter, ltFilter);
		}
		return count;
	}

	@Override
	public List<CouponCode> getCouponCodeList(Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter, String orderby) {
		// TODO Auto-generated method stub
		List<CouponCode> list = new LinkedList<>();
		CouponCodeDao dao = (CouponCodeDao) this.getDao();
		list.addAll(dao.getCouponCodeList(eqFilter, geFilter, ltFilter, orderby));
		return list;
	}
}
