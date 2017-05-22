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
@Table(name="tb_member_rank")
public class MemberRank
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//等级编号
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="amount")
	private double amount;//消费金额
	
	@Column(name="is_default")
	private boolean isDefault;//是否默认
	
	@Column(name="is_special")
	private boolean special;//是否特殊
	
	@Column(name="scale")
	private double scale;
	
	@Column(name="name")
	private String name;//等级名称
	
	@Column(name="level")
	private int level;//会员等级
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	public Date getModifyDate()
	{
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public boolean getIsDefault()
	{
		return isDefault;
	}
	public void setIsDefault(boolean isDefault)
	{
		this.isDefault = isDefault;
	}
	public boolean getSpecial()
	{
		return special;
	}
	public void setSpecial(boolean special)
	{
		this.special = special;
	}
	public double getScale()
	{
		return scale;
	}
	public void setScale(double scale)
	{
		this.scale = scale;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
