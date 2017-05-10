package com.mall.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mall.controller.base.BaseController;
import com.mall.service.ConfigService;

import cn.tekism.mall.bean.SystemConfig;
import cn.tekism.mall.util.SystemConfigUtil;
import cn.tekism.mall.util.XmlValidatorUtils;
/**
 * 系统配置控制器
 * @author zonghuan
 *
 */

@Controller
@RequestMapping(value="/system/")
public class SystemConfigController extends BaseController
{
	/**
	 * 广告位服务
	 */
	//@Autowired
	//private AdPositionService positionService;
	
	/**
	 * 商品类别服务
	 */
	@Resource
	//private ProductCategoryService pcService;
	
	/**
	 * 系统配置服务
	 */
	private ConfigService cfgService;
	
	@RequestMapping(value="/config.html", method=RequestMethod.GET)
	public ModelAndView config()
	{
		ModelAndView mv = new ModelAndView("system/config");
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		mv.addObject("config", config);
		return mv;
	}
	
	/**
	 * 保存系统配置
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	//@AdminOperationMethod(operationType=AdminOperationType.editSystemSetting)
	public String updateConfig(@RequestParam(required=false, value="securityLogin") boolean securityLogin)
	{
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		//basic information
		config.setSiteName(this.getParameter("siteName"));
		config.setVersion(this.getParameter("version"));
		config.setSiteUrl(this.getParameter("siteUrl"));
		config.setLogo(this.getParameter("logo"));
		config.setHotSearch(this.getParameter("hotSearch"));
		config.setAddress(this.getParameter("address"));
		config.setJobTime(this.getParameter("jobTime"));
		config.setPhone(this.getParameter("phone"));
		config.setZipCode(this.getParameter("zipCode"));
		config.setEmail(this.getParameter("email"));
		config.setSinaWeiBo(this.getParameter("sinaWeiBo"));
		config.setWeiXin(this.getParameter("weiXin"));
		config.setCerttext(this.getParameter("certtext"));
		
		config.setSiteEnabled(this.getBooleanParameter("isSiteEnabled"));
		config.setSiteClosedMessage(this.getParameter("siteCloseMessage"));
		//visual settings
		config.setLargeProductImageWidth(this.getIntParameter("largeProductImageWidth"));
		config.setLargeProductImageHeight(this.getIntParameter("largeProductImageHeight"));
		config.setMediumProductImageWidth(this.getIntParameter("mediumProductImageWidth"));
		config.setMediumProductImageHeight(this.getIntParameter("mediumProductImageHeight"));
		config.setThumbnailProductImageWidth(this.getIntParameter("thumbnailProductImageWidth"));
		config.setThumbnailProductImageHeight(this.getIntParameter("thumbnailProductImageHeight"));
		config.setDefaultlargeProductImage(this.getParameter("defaultLargeProductImage"));
		config.setDefaultMediumProductImage(this.getParameter("defaultMediumProductImage"));
		config.setDefaultThumbnailProductImage(this.getParameter("defaultThumbnailProductImage"));
		config.setSalesShowCount(this.getIntParameter("salesShowCount"));
		config.setSearchPageSize(this.getIntParameter("searchPageSize"));
		config.setInvoiceEnabled(this.getBooleanParameter("isInvoiceEnabled"));
		
		config.setNotifyEnabled(this.getBooleanParameter("notifyEnabled"));
		config.setNotifyInfo(this.getParameter("notifyInfo"));
		
		config.setPriceScale(this.getIntParameter("priceScale"));
		config.setShowMarketPrice(this.getBooleanParameter("isShowMarketPrice"));
		config.setHomeCategorySetting(this.getParameter("homeCategorySetting"));
		//registration and security
		config.setRegisterEnabled(this.getBooleanParameter("isRegisterEnabled"));
		config.setRegisterCoupon(this.getLongParameter("registerCoupon"));
		config.setEmailLogin(this.getBooleanParameter("isEmailLogin"));
		
		config.setDisabledUsername(this.getParameter("disabledUsername"));
		config.setUserNameMinLength(this.getIntParameter("usernameMinLength"));
		config.setUserNameMaxLength(this.getIntParameter("usernameMaxLength"));
		config.setPasswordMinLength(this.getIntParameter("passwordMinLength"));
		config.setPasswordMaxLength(this.getIntParameter("passwordMaxLength"));
		config.setRegisterPoint(this.getIntParameter("registerPoint"));
		config.setRegisterAgreement(this.getParameter("registerAgreement"));
		config.setAccountLockCount(this.getIntParameter("accountLockCount"));
		config.setAccountLockTime(this.getIntParameter("accountLockTime"));
		config.setSecurityLogin(securityLogin);
		
		config.setUploadMaxSize(this.getIntParameter("uploadMaxSize"));
		config.setUploadImageExtension(this.getParameter("uploadImageExtension"));
		config.setUploadMediaExtension(this.getParameter("uploadMediaExtension"));
		config.setUploadFileExtension(this.getParameter("uploadFileExtension"));
		//mail configuration
		config.setSmtpFromMail(this.getParameter("smtpFromMail"));
		config.setSmtpHost(this.getParameter("smtpHost"));
		config.setSmtpPort(this.getIntParameter("smtpPort"));
		config.setSmtpUsername(this.getParameter("smtpUsername"));
		if(StringUtils.isNotEmpty(this.getParameter("smtpPassword")))
			config.setSmtpPassword(this.getParameter("smtpPassword"));
		
		//邮件通知设置
		config.setPaymentRecievedNofityEmail(this.getParameter("paymentRecievedNofityEmail").trim());
		config.setOrderNotifyEmail(this.getParameter("orderNotifyEmail"));
		config.setRefundNotifyEmail(this.getParameter("refundNotifyEmail"));
		config.setDoRefundNotifyMail(this.getParameter("doRefundNotifyEmail").trim());
		config.setApplyCheckNotifyEmail(this.getParameter("applyCheckNotifyEmail").trim());
		config.setReturnNotifyEmail(this.getParameter("returnNotifyEmail").trim());
		config.setRepairCheckNofiyEmail(this.getParameter("repairCheckNofiyEmail").trim());
		
		//其他设置
		config.setCurrencySign(this.getParameter("currencySign"));
		config.setCurrencyUnit(this.getParameter("currencyUnit"));
		config.setStockAlertCount(this.getIntParameter("stockAlertCount"));
		config.setFixedFreight(this.getDoubleParameter("fixedFreight"));
		config.setFreightLimitation(this.getDoubleParameter("freightLimitation"));
		
		//功能开关
		config.setReviewEnabled(this.getBooleanParameter("isReviewEnabled"));
		config.setServiceEnable(this.getBooleanParameter("serviceEnable"));
		config.setThirdLoginEnable(this.getBooleanParameter("thirdLoginEnable"));
		config.setSalesAfterEnable(this.getBooleanParameter("salesAfterEnable"));
		config.setCcServiceId(this.getParameter("ccServiceId"));
		config.setDebugable(this.getBooleanParameter("debugable"));
		
		config.setK100Key(this.getParameter("k100Key"));
		config.setSafeKeyExpiryTime(this.getIntParameter("safeKeyExpiryTime"));
		config.setOrderExpiredTime(this.getIntParameter("orderExpiredTime"));
		
		config.setBeanScale(this.getDoubleParameter("beanScale"));
		config.setBeanExpiredTime(this.getIntParameter("beanExpiredTime"));
		config.setBeanComImage(this.getIntParameter("beanComImage"));
		config.setBeanComment(this.getIntParameter("beanComment"));
		config.setFeedbackScale(this.getDoubleParameter("feedbackScale"));
		config.setBeanOrderScale(this.getDoubleParameter("beanOrderScale"));
		
		//update config to xml
		SystemConfigUtil.updateSystemConfig(config);
		
		return "redirect:/system/config.html";
	}
	
	/**
	 * 系统广告管理
	 * @return
	 */
	@RequestMapping(value="/ad_admin.do", method=RequestMethod.GET)
	public ModelAndView adAdmin()
	{
		ModelAndView mv = new ModelAndView("admin/system/ad_admin");
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		mv.addObject("config", config);
		//mv.addObject("positions", positionService.getAll());
				
		return mv;
	}
	
