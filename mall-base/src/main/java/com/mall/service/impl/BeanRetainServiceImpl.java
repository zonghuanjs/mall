package com.mall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mall.bean.SystemConfig;

import com.mall.dao.BeanRetainDao;
import com.mall.entity.BeanRetain;
import com.mall.entity.Comment;
import com.mall.entity.Member;
import com.mall.entity.Order;
import com.mall.pager.Pager;
import com.mall.service.BeanRetainService;
import com.mall.util.SystemConfigUtil;
import com.mall.util.TimeUtils;

@Service
public class BeanRetainServiceImpl extends BaseServiceImpl<Long, BeanRetain> implements BeanRetainService {
	private static final Logger logger = LoggerFactory.getLogger(BeanRetainServiceImpl.class);

	@Override
	public boolean add(BeanRetain model) {
		if (model != null) {
			model.setCreateDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public int queryTotal(Member member) {
		if (member == null) {
			return 0;
		}

		BeanRetainDao dao = (BeanRetainDao) super.getDao();
		return dao.queryTotal(member);
	}

	@Override
	public List<BeanRetain> findByPager(Pager<BeanRetain> pager) {
		BeanRetainDao dao = (BeanRetainDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public List<BeanRetain> findByPager(Pager<BeanRetain> pager, Map<String, Object> geFilter,
			Map<String, Object> leFilter) {
		BeanRetainDao dao = (BeanRetainDao) this.getDao();
		return dao.findByPager(pager, geFilter, leFilter);
	}

	@Override
	public void beanExpiredTaskService(int count) {

		BeanRetainDao dao = (BeanRetainDao) this.getDao();
		dao.beanExpiredTaskService(count);
	}

	@Override
	public boolean consumeBeansAccordingtoMember(long memberid, long orderid, int beans) {
		BeanRetainDao dao = (BeanRetainDao) this.getDao();
		return dao.consumeBeansAccordingtoMember(memberid, orderid, beans);
	}

	@Override
	public int queryExpiredTotal(Member member, Date date) {
		if (member == null || date == null) {
			return 0;
		}

		BeanRetainDao dao = (BeanRetainDao) this.getDao();
		return dao.queryExpiredTotal(member, date);
	}

	@Override
	public boolean issueBean(Order order) {
		if (order == null || order.getMember() == null) {
			logger.error("非法会员对象或者订单");
			return false;
		}

		SystemConfig config = SystemConfigUtil.getSystemConfig();
		Date beanExpireDate = TimeUtils.delayTime(new Date(), config.getBeanExpiredTime());
		BeanRetain bean = new BeanRetain();
		bean.setExpiredTime(beanExpireDate);
		bean.setMember(order.getMember());
		bean.setMemo("确认收货，获取芯豆");
		bean.setOptValue((int) (order.getAmountPaid() * config.getFeedbackScale()));
		bean.setType(1);

		return add(bean);
	}

	@Override
	public boolean issueBean(Comment comment) {
		if (comment == null || comment.getMember() == null) {
			logger.warn("非法订单评论");
			return false;
		}

		SystemConfig config = SystemConfigUtil.getSystemConfig();
		Date beanExpireDate = TimeUtils.delayTime(new Date(), config.getBeanExpiredTime());
		BeanRetain bean = new BeanRetain();
		bean.setExpiredTime(beanExpireDate);
		bean.setMember(comment.getMember());
		bean.setMemo("评论订单奖励");

		int optValue = comment.getImgs().isEmpty() ? config.getBeanComment() : config.getBeanComImage();
		bean.setOptValue(optValue);
		bean.setType(2);

		return add(bean);
	}

}
