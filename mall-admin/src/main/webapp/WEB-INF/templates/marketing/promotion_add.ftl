<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加促销 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/promotion.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

    var $inputForm = $("#inputForm");
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				},
				filterMode: false,
				filePostName: 'file',
				uploadJson:'${base}/admin/file/upload.do'
			});
		});
	}
});
</script>
<style type="text/css">
.error
{
	color: red;
}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加促销
	</div>
	<form id="inputForm" action="<#if promotion??>${base}/admin/promotion/save.do?id=${promotion.id}<#else>${base}/admin/promotion/add.do</#if>" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="促销策略" />
			</li>
			<li>
				<input type="button" value="介绍" />
			</li>
		</ul>
		<div class="tabContent">
			<table class="input">
				<tr>
					<th>
						<span class="requiredField">*</span>名称:
					</th>
					<td colspan="2">
						<input type="text" name="name" class="text" maxlength="200" <#if promotion??> value="${promotion.name!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>标题:
					</th>
					<td colspan="2">
						<input type="text" name="title" class="text" maxlength="200" <#if promotion??> value="${promotion.title!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>起始日期:
					</th>
					<td colspan="2">
						
						<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if promotion??>value="${promotion.beginDate!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>结束日期:
					</th>
					<td colspan="2">
						<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if promotion??>value="${promotion.endDate!""}"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>最少购买数量:
					</th>
					<td colspan="2">
						<input type="text" name="minNum" class="text" maxlength="9" <#if promotion??>value="${promotion.minNum!""}"<#else>value="0"</#if>/>
						每件商品至少购买数量; 0表示不限制
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>最多购买数量:
					</th>
					<td colspan="2">
						<input type="text" name="maxNum" class="text" maxlength="9" <#if promotion??>value="${promotion.maxNum!""}"<#else>value="0"</#if>/>
						每件商品最多购买数量; 0表示不限制
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>最低价格限制:
					</th>
					<td colspan="2">
						<input type="text" name="startPrice" class="text" maxlength="16" <#if promotion??>value="${promotion.startPrice!""}"<#else>value="0"</#if>/>
						超过该价格的商品才能参加活动; 0表示不限制
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>最高价格限制:
					</th>
					<td colspan="2">
						<input type="text" name="endPrice" class="text" maxlength="16" <#if promotion??>value="${promotion.endPrice!""}"<#else>value="0"</#if>/>
						低于该价格的商品才能参加活动; 0表示不限制
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>价格策略:
					</th>
					<td colspan="2">
						<fieldset>
						<#if promotion?has_content>
							<label>
								<input type="radio" name="priceOperator" value="-1" <#if promotion?has_content && promotion.priceOperator==-1>checked="checked"</#if> />减价
							</label>
						<#else>
							<label>
								<input type="radio" name="priceOperator" value="-1" checked="checked" />减价
							</label>
						</#if>							
							<label>
								<input type="radio" name="priceOperator" value="2" <#if promotion?has_content && promotion.priceOperator==2>checked="checked"</#if> />哲扣
							</label>
							<label>
								<input type="radio" name="priceOperator" value="1" <#if promotion?has_content && promotion.priceOperator==1>checked="checked"</#if> />加价
							</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>价格操作值:
					</th>
					<td colspan="2">
						<input type="text" name="priceValue" class="text" maxlength="16" <#if promotion??>value="${promotion.priceValue!""}"<#else>value="0"</#if>/>
						此值为价格策略的操作值; 0表示不操作
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>积分操作策略:
					</th>
					<td colspan="2">
						<fieldset>
						<#if promotion?has_content>
							<label>
								<input type="radio" name="pointOperator" value="-1" <#if promotion.pointOperator==-1>checked="checked"</#if> />扣除
							</label>
						<#else>
							<label>
								<input type="radio" name="pointOperator" value="-1" checked="checked" />扣除
							</label>
						</#if>							
							<label>
								<input type="radio" name="pointOperator" value="1" <#if promotion?has_content && promotion.pointOperator==1>checked="checked"</#if> />奖励
							</label>
						</fieldset>
					</td>
				</tr>
				<tr>
					<th>
						积分操作值:
					</th>
					<td colspan="2">
						<input type="text" name="pointValue" class="text" maxlength="255" <#if promotion??>value="${promotion.pointValue!""}"<#else>value="0"</#if>/>
						此值为积分操作策略的操作值; 0表示不操作
					</td>
				</tr>
				<tr>
					<th>
						排序:
					</th>
					<td>
						<input type="text" name="orders" class="text" maxlength="9" <#if promotion??>value="${promotion.orders!""}"<#else>value="0"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td>
						<input type="checkbox" name="promotionEnabled" value="true" 
						<#if promotion?has_content && promotion.attributes['isEnabled']?has_content && "true"==promotion.attributes['isEnabled']>checked="checked"</#if>
						<#if !adminView.authorities?seq_contains('admin:promotion')>disabled="disabled"</#if>
						/> (是否在线上商城发布此促销活动)
					</td>
				</tr>
				<tr>
					<th>
						是否设为抢购:
					</th>
					<td>
						<input type="checkbox" name="snatchEnabled" value="true" 
						<#if promotion?has_content && promotion.attributes['snatchEnabled']?has_content && "true"==promotion.attributes['snatchEnabled']>checked="checked"</#if>
						<#if !adminView.authorities?seq_contains('admin:promotion')>disabled="disabled"</#if>/>
					</td>
				</tr>
				<tr>
					<th>
						抢购时间点:
					</th>
					<td>
						<input type="text" name="snatchDatePoint" class="text" maxlength="2" 
						value = "<#if promotion?has_content && promotion.attributes['snatchDatePoint']?has_content >${promotion.attributes['snatchDatePoint']}</#if>"/>
						(24小时制  整数 例如：9或者 20等等)
					</td>
				</tr>
			</table>
						
		</div>
		<div class="tabContent">
			<table class="input">
				<tr>
					<th>
						会员资料限制:
					</th>
					<td>
						<fieldset>
							<label>
								<input type="checkbox" name="memberInfos" value="email" <#if promotion?has_content && promotion.attributes['member_mail']?has_content>checked="checked"</#if> />验证邮箱
							</label>
							<label>
								<input type="checkbox" name="memberInfos" value="mobile" <#if promotion?has_content && promotion.attributes['member_mobile']?has_content>checked="checked"</#if> />绑定手机
							</label>
							<label>
								<input type="checkbox" name="memberInfos" value="birth" <#if promotion?has_content && promotion.attributes['member_birth']?has_content>checked="checked"</#if> />填写生日
							</label>
						</fieldset>
					</td>
				</tr>
				<tr class="memberRank">
					<th>
						允许参加会员等级
					</th>
					<td colspan="2">
					<#list memberRanks as memberRank>
							<label>
								<input type="checkbox" name="memberRankIds" value="${memberRank.id}" <#if promotion?? &&  promotion.memberRanks?seq_contains(memberRank)>checked="checked"</#if>/>${memberRank.name!""}
							</label>
					</#list>
					</td>
				</tr>
				<tr class="productCategory">
					<th>
						允许参与商品分类
					</th>
					<td colspan="2">
					<#list productCategorys as productCategory>
							<label>
								<input type="checkbox" name="productCategoryIds" value="${productCategory.id}" <#if promotion?? &&  promotion.productCategorys?seq_contains(productCategory)>checked="checked"</#if>/>${productCategory.name!""}
							</label>
					</#list>														
					</td>
				</tr>
				<tr class="product">
					<th>
						允许参与商品
					</th>
					<td colspan="2">
						<input type="text" name="search" id="search" class="text" /><span id="findProductButton" class="button">查找</span>
						<div id="product_container">
						<#if promotion?has_content && promotion.products?has_content>
							<#list promotion.products as product>
							<label>
								<input type="checkbox" name="productIds" checked="checked" value="${product.id}" />${product.name}
								<#if product.specificationValues?has_content>
								[<#list product.specificationValues as svalue>
									${svalue.name}<#if svalue_has_next>,</#if>
								</#list>]
								</#if>
							</label>
							</#list>
						</#if>	
						</div>		
					</td>
				</tr>
				<tr class="brand">
					<th>
						允许参与品牌
					</th>
					<td colspan="2">
					<#list brands as brand>
							<label>
								<input type="checkbox" name="brandIds" value="${brand.id}" />${brand.name!""}
							</label>
					</#list>						
					</td>
				</tr>
				
				<tr>
					<th>
						设置:
					</th>
					<td colspan="2">
						<label>
							<input type="checkbox" name="freeShipping" value="true" <#if promotion??&&promotion.freeShipping> checked="checked"</#if>/>是否免运费
							<input type="hidden" name="freeShipping" value="false" />
						</label>
						<label>
							<input type="checkbox" name="couponAllowed" value="true" <#if promotion??&&promotion.couponAllowed> checked="checked"</#if> />是否允许使用优惠券
							<input type="hidden" name="couponAllowed" value="false" />
						</label>
					</td>
				</tr>
				<tr class="coupon">
					<th>
						赠送优惠券
					</th>
					<td colspan="2">
					</td>
				</tr>
			</table>
		</div>
		<div class="tabContent">
			<table class="input">
				<tr>
					<td colspan="2">
						<textarea id="editor" name="description" class="editor" style="width: 100%;"><#if promotion??>${promotion.description!""}</#if></textarea>
					</td>
				</tr>
				<tr>
					<th>
						图片上传：
					</th>
					<td>
						<input type="text" class="text" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="" target="_blank">查看</a>
					</td>
				</tr>
			</table>
		</div>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/promotion/list.do'" />
				</td>
			</tr>
		</table>
	</form>
	<table class="input">
		<tr>
			<td>
				说明:<br />
				1.减价策略只针对最终订单金额（不含运费）有效<br />
				2.折扣策略只针对满足活动条件的商品结算有效，不针对最终订单金额<br />
				3.会员资料限制即为满足勾选条件的会员方可参加该活动
			</td>
		</tr>
	</table>
</body>
</html>