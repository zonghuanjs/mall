package com.mall.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.mall.dao.BeanRetainDao;
import com.mall.entity.BeanRetain;
import com.mall.entity.Member;
import com.mall.pager.Pager;
import com.mall.util.CriteriaUtils;
import com.mall.util.SQLUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;



@Repository
public class BeanRetainDaoImpl extends BaseDaoImpl<Long, BeanRetain> implements BeanRetainDao
{
	@Resource
	private ComboPooledDataSource dataSource;
	

	@Override
	public int queryTotal(Member member)
	{
		int total = 0;
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			String sql = "select sum(opt_value - used_value) as total from tk_bean_retain where member=? and is_expired=false";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, member.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				total = rs.getInt("total");
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			SQLUtils.closeConnection(conn);
		}
		
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BeanRetain> findByPager(Pager<BeanRetain> pager,
			Map<String, Object> geFilter, Map<String, Object> leFilter)
	{
		if(pager == null)
		{
			return null;
		}
		else
		{
			Session session = null;
			List<BeanRetain> list = new LinkedList<>();
			try
			{
				session = this.currentSession();
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BeanRetain.class);
				
				Criteria query = detachedCriteria.getExecutableCriteria(session);
																
				//集合筛选
				if(pager.getInset() != null)
				{
					CriteriaUtils.setInCondition(query, pager.getInset());
				}
				if(pager.getFilter() != null)
				{
					CriteriaUtils.setEQCondition(query, pager.getFilter());
				}
				//模糊查询
				if(pager.getLikes() != null && !pager.getLikes().isEmpty())
				{
					CriteriaUtils.setLikeCondition(query, pager.getLikes());
				}
				if(geFilter != null)
				{
					CriteriaUtils.setGECondition(query, geFilter);
				}
				if(leFilter != null)
				{
					CriteriaUtils.setLECondition(query, leFilter);
				}
				
				//查询数量
				query.setProjection(Projections.rowCount());
				pager.setTotalCount(((Number)query.uniqueResult()).longValue());
				
				//排序条件
				if(pager.getOrderby() != null && pager.getOrderby().length() > 0)
				{
					CriteriaUtils.setOrderCondition(query, pager.getOrderby());
				}
				
				query.setProjection(null);
				query.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
						
				if(pager.getFirst() > 0)
					query.setFirstResult(pager.getFirst());
				if(pager.getPageSize() > 0)
					query.setMaxResults(pager.getPageSize());
				list.addAll(query.list());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return list;
		}
	}


	@Override
	public void beanExpiredTaskService(int count) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		ResultSet rs = null;
		ResultSet rs2 = null;
		long[] retainids = new long[count];
		long usedid = 1;
		long[] memberids = new long[count];
		int[] values = new int[count];
		int i = 0;
		
		try
		{

			conn = dataSource.getConnection();
			if(conn != null){
				
				//筛选过期的记录 
				sql = "select id,member,(opt_value-used_value) as opt_value from tk_bean_retain "
						+ "where expired_time < now() and is_expired = 0 and opt_value > used_value limit ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, count);
				
				rs = ps.executeQuery();
				while(rs.next()){
					
					retainids[i] = rs.getLong("id");
					memberids[i] = rs.getLong("member");
					values[i] = rs.getInt("opt_value");
					
					i++;
				}
				
				if(rs != null){
					rs.close();
				}
				
				if(retainids!=null && retainids.length >0){
					
					for(int j=0;j<i;j++){
						
						//更新芯豆获取的table（tk_bean_retain）
						sql = "update tk_bean_retain set is_expired=1, memo=concat(memo,'_芯豆过期不可用') where id= " + retainids[j];  
						ps.executeUpdate(sql);
					
						//插入使用记录table（tk_bean_used）一条记录
						sql = "insert into tk_bean_used (create_date,member,opt_value,type,memo) values (now()," + memberids[j]
								+ "," + values[j] + ",2,'芯豆过期清理')";
						ps.execute(sql, PreparedStatement.RETURN_GENERATED_KEYS);
						rs2 = ps.getGeneratedKeys();
						
						if(rs2.next()){
							usedid = rs2.getLong(1);
							
							//更新关联表的table（tk_bean_used_origin）
							sql = "insert into tk_bean_used_origin values ("+usedid+","+retainids[j]+","+values[j]+")";
							ps.execute(sql);
						}
						
						if(rs2 != null){
							rs2.close();
						}
					}
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			
			SQLUtils.closeConnection(conn);
			
			try {
				
				if(ps != null){
					ps.close();
				}
				
				if(rs != null){
					rs.close();
				}
				
				if(rs2 != null){
					rs2.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean consumeBeansAccordingtoMember(long memberid, long orderid, int beans) {
		
		if(memberid<=0 || orderid<=0 || beans<=0){
			return false;
		}
		
		boolean successful = true;
		Connection conn = null;
		CallableStatement callStatement = null;
		try
		{
			conn = dataSource.getConnection();
			if(conn != null){
				
				callStatement = conn.prepareCall("{call use_bean_service(?,?,?)}");
				callStatement.setLong(1, memberid);
				callStatement.setLong(2, orderid);
				callStatement.setInt(3, beans);
				callStatement.execute();
			}
		}
		catch(SQLException ex)
		{
			successful = false;
			ex.printStackTrace();
		}
		finally
		{
			SQLUtils.closeConnection(conn);
			if(callStatement != null){
				try {
					callStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return successful;
	}

	@Override
	public int queryExpiredTotal(Member member, Date date)
	{
		int total = 0;
		if(member != null && date != null)
		{
			Connection conn = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try
			{
				conn = dataSource.getConnection();
				String sql = "select sum(opt_value - used_value) as total from tk_bean_retain where member=? and is_expired=false and expired_time<?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, member.getId());
				ps.setString(2, format.format(date));
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					total = rs.getInt("total");
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				SQLUtils.closeConnection(conn);
			}
		}		
		return total;
	}

}
