package com.mall.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.mall.entity.AddTax;
import com.mall.service.AddTaxService;
import com.mall.util.CommonUtil;

@Service
public class AddTaxServiceImpl extends BaseServiceImpl<Long, AddTax> implements AddTaxService {
	@Override
	public boolean add(AddTax model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());

			checkSecurity(model);
		}
		return super.add(model);
	}

	@Override
	public boolean update(AddTax model) {
		if (model != null) {
			model.setModifyDate(new Date());
			checkSecurity(model);
		}
		return super.update(model);
	}

	/**
	 * 恶意代码过滤
	 * 
	 * @param model
	 */
	protected void checkSecurity(AddTax model) {
		if (model != null) {
			model.setCompany(CommonUtil.htmlToPlainText(model.getCompany()));
			model.setIdNumber(CommonUtil.htmlToPlainText(model.getIdNumber()));
			model.setRegisterAccount(CommonUtil.htmlToPlainText(model.getRegisterAccount()));
			model.setRegisterAddress(CommonUtil.htmlToPlainText(model.getRegisterAddress()));
			model.setRegisterBank(CommonUtil.htmlToPlainText(model.getRegisterBank()));
			model.setRegisterTel(CommonUtil.htmlToPlainText(model.getRegisterTel()));
			model.setRegisterCertificate(CommonUtil.htmlToPlainText(model.getRegisterCertificate()));
			model.setTaxCertificate(CommonUtil.htmlToPlainText(model.getTaxCertificate()));
			model.setTaxData(CommonUtil.htmlToPlainText(model.getTaxData()));
			model.setOtherData(CommonUtil.htmlToPlainText(model.getOtherData()));
			model.setReason(CommonUtil.htmlToPlainText(model.getReason()));
		}
	}
}
