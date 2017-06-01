package com.mall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mall.dao.BeanUsedDao;
import com.mall.entity.BeanUsed;
import com.mall.pager.Pager;
import com.mall.service.BeanUsedService;

@Service
public class BeanUsedServiceImpl extends BaseServiceImpl<Long, BeanUsed> implements BeanUsedService {
	@Override
	public boolean add(BeanUsed model) {
		if (model != null) {
			model.setCreateDate(new Date());
		}

		return super.add(model);
	}

	@Override
	public List<BeanUsed> findByPager(Pager<BeanUsed> pager, Map<String, Object> geFilter,
			Map<String, Object> leFilter) {
		// TODO Auto-generated method stub
		BeanUsedDao dao = (BeanUsedDao) this.getDao();
		return dao.findByPager(pager, geFilter, leFilter);
	}
}
