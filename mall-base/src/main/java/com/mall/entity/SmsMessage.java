package com.mall.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 短信发送记录
 * @author ChenMingcai
 *
 */

@Entity
@Table(name="tb_message_send")
public class SmsMessage
{
	private static int seqCode = 0;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;//发送标识
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name="sn")
	private String sn;//流水号
	
	@Column(name="status")
	private int status;//发送状态: 1, 进行中; 0, 发送完毕
	
	@Column(name="content")
	private String content;//发送内容
	
	@Column(name="result")
	private String result;//发送结果
	
	@Column(name="description")
	private String description;
	
	@Column(name="taskid")
	private String taskid;
	
	@Column(name="faillist")
	private String faillist;
	
	@Column(name="ip")
	private String ip;
	
	@ElementCollection(targetClass=String.class)
	@JoinTable(name="tb_message_item", joinColumns=@JoinColumn(name="messages"))
	private List<String> numbers;//发送号码 
	
	public SmsMessage()
	{
		this.numbers = new LinkedList<>();
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

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getTaskid()
	{
		return taskid;
	}

	public void setTaskid(String taskid)
	{
		this.taskid = taskid;
	}

	public String getFaillist()
	{
		return faillist;
	}

	public void setFaillist(String faillist)
	{
		this.faillist = faillist;
	}

	public List<String> getNumbers()
	{
		return numbers;
	}

	public void setNumbers(List<String> numbers)
	{
		this.numbers = numbers;
	}
	
	/**
	 * 产生短信序列号
	 * @return
	 */
	public static synchronized int getSeqCode()
	{
		if(seqCode > 9000)
			seqCode = 0;
		seqCode++;
		return seqCode;
	}
	
	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * 发送状态
	 * @author ChenMingcai
	 *
	 */
	public static final class Status
	{
		public static final int sending = 1;//发送中
		public static final int finished = 0;//发送完毕
		public static final int error = -1;//发送失败
	}
}
