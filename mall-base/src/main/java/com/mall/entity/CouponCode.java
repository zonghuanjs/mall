package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Lijun
 * 优惠码
 *
 */
@Entity
@Table(name = "tb_coupon_code")
public class CouponCode
{
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; //优惠码id
	
	@Column(name = "create_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "code", updatable = false)
	private String code; //优惠码
	
	@Column(name = "used")
	private boolean used; //是否使用
		
	@Column(name = "used_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usedDate; //使用时间
	
	@ManyToOne(targetEntity = Coupon.class, cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "coupon")
	private Coupon coupon; //所属优惠
	
	@ManyToOne(targetEntity = Member.class, cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "member")
	private Member member; //所属会员
	
	@Column(name = "expired", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expired; //过期时间
	
	public CouponCode()
	{

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

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public boolean isUsed()
	{
		return used;
	}

	public void setUsed(boolean used)
	{
		this.used = used;
	}

	public Date getUsedDate()
	{
		return usedDate;
	}

	public void setUsedDate(Date usedDate)
	{
		this.usedDate = usedDate;
	}

	public Coupon getCoupon()
	{
		return coupon;
	}

	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public Date getExpired()
	{
		return expired;
	}

	public void setExpired(Date expired)
	{
		this.expired = expired;
	}
	
}
