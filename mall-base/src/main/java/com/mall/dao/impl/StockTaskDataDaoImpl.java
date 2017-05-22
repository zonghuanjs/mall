package com.mall.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.StockTaskDataDao;
import com.mall.entity.StockTaskData;

@Repository
public class StockTaskDataDaoImpl extends BaseDaoImpl<Long, StockTaskData> implements StockTaskDataDao {

	@Override
	public StockTaskData findTaskData(Date date) {
		StockTaskData data = null;
		Session session = super.currentSession();
		try {
			Criteria query = session.createCriteria(StockTaskData.class);
			query.add(Restrictions.le("startDate", date));
			query.add(Restrictions.ge("endDate", date));
			query.setMaxResults(1);
			List<?> list = query.list();
			data = (StockTaskData) (list.isEmpty() ? null : list.iterator().next());
		} catch (HibernateException ex) {

		}
		return data;
	}

}
