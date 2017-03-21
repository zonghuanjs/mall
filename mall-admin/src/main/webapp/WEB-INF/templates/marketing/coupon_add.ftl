<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发行优惠券</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/coupon.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $('#inputForm');
	$inputForm.validate({
		rules: {
			name: {
				required: true
			},
			beginDate: {
				required: true,			
			},
			endDate: {
				required: true,			
			},
			days: {
				required: true,
				digits:true
			},
			point: {
				digits:true,
			},
			priceValue: {
				number:true,
			},
			startPrice: {
				number:true,
			},
			endPrice: {
				number:true,
			},
			number: {
				digits:true,
			},
			dayNumber: {
				digits:true,
			},
					
		},
		
		messages: {
			name: {
				required: "必填"				
			},			
			beginDate: {
				required: "必填",				
			},
			endDate: {
				required: "必填",				
			},
			days: {
				required: "必填",
				digits:"必须为整数"				
			},
			point: {
				digits:"必须为整数",
			},
			priceValue: {
				number:"必须为数字",
			},
			startPrice: {
				number:"必须为数字",
			},
			endPrice: {
				number:"必须为数字",
			},
			number: {
				digits:"必须为整数",
			},
			dayNumber: {
				digits:"必须为整数",
			},
			
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 发行优惠券
	</div>
	<form id="inputForm" <#if coupon?has_content>action="save.do?id=${coupon.id}"<#else>action="add.do"</#if> method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if coupon?has_content>value="${coupon.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" <#if coupon?has_content>value="${coupon.description}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>起始日期:
				</th>
				<td colspan="2">
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if coupon?has_content>value="${coupon.beginDate?datetime}"</#if> />
					优惠券领取开始时间
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>结束日期:
				</th>
				<td colspan="2">
					<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if coupon?has_content>value="${coupon.endDate?datetime}"</#if> />
					优惠券领取结束时间
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>优惠券有效期:
				</th>
				<td colspan="2">
					<input type="text" name="days" class="text" maxlength="20"  <#if coupon?has_content>value="${coupon.days!0}"<#else>value="0"</#if>/>
					优惠券有效期;0表示过期时间为优惠券结束时间
				</td>
			</tr>
			<tr>
				<th>
					是否启用:
				</th>
				<td>
					<input type="checkbox" name="couponEnabled" value="true" <#if coupon?has_content&&coupon.enabled>checked="checked"</#if>
					<#if !adminView.authorities?seq_contains('admin:coupon')>disabled="disabled"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>优惠码前缀:
				</th>
				<td>
			 		<input type="text" name="prefix" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.prefix}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					积分策略:
				</th>
				<td>
					<input type="text" name="point" class="text" maxlength="200" <#if coupon?has_content>value="${coupon.point}"<#else>value="0"</#if> />
					会员积分超过此值才可使用; 0表示无限制
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>价格操作:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="priceOperator" value="-1" <#if coupon?has_content && coupon.priceOperator==-1>checked="checked"</#if><#if !coupon?has_content>checked="checked"</#if> >减价
						</label>
						<label>
							<input type="radio" name="priceOperator" value="2" <#if coupon?has_content && coupon.priceOperator==2>checked="checked"</#if> >折扣
						</label>
						<label>
							<input type="radio" name="priceOperator" value="1" <#if coupon?has_content && coupon.priceOperator==1>checked="checked"</#if> >加价
						</label>
					</fieldset>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>价格操作值:
				</th>
				<td>
					<input type="text" name="priceValue" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.priceValue?string('0.00')}"<#else>value="0.00"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>最低起用价格:
				</th>
				<td>
					<input type="text" name="startPrice" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.startPrice?string('0.00')}"<#else>value="0.00"</#if> />
					超过此价格的商品或订单才能使用; 0表示无限制
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>最高价格限制:
				</th>
				<td>
					<input type="text" name="endPrice" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.endPrice?string('0.00')}"<#else>value="0.00"</#if> />
					低于此价格的商品或订单才能使用; 0表示无限制
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>总数量限制:
				</th>
				<td>
					<input type="text" name="number" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.number!0}"<#else>value="0"</#if> />
					限制优惠券总发放的数量; 0表示无限制
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>每天数量限制:
				</th>
				<td>
					<input type="text" name="dayNumber" class="text" maxlength="20" <#if coupon?has_content>value="${coupon.dayNumber!0}"<#else>value="0"</#if> />
					限制优惠券每天发放的数量; 0表示无限制
				</td>
			</tr>
			<tr>
				<th>
					商品限制:
				</th>
				<td>
					<input type="text" name="search" id="search" placeholder="名称关键字" class="text" />
					<span id="findProductButton" class="button">查找</span>
					限制指定的商品享有此优惠；不指定则不限制商品
					<div id="product_container">
					<#if coupon?has_content && coupon.products?has_content>
						<#list coupon.products as product>
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
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定"  />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>