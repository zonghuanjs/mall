package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.entity.RepairReport;
import com.mall.service.RepairReportService;

@Service
public class RepairReportServiceImpl extends BaseServiceImpl<Long, RepairReport> implements RepairReportService {

	@Override
	public boolean add(RepairReport model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(RepairReport model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}
}
