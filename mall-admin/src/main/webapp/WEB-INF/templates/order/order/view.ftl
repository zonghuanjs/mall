<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $confirmButton = $("#confirmButton");
	var $shippingButton = $("#shippingButton");
	var $completeButton = $("#completeButton");
	var $returnsButton = $("#returnsButton");
	var $cancelButton = $("#cancelButton");
	var $refundsButton = $('#refundsButton');
	var $lostPaymentButton = $('#lostPaymentButton');
	var $shippingPrepareButton = $('#shippingPrepareButton');
	var isLocked = false;
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});

<#if orderView.confirmButton>	
	// 确认
	$confirmButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要订单支付已到账吗?",
			onOk: function() {
				//$confirmForm.submit();
				$.ajax({
					url: 'confirm.do?id=${orderView.order.id}',
					type: 'post',
					dataType: 'text',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>

<#if orderView.shippingButton>
	// 发货
	$shippingButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要发货吗?",
			onOk: function() {
				location.href = '${base}/shipping/addshipping.do?id=${orderView.order.id}';
			}
		});
	});
</#if>

<#if orderView.refundButton>

	$refundsButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要执行退款操作吗，是否继续?",
			onOk: function() {
				window.open($this.attr('data_url'));
			}
		});
	});
</#if>

<#if orderView.lostPaymentButton>
	$lostPaymentButton.click(function(){
		$.dialog({
			type: "warn",
			content: "确定要执行丢单处理操作吗，是否继续?",
			onOk: function() {
				location.href = '${base}/payment/add.do?id=${orderView.order.id}';
			}
		});
	});
</#if>

