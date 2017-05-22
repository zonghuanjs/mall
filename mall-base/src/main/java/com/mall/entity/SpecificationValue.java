package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_specification_value")
public class SpecificationValue
{

	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;//规格编号
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
			
	@Column(name="orders")
	private int orders;// 显示顺序
	
	@Column(name="image")
	private String image;// 图片
	
	@Column(name="name")
	private String name;// 规格值名
	
	@ManyToOne(targetEntity=Specification.class, fetch=FetchType.LAZY)
	@JoinColumn(name="specification")
	private Specification specification;// 所属规格
	
	@ManyToMany(targetEntity=Product.class, mappedBy="specificationValues", fetch=FetchType.LAZY)
	private Set<Product> products;//具有规格值的商品列表
	
	public SpecificationValue()
	{
		this.products = new HashSet<>();
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

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Specification getSpecification()
	{
		return specification;
	}

	public void setSpecification(Specification specification)
	{
		this.specification = specification;
	}

	public Set<Product> getProducts()
	{
		return products;
	}

	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}

	
	
}
