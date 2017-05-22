package com.mall.service;

import com.mall.entity.Apply;
import com.mall.entity.ApplyLog;

public interface ApplyLogService extends BaseService<Long, ApplyLog> {
	
	/**
	 * 记录日志 
	 * @param apply 售后申请单
	 * @param content 日志内容
	 * @param operator 操作员
	 * @param ip 操作IP
	 */
	void log(Apply apply, String content, String operator, String ip);
}