<#if orderView.shippingPrepareButton>
	$shippingPrepareButton.click(function(){
		$.dialog({
			type: "warn",
			content: "确定要执行备货操作吗，是否继续?",
			onOk: function() {
				$.ajax({
					url: 'shipping_prepared.do?id=${orderView.order.id}',
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>
<#assign cancelStatus=[1, 2, 3]>
<#if cancelStatus?seq_contains(orderView.order.orderStatus)>
	var $cancelButton = $('#cancelButton');
	$cancelButton.click(function(){
		
		$.dialog({
			type: "warn",
			content: "确定要执行关闭订单操作吗，是否继续?",
			onOk: function() {
				$.ajax({
					url: 'cancel.do',
					type: 'post',
					data: {id: ${orderView.order.id}},
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	
	});
</#if>
});
</script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 查看订单
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="订单信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
	<#if payments?has_content>
		<li>
			<input type="button" value="收款信息" />
		</li>
	</#if>
	<#if order.shipping?has_content>
		<li>
			<input type="button" value="发货信息" />
		</li>
	</#if>
	<#if refunds?has_content>
		<li>
			<input type="button" value="退款信息" />
		</li>
	</#if>
		<li>
			<input type="button" value="退货信息" />
		</li>
		<li>
			<input type="button" value="订单日志" />
		</li>
	<#if order.isInvoice>
		<li>
			<input type="button" value="发票信息" />
		</li>
	</#if>
	<#if order.ids?has_content>
		<li>
			<input type="button" value="身份证信息" />
		</li>
	</#if>
		<li>
			<input type="button" value="售后记录" />
		</li>
	</ul>
	<!-- 订单信息 -->
	<table class="input tabContent">
		<tr>
			<td>
				&nbsp;
			</td>
			<td>
			<#if orderView.lostPaymentButton && authorities?seq_contains('admin:payment:add')>
				<input type="button" class="button" id="lostPaymentButton" name="lostPaymentButton" value="支付丢单处理" />
			</#if>
			<#if orderView.confirmButton && authorities?seq_contains('admin:order:check')>
				<input type="button" id="confirmButton" class="button" value="付款已到账" <#if order.orderStatus !=2>disabled="disabled"</#if>/>	
			</#if>
			<#if orderView.shippingPrepareButton && authorities?seq_contains('admin:order:prepare')>
				<input type="button" id="shippingPrepareButton" class="button" value="备货" />
			</#if>
			<#if orderView.shippingButton && authorities?seq_contains('admin:order:shipping')>	
				<input type="button" id="shippingButton" class="button" value="发 货" <#if order.orderStatus !=3>disabled="disabled"</#if>/>
			</#if>	
			<#if cancelStatus?seq_contains(orderView.order.orderStatus) && authorities?seq_contains('admin:order:close')>
				<input type="button" id="cancelButton" class="button" value="关闭订单" />
			</#if>
			</td>
			<td>
				&nbsp;
			</td>
			<td>
				
			</td>
		</tr>
		<tr>
			<th>
				订单编号:
			</th>
			<td width="360">
				${order.sn!"-"}
			</td>
			<th>
				创建日期:
			</th>
			<td>
				${order.createDate!'-'}
			</td>
		</tr>
		<tr>
			<th>
				订单状态:
			</th>
			<td>
				${orderView.status}
				<#if orderView.showExpireTime>
					(过期时间:${orderView.order.expire?datetime})
				</#if>
			</td>
			<th>
				用户名:
			</th>
			<td>
				${order.member.username!"-"}
			</td>
		</tr>
		
	
		<tr>
			<th>
				订单金额:
			</th>
			<td>
				${currency(order.amountPaid)}
			</td>
			<th>
				已付金额:
			</th>
			<td>
				<#if order.payment??>
				${currency(order.payment.amount)}
				<#else>
				${config.currencySign}${(0)?string('0.00')}
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				促销:
			</th>
			<td>
				<#if order.promotion?has_content>
					${order.promotion.name}
				</#if>
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
				促销折扣:
			</th>
			<td>
				
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
				赠送积分:
			</th>
			<td>
				${order.point!'-'}
			</td>
			<th>
				芯豆抵现：
			</th>
			<td>
				<#if beansMoney?has_content>
				${beansMoney} (使用芯豆${beans}个)
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				运费:
			</th>
			<td>
				${currency(order.freight)}
			</td>
			<th>
				支付手续费:
			</th>
			<td>
				${currency(order.fee)}
			</td>
		</tr>
		<tr>
			<th>
				支付方式:
			</th>
			<td>
				${order.paymentMethod.name!'-'}
			</td>
			<th>
				配送方式:
			</th>
			<td>
				<#if order.shippingMethod?has_content>
					${order.shippingMethod.name!'-'}
				<#else>
					无需配送
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				收货人:
			</th>
			<td>
				${order.consignee!'-'}
			</td>
			<th>
				地区:
			</th>
			<td>
				${order.areaName!'-'}
			</td>
		</tr>
		<tr>
			<th>
				地址:
			</th>
			<td>
				${order.address!'-'}
			</td>
			<th>
				邮编:
			</th>
			<td>
				${order.zipCode!'-'}
			</td>
		</tr>
		<tr>
			<th>
				电话:
			</th>
			<td>
				${order.phone!'-'}
			</td>
			<th>
				附言:
			</th>
			<td>
				${order.memo!'-'}
			</td>
		</tr>
		<tr>
			<th>
				收货地址:
			</th>
			<td colspan="3">
				${order.areaName!'-'}${order.address!'-'}
			</td>
		</tr>
	</table>
	<!-- 商品信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				商品编号
			</th>
			<th>
				商品名称
			</th>
			<th>
				商品价格
			</th>
			<th>
				数量
			</th>
			<th>
				重量
			</th>
			<th>
				小计
			</th>
		</tr>
		<#list orderView.order.items as orderItem>
			<tr>
				<td style="width:230px;">
				<#if orderItem.product?has_content>
					<a href = "${base}/product/product!${orderItem.product.id}.do" target="_blank" style="text-decoration:underline;color:#A26D6D">${orderItem.product.sn}</a>  
				<#else>
					-
				</#if> 
				</td>
				<td width="400">
					<span title="${orderItem.fullName!''}">${orderItem.fullName!''} 
					<#if orderItem.specification?has_content>[${orderItem.specification!''}]</#if>
					</span>
				</td>
				<td>
					${currency(orderItem.price)}
				</td>
				<td>
					${orderItem.quantity!''}
				</td>
				<td>
					${orderItem.weight!''}
				</td>
				<td>
					${currency(orderItem.price)}&nbsp;&Chi;&nbsp;${orderItem.quantity}
				</td>
			</tr>
		</#list>
	</table>

<#if payments?has_content>
	<!--支付信息-->
	<table class="input tabContent">
		<tr class="title">
			<th>
				编号
			</th>
			<th>
				支付方式
			</th>
			<th>
				支付类型
			</th>
			<th>
				支付插件
			</th>
			<th>
				付款金额
			</th>
			<th>
				状态
			</th>
			<th>
				付款日期
			</th>
		</tr>
		<#list payments as payment>
		<tr>
			<td>${payment.sn!''}</td>
			<td>${payment.paymentMethod.name}</td>
			<td>
				<#switch payment.type>
					<#case 0>即时到账<#break>
					<#case 1>担保交易<#break>
				</#switch>
			</td>
			<td>${payment.paymentPlugin.pluginName}</td>
			<td>${payment.amount?string.currency}</td>
			<td>
				<#switch payment.status>
					<#case 0>支付成功<#break>
					<#case 1>等待支付<#break>
					<#case 2>等待发货<#break>
					<#case 3>等待收货<#break>
					<#case -1>支付失败<#break>
				</#switch>
			</td>
			<td>${payment.paymentDate}</td>
		</tr>
		</#list>
	</table>
</#if>
<#if order.shipping?has_content>
	<!-- 发货信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				编号
			</th>
			<th>
				配送方式
			</th>
			<th>
				物流公司
			</th>
			<th>
				运单号
			</th>
			<th>
				收货人
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list shippings as shipping>
		<tr>
			<td>${shipping.sn}</td>
			<td>${shipping.shippingMethod.name}</td>
			<td>${shipping.deliveryCorp}</td>
			<td>${shipping.trackingNo}</td>
			<td>${shipping.consignee}</td>
			<td>${shipping.createDate}</td>
		</tr>
		</#list>
	</table>
</#if>
<#if refunds?has_content>
    <!-- 退款信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				编号
			</th>
			<th>
				方式
			</th>
			<th>
				支付方式
			</th>
			<th>
				退款金额
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list refunds as refund>
		<tr>
			<td>${refund.sn}</td>
			<td>
				<#switch refund.type>
					<#case 0>网上支付<#break>
					<#case 1>银行汇款<#break>
				</#switch>
			</td>
			<td>${refund.paymentMethod.name}</td>
			<td>${refund.amount?string.currency}</td>
			<td>${refund.createDate}</td>
		</tr>
		</#list>
	</table>
</#if>
	<!-- 退货信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				编号
			</th>
			<th>
				运费
			</th>
			<th>
				物流公司
			</th>
			<th>
				运单号
			</th>
			<th>
				发货人
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list applys as return>
		<#if return.returns??>
		<tr>
			<td>${return.returns.sn!""}</td>
			<td>${return.returns.freight!""}</td>
			<td>${return.returns.deliveryCorp.name!""}</td>
			<td>${return.returns.trackingNo!""}</td>
			<td>${return.returns.operator!""}</td>
			<td>${return.returns.createDate!""}</td>
		</tr>
		</#if>
		</#list>
	</table>

	<!-- 订单日志 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				类型
			</th>
			<th>
				操作员
			</th>
			<th>
				内容
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list orderLogs as orderLog>
			<tr>
				<td>
				<#switch orderLog.type>
					<#case 0>订单完成<#break>
					<#case 1>创建订单<#break>
					<#case 2>取消订单<#break>
					<#case 3>订单付款<#break>
					<#case 4>订单审核<#break>
					<#case 5>订单退款<#break>
					<#case 6>订单发货<#break>
					<#case -1>订单关闭<#break>
				</#switch>
				</td>
				<td>
					${orderLog.operator!"-"}
				</td>
				<td>
					${orderLog.content!"-"}
				</td>
				<td>
					<span title="">${orderLog.createDate!"-"}</span>
				</td>
			</tr>
		</#list>
	</table>
	
	<#if order.isInvoice>
		<!-- 发票信息 -->
		<table class="input tabContent">			
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
	<#if order.ids?has_content>
		<table class="input tabContent">
			<tr>
				<th>
					姓名:
				</th>
				<td>
					${order.ids.name}
				</td>
			</tr>
			<tr>
				<th>
					身份证号:
				</th>
				<td>
					${order.ids.numbers}
				</td>
			</tr>
			<tr>
				<th>
					身份证正面:
				</th>
				<td>
					<#if order.ids.frontImg?has_content>
						<img height="200px" src="${order.ids.frontImg}" />
						<a href="${order.ids.frontImg}" target="_blank">查看</a>
					<#else>
						无
					</#if>
				</td>
			</tr>
			<tr>
				<th>
					身份证反面:
				</th>
				<td>
					<#if order.ids.backImg?has_content>
						<img height="200px" src="${order.ids.backImg}" />
						<a href="${order.ids.backImg}" target="_blank">查看</a>
					<#else>
						无
					</#if>
				</td>
			</tr>
		</table>
	</#if>
	<table class="input tabContent">			
		<#list order.applys as apply>
			<tr>
				<th>
					 售后单:
				</th>
				<td>
					${apply.sn}
					<a href="${base}/admin/apply/view!${apply.id}.do" target="_blank">查看</a>
				</td>
				<th>
					类型:
				</th>
				<td>
					<#switch apply.type>
					<#case 1>退货<#break>
					<#case 2>换货<#break>
					<#case 3>维修<#break>
					</#switch>
				</td>
				<th>
					申请时间:
				</th>
				<td>
					${apply.createDate?datetime}
				</td>
			</tr>
		</#list>	
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);" />
			</td>
		</tr>
	</table>
</body>
</html>