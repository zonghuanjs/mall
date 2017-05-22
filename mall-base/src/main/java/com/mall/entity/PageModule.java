package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import cn.tekism.mall.common.PageType;

/**
 * 页面模块
 * @author ChenMingcai
 * 2016-12-14
 *
 */

@Entity
@Table(name="tk_page_module")
public class PageModule extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模块类型
	 * @author ChenMingcai
	 *
	 */
	public enum Type
	{
		/**
		 * 单图列表
		 */
		singleImageList(1),
		/**
		 * 分类模块
		 */
		categoryList(2),
		/**
		 * 轮播
		 */
		slideShow(3),
		/**
		 * 并排图
		 */
		sideBySide(4),
		/**
		 * 标题图片列表
		 */
		titleAndList(5);
		
		private final int type;
		
		private Type(int type)
		{
			this.type = type;
		}
		
		public int type()
		{
			return this.type;
		}
		
		/**
		 * 查找模块类型
		 * @param type	类型值
		 * @return	模块类型
		 */
		public static Type findType(int type)
		{
			for(Type t : values())
			{
				if(t.type == type)
				{
					return t;
				}
			}
			
			return singleImageList;
		}
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
	 * 模块名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * 模块显示顺序
	 */
	@Column(name = "n_order")
	private Integer order;
	
	/**
	 * 显示内容数量
	 */
	@Column(name = "n_count")
	private Integer count;
	
	/**
	 * 模块类型
	 */
	@Column(name = "n_type")
	private Integer type = Type.singleImageList.type();
	
	/**
	 * 页面类型
	 */
	//@Column(name = "n_page_type")
	//private Integer pageType = PageType.pcHome.type();

	/**
	 * 页面元素表
	 */
	@ElementCollection
	@CollectionTable(name = "tk_page_module_attr", joinColumns=@JoinColumn(name="page_module"))
	@MapKeyColumn(name = "rules")
	@MapKeyClass(Integer.class)
	@Column(name="module_attribute")
	private Map<Integer, String> pageModuleAttrs;
	
	/**
	 * 关联模块元素
	 */
	@OneToMany(targetEntity=ModuleElement.class, mappedBy="module", fetch=FetchType.LAZY)
	@OrderBy("orders asc")
	private List<ModuleElement> elements;
	
	public PageModule() {
		this.pageModuleAttrs = new HashMap<Integer, String>();
		this.elements = new LinkedList<ModuleElement>();
	}
	
	/*public PageType getPageType()
	{
		return PageType.forType(pageType);
	}

	public void setPageType(PageType pageType)
	{
		this.pageType = pageType.type();
	}*/	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Type getType() {
		return Type.findType(this.type);
	}

	public void setType(Type type) {
		this.type = type.type();
	}

	public Map<Integer, String> getPageModuleAttrs() {
		return pageModuleAttrs;
	}

	public void setPageModuleAttrs(Map<Integer, String> pageModuleAttrs) {
		this.pageModuleAttrs = pageModuleAttrs;
	}

	public List<ModuleElement> getElements() {
		return elements;
	}

	public void setElements(List<ModuleElement> elements) {
		this.elements = elements;
	}
	
	

}
