package com.mall.dao;

import com.mall.entity.Admin;

/**
 * 
 * @author chenmingcai
 * 2014-09-27
 *
 */

public interface AdminDao extends BaseDao<Long, Admin>
{
	public Long maxId();
}
