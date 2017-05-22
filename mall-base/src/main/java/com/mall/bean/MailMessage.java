package com.mall.bean;

import java.util.List;

/**
 * 
 * 邮件信息封装
 * 
 * @author ChenMingcai
 *
 */

public class MailMessage
{
	private String sender;//发件人
	private String from;//发件地址
	private List<String> receivers;//收件人列表
	private List<String> copiers;//抄送列表
	private String subject;//主题
	private String content;//内容
	
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	public List<String> getReceivers()
	{
		return receivers;
	}
	public void setReceivers(List<String> receivers)
	{
		this.receivers = receivers;
	}
	public List<String> getCopiers()
	{
		return copiers;
	}
	public void setCopiers(List<String> copiers)
	{
		this.copiers = copiers;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getFrom()
	{
		return from;
	}
	public void setFrom(String from)
	{
		this.from = from;
	}
}
