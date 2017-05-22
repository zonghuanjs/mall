package com.mall.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.EggDao;
import com.mall.entity.Member;
import com.mall.util.SQLUtils;
import com.mall.util.TimeUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class EggDaoImpl implements EggDao {
	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public int memberDayNumber(Member member, String mode, Date date) {
		if (member == null || mode == null || "".equals(mode)) {
			return 0;
		}

		Connection connection = null;
		int count = 0;

		StringBuffer sql = new StringBuffer("select attributes as number from tb_member_attributes ");
		sql.append("where members=? and attributes_key like ?");

		StringBuffer keyBuilder = new StringBuffer("EGG_");
		keyBuilder.append(mode).append("_").append(TimeUtils.formatDate("yyyyMMdd", date));

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setString(1, member.getId() + "");
			ps.setString(2, keyBuilder.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return count;
	}

	@Override
	public int rewardDayNumber(Long reward, String mode, Date date) {
		Connection connection = null;
		int count = 0;

		String sql = "select count(*) as number from tb_order_attributes oa, tb_order o where oa.orders=o.id and o.order_status<> -1 and oa.attributes_key like ?";
		String key = "EGG_" + mode + "_" + reward + "_" + TimeUtils.formatDate("yyyyMMdd", date);
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return count;
	}
}
