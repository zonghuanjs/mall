package com.mall.bean;

/**
 * 邮件配置
 * 
 * @author ChenMingcai
 *
 */

public class MailConfig
{
	private String smtpHost;//邮件主机
	private int smtpPort;//邮件端口
	private String smtpUsername;//用户名
	private String smtpPassword;//密码
	
	public String getSmtpHost()
	{
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}
	public int getSmtpPort()
	{
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort)
	{
		this.smtpPort = smtpPort;
	}
	public String getSmtpUsername()
	{
		return smtpUsername;
	}
	public void setSmtpUsername(String smtpUsername)
	{
		this.smtpUsername = smtpUsername;
	}
	public String getSmtpPassword()
	{
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword)
	{
		this.smtpPassword = smtpPassword;
	}
}
