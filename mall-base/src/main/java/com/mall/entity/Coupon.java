package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Lijun
 * 优惠券
 *
 */
@Entity
@Table(name = "tb_coupon")
public class Coupon
{
	/**
	 * 优惠券id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; 
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; 
	
	/**
	 * 修改时间
	 */
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; 
	
	/**
	 * 开始时间
	 */
	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate; 
	
	/**
	 * 截止时间
	 */
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	/**
	 * 最高价格
	 */
	@Column(name = "end_price")
	private double endPrice; 
	
	/**
	 * 优惠描述
	 */
	@Column(name = "description")
	private String description; 
	
	/**
	 * 是否有效
	 */
	@Column(name = "enabled")
	private boolean enabled; 
	
	/**
	 * 能否交换
	 */
	@Column(name = "exchange")
	private boolean exchange; 
	
	/**
	 * 名称
	 */
	@Column(name = "name")
	private String name; 
	
	/**
	 * 积分限制
	 */
	@Column(name = "point")
	private int point; 
	
	/**
	 * 前缀
	 */
	@Column(name = "prefix")
	private String prefix; 
	
	/**
	 * 价格操作     减价:-1、 	折扣（打折）:2、	 加价:1
	 */
	@Column(name = "price_operator")
	private int priceOperator; 
	
	/**
	 * 操作值    折扣（打折）为小数输入  例如：8折输入0.8
	 */
	@Column(name = "price_value")
	private double priceValue; 
	
	/**
	 * 生效价格
	 */
	@Column(name = "start_price")
	private double startPrice; 
	
	/**
	 * 优惠券总数量限制
	 */
	@Column(name= "number")
	private Long number;
	
	/**
	 * 每天数量限制
	 */
	@Column(name="day_number")
	private Long dayNumber;
	
	/**
	 * 有效天数
	 */
	@Column(name="days")
	private int days;
	
	@OneToMany(targetEntity = CouponCode.class, mappedBy = "coupon")
	private Set<CouponCode> codes;//发放优惠券集合
	
	@ManyToMany(targetEntity = Promotion.class, mappedBy = "coupons")
	@OrderBy("endDate asc")
	private List<Promotion> promotions;//优惠活动列表
	
	@ManyToMany(targetEntity = Product.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_coupon_product", joinColumns=@JoinColumn(name="coupons"), inverseJoinColumns=@JoinColumn(name="products"))
	private Set<Product> products;//关联商品集合  限制指定的商品享有此优惠；不指定则不限制商品
	
	public Coupon()
	{
		this.codes = new HashSet<>();
		this.promotions = new LinkedList<>();
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

	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public double getEndPrice()
	{
		return endPrice;
	}

	public void setEndPrice(double endPrice)
	{
		this.endPrice = endPrice;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public boolean isExchange()
	{
		return exchange;
	}

	public void setExchange(boolean exchange)
	{
		this.exchange = exchange;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public int getPriceOperator()
	{
		return priceOperator;
	}

	public void setPriceOperator(int priceOperator)
	{
		this.priceOperator = priceOperator;
	}

	public double getPriceValue()
	{
		return priceValue;
	}

	public void setPriceValue(double priceValue)
	{
		this.priceValue = priceValue;
	}

	public double getStartPrice()
	{
		return startPrice;
	}

	public void setStartPrice(double startPrice)
	{
		this.startPrice = startPrice;
	}

	public Long getNumber()
	{
		return number;
	}

	public void setNumber(Long number)
	{
		this.number = number;
	}

	public Long getDayNumber()
	{
		return dayNumber;
	}

	public void setDayNumber(Long dayNumber)
	{
		this.dayNumber = dayNumber;
	}

	public int getDays()
	{
		return days;
	}

	public void setDays(int days)
	{
		this.days = days;
	}

	public Set<CouponCode> getCodes()
	{
		return codes;
	}

	public void setCodes(Set<CouponCode> codes)
	{
		this.codes = codes;
	}

	public List<Promotion> getPromotions()
	{
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions)
	{
		this.promotions = promotions;
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
