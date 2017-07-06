package com.mall.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_feedback")
public class Feedback extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/**
	 * 修改时间
	 */
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	/**
	 * 返回类型: 默认为1
	 */
	@Column(name = "type")
	private int type = Type.functional;

	/**
	 * 详细描述
	 */
	@Column(name = "detail")
	private String detail;

	/**
	 * 关联会员
	 */
	@ManyToOne(targetEntity = Member.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "member")
	private Member member;

	/**
	 * 是否已查看
	 */
	@Column(name = "viewed")
	private boolean viewed = false;

	/**
	 * 处理反馈
	 */
	@Column(name = "response")
	private String response;

	/**
	 * 关联图片
	 */
	@ElementCollection(targetClass = String.class)
	@CollectionTable(name = "tb_feedback_images", joinColumns = @JoinColumn(name = "feedbacks"))
	@Column(name = "images")
	private Set<String> images = new HashSet<String>();

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	/**
	 * 反馈类型
	 * 
	 * @author ChenMingcai 2016-07-06
	 *
	 */
	public static final class Type {
		/**
		 * 功能异常
		 */
		public static final int functional = 1;

		/**
		 * 体验问题
		 */
		public static final int experience = 2;

		/**
		 * 优化建议
		 */
		public static final int optimize = 3;

		/**
		 * 其他问题
		 */
		public static final int other = 99;
	}

	public String getTypeView() {
		String view = "";

		switch (this.type) {
		case Type.functional:
			view = "功能异常";
			break;
		case Type.experience:
			view = "体验问题";
			break;
		case Type.optimize:
			view = "优化建议";
			break;
		case Type.other:
			view = "其他问题";
			break;
		}
		return view;
	}
}
