package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 会员增值税信息
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_add_tax")
public class AddTax extends BaseEntity
{
	private static final long serialVersionUID = 6517829457502272433L;

	/**
	 * 创建时间
	 */
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	/**
	 * 注册公司
	 */
	@Column(name="company")
	private String company;
	
	/**
	 * 纳税人识别号：15位, 18位或者20位
	 */
	@Column(name="id_number")
	private String idNumber;
	
	/**
	 * 注册地区
	 */
	@ManyToOne(targetEntity=Area.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="register_area")
	private Area registerArea;
	
	/**
	 * 注册地址
	 */
	@Column(name="register_address")
	private String registerAddress;
	
	/**
	 * 注册电话
	 */
	@Column(name="register_tel")
	private String registerTel;
	
	/**
	 * 注册银行
	 */
	@Column(name="register_bank")
	private String registerBank;
	
	/**
	 * 注册账户
	 */
	@Column(name="register_account")
	private String registerAccount;
	
	/**
	 * 税务登记证
	 */
	@Column(name="register_certificate")
	private String registerCertificate;
	
	/**
	 * 纳税人证书
	 */
	@Column(name="tax_certificate")
	private String taxCertificate;
	
	/**
	 * 是否已审核
	 */
	@Column(name="checked")
	private boolean checked;
	
	/**
	 * 审核状态
	 */
	@Column(name="check_status")
	private int checkStatus;
	
	/**
	 * 开票资料  营业执照
	 */
	@Column(name="tax_data")
	private String taxData;

	/**
	 * 替代资料
	 */
	@Column(name="other_data")
	private String otherData;
	
	/**
	 * 委托书
	 */
	 @Column(name="delegate")
	 private String delegate;
	
	/**
	 * 不通过原因
	 */
	@Column(name="reason")
	private String reason;
	
	/**
	 * 关联会员
	 */
	@OneToOne(targetEntity=Member.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="member")
	private Member member;

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

	public Area getRegisterArea()
	{
		return registerArea;
	}

	public void setRegisterArea(Area registerArea)
	{
		this.registerArea = registerArea;
	}

	public String getRegisterAddress()
	{
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress)
	{
		this.registerAddress = registerAddress;
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

	public String getRegisterCertificate()
	{
		return registerCertificate;
	}

	public void setRegisterCertificate(String registerCertificate)
	{
		this.registerCertificate = registerCertificate;
	}

	public String getTaxCertificate()
	{
		return taxCertificate;
	}

	public void setTaxCertificate(String taxCertificate)
	{
		this.taxCertificate = taxCertificate;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public int getCheckStatus()
	{
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus)
	{
		this.checkStatus = checkStatus;
	}
	
	
	public String getTaxData()
	{
		return taxData;
	}

	public void setTaxData(String taxData)
	{
		this.taxData = taxData;
	}

	public String getOtherData()
	{
		return otherData;
	}

	public void setOtherData(String otherData)
	{
		this.otherData = otherData;
	}

	public String getDelegate()
	{
		return delegate;
	}

	public void setDelegate(String delegate)
	{
		this.delegate = delegate;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	/**
	 * 重置增税状态
	 */
	public void resetStatus()
	{
		this.checked = false;
		this.checkStatus = CheckStatus.WAIT;
		this.reason = null;
	}
	/**
	 * 显示当前审核状态
	 * @return
	 */
	public String getCurrentStatus(){
		
		if(this.checkStatus == CheckStatus.CONFIRM){
			return "审核通过";
		}else if(this.checkStatus == CheckStatus.CANCEL){
			return "审核未通过";
		}else{
			return "待审核";
		}
	}
	
	public static final class CheckStatus
	{
		public static final int CONFIRM = 1;//审核通过
		public static final int CANCEL = 2;//审核不通过
		public static final int WAIT = 0;//未处理
	}
	
	
}
