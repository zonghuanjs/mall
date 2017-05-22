package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mall.dao.ProductDao;
import com.mall.entity.Product;
import com.mall.util.SQLUtils;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Long, Product> implements ProductDao {

	private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);

	/**
	 * 数据源
	 */
	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Override
	public List<Product> getListAccordingSales(Map<String, Object> filter, String orderby, int topNumber) {
		return this.getList(filter, orderby, 0, topNumber);
	}

	/**
	 * 根据商品名检索
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getListPropertyNameLike(Map<String, Object> filter) {
		Session session = this.currentSession();
		Criteria query = session.createCriteria(Product.class);
		List<Product> list = new LinkedList<>();
		if (filter != null) {
			Set<String> keys = filter.keySet();
			for (String key : keys) {
				if (filter.get(key) != null) {
					if (!key.equals("name")) {
						query.add(Restrictions.eq(key, filter.get(key)));
					} else {
						query.add(Restrictions.like(key, filter.get(key).toString(), MatchMode.ANYWHERE));
					}
				} else {
					query.add(Restrictions.isNull(key));
				}
			}
		}
		list.addAll(query.list());
		return list;
	}

	/**
	 * 可以按照属性值like进行查询 并带分页功能
	 * 
	 * @param eqFilter
	 * @param likeFilter
	 * @param inSet
	 * @param orderby
	 * @param first
	 * @param count
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getList(Map<String, Object> eqFilter, Map<String, Object> likeFilter,
			Map<String, Set<Object>> inSet, String orderby, int first, int count) {
		Session session = null;
		List<Product> list = new ArrayList<Product>();
		try {
			session = this.currentSession();
			Criteria query = session.createCriteria(Product.class);
			// 等值过滤
			if (eqFilter != null) {
				Set<String> keys = eqFilter.keySet();
				if (keys.size() > 0) {
					for (String key : keys) {
						if (eqFilter.get(key) != null) {
							query.add(Restrictions.eq(key, eqFilter.get(key)));
						} else {
							query.add(Restrictions.isNull(key));
						}
					}
				}
			}
			// 模糊匹配过滤
			if (likeFilter != null) {
				Set<String> keys = likeFilter.keySet();
				for (String key : keys) {
					if (likeFilter.get(key) != null) {
						query.add(Restrictions.like(key, likeFilter.get(key).toString(), MatchMode.ANYWHERE));
					} else {
						query.add(Restrictions.isNull(key));
					}
				}
			}
			// 集合筛选
			if (inSet != null) {
				Set<String> keys = inSet.keySet();
				for (String key : keys) {
					Set<Object> values = inSet.get(key);
					if (values != null && values.size() > 0) {
						query.add(Restrictions.in(key, values));
					}
				}
			}
			// 排序条件
			if (orderby != null && orderby.trim().length() > 0) {
				String[] orderInfo = orderby.split(",");
				for (String order : orderInfo) {
					Order propertyOrder = null;
					int index = order.indexOf(" ");
					if (index > 0) {
						String property = order.substring(0, index);
						String seqencing = order.substring(index + 1);

						if ("asc".equalsIgnoreCase(seqencing)) {
							propertyOrder = Order.asc(property);
						} else if ("desc".equalsIgnoreCase(seqencing)) {
							propertyOrder = Order.desc(property);
						}
					}
					if (propertyOrder != null) {
						query.addOrder(propertyOrder);
					}
				}
			}

			if (first > 0) {
				query.setFirstResult(first);
			}

			if (count > 0) {
				query.setMaxResults(count);
			}

			list.addAll(query.list());
		} catch (Exception ex) {
			throw ex;
		}
		return list;
	}

	/**
	 * 获取总数
	 * 
	 * @param eqFilter
	 * @param likeFilter
	 * @return
	 */
	@Override
	public long getCount(Map<String, Object> eqFilter, Map<String, Object> likeFilter) {

		Session session = null;
		long count = 0;
		try {
			session = this.currentSession();
			Criteria query = session.createCriteria(Product.class);
			query.setProjection(Projections.rowCount());
			if (eqFilter != null) {
				Set<String> keys = eqFilter.keySet();
				for (String key : keys) {
					if (eqFilter.get(key) != null)
						query.add(Restrictions.eq(key, eqFilter.get(key)));
					else
						query.add(Restrictions.isNull(key));
				}
			}

			if (likeFilter != null) {
				Set<String> keys = likeFilter.keySet();
				for (String key : keys) {
					if (likeFilter.get(key) != null)
						query.add(Restrictions.like(key, likeFilter.get(key).toString(), MatchMode.ANYWHERE));
					else
						query.add(Restrictions.isNull(key));
				}
			}

			count = ((Number) query.uniqueResult()).longValue();
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getList(Map<String, Object> eqFilter, Map<String, Object> likeFilter,
			Map<String, Set<Object>> inSet, Map<String, List<Double>> filterBetween, String orderby, int first,
			int count) {
		Session session = null;
		List<Product> list = new ArrayList<Product>();
		try {
			session = this.currentSession();
			Criteria query = session.createCriteria(Product.class);
			// 等值过滤
			if (eqFilter != null) {
				Set<String> keys = eqFilter.keySet();
				if (keys.size() > 0) {
					for (String key : keys) {
						if (eqFilter.get(key) != null) {
							query.add(Restrictions.eq(key, eqFilter.get(key)));
						} else {
							query.add(Restrictions.isNull(key));
						}
					}
				}
			}
			// 模糊匹配过滤
			if (likeFilter != null) {
				Set<String> keys = likeFilter.keySet();
				for (String key : keys) {
					if (likeFilter.get(key) != null) {
						query.add(Restrictions.like(key, likeFilter.get(key).toString(), MatchMode.ANYWHERE));
					} else {
						query.add(Restrictions.isNull(key));
					}
				}
			}
			// between取值
			if (filterBetween != null) {
				Set<String> keys = filterBetween.keySet();
				for (String key : keys) {
					if (filterBetween.get(key) != null) {
						List<Double> intList = filterBetween.get(key);
						if (intList != null && intList.size() == 2) {
							query.add(Restrictions.between(key, intList.get(0), intList.get(1)));
						}
					}
				}
			}

			// 集合筛选
			if (inSet != null) {
				Set<String> keys = inSet.keySet();
				for (String key : keys) {
					Set<Object> values = inSet.get(key);
					if (values != null && values.size() > 0) {
						query.add(Restrictions.in(key, values));
					}
				}
			}
			// 排序条件
			if (orderby != null && orderby.trim().length() > 0) {
				String[] orderInfo = orderby.split(",");
				for (String order : orderInfo) {
					Order propertyOrder = null;
					int index = order.indexOf(" ");
					if (index > 0) {
						String property = order.substring(0, index);
						String seqencing = order.substring(index + 1);

						if ("asc".equalsIgnoreCase(seqencing)) {
							propertyOrder = Order.asc(property);
						} else if ("desc".equalsIgnoreCase(seqencing)) {
							propertyOrder = Order.desc(property);
						}
					}
					if (propertyOrder != null) {
						query.addOrder(propertyOrder);
					}
				}
			}

			if (first > 0) {
				query.setFirstResult(first);
			}

			if (count > 0) {
				query.setMaxResults(count);
			}

			list.addAll(query.list());
		} catch (Exception ex) {
			throw ex;
		}
		return list;
	}

	@Override
	public List<Product> exactSearch(String key, String brandIds, String categoryIds, String specValueIds,
			String orderkey, boolean isdesc, int pageNumber, int pageSize, double low, double high,
			List<Integer> total) {
		List<Long> ids = new ArrayList<Long>();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement call = connection
					.prepareCall("{call exact_search_product(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

			if (key == null || "".equals(key.trim())) {
				call.setNull(1, Types.VARCHAR);
			} else {
				call.setString(1, key);
			}

			if (brandIds == null || "".equals(brandIds.trim())) {
				call.setNull(2, Types.VARCHAR);
			} else {
				call.setString(2, brandIds);
			}

			if (categoryIds == null || "".equals(categoryIds.trim())) {
				call.setNull(3, Types.VARCHAR);
			} else {
				call.setString(3, categoryIds);
			}

			if (specValueIds == null || "".equals(specValueIds.trim())) {
				call.setNull(4, Types.VARCHAR);
			} else {
				call.setString(4, specValueIds);
			}

			if (orderkey == null || "".equals(orderkey.trim())) {
				call.setNull(5, Types.VARCHAR);
				call.setNull(6, Types.BOOLEAN);
			} else {
				call.setString(5, orderkey);
				call.setBoolean(6, isdesc);
			}

			call.setInt(7, (pageNumber < 1 ? 1 : pageNumber));
			call.setInt(8, pageSize);

			if (low == 0 && high == 0) {
				call.setNull(9, Types.DOUBLE);
				call.setNull(10, Types.DOUBLE);
			} else {
				call.setDouble(9, low);
				call.setDouble(10, high);
			}

			call.registerOutParameter(11, Types.INTEGER);

			ResultSet rs = call.executeQuery();

			int count = call.getInt(11);
			total.add(count);

			while (rs.next()) {
				ids.add(Long.valueOf(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		Long[] pids = new Long[ids.size()];
		ids.toArray(pids);

		return get(pids);
	}

	@Override
	public Map<String, Object> getSomeInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> categoryids = new ArrayList<Long>();
		List<Long> specids = new ArrayList<Long>();
		List<Long> specvalueids = new ArrayList<Long>();
		List<Long> brandids = new ArrayList<Long>();

		Connection connection = null;

		try {
			String sql1 = "select category.id from tb_product as product left join tb_product_category as category"
					+ " on product.product_category = category.id where product.id in "
					+ " (select temp from tb_search_other) and category.parent is null group by category.id";

			String sql2 = "select distinct(specs.specifications) from tb_product_specification as specs "
					+ " where specs.products in (select temp from tb_search_other)";

			String sql3 = "select distinct(sp_values.specification_values) from tb_product_specification_value as sp_values "
					+ " where sp_values.products in (select temp from tb_search_other)";

			String sql4 = "select max(price),min(price) from tb_product where id in (select temp from tb_search_other)";

			String sql5 = "select distinct(product.brand) from tb_product as product where id in (select temp from tb_search_other)";

			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql1);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				categoryids.add(rs.getLong(1));
			}
			map.put("categoryids", categoryids);

			rs = ps.executeQuery(sql2);
			while (rs.next()) {
				specids.add(rs.getLong(1));
			}
			map.put("specids", specids);

			rs = ps.executeQuery(sql3);
			while (rs.next()) {
				specvalueids.add(rs.getLong(1));
			}
			map.put("specvalueids", specvalueids);

			rs = ps.executeQuery(sql4);
			while (rs.next()) {
				map.put("maxPrice", rs.getDouble(1));
				map.put("minPrice", rs.getDouble(2));
			}

			rs = ps.executeQuery(sql5);
			while (rs.next()) {
				brandids.add(rs.getLong(1));
			}
			map.put("brandids", brandids);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}
		return map;
	}

	@Override
	public void updateScore(Product product) {
		// 准备查询sql
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("select count(c.id) as num, avg(c.score) as score ");
		querySQL.append("from tb_comment c ");
		querySQL.append("left join tb_order_item oi on c.order_item=oi.id ");
		querySQL.append("where oi.product=? ").append("group by oi.product");

		Session session = super.currentSession();
		SQLQuery query = session.createSQLQuery(querySQL.toString());
		query.setLong(0, product.getId());
		ScrollableResults results = query.scroll();
		if (results.next()) {
			Object[] cols = results.get();
			if (cols != null && cols.length == 2) {
				Long count = ((Number) cols[0]).longValue();
				float score = ((Number) cols[1]).floatValue();

				// 更新数据
				product.setScore(score);
				product.setScoreCount(count);
				update(product);
			} else {
				logger.error("此次查询数据长度非法:{}", cols.length);
			}

		}
	}

}
