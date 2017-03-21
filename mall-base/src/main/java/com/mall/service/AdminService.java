package com.mall.service;

import com.mall.entity.Admin;

/**
 * 
 * @author zonghuan
 *
 */

public interface AdminService extends BaseService<Long, Admin>
{
	public Long maxId();
}
