package cn.tekism.mall.bean;

import java.io.Serializable;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 
 * @author ChenMingcai
 * 2014-09-26
 *
 */

public class SystemConfig implements Serializable
{	
	private static final long serialVersionUID = 1L;

	//小米位精确方式(四舍五入，向上取整，向下取整)
	public enum RoundType
	{
		roundHalfUp, roundUp, roundDown
	}
	
	public static final String ADMIN_LOGIN_KEY = "admin_key";//管理员登录信息
	public static final String USER_PERMISSION_KEY = "authorities_key";//管理员权限信息
	public static final String MEMBER_KEY = "member_key";//登录key
	public static final String AutoLogin = "tekism_aolo";//自动登录key 
	public static final String UPLOAD_IMAGE_DIR = "/upload/images/";//图片上传目录
	public static final String UPLOAD_MEDIA_DIR = "/upload/media/";//媒体文件上传目录
	public static final String UPLOAD_FILE_DIR = "/upload/file/";//其他文件上传目录
	
	/**
	 * 网站名称
	 */
	private String siteName;
	
	/**
	 * 系统版本
	 */
	private String version;
	
	/**
	 * 网站url
	 */
	private String siteUrl;
	
	/**
	 * 网站logo
	 */
	private String logo;
	
	/**
	 * 热门搜索关键字 
	 */
	private String hotSearch;
	
	private String address;//地址
	private String phone;//热线电话
	private String zipCode;//邮编
	private String sinaWeiBo;//新浪微博
	private String weiXin;//微信
	private String email;//邮件地址
	private String certtext;//备案号
	
	private boolean siteEnabled;//网站是否启动
	private String siteClosedMessage;//网站关闭显示信息
	
	/**
	 * 系统顶部黄色通知
	 */
	private boolean notifyEnabled = false;
	
	/**
	 * 系统通知内容
	 */
	private String notifyInfo = "";
	
	private int largeProductImageWidth;//商品大图宽度
	private int largeProductImageHeight;//商品大图高度
	private int mediumProductImageWidth;//商品中图宽度
	private int mediumProductImageHeight;//商品中图高度
	private int thumbnailProductImageWidth;//商品缩略图宽度
	private int thumbnailProductImageHeight;//商品缩略图高度
	private String defaultlargeProductImage;//默认商品大图
	private String defaultMediumProductImage;//默认商品中图
	private String defaultThumbnailProductImage;//默认商品缩略图 
	private int salesShowCount;//商品销量榜显示数量
	
	/**
	 * 商品搜索页大小: 默认为20
	 */
	private int searchPageSize = 20;
	
	private int priceScale;//价格精准度
	private RoundType priceRoundType;//价格精准方式
	private boolean showMarketPrice;//是否显示市场价
	
	/**
	 * 分类列表配置
	 */
	private String homeCategorySetting;

	/**
	 * 注册与安全配置内容
	 */
	
	/**
	 * 是否开放注册
	 */
	private boolean registerEnabled;
	private Long registerCoupon;//注册赠送优惠券
	private String disabledUsername;//禁用用户名
	private int userNameMinLength;//用户名最小长度
	private int userNameMaxLength;//用户名最大长度
	private int passwordMinLength;//密码最小长度
	private int passwordMaxLength;//密码最大长度
	private int registerPoint;//注册初始积分
	private String registerAgreement;//注册协议 
	
	private boolean emailLogin;//是否允许email登陆
	private int accountLockCount;//账户锁定次数
	private int accountLockTime; //账户锁定时间，单位分钟
	
	/**
	 * 安全登录：默认开启
	 */
	private boolean securityLogin = true;
	
	private double fixedFreight;//固定运费
	private double freightLimitation;//运费阈值
	
	/**
	 * 上传文件大小限制, 单位MB: 0表示未设置, -1表示不限制
	 */
	private int uploadMaxSize = 10;
	
	/**
	 * 上传图片扩展名
	 */
	private String uploadImageExtension;
	
	/**
	 * 上传视频扩展名
	 */
	private String uploadMediaExtension;
	
	/**
	 * 上传其他文件扩展名
	 */
	private String uploadFileExtension;
	
