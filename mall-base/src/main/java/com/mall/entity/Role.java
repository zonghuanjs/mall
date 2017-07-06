package com.mall.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = -8903415515918364459L;

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
	 * 角色描述
	 */
	@Column(name = "description")
	private String description;

	/**
	 * 系统级管理员
	 */
	@Column(name = "system")
	private boolean system;

	/**
	 * 角色名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 角色权限：权限之间以逗号相连
	 */
	@Column(name = "authorities")
	private String authorities;

	public Role() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
}
