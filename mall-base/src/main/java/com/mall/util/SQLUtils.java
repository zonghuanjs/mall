package com.mall.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * SQL工具集
 * @author ChenMingcai
 * 2015-10-15
 *
 */

public class SQLUtils
{
	/**
	 * 关闭数据库连接
	 * @param connection		数据库连接对象
	 */
	public static void closeConnection(Connection connection)
	{
		try
		{
			if(connection != null)
			{
				connection.close();
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
