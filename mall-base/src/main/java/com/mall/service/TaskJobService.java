package com.mall.service;

import com.mall.entity.TaskJob;

public interface TaskJobService extends BaseService<Long, TaskJob> {
	/**
	 * 创建调度任务
	 * 
	 * @param job
	 *            调度任务信息
	 */
	void createScheduleTask(TaskJob job);

	/**
	 * 重新调度任务
	 * 
	 * @param job
	 */
	void rescheduleTask(TaskJob job);

	/**
	 * 立即执行调度任务
	 * 
	 * @param job
	 *            调度任务信息
	 */
	void doTask(TaskJob job);
}
