package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.RefundDao;
import com.mall.entity.Apply;
import com.mall.entity.Order;
import com.mall.entity.OrderLog;
import com.mall.entity.Refund;
import com.mall.pager.Pager;
import com.mall.service.ApplyLogService;
import com.mall.service.OrderLogService;
import com.mall.service.OrderService;
import com.mall.service.RefundService;
import com.mall.util.CommonUtil;

@Service
public class RefundServiceImpl extends BaseServiceImpl<Long, Refund> implements RefundService {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderLogService logService;// 订单日志服务

	@Autowired
	private ApplyLogService alogService;// 售后申请日志服务

	@Override
	public boolean add(Refund model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
			if (model.getSn() == null) {
				model.setSn(CommonUtil.createRefundSN());
			}
		}
		return super.add(model);
	}

	@Override
	public boolean update(Refund model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}

		boolean ret = super.update(model);
		if (ret) {
			// 检测退款状态
			if (model.getStatus() == Refund.Status.success) {
				Order order = model.getOrder();

				if (order.getReturnApply() != null) {
					Apply apply = order.getReturnApply();
					// 添加日志
					alogService.log(apply, "退款成功", "system", "-");
				} else {
					order.setOrderStatus(Order.OrderStatus.CLOSED);
					if (orderService.update(order)) {
						logService.log(order, "system", "订单退款成功", OrderLog.OrderOperationType.refundOrder);
					}
				}
			}
		}
		return ret;
	}

	@Override
	public List<Refund> findByPager(Pager<Refund> pager) {
		// TODO Auto-generated method stub
		RefundDao dao = (RefundDao) this.getDao();
		return dao.findByPager(pager);
	}

	@Override
	public Refund getFromSn(String sn) {
		List<Refund> list = this.getListFromProperty("sn", sn);
		Refund refund = list.isEmpty() ? null : list.iterator().next();
		return refund;
	}

}
