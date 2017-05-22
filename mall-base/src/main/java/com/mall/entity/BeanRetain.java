package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 芯豆获取记录实体
 * @author ChenMingcai
 * 2016-05-05
 *
 */

@Entity
@Table(name="tk_bean_retain")
public class BeanRetain extends BaseEntity
{
	private static final long serialVersionUID = 1145437602788674617L;

	/**
	 * 芯豆领取时间
	 */
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 关联会员
	 */
	@ManyToOne(targetEntity=Member.class)
	@JoinColumn(name = "member")
	private Member member;
	
	/**
	 * 领取类型: 1：购物回馈; 2：评价奖励; 3: 晒单奖励
	 */
	@Column(name = "type")
	private Integer type = 1;
	
	/**
	 * 领取数量
	 */
	@Column(name = "opt_value")
	private Integer optValue = 0;
	
	/**
	 * 是否已被使用
	 */
	@Column(name = "used")
	private Boolean used = false;
	
	/**
	 * 用完时间
	 */
	@Column(name = "used_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usedTime;
	
	/**
	 * 是否已经失效
	 */
	@Column(name = "is_expired")
	private Boolean isExpired = false;
	
	/**
	 * 自然失效时间: 如未使用的过期时间
	 */
	@Column(name = "expired_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredTime;
	
	/**
	 * 已使用数量
	 */
	@Column(name = "used_value")
	private Integer usedValue = 0;
	
	/**
	 * 备注信息
	 */
	@Column(name = "memo")
	private String memo;

	
	public BeanRetain(){
		this.createDate = new Date();
		this.used = false;
		this.usedTime = null;
		this.isExpired = false;
		this.usedValue = 0;
	}
	
	
	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getOptValue()
	{
		return optValue;
	}

	public void setOptValue(Integer optValue)
	{
		this.optValue = optValue;
	}

	public Boolean getUsed()
	{
		return used;
	}

	public void setUsed(Boolean used)
	{
		this.used = used;
	}

	public Date getUsedTime()
	{
		return usedTime;
	}

	public void setUsedTime(Date usedTime)
	{
		this.usedTime = usedTime;
	}

	public Boolean getIsExpired()
	{
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired)
	{
		this.isExpired = isExpired;
	}

	public Date getExpiredTime()
	{
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime)
	{
		this.expiredTime = expiredTime;
	}

	public Integer getUsedValue()
	{
		return usedValue;
	}

	public void setUsedValue(Integer usedValue)
	{
		this.usedValue = usedValue;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}
	
	
	/**
	 * 显示领取类型
	 * @return
	 */
	public String getTypeView()
	{
		String view = "";
		switch(this.type)
		{
			case 1:
				view = "购物回馈";
				break;
			case 2:
				view = "评价奖励";
				break;
			case 3:
				view = "晒单奖励";
				break;
		}
		return view;
	}
}
