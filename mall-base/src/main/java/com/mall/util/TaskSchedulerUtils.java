package com.mall.util;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.mall.entity.TaskJob;

public final class TaskSchedulerUtils {
	public static final String jobDataKey = "data_key";// job数据key

	/**
	 * 创建调度任务
	 * 
	 * @param scheduler
	 *            任务调度器
	 * @param job
	 *            任务
	 * @param param
	 *            任务参数
	 */
	@SuppressWarnings("unchecked")
	public static void createScheduleTask(Scheduler scheduler, TaskJob job, Object param) {
		Class<? extends Job> jobClass = null;
		try {
			jobClass = (Class<? extends Job>) Class.forName(job.getJobClass());
		} catch (ClassNotFoundException ex) {
			return;
		}

		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getName(), job.getGroup()).build();
		CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroup())
				.withSchedule(cronBuilder).build();

		// 调度任务
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新调度任务
	 * 
	 * @param scheduler
	 * @param job
	 * @param param
	 */
	public static void reScheduleTask(Scheduler scheduler, TaskJob job, Object param) {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
		CronTrigger trigger = null;
		try {
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException ex) {
			return;
		}

		if (trigger != null) {
			CronScheduleBuilder cronBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronBuilder).build();
			try {
				scheduler.rescheduleJob(triggerKey, trigger);
			} catch (SchedulerException ex) {
				ex.printStackTrace();
			}
		} else {
			// 创建调度任务
			createScheduleTask(scheduler, job, param);
		}
	}

	/**
	 * 删除调度任务
	 * 
	 * @param scheduler
	 * @param job
	 */
	public static void deleteScheduleTask(Scheduler scheduler, TaskJob job) {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 立即执行调度任务
	 * 
	 * @param scheduler
	 * @param job
	 */
	public static void doTask(Scheduler scheduler, TaskJob job) {
		if (scheduler == null || job == null) {
			return;
		}

		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		if (jobKey != null) {
			try {
				scheduler.triggerJob(jobKey);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 在指定时间调度任务1次
	 * 
	 * @param scheduler
	 *            调度器
	 * @param taskClass
	 *            任务实现类
	 * @param runTime
	 *            调度时间
	 * @param param
	 *            调度数据
	 */
	public static void doTaskOnce(Scheduler scheduler, Class<? extends Job> taskClass, Date runTime, Object param) {
		Class<? extends Job> jobClass = taskClass;
		if (scheduler == null || taskClass == null) {
			return;
		}

		JobDetail jobDetail = JobBuilder.newJob(jobClass).build();
		jobDetail.getJobDataMap().put(jobDataKey, param);
		Trigger trigger = TriggerBuilder.newTrigger().startAt(runTime).forJob(jobDetail).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
	}
}
