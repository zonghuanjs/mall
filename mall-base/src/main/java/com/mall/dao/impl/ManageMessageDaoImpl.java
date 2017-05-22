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

import com.mall.dao.ManageMessageDao;
import com.mall.entity.MallMessage;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ManageMessageDaoImpl extends BaseDaoImpl<Long, MallMessage> implements ManageMessageDao {

	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public List<Long> getMallMessages(long memberId, int pageNumber, int pageSize, int msgType, Date lastTime,
			List<Long> total) {

		Connection connection = null;
		List<Long> ids = new ArrayList<Long>();

		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				String sql = "{call get_mall_message(?, ?, ?, ?, ?, ?, ?)}";
				CallableStatement call = connection.prepareCall(sql);
				call.setLong(1, memberId);
				if (memberId <= 0) {
					call.setNull(1, Types.BIGINT);
				}

				call.setInt(2, pageNumber);
				if (pageNumber == -1) {
					call.setNull(2, Types.INTEGER);
				}

				call.setInt(3, pageSize);
				if (pageSize == -1) {
					call.setNull(3, Types.INTEGER);
				}

				call.setInt(4, msgType);

				if (lastTime == null) {
					call.setNull(5, Types.TIMESTAMP);
				} else {
					call.setTimestamp(5, new Timestamp(lastTime.getTime()));
				}

				call.registerOutParameter(6, Types.INTEGER);
				call.registerOutParameter(7, Types.INTEGER);
				ResultSet rs = call.executeQuery();
				int count = call.getInt(6);
				int newc = call.getInt(7);

				while (rs.next()) {
					ids.add(rs.getLong(1));
				}

				if (total == null) {
					total = new ArrayList<Long>();
				}
				total.add((long) count);
				total.add((long) newc);
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

		return ids;
	}

}
