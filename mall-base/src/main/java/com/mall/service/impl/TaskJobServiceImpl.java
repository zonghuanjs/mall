package com.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.entity.TaskJob;
import com.mall.service.TaskJobService;
import com.mall.util.TaskSchedulerUtils;

@Service
public class TaskJobServiceImpl extends BaseServiceImpl<Long, TaskJob> implements TaskJobService {
	@Autowired
	private Scheduler scheduler;

	@Override
	public boolean add(TaskJob model) {
		if (model != null) {
			model.setCreateDate(new Date());
			model.setModifyDate(new Date());
		}
		boolean ret = super.add(model);

		if (ret) {

		}
		return ret;
	}

	@Override
	public boolean update(TaskJob model) {
		if (model != null) {
			model.setModifyDate(new Date());
		}

		boolean ret = super.update(model);
		if (ret) {
			if (model.getStatus() == TaskJob.Status.enabled) {
				this.rescheduleTask(model);
			} else {
				TaskSchedulerUtils.deleteScheduleTask(scheduler, model);
			}
		}
		return ret;
	}

	@Override
	public boolean delete(Long[] ids) {
		List<TaskJob> list = this.get(ids);
		for (TaskJob job : list) {
			TaskSchedulerUtils.deleteScheduleTask(scheduler, job);
		}
		return super.delete(ids);
	}

	@Override
	public void createScheduleTask(TaskJob job) {
		if (job == null) {
			return;
		}

		TaskSchedulerUtils.createScheduleTask(scheduler, job, null);
	}

	@Override
	public void doTask(TaskJob job) {
		if (job == null) {
			return;
		}

		TaskSchedulerUtils.doTask(scheduler, job);
	}

	@Override
	public void rescheduleTask(TaskJob job) {
		if (job == null) {
			return;
		}

		TaskSchedulerUtils.reScheduleTask(scheduler, job, null);
	}
}
