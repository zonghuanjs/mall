package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.dao.ManageMessageDao;
import com.mall.entity.MallMessage;
import com.mall.service.ManageMessageService;

@Repository
public class ManageMessageServiceImpl extends BaseServiceImpl<Long, MallMessage> implements ManageMessageService {

	@Autowired
	private ManageMessageDao manageMessageDao;

	@Override
	public List<Long> getMallMessages(long memberId, int pageNumber, int pageSize, int msgType, Date lastTime,
			List<Long> total) {
		// 消息类型: 1,系统消息; 2,优惠券消息; 3,促销消息
		if (msgType == 2 && memberId <= 0) {
			return null;
		}

		return manageMessageDao.getMallMessages(memberId, pageNumber, pageSize, msgType, lastTime, total);
	}

}
