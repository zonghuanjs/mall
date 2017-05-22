package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.dao.QueueDao;
import com.mall.util.QiangQueue;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class QueueDaoImpl implements QueueDao {

	@Autowired
	private ComboPooledDataSource dataSource;

	@Override
	public boolean add(Long key1, Long key2, Long value) {
		boolean ret = true;

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call queue_add(?,?,?)}");
				call.setLong(1, key1);
				call.setLong(2, key2);
				call.setLong(3, value);
				call.execute();
			}
		} catch (SQLException ex) {
			ret = false;
		} finally {
			close(connection);
		}

		return ret;
	}

	@Override
	public List<Long> get(Long key1, Long key2) {
		List<Long> list = new LinkedList<>();

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call queue_get(?,?)}");
				call.setLong(1, key1);
				call.setLong(2, key2);
				ResultSet rs = call.executeQuery();
				while (rs.next()) {
					list.add(rs.getLong(1));
				}
				rs.close();
			}
		} catch (SQLException ex) {

		} finally {
			close(connection);
		}
		return list;
	}

	@Override
	public void clear(Long key1, Long key2) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call queue_clear(?,?)}");
				call.setLong(1, key1);
				call.setLong(2, key2);
				call.execute();
			}
		} catch (SQLException ex) {

		} finally {
			close(connection);
		}
	}

	@Override
	public void remove(Long key1, Long key2, Long value) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call queue_remove(?,?,?)}");
				call.setLong(1, key1);
				call.setLong(2, key2);
				call.setLong(3, value);
				call.execute();
			}
		} catch (SQLException ex) {

		} finally {
			close(connection);
		}
	}

	/**
	 * 释放连接
	 * 
	 * @param connection
	 */
	protected void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public QiangQueue getQueueData() {
		QiangQueue queue = new QiangQueue();

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				PreparedStatement ps = connection.prepareCall("select * from tb_queue");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Long key1 = rs.getLong("key1");
					Long key2 = rs.getLong("key2");
					Long value = rs.getLong("kvalue");
					queue.add(key1, key2, value);
				}
				rs.close();
			}
		} catch (SQLException ex) {

		} finally {
			close(connection);
		}
		return queue;
	}
}
