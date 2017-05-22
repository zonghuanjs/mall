package com.mall.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 记录APP更新日志
 * （Android、 IOS） 
 * @author shaoling.mi
 *
 */

@Entity
@Table(name="tb_app_version")
public class AppPackage
{
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name="modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;
		
	@Column(name="enabled")
	private boolean enabled;//是否可用
	
	@Column(name="orders")
	private int orders; // 显示顺序
	
	@Column(name="type")
	private int type;//APP类型（Android 、OIS）
	
	@Column(name="version_code")
	private String versionCode;//当前版本代码
	
	@Column(name="version_name")
	private String versionName;//当前版本名称
	
	/**
	 * 下载地址
	 */
	@Column(name="download_url")
	private String downloadUrl;
	
	/**
	 * 安装包名称
	 */
	@Column(name="package_name")
	private String packageName;
	
	/**
	 * 更新内容描述
	 */
	@Column(name="update_content")
	private String updateContent; 
	

	public AppPackage(){
		
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


	public boolean isEnabled()
	{
		return enabled;
	}


	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}


	public int getOrders()
	{
		return orders;
	}


	public void setOrders(int orders)
	{
		this.orders = orders;
	}


	public int getType()
	{
		return type;
	}


	public void setType(int type)
	{
		this.type = type;
	}


	public String getVersionCode()
	{
		return versionCode;
	}


	public void setVersionCode(String versionCode)
	{
		this.versionCode = versionCode;
	}


	public String getVersionName()
	{
		return versionName;
	}


	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}


	public String getDownloadUrl()
	{
		return downloadUrl;
	}


	public void setDownloadUrl(String downloadUrl)
	{
		this.downloadUrl = downloadUrl;
	}


	public String getPackageName()
	{
		return packageName;
	}


	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}


	public String getUpdateContent()
	{
		return updateContent;
	}


	public void setUpdateContent(String updateContent)
	{
		this.updateContent = updateContent;
	}
	
	
	public static class AppType{
		
		public static final int ANDROID = 1;
		public static final int IOS = 2;
	}
	
}