	@RequestMapping(value="/ad_admin.do", method=RequestMethod.POST)
	public String saveAdPosition()
	{
		SystemConfig config = SystemConfigUtil.getSystemConfig();
		config.setHomeAdPosition1(this.getLongParameter("homeAdPosition1"));
		config.setHomeAdPosition2(this.getLongParameter("homeAdPosition2"));
		config.setHomeAdPosition3(this.getLongParameter("homeAdPosition3"));
		config.setHomeAdPosition4(this.getLongParameter("homeAdPosition4"));
		config.setHomeAdPosition5(this.getLongParameter("homeAdPosition5"));
		config.setHomeAdPosition6(this.getLongParameter("homeAdPosition6"));
		
		String strId4 = "";
		String[] ads = this.getParameters("adlist");
		if(ads !=null){
			for(int i=0;i<ads.length;i++){
				strId4 += ads[i] + ";";
			}
		}
		config.setHomeAdPositionList(strId4);
		
		config.setHomeAdPosition7(this.getLongParameter("homeAdPosition7"));
		config.setHomeAdPosition8(this.getLongParameter("homeAdPosition8"));
		config.setHomeAdPosition9(this.getLongParameter("homeAdPosition9"));
		
		config.setProductAdPosition1(this.getLongParameter("productAdPosition1"));
		config.setHotAdPosition(this.getLongParameter("hotAdPosition"));
		
		config.setHomeADTopmost(this.getLongParameter("homeADTopmost"));
		config.setSolidStateDisk(this.getLongParameter("solidStateDisk"));
		config.setMemoryBank(this.getLongParameter("memoryBank"));
		config.setRemovableStorage(this.getLongParameter("removableStorage"));
		config.setDigitalAccessories(this.getLongParameter("digitalAccessories"));
		config.setCategoryAdPos(this.getLongParameter("categoryAdPos"));
		
		
		SystemConfigUtil.updateSystemConfig(config);
		return "redirect:/admin/system/ad_admin.do";
	}

