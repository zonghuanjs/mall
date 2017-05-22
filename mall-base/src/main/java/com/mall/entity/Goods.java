package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商品组实体：同系列的商品绑定在一起成一个组
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_goods")
public class Goods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	/**
	 * 商品集合
	 */
	@OneToMany(targetEntity=Product.class, mappedBy="goods", fetch=FetchType.LAZY)
	@OrderBy("createDate")
	private Set<Product> products;
	
	/**
	 * 资源列表
	 */
	@OneToMany(targetEntity=GoodsResources.class, mappedBy="goods", fetch=FetchType.LAZY)
	private List<GoodsResources> resources;
	
	public Goods()
	{
		this.products = new HashSet<>();
		this.resources = new LinkedList<>();
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

	public Set<Product> getProducts()
	{
		return products;
	}

	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}

	public List<GoodsResources> getResources()
	{
		return resources;
	}

	public void setResources(List<GoodsResources> resources)
	{
		this.resources = resources;
	}
}
