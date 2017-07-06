package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_article_resources")
public class ArticleResource {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreateDate;// 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间

	@Column(name = "title")
	private String title;// 标题

	@Column(name = "orders") // 排序
	private int orders;

	@Column(name = "url")
	private String url; // 链接地址

	@Column(name = "articles")
	private Long articles; // 关联article的id

	/*
	 * @ManyToOne(targetEntity=Article.class, cascade={CascadeType.PERSIST,
	 * CascadeType.MERGE}, fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "articles") private Article atticle;
	 */
	public ArticleResource() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getArticles() {
		return articles;
	}

	public void setArticles(Long articles) {
		this.articles = articles;
	}

}
