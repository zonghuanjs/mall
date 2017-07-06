package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_article")
public class Article {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; // 修改时间

	@Column(name = "author")
	private String author; // 作者

	@Column(name = "title")
	private String title; // 标题

	@ManyToOne(targetEntity = ArticleCategory.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	private ArticleCategory category; // 文章分类

	@OneToMany(targetEntity = ArticleResource.class, mappedBy = "articles", fetch = FetchType.LAZY)
	private Set<ArticleResource> resources;

	@Column(name = "content")
	private String content; // 内容

	@Column(name = "hits")
	private Long hits; // 点击量

	@Column(name = "publication")
	private boolean isPublication; // 是否出版

	@Column(name = "top")
	private boolean isTop; // 是否置顶

	@Column(name = "seo_description")
	private String seoDescription; // seo描述

	@Column(name = "seo_keywords")
	private String seoKeywords; // seo关键字

	@Column(name = "seo_title")
	private String seoTitle; // seo标题

	@ManyToMany(targetEntity = Tag.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tb_article_tag", joinColumns = @JoinColumn(name = "articles"), inverseJoinColumns = @JoinColumn(name = "tags"))
	private Set<Tag> tags;

	public Article() {
		resources = new HashSet<ArticleResource>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArticleCategory getCategory() {
		return category;
	}

	public void setCategory(ArticleCategory category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<ArticleResource> getResources() {
		return resources;
	}

	public void setResources(Set<ArticleResource> resources) {
		this.resources = resources;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public boolean isPublication() {
		return isPublication;
	}

	public void setPublication(boolean isPublication) {
		this.isPublication = isPublication;
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
