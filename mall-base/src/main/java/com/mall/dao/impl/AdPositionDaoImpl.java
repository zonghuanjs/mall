package com.mall.dao.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.AdPositionDao;
import com.mall.entity.Ad;
import com.mall.entity.AdPosition;

@Repository
public class AdPositionDaoImpl extends BaseDaoImpl<Long, AdPosition> implements AdPositionDao {

	@Override
	public List<Ad> findEffectiveAds(AdPosition pos) {
		List<Ad> ads = new LinkedList<Ad>();

		Session session = currentSession();
		DetachedCriteria criteria = DetachedCriteria.forClass(Ad.class);
		Criteria query = criteria.getExecutableCriteria(session);
		Date nowDate = new Date();
		query.add(Restrictions.lt("beginDate", nowDate));
		query.add(Restrictions.gt("endDate", nowDate));
		query.add(Restrictions.eq("position", pos));
		query.addOrder(Order.asc("orders"));

		ScrollableResults results = query.scroll();
		while (results.next()) {
			Ad ad = (Ad) results.get(0);
			ads.add(ad);
		}

		return ads;
	}

}
