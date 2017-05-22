package com.mall.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mall.dao.MessageDao;
import com.mall.entity.SmsMessage;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Long, SmsMessage> implements MessageDao {
	/**
	 * 数据源
	 */
	@Resource
	private ComboPooledDataSource dataSource;

	public Date lastSendDate(String mobile, String ip) {
		Date lastDate = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select ms.create_date from tb_message_send ms left join tb_message_item mi on ms.id = mi.messages"
					+ " where ms.ip=? and mi.numbers=? order by create_date desc limit 2";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ip);
			ps.setString(2, mobile);
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.next()) {
				lastDate = rs.getTimestamp("create_date");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(conn);
		}
		return lastDate;
	}

	public int daySendNumber(String mobile) {
		int number = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select count(*) as num from tb_message_send ms left join tb_message_item mi on ms.id = mi.messages"
					+ " where TIMESTAMPDIFF(hour, create_date, now()) < 24 and mi.numbers=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mobile);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				number = rs.getInt("num") - 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(conn);
		}

		return number;
	}

	public int dayIpNumber(String ip) {
		int number = 0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select count(*) as num from tb_message_send ms"
					+ " where TIMESTAMPDIFF(hour, create_date, now()) < 24 and ms.ip=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ip);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				number = rs.getInt("num") - 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SQLUtils.closeConnection(conn);
		}

		return number;
	}

}
