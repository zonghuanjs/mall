package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.StringUtils;


@Entity
@Table(name="tb_product")
public class Product extends BaseEntity
{
	private static final long serialVersionUID = -2667610436971115835L;

	public static class Type
	{
		public static final int commonProduct = 1;//普通商品
		public static final int mobileCard = 2;//手机卡
		public static final int netCard = 3;//上网卡
		public static final int globalProduct = 5;//海淘商品
	}
		
	/**
	 * 创建时间
	 */
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间
	
	@Column(name="stock", updatable=false)
	private int stock;// 库存量
	
	/**
	 *  成本价格
	 */
	@Column(name="cost")
	private double cost;
	
	/**
	 * 商品分类
	 */
	@ManyToOne(targetEntity = ProductCategory.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="product_category")
	private ProductCategory productCategory;
	
	/**
	 * 商品名称
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 浏览量
	 */
	@Column(name="hits")
	private Long hits;
	
	/**
	 * 商品主图 
	 */
	@Column(name="image")
	private String image;
	
	/**
	 * 商品中图: 用于商品列表中显示
	 */
	@Column(name="medium_image")
	private String mediumImage;
	
	/**
	 * 商品描述: 用于商品详情显示
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * 是否赠品
	 */
	@Column(name="gift")
	private boolean gift;
	
	/**
	 * 是否上架: 只有上架的商品才能购买
	 */
	@Column(name="marketable")
	private boolean marketable;
	
	/**
	 * 是否置顶: 是否显示在商品列表的最前面
	 */
	@Column(name="top")
	private boolean top;
	
	/**
	 * 商品搜索关键词：用于商城中搜索商品时使用
	 */
	@Column(name="keyword")
	private String keyword;
	
	/**
	 * 市场价
	 */
	@Column(name="market_price")
	private double marketPrice;
	
	/**
	 * 月浏览量
	 */
	@Column(name="month_hits")
	private Long monthHits;
	
