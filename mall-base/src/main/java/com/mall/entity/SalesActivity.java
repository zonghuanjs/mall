package com.mall.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 商城销售活动
 * 涉及促销打折抽奖等等活动 
 * 
 * @author shaoling.mi
 *
 */
@Entity
@Table(name="tb_sales_activity")
public class SalesActivity {

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
	private Date beginDate; //开始时间
	
	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate; //结束时间
	
	@Column(name = "title")
	private String title;//活动主题
	
	@Column(name = "thumbnail")
	private String thumbnail;//活动图片image的URL 全路径
	
	@Column(name = "url")
	private String url;//活动链接地址   全路径
	
	@Column(name = "type")
	private int type;//活动类型 1：减价 打折促销  2：节日促销  3：积分促销   4：赠送促销（买赠送其他商品）5：抽奖活动  6：限时限量抢购  7：众筹促销  8：答题竞猜促销  9：参与游戏促销

	@Column(name = "deleted")
	private boolean isDeleted;//是否已经删除   实际数据库记录未删除，只是字段变动    true：删除  false：未删除 
	
	@Transient
	private int status;//状态  1：未开始  2：进行中 3：活动结束 
	
	public SalesActivity(){
		
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public int getStatus() {
		
		//状态  1：未开始  2：进行中 3：活动结束 
		int s = 2;  
		Date now =  new Date();
		
		if(now.before(this.beginDate)){
			s = 1; 
		}else if(now.after(this.endDate)){
			s = 3; 
		}
		
		return s;
	}

	public String getActivityDesc(){
		
		String desc = "";
		//活动类型 1：减价 打折促销  2：节日促销  3：积分促销   4：买就送促销（买赠送其他商品）5：抽奖活动  6：限时限量抢购  7：众筹促销  8：答题竞猜促销  9：参与游戏促销
		switch(this.type){
		
		case 1:
			desc = "销价打折促销";
			break;
		case 2:
			desc = "节日促销";
			break;
		case 3:
			desc = "积分促销";
			break;
		case 4:
			desc = "买就送促销";
			break;
		case 5:
			desc = "抽奖活动";
			break;
		case 6:
			desc = "限时限量抢购";
			break;
		case 7:
			desc = "众筹促销";
			break;
		case 8:
			desc = "答题竞猜促销";
			break;
		case 9:
			desc = "参与游戏促销";
			break;
		}
		
		return desc;
	}
	
}
