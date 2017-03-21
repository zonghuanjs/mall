<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="resources/css/list.css"/>
<script type="text/javascript" src="js/js!settings.html"></script>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/jquery.tab.js"></script>
<script type="text/javascript" src="resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="resources/js/input.js"></script>
<script type="text/javascript" src="js/js!config.html"></script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="home.html">首页</a> &raquo; 系统设置
	</div>
	<form id="inputForm" action="system/update.html" method="post" >
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本设置" />
			</li>
			<li>
				<input type="button" value="显示设置" />
			</li>
			<li>
				<input type="button" value="注册与安全" />
			</li>
			<li>
				<input type="button" value="邮件设置" />
			</li>
			<li>
				<input type="button" value="通知设置" />
			</li>
			<li>
				<input type="button" value="芯豆设置" />
			</li>
			<li>
				<input type="button" value="其它设置" />
			</li>
		</ul>
		<!-- 基本设置 -->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>网站名称:
				</th>
				<td>
					<input type="text" name="siteName" class="text" value="${config.siteName}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>系统版本:
				</th>
				<td>
					<input type="text" name="version" class="text" value="${config.version!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>网站网址:
				</th>
				<td>
					<input type="text" name="siteUrl" class="text" value="${config.siteUrl!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>logo:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="logo" class="text" value="${config.logo!''}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(config.logo, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					热门搜索:
				</th>
				<td>
					<input type="text" name="hotSearch" class="text" value="${config.hotSearch!''}" maxlength="200" title="多个关键字以(,)分隔" />
					注：多个关键字以逗号(，)分隔
				</td>
			</tr>
			<tr>
				<th>
					联系地址:
				</th>
				<td>
					<input type="text" name="address" class="text" value="${config.address!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					联系电话:
				</th>
				<td>
					<input type="text" name="phone" class="text" value="${config.phone!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					新浪微博:
				</th>
				<td>
					<input type="text" name="sinaWeiBo" class="text" value="${config.sinaWeiBo!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					微信平台:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="weiXin" class="text" value="${config.weiXin!''}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${base}${config.weiXin!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					上班时间:
				</th>
				<td>
					<input type="text" name="jobTime" class="text" value="${config.jobTime!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					邮政编码:
				</th>
				<td>
					<input type="text" name="zipCode" class="text" value="${config.zipCode!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					E-mail:
				</th>
				<td>
					<input type="text" name="email" class="text" value="${config.email!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					备案编号:
				</th>
				<td>
					<input type="text" name="certtext" class="text" value="${config.certtext!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					是否网站开启:
				</th>
				<td>
					<input type="checkbox" name="isSiteEnabled" value="true" <#if config.siteEnabled> checked="checked" </#if> />
					<input type="hidden" name="_isSiteEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>网站关闭消息:
				</th>
				<td>
					<textarea name="siteCloseMessage" class="text">${config.siteClosedMessage!''}</textarea>
				</td>
			</tr>
		</table>
		<!-- 显示设置 -->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>商品图片(大):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="largeProductImageWidth" class="text" value="${config.largeProductImageWidth!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="largeProductImageHeight" class="text" value="${config.largeProductImageHeight!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品图片(中):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="mediumProductImageWidth" class="text" value="${config.mediumProductImageWidth!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="mediumProductImageHeight" class="text" value="${config.mediumProductImageHeight!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品图片(缩略):
				</th>
				<td>
					<span class="fieldTitle">宽度:</span>
					<input type="text" name="thumbnailProductImageWidth" class="text" value="${config.thumbnailProductImageWidth!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
					<span class="fieldTitle">高度:</span>
					<input type="text" name="thumbnailProductImageHeight" class="text" value="${config.thumbnailProductImageHeight!0}" maxlength="9" style="width: 50px;" title="单位: 像素" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认商品图片(大):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultLargeProductImage" class="text" value="${config.defaultlargeProductImage!''}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(config.defaultlargeProductImage, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认商品图片(中):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultMediumProductImage" class="text" value="${config.defaultMediumProductImage!''}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(config.defaultMediumProductImage, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>默认商品图片(缩略):
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="defaultThumbnailProductImage" class="text" value="${config.defaultThumbnailProductImage!''}" maxlength="200" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(config.defaultThumbnailProductImage, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>销量榜显示数量:
				</th>
				<td>
					<input type="text" name="salesShowCount" class="text" value="${config.salesShowCount!10}" maxlength="9" title="取值范围: 0-100" />
					商品详情页左侧销量榜显示商品数
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>搜索页显示数量:
				</th>
				<td>
					<input type="text" name="searchPageSize" class="text" value="${config.searchPageSize!20}" maxlength="9" title="取值范围: 0-100" />
					商品搜索页显示商品数
				</td>
			</tr>
			<tr>
				<th>
					显示系统通知:
				</th>
				<td>
					<input type="checkbox" name="notifyEnabled" value="true" <#if config.notifyEnabled>checked="checked"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					系统通知内容:
				</th>
				<td>
					<textarea name="notifyInfo" class="text">${config.notifyInfo!''}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					价格精确位数:
				</th>
				<td>
					<select name="priceScale">
						<option value="0" <#if config.priceScale == 0 >selected="selected"</#if> >无小数位</option>
						<option value="1" <#if config.priceScale == 1 >selected="selected"</#if> >1位小数</option>
						<option value="2" <#if config.priceScale == 2 >selected="selected"</#if> >2位小数</option>
						<option value="3" <#if config.priceScale == 3 >selected="selected"</#if> >3位小数</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					显示市场价:
				</th>
				<td>
					<input type="checkbox" name="isShowMarketPrice" value="true" <#if config.showMarketPrice> checked="checked" </#if> />
					<input type="hidden" name="_isShowMarketPrice" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页分类配置:
				</th>
				<td>
					<textarea name="homeCategorySetting" class="text">${config.homeCategorySetting!''}</textarea>
				</td>
			</tr>
		</table>
		<!-- 注册与安全 -->
		<table class="input tabContent">
			<tr>
				<th>
					是否开放注册:
				</th>
				<td>
					<input type="checkbox" name="isRegisterEnabled" value="true" <#if config.registerEnabled> checked="checked" </#if> />
					<input type="hidden" name="_isRegisterEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					注册赠送优惠券:
				</th>
				<td>
					<input type="text" class="text" name="registerCoupon" value="${config.registerCoupon!0}" />
					优惠活动ID; 为0表示不赠送
				</td>
			</tr>
			<tr>
				<th>
					禁用用户名:
				</th>
				<td>
					<input type="text" name="disabledUsername" class="text" value="${config.disabledUsername!''}" maxlength="200" title="多个内容以(,)分隔" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>用户名最小长度:
				</th>
				<td>
					<input type="text" id="usernameMinLength" name="usernameMinLength" class="text" value="${config.userNameMinLength!0}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>用户名最大长度:
				</th>
				<td>
					<input type="text" name="usernameMaxLength" class="text" value="${config.userNameMaxLength!0}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密码最小长度:
				</th>
				<td>
					<input type="text" id="passwordMinLength" name="passwordMinLength" class="text" value="${config.passwordMinLength!0}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密码最大长度:
				</th>
				<td>
					<input type="text" name="passwordMaxLength" class="text" value="${config.passwordMaxLength!20}" maxlength="3" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>注册初始积分:
				</th>
				<td>
					<input type="text" name="registerPoint" class="text" value="${config.registerPoint!0}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>注册协议:
				</th>
				<td>
					<textarea name="registerAgreement" class="text">${config.registerAgreement!''}</textarea>
				</td>
			</tr>
			<tr>
				<th>
					是否允许E-mail登录:
				</th>
				<td>
					<input type="checkbox" name="isEmailLogin" value="true" <#if config.emailLogin> checked="checked" </#if> />
					<input type="hidden" name="_isEmailLogin" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					开启ssl安全登录:
				</th>
				<td>
					<input type="checkbox" name="securityLogin" value="true" <#if config.securityLogin> checked="checked" </#if> />
					<input type="hidden" name="_isEmailLogin" value="false" />
				</td>
			</tr>			
			<tr>
				<th>
					<span class="requiredField">*</span>连续登录失败几次显示验证码:
				</th>
				<td>
					<input type="text" name="accountLockCount" class="text" value="${config.accountLockCount!5}" maxlength="9" title="当连续登录失败次数超过设定值时，系统将自动锁定该账号" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>自动解锁时间:
				</th>
				<td>
					<input type="text" name="accountLockTime" class="text" value="${config.accountLockTime!10}" maxlength="9" title="账号锁定后，自动解除锁定的时间，单位: 分钟，0表示永久锁定" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>安全密匙有效时间:
				</th>
				<td>
					<input type="text" name="safeKeyExpiryTime" class="text" value="${config.safeKeyExpiryTime!30}" maxlength="9" title="找回密码时，安全密匙的有效时间，单位: 分钟，0表示永久有效" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>上传文件最大限制:
				</th>
				<td>
					<input type="text" name="uploadMaxSize" class="text" value="${config.uploadMaxSize!10}" maxlength="9" title="单位: MB，0表示无限制" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传图片扩展名:
				</th>
				<td>
					<input type="text" name="uploadImageExtension" class="text" value="${config.uploadImageExtension!''}" maxlength="200" title="多个扩展名以(,)分隔，留空表示不允许上传" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传媒体扩展名:
				</th>
				<td>
					<input type="text" name="uploadMediaExtension" class="text" value="${config.uploadMediaExtension!''}" maxlength="200" title="多个扩展名以(,)分隔，留空表示不允许上传" />
				</td>
			</tr>
			<tr>
				<th>
					允许上传文件扩展名:
				</th>
				<td>
					<input type="text" name="uploadFileExtension" class="text" value="${config.uploadFileExtension!''}" maxlength="200" title="多个扩展名以(,)分隔，留空表示不允许上传" />
				</td>
			</tr>			
		</table>
		<!--邮件设置-->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>发件人邮箱:
				</th>
				<td>
					<input type="text" id="smtpFromMail" name="smtpFromMail" class="text" value="${config.smtpFromMail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>SMTP服务器地址:
				</th>
				<td>
					<input type="text" id="smtpHost" name="smtpHost" class="text" value="${config.smtpHost!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>SMTP服务器端口:
				</th>
				<td>
					<input type="text" id="smtpPort" name="smtpPort" class="text" value="${config.smtpPort!25}" maxlength="9" title="默认端口: 25" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>SMTP用户名:
				</th>
				<td>
					<input type="text" id="smtpUsername" name="smtpUsername" class="text" value="${config.smtpUsername!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					SMTP密码:
				</th>
				<td>
					<input type="password" id="smtpPassword" name="smtpPassword" class="text" maxlength="200" title="留空则不进行密码修改" />
				</td>
			</tr>
			<tr>
				<th>
					邮件测试:
				</th>
				<td>
					<span class="fieldSet">
						<span id="toMailWrap" class="hidden">
							收件人邮箱: <br />
							<input type="text" id="toMail" name="toMail" class="text ignore" maxlength="200" />
						</span>
						<input type="button" id="mailTest" class="button" value="邮件测试" />
						<span id="mailTestStatus">&nbsp;</span>
					</span>
				</td>
			</tr>
		</table>
		<!--通知设置-->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>订单到账审核邮箱:
				</th>
				<td>
					<input type="text" id="paymentRecievedNofityEmail" name="paymentRecievedNofityEmail" class="text" value="${config.paymentRecievedNofityEmail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>订单发货通知邮箱:
				</th>
				<td>
					<input type="text" id="orderNotifyEmail" name="orderNotifyEmail" class="text" value="${config.orderNotifyEmail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>退款审核通知邮箱:
				</th>
				<td>
					<input type="text" id="refundNotifyEmail" name="refundNotifyEmail" class="text" value="${config.refundNotifyEmail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>退款执行通知邮箱:
				</th>
				<td>
					<input type="text" id="doRefundNotifyEmail" name="doRefundNotifyEmail" class="text" value="${config.doRefundNotifyMail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>售后申请审核通知邮箱:
				</th>
				<td>
					<input type="text" id="applyCheckNotifyEmail" name="applyCheckNotifyEmail" class="text" value="${config.applyCheckNotifyEmail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>收货通知邮箱:
				</th>
				<td>
					<input type="text" id="returnNotifyEmail" name="returnNotifyEmail" class="text" value="${config.returnNotifyEmail!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品检测通知邮箱:
				</th>
				<td>
					<input type="text" id="repairCheckNofiyEmail" name="repairCheckNofiyEmail" class="text" value="${config.repairCheckNofiyEmail!''}" maxlength="200" />
				</td>
			</tr>
		</table>
		<!--芯豆设置-->
		<table class="input tabContent">
			<tr>
				<th>
					芯豆购物回馈比例:
				</th>
				<td>
					<input type="text" name="feedbackScale" class="text" value="${config.feedbackScale!''}" maxlength="200" /> 购物回馈比例，例如：消费1元可获芯豆1个， 此时比例为1
				</td>
			</tr>
			<tr>
				<th>
					芯豆抵扣比例:
				</th>
				<td>
					<input type="text" name="beanScale" class="text" value="${config.beanScale!''}" maxlength="200" /> 芯豆抵实际支付的比例，例如：100芯豆可抵1元现金， 此时比例为0.01
				</td>
			</tr>
			<tr>
				<th>
					只评价订单赠送个数:
				</th>
				<td>
					<input type="text" name="beanComment" class="text" value="${config.beanComment!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					评价订单并晒图赠送个数:
				</th>
				<td>
					<input type="text" name="beanComImage" class="text" value="${config.beanComImage!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					芯豆过期时间:
				</th>
				<td>
					<input type="text" name="beanExpiredTime" class="text" value="${config.beanExpiredTime!''}" maxlength="200" /> 芯豆过期时间，以分钟作为单位  默认值为：525600 （一年）
				</td>
			</tr>
			<tr>
				<th>
					单笔订单芯豆最大支付比例:
				</th>
				<td>
					<input type="text" name="beanOrderScale" class="text" value="${config.beanOrderScale!''}" maxlength="200" /> 可用芯豆抵现的最大的比例，例如消费100元，最多可用芯豆抵现30元，此时比例为0.3
				</td>
			</tr>
		</table>
		<!-- 其他设置 -->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>货币符号: 
				</th>
				<td>
					<input type="text" name="currencySign" class="text" value="${config.currencySign!'￥'}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>货币单位: 
				</th>
				<td>
					<input type="text" name="currencyUnit" class="text" value="${config.currencyUnit!'元'}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>库存警告数: 
				</th>
				<td>
					<input type="text" name="stockAlertCount" class="text" value="${config.stockAlertCount!5}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					固定运费: 
				</th>
				<td>
					<input type="text" name="fixedFreight" class="text" value="${config.fixedFreight!0}" title="运费,单位元" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					免邮金额: 
				</th>
				<td>
					<input type="text" name="freightLimitation" class="text" value="${config.freightLimitation!0}" title="超过此金额包邮" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>订单过期时间: 
				</th>
				<td>
					<input type="text" name="orderExpiredTime" class="text" value="${config.orderExpiredTime!'1440'}" maxlength="7" title="订单创建完超过此时间, 自动取消, 单位:分钟" />
				</td>
			</tr>
			<tr>
				<th>
					是否开启客服:
				</th>
				<td>
					<input type="checkbox" name="serviceEnable" value="true" <#if config.serviceEnable> checked="checked" </#if> />
					<input type="hidden" name="_isServiceEnable" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					CC客服WEBID:
				</th>
				<td>
					<input type="text" name="ccServiceId" class="text" value="${config.ccServiceId!''}" maxlength="200" />
					必须开启客服并同是设置WEBID客服才能生效
				</td>
			</tr>
			<tr>
				<th>
					是否开启评论:
				</th>
				<td>
					<input type="checkbox" name="isReviewEnabled" value="true" <#if config.reviewEnabled> checked="checked" </#if> />
					<input type="hidden" name="_isReviewEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					是否售后功能:
				</th>
				<td>
					<input type="checkbox" name="salesAfterEnable" value="true" <#if config.salesAfterEnable> checked="checked" </#if> />
					<input type="hidden" name="_isReviewCheck" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					是否开启发票功能:
				</th>
				<td>
					<input type="checkbox" name="isInvoiceEnabled" value="true" <#if config.invoiceEnabled>checked="checked"</#if> />
					<input type="hidden" name="_isInvoiceEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					是否开启第三方登录:
				</th>
				<td>
					<input type="checkbox" name="thirdLoginEnable" value="true"<#if config.thirdLoginEnable> checked="checked" </#if> />
				</td>
			</tr>
			<tr>
				<th>
					是否开启含税价:
				</th>
				<td>
					<input type="checkbox" name="isTaxPriceEnabled" value="true" title="启用后顾客将承担订单发票税金" checked="checked" />
					<input type="hidden" name="_isTaxPriceEnabled" value="false" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>税率:
				</th>
				<td>
					<input type="text" name="taxRate" class="text" value="0.06" maxlength="7" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>Cookie路径: 
				</th>
				<td>
					<input type="text" name="cookiePath" class="text" value="/" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					Cookie作用域: 
				</th>
				<td>
					<input type="text" name="cookieDomain" class="text" value="" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					快递100授权KEY: 
				</th>
				<td>
					<input type="text" name="k100Key" class="text" value="${config.k100Key}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					开启调试模式:
				</th>
				<td>
					<input type="checkbox" name="debugable" value="true"<#if config.debugable> checked="checked" </#if> />
				</td>
			</tr>
		</table>
		
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>

</body>
</html>