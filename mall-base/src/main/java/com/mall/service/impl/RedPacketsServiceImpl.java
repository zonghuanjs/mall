package com.mall.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.bean.EggConfig;
import com.mall.bean.RedPacketsConfig;

import com.mall.dao.RedPacketsDao;
import com.mall.entity.Coupon;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.service.CouponService;
import com.mall.service.MemberService;
import com.mall.service.RedPacketsService;
import com.mall.util.TimeUtils;

@Service
public class RedPacketsServiceImpl implements RedPacketsService {

	@Resource
	private CouponService cpService;// 优惠服务

	@Resource
	private RedPacketsDao dao;// 活动数据访问接口

	@Resource
	private MemberService memberService;// 会员服务

	@Override
	public int memberDayNumber(Member member, Date date) {
		// TODO Auto-generated method stub
		int count = 0;
		if (member != null && date != null) {
			count = dao.memberDayNumber(member, date);
		}
		return count;
	}

	@Override
	public synchronized void markMember(Member member, Date date) {
		// TODO Auto-generated method stub
		int number = this.memberDayNumber(member, date);

		String key = "RED_Packets_" + TimeUtils.formatDate("yyyyMMdd", date);

		if (number + 1 <= EggConfig.dayLimitMember) {
			int newNumber = dao.memberDayNumber(member, date) + 1;
			member.getAttributes().put(key, newNumber + "");
			memberService.update(member);
		}
	}

	@Override
	public CouponCode createCouponCode(Member member) {
		// TODO Auto-generated method stub

		int idx = idx();
		if (idx < RedPacketsConfig.coupons.length) {
			Long couponId = new Long(RedPacketsConfig.coupons[idx]);
			CouponCode code = null;
			Coupon coupon = cpService.get(couponId);
			if (coupon != null) {
				if (this.checkCoupon(coupon, member)) {
					code = cpService.issueCode(coupon, member);
				}

			}
			return code;
		}
		return null;
	}

	public int idx() {
		// 计算每个物品在总概率的基础下的概率情况
		List<Integer> sortOrignalRates = new ArrayList<Integer>();
		int tempSumRate = 0;
		for (int rate : RedPacketsConfig.percent) {
			tempSumRate += rate;
			sortOrignalRates.add(tempSumRate);
		}

		Random random = new Random();
		// 根据区块值来获取抽取到的物品索引
		int nextInt = random.nextInt(1000) + 1;
		sortOrignalRates.add(nextInt);
		Collections.sort(sortOrignalRates);
		return sortOrignalRates.indexOf(nextInt);
	}

	/**
	 * 检测优惠信息
	 * 
	 * @param coupon
	 * @param member
	 * @return
	 */
	public boolean checkCoupon(Coupon coupon, Member member) {
		int errCode = 0;
		if (coupon == null || member == null) {
			// 优惠信息无效
			errCode = 1;
		}

		Date now = new Date();
		if (errCode == 0) {
			if (coupon.getBeginDate().getTime() > now.getTime()) {
				// 还未到领取时间
				errCode = 2;
			} else if (coupon.getEndDate().getTime() < now.getTime()) {
				// 优惠信息已过期
				errCode = 3;
			}
		}

		// 发放数量检测
		if (errCode == 0) {
			// 检测总数量
			if (coupon.getNumber() != 0 && coupon.getCodes().size() >= coupon.getNumber()) {
				errCode = 5;
			}

			// 检测日数量
			if (errCode == 0 && coupon.getDayNumber() != 0
					&& cpService.issueNumber(coupon, new Date()) >= coupon.getDayNumber()) {
				errCode = 6;
			}
		}

		if (errCode == 0) {
			return true;
		}
		return false;

	}

}
