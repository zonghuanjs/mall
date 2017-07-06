package com.mall.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_apply_log")
public class ApplyLog {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id; // id

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate; // 创建时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate; // 修改时间

	@Column(name = "operator")
	private String operator; // 操作员

	@Column(name = "ip")
	private String ip; // ip地址

	@Column(name = "content")
	private String content; // 日志内容

	@Column(name = "type")
	private int type; // 类型

	@ManyToOne(targetEntity = Apply.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "apply")
	private Apply apply; // 所属申请

	public ApplyLog() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public String getTypeView() {
		String view = "";
		switch (this.type) {
		case Type.RETURN:
			view = "退货";
			break;
		case Type.SWICTH:
			view = "换货";
			break;
		case Type.REPAIR:
			view = "维修";
			break;
		}
		return view;
	}

	public static final class Type {
		public static final int RETURN = 1;// 退货
		public static final int SWICTH = 2;// 换货
		public static final int REPAIR = 3;// 维修
	}

}
