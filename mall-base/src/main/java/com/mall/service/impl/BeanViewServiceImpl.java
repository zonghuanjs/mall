package com.mall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mall.dao.BeanViewDao;
import com.mall.entity.BeanView;
import com.mall.pager.Pager;
import com.mall.service.BeanViewService;

@Service
public class BeanViewServiceImpl extends BaseServiceImpl<Date, BeanView> implements BeanViewService {

	@Override
	public List<BeanView> findByPager(Pager<BeanView> pager, Map<String, Object> geFilter,
			Map<String, Object> leFilter) {
		// TODO Auto-generated method stub
		BeanViewDao dao = (BeanViewDao) this.getDao();
		return dao.findByPager(pager, geFilter, leFilter);
	}

}
