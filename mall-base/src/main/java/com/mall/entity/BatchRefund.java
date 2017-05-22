package com.mall.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 批量退款实体
 * @author ChenMingcai
 * 2015-12-21
 *
 */

@Entity
@Table(name="tb_batch_refund")
public class BatchRefund
{
	public static final class Status
	{
		public static final int success = 0;//退款完成 
		public static final int pending = 1;//等待退款
		public static final int failure = 2;//退款失败
		public static final int closed = -1;//退款关闭
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//批量标识
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;//修改时间
	
	@Column(name="status")
	private int status;//状态
	
	@Column(name="sn")
	private String sn;//退款批次号
	
	@Column(name="memo")
	private String memo;//备注
	
	@OneToMany(targetEntity=Refund.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_batch_refund_refund", joinColumns=@JoinColumn(name="batchs"), 
		inverseJoinColumns=@JoinColumn(name="refunds"))
	private List<Refund> refunds;//退款列表
	
	public BatchRefund()
	{
		this.refunds = new LinkedList<Refund>();
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

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public List<Refund> getRefunds()
	{
		return refunds;
	}

	public void setRefunds(List<Refund> refunds)
	{
		this.refunds = refunds;
	}
}
