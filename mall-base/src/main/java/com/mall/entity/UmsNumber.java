package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_number")
public class UmsNumber {
	/**
	 * 号码状态
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static class Status {
		public static final int AVAILABLE = 0;// 可用状态
		public static final int LOCKED = 1;// 锁定状态
		public static final int SELLED = 2;// 已卖出
	}

	/**
	 * 号码类型
	 * 
	 * @author ChenMingcai
	 *
	 */
	public static class Type {
		public static final int MOBILE_CARD = 1;// 手机卡
		public static final int NET_CARD = 2;// 上网卡
	}

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;// 号码标识符

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 创建时间

	@Column(name = "status")
	private int status;// 号码状态

	@Column(name = "sell_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sellDate;// 卖出日期

	@Column(name = "unlock_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date unlockDate;// 解锁时间

	@Column(name = "rules")
	private String rules;// 号码规则

	@Column(name = "ids")
	private String ids;

	@Column(name = "type")
	private int Type;// 号码类型： 1, 手机卡; 2, 上网卡

}
