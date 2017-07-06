package com.mall.entity;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tk_module_element")
public class ModuleElement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 图片地址
	 */
	@Column(name = "image")
	private String image;

	/**
	 * 连接地址
	 */
	@Column(name = "link")
	private String link;

	/**
	 * 元素标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 显示顺序
	 */
	@Column(name = "n_orders")
	private int orders;

	/**
	 * 关联模块
	 */
	@ManyToOne(targetEntity = PageModule.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "modules")
	private PageModule module;

	/**
	 * 模块元素表
	 */
	@ElementCollection
	@CollectionTable(name = "tk_module_element_attr", joinColumns = @JoinColumn(name = "module_elemnt"))
	@MapKeyColumn(name = "rules")
	@MapKeyClass(Integer.class)
	@Column(name = "element_attribute")
	private Map<Integer, String> moduleElementAttrs;

	public ModuleElement() {

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public PageModule getModule() {
		return module;
	}

	public void setModule(PageModule module) {
		this.module = module;
	}

	public Map<Integer, String> getModuleElementAttrs() {
		return moduleElementAttrs;
	}

	public void setModuleElementAttrs(Map<Integer, String> moduleElementAttrs) {
		this.moduleElementAttrs = moduleElementAttrs;
	}

}
