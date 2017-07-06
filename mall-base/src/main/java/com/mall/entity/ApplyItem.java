package com.mall.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mall.service.ProductService;
import com.mall.util.SpringUtil;

@Entity
@Table(name="tb_apply_item")
public class ApplyItem extends BaseEntity
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
	 * 申请商品名称
	 */
	@Column(name="name")
	private String name; 
	
	/**
	 * 申请数量
	 */
	@Column(name="quantity")
	private int quantity; 

	/**
	 * 申请商品SN
	 */
	@Column(name="sn")
	private String sn; 
	
	/**
	 * 关联售后申请单
	 */
	@ManyToOne(targetEntity=Apply.class, fetch=FetchType.LAZY)
	@JoinColumn(name="apply")
	private Apply apply;
	
	/**
	 * 购买时商品单价
	 */
	@Column(name="price")
	private double price; 
	
	/**
	 * 商品重量
	 */
	@Column(name="weight")
	private double weight; 
	
	public ApplyItem()
	{
		
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public Apply getApply()
	{
		return apply;
	}

	public void setApply(Apply apply)
	{
		this.apply = apply;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	/**
	 * 关联商品
	 * @return	如有关联商品返回商品对象; 否则返回 null
	 */
	public Product getProduct() 
	{
		Product product = null;
		
		if(this.sn != null)
		{
			
			ProductService productService = (ProductService)SpringUtil.getBean("productServiceImpl");
			if(productService != null){
				List<Product> list = productService.getListFromProperty("sn", sn);
				product = list.isEmpty() ? null : list.iterator().next();
			}
		}
		
		return product;
	}

	
	
}