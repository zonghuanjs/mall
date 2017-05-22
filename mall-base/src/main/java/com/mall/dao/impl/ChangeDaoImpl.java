package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mall.dao.ChangeDao;
import com.mall.entity.Member;
import com.mall.entity.Product;
import com.mall.service.ChangeType;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ChangeDaoImpl implements ChangeDao {
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
	public boolean changeStock(Product product, int quantity, ChangeType ot) {
		int ret = -1;
		Connection connection = null;
		try {

			connection = this.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call change_stock(?,?,?,?)}");
				call.setLong(1, product.getId());
				if (ot.equals(ChangeType.add)) {
					call.setInt(2, 2);
				} else {
					call.setInt(2, 1);
				}
				call.setInt(3, quantity);
				call.registerOutParameter(4, Types.INTEGER);
				call.execute();
				ret = call.getInt(4);
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

		return ret >= 0;
	}

	@Override
	public boolean changeMemberPoint(Member member, int quantity, ChangeType ct) {
		int ret = -1;
		Connection connection = null;
		try {

			connection = this.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call change_member_point(?,?,?,?)}");
				call.setLong(1, member.getId());

				// 1为减少, 2为增加
				if (ct.equals(ChangeType.add)) {
					call.setInt(2, 2);
				} else {
					call.setInt(2, 1);
				}
				call.setInt(3, quantity);
				call.registerOutParameter(4, Types.BIGINT);
				call.execute();
				ret = call.getInt(4);
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

		return ret >= 0;
	}

	@Override
	public boolean changeMemberAmount(Member member, double quantity, ChangeType ct) {
		double ret = -1;
		Connection connection = null;
		try {

			connection = this.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call change_member_amount(?,?,?,?)}");
				call.setLong(1, member.getId());

				// 1为减少, 2为增加
				if (ct.equals(ChangeType.add)) {
					call.setInt(2, 2);
				} else {
					call.setInt(2, 1);
				}
				call.setDouble(3, quantity);
				call.registerOutParameter(4, Types.DECIMAL);
				call.execute();
				ret = call.getDouble(4);
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

		return ret >= 0.0;
	}

	@Override
	public boolean changeSales(Product product, int quantity, ChangeType ct) {
		long ret = -1;
		Connection connection = null;
		try {

			connection = this.getConnection();
			if (connection != null) {
				CallableStatement call = connection.prepareCall("{call change_sales(?,?,?,?)}");
				call.setLong(1, product.getId());

				// 1为减少, 2为增加
				if (ct.equals(ChangeType.add)) {
					call.setInt(2, 2);
				} else {
					call.setInt(2, 1);
				}
				call.setInt(3, quantity);
				call.registerOutParameter(4, Types.BIGINT);
				call.execute();
				ret = call.getLong(4);
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

		return ret >= 0;
	}
}
