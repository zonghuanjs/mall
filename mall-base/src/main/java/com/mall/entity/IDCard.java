package com.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 身份证信息实体
 * @author ChenMingcai
 * 2015-06-23
 */

@Entity
@Table(name = "tb_id_card")
public class IDCard implements Serializable
{
	private static final long serialVersionUID = 2691789586107548489L;

	public static class Keys
	{
		public static final String netCardIdKey = "netcard_id_key";//上网卡身份证KEY
		public static final String mobileCardIdKey = "mobilecard_id_key";//手机卡身份证KEY
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;//实体标识
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name = "name")
	private String name;//机主姓名
	
	@Column(name = "numbers")
	private String numbers;//身份证号
	
	@Column(name = "front_img")
	private String frontImg;//身份证正面
	
	@Column(name = "back_img")
	private String backImg;//身份证背面
	
	@OneToOne(targetEntity = Order.class, mappedBy="ids")
	private Order orders;//关联订单

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNumbers()
	{
		return numbers;
	}

	public void setNumbers(String numbers)
	{
		this.numbers = numbers;
	}

	public String getFrontImg()
	{
		return frontImg;
	}

	public void setFrontImg(String frontImg)
	{
		this.frontImg = frontImg;
	}

	public String getBackImg()
	{
		return backImg;
	}

	public void setBackImg(String backImg)
	{
		this.backImg = backImg;
	}

	public Order getOrders()
	{
		return orders;
	}

	public void setOrders(Order orders)
	{
		this.orders = orders;
	}
}
