package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 发票实体
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_invoice")
public class Invoice
{
	public static final String[] invoiceContent = {
		"明细", "办公用品", "电脑配件", "耗材"
	};
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//发票ID
	
	@Column(name="type")
	private int type;//发票类型: 0, 普通发票; 1, 增值税发票
	
	@Column(name="title")
	private String title;//发票台头
	
	@Column(name="content")
	private String content;//发票内容
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="company")
	private String company;//单位名称
	
	@Column(name="id_number")
	private String idNumber;//纳税人识别号
	
	@Column(name="register_address")
	private String registerAddress;//注册地址
	
	@Column(name="register_area_name")
	private String registerAreaName;//注册地区
	
	@Column(name="register_tel")
	private String registerTel;//注册电话
	
	@Column(name="register_bank")
	private String registerBank;//注册银行
	
	@Column(name="register_account")
	private String registerAccount;//注册账户
	
	@OneToOne(targetEntity=Order.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="orders")
	private Order orders;//对应订单
	
	@Column(name="consignee")
	private String consignee;//收票人
	
	@Column(name="phone")
	private String phone;//收票人手机号
	
	@Column(name="area_name")
	private String areaName;//收票人地区
	
	@Column(name="address")
	private String address;//收票人详细地址
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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
	public Order getOrders()
	{
		return orders;
	}
	public void setOrders(Order orders)
	{
		this.orders = orders;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}
	public String getIdNumber()
	{
		return idNumber;
	}
	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}
	public String getRegisterAddress()
	{
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress)
	{
		this.registerAddress = registerAddress;
	}
	public String getRegisterAreaName()
	{
		return registerAreaName;
	}
	public void setRegisterAreaName(String registerAreaName)
	{
		this.registerAreaName = registerAreaName;
	}
	public String getRegisterTel()
	{
		return registerTel;
	}
	public void setRegisterTel(String registerTel)
	{
		this.registerTel = registerTel;
	}
	public String getRegisterBank()
	{
		return registerBank;
	}
	public void setRegisterBank(String registerBank)
	{
		this.registerBank = registerBank;
	}
	public String getRegisterAccount()
	{
		return registerAccount;
	}
	public void setRegisterAccount(String registerAccount)
	{
		this.registerAccount = registerAccount;
	}
	public String getConsignee()
	{
		return consignee;
	}
	public void setConsignee(String consignee)
	{
		this.consignee = consignee;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAreaName()
	{
		return areaName;
	}
	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public static String[] getInvoicecontent()
	{
		return invoiceContent;
	}
	
}
