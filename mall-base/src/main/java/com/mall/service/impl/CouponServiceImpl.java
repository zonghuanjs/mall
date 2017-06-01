package com.mall.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mall.dao.CouponDao;
import com.mall.entity.Coupon;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.pager.Pager;
import com.mall.service.CouponService;
import com.mall.util.CommonUtil;

@Service
public class CouponServiceImpl extends BaseServiceImpl<Long, Coupon> implements CouponService {

	@Override
	public boolean add(Coupon model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public List<Coupon> findByPager(Pager<Coupon> pager) {
		CouponDao dao = (CouponDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public boolean checkCondition(Coupon coupon, Member member) {
		if (coupon == null || !coupon.isEnabled()) {
			return false;
		}

		if (!checkPeriod(coupon)) {
			return false;
		}

		if (!checkPoint(coupon, member)) {
			return false;
		}

		return true;
	}

	@Override
	public CouponCode issueCode(Coupon coupon, Member member) {
		CouponCode code = null;

		if (checkCondition(coupon, member)) {
			code = new CouponCode();

			StringBuffer token = new StringBuffer();
			token.append(coupon.getPrefix()).append(CommonUtil.createRandomCode());
			code.setCode(token.toString());
			code.setCoupon(coupon);
			code.setMember(member);
			code.setUsed(false);
			// 过期日期
			if (coupon.getDays() == 0) {
				code.setExpired(coupon.getEndDate());
			} else {
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DATE, coupon.getDays());
				code.setExpired(c.getTime());
			}

		}

		return code;
	}

	@Override
	public long issueNumber(Coupon coupon, Date day) {
		if (coupon == null || day == null) {
			return 0;
		}

		CouponDao dao = (CouponDao) super.getDao();
		return dao.issueNumber(coupon, day);
	}

	/**
	 * 检测优惠信息是否在领取期内
	 * 
	 * @param coupon
	 * @return
	 */
	private boolean checkPeriod(Coupon coupon) {
		if (coupon == null) {
			return false;
		}

		Date now = new Date();
		if (coupon.getBeginDate().after(now) || coupon.getEndDate().before(now)) {
			return false;
		}

		return true;
	}

	/**
	 * 检测会员积分是否满足条件
	 * 
	 * @param coupon
	 *            优惠信息
	 * @param member
	 *            会员对象
	 * @return 满足条件, 返回true; 否则, 返回false
	 */
	private boolean checkPoint(Coupon coupon, Member member) {
		if (coupon == null || member == null) {
			return false;
		}

		if (coupon.getPoint() != 0 && member.getPoint() < coupon.getPoint()) {
			return false;
		}
		return true;
	}

	/**
	 * 检测优惠券使用期限
	 */
	@Override
	public boolean checkUseCondition(Coupon coupon, Member member) {
		// TODO Auto-generated method stub
		if (coupon == null || !coupon.isEnabled()) {
			return false;
		}

		/*
		 * if(!checkUsePeriod(coupon)) { return false; }
		 */

		if (!checkPoint(coupon, member)) {
			return false;
		}

		return true;
	}

	/**
	 * 检测优惠信息是否在使用期内
	 * 
	 * @param coupon
	 * @return
	 */
	/*
	 * private boolean checkUsePeriod(Coupon coupon) { if(coupon == null) {
	 * return false; }
	 * 
	 * Date now = new Date();
	 * 
	 * if(coupon.getBeginDate().after(now)) { return false; }
	 * 
	 * return true; }
	 */
}
