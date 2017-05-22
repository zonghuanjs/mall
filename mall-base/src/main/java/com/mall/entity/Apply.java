package com.mall.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.mall.util.SpringUtil;

/**
 * @author Lijun
 * @version 2015年1月7日上午8:34:02
 */
@Entity
@Table(name="tb_apply")
public class Apply
{

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id; //申请单id
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	private Date createDate; //申请日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private Date modifyDate; //修改日期
	
	@Column(name="sn")
	private String sn; //申请流水号
	
	@Column(name="phone")
	private String phone; //联系电话
	
	@Column(name="contact")
	private String contact; //联系电话
	
	@ManyToOne(targetEntity=Area.class, fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area area; //区域
	
	@Column(name="address")
	private String address; //街道地址
	
	@Column(name="zipcode")
	private String zipCode; //邮编
	
	@Column(name="reason")
	private String reason; //理由
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="buy_date")
	private Date buyDate; //购买日期
	
	@ManyToOne(targetEntity=Member.class, fetch=FetchType.LAZY)
	@JoinColumn(name="member")
	private Member member; //所属会员
	
	@Column(name="checked")
	private boolean checked; //是否审核通过
	
	@Column(name="status")
	private int status; //申请状态: 0, 处理完成; 1, 未审核; 2, 审核通过; -1, 关闭
	
	@Column(name="description")
	private String description; //无用字段     暂时用以保存退货理由 0:7天无理由退货， 1：其他退货理由
	
	@Column(name="type")
	private int type; //申请类型
	
	@Column(name="invoice_image")
	private String invoiceImage; //发票照片
	
	@Column(name="product_image")
	private String productImage; //产品照片  以";"作分割的string
	
	@OneToMany(targetEntity=ApplyItem.class, mappedBy="apply", fetch=FetchType.LAZY)
	private List<ApplyItem> items;//关联商品项
	
	@ManyToOne(targetEntity=Order.class,fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "orders")
	private Order orders;
	
	@OneToOne(targetEntity=Return.class, mappedBy="apply", fetch=FetchType.LAZY)
	private Return returns; //关联return
	
	@OneToOne(targetEntity=Back.class, mappedBy="apply", fetch=FetchType.LAZY)
	private Back back;//关联寄回客户物流
	
	@OneToOne(targetEntity=Repair.class, mappedBy="apply", fetch=FetchType.LAZY)
	private Repair repair;//关联维修信息
	
	@ElementCollection(targetClass=String.class)
	@JoinTable(name="tb_apply_attributes", joinColumns=@JoinColumn(name="applys"))
	private Map<String, String> attributes;//其他属性表
	
	@OneToMany(targetEntity=ApplyLog.class, mappedBy="apply", fetch=FetchType.LAZY)
	@OrderBy("createDate desc, id desc")
	private List<ApplyLog> logs;//关联日志
	