	/**
	 * 手机端首页广告管理
	 */
	@RequestMapping(value="/mobile_home.do", method=RequestMethod.GET)
	public ModelAndView mobileHomeAd()
	{
		ModelAndView mv = new ModelAndView("admin/system/mobile_home");
		//MobileHomeConfig config = (MobileHomeConfig)cfgService.getConfig(MobileHomeConfig.class);
		//List<AdPosition> poses = positionService.getAll();
		//List<ProductCategory> categorys = pcService.getAll();
		//mv.addObject("homeConfig", config);
		//mv.addObject("positions", poses);
		//mv.addObject("categorys", categorys);
		return mv;
	}
	
	/**
	 * 手机端首页广告管理保存处理
	 * @return
	 */
	@RequestMapping(value="/mobile_home.do", method=RequestMethod.POST)
	public String mobileHomeAdSave(@RequestParam("home1") Long home1,
			@RequestParam("home2") Long home2,
			@RequestParam("home31") Long home31, @RequestParam("home32") Long home32,
			@RequestParam("home41") Long home41, @RequestParam("home42") Long home42,
			@RequestParam("category") String category, @RequestParam("linkPos") Long linkPos,
			@RequestParam("crossPos") Long crossPos, @RequestParam("startPos") Long startPos)
	{
		//MobileHomeConfig config = (MobileHomeConfig)cfgService.getConfig(MobileHomeConfig.class);
/*		config.setHome1(home1);
		config.setHome2(home2);
		config.setHome31(home31);
		config.setHome32(home32);
		config.setHome41(home41);
		config.setHome42(home42);
		config.setCategory(category);
		config.setLinkPos(linkPos);
		config.setCrossPos(crossPos);
		config.setStartPos(startPos);
		
		cfgService.saveConfig(config);
*/		
		return "redirect:mobile_home.do";
	}
	
	/**
	 * 检测分类配置是否正确
	 * @return
	 */
	@RequestMapping(value="/category_pos_check.do", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	@ResponseBody
	public String checkCategorySetting(@RequestParam("homeCategorySetting") String xml)
	{
		String result = "true";
		try
		{
			XmlValidatorUtils.validateXml("category-pos.xsd", xml);
		}
		catch(Exception ex)
		{
			result = "false";
			ex.printStackTrace();
		}
		return result;
	}
}
