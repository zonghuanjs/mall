package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 地区
 * @author zonghuan
 *
 */
@Entity
@Table(name = "tb_area")
public class Area
{

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "orders")
	private int orders;//显示顺序
	
	@Column(name = "full_name")
	private String fullName;//全称
	
	@Column(name = "name")
	private String name;//名称
	
	@Column(name = "tree_path")
	private String treePath;//路径
	
	@ManyToOne(targetEntity=Area.class, fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private Area parent;//上级地区
	
	public Area()
	{
		
	}

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

	public int getOrders()
	{
		return orders;
	}

	public void setOrders(int orders)
	{
		this.orders = orders;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTreePath()
	{
		return treePath;
	}

	public void setTreePath(String treePath)
	{
		this.treePath = treePath;
	}

	public Area getParent()
	{
		return parent;
	}

	public void setParent(Area parent)
	{
		this.parent = parent;
	}
	
}
