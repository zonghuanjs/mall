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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tb_browsing_histories")
public class BrowsingHistory {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "access_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessDate; // 访问时间

	@Column(name = "leave_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveDate; // 离开时间

	@Column(name = "userIp")
	private String userIp;

	@JoinColumn(name = "member")
	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	private Member member;

	@Column(name = "type")
	private int type;// 1:商品 2：活动页 ...

	@JoinColumn(name = "product")
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private Product product;// 商品 对应type=1

	@JoinColumn(name = "activity")
	@ManyToOne(targetEntity = SalesActivity.class, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private SalesActivity activity;// 活动 对应type=2

	public BrowsingHistory() {

		this.accessDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SalesActivity getActivity() {
		return activity;
	}

	public void setActivity(SalesActivity activity) {
		this.activity = activity;
	}

}
