package com.mall.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.RedPacketsDao;
import com.mall.entity.Member;
import com.mall.util.SQLUtils;
import com.mall.util.TimeUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class RedPacketsDaoImpl implements RedPacketsDao {

	@Resource
	private ComboPooledDataSource dataSource;// 数据源

	@Override
	public int memberDayNumber(Member member, Date date) {
		// TODO Auto-generated method stub
		if (member == null) {
			return 0;
		}

		Connection connection = null;
		int count = 0;

		StringBuffer sql = new StringBuffer("select sum(attributes) as number from tb_member_attributes ");
		sql.append("where members=? and attributes_key = ?");

		StringBuffer keyBuilder = new StringBuffer("RED_Packets_");
		keyBuilder.append(TimeUtils.formatDate("yyyyMMdd", date));

		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, member.getId());
			ps.setString(2, keyBuilder.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count += rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(connection);
		}

		return count;
	}

}
