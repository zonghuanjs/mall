package com.mall.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基础实体
 * @author ChenMingcai
 * 2016-05-06
 *
 */

@MappedSuperclass
public class BaseEntity implements Serializable
{
	private static final long serialVersionUID = 6139187803837668163L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	protected Long id;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
}
