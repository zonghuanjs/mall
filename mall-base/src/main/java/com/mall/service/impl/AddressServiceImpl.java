package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.Address;
import com.mall.service.AddressService;
import com.mall.util.CommonUtil;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Long, Address> implements AddressService {

	@Override
	public boolean add(Address model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			// 安全检测
			secureCheck(model);
		}
		return super.add(model);
	}

	@Override
	public boolean update(Address model) {
		if (model != null) {
			model.setModifyDate(new Date());
			// 安全检测
			secureCheck(model);
		}
		return super.update(model);
	}

	/**
	 * 输入安全性检测
	 * 
	 * @param model
	 */
	private void secureCheck(Address model) {
		if (model != null) {
			model.setAddress(CommonUtil.htmlToPlainText(model.getAddress()));
			model.setConsignee(CommonUtil.htmlToPlainText(model.getConsignee()));
			model.setName(CommonUtil.htmlToPlainText(model.getName()));
			model.setPhone(CommonUtil.htmlToPlainText(model.getPhone()));
			model.setZipCode(CommonUtil.htmlToPlainText(model.getZipCode()));
		}
	}
}
