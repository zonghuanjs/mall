package com.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 商城消息实体类
 * 
 * 推送系统消息 优惠券  促销消息
 * 
 * @author shaoling.mi
 *
 */
@Entity
@Table(name = "tb_mall_message")
public class MallMessage {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate; //创建时间
	
	@Column(name = "modify_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate; //修改时间
	
	@Column(name = "begin_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate; //消息推送开始时间
	
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate; //消息推送结束时间
	
	@Column(name = "type")
	private int type;//消息类型: 1,系统消息; 2,优惠券消息; 3,促销消息
	
	@Column(name = "title")
	private String title;//消息标题 
	
	@Column(name = "conType")
	private int contentType;//消息内容类型    1, 文本; 2, 图片;
	
	@Column(name = "content")
	private String content;//消息内容  contentType=1时，对应的消息内容
	
	@Column(name = "imgURL")
	private String imgURL;//消息图片  contentType=2时，对应的图片资源URL
	
	@Column(name = "msgURL")
	private String msgURL;//
	
	@Column(name = "enabled")
	private boolean isEnabled;//立即启用
	
	@OneToMany(targetEntity=MemberRank.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="tb_message_rank",joinColumns=@JoinColumn(name="messages"),inverseJoinColumns=@JoinColumn(name="ranks"))
	private List<MemberRank> forRanks;//适用于会员等级
	
	public MallMessage(){
		forRanks = new ArrayList<MemberRank>();
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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getMsgURL() {
		return msgURL;
	}

	public void setMsgURL(String msgURL) {
		this.msgURL = msgURL;
	}

	public boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<MemberRank> getForRanks() {
		return forRanks;
	}

	public void setForRanks(List<MemberRank> forRanks) {
		this.forRanks = forRanks;
	}
	
}
	
	