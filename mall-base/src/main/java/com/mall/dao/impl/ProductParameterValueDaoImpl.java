package com.mall.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mall.dao.ProductParameterValueDao;
import com.mall.entity.ProductParameterValue;

@Repository
public class ProductParameterValueDaoImpl extends BaseDaoImpl<Long, ProductParameterValue>
		implements ProductParameterValueDao {
	@Override
	public ProductParameterValue get(Long products, Long parameters) {

		Session session = null;
		ProductParameterValue ppv = null;
		try {
			session = this.currentSession();
			String hql = String.format("from ProductParameterValue where products=:value and parameters=:param");
			Query query = session.createQuery(hql);
			query.setParameter("value", products);
			query.setParameter("param", parameters);
			ppv = (ProductParameterValue) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppv;
	}
}
