package com.mall.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.exception.PromotionException;

import com.mall.dao.PromotionDao;
import com.mall.entity.Member;
import com.mall.entity.MemberRank;
import com.mall.entity.OrderItem;
import com.mall.entity.Product;
import com.mall.entity.Promotion;
import com.mall.pager.Pager;
import com.mall.service.MarkService;
import com.mall.service.PromotionService;
import com.mall.util.TimeUtils;

@Service
public class PromotionServiceImpl extends BaseServiceImpl<Long, Promotion> implements PromotionService {
	@Autowired
	private MarkService markService;// 标记服务

	@Override
	public boolean add(Promotion model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public boolean update(Promotion model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public List<Promotion> findByPager(Pager<Promotion> pager) {
		PromotionDao dao = (PromotionDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public void process(Promotion promotion, OrderItem item, Member member) throws PromotionException {
		if (!checkCondition(promotion, item, member)) {
			throw new PromotionException("商品" + item.getProduct().getName() + "不满足活动条件");
		}

		markService.markMember(member, promotion, item.getProduct(), item.getQuantity());
	}

	@Override
	public boolean checkCondition(Promotion promotion, Product product, Member member) {
		if (product == null || member == null || promotion == null) {
			return false;
		}

		// 检测商品是否违规
		if (!promotion.getProducts().contains(product)) {
			return false;
		}

		// 检测抢购活动是否启用
		String snatchTimeStr = promotion.getAttributes().get(Promotion.Keys.snatchDatePoint);
		String snatchEnabled = promotion.getAttributes().get(Promotion.Keys.snatchEnabled);
		String promotionEnabled = promotion.getAttributes().get(Promotion.Keys.isEnabled);
		if (!"true".equals(promotionEnabled) || !"true".equals(snatchEnabled) || StringUtils.isEmpty(snatchTimeStr)) {
			return false;
		}

		// 检测抢购活动是否未开始或者已经过期、结束
		Date now = new Date();
		if (now.getTime() < promotion.getBeginDate().getTime() || now.getTime() > promotion.getEndDate().getTime()) {
			return false;
		}

		String snatchPointStr = promotion.getAttributes().get(Promotion.Keys.snatchDatePoint);
		if (StringUtils.isEmpty(snatchPointStr)) {
			return false;
		} else {
			int snatchPoint = new Integer(snatchPointStr);
			int hours = TimeUtils.getField(now, Calendar.HOUR_OF_DAY);
			if (hours < snatchPoint) {
				return false;
			}
		}

		// 检测会员资料是否满足条件
		// 检测手机
		String memberMobile = promotion.getAttributes().get(Promotion.Keys.memberMobile);
		if (StringUtils.isNotEmpty(memberMobile) && StringUtils.equals(memberMobile, "true")) {
			String mobileValidated = member.getAttributes().get(Member.Keys.mobileValidated);
			if (StringUtils.isEmpty(mobileValidated) || !StringUtils.equals(mobileValidated, "true")) {
				return false;
			}
		}

		// 检测邮箱
		String memberMail = promotion.getAttributes().get(Promotion.Keys.memberMail);
		if (StringUtils.isNotEmpty(memberMail) && StringUtils.equals(memberMail, "true")) {
			String mailValidated = member.getAttributes().get(Member.Keys.mailValidated);
			if (StringUtils.isEmpty(mailValidated) || !StringUtils.equals(mailValidated, "true")) {
				return false;
			}
		}

		// 检测生日
		String memberBirth = promotion.getAttributes().get(Promotion.Keys.memberBirth);
		if (StringUtils.isNotEmpty(memberBirth) && StringUtils.equals(memberBirth, "true")) {
			if (member.getBirth() == null) {
				return false;
			}
		}

		// 检测限购条件
		String numberKey = Promotion.Keys.numberKey + promotion.getId() + "_" + product.getId();
		String numberValue = member.getAttributes().get(numberKey);
		if (promotion.getMaxNum() != 0) {
			if (StringUtils.isNotEmpty(numberValue)) {
				// 已参加此次活动
				int number = Integer.valueOf(numberValue);
				if (number >= promotion.getMaxNum()) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public int getTokenCount(Promotion promotion, Product product) {
		int count = 0;
		if (promotion != null && product != null) {
			PromotionDao dao = (PromotionDao) super.getDao();
			count = dao.getTokenCount(promotion, product);
		}
		return count;
	}

	@Override
	public boolean checkCondition(Promotion promotion, OrderItem item, Member member) throws PromotionException {
		// 检测有效期
		if (!checkTimePeriod(promotion)) {
			return false;
		}

		try {
			// 检测会员资料
			if (!checkMember(promotion, member)) {
				return false;
			}

			// 检测限购条件
			if (!checkBuyLimition(promotion, member, item)) {
				return false;
			}
		} catch (PromotionException ex) {
			throw ex;
		}

		return true;
	}

	/**
	 * 检测促销活动将要发生的交易是否满足限购条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @param item
	 *            订单项
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkBuyLimition(Promotion promotion, Member member, OrderItem item) throws PromotionException {
		if (promotion == null || member == null || item == null) {
			return false;
		}

		// 检测商品是否违规
		if (!promotion.getProducts().isEmpty()) {
			if (item.getProduct() != null
					&& !item.getProduct().getCurrentPromotion().getId().equals(promotion.getId())) {
				String message = "商品" + item.getProduct().getName() + "无法参加活动(" + promotion.getName() + ")";
				throw new PromotionException(message);
			}
		}

		// 检测最小起购
		if (promotion.getMinNum() != 0 && item.getQuantity() < promotion.getMinNum()) {
			String message = "商品" + item.getProduct().getName() + "至少购买" + promotion.getMinNum() + "件";
			throw new PromotionException(message);
		}

		// 检测最大限购
		int number = markService.markNumber(member, promotion, item.getProduct());
		if (promotion.getMaxNum() != 0 && number + item.getQuantity() > promotion.getMaxNum()) {
			String message = "商品" + item.getProduct().getName() + "最多只能购买" + promotion.getMaxNum() + "件";
			throw new PromotionException(message);
		}

		return true;
	}

	/**
	 * 检测会员是否满足促销活动基本条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	public boolean checkMember(Promotion promotion, Member member) throws PromotionException {
		if (promotion == null || member == null) {
			return false;
		}

		if (!checkMobile(promotion, member) || !checkMail(promotion, member) || !checkBirth(promotion, member)) {
			return false;
		}

		if (!checkPoint(promotion, member) || !checkRank(promotion, member)) {
			return false;
		}

		return true;
	}

	/**
	 * 检测是否在有效期内
	 * 
	 * @param promotion
	 *            促销活动
	 * @return 在有效期内, 返回true; 否则, 返回false
	 */
	private boolean checkTimePeriod(Promotion promotion) {
		if (promotion == null) {
			return false;
		}

		Date now = new Date();
		if (promotion.getBeginDate().after(now) || promotion.getEndDate().before(now)) {
			return false;
		}

		return true;
	}

	/**
	 * 检测手机绑定是否满足条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkMobile(Promotion promotion, Member member) throws PromotionException {
		if (promotion == null || member == null) {
			return false;
		}

		String memberMobile = promotion.getAttributes().get(Promotion.Keys.memberMobile);
		if (StringUtils.isNotEmpty(memberMobile) && StringUtils.equals(memberMobile, "true")) {
			String mobileValidated = member.getAttributes().get(Member.Keys.mobileValidated);
			if (StringUtils.isEmpty(mobileValidated) || !StringUtils.equals(mobileValidated, "true")) {
				throw new PromotionException("参与活动" + promotion.getName() + "需要绑定手机号");
			}
		}

		return true;
	}

	/**
	 * 检测邮件验证是否满足条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkMail(Promotion promotion, Member member) throws PromotionException {
		if (promotion == null || member == null) {
			return false;
		}

		String memberMail = promotion.getAttributes().get(Promotion.Keys.memberMail);
		if (StringUtils.isNotEmpty(memberMail) && StringUtils.equals(memberMail, "true")) {
			String mailValidated = member.getAttributes().get(Member.Keys.mailValidated);
			if (StringUtils.isEmpty(mailValidated) || !StringUtils.equals(mailValidated, "true")) {
				throw new PromotionException("参与活动" + promotion.getName() + "需要验证邮箱");
			}
		}

		return true;
	}

	/**
	 * 检测会员生日资料是否满足条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkBirth(Promotion promotion, Member member) {
		if (promotion == null || member == null) {
			return false;
		}

		String memberBirth = promotion.getAttributes().get(Promotion.Keys.memberBirth);
		if (StringUtils.isNotEmpty(memberBirth) && StringUtils.equals(memberBirth, "true")) {
			if (member.getBirth() == null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检测会员积分是否满足条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkPoint(Promotion promotion, Member member) {
		if (promotion == null || member == null) {
			return false;
		}

		if (promotion.getPointValue() > 0 && member.getPoint() < promotion.getPointValue()) {
			return false;
		}

		return true;
	}

	/**
	 * 检测会员等级是否满足条件
	 * 
	 * @param promotion
	 *            促销活动
	 * @param member
	 *            会员对象
	 * @return 满足条件返回true; 否则, 返回false
	 */
	private boolean checkRank(Promotion promotion, Member member) {
		if (promotion == null || member == null) {
			return false;
		}

		Set<MemberRank> ranks = promotion.getMemberRanks();
		if (!ranks.isEmpty() && !ranks.contains(member.getMemberRank())) {
			return false;
		}

		return true;
	}

}
