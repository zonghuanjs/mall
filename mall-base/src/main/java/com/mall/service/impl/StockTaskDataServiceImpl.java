package com.mall.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.mall.dao.StockTaskDataDao;
import com.mall.entity.StockTaskData;
import com.mall.service.StockTaskDataService;

@Service
public class StockTaskDataServiceImpl extends BaseServiceImpl<Long, StockTaskData> implements StockTaskDataService {

	@Override
	public StockTaskData findTaskData(Date date) {
		StockTaskData data = null;
		if (date != null) {
			StockTaskDataDao dao = (StockTaskDataDao) super.getDao();
			data = dao.findTaskData(date);
		}
		return data;
	}

}
