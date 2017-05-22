package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.dao.OrderDao;
import com.mall.entity.Order;
import com.mall.pager.Pager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Long, Order> implements OrderDao {

	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	/**
	 * 个人中心 我的订单总数
	 */
	@Override
	public long getOrdersCount(Map<String, Object> filter, Map<String, Set<Object>> inSet, String orderby) {
		long count = 0;
		List<Order> list = this.getList(filter, inSet, orderby, 0, 0);
		if (list != null) {
			count = list.size();
		}
		return count;
	}

	/**
	 * 获取个人订单
	 */
	@Override
	public List<Order> getOrderList(Map<String, Object> filter, Map<String, Set<Object>> inSet, String orderby) {
		List<Order> list = this.getList(filter, inSet, orderby, 0, 0);

		return list;
	}

	@Override
	public List<Order> getOrderList(Map<String, Object> filter, String orderby, String propertyName, Object lo,
			Object hi) {
		List<Order> list = this.getList(filter, orderby, propertyName, lo, hi);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByPager(Pager<Order> pager, Date beginDate, Date endDate) {
		Session session = null;
		List<Order> list = new LinkedList<>();

		try {
			session = this.currentSession();
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
			Criteria query = detachedCriteria.getExecutableCriteria(session);

			// 等值条件
			if (pager.getFilter() != null) {
				super.setEQCondition(query, pager.getFilter());
			}

			// 模糊查询
			if (pager.getLikes() != null) {
				super.setLikeCondition(query, pager.getLikes());
			}

			// 集合查询
			if (pager.getInset() != null) {
				super.setInCondition(query, pager.getInset());
			}

			// 时间区间设置
			query.add(Restrictions.between("createDate", beginDate, endDate));

			// 查询数量
			query.setProjection(Projections.rowCount());
			pager.setTotalCount(((Number) query.uniqueResult()).longValue());

			// 排序条件
			if (pager.getOrderby() != null && !"".equals(pager.getOrderby())) {
				super.setOrderCondition(query, pager.getOrderby());
			}

			query.setProjection(null);
			query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

			if (pager.getFirst() > 0) {
				query.setFirstResult(pager.getFirst());
			}

			query.setMaxResults(pager.getPageSize());

			list.addAll(query.list());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByPager(Pager<Order> pager, Date date, int option) {
		Session session = null;
		List<Order> list = new LinkedList<>();

		try {
			session = this.currentSession();
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
			Criteria query = detachedCriteria.getExecutableCriteria(session);

			// 等值条件
			if (pager.getFilter() != null) {
				super.setEQCondition(query, pager.getFilter());
			}

			// 模糊查询
			if (pager.getLikes() != null) {
				super.setLikeCondition(query, pager.getLikes());
			}

			// 集合查询
			if (pager.getInset() != null) {
				super.setInCondition(query, pager.getInset());
			}

			// 时间查询
			if (option < 0) {
				query.add(Restrictions.le("createDate", date));
			} else if (option > 0) {
				query.add(Restrictions.ge("createDate", date));
			} else {
				query.add(Restrictions.eq("createDate", date));
			}

			// 查询数量
			query.setProjection(Projections.rowCount());
			pager.setTotalCount(((Number) query.uniqueResult()).longValue());

			// 排序条件
			if (pager.getOrderby() != null && !"".equals(pager.getOrderby())) {
				super.setOrderCondition(query, pager.getOrderby());
			}

			query.setProjection(null);
			query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

			if (pager.getFirst() > 0) {
				query.setFirstResult(pager.getFirst());
			}

			query.setMaxResults(pager.getPageSize());

			list.addAll(query.list());
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Order> getFilterOrders(long memberid, int pageNumber, int pageSize, int status, int days,
			List<Long> total) {

		Connection connection = null;
		List<Order> orders = new ArrayList<Order>();
		List<Long> ids = new ArrayList<Long>();

		if (memberid < 1) {
			return null;
		}

		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				String sql = "{call search_orders(?, ?, ?, ?, ?, ?)}";
				CallableStatement call = connection.prepareCall(sql);
				call.setLong(1, memberid);

				call.setInt(2, pageNumber);
				if (pageNumber == -1) {
					call.setNull(2, Types.INTEGER);
				}

				call.setInt(3, pageSize);
				if (pageSize == -1) {
					call.setNull(3, Types.INTEGER);
				}

				call.setInt(4, status);

				call.setInt(5, days);
				if (days == -1) {
					call.setNull(5, Types.INTEGER);
				}

				call.registerOutParameter(6, Types.INTEGER);
				ResultSet rs = call.executeQuery();
				int count = call.getInt(6);

				while (rs.next()) {
					ids.add(rs.getLong(1));
				}

				orders = this.get(ids.toArray(new Long[ids.size()]));

				if (total == null) {
					total = new ArrayList<Long>();
				}
				total.add((long) count);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {

				}
			}
		}

		return orders;
	}

}
