package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect("(select create_date,member,opt_value,memo,1 as type from tk_bean_retain)" + "union all"
		+ "(select create_date,member,opt_value,memo,2 from tk_bean_used)" + "order by create_date desc")
@Synchronize({ "tk_bean_retain", "tk_bean_used" })
public class BeanView {
	/**
	 * 日期
	 */
	@Id
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	/**
	 * 关联会员
	 */
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name = "member")
	private Member member;
	/**
	 * 收入支出数量
	 */
	@Column(name = "opt_value")
	private Integer optValue;
	/**
	 * 说明
	 */
	@JoinColumn(name = "memo")
	private String memo;
	/**
	 * 类型1：收入 ,2：支出
	 */
	@Column(name = "type")
	private Integer type;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getOptValue() {
		return optValue;
	}

	public void setOptValue(Integer optValue) {
		this.optValue = optValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BeanView() {

	}

}
