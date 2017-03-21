<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑订单</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $isInvoice = $("#isInvoice");
	var $invoiceTitle = $("#invoiceTitle");
	var $tax = $("#tax");
	var $areaId = $("#areaId");
	
	var isLocked = false;
	var timeouts = {};

	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/common/areaQuery.do"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			offsetAmount: {
				required: true,
				number: true,
				decimal: {
					integer: 12,
					fraction: 2
				}
			},
			point: {
				required: true,
				digits: true
			},
			freight: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				}
			},
			invoiceTitle: "required",
			tax: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				}
			},
			consignee: "required",
			areaId: "required",
			address: "required",
			zipCode: "required",
			phone: "required"
		}
	});
		
	// 开据发票
	$isInvoice.click(function() {
		if ($(this).prop("checked")) {
			$invoiceTitle.prop("disabled", false);
			$tax.prop("disabled", false);
		} else {
			$invoiceTitle.prop("disabled", true);
			$tax.prop("disabled", true);
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 编辑订单
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="订单信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
		<li>
			<input type="button" value="发票信息" />
		</li>
	</ul>
	<form id="inputForm" action="save.do" method="post">
		<input type="hidden" name="id" value="${order.id}" />
		<table class="input tabContent">
			<tr>
				<th>
					订单编号:
				</th>
				<td width="360">
					${order.sn!''}
				</td>
				<th>
					创建日期:
				</th>
				<td>
					${order.createDate!''}
				</td>
			</tr>
			<tr>
				<th>
					订单状态:
				</th>
				<td>
					<#switch order.orderStatus>
                         <#case 1>未支付<#break>
                         <#case 2>已支付<#break>
                         <#case 3>待发货<#break>
                         <#case 4>已发货<#break>
                         <#case 5>待处理<#break>
                         <#case 6>退货处理<#break>
                         <#case 0>交易完成<#break>
                         <#case -1>交易关闭<#break>                          
                         <#default>处理中
                        </#switch>
                        <#if order.orderStatus==1||order.orderStatus==4>
						<span title="到期时间:">(到期时间: ${order.expire!''})</span>
						</#if>
				</td>
				<th>
					会员:
				</th>
				<td>
					<a href="">${order.member.username!''}</a>
				</td>
			</tr>
			<tr>
				<th>
					订单金额:
				</th>
				<td>
					<span id="amount">￥${order.amountPaid?string('0.00')!"-"}</span>
				</td>
				<th>
					已付金额:
				</th>
				<td>
					<#if order.payment??>
						${order.payment.amount?string.currency!"-"}
					<#else>
						${(0)?string.currency}
					</#if>
				</td>
			</tr>
			<tr>
				<th>
					支付方式:
				</th>
				<td>
					${order.paymentMethod.name}
				</td>
				<th>
					配送方式:
				</th>
				<td>
					${order.shippingMethod.name}
				</td>
			</tr>
			<tr>
				<th>
					促销:
				</th>
				<td>
					${order.promotion!''}
				</td>
				<th>
					使用优惠券:
				</th>
				<td>
					<#if coupon?exists>
						${coupon.name}
					<#else>
						(无)
					</#if>
				</td>
			</tr>
			<tr>
				<th>
					其他折扣:
				</th>
				<td>
					<#if order.orderStatus == 1>
						<input type="text" name="discount" value="0" />
					</#if>
				</td>
				<th>
					优惠券折扣:
				</th>
				<td>
					<#if couponPrice?has_content>${couponPrice}</#if>
				</td>
			</tr>
			<tr>
				<th>
					芯豆抵现:
				</th>
				<td>
					<#if beansMoney?has_content>
					${beansMoney} (使用芯豆${beans}个)
					</#if>
				</td>
				<th>
				</th>
				<td>
				</td>
			</tr>
			<tr>
				<th>
					运费:
				</th>
				<td>
				<#if order.orderStatus == 1>
					<input type="text" name="freight" class="text" maxlength="16"  value="${order.freight!''}" />
				<#else>
					<span>￥${order.freight?string('0.00')}</span>
				</#if>
				</td>
				<th>
					支付手续费:
				</th>
				<td>
					￥${order.fee!''}
				</td>
			</tr>			
			<tr>
				<th>
					发票抬头:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="invoiceTitle" name="invoiceTitle" class="text" <#if order.isInvoice>value="${order.invoice.title}"</#if> maxlength="200" <#if !order.isInvoice> disabled="disabled"</#if>/>
						<label>
							<input type="checkbox" id="isInvoice" name="isInvoice" value="true" <#if order.isInvoice> checked="checked"</#if> />开据发票
						</label>
					</span>
				</td>
				<th>
					税金:
				</th>
				<td>
					<input type="text" id="tax" name="tax" class="text" maxlength="16" <#if order??>value="${order.fee!''}"</#if>  <#if !order.isInvoice> disabled="disabled"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					收货人:
				</th>
				<td>
					<input type="text" name="consignee" class="text" maxlength="200" <#if order??>value="${order.consignee}"</#if> />
				</td>
				<th>
					地区:
				</th>
				<td>
					<span class="fieldSet">
						<input type="hidden" id="areaId" name="areaId" <#if area??>value="${area.id}" treePath="${area.treePath!''}"</#if> />
					</span>
				</td>
			</tr>
			<tr>
				<th>
					地址:
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200" <#if order??>value="${order.address!''}"</#if> />
				</td>
				<th>
					邮编:
				</th>
				<td>
					<input type="text" name="zipCode" class="text" maxlength="200" <#if order??>value="${order.zipCode!''}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					电话:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="200" <#if order??>value="${order.phone!''}"</#if> />
				</td>
				<th>
					附言:
				</th>
				<td>
					<input type="text" name="memo" class="text" maxlength="200" <#if order??>value="${order.memo!''}"</#if> />
				</td>
			</tr>
		</table>
		<table id="orderItemTable" class="input tabContent">
			<tr class="title">
				<th>
					编号
				</th>
				<th>
					名称
				</th>
				<th>
					价格
				</th>
				<th>
					购买数量
				</th>
				<th>
				             重量
			    </th>
				<th>
					小计
				</th>
				<th>
					操作
				</th>
			</tr>
			<#list order.items as item>
				<tr class="orderItemTr">
					<td>
						${item.product.sn}
					</td>
					<td width="400">
						<span title="${item.fullName}">${item.fullName}</span>
					</td>
					<td>
						<span>￥${item.price?string('0.00')}</span>
					</td>
					<td>
						<span>${item.quantity}</span>
					</td>
					<td>
						${item.weight!''}
				    </td>
					<td>
							<span class="subtotal">￥${item.price * item.quantity}</span>
					</td>
					<td>
						<a href="javascript:;" class="deleteOrderItem">[删除]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#if order.isInvoice>
		<table id="invoiceTable" class="input tabContent">			
			<tr>
				<th>
					  发票类型:
				</th>
				<td>
					<#if order.invoice.type==0>普通发票<#elseif order.invoice.type==1>增值发票</#if>
				</td>
				<th>
					发票抬头:
				</th>
				<td>
					${order.invoice.title}
				</td>
			</tr>			
			<tr>
				<th>
					发票内容:
				</th>
				<td>
					${order.invoice.content}
				</td>
				<th>
					创建时间:
				</th>
				<td>
					${order.invoice.createDate}
				</td>				
			</tr>
			<#if order.invoice.type==1>
			<tr>
				<th>
					单位名称:
				</th>
				<td>
					${order.invoice.company!"-"}
				</td>
				<th>
					纳税人识别号:
				</th>
				<td>
					${order.invoice.idNumber!"-"}
				</td>					
			</tr>
			
			<tr>
			    <th>
					注册地区:
				</th>
				<td>
					${order.invoice.registerAreaName!"-"}
				</td>
				<th>
					注册地址:
				</th>
				<td>
					${order.invoice.registerAddress!"-"}
				</td>				
			</tr>
			<tr>
				<th>
					注册电话:
				</th>
				<td>
					${order.invoice.registerTel!"-"}
				</td>
				<th>
					注册银行:
				</th>
				<td>
					${order.invoice.registerBank!"-"}
				</td>				
			</tr>
			
			<tr>
				<th>
					注册账户:
				</th>
				<td>
					${order.invoice.registerAccount!"-"}
				</td>	
				<th>
					
				</th>
				<td>
					
				</td>			
			</tr>
						
			<tr>
				<th>
					收票人:
				</th>
				<td>
					${order.invoice.consignee!"-"}
				</td>	
				<th>
					收票人手机号:
				</th>
				<td>
					${order.invoice.phone!"-"}
				</td>			
			</tr>
			
			<tr>
				<th>
					收票人区域:
				</th>
				<td>
					${order.invoice.areaName!"-"}
				</td>	
				<th>
					收票人地址:
				</th>
				<td>
					${order.invoice.address!"-"}
				</td>			
			</tr>
			</#if>
		</table>
		</#if>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>