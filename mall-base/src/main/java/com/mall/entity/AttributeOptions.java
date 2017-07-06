package com.mall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_attribute_options")
public class AttributeOptions {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@ManyToOne(targetEntity = Attribute.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "attribute")
	private Attribute attribute;

	@Column(name = "options")
	private String options; // 可选项

	public AttributeOptions() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}
