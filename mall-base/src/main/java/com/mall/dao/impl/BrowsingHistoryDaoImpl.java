package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import aj.org.objectweb.asm.Type;

import com.mall.dao.BrowsingHistoryDao;
import com.mall.entity.BrowsingHistory;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class BrowsingHistoryDaoImpl extends BaseDaoImpl<Long, BrowsingHistory> implements BrowsingHistoryDao {
	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public List<BrowsingHistory> getBrowsingHistories(Long memberid, String ip, Date begin, Date end, int pagenumber,
			int pagesize, List<Long> count) {
		List<BrowsingHistory> result = null;
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call search_histories(?, ?, ?, ?, ?, ?, ?)}");

			if (memberid == null || memberid < 1) {
				call.setNull(1, Types.BIGINT);
			} else {
				call.setLong(1, memberid);
			}

			if (ip == null || "".equals(ip.trim())) {
				call.setNull(2, Types.VARCHAR);
			} else {
				call.setString(2, ip);
			}

			if (begin == null) {
				call.setNull(3, Types.TIMESTAMP);
			} else {
				call.setTimestamp(3, new Timestamp(begin.getTime()));
			}

			if (end == null) {
				call.setNull(4, Types.TIMESTAMP);
			} else {
				call.setTimestamp(4, new Timestamp(end.getTime()));
			}

			if (pagenumber <= 0 && pagesize <= 0) {
				call.setNull(5, Types.INTEGER);
				call.setNull(6, Types.INTEGER);
			} else {
				call.setInt(5, (pagenumber < 1 ? 1 : pagenumber));
				call.setInt(6, pagesize);
			}

			call.registerOutParameter(7, Types.BIGINT);
			ResultSet rs = call.executeQuery();
			count.add(call.getLong(7));

			List<Long> ids = new ArrayList<Long>();
			while (rs.next()) {

				ids.add(rs.getLong(1));
			}

			if (ids != null && ids.size() > 0) {

				result = new ArrayList<BrowsingHistory>();
				result = this.get(ids.toArray(new Long[ids.size()]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return result;
	}

	@Override
	public boolean judgeProductExists(Long memberid, String ip, Date dt, Long pid, List<Long> ids) {

		if (ids == null) {
			ids = new ArrayList<Long>();
		}

		Connection connection = null;

		try {

			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call judge_product_in_histories(?, ?, ?, ?)}");

			if (memberid == null || memberid < 1) {
				call.setNull(1, Types.BIGINT);
			} else {
				call.setLong(1, memberid);
			}

			if (ip == null || ip.trim().length() == 0) {
				call.setNull(2, Types.VARCHAR);
			} else {
				call.setString(2, ip);
			}

			if (dt == null) {
				call.setNull(3, Types.TIMESTAMP);
			} else {
				call.setTimestamp(3, new Timestamp(dt.getTime()));
			}

			if (pid == null || pid < 0) {
				call.setNull(4, Types.BIGINT);
			} else {
				call.setLong(4, pid);
			}

			ResultSet rs = call.executeQuery();

			while (rs.next()) {

				ids.add(rs.getLong(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return ids.size() > 0 ? true : false;
	}

	
	@Override
	public List<Long> getProductsAccordingtoHistories(Long memberid, String ip, int pagenumber, int pagesize,
			List<Integer> total) {

		Connection connection = null;
		List<Long> ids = new ArrayList<Long>();

		try {

			connection = dataSource.getConnection();
			CallableStatement call = connection.prepareCall("{call get_products_by_histories(?, ?, ?, ?, ?)}");

			if (memberid == null) {
				call.setNull(1, Type.LONG);
			} else {
				call.setLong(1, memberid);
			}

			call.setString(2, ip);
			if (ip == null) {
				call.setNull(2, Type.CHAR);
			} else {
				call.setString(2, ip);
			}

			call.setInt(3, pagenumber);
			call.setInt(4, pagesize);
			call.registerOutParameter(5, Type.INT);

			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				ids.add(rs.getLong(1));
			}

			int count = call.getInt(5);
			if (total == null) {
				total = new ArrayList<Integer>();
			}
			total.add(count);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return ids;
	}

}
