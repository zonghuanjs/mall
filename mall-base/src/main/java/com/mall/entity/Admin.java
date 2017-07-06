package com.mall.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_admin")
public class Admin
{
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;//管理员编号
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="department")
	private String department;//所属部门
	
	@Column(name="email")
	private String email;//邮箱
	
	@Column(name="enabled")
	private boolean enabled;//是否可用
	
	@Column(name="locked")
	private boolean locked;//是否锁定
	
	@Column(name="locked_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lockDate;//锁定时间
	
	@Column(name="login_failure_count")
	private int loginFailureCount = 0;//登录失败次数
	
	@Column(name="login_ip")
	private String loginIP;//上次登录IP
	
	@Column(name="login_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;
	
	@Column(name="name")
	private String name;//真实姓名
	
	@Column(name="password")
	private String password;//登录密码
	
	@Column(name="username")
	private String username;//登录名称
	
	@ManyToMany(targetEntity=Role.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_admin_role", joinColumns=@JoinColumn(name="admins"), inverseJoinColumns=@JoinColumn(name="roles"))
	private Set<Role> roles;
	
	public Admin()
	{
		this.roles = new HashSet<>();
	}
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
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
	public String getDepartment()
	{
		return department;
	}
	public void setDepartment(String department)
	{
		this.department = department;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public boolean getEnabled()
	{
		return enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	public boolean getLocked()
	{
		return locked;
	}
	public void setLocked(boolean locked)
	{
		this.locked = locked;
	}
	public Date getLockDate()
	{
		return lockDate;
	}
	public void setLockDate(Date lockDate)
	{
		this.lockDate = lockDate;
	}
	public int getLoginFailureCount()
	{
		return loginFailureCount;
	}
	public void setLoginFailureCount(int loginFailureCount)
	{
		this.loginFailureCount = loginFailureCount;
	}
	public String getLoginIP()
	{
		return loginIP;
	}
	public void setLoginIP(String loginIP)
	{
		this.loginIP = loginIP;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public Date getLoginDate()
	{
		return loginDate;
	}
	public void setLoginDate(Date loginDate)
	{
		this.loginDate = loginDate;
	}
	public Set<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}	
	
	/**
	 * 获取管理员权限
	 * @return 返回权限集合
	 */
	public Set<String> getAuthorities()
	{
		Set<String> authorities = new HashSet<>();
		for(Role role : roles)
		{
			String[] auths = role.getAuthorities().split(",");
			authorities.addAll(Arrays.asList(auths));
		}
		return authorities;
	}
}
