package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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

@Entity
@Table(name = "tb_product_category")
public class ProductCategory extends BaseEntity {
	private static final long serialVersionUID = 2638722541003948209L;

	/**
	 * 类别名称
	 */
	@Column(name = "name")
	private String name;

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
	 * SEO标题
	 */
	@Column(name = "seo_title")
	private String seoTitle;

	/**
	 * SEO关键词
	 */
	@Column(name = "seo_keywords")
	private String seoKeywords;

	/**
	 * SEO描述
	 */
	@Column(name = "seo_description")
	private String seoDescription;

	/**
	 * 类别路径 以英文逗号分隔，排序是父接着子，例如：,祖父辈,父辈 顶级是：,
	 */
	@Column(name = "tree_path")
	private String treePath;

	/**
	 * 显示顺序
	 */
	@Column(name = "orders")
	private int orders;

	/**
	 * 上级分类
	 */
	@ManyToOne(targetEntity = ProductCategory.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private ProductCategory parent;

	/**
	 * 关联品牌
	 */
	@ManyToMany(targetEntity = Brand.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tb_product_category_brand", joinColumns = @JoinColumn(name = "product_categorys"), inverseJoinColumns = @JoinColumn(name = "brands"))
	@OrderBy("id")
	private Set<Brand> brands;

	/**
	 * 直接子类列表
	 */
	@OneToMany(targetEntity = ProductCategory.class, mappedBy = "parent", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@OrderBy("parent asc,orders asc")
	private Set<ProductCategory> children;

	/**
	 * 关联参数组
	 */
	@OneToMany(targetEntity = ParameterGroup.class, mappedBy = "productCategory", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@OrderBy("orders")
	private List<ParameterGroup> groups;

	@OneToMany(targetEntity = Specification.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tb_product_category_specification", joinColumns = @JoinColumn(name = "categorys"), inverseJoinColumns = @JoinColumn(name = "specifications"))
	@OrderBy("orders")
	private Set<Specification> specs;

	@OneToMany(targetEntity = SpecificationValue.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tb_product_category_specification_value", joinColumns = @JoinColumn(name = "categorys"), inverseJoinColumns = @JoinColumn(name = "spec_values"))
	@OrderBy("orders")
	private Set<SpecificationValue> specValues;

	/**
	 * 分类筛选参数
	 */
	@ElementCollection
	@CollectionTable(name = "tb_category_parameter", joinColumns = @JoinColumn(name = "categorys"))
	@Column(name = "parameter")
	private Set<String> filterParameter;

	public ProductCategory() {
		this.brands = new HashSet<>();
		this.children = new HashSet<>();
		this.groups = new LinkedList<>();
		this.specs = new HashSet<>();
		this.specValues = new HashSet<>();

		filterParameter = new HashSet<String>();
	}

	/**
	 * 获取父类ID列表
	 * 
	 * @return
	 */
	public Long[] getParentIds() {
		if (treePath != null && !",".equals(treePath)) {
			String[] tokens = treePath.split(",");
			List<Long> idsList = new ArrayList<Long>(tokens.length);
			for (String token : tokens) {
				if (!"".equals(token)) {
					idsList.add(Long.valueOf(token));
				}
			}

			Long[] ids = new Long[idsList.size()];
			idsList.toArray(ids);
			return ids;
		}

		return new Long[] {};
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

	public Set<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}

	public Set<ProductCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductCategory> children) {
		this.children = children;
	}

	public List<ParameterGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ParameterGroup> groups) {
		this.groups = groups;
	}

	public Set<Specification> getSpecs() {
		return specs;
	}

	public void setSpecs(Set<Specification> specs) {
		this.specs = specs;
	}

	public Set<SpecificationValue> getSpecValues() {
		return specValues;
	}

	public void setSpecValues(Set<SpecificationValue> specValues) {
		this.specValues = specValues;
	}

	/**
	 * 根据给定的Specification，获取category对应的SpecificationValue
	 * 
	 * @param spec
	 * @return
	 */
	public List<SpecificationValue> getSpecValues(Specification spec) {

		List<SpecificationValue> specValues = null;
		if (spec == null || !this.specs.contains(spec) || this.specValues.size() == 0) {

			return specValues;
		}

		specValues = new ArrayList<SpecificationValue>();
		List<SpecificationValue> spvlAll = spec.getValues();

		if (spvlAll == null || spvlAll.size() == 0) {
			return specValues;
		}

		for (SpecificationValue sv : spvlAll) {

			if (this.specValues.contains(sv)) {

				specValues.add(sv);
			}
		}

		return specValues;
	}

	public Set<String> getFilterParameter() {
		return filterParameter;
	}

	public void setFilterParameter(Set<String> filterParameter) {
		this.filterParameter = filterParameter;
	}

}
