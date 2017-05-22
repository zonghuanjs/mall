package com.mall.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.service.MarkService;
import com.mall.service.MemberService;

@Service
public class MarkServiceImpl implements MarkService {
	@Autowired
	private MemberService memberService;

	@Override
	public void markMember(Member member, Promotion promotion, Product product, int number) {
		if (member == null || promotion == null || product == null) {
			return;
		}

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId()).append("_").append(product.getId());

		String tokenValue = "1";
		if (isMark(member, promotion, product) && number > 0) {
			int oldNumber = markNumber(member, promotion, product);
			tokenValue = (oldNumber + number) + "";
		}

		member.getAttributes().put(token.toString(), tokenValue);
		memberService.update(member);
	}

	@Override
	public void revokeMark(Member member, Promotion promotion, Product product, int number) {
		if (member == null || promotion == null || product == null) {
			return;
		}

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId()).append("_").append(product.getId());

		int oldNumber = markNumber(member, promotion, product);
		int newNumber = oldNumber - number;
		if (newNumber > 0) {
			member.getAttributes().put(token.toString(), newNumber + "");
		} else {
			member.getAttributes().remove(token.toString());
		}

		memberService.update(member);
	}

	@Override
	public boolean isMark(Member member, Promotion promotion, Product product) {
		if (member == null || promotion == null || product == null) {
			return false;
		}

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId()).append("_").append(product.getId());

		String tokenValue = member.getAttributes().get(token.toString());
		if (StringUtils.isNotEmpty(tokenValue)) {
			return true;
		}

		return false;
	}

	@Override
	public int markNumber(Member member, Promotion promotion, Product product) {
		if (member == null || promotion == null || product == null) {
			return 0;
		}

		int number = 0;

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId()).append("_").append(product.getId());
		String tokenValue = member.getAttributes().get(token.toString());

		try {
			number = new Integer(tokenValue);
		} catch (Exception ex) {

		}

		return number;
	}

	@Override
	public void markMember(Member member, Promotion promotion, int number) {
		if (member == null || promotion == null) {
			return;
		}

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId());
		String tokenValue = (markNumber(member, promotion) + number) + "";

		member.getAttributes().put(token.toString(), tokenValue);
		memberService.update(member);
	}

	@Override
	public void resetMember(Member member, Promotion promotion) {
		if (member == null || promotion == null) {
			return;
		}

		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId());
		member.getAttributes().remove(token.toString());
		memberService.update(member);
	}

	@Override
	public int markNumber(Member member, Promotion promotion) {
		if (member == null || promotion == null) {
			return 0;
		}

		int number = 0;
		StringBuffer token = new StringBuffer(Promotion.Keys.numberKey);
		token.append(promotion.getId());

		String tokenValue = member.getAttributes().get(token.toString());
		try {
			number = new Integer(tokenValue);
		} catch (Exception ex) {

		}

		return number;
	}
}
