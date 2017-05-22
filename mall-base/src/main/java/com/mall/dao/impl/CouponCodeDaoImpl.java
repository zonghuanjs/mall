package com.mall.dao.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.CouponCodeDao;
import com.mall.entity.CouponCode;
import com.mall.entity.Member;
import com.mall.pager.Pager;
import com.mall.util.CriteriaUtils;

@Repository
public class CouponCodeDaoImpl extends BaseDaoImpl<Long, CouponCode> implements CouponCodeDao {

	@SuppressWarnings("unchecked")
	public List<CouponCode> findValidCodes(Member member, Pager<CouponCode> pager) {

		Session session = null;
		List<CouponCode> list = new LinkedList<>();
		try {
			session = this.currentSession();
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CouponCode.class);

			Criteria query = detachedCriteria.getExecutableCriteria(session);

			if (pager != null) {
				// 集合筛选
				if (pager.getInset() != null) {
					CriteriaUtils.setInCondition(query, pager.getInset());
				}

				// 时间
				Date now = new Date();
				query.add(Restrictions.eq("used", false)).add(Restrictions.eq("member", member));
				query.add(Restrictions.ge("expired", now));
				Criteria couponQuery = query.createCriteria("coupon");
				couponQuery.add(Restrictions.le("beginDate", now));
				couponQuery.add(Restrictions.eq("enabled", true));

				// 查询数量
				query.setProjection(Projections.rowCount());
				pager.setTotalCount(((Number) query.uniqueResult()).longValue());

				// 排序条件
				if (pager.getOrderby() != null && pager.getOrderby().length() > 0) {
					CriteriaUtils.setOrderCondition(query, pager.getOrderby());
				}

				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

				if (pager.getFirst() > 0)
					query.setFirstResult(pager.getFirst());
				if (pager.getPageSize() > 0)
					query.setMaxResults(pager.getPageSize());
			} else {
				// 时间
				Date now = new Date();
				query.add(Restrictions.eq("used", false)).add(Restrictions.eq("member", member));
				query.add(Restrictions.ge("expired", now));
				Criteria couponQuery = query.createCriteria("coupon");
				couponQuery.add(Restrictions.le("beginDate", now));
				couponQuery.add(Restrictions.eq("enabled", true));

				// 查询数量
				query.setProjection(Projections.rowCount());
				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

			}
			list.addAll(query.list());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CouponCode> findByPager(Pager<CouponCode> pager) {
		// TODO Auto-generated method stub
		if (pager == null)
			return null;
		return super.findByPager(pager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CouponCode> findByPager(Member member, Pager<CouponCode> pager, Map<String, Object> geFilter,
			Map<String, Object> ltFilter) {
		// TODO Auto-generated method stub
		if (pager == null) {
			return null;
		} else {
			Session session = null;
			List<CouponCode> list = new LinkedList<>();
			try {
				session = this.currentSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CouponCode.class);

				Criteria query = detachedCriteria.getExecutableCriteria(session);

				// 集合筛选
				if (pager.getInset() != null) {
					CriteriaUtils.setInCondition(query, pager.getInset());
				}
				if (pager.getFilter() != null) {
					CriteriaUtils.setEQCondition(query, pager.getFilter());
				}
				// 模糊查询
				if (pager.getLikes() != null && !pager.getLikes().isEmpty()) {
					CriteriaUtils.setLikeCondition(query, pager.getLikes());
				}
				if (geFilter != null) {
					CriteriaUtils.setGECondition(query, geFilter);
				}
				if (ltFilter != null) {
					CriteriaUtils.setLTCondition(query, ltFilter);
				}
				// 时间
				// Date now = new Date();
				query.add(Restrictions.eq("member", member));
				/*
				 * query.add(Restrictions.ge("expired", now)); Criteria
				 * couponQuery = query.createCriteria("coupon");
				 * couponQuery.add(Restrictions.le("beginDate", now));
				 * couponQuery.add(Restrictions.eq("enabled", true));
				 */
				// 查询数量
				query.setProjection(Projections.rowCount());
				pager.setTotalCount(((Number) query.uniqueResult()).longValue());

				// 排序条件
				if (pager.getOrderby() != null && pager.getOrderby().length() > 0) {
					CriteriaUtils.setOrderCondition(query, pager.getOrderby());
				}

				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

				if (pager.getFirst() > 0)
					query.setFirstResult(pager.getFirst());
				if (pager.getPageSize() > 0)
					query.setMaxResults(pager.getPageSize());
				list.addAll(query.list());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return list;
		}

	}

	@Override
	public long getCount(Member member, Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = null;
		long count = 0;
		try {
			session = this.currentSession();
			Criteria query = session.createCriteria(CouponCode.class);

			if (eqFilter != null) {
				CriteriaUtils.setEQCondition(query, eqFilter);
			}
			if (geFilter != null) {
				CriteriaUtils.setGECondition(query, geFilter);
			}
			if (ltFilter != null) {
				CriteriaUtils.setLTCondition(query, ltFilter);
			}

			query.add(Restrictions.eq("member", member));
			query.setProjection(Projections.rowCount());
			// 查询数量
			count = ((Number) query.uniqueResult()).longValue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CouponCode> getCouponCodeList(Map<String, Object> eqFilter, Map<String, Object> geFilter,
			Map<String, Object> ltFilter, String orderby) {
		// TODO Auto-generated method stub
		Session session = null;
		List<CouponCode> list = new LinkedList<>();
		try {
			session = this.currentSession();

			Criteria query = session.createCriteria(CouponCode.class);

			// 筛选
			if (eqFilter != null) {
				CriteriaUtils.setEQCondition(query, eqFilter);
			}
			if (geFilter != null) {
				CriteriaUtils.setGECondition(query, geFilter);
			}
			if (ltFilter != null) {
				CriteriaUtils.setLTCondition(query, ltFilter);
			}

			// 排序条件
			if (orderby != null && orderby.length() > 0) {
				CriteriaUtils.setOrderCondition(query, orderby);
			}
			list.addAll(query.list());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
