package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.mall.util.RequestUtil;

@Entity
@Table(name = "tb_ad_position")
public class AdPosition extends BaseEntity
{
	private static final long serialVersionUID = 2425034779512626311L;
	
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
	 * 位置描述
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * 位置宽度
	 */
	@Column(name = "width")
	private int width;
	
	/**
	 * 位置高度
	 */
	@Column(name = "height")
	private int height;
	
	/**
	 * 位置名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 模版
	 */
	@Column(name = "template")
	private String template;
	
	@OneToMany(targetEntity=Ad.class, mappedBy="position")
	@OrderBy("orders asc")
	private List<Ad> ads;//关联广告
	
	@Transient
	private String view;//广告内容
	
	@Transient
	private  List<Ad> effectiveADs;//有效的广告

	public AdPosition()
	{
		this.ads = new LinkedList<>();
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTemplate()
	{
		return template;
	}

	public void setTemplate(String template)
	{
		this.template = template;
	}

	public List<Ad> getAds()
	{
		return ads;
	}

	public void setAds(List<Ad> ads)
	{
		this.ads = ads;
	}

	public String getView()
	{
		Map<String, Object> model = new HashMap<>();
		List<Ad> adList = new LinkedList<>();
		
		//过滤未开始广告和过期广告
		Iterator<Ad> iter = ads.iterator();
		Date now = new Date();
		while(iter.hasNext())
		{
			Ad ad = iter.next();
			if(now.getTime() >= ad.getBeginDate().getTime() && now.getTime() <= ad.getEndDate().getTime())
			{
				adList.add(ad);
			}
		}
		model.put("ads", adList);
		String base = RequestUtil.getRequest().getContextPath();
		model.put("base", base);
		//view = FreemarkerUtils.process(template, model);
		return view;
	}

	public void setView(String view)
	{
		this.view = view;
	}
	
	public  List<Ad> getEffectiveADs() {
		
		List<Ad> adList = new LinkedList<>();
		
		//过滤未开始广告和过期广告
		Iterator<Ad> iter = ads.iterator();
		Date now = new Date();
		while(iter.hasNext())
		{
			Ad ad = iter.next();
			if(now.getTime() >= ad.getBeginDate().getTime() && now.getTime() <= ad.getEndDate().getTime())
			{
				adList.add(ad);
			}
		}
		return adList;
	}

	public void setEffectiveADs(List<Ad> effectiveADs) {
		
		this.effectiveADs = effectiveADs;
	}
	
	
}
