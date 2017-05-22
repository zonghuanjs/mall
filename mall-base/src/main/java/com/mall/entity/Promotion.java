package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Lijun
 * 
 *  促销
 *
 */
@Entity
@Table(name = "tb_promotion")
public class Promotion extends BaseEntity
{
	private static final long serialVersionUID = -4629332184277833432L;

	/**
	 * 属性Keys
	 * @author ChenMingcai
	 *
	 */
	public static final class Keys
	{
		public static final String memberMobile = "member_mobile";//验证会员手机
		public static final String memberMail = "member_mail";//验证会员邮箱
		public static final String memberBirth = "member_birth";//填写生日
		public static final String isEnabled = "isEnabled"; //是否在线上启用此促销活动 
		public static final String snatchEnabled = "snatchEnabled"; //此促销活动是否设置为抢购性质
		public static final String snatchDatePoint = "snatchDatePoint"; //抢购时间点 24小时制   整数
		public static final String numberKey = "pro_num_";//数量限制Key
		public static final String xinCodeKey = "xincode_";//芯码资格(特权)
	}
	
	/**
	 * 操作符号
	 * @author ChenMingcai
	 *
	 */
	public static final class Operation
	{
		public static final int add = 1;//加法
		public static final int substract = -1;//减法
		public static final int multi = 2;//乘法，即折扣
	}
	
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
	 * 排序
	 */
	@Column(name = "orders")
	private int orders; 
	
	/**
	 * 活动开始时间
	 */
	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate; 
	
	/**
	 * 活动截止时间
	 */
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate; 
	
	/**
	 * 促销最高价格限制：即活动要有效不能超过此价格, 如果此值为0, 则表示不限制
	 */
	@Column(name = "end_price")
	private double endPrice; 
	
	/**
	 * 活动详情描述
	 */
	@Column(name = "description")
	private String description; 
	
	/**
	 * 参与此促销活动允许使用优惠券
	 */
	@Column(name = "coupon_allowed")
	private boolean couponAllowed; 
	
	/**
	 * 活动是否包邮
	 */
	@Column(name = "free_shipping")
	private boolean freeShipping; 
	
	/**
	 * 促销活动名称
	 */
	@Column(name = "name")
	private String name; 
	
	/**
	 * 促销活动页面标题
	 */
	@Column(name = "title")
	private String title; 
	
	/**
	 * 积分操作: 1, 增加; -1, 减少
	 */
	@Column(name = "point_operator")
	private int pointOperator; 
	
	/**
	 * 积分操作值
	 */
	@Column(name = "point_value")
	private Long pointValue; 
	
	/**
	 * 价格操作: 1, 增加; -1, 减少
	 */
	@Column(name = "price_operator")
	private int priceOperator; 
	
	/**
	 * 价格操作值
	 */
	@Column(name = "price_value")
	private double priceValue; 
	
	/**
	 * 参与该活动最少需要购买的商品数量：0表示不限制
	 */
	@Column(name = "min_num")
	private int minNum; 
	
	/**
	 * 参与该活动最多能购买的商品数量限制：0表示不限制
	 */
	@Column(name = "max_num")
	private int maxNum; 
	
	/**
	 * 起步价格：要参与该活动的商品最低价格限制, 0表示不限制
	 */
	@Column(name = "start_price")
	private double startPrice; 
	
	/**
	 * 会员资料限制：是否对会员所填写资料的完整性有限制
	 */
	@Column(name = "member_info")
	private boolean memberInfo;
	
	/**
	 * 商品分类列表：能够参与该活动的商品分类
	 */
	@ManyToMany(targetEntity=ProductCategory.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_promotion_product_category", joinColumns=@JoinColumn(name="promotion"), inverseJoinColumns=@JoinColumn(name="category"))
	private Set<ProductCategory> productCategorys;
	
	/**
	 * 商品列表：能够参与该活动的商品列表
	 */
	@ManyToMany(targetEntity=Product.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_promotion_product", joinColumns=@JoinColumn(name="promotion"), inverseJoinColumns=@JoinColumn(name="product"))
	private Set<Product> products;
	
	/**
	 * 会员等级列表：能够参与该活动的会员等级
	 */
	@ManyToMany(targetEntity=MemberRank.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_promotion_member_rank", joinColumns=@JoinColumn(name="promotion"), inverseJoinColumns=@JoinColumn(name="member_rank"))
	private Set<MemberRank> memberRanks;
	
	/**
	 * 优惠券列表：该活动可以使用的优惠券列表
	 */
	@ManyToMany(targetEntity=Coupon.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_promotion_coupon", joinColumns=@JoinColumn(name="promotion"), inverseJoinColumns=@JoinColumn(name="coupons"))
	private Set<Coupon> coupons;
	
	/**
	 * 其他相关设置
	 */
	@ElementCollection(targetClass=String.class)
	@CollectionTable(name="tb_promotion_attributes", joinColumns=@JoinColumn(name="promotions"))
	@MapKeyColumn(name="attribute_key", nullable = false)
	@Column(name="attribute")
	private Map<String, String> attributes;
	
	public Promotion()
	{
		this.productCategorys = new HashSet<>();
		this.products = new HashSet<>();
		this.memberRanks = new HashSet<>();
		this.coupons = new HashSet<>();
		this.attributes = new HashMap<>();
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

	public boolean isCouponAllowed()
	{
		return couponAllowed;
	}

	public void setCouponAllowed(boolean couponAllowed)
	{
		this.couponAllowed = couponAllowed;
	}

	public boolean isFreeShipping()
	{
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping)
	{
		this.freeShipping = freeShipping;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getPointOperator()
	{
		return pointOperator;
	}

	public void setPointOperator(int pointOperator)
	{
		this.pointOperator = pointOperator;
	}

	public Long getPointValue()
	{
		return pointValue;
	}

	public void setPointValue(Long pointValue)
	{
		this.pointValue = pointValue;
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

	public boolean isMemberInfo()
	{
		return memberInfo;
	}

	public void setMemberInfo(boolean memberInfo)
	{
		this.memberInfo = memberInfo;
	}

	public int getMinNum()
	{
		return minNum;
	}

	public void setMinNum(int minNum)
	{
		this.minNum = minNum;
	}

	public int getMaxNum()
	{
		return maxNum;
	}

	public void setMaxNum(int maxNum)
	{
		this.maxNum = maxNum;
	}

	public Set<ProductCategory> getProductCategorys()
	{
		return productCategorys;
	}

	public void setProductCategorys(Set<ProductCategory> productCategorys)
	{
		this.productCategorys = productCategorys;
	}

	public Set<Product> getProducts()
	{
		return products;
	}

	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}

	public Set<MemberRank> getMemberRanks()
	{
		return memberRanks;
	}

	public void setMemberRanks(Set<MemberRank> memberRanks)
	{
		this.memberRanks = memberRanks;
	}

	public Set<Coupon> getCoupons()
	{
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons)
	{
		this.coupons = coupons;
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}	
}
