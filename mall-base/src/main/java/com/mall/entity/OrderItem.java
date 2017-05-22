package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_order_item")
public class OrderItem
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id; //订单项id
	
	@ManyToOne(targetEntity=Order.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="orders")
	private Order orders; //所属订单
	
	@ManyToOne(targetEntity=Product.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="product")
	private Product product; //所属商品
	
	@ManyToOne(targetEntity=Lottery.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="lottery")
	private Lottery lottery;//关联抽奖
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name="full_name")
	private String fullName; //订单全称
	
	@Column(name="gift")
	private boolean gift; //是否赠品
	
	@Column(name="specification")
	private String specification;//规格值
	
	@Column(name="name")
	private String name; //产品名称
	
	@Column(name="price")
	private double price; //单品单价
	
	@Column(name="quantity")
	private int quantity; //产品数量
	 
	@Column(name="shipped_quantity")
	private int shippedQuantity; //已发货数量
	
	@Column(name="return_quantity")
	private int returnQuantity; //已退货数量
	
	@Column(name="sn")
	private String sn; //产品编号
	
	@Column(name="thumbnail")
	private String thumbnail; //缩略图
	
	@Column(name="weight")
	private double weight; //重量
	
	@OneToOne(targetEntity=Comment.class, mappedBy="orderItem", fetch=FetchType.LAZY)
	private Comment comment;//关联评论
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Order getOrders()
	{
		return orders;
	}
	public void setOrders(Order orders)
	{
		this.orders = orders;
	}
	
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	public Lottery getLottery()
	{
		return lottery;
	}
	public void setLottery(Lottery lottery)
	{
		this.lottery = lottery;
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
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public boolean isGift()
	{
		return gift;
	}
	public void setGift(boolean gift)
	{
		this.gift = gift;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public int getShippedQuantity()
	{
		return shippedQuantity;
	}
	public void setShippedQuantity(int shippedQuantity)
	{
		this.shippedQuantity = shippedQuantity;
	}
	public int getReturnQuantity()
	{
		return returnQuantity;
	}
	public void setReturnQuantity(int returnQuantity)
	{
		this.returnQuantity = returnQuantity;
	}
	public String getSn()
	{
		return sn;
	}
	public void setSn(String sn)
	{
		this.sn = sn;
	}
	public String getThumbnail()
	{
		return thumbnail;
	}
	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}
	public double getWeight()
	{
		return weight;
	}
	public void setWeight(double weight)
	{
		this.weight = weight;
	}
	public Comment getComment()
	{
		return comment;
	}
	public void setComment(Comment comment)
	{
		this.comment = comment;
	}
	public String getSpecification()
	{
		return specification;
	}
	public void setSpecification(String specification)
	{
		this.specification = specification;
	}
	
}