	public Apply()
	{
		this.items = new LinkedList<>();
		this.attributes = new HashMap<>();
		this.logs = new LinkedList<>();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public String getSn()
	{
		return sn;
	}

	public void setSn(String sn)
	{
		this.sn = sn;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getContact()
	{
		return contact;
	}

	public void setContact(String contact)
	{
		this.contact = contact;
	}

	public Area getArea()
	{
		return area;
	}

	public void setArea(Area area)
	{
		this.area = area;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public Date getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(Date buyDate)
	{
		this.buyDate = buyDate;
	}

	public Member getMember()
	{
		return member;
	}

	public void setMember(Member member)
	{
		this.member = member;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public Return getReturns()
	{
		return returns;
	}

	public void setInvoiceImage(String invoiceImage)
	{
		this.invoiceImage = invoiceImage;
	}

	public String getProductImage()
	{
		return productImage;
	}

	public void setProductImage(String productImage)
	{
		this.productImage = productImage;
	}
	
	public List<ApplyItem> getItems()
	{
		return items;
	}

	public void setItems(List<ApplyItem> items)
	{
		this.items = items;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}
	
	public void setReturns(Return returns)
	{
		this.returns = returns;
	}

	public String getInvoiceImage()
	{
		return invoiceImage;
	}
	
	public Back getBack()
	{
		return back;
	}

	public void setBack(Back back)
	{
		this.back = back;
	}

	public Repair getRepair()
	{
		return repair;
	}

	public void setRepair(Repair repair)
	{
		this.repair = repair;
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<ApplyLog> getLogs()
	{
		return logs;
	}

	public void setLogs(List<ApplyLog> logs)
	{
		this.logs = logs;
	}

	/**
	 * 显示确认收货
	 * @return
	 */
	@Transient
	public boolean getConfirmRecieved()
	{
		return this.status == Status.sendBacked;
	}
	
	/**
	 * 显示退款
	 * @return
	 */
	@Transient
	public boolean getShowRefund()
	{
		return this.status == Status.received && this.type == Type.RETURN;
	}
	
	/**
	 * 是否显示审核按钮
	 * @return
	 */
	public boolean getShowCheckButton()
	{
		return !isChecked() && this.status == Status.unChecked;
	}
	
	/**
	 * 显示商品检测按钮
	 * 维修
	 * @return
	 */
	public boolean getShowProductCheckButtion()
	{
		boolean bShow = isChecked() && this.status == Status.received;
		//检测是否是维修申请
		return bShow && this.type == Type.REPAIR;
	}
	
	/**
	 * 显示同意商品换货按钮
	 * 换货
	 * @return
	 */
	public boolean getShowProductSwitchButton()
	{
		boolean pShow = isChecked() && this.status == Status.productChecking;
		//检测是否是换货申请
		return pShow && this.type == Type.SWICTH;
	}
	
	/**
	 * 显示检测报告填写按钮
	 * @return
	 */
	public boolean getShowFillReportButton()
	{
		boolean show = this.isChecked() && this.status == Status.repairChecking;
		if(this.getRepair() != null)
		{
			show = show && this.getRepair().getStatus() == Repair.Status.checking;
		}
		return show;
	}
	
	/**
	 * 显示维修按钮
	 * @return
	 */
	public boolean getShowRepairButton()
	{
		boolean show = this.isChecked() && this.status == Status.repairChecking && this.getRepair() != null;
		if(show)
		{			
			show = show && this.getRepair().getStatus() == Repair.Status.reporting;
			//是否付费维费
			if(show && this.getRepair().getAmount() > 0)
			{
				show = show && this.getRepairOrder() != null;
				if(show)
				{
					show = show && this.getRepairOrder().getOrderStatus() == Order.OrderStatus.FINSHED;
				}
			}
		}
		return show;
	}
	
	/**
	 * 显示维修完成按钮
	 * @return
	 */
	public boolean getShowRepairFinishButton()
	{
		boolean show = checked && this.status == Status.repairing;
		show = show && this.repair != null && repair.getStatus() == Repair.Status.processing;
		return show;
	}
	
	/**
	 * 显示公司确认收货按钮
	 * @return
	 */
	public boolean getShowConformRecievedButton()
	{
		return this.isChecked() && this.status == Status.sendBacked && this.getReturns() != null;
	}
	
	/**
	 * 显示发回用户按钮
	 * @return
	 */
	public boolean getShowShippingButton()
	{
		boolean show = this.checked && this.status != Status.unChecked && status != Status.checkfailure && back == null;
		switch(this.type)
		{
			case Type.REPAIR:
			{
				//免付维修
				if(show && repair != null)
				{
					//有检测报告
					if(repair.getReport() != null)
					{
						if(repair.getReport().getFree())
						{
							//免费维修
							show = show && status == Status.repairing && repair.getStatus() == Repair.Status.finished;
						}
						else
						{
							//取消维修
							if(repair.getStatus() == Repair.Status.closed)
							{
								show = show && status == Status.repairChecking;
							}
							else
							{
								//付费维修
								Order order = this.getRepairOrder();
								show = show && order != null && order.getPayment() != null;
								show = show && status == Status.repairing && repair.getStatus() == Repair.Status.finished;
							}
						}
						
					}
					else
					{
						//无检测报告
						show = false;
					}			
				}
				else
				{
					//没有维修记录
					show = false;
				}
				break;
			}
			case Type.SWICTH:
			{
				//换货
				if(show && returns != null)
				{
					show = show && status == Status.productChecked && back == null;
				}
				else
					show = false;
				break;
			}
		}
		return show;
	}
	
	/**
	 * 显示用户确认收货按钮
	 * @return
	 */
	public boolean getShowCustomRecievedButton()
	{
		return checked && status == Status.shipping && back != null;
	}
	
	/**
	 * 显示完成物品检测按钮
	 * @return
	 */
	public boolean getFinishProductCheckingButton()
	{
		boolean show = checked && status == Status.received && returns != null && back == null;
		return show;
	}
	
	/**
	 * 显示状态名称
	 * @return
	 */
	public String getStatusView()
	{
		String view = "";
		switch(this.status)
		{
			case Status.finished:
			{
				view = "已完成";
				break;
			}
			case Status.unChecked:
			{
				view = "未审核";
				break;
			}
			case Status.checked:
			{
				view = "审核通过";
				break;
			}
			case Status.checkfailure:
			{
				view = "审核未通过";
				break;
			}
			case Status.closed:
			{
				view = "已关闭";
				break;
			}
			case Status.sendBacked:
			{
				view = "用户寄货";
				break;
			}
			case Status.received:
			{
				view = "已收到用户寄货";
				break;
			}
			case Status.repairChecking:
			{
				view = "故障检测";
				break;
			}
			case Status.repairing:
			{
				view = "维修";
				break;
			}
			case Status.shipping:
			{
				view = "发回用户";
				break;
			}
			case Status.shippingRecieved:
			{
				view = "用户确认收货";
				break;
			}
			case Status.productChecking:
			{
				view = "商品检测";
				break;
			}
			case Status.productChecked:
			{
				view = "物品检测完成";
				break;
			}
		}
		return view;
	}

	/**
	 * 显示售后申请类型
	 * @return
	 */
	public String getTypeView()
	{
		String view = "";
		switch(this.type)
		{
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
	
	/**
	 * 获取维修订单
	 * @return
	 */
	public Order getRepairOrder()
	{
		Order order = null;
		if(this.attributes.get(Key.repairOrder) != null)
		{
			/*OrderService service = (OrderService)SpringUtil.getBean("orderServiceImpl");
			try
			{
				Long orderId = Long.valueOf(this.attributes.get(Key.repairOrder));
				order = service.get(orderId);
			}
			catch(Exception ex)
			{
				
			}*/
		}
		return order;
	}
	
	/**
	 * 审请单状态
	 * @author ChenMingcai
	 *
	 */
	public static final class Status
	{
		public static final int finished = 0;//已完成
		public static final int unChecked = 1;//未审核
		public static final int checked = 2;//审核通过
		public static final int checkfailure = 3;//审核不通过
		public static final int closed = -1;//关闭
		public static final int sendBacked = 4; //用户寄货
		public static final int received = 5; //确认收货
		public static final int repairChecking = 6;//故障检测
		public static final int repairing = 7;//维修
		public static final int shipping = 8;//发回用户
		public static final int shippingRecieved = 9;//用户确认收货
		public static final int productChecking = 10;//换货 物品检测
		public static final int productChecked = 11;//换货 物品检测完成 
	}
	
	/**
	 * 审请单类型
	 * @author ChenMingcai
	 *
	 */
	public static final class Type
	{
		public static final int RETURN = 1;//退货
		public static final int SWICTH = 2;//换货
		public static final int REPAIR = 3;//维修
	}
	
	/**
	 * 售后属性Key
	 * @author ChenMingcai
	 *
	 */
	public static final class Key
	{
		public static final String repairOrder = "repair_order";//支付订单
		public static final String switched = "switched";//是否换货, "true", 换货; "false", 原货退回
	}

}
