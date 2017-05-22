package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.Apply;
import com.mall.entity.ApplyLog;
import com.mall.service.ApplyLogService;

@Service
public class ApplyLogServiceImpl extends BaseServiceImpl<Long, ApplyLog> implements ApplyLogService {

	@Override
	public boolean add(ApplyLog model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(ApplyLog model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public void log(Apply apply, String content, String operator, String ip) {
		ApplyLog log = new ApplyLog();
		log.setApply(apply);
		log.setContent(content);
		log.setOperator(operator);
		log.setIp(ip);
		log.setType(apply.getType());
		this.add(log);
	}

}
