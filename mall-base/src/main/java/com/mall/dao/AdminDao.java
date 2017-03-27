package com.mall.dao;

import com.mall.entity.Admin;

/**
 * 
 * @author zonghuan
 *
 */

public interface AdminDao extends BaseDao<Long, Admin>
{
	public Long maxId();
}
