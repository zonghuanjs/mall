package com.mall.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.Repair;
import com.mall.service.RepairReportService;
import com.mall.service.RepairService;

@Service
public class RepairServiceImpl extends BaseServiceImpl<Long, Repair> implements RepairService {
	@Autowired
	private RepairReportService reportService;// 检测报告服务

	@Override
	public boolean add(Repair model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		return super.add(model);
	}

	@Override
	public boolean update(Repair model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}
		return super.update(model);
	}

	@Override
	public boolean delete(Long id) {
		Repair model = this.get(id);
		if (model != null)
			beforeDelete(model);
		return super.delete(id);
	}

	@Override
	public boolean delete(Long[] ids) {
		for (Long id : ids) {
			Repair model = this.get(id);
			if (model != null)
				beforeDelete(model);
		}
		return super.delete(ids);
	}

	/**
	 * 删除前的清理工作
	 * 
	 * @param model
	 */
	private void beforeDelete(Repair model) {
		if (model != null) {
			if (model.getReport() != null)
				reportService.delete(model.getReport().getId());
		}
	}

}
