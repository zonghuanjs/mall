package com.mall.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.OrderItemDao;
import com.mall.entity.OrderItem;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class OrderItemDaoImpl extends BaseDaoImpl<Long, OrderItem> implements OrderItemDao {
	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public long findNumber(String property, Object value) {
		Connection connection = null;
		long count = 0;

		try {
			connection = dataSource.getConnection();
			String sql = String.format("select count(*) as num from tb_order_item where %s like ?", property);
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + value + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getLong("num");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception ex) {

			}
		}

		return count;
	}
}
