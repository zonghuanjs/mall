<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>售后申请详情 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	var $confirmButton = $("#confirmButton");
	var $cancelButton = $("#cancelButton");
	
<#if apply.showCheckButton>
	$confirmButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/apply/confirm.do',
					data: {id: ${apply.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
	
	$cancelButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定不通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/apply/cancel.do',
					data: {id: ${apply.id}},
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

});
</script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 售后申请详情
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="售后申请信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
		<li>
			<input type="button" value="客户寄回信息" />
		</li>
		<li>
			<input type="button" value="公司回寄信息" />
		</li>
		<li>
			<input type="button" value="退款信息" />
		</li>
		<li>
			<input type="button" value="处理日志" />
		</li>		
	</ul>
	<#if apply??>
	<table class="input tabContent">
		<tr>
			<th>
				申请编号:
			</th>
			<td>
				${apply.sn!''}
			</td>
			<th>
				申请日期:
			</th>
			<td>
				${apply.createDate!''}
			</td>
		</tr>
		<tr>
			<th>
				申请会员:
			</th>
			<td>
				${apply.member.username!''}
			</td>
			<th>
				购买日期:
			</th>
			<td>
				${apply.buyDate}
			</td>
		</tr>
		<tr>
			<th>
				关联订单:
			</th>
			<td>
				<a href="${base}/admin/order/view!${apply.orders.id}.do" title="点击查看详情" >${apply.orders.sn}</a>
			</td>
			<th>
				申请类型:
			</th>
			<td>
				${apply.typeView}
			</td>
		</tr>
		<tr>
			<th>
				申请状态:
			</th>
			<td>
				${apply.statusView}
			</td>
			<th>
				申请备注:
			</th>
			<td>
				${apply.description!''}
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<th>
				申请理由:
			</th>
			<td colspan="3">
				${apply.reason}
			</td>
		</tr>
		<tr>
			<th>
				发票照片:
			</th>
			<td>
				<#if apply.invoiceImage?exists>
				<a href="${mall_url(apply.invoiceImage, base)}" target="_blank">查看</a>
				<#else>
					无
				</#if>
			</td>
			<th>
				产品故障照片:
			</th>
			<td>
				<#if imgs?exists>
					<#list imgs as img>
						<a href="${mall_url(img, base)}" target="_blank">查看图片${img_index+1}</a>&nbsp;&nbsp;
					</#list>
				<#else>
					无
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				联系人:
			</th>
			<td>
				${apply.contact!''}
			</td>
			<th>
				联系电话:
			</th>
			<td>
				${apply.phone!''}
			</td>
		</tr>
		<tr>
			<th>
				客户地址:
			</th>
			<td>
				${apply.area.fullName!''} ${apply.address!''}
			</td>
			<th>
				邮编:
			</th>
			<td>
				${apply.zipCode}
			</td>
		</tr>
	</table>
	<table class="input tabContent">
		<tr class="title">
			<th>
				商品编号
			</th>
			<th>
				商品名称
			</th>
			<th>
				商品单价
			</th>
			<th>
				商品重量
			</th>
			<th>
				数量
			</th>
		</tr>
		<#list apply.items as item>
			<tr>
				<td>${item.sn}</td>
				<td>${item.name}</td>
				<td>${item.price?string.currency}</td>
				<td>${item.weight}</td>
				<td>${item.quantity}</td>
			</tr>
		</#list>
	</table>
	
	<table class="input tabContent">
	<#if apply.returns??>
			<tr>
				<th>寄货编号:</th>
				<td>${apply.returns.sn!''}</td>
				<th>寄货日期:</th>
				<td>${apply.returns.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${apply.returns.apply.contact}</td>
				<th>联系电话：</th>
				<td>${apply.returns.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${apply.returns.area.fullName} ${apply.returns.address}</td>
				<th>邮编：</th> 
				<td>${apply.returns.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if apply.returns.deliveryCorp??>${apply.returns.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if apply.returns.shippingMethod??>${apply.returns.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${apply.returns.trackingNo}</td>
				<th>运费：</th>
				<td>${apply.returns.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${apply.returns.operator}</td>
				<th>发货人：</th>
				<td>${apply.returns.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${apply.returns.memo}</td>
				<th>是否到货：</th>
				<td></td>
			</tr>
			</#if>
		</table>
		
	<table class="input tabContent">
		<#if apply.back??>
		<tr>
				<th>寄货编号:</th>
				<td>${apply.back.sn!''}</td>
				<th>寄货日期:</th>
				<td>${apply.back.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${apply.back.apply.contact}</td>
				<th>联系电话：</th>
				<td>${apply.back.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${apply.back.area.fullName} ${apply.back.address}</td>
				<th>邮编：</th> 
				<td>${apply.back.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if apply.back.deliveryCorp??>${apply.back.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if apply.back.shippingMethod??>${apply.back.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${apply.back.trackingNo}</td>
				<th>运费：</th>
				<td>${apply.back.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${apply.back.operator}</td>
				<th>发货人：</th>
				<td>${apply.back.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${apply.back.memo}</td>
				<th>是否到货：</th>
				<td></td>
			</tr>
		</#if>
	</table>
	
	<table class="input tabContent">
	<#if refunds??>
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
	</#if>
	</table>
	
	<table class="input tabContent">
	<#if applyLogs??>
		<tr class="title">
			<th>
				操作员
			</th>
			<th>
				IP
			</th>
			<th>
				操作类型
			</th>
			<th>
				操作内容
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list applyLogs as apply>
		<tr>
			<td>${apply.operator}</td>
			
			<td>${apply.ip!""}</td>
			<td>
				${apply.typeView}
			</td>
			<td>${apply.content!""}</td>
			<td>${apply.createDate!""}</td>
		</tr>
		</#list>
	</#if>
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
			<#if adminView.authorities?seq_contains('admin:apply:check')>
				<#if apply.status ==1><input type="button" id="confirmButton" class="button" value="审核通过" /></#if>
				<#if apply.status ==1><input type="button" id="cancelButton" class="button" value="审核不通过" /></#if>
			</#if>
				<input type="button" class="button" value="返回" onClick="location.href='${base}/admin/apply/list.do'" />
			</td>
		</tr>
	</table>
	</#if>
</body>
</html>