	private String smtpFromMail;//发件人邮箱
	private String smtpHost;//SMTP服务器地址
	private int smtpPort;//SMTP服务器端口
	private String smtpUsername;//SMTP用户名
	private String smtpPassword;//SMTP密码
	
	/**
	 * 邮件通知设置
	 */
	private String paymentRecievedNofityEmail;//支付到账审核邮箱
	private String orderNotifyEmail;//订单通知邮箱
	private String refundNotifyEmail;//退款通知邮箱
	private String applyCheckNotifyEmail;//售后审核通知
	private String returnNotifyEmail;//客户回寄收货通知
	private String repairCheckNofiyEmail;//商品检测通知
	private String doRefundNotifyMail;//执行退款通知邮件
	
	private String currencySign;//货币符号 
	private String currencyUnit;//货币单位
	private int stockAlertCount;//库存报警数量
	
	/**
	 * 功能开关
	 */
	private boolean reviewEnabled;//是否启用评论
	private boolean serviceEnable;//是否启用客服
	private boolean invoiceEnabled;//是否启用发票
	private boolean thirdLoginEnable;//是否启用第三方登录
	private boolean salesAfterEnable;//是否启用售后功能
	private boolean statisticsEnable;//是否启用访问统计
	
	private boolean debugable;//调试模式
	
	private String statisticAppKey;//统计应用授权Key
	
	private String k100Key;//快递100授权KEY
	private String jobTime;//上班时间
	private int safeKeyExpiryTime;//安全Key过期时间, 单位:分钟
	private int orderExpiredTime;//订单过期时间
	private String ccServiceId;//CC客服网站ID
	
	/**
	 * 购物回馈比例，例如：消费1元可获芯豆1个， 此时比例为1
	 */
	private double feedbackScale=1;
	
	/**
	 * 芯豆抵实际支付的比例，例如：100芯豆可抵1元现金， 此时比例为0.01
	 */
	private double beanScale=0.01;
	
	/**
	 * 芯豆过期时间，以分钟为单位 ， 默认一年
	 */
	private int beanExpiredTime=525600;
	
	/**
	 * 芯豆只评价订单赠送个数
	 */
	private int beanComment=1;
	
	/**
	 * 芯豆评价订单并晒图赠送个数
	 */
	private int beanComImage=2;
	
	/**
	 * 芯豆支付不得超过每笔订单结算金额的比例：0.3
	 */
	private double beanOrderScale=0.3;
	
	/**
	 * 广告位设置
	 */
	private Long homeAdPosition1;//首页1号广告位  （首页顶部循环滚动的广告位:	）
	private Long homeAdPosition2;//首页2号广告位  （首页大banner右侧的广告位:）
	private Long homeAdPosition3;//首页3号广告位  （首页大banner下左一）
	private Long homeAdPosition4;//首页4号广告位   （首页大banner下左二）
	private Long homeAdPosition5;//首页5号广告位   （首页大banner下左三）
	private Long homeAdPosition6;//首页6号广告位    （首页大banner下左四）
	private Long homeAdPosition7;//首页7号广告位    （个人中心右上）
	private Long homeAdPosition8;//首页8号广告位    （个人中心右下）
	private Long homeAdPosition9;//首页9号广告位     （商品详情页左下）
	private String homeAdPositionList; //首页滚动列表广告位   （首页中间分类列表广告位）
	private Long productAdPosition1; //商品详情页新品1号广告位
	private Long hotAdPosition; //个人中心页热门商品广告位
	
	/**
	 * 商城首页最顶部的横幅广告位
	 */
	private Long homeADTopmost;
	/**
	 * 首页固态硬盘广告栏
	 */
	private Long solidStateDisk;
	/**
	 * 首页内存条广告栏
	 */
	private Long memoryBank;
	/**
	 * 首页移动存储广告栏
	 */
	private Long removableStorage;
	/**
	 * 首页数码配件广告栏
	 */
	private Long digitalAccessories;
	
	/**
	 * 首页分类列表配置
	 */
	private Long categoryAdPos;
	
	/**
	 * 获取分类列表
	 * @return 返回分类菜单列表['name', 'id']
	 */
	public String[][] getCategoryList()
	{
		if(homeCategorySetting == null || "".equals(homeCategorySetting))
		{
			return null;
		}
		
		String[][] categorys = {};
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource source = new InputSource(new StringReader(homeCategorySetting));
			Document document = builder.parse(source);
			NodeList list = document.getElementsByTagName("category");
			int length = list.getLength();
			if(length > 0)
			{
				categorys = new String[length][3];
				for(int i = 0; i < length; i++)
				{
					Node node = list.item(i);
					if(node instanceof Element)
					{
						Element category = (Element)node;
						categorys[i][0] = category.getAttribute("name");
						categorys[i][1] = category.getAttribute("id");
						categorys[i][2] = category.getAttribute("ico");
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		return categorys;
	}
	
	/**
	 * @return the currencySign
	 */
	public String getCurrencySign()
	{
		return currencySign;
	}

	/**
	 * @param currencySign the currencySign to set
	 */
	public void setCurrencySign(String currencySign)
	{
		this.currencySign = currencySign;
	}

	/**
	 * @return the currencyUnit
	 */
	public String getCurrencyUnit()
	{
		return currencyUnit;
	}

	/**
	 * @param currencyUnit the currencyUnit to set
	 */
	public void setCurrencyUnit(String currencyUnit)
	{
		this.currencyUnit = currencyUnit;
	}

	/**
	 * @return the stockAlertCount
	 */
	public int getStockAlertCount()
	{
		return stockAlertCount;
	}

	/**
	 * @param stockAlertCount the stockAlertCount to set
	 */
	public void setStockAlertCount(int stockAlertCount)
	{
		this.stockAlertCount = stockAlertCount;
	}

	/**
	 * @return the uploadMaxSize
	 */
	public int getUploadMaxSize()
	{
		return uploadMaxSize;
	}

	/**
	 * @param uploadMaxSize the uploadMaxSize to set
	 */
	public void setUploadMaxSize(int uploadMaxSize)
	{
		this.uploadMaxSize = uploadMaxSize;
	}

	/**
	 * @return the uploadImageExtension
	 */
	public String getUploadImageExtension()
	{
		return uploadImageExtension;
	}

	/**
	 * @param uploadImageExtension the uploadImageExtension to set
	 */
	public void setUploadImageExtension(String uploadImageExtension)
	{
		this.uploadImageExtension = uploadImageExtension;
	}

	/**
	 * @return the uploadMediaExtension
	 */
	public String getUploadMediaExtension()
	{
		return uploadMediaExtension;
	}

	/**
	 * @param uploadMediaExtension the uploadMediaExtension to set
	 */
	public void setUploadMediaExtension(String uploadMediaExtension)
	{
		this.uploadMediaExtension = uploadMediaExtension;
	}

	/**
	 * @return the uploadFileExtension
	 */
	public String getUploadFileExtension()
	{
		return uploadFileExtension;
	}

	/**
	 * @param uploadFileExtension the uploadFileExtension to set
	 */
	public void setUploadFileExtension(String uploadFileExtension)
	{
		this.uploadFileExtension = uploadFileExtension;
	}

	/**
	 * @return the smtpFromMail
	 */
	public String getSmtpFromMail()
	{
		return smtpFromMail;
	}

	/**
	 * @param smtpFromMail the smtpFromMail to set
	 */
	public void setSmtpFromMail(String smtpFromMail)
	{
		this.smtpFromMail = smtpFromMail;
	}

	/**
	 * @return the smtpUsername
	 */
	public String getSmtpUsername()
	{
		return smtpUsername;
	}

	/**
	 * @param smtpUsername the smtpUsername to set
	 */
	public void setSmtpUsername(String smtpUsername)
	{
		this.smtpUsername = smtpUsername;
	}

	/**
	 * @return the smtpPassword
	 */
	public String getSmtpPassword()
	{
		return smtpPassword;
	}

	/**
	 * @param smtpPassword the smtpPassword to set
	 */
	public void setSmtpPassword(String smtpPassword)
	{
		this.smtpPassword = smtpPassword;
	}

	/**
	 * @return the siteName
	 */
	public String getSiteName()
	{
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl()
	{
		return siteUrl;
	}

	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl)
	{
		this.siteUrl = siteUrl;
	}

	/**
	 * @return the logo
	 */
	public String getLogo()
	{
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo)
	{
		this.logo = logo;
	}

	/**
	 * @return the hotSearch
	 */
	public String getHotSearch()
	{
		return hotSearch;
	}

	/**
	 * @param hotSearch the hotSearch to set
	 */
	public void setHotSearch(String hotSearch)
	{
		this.hotSearch = hotSearch;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the certtext
	 */
	public String getCerttext()
	{
		return certtext;
	}

	/**
	 * @param certtext the certtext to set
	 */
	public void setCerttext(String certtext)
	{
		this.certtext = certtext;
	}

	/**
	 * @return the siteEnabled
	 */
	public boolean getSiteEnabled()
	{
		return siteEnabled;
	}

	/**
	 * @param siteEnabled the siteEnabled to set
	 */
	public void setSiteEnabled(boolean siteEnabled)
	{
		this.siteEnabled = siteEnabled;
	}

	/**
	 * @return the siteClosedMessage
	 */
	public String getSiteClosedMessage()
	{
		return siteClosedMessage;
	}

	/**
	 * @param siteClosedMessage the siteClosedMessage to set
	 */
	public void setSiteClosedMessage(String siteClosedMessage)
	{
		this.siteClosedMessage = siteClosedMessage;
	}

	public boolean isNotifyEnabled()
	{
		return notifyEnabled;
	}

	public void setNotifyEnabled(boolean notifyEnabled)
	{
		this.notifyEnabled = notifyEnabled;
	}

	public String getNotifyInfo()
	{
		return notifyInfo;
	}

	public void setNotifyInfo(String notifyInfo)
	{
		this.notifyInfo = notifyInfo;
	}

	/**
	 * @return the largeProductImageWidth
	 */
	public int getLargeProductImageWidth()
	{
		return largeProductImageWidth;
	}

	/**
	 * @param largeProductImageWidth the largeProductImageWidth to set
	 */
	public void setLargeProductImageWidth(int largeProductImageWidth)
	{
		this.largeProductImageWidth = largeProductImageWidth;
	}

	/**
	 * @return the largeProductImageHeight
	 */
	public int getLargeProductImageHeight()
	{
		return largeProductImageHeight;
	}

	/**
	 * @param largeProductImageHeight the largeProductImageHeight to set
	 */
	public void setLargeProductImageHeight(int largeProductImageHeight)
	{
		this.largeProductImageHeight = largeProductImageHeight;
	}

	/**
	 * @return the mediumProductImageWidth
	 */
	public int getMediumProductImageWidth()
	{
		return mediumProductImageWidth;
	}

	/**
	 * @param mediumProductImageWidth the mediumProductImageWidth to set
	 */
	public void setMediumProductImageWidth(int mediumProductImageWidth)
	{
		this.mediumProductImageWidth = mediumProductImageWidth;
	}

	/**
	 * @return the mediumProductImageHeight
	 */
	public int getMediumProductImageHeight()
	{
		return mediumProductImageHeight;
	}

	/**
	 * @param mediumProductImageHeight the mediumProductImageHeight to set
	 */
	public void setMediumProductImageHeight(int mediumProductImageHeight)
	{
		this.mediumProductImageHeight = mediumProductImageHeight;
	}

	/**
	 * @return the thumbnailProductImageWidth
	 */
	public int getThumbnailProductImageWidth()
	{
		return thumbnailProductImageWidth;
	}

	/**
	 * @param thumbnailProductImageWidth the thumbnailProductImageWidth to set
	 */
	public void setThumbnailProductImageWidth(int thumbnailProductImageWidth)
	{
		this.thumbnailProductImageWidth = thumbnailProductImageWidth;
	}

	/**
	 * @return the thumbnailProductImageHeight
	 */
	public int getThumbnailProductImageHeight()
	{
		return thumbnailProductImageHeight;
	}

	/**
	 * @param thumbnailProductImageHeight the thumbnailProductImageHeight to set
	 */
	public void setThumbnailProductImageHeight(int thumbnailProductImageHeight)
	{
		this.thumbnailProductImageHeight = thumbnailProductImageHeight;
	}

	/**
	 * @return the defaultlargeProductImage
	 */
	public String getDefaultlargeProductImage()
	{
		return defaultlargeProductImage;
	}

	/**
	 * @param defaultlargeProductImage the defaultlargeProductImage to set
	 */
	public void setDefaultlargeProductImage(String defaultlargeProductImage)
	{
		this.defaultlargeProductImage = defaultlargeProductImage;
	}

	/**
	 * @return the defaultMediumProductImage
	 */
	public String getDefaultMediumProductImage()
	{
		return defaultMediumProductImage;
	}

	/**
	 * @param defaultMediumProductImage the defaultMediumProductImage to set
	 */
	public void setDefaultMediumProductImage(String defaultMediumProductImage)
	{
		this.defaultMediumProductImage = defaultMediumProductImage;
	}

	/**
	 * @return the defaultThumbnailProductImage
	 */
	public String getDefaultThumbnailProductImage()
	{
		return defaultThumbnailProductImage;
	}

	/**
	 * @param defaultThumbnailProductImage the defaultThumbnailProductImage to set
	 */
	public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage)
	{
		this.defaultThumbnailProductImage = defaultThumbnailProductImage;
	}

	/**
	 * @return the priceScale
	 */
	public int getPriceScale()
	{
		return priceScale;
	}

	/**
	 * @param priceScale the priceScale to set
	 */
	public void setPriceScale(int priceScale)
	{
		this.priceScale = priceScale;
	}

	/**
	 * @return the priceRoundType
	 */
	public RoundType getPriceRoundType()
	{
		return priceRoundType;
	}

	/**
	 * @param priceRoundType the priceRoundType to set
	 */
	public void setPriceRoundType(RoundType priceRoundType)
	{
		this.priceRoundType = priceRoundType;
	}

	/**
	 * @return the showMarketPrice
	 */
	public boolean isShowMarketPrice()
	{
		return showMarketPrice;
	}

	/**
	 * @param showMarketPrice the showMarketPrice to set
	 */
	public void setShowMarketPrice(boolean showMarketPrice)
	{
		this.showMarketPrice = showMarketPrice;
	}

	public String getHomeCategorySetting()
	{
		return homeCategorySetting;
	}

	public void setHomeCategorySetting(String homeCateogrySetting)
	{
		this.homeCategorySetting = homeCateogrySetting;
	}

	/**
	 * @return the registerEnabled
	 */
	public boolean isRegisterEnabled()
	{
		return registerEnabled;
	}

	/**
	 * @param registerEnabled the registerEnabled to set
	 */
	public void setRegisterEnabled(boolean registerEnabled)
	{
		this.registerEnabled = registerEnabled;
	}

	/**
	 * @return the duplicateEmail
	 */
	public Long getRegisterCoupon()
	{
		return registerCoupon;
	}

	/**
	 * @param duplicateEmail the duplicateEmail to set
	 */
	public void setRegisterCoupon(Long registerCoupon)
	{
		this.registerCoupon = registerCoupon;
	}

	/**
	 * @return the disabledUsername
	 */
	public String getDisabledUsername()
	{
		return disabledUsername;
	}

	/**
	 * @param disabledUsername the disabledUsername to set
	 */
	public void setDisabledUsername(String disabledUsername)
	{
		this.disabledUsername = disabledUsername;
	}

	/**
	 * @return the userNameMinLength
	 */
	public int getUserNameMinLength()
	{
		return userNameMinLength;
	}

	/**
	 * @param userNameMinLength the userNameMinLength to set
	 */
	public void setUserNameMinLength(int userNameMinLength)
	{
		this.userNameMinLength = userNameMinLength;
	}

	/**
	 * @return the userNameMaxLength
	 */
	public int getUserNameMaxLength()
	{
		return userNameMaxLength;
	}

	/**
	 * @param userNameMaxLength the userNameMaxLength to set
	 */
	public void setUserNameMaxLength(int userNameMaxLength)
	{
		this.userNameMaxLength = userNameMaxLength;
	}

	/**
	 * @return the passwordMinLength
	 */
	public int getPasswordMinLength()
	{
		return passwordMinLength;
	}

	/**
	 * @param passwordMinLength the passwordMinLength to set
	 */
	public void setPasswordMinLength(int passwordMinLength)
	{
		this.passwordMinLength = passwordMinLength;
	}

	/**
	 * @return the passwordMaxLength
	 */
	public int getPasswordMaxLength()
	{
		return passwordMaxLength;
	}

	/**
	 * @param passwordMaxLength the passwordMaxLength to set
	 */
	public void setPasswordMaxLength(int passwordMaxLength)
	{
		this.passwordMaxLength = passwordMaxLength;
	}

	/**
	 * @return the registerPoint
	 */
	public int getRegisterPoint()
	{
		return registerPoint;
	}

	/**
	 * @param registerPoint the registerPoint to set
	 */
	public void setRegisterPoint(int registerPoint)
	{
		this.registerPoint = registerPoint;
	}

	/**
	 * @return the registerAgreement
	 */
	public String getRegisterAgreement()
	{
		return registerAgreement;
	}

	/**
	 * @param registerAgreement the registerAgreement to set
	 */
	public void setRegisterAgreement(String registerAgreement)
	{
		this.registerAgreement = registerAgreement;
	}

	/**
	 * @return the emailLogin
	 */
	public boolean isEmailLogin()
	{
		return emailLogin;
	}

	/**
	 * @param emailLogin the emailLogin to set
	 */
	public void setEmailLogin(boolean emailLogin)
	{
		this.emailLogin = emailLogin;
	}

	/**
	 * @return the accountLockCount
	 */
	public int getAccountLockCount()
	{
		return accountLockCount;
	}

	/**
	 * @param accountLockCount the accountLockCount to set
	 */
	public void setAccountLockCount(int accountLockCount)
	{
		this.accountLockCount = accountLockCount;
	}

	public boolean getSecurityLogin()
	{
		return securityLogin;
	}

	public void setSecurityLogin(boolean securityLogin)
	{
		this.securityLogin = securityLogin;
	}

	/**
	 * @return the accountLockTime
	 */
	public int getAccountLockTime()
	{
		return accountLockTime;
	}

	/**
	 * @param accountLockTime the accountLockTime to set
	 */
	public void setAccountLockTime(int accountLockTime)
	{
		this.accountLockTime = accountLockTime;
	}

	public String getSmtpHost()
	{
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost)
	{
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort()
	{
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort)
	{
		this.smtpPort = smtpPort;
	}

	public String getK100Key()
	{
		return k100Key;
	}

	public void setK100Key(String k100Key)
	{
		this.k100Key = k100Key;
	}

	public String getJobTime()
	{
		return jobTime;
	}

	public void setJobTime(String jobTime)
	{
		this.jobTime = jobTime;
	}
	
	/**
	 * 获取系统邮件配置
	 * @return
	 */
	public MailConfig getMailConfig()
	{
		MailConfig config = new MailConfig();
		config.setSmtpHost(this.getSmtpHost());
		config.setSmtpPassword(this.getSmtpPassword());
		config.setSmtpPort(this.getSmtpPort());
		config.setSmtpUsername(this.getSmtpUsername());
		return config;
	}

	public double getFixedFreight()
	{
		return fixedFreight;
	}

	public void setFixedFreight(double fixedFreight)
	{
		this.fixedFreight = fixedFreight;
	}

	public double getFreightLimitation()
	{
		return freightLimitation;
	}

	public void setFreightLimitation(double freightLimitation)
	{
		this.freightLimitation = freightLimitation;
	}

	public boolean isReviewEnabled()
	{
		return reviewEnabled;
	}

	public void setReviewEnabled(boolean reviewEnabled)
	{
		this.reviewEnabled = reviewEnabled;
	}

	public boolean isServiceEnable()
	{
		return serviceEnable;
	}

	public void setServiceEnable(boolean serviceEnable)
	{
		this.serviceEnable = serviceEnable;
	}

	public int getSafeKeyExpiryTime()
	{
		return safeKeyExpiryTime;
	}

	public void setSafeKeyExpiryTime(int safeKeyExpiryTime)
	{
		this.safeKeyExpiryTime = safeKeyExpiryTime;
	}

	public int getSalesShowCount()
	{
		return salesShowCount;
	}

	public void setSalesShowCount(int salesShowCount)
	{
		this.salesShowCount = salesShowCount;
	}

	public int getSearchPageSize()
	{
		return searchPageSize;
	}

	public void setSearchPageSize(int searchPageSize)
	{
		this.searchPageSize = searchPageSize;
	}

	public boolean isInvoiceEnabled()
	{
		return invoiceEnabled;
	}

	public void setInvoiceEnabled(boolean invoiceEnabled)
	{
		this.invoiceEnabled = invoiceEnabled;
	}

	public String getSinaWeiBo()
	{
		return sinaWeiBo;
	}

	public void setSinaWeiBo(String sinaWeiBo)
	{
		this.sinaWeiBo = sinaWeiBo;
	}

	public String getWeiXin()
	{
		return weiXin;
	}

	public void setWeiXin(String weiXin)
	{
		this.weiXin = weiXin;
	}

	public int getOrderExpiredTime()
	{
		return orderExpiredTime;
	}

	public void setOrderExpiredTime(int orderExpiredTime)
	{
		this.orderExpiredTime = orderExpiredTime;
	}

	public boolean isThirdLoginEnable()
	{
		return thirdLoginEnable;
	}

	public void setThirdLoginEnable(boolean thirdLoginEnable)
	{
		this.thirdLoginEnable = thirdLoginEnable;
	}

	public boolean isSalesAfterEnable()
	{
		return salesAfterEnable;
	}

	public void setSalesAfterEnable(boolean salesAfterEnable)
	{
		this.salesAfterEnable = salesAfterEnable;
	}

	public String getOrderNotifyEmail()
	{
		return orderNotifyEmail;
	}

	public void setOrderNotifyEmail(String orderNotifyEmail)
	{
		this.orderNotifyEmail = orderNotifyEmail;
	}

	public String getPaymentRecievedNofityEmail()
	{
		return paymentRecievedNofityEmail;
	}

	public void setPaymentRecievedNofityEmail(String paymentRecievedNofityEmail)
	{
		this.paymentRecievedNofityEmail = paymentRecievedNofityEmail;
	}

	public String getRefundNotifyEmail()
	{
		return refundNotifyEmail;
	}

	public void setRefundNotifyEmail(String refundNotifyEmail)
	{
		this.refundNotifyEmail = refundNotifyEmail;
	}

	public String getDoRefundNotifyMail()
	{
		return doRefundNotifyMail;
	}

	public void setDoRefundNotifyMail(String doRefundNotifyMail)
	{
		this.doRefundNotifyMail = doRefundNotifyMail;
	}

	public Long getHomeAdPosition1()
	{
		return homeAdPosition1;
	}

	public void setHomeAdPosition1(Long homeAdPosition1)
	{
		this.homeAdPosition1 = homeAdPosition1;
	}

	public Long getHomeAdPosition2()
	{
		return homeAdPosition2;
	}

	public void setHomeAdPosition2(Long homeAdPosition2)
	{
		this.homeAdPosition2 = homeAdPosition2;
	}

	public Long getHomeAdPosition3()
	{
		return homeAdPosition3;
	}

	public void setHomeAdPosition3(Long homeAdPosition3)
	{
		this.homeAdPosition3 = homeAdPosition3;
	}

	public Long getHomeAdPosition4()
	{
		return homeAdPosition4;
	}

	public void setHomeAdPosition4(Long homeAdPosition4)
	{
		this.homeAdPosition4 = homeAdPosition4;
	}

	public Long getHomeAdPosition5()
	{
		return homeAdPosition5;
	}

	public void setHomeAdPosition5(Long homeAdPosition5)
	{
		this.homeAdPosition5 = homeAdPosition5;
	}

	public Long getHomeAdPosition6()
	{
		return homeAdPosition6;
	}

	public void setHomeAdPosition6(Long homeAdPosition6)
	{
		this.homeAdPosition6 = homeAdPosition6;
	}

	public Long getHomeAdPosition7()
	{
		return homeAdPosition7;
	}

	public void setHomeAdPosition7(Long homeAdPosition7)
	{
		this.homeAdPosition7 = homeAdPosition7;
	}

	public Long getHomeAdPosition8()
	{
		return homeAdPosition8;
	}

	public void setHomeAdPosition8(Long homeAdPosition8)
	{
		this.homeAdPosition8 = homeAdPosition8;
	}

	public Long getHomeAdPosition9()
	{
		return homeAdPosition9;
	}

	public void setHomeAdPosition9(Long homeAdPosition9)
	{
		this.homeAdPosition9 = homeAdPosition9;
	}

	public Long getProductAdPosition1()
	{
		return productAdPosition1;
	}

	public void setProductAdPosition1(Long productAdPosition1)
	{
		this.productAdPosition1 = productAdPosition1;
	}

	public Long getHotAdPosition()
	{
		return hotAdPosition;
	}

	public void setHotAdPosition(Long hotAdPosition)
	{
		this.hotAdPosition = hotAdPosition;
	}

	public String getCcServiceId()
	{
		return ccServiceId;
	}

	public void setCcServiceId(String ccServiceId)
	{
		this.ccServiceId = ccServiceId;
	}

	public boolean isStatisticsEnable()
	{
		return statisticsEnable;
	}

	public void setStatisticsEnable(boolean statisticsEnable)
	{
		this.statisticsEnable = statisticsEnable;
	}

	public String getStatisticAppKey()
	{
		return statisticAppKey;
	}

	public void setStatisticAppKey(String statisticAppKey)
	{
		this.statisticAppKey = statisticAppKey;
	}

	public String getApplyCheckNotifyEmail()
	{
		return applyCheckNotifyEmail;
	}

	public void setApplyCheckNotifyEmail(String applyCheckNotifyEmail)
	{
		this.applyCheckNotifyEmail = applyCheckNotifyEmail;
	}

	public String getReturnNotifyEmail()
	{
		return returnNotifyEmail;
	}

	public void setReturnNotifyEmail(String returnNotifyEmail)
	{
		this.returnNotifyEmail = returnNotifyEmail;
	}

	public String getRepairCheckNofiyEmail()
	{
		return repairCheckNofiyEmail;
	}

	public void setRepairCheckNofiyEmail(String repairCheckNofiyEmail)
	{
		this.repairCheckNofiyEmail = repairCheckNofiyEmail;
	}

	public boolean isDebugable()
	{
		return debugable;
	}

	public void setDebugable(boolean debugable)
	{
		this.debugable = debugable;
	}
	
	public String getHomeAdPositionList() {
		return homeAdPositionList;
	}

	public void setHomeAdPositionList(String homeAdPositionList) {
		this.homeAdPositionList = homeAdPositionList;
	}

	public double getBeanScale() {
		return beanScale;
	}

	public void setBeanScale(double beanScale) {
		this.beanScale = beanScale;
	}

	public int getBeanExpiredTime() {
		return beanExpiredTime;
	}

	public void setBeanExpiredTime(int beanExpiredTime) {
		this.beanExpiredTime = beanExpiredTime;
	}

	public double getFeedbackScale() {
		return feedbackScale;
	}

	public void setFeedbackScale(double feedbackScale) {
		this.feedbackScale = feedbackScale;
	}

	public int getBeanComment() {
		return beanComment;
	}

	public void setBeanComment(int beanComment) {
		this.beanComment = beanComment;
	}

	public int getBeanComImage() {
		return beanComImage;
	}

	public void setBeanComImage(int beanComImage) {
		this.beanComImage = beanComImage;
	}

	public double getBeanOrderScale() {
		return beanOrderScale;
	}

	public void setBeanOrderScale(double beanOrderScale) {
		this.beanOrderScale = beanOrderScale;
	}

	public Long getSolidStateDisk() {
		return solidStateDisk;
	}

	public void setSolidStateDisk(Long solidStateDisk) {
		this.solidStateDisk = solidStateDisk;
	}

	public Long getMemoryBank() {
		return memoryBank;
	}

	public void setMemoryBank(Long memoryBank) {
		this.memoryBank = memoryBank;
	}

	public Long getRemovableStorage() {
		return removableStorage;
	}

	public void setRemovableStorage(Long removableStorage) {
		this.removableStorage = removableStorage;
	}

	public Long getDigitalAccessories() {
		return digitalAccessories;
	}

	public void setDigitalAccessories(Long digitalAccessories) {
		this.digitalAccessories = digitalAccessories;
	}

	public Long getCategoryAdPos() {
		return categoryAdPos;
	}

	public void setCategoryAdPos(Long categoryAdPos) {
		this.categoryAdPos = categoryAdPos;
	}

	public Long getHomeADTopmost() {
		return homeADTopmost;
	}

	public void setHomeADTopmost(Long homeADTopmost) {
		this.homeADTopmost = homeADTopmost;
	}
}
