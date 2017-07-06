package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tk_bean_used")
public class BeanUsed extends BaseEntity {
	private static final long serialVersionUID = -8688466927452414011L;

	/**
	 * 使用时间
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/**
	 * 关联会员
	 */
	@ManyToOne(targetEntity = Member.class)
	@JoinColumn(name = "member")
	private Member member;

	/**
	 * 使用数量
	 */
	@Column(name = "opt_value")
	private Integer optValue;

	/**
	 * 使用类型: 1: 订单消费; 2: 过期清理
	 */
	@Column(name = "type")
	private Integer type = 1;

	/**
	 * 关联订单：当类型为订单消费时, 需提供此字段
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(targetEntity = Order.class)
	@JoinColumn(name = "orders")
	private Order order;

	/**
	 * 备注
	 */
	@Column(name = "memo")
	private String memo;

	/**
	 * 消费来源
	 */
	@ElementCollection
	@CollectionTable(name = "tk_bean_used_origin", joinColumns = @JoinColumn(name = "used_id"))
	@MapKeyJoinColumn(name = "origin_id")
	@MapKeyClass(BeanRetain.class)
	@Column(name = "opt_value")
	private Map<BeanRetain, Integer> sources;

	public BeanUsed() {
		sources = new HashMap<>();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getOptValue() {
		return optValue;
	}

	public void setOptValue(Integer optValue) {
		this.optValue = optValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Map<BeanRetain, Integer> getSources() {
		return sources;
	}

	public void setSources(Map<BeanRetain, Integer> sources) {
		this.sources = sources;
	}

	/**
	 * 显示使用类型
	 * 
	 * @return
	 */
	public String getTypeView() {
		String view = "";
		switch (this.type) {
		case 1:
			view = "订单消费";
			break;
		case 2:
			view = "过期清理";
			break;
		}
		return view;
	}
}
