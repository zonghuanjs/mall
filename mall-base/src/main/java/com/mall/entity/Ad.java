package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.mall.service.ProductService;
import com.mall.util.FreemarkerUtils;
import com.mall.util.RequestUtil;
import com.mall.util.SpringUtil;

/**
 * 
 * @author huan.zong
 *
 */
@Entity
@Table(name = "tb_ad")
public class Ad extends BaseEntity
{
	private static final long serialVersionUID = -5236322876547487429L;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "orders")
	private int orders;//顺序
	
	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate; //开始时间
	
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate; //结束时间
	
	@Column(name = "content")
	private String content;//广告内容
	
	@Column(name = "path")
	private String path;//图片资源URL
	
	@Column(name = "title")
	private String title;//广告标题
	
	@Column(name = "type")
	private int type;//广告类型: 1, 文本; 2, 图片; 3, flash
	
	@Column(name = "url")
	private String url;//链接地址(http://mall.tekism.cn/product/product!116.do)
	
	@ManyToOne(targetEntity=AdPosition.class, fetch=FetchType.LAZY)
	@JoinColumn(name="position")
	private AdPosition position;//呈现位置
	
	@Transient
	private String view;//模板视图
	
	@Transient
	private String content1;//广告内容分割1
	
	@Transient
	private String content2;//广告内容分割2
	
	public Ad()
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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
    
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public AdPosition getPosition()
	{
		return position;
	}

	public void setPosition(AdPosition position)
	{
		this.position = position;
	}
	
	public String getView()
	{
		Map<String, Object> model = new HashMap<>();
		model.put("ad", this);
		String base = RequestUtil.getRequest().getContextPath();
		model.put("base", base);
		String template = this.content;
		view = FreemarkerUtils.process(template, model);
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}
	
	/**
	 * 广告类型
	 * @author ChenMingcai
	 *
	 */
	public static final class Type
	{
		public static final int TEXT = 1;//文本
		public static final int IMAGE = 2;//图片
		public static final int FLASH = 3;//flash
	}

	public String getContent1() {
		
		String con1 = null; 
		if(content != null){
			
			content = content.replace("；", ";");
			String[] con = content.split(";");
			if(con!=null && con.length >0){
				con1 = con[0];
			}
		}
		return con1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		String con2 = null; 
		if(content != null){
			
			content = content.replace("；", ";");
			String[] con = content.split(";");
			if(con!=null && con.length >1){
				con2 = con[1];
			}
		}
		return con2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	/**
	 * 获取与广告关联的商品对象
	 * @return	如果关联有商品返回商品对象; 否则, 返回null
	 */
	public Product getProduct() 
	{		
		Product product = null;
		
		if(this.url != null)
		{	
			int index = url.lastIndexOf("/product/product!");
			if(index >0)
			{	
				int separator = url.lastIndexOf("!");
				int dot = url.lastIndexOf(".");
				
				Long id = Long.valueOf(url.substring(separator + 1, dot));
				
				ProductService productService = (ProductService)SpringUtil.getBean("productServiceImpl");
				
				product = productService.get(id);
			}
		}
		return product;
	}

}
