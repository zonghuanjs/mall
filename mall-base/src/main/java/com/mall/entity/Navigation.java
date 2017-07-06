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
@Table(name = "tb_navigation")
public class Navigation {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;// 编号

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;// 注册时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;// 修改时间

	@Column(name = "orders")
	private int orders;// 显示顺序

	@Column(name = "blank_target")
	private boolean blankTarget;// 是否新窗

	@Column(name = "name")
	private String name;// 导航名称

	@Column(name = "position")
	private int position;// 导航位置: 1, 顶部; 2, 中部; 3,底部

	@Column(name = "url")
	private String url;// 导航地址

	public Navigation() {

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

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public boolean isBlankTarget() {
		return blankTarget;
	}

	public void setBlankTarget(boolean blankTarget) {
		this.blankTarget = blankTarget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static final class Position {
		public static final int top = 1;// 顶部导航
		public static final int nav = 2;// 主导条
		public static final int footer = 3;// 页脚导航
		public static final int bottom = 4;// 底部导航
	}
}