	/**
	 * 月销量峰值
	 */
	@Column(name="month_hits_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date monthHitsdate;
	
	/**
	 * 月销量
	 */
	@Column(name="month_sales")
	private Long monthSales;
	
	/**
	 * 月销量峰值
	 */
	@Column(name="month_sales_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date monthSalesdate;
	
	/**
	 * app专享价格：Android版，IOS版专享优惠价格
	 * 当其值小于0时，表示未设置手机专享价格
	 */
	@Column(name="app_price")
	private double appPrice = -1.0;
	
	/**
	 * 价格: 正常销售价格
	 */
	@Column(name="price")
	private double price;
	
	/**
	 *  总销量(未剔除交易取消的销量) 实际的有效的请用getEffectiveSales();
	 */
	@Column(name="sales")
	private Long sales;
	
	/**
	 * 商品评分
	 */
	@Column(name="score")
	private float score;// 
	
	/**
	 * 评分人数
	 */
	@Column(name="score_count")
	private Long scoreCount = 0L;
	
	/**
	 * SEO描述
	 */
	@Column(name="seo_description")
	private String seoDescription;
	
	/**
	 * SEO关键字
	 */
	@Column(name="seo_keywords")
	private String seoKeywords;
	
	/**
	 * SEO标题
	 */
	@Column(name="seo_title")
	private String seoTitle;
	
	@Column(name="sn", updatable = false)
	private String sn;// 商品编号
	
	@Column(name="unit")
	private String unit;// 单位
	
	@Column(name="week_hits")
	private Long weekHits;// 周浏览量
	
	@Column(name="week_hits_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date weekHitsdate;// 周浏览量峰值
	
	@Column(name="week_sales")
	private Long weekSales;// 周销量
	
	@Column(name="week_sales_date")
	private Date weekSalesdate;// 周销量峰值
	
	@Column(name="weight")
	private double weight;// 重量
	
	@ManyToOne(targetEntity=Goods.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="goods")
	private Goods goods;//
	
	@Column(name="list")
	private boolean list;// 是否列出
	
	@Column(name="point")
	private Long point;// 消费积分
	
	/**
	 * 备注: 此字段已用作ERP品号
	 */
	@Column(name="memo")
	private String memo;//
	
	@Column(name = "type")
	private int type;//商品类型
	
	/**
	 * 是否包邮
	 */
	@Column(name = "free_post")
	private boolean freePost;
	
	/**
	 * 所在仓库
	 */
	@Column(name="warehouse")
	private String warehouse;
	
	@ManyToOne(targetEntity=Brand.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="brand")
	private Brand brand;// 品牌
	
	@Column(name="thumbnail")
	private String thumbnail;// 缩略图	
	
	@OneToMany(targetEntity=ProductImage.class, mappedBy="products", fetch=FetchType.LAZY)
	@OrderBy("orders asc")
	private Set<ProductImage> images;//商品图片
	
	@ManyToMany(targetEntity=Tag.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_product_tag", joinColumns=@JoinColumn(name="products"), inverseJoinColumns=@JoinColumn(name="tags"))
	private Set<Tag> tags;//商品标签
	
	@ManyToMany(targetEntity=Specification.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_product_specification", joinColumns=@JoinColumn(name="products"), inverseJoinColumns=@JoinColumn(name="specifications"))
	@OrderBy("orders")
	private Set<Specification> specifications;//商品规格
	
	@ManyToMany(targetEntity=SpecificationValue.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_product_specification_value", joinColumns=@JoinColumn(name="products"), inverseJoinColumns=@JoinColumn(name="specification_values"))
	@OrderBy("orders")
	private Set<SpecificationValue> specificationValues;//商品规格值
	
	@OneToMany(targetEntity=OrderItem.class, mappedBy="product", fetch=FetchType.LAZY)
	private List<OrderItem> orderItems;//交易记录
	
	@ManyToMany(targetEntity=Member.class, mappedBy="favoriteProducts", fetch=FetchType.LAZY)
	private List<Member> members;//收藏用户列表
	
	@OneToMany(targetEntity=CartItem.class, mappedBy="product", fetch=FetchType.LAZY)
	private List<CartItem> cartItems;//购物车项
	
	@ManyToMany(targetEntity=Promotion.class, mappedBy="products", fetch=FetchType.LAZY)
	@OrderBy("beginDate desc")
	private List<Promotion> promotions;//关联活动
	
	@OneToMany(targetEntity=Lottery.class, mappedBy="product", fetch=FetchType.LAZY)
	private List<Lottery> lotterys;//关联抽奖活动

	public Product()
	{
		this.tags = new HashSet<>();
		this.specifications = new HashSet<>();
		this.specificationValues = new HashSet<>();
		this.images = new HashSet<>();
		this.orderItems = new LinkedList<>();
		this.members = new LinkedList<>();
		this.promotions = new LinkedList<>();
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

	public int getStock()
	{
		return stock;
	}

	public void setStock(int stock)
	{
		this.stock = stock;
	}

	public double getCost()
	{
		return cost;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public ProductCategory getProductCategory()
	{
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory)
	{
		this.productCategory = productCategory;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Long getHits()
	{
		return hits;
	}

	public void setHits(Long hits)
	{
		this.hits = hits;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getMediumImage()
	{
		return mediumImage;
	}

	public void setMediumImage(String mediumImage)
	{
		this.mediumImage = mediumImage;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean isGift()
	{
		return gift;
	}

	public void setGift(boolean gift)
	{
		this.gift = gift;
	}

	public boolean isMarketable()
	{
		return marketable;
	}

	public void setMarketable(boolean marketable)
	{
		this.marketable = marketable;
	}

	public boolean isTop()
	{
		return top;
	}

	public void setTop(boolean top)
	{
		this.top = top;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

	public double getMarketPrice()
	{
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice)
	{
		this.marketPrice = marketPrice;
	}

	public Long getMonthHits()
	{
		return monthHits;
	}

	public void setMonthHits(Long monthHits)
	{
		this.monthHits = monthHits;
	}

	public Date getMonthHitsdate()
	{
		return monthHitsdate;
	}

	public void setMonthHitsdate(Date monthHitsdate)
	{
		this.monthHitsdate = monthHitsdate;
	}

	public Long getMonthSales()
	{
		return monthSales;
	}

	public void setMonthSales(Long monthSales)
	{
		this.monthSales = monthSales;
	}

	public Date getMonthSalesdate()
	{
		return monthSalesdate;
	}

	public void setMonthSalesdate(Date monthSalesdate)
	{
		this.monthSalesdate = monthSalesdate;
	}

	public double getAppPrice()
	{
		double app_price = appPrice;
		if(app_price < 0)
		{
			app_price = price;
		}
		return app_price;
	}

	public void setAppPrice(double appPrice)
	{
		this.appPrice = appPrice;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Long getSales()
	{
		return sales==null?0:sales;
	}

	public void setSales(Long sales)
	{
		this.sales = sales;
	}

	public float getScore()
	{
		return score;
	}

	public void setScore(float score)
	{
		this.score = score;
	}

	public Long getScoreCount()
	{
		return scoreCount;
	}

	public void setScoreCount(Long scoreCount)
	{
		this.scoreCount = scoreCount;
	}

	public String getSeoDescription()
	{
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription)
	{
		this.seoDescription = seoDescription;
	}

	public String getSeoKeywords()
	{
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords)
	{
		this.seoKeywords = seoKeywords;
	}

	public String getSeoTitle()
	{
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle)
	{
		this.seoTitle = seoTitle;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public Long getWeekHits()
	{
		return weekHits;
	}

	public void setWeekHits(Long weekHits)
	{
		this.weekHits = weekHits;
	}

	public Date getWeekHitsdate()
	{
		return weekHitsdate;
	}

	public void setWeekHitsdate(Date weekHitsdate)
	{
		this.weekHitsdate = weekHitsdate;
	}

	public Long getWeekSales()
	{
		return weekSales;
	}

	public void setWeekSales(Long weekSales)
	{
		this.weekSales = weekSales;
	}

	public Date getWeekSalesdate()
	{
		return weekSalesdate;
	}

	public void setWeekSalesdate(Date weekSalesdate)
	{
		this.weekSalesdate = weekSalesdate;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	public Goods getGoods()
	{
		return goods;
	}

	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}

	public boolean isList()
	{
		return list;
	}

	public void setList(boolean list)
	{
		this.list = list;
	}

	public Long getPoint()
	{
		return point;
	}

	public void setPoint(Long point)
	{
		this.point = point;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public Brand getBrand()
	{
		return brand;
	}

	public void setBrand(Brand brand)
	{
		this.brand = brand;
	}

	public String getThumbnail()
	{
		return thumbnail;
	}	

	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public boolean isFreePost()
	{
		return freePost;
	}
	
	public String getWarehouse()
	{
		return warehouse;
	}

	public void setWarehouse(String warehouse)
	{
		this.warehouse = warehouse;
	}

	public void setFreePost(boolean freePost)
	{
		this.freePost = freePost;
	}

	public Set<ProductImage> getImages() {
		return images;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}

	public Set<Tag> getTags()
	{
		return tags;
	}

	public void setTags(Set<Tag> tags)
	{
		this.tags = tags;
	}

	public Set<Specification> getSpecifications()
	{
		return specifications;
	}

	public void setSpecifications(Set<Specification> specifications)
	{
		this.specifications = specifications;
	}

	public Set<SpecificationValue> getSpecificationValues()
	{
		return specificationValues;
	}

	public void setSpecificationValues(Set<SpecificationValue> specificationValues)
	{
		this.specificationValues = specificationValues;
	}

	/**
	 * 获取兄弟产品
	 * @return
	 */
	public Set<Product> getSlibings()
	{
		Set<Product> set = new HashSet<>();
		for(Product model : this.goods.getProducts())
		{
			if(model.id != this.id)
				set.add(model);
		}
		return set;
	}
	
	/**
	 * 获取规格值
	 * @param specification
	 * @return
	 */
	public SpecificationValue getSpecificationValue(Specification specification)
	{
		SpecificationValue value = null;
		if(specification == null)
			return null;
		for(SpecificationValue sv : this.getSpecificationValues())
		{
			if(sv.getSpecification().getId() == specification.getId())
			{
				value = sv;
				break;
			}
		}
		return value;
	}
	
	/**
	 * 获取本身及兄弟商品的所有规格值列表
	 * @param specification
	 * @return
	 */
	public List<SpecificationValue> getAllSpecificationValues()
	{
		List<SpecificationValue> values = new LinkedList<>();
		if(this.goods != null)
		{
			for(Product model : goods.getProducts())
			{
				//只搜集所有已上架商品
				if(model.isMarketable())
				{
					for(SpecificationValue value : model.getSpecificationValues())
					{
						if(!values.contains(value))
							values.add(value);
					}
				}				
			}
		}
		return values;
	}

	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public List<Member> getMembers()
	{
		return members;
	}

	public void setMembers(List<Member> members)
	{
		this.members = members;
	}

	public List<CartItem> getCartItems()
	{
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems)
	{
		this.cartItems = cartItems;
	}

	public List<Promotion> getPromotions()
	{
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions)
	{
		this.promotions = promotions;
	}
	
	/**
	 * 获取当前正在抢购的促销活动
	 * @return 存在, 返回活动对象; 否则, 返回null
	 */
	public Promotion getCurrentSnatchPromotion()
	{
		Promotion promotion = null;
		Date now = new Date();
		Iterator<Promotion> iter = promotions.iterator();
		while(iter.hasNext())
		{
			Promotion pmt = iter.next();
			String enabled = pmt.getAttributes().get(Promotion.Keys.isEnabled);
			String snacth = pmt.getAttributes().get(Promotion.Keys.snatchEnabled);
			if(!StringUtils.equals(enabled, "true") || !StringUtils.equals(snacth, "true"))
			{
				continue;
			}
			
			if(now.getTime() >= pmt.getBeginDate().getTime() && now.getTime() <= pmt.getEndDate().getTime())
			{
				promotion = pmt;
				break;
			}
		}
		return promotion;
	}
	
	/**
	 * 获取当前商品参与的促销活动(非抢购)
	 * @return	获取当前商品的非抢购促销活动
	 */
	public Promotion getCurrentPromotion()
	{
		Promotion promotion = null;
		Date now = new Date();
		Iterator<Promotion> iter = promotions.iterator();
		while(iter.hasNext())
		{
			Promotion pmt = iter.next();
			String enabled = pmt.getAttributes().get(Promotion.Keys.isEnabled);
			String snacth = pmt.getAttributes().get(Promotion.Keys.snatchEnabled);
			if(!StringUtils.equals(enabled, "true") || StringUtils.equals(snacth, "true"))
			{
				continue;
			}
			
			if(now.getTime() >= pmt.getBeginDate().getTime() && now.getTime() <= pmt.getEndDate().getTime())
			{
				promotion = pmt;
				break;
			}
		}
		return promotion;
	}
	
	/**
	 * 根据给定的Specification，获取product对应的SpecificationValue
	 * @param spec
	 * @return
	 */
	public List<SpecificationValue> getSpecValues(Specification spec){
		
		List<SpecificationValue> specValues = null;
		if(spec == null || !this.specifications.contains(spec) || this.specificationValues.size()==0 ){
			
			return specValues;
		}
		
		specValues = new ArrayList<SpecificationValue>();
		List<SpecificationValue> spvlAll = spec.getValues();
		
		if(spvlAll == null || spvlAll.size()==0){
			return specValues;
		}
		
		for(SpecificationValue sv : spvlAll){
			
			if(this.specificationValues.contains(sv)){
				
				specValues.add(sv);
			}
		}
		
		return specValues;
	}
	
	/**
	 * 获取所有的规格值的拼接string，以空格分隔
	 * @return
	 */
	public String getSpValuesStr()
	{	
		StringBuffer builder = new StringBuffer();
		if(specificationValues != null && !specificationValues.isEmpty())
		{
			for(SpecificationValue value : specificationValues)
			{
				builder.append(" ").append(value.getName());
			}
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * 获取实际的销量
	 * 
	 * 不含交易取消的
	 * @return
	 */
	public long getEffectiveSales(){
		
		return 0L;
	}

	public List<Lottery> getLotterys()
	{
		return lotterys;
	}

	public void setLotterys(List<Lottery> lotterys)
	{
		this.lotterys = lotterys;
	} 
	
}
