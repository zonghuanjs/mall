package com.mall.dao.impl;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.CouponDao;
import com.mall.entity.Coupon;
import com.mall.entity.CouponCode;
import com.mall.util.TimeUtils;

@Repository
public class CouponDaoImpl extends BaseDaoImpl<Long, Coupon> implements CouponDao
{

	@Override
	public long issueNumber(Coupon coupon, Date day)
	{
		long number = 0;
		
		try
		{
			Session session = this.currentSession();
			Criteria codeQuery = session.createCriteria(CouponCode.class);
			codeQuery.add(Restrictions.eq("coupon", coupon));
			codeQuery.setProjection(Projections.rowCount());
			
			StringBuffer prefix = new StringBuffer(coupon.getPrefix());
			prefix.append(TimeUtils.formatDate("yyyyMMdd", day)).append("%");
			codeQuery.add(Restrictions.like("code", prefix.toString()));
			
			number = ((Number)codeQuery.uniqueResult()).longValue();
		}
		catch(HibernateException ex)
		{
			ex.printStackTrace();
		}
		
		return number;
	}
}
