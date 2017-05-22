package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商品资源文件
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_goods_resources")
public class GoodsResources extends BaseEntity
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
	 * 资源名称
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 文件标题
	 */
	@Column(name="title")
	private String title;
	
	/**
	 * 显示顺序
	 */
	@Column(name="orders")
	private int orders;
	
	/**
	 * 资源类型
	 */
	@Column(name="type")
	private int type;
	
	/**
	 * 资源地址
	 */
	@Column(name="url")
	private String url;
	
	/**
	 * 关联商品组
	 */
	@ManyToOne(targetEntity=Goods.class, cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="goods")
	private Goods goods;

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

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getOrders()
	{
		return orders;
	}

	public void setOrders(int orders)
	{
		this.orders = orders;
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

	public Goods getGoods()
	{
		return goods;
	}

	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}
}
