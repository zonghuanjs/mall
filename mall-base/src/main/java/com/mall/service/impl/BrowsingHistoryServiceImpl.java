package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.BrowsingHistoryDao;
import com.mall.entity.BrowsingHistory;
import com.mall.entity.Product;
import com.mall.service.BrowsingHistoryService;
import com.mall.service.ProductService;

@Service
public class BrowsingHistoryServiceImpl extends BaseServiceImpl<Long, BrowsingHistory>
		implements BrowsingHistoryService {

	@Autowired
	private BrowsingHistoryDao browsingHistoryDao;

	@Autowired
	private ProductService productService;

	@Override
	public List<BrowsingHistory> getBrowsingHistories(Long memberid, String ip, Date begin, Date end, int pagenumber,
			int pagesize, List<Long> count) {

		return this.browsingHistoryDao.getBrowsingHistories(memberid, ip, begin, end, pagenumber, pagesize, count);
	}

	@Override
	public boolean judgeProductExists(Long memberid, String ip, Date dt, Long pid, List<Long> ids) {
		return this.browsingHistoryDao.judgeProductExists(memberid, ip, dt, pid, ids);
	}

	@Override
	public List<Product> getProductsAccordingtoHistories(Long memberid, String ip, int pagenumber, int pagesize,
			List<Integer> count) {

		List<Product> ps = null;

		List<Long> ids = this.browsingHistoryDao.getProductsAccordingtoHistories(memberid, ip, pagenumber, pagesize,
				count);
		if (ids != null && ids.size() > 0) {

			Long[] pids = ids.toArray(new Long[ids.size()]);
			ps = productService.get(pids);
		}

		return ps;
	}

}
