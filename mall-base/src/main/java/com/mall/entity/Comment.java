package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_comment")
public class Comment {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id; // id

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; // 创建时间

	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; // 修改时间

	@Column(name = "content")
	private String content; // 评论内容

	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "member")
	private Member member; // 所属会员

	@Column(name = "deleted")
	private boolean deleted; // 是否删除

	@Column(name = "score")
	private int score; // 评分

	@Column(name = "ip")
	private String ip; // ip

	@OneToOne(targetEntity = OrderItem.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "order_item")
	private OrderItem orderItem;// 关联订单项

	@ElementCollection(targetClass = String.class)
	@CollectionTable(name = "tb_comment_picture", joinColumns = @JoinColumn(name = "comments"))
	@Column(name = "imgs")
	private List<String> imgs;

	public Comment() {
		imgs = new ArrayList<String>();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

}
