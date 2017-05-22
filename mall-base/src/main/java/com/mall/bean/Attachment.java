package com.mall.bean;

import java.util.Date;

/**
 * 附件
 * @author ChenMingcai
 *
 */

public class Attachment
{
	public enum FileType
	{
		image, media, file
	}
	
	private boolean directory;//是否目录
	private String name;//文件名
	private String url;//文件路径
	private Long size;//文件大小, 单位KB
	private Date lastModified;//最后修改时间
	
	public boolean getDirectory()
	{
		return directory;
	}
	public void setDirectory(boolean directory)
	{
		this.directory = directory;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public Long getSize()
	{
		return size;
	}
	public void setSize(Long size)
	{
		this.size = size;
	}
	public Date getLastModified()
	{
		return lastModified;
	}
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}
}
