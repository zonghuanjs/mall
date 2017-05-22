package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_member")
public class Member
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//会员编号
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//注册时间
	
	@Column(name="head")
	private String head;//头像
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="address")
	private String address;//地址
	
	@Column(name="birth")
	@Temporal(TemporalType.DATE)
	private Date birth;//生日
	
	@Column(name="email")
	private String email;
	
	@Column(name="gender")
	private int gender;//性别, 1男, 0女
	
	@Column(name="enabled")
	private boolean enabled;//是否启用
	
	@Column(name="locked")
	private boolean locked;//是否锁定
	
	@Column(name="lock_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lockDate;//锁定时间
	
	@Column(name="login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;//上次登录时间
	
	@Column(name="login_failure_count")
	private int loginFailureCount;//登录失败次数
	
	@Column(name="login_ip")
	private String loginIP;//登录IP
	
	@Column(name="mobile")
	private String mobile;//手机号
	
	@Column(name="name")
	private String name;//姓名
	
	@Column(name="password")
	private String password;//登录密码
	
	@Column(name="phone")
	private String phone;//电话
	
	@Column(name="register_ip")
	private String registerIP;//注册IP
	
	@Column(name="username")
	private String username;//登录用户名
	
	@Column(name="zip_code")
	private String zipCode;//邮政编码
	
	@Column(name="location")
	private Long location;//用户来源,0注册,1QQ,2淘宝,3微信
	
	@Column(name="openid")
	private String openid;//第三方关联ID
	
	@Column(name="token")
	private String token;//令牌
	
	@Column(name="token_expired")
	private Date tokenExpired; //令牌有效期
	
	@Column(name="refresh_token")
	private String refreshToken;//更新令牌的凭证令牌
	
	@Column(name="third_head")
	private String thirdHead; //QQ或者淘宝等等头像url
	
	@ManyToOne(targetEntity=Area.class, fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area area;
	
	@ManyToOne(targetEntity=MemberRank.class, fetch=FetchType.LAZY)
	@JoinColumn(name="rank")
	private MemberRank memberRank;//会员等级
	
	@Column(name="point", updatable = false)
	private Long point;//会员积分
	
	@Column(name = "amount", updatable = false)
	private double amount;//累积消费金额, 即成长值
	
	@Column(name="balance")
	private double balance;//账户余额
	
	@Column(name="tmp_key")
	private String tmpKey;//KEY
	
	@Column(name="key_expired")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expired;//过期时间
	
	@ManyToMany(targetEntity=Product.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_member_favorite_product", joinColumns=@JoinColumn(name="members"), inverseJoinColumns=@JoinColumn(name="products"))
	private List<Product> favoriteProducts;//收藏产品列表
	
	@OneToOne(targetEntity=Cart.class, mappedBy="member", fetch=FetchType.LAZY)
	private Cart cart;//关联购物车
	
	@OneToMany(targetEntity=Address.class, mappedBy="member", fetch=FetchType.LAZY)
	private List<Address> addresses; //获取地址列表
	
	@OneToMany(targetEntity=Comment.class, mappedBy="member", fetch=FetchType.LAZY)
	private List<Comment> comments; //获取评论
	
	@OneToMany(targetEntity=Order.class, mappedBy="member", fetch=FetchType.LAZY)
	private List<Order> orders; //获取订单
	
	@OneToOne(targetEntity=AddTax.class, mappedBy="member", fetch=FetchType.LAZY)
	private AddTax addTax;//对应的增值税信息
	
	@ElementCollection(targetClass=String.class)
	@JoinTable(name="tb_member_attributes", joinColumns=@JoinColumn(name="members"))
	private Map<String, String> attributes;//会员属性
	
	@OneToMany(targetEntity=Apply.class, mappedBy="member", fetch=FetchType.LAZY)
	private List<Apply> applys; //关联售后申请单列表
	
	@OneToMany(targetEntity=CouponCode.class, mappedBy="member", fetch=FetchType.LAZY)
	@OrderBy("createDate desc")
	private List<CouponCode> codes;//领取的优惠券列表

	@ManyToMany(targetEntity=SalesActivity.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_member_activities", joinColumns=@JoinColumn(name="member"), inverseJoinColumns=@JoinColumn(name="activity"))
	private List<SalesActivity> activities; //关注的商城的一些促销打折抽奖等等活动
	
	@ManyToMany(targetEntity=Brand.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_member_brands", joinColumns=@JoinColumn(name="member"), inverseJoinColumns=@JoinColumn(name="brand"))
	private List<Brand> brands; //关注的一些品牌
	
	public Member()
	{
		this.favoriteProducts = new LinkedList<>();
		this.addresses = new LinkedList<>();
		this.comments = new LinkedList<>();
		this.orders = new LinkedList<>();
		this.attributes = new HashMap<>();
		this.applys = new LinkedList<>();
		this.codes = new LinkedList<>();
		this.activities = new ArrayList<SalesActivity>();
		this.brands = new ArrayList<Brand>();
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
	

	public String getHead()
	{
		return head;
	}

	public void setHead(String head)
	{
		this.head = head;
	}
	
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Date getBirth()
	{
		return birth;
	}
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getGender()
	{
		return gender;
	}
	public void setGender(int gender)
	{
		this.gender = gender;
	}
	public boolean getEnabled()
	{
		return enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public boolean getLocked()
	{
		return locked;
	}
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
	public Date getLockDate()
	{
		return lockDate;
	}
	public void setLockDate(Date lockDate)
	{
		this.lockDate = lockDate;
	}
	public Date getLoginDate()
	{
		return loginDate;
	}
	public void setLoginDate(Date loginDate)
	{
		this.loginDate = loginDate;
	}
	public int getLoginFailureCount()
	{
		return loginFailureCount;
	}
	public void setLoginFailureCount(int loginFailureCount)
	{
		this.loginFailureCount = loginFailureCount;
	}
	public String getLoginIP()
	{
		return loginIP;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getRegisterIP()
	{
		return registerIP;
	}
	public void setRegisterIP(String registerIP)
	{
		this.registerIP = registerIP;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getZipCode()
	{
		return zipCode;
	}
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	public Long getLocation()
	{
		return location;
	}
	public void setLocation(Long location)
	{
		this.location = location;
	}
	public String getOpenid()
	{
		return openid;
	}
	public void setOpenid(String openid)
	{
		this.openid = openid;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public void setLoginIP(String loginIP)
	{
		this.loginIP = loginIP;
	}
	public Long getPoint()
	{
		return point;
	}
	public void setPoint(Long point)
	{
		this.point = point;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double banlance)
	{
		this.balance = banlance;
	}
	public MemberRank getMemberRank()
	{
		return memberRank;
	}
	public void setMemberRank(MemberRank memberRank)
	{
		this.memberRank = memberRank;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public List<Product> getFavoriteProducts()
	{
		return favoriteProducts;
	}
	public void setFavoriteProducts(List<Product> favoriteProducts)
	{
		this.favoriteProducts = favoriteProducts;
	}

	public Cart getCart()
	{
		return cart;
	}

	public void setCart(Cart cart)
	{
		this.cart = cart;
	}

	public List<Address> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(List<Address> addresses)
	{
		this.addresses = addresses;
	}
	
	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}
	
	public List<Order> getOrders()
	{
		return orders;
	}

	public void setOrders(List<Order> orders)
	{
		this.orders = orders;
	}

	/**
	 * 获取默认地址
	 * @return  默认收货地址
	 */
	public Address defaultAddress()
	{
		Address defaultAddress = null;
		for(Address address : this.addresses)
		{
			if(address.isDafault())
			{
				defaultAddress = address;
				break;
			}
		}
		//如果没有,取第一条
		if(defaultAddress == null && this.addresses.size() > 0)
		{
			defaultAddress = this.addresses.iterator().next();
		}
		return defaultAddress;
	}

	public AddTax getAddTax()
	{
		return addTax;
	}

	public void setAddTax(AddTax addTax)
	{
		this.addTax = addTax;
	}

	public String getTmpKey()
	{
		return tmpKey;
	}

	public void setTmpKey(String tmpKey)
	{
		this.tmpKey = tmpKey;
	}

	public Date getExpired()
	{
		return expired;
	}

	public void setExpired(Date expired)
	{
		this.expired = expired;
	}

	public Date getTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(Date tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getThirdHead() {
		return thirdHead;
	}

	public void setThirdHead(String thirdHead) {
		this.thirdHead = thirdHead;
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}
	
	public List<Apply> getApplys()
	{
		return applys;
	}

	public void setApplys(List<Apply> applys)
	{
		this.applys = applys;
	}

	public List<CouponCode> getCodes()
	{
		return codes;
	}

	public void setCodes(List<CouponCode> codes)
	{
		this.codes = codes;
	}
	
	public List<SalesActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<SalesActivity> activities) {
		this.activities = activities;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	/**
	 * 邮件是否验证
	 * @return
	 */
	public boolean getMailValidated()
	{
		String validate = attributes.get(Keys.mailValidated);
		return email != null && !"".equals(email) && "true".equals(validate);
	}
	
	/**
	 * 手机是否验证
	 * @return
	 */
	public boolean getMobileValidated()
	{
		String validate = attributes.get(Keys.mobileValidated);
		return mobile != null && !"".equals(mobile) && "true".equals(validate);
	}
	
	public String getMailValidateToken()
	{
		String token = "";
		if(this.attributes.get(Keys.mailValidateToken) != null)
			token = this.attributes.get(Keys.mailValidateToken);
		return token;
	}
	
	/**
	 * 会员账户安全等级
	 * @return
	 */
	public int getSecureScore()
	{
		int score = 2;
		if(getMailValidated())
		{
			//邮箱是否验证
			score += 1;
		}
		if(getMobileValidated())
		{
			//手机是否验证
			score += 2;
		}
		return score;
	}

	/**
	 * 会员属性KEY
	 * @author ChenMingcai
	 *
	 */
	public static final class Keys
	{
		public static final String smsCode = "smsCode";
		public static final String smsSeq = "smsSeq";
		public static final String mobileValidated = "mobileValidated";//手机绑定KEY
		public static final String mailValidated = "mail_validated";//邮件验证KEY
		public static final String mailValidateToken = "mail_validate_token";//邮件验证TOKEN
		public static final String registerCoupon = "register_coupon";
	}

	
}
