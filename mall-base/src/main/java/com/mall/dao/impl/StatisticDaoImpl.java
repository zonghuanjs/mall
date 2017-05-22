package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.poi.hssf.record.formula.functions.T;
//import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mall.dao.StatisticDao;
import com.mall.pager.Pager;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class StatisticDaoImpl implements StatisticDao {

	@Autowired
	private ComboPooledDataSource dataSource;

	/**
	 * 获取原生的数据库连接
	 * 
	 * @return
	 */
	protected Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public long mobileBindCount() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null) {

			try {
				String sql = "select Count(*) from tb_member_attributes where attributes_key=? and attributes=?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, "mobileValidated");
				pstmt.setString(2, "true");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public long mailValidateCount() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null) {

			try {
				String sql = "select Count(*) from tb_member_attributes where attributes_key=? and attributes=?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, "mail_validated");
				pstmt.setString(2, "true");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public long loginInDaysCount(int days) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null) {

			try {
				String sql = "select Count(*) from tb_member where ((TO_DAYS(NOW()) - TO_DAYS(login_date)) < ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, days);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public double orderSalseCount(String propertyName, Object value) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null) {

			try {

				String sql = "select sum(amount_paid) from tb_order ";

				if (propertyName != null) {
					sql = sql + " where " + propertyName + "= ?";
					pstmt = connection.prepareStatement(sql);
					pstmt.setObject(1, value);
				} else {
					pstmt = connection.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public double refundSalseCount(String propertyName, Object value) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null) {

			try {

				String sql = "select sum(amount) from tb_refunds ";

				if (propertyName != null) {
					sql = sql + " where " + propertyName + "= ?";
					pstmt = connection.prepareStatement(sql);
					pstmt.setObject(1, value);
				} else {
					pstmt = connection.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public Map<String, Object> orderTimeSalseCount(Date date, int minDay, int maxDay) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		Map<String, Object> map = new HashMap<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("time", format.format(date));
		if (connection != null) {

			try {

				String sql = "select amount_paid from tb_order where to_days(create_date) - to_days(?) >= ? and to_days(create_date) - to_days(?) <= ? and order_status in (0,1,2,3,4)";

				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, format.format(date));
				pstmt.setInt(2, minDay);
				pstmt.setString(3, format.format(date));
				pstmt.setInt(4, maxDay);
				CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
				crs.populate(pstmt.executeQuery());
				pstmt.close();

				double salse = 0;
				long count = 0;
				while (crs.next()) {
					count++;
					salse += crs.getDouble(1);
				}

				map.put("salse", salse);
				map.put("count", count);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return map;
	}

	@Override
	public Map<String, Object> registerMemberCount(Date startDate, Date endDate) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		Map<String, Object> map = new HashMap<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (connection != null) {
			try {
				String sql = "select count(*) from tb_member where create_date >= ? and create_date <= ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, format.format(startDate));
				pstmt.setString(2, format.format(endDate));

				rs = pstmt.executeQuery();

				long count = 0;
				if (rs.next()) {
					count = rs.getLong(1);
				}
				map.put("count", count);
				map.put("time", format.format(startDate));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return map;
	}

	/**
	 * 关闭数据库链接
	 * 
	 * @param connection
	 * @param pstmt
	 * @param rs
	 */
	public void closeSql(Connection connection, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {

			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {

			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {

			}
		}

	}

	@Override
	public long getTradeRecodesCount(long productId) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		if (connection != null && productId > 0) {

			try {
				String sql = "select count(*) from tb_order_item as orderItems left join tb_order as orders on orderItems.orders = orders.id "
						+ "where orders.order_status != -1 and orderItems.product = ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, productId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;
	}

	@Override
	public Long[] getTradeRecodesInfo(long productId, int pageNumber, int pageSize, String order) {

		Long[] itemIds = new Long[pageSize];

		if (productId <= 0 || pageNumber < 1 || pageSize < 1 || order == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();

		if (connection != null && productId > 0) {

			try {

				String sql = "select orderItems.id from tb_order_item as orderItems left join tb_order as orders on orderItems.orders = orders.id "
						+ " where orders.order_status != -1 and orderItems.product = ?" + " order by orderItems."
						+ order.trim() + ", orderItems.id desc limit ? , ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, productId);
				pstmt.setInt(2, (pageNumber - 1) * pageSize);
				pstmt.setInt(3, pageSize);

				CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
				rs = pstmt.executeQuery();
				crs.populate(rs);
				pstmt.close();

				int i = 0;
				while (crs.next()) {
					itemIds[i++] = crs.getLong(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}
		}

		return itemIds;

	}

	@Override
	public Long[] getCommentsInfo(long productId, int pageNumber, int pageSize, String order) {
		Long[] itemIds = null;

		if (productId <= 0 || pageNumber < 1 || pageSize < 1 || order == null) {
			return null;
		}

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();

		if (connection != null && productId > 0) {

			try {

				String sql = "select comments.id from tb_comment as comments left join tb_order_item as orderitems on comments.order_item = orderitems.id "
						+ " where comments.deleted = 0 and orderitems.product = ? order by comments." + order.trim()
						+ " limit ?, ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, productId);
				pstmt.setInt(2, (pageNumber - 1) * pageSize);
				pstmt.setInt(3, pageSize);

				CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
				rs = pstmt.executeQuery();
				crs.populate(rs);
				pstmt.close();

				List<Long> ids = new LinkedList<Long>();
				while (crs.next()) {
					ids.add(crs.getLong(1));
				}

				itemIds = new Long[ids.size()];
				ids.toArray(itemIds);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}
		}

		return itemIds;

	}

	@Override
	public Map<String, Object> getCommentsInfo(long productId) {

		if (productId <= 0) {
			return null;
		}

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		Map<String, Object> result = new HashMap<String, Object>();

		if (connection != null && productId > 0) {

			try {

				String sql = "select count(*), avg(score) from tb_comment as comments left join tb_order_item as orderitems on comments.order_item = orderitems.id "
						+ " where comments.deleted = 0 and orderitems.product = ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, productId);

				CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
				rs = pstmt.executeQuery();
				crs.populate(rs);
				pstmt.close();

				while (crs.next()) {
					result.put("totalcount", crs.getLong(1));
					result.put("averagescore", crs.getDouble(2));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}
		}

		return result;
	}

	@Override
	public long getSalesCount(long productId) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		connection = this.getConnection();

		if (connection != null && productId > 0) {

			try {
				String sql = "select sum(orderItems.quantity) from tb_order_item as orderItems left join tb_order as orders on orderItems.orders = orders.id "
						+ "where orders.order_status != -1 and orderItems.product = ?";

				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1, productId);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return 0;

	}

	@Override
	public long getLoginCountAfter(Date date) {
		Connection connection = null;
		long count = 0;
		try {
			connection = dataSource.getConnection();
			String sql = "select count(*) as num from tb_member where login_date >= ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setObject(1, date);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong("num");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}
		return count;
	}

	@Override
	public List<Long> getCommentsInfoById(long productid, List<Integer> info) {

		if (productid <= 0) {
			return null;
		}

		Connection connection = null;
		ResultSet rs = null;
		List<Long> ids = new ArrayList<Long>();

		if (info == null) {
			info = new ArrayList<Integer>();
		}

		try {
			connection = dataSource.getConnection();

			if (connection != null) {

				// 顺序：评论总数、平均数、 好评数、中评数、差评数、晒图数
				CallableStatement statement = connection.prepareCall("{call calculateComments(?,?,?,?,?,?)}");
				statement.setLong(1, productid);
				statement.registerOutParameter(2, Types.INTEGER);// 评论总数
				statement.registerOutParameter(3, Types.DOUBLE);// 平均数
				statement.registerOutParameter(4, Types.INTEGER);// 好评数
				statement.registerOutParameter(5, Types.INTEGER);// 差评数
				statement.registerOutParameter(6, Types.INTEGER);// 晒图数

				CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
				rs = statement.executeQuery();
				crs.populate(rs);

				while (crs.next()) {
					ids.add(crs.getLong(1));
				}

				// 顺序：评论总数、平均数、 好评数、中评数、差评数、晒图数
				info.add(statement.getInt(2));// 评论总数

				int avg = (int) Math.ceil(statement.getDouble(3));// 平均数
				info.add(avg);// 平均数

				info.add(statement.getInt(4));// 好评数
				info.add(statement.getInt(2) - statement.getInt(4) - statement.getInt(5));// 中评数
				info.add(statement.getInt(5));// 差评数
				info.add(statement.getInt(6));// 晒图数
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ids;
	}

	@Override
	public List<Long> getCommentsInfoByPage(long productid, String type, int pageNumber, int pageSize) {

		if (productid <= 0 || type == "" || StringUtils.isEmpty(type.trim()) || pageNumber < 1 || pageSize < 1) {
			return null;
		}

		Connection connection = null;
		ResultSet rs = null;
		List<Long> ids = new ArrayList<Long>();

		try {
			connection = dataSource.getConnection();

			if (connection != null) {

				CallableStatement statement = connection.prepareCall("{call getCommentsByPage(?,?,?,?)}");
				statement.setLong(1, productid);
				statement.setString(2, type + "");
				statement.setInt(3, pageSize);
				statement.setInt(4, pageNumber);

				rs = statement.executeQuery();

				while (rs.next()) {
					ids.add(rs.getLong(1));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ids;

	}

	@SuppressWarnings("resource")
	@Override
	public List<Map<String, Object>> salesRanking(Pager<T> pager, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		connection = this.getConnection();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (connection != null) {

			try {

				StringBuffer stringBuffer = new StringBuffer();
				StringBuffer stringBuffer1 = new StringBuffer();
				stringBuffer.append(
						"select i.product,i.sn,i.full_name, sum(i.price),sum(i.quantity) from tb_order_item as i,tb_order as o where i.orders = o.id and o.order_status in (0,1,2,3,4) ");
				stringBuffer1.append(
						"select count(*) from tb_order_item as i,tb_order as o where i.orders = o.id and o.order_status in (0,1,2,3,4) ");

				if (startDate != null) {
					stringBuffer.append(" and i.create_date >= ?");
					stringBuffer1.append(" and i.create_date >= ?");
				}
				if (endDate != null) {
					stringBuffer.append(" and i.create_date <= ?");
					stringBuffer1.append(" and i.create_date <= ?");
				}
				stringBuffer.append(" group by i.product");
				stringBuffer1.append(" group by i.product");
				if (pager.getOrderby() != null && pager.getOrderby().length() > 0) {
					stringBuffer.append(" order by " + pager.getOrderby());
				}

				if (pager.getFirst() > 0) {
					stringBuffer.append(" limit " + pager.getFirst());
				} else {
					stringBuffer.append(" limit 0");
				}
				if (pager.getPageSize() > 0) {
					stringBuffer.append("," + pager.getPageSize());
				} else {
					stringBuffer.append(", 20");
				}

				pstmt = connection.prepareStatement(stringBuffer.toString());
				if (startDate != null) {
					if (endDate != null) {
						pstmt.setString(1, format.format(startDate));
						pstmt.setString(2, format.format(endDate));
					} else {
						pstmt.setString(1, format.format(startDate));
					}
				} else {
					if (endDate != null) {
						pstmt.setString(1, format.format(endDate));
					}

				}

				rs = pstmt.executeQuery();

				while (rs.next()) {
					Map<String, Object> map = new HashMap<>();
					map.put("id", rs.getLong(1));
					map.put("sn", rs.getString(2));
					map.put("name", rs.getString(3));
					map.put("price", rs.getDouble(4));
					map.put("quantity", rs.getLong(5));
					list.add(map);
				}

				pstmt = connection.prepareStatement(stringBuffer1.toString());
				if (startDate != null) {
					if (endDate != null) {
						pstmt.setString(1, format.format(startDate));
						pstmt.setString(2, format.format(endDate));
					} else {
						pstmt.setString(1, format.format(startDate));
					}
				} else {
					if (endDate != null) {
						pstmt.setString(1, format.format(endDate));
					}

				}
				rs = pstmt.executeQuery();
				Long i = 0L;
				while (rs.next()) {
					i++;
				}
				pager.setTotalCount(i);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeSql(connection, pstmt, rs);
			}

		}
		return list;
	}

	@Override
	public long memberCountLoginBeforeTime(Date lastLoginDate) {
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("select count(*) ").append("from tb_member m ");
		querySQL.append("where m.login_date < ? ").append("and m.enabled = true");

		long count = 0;
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(querySQL.toString());
			ps.setObject(1, lastLoginDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return count;
	}

}
