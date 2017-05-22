package com.mall.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 维修实体
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_repair")
public class Repair
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//维修实体标识
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
	
	@OneToOne(targetEntity=Apply.class, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="apply")
	private Apply apply;//关联申请单
	
	@Column(name="amount")
	private Double amount;//维修费用
	
	@Column(name="status")
	private int status;//维修状态
	
	@Column(name="operator")
	private String operator;//操作员
	
	@OneToOne(targetEntity=RepairReport.class, mappedBy="repair", fetch=FetchType.LAZY)
	private RepairReport report;//关联维修检测报告

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

	public Apply getApply()
	{
		return apply;
	}

	public void setApply(Apply apply)
	{
		this.apply = apply;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}
	
	public RepairReport getReport()
	{
		return report;
	}

	public void setReport(RepairReport report)
	{
		this.report = report;
	}
	
	/**
	 * 显示维后报告编辑按钮
	 * @return
	 */
	public boolean getShowReportEdit()
	{
		boolean show = this.getReport() != null;
		return show && this.status == Status.checking;
	}
	
	/**
	 * 显示状态名称
	 * @return
	 */
	public String getStatusView()
	{
		String view = "";
		switch(this.status)
		{
			case Status.finished:
			{
				view = "已完成";
				break;
			}
			case Status.checking:
			{
				view = "检测中";
				break;
			}
			case Status.reporting:
			{
				view = "检测完毕";
				break;
			}
			case Status.processing:
			{
				view = "维修中";
				break;
			}
			case Status.closed:
			{
				view = "取消维修";
				break;
			}
			
		}
		return view;
	}
	
	/**
	 * 维修状态
	 * @author ChenMingcai
	 *
	 */
	public static final class Status
	{
		public static final int finished = 0;//维修完成
		public static final int checking = 1;//维修检测中
		public static final int reporting = 2;//检测完毕
		public static final int processing = 3;//维修中
		public static final int closed = -1;//维修取消
	}
}
