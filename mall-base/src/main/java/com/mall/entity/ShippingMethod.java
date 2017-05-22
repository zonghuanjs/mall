package com.mall.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_shipping_method")
public class ShippingMethod
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	@Column(name="description")
	private String description;
		
	@Column(name="icon")
	private String icon;
	
	@ManyToOne(targetEntity = DeliveryCorp.class, fetch = FetchType.LAZY)
	@JoinColumn(name="default_delivery_corp")
	private DeliveryCorp defaultDeliveryCorp;

	@Column(name = "orders")
	private int orders;
	
	@OneToMany(targetEntity = FreightRule.class, mappedBy="method", fetch = FetchType.LAZY)	
	private List<FreightRule> rules;//运费规则列表
	
	public ShippingMethod()
	{
		this.rules = new LinkedList<>();
	}
	
	public int getOrders()
	{
		return orders;
	}

	public void setOrders(int orders)
	{
		this.orders = orders;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public DeliveryCorp getDefaultDeliveryCorp()
	{
		return defaultDeliveryCorp;
	}

	public void setDefaultDeliveryCorp(DeliveryCorp defaultDeliveryCorp)
	{
		this.defaultDeliveryCorp = defaultDeliveryCorp;
	}

	public List<FreightRule> getRules()
	{
		return rules;
	}

	public void setRules(List<FreightRule> rules)
	{
		this.rules = rules;
	}
	
}
