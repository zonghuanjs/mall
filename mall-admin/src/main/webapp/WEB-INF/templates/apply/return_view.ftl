<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发货信息 </title>
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
	
	var $recievedButton = $("#recievedButton");
	var $refundForm = $('#refundForm');
	var $refund = $('#refund');
	var $money = parseFloat($refund.val());
	var $error = $('label.error');
	var $refundButton = $('#refundButton');
	
<#if return.showCheckButton>
	$confirmButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/return/confirm.do',
					data: {id: ${return.id}},
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
					url: '${base}/admin/return/cancel.do',
					data: {id: ${return.id}},
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
<#if return.showRefund>
	//退款金额限制
	$refundButton.click(function(){
		if($.trim($refund.val()).length == 0)
		{
			$error.text('必填');
			$refund.val($money);
			$refund.focus();
		}
		else if(parseFloat($refund.val()) > $money)
		{
			$error.text('金额过多');
			$refund.val($money);
			$refund.focus();
		}
		else if(isNaN(parseFloat($refund.val())))
		{
			$error.text('金额只能为数字');
			$refund.val('');
			$refund.focus();
		}
		else
		{
			$.dialog({
				type: "warn",
				content: "确定要执行退款?",
				onOk: function(){
					$refundForm.submit();
				}
			});
		}
	});	
	
	var $refundFailButton = $('#refundFailButton');
	$refundFailButton.click(function(){
		$.dialog({
			type: "warn",
			content:"不通过需将物品寄会给用户, 确定执行?",
			onOk: function(dialog){
				location.href='${base}/admin/product_back/add.do?id=${return.id}';
			}
		});
	});
	
</#if>	

<#if return.showConformRecievedButton>	
	// 确认收货
	$recievedButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定收货吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/product_return/confirm.do',
					type: 'post',
					data: {id: ${return.returns.id}},
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>	
	$refund.keypress(function(event) {
		var key = event.keyCode ? event.keyCode : event.which;
		if ((key == 13 && $(this).val().length > 0) || (key >= 48 && key <= 57)) {
			return true;
		} else {
			return false;
		}
	});
	

});
</script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 退货详细信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="退货申请信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
		<li>
			<input type="button" value="客户寄回信息" />
		</li>
		<li>
			<input type="button" value="退款信息" />
		</li>
		<li>
			<input type="button" value="处理日志" />
		</li>
	</ul>
	
	<#if return??>
	<#if return.showRefund>
	<form id="refundForm" action="${base}/admin/product_return/refund.do?id=${return.returns.id}" method="post">
   </#if>
	<table class="input tabContent">
		<tr>
			<th>
				流水号:
			</th>
			<td>
				${return.sn!''}
			</td>
			<th>
				申请日期:
			</th>
			<td>
				${return.createDate!''}
			</td>
		</tr>
		<tr>
		    <th>
				联系人:
			</th>
			<td>
				${return.contact!''}
			</td>
			<th>
				手机:
			</th>
			<td>
				${return.phone!''}
			</td>			
		</tr>
		<tr>
			<th>
				区域:
			</th>
			<td>
				${return.area.name!''}
			</td>
			<th>
				地址:
			</th>
			<td>
				${return.address!''}
			</td>
		</tr>
		<tr>
			<th>
				邮编:
			</th>
			<td>
				${return.zipCode!''}
			</td>
			<th>
				理由:
			</th>
			<td>
				${return.reason!''}
			</td>
		</tr>
		<tr>
			<th>
				购买日期:
			</th>
			<td>
				${return.buyDate!''}
			</td>
			<th>
				所属会员:
			</th>
			<td>
				${return.member.username!""}
			</td>
		</tr>
		<tr>
			<th>
				关联订单:
			</th>
			<td>
				<a href="${base}/admin/order/view!${return.orders.id}.do" title="点击查看详情" >${return.orders.sn}</a>
			</td>
			<th>
				
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
			<th>
				是否审核通过:
			</th>
			<td>
				<#if return.checked>是<#else>否</#if>
			</td>
			<th>
				申请状态:
			</th>
			<td>
			   ${return.statusView}
			</td>
		</tr>
		<tr>			
			<th>
				发票照片:
			</th>
			<td>
			<#if return.invoiceImage?has_content>
				<img src="${mall_url(return.invoiceImage, base)!''}">
			<#else>
				无
			</#if>
			</td>
			<th>
				产品照片:
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
		<#list return.items as item>
		<tr>
			<td>
				${item.sn!""}
			</td>
			<td>
				${item.name!""}
			</td>
			<td>
				${item.price!""}
			</td>
			<td>
				${item.weight!""}
			</td>
			<td>
				${item.quantity!""}
			</td>
		</tr>
		</#list>
	</table>
	
    <table class="input tabContent">
	     <#if return.returns??>
			<tr>
				<th>寄货编号:</th>
				<td>${return.returns.sn!''}</td>
				<th>寄货日期:</th>
				<td>${return.returns.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${return.returns.apply.contact}</td>
				<th>联系电话：</th>
				<td>${return.returns.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${return.returns.area.fullName} ${return.returns.address}</td>
				<th>邮编：</th> 
				<td>${return.returns.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if return.returns.deliveryCorp??>${return.returns.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if return.returns.shippingMethod??>${return.returns.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${return.returns.trackingNo}</td>
				<th>运费：</th>
				<td>${return.returns.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${return.returns.operator}</td>
				<th>发货人：</th>
				<td>${return.returns.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${return.returns.memo}</td>
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
	    <#if return.showRefund>
			 	<tr>
			 		<th>退款金额</th>
			 		<td>
			 			<input type="hidden" name="freight" id="freight" value="${return.returns.freight}"/>
			 			<input type="text" name="refund" id="refund" value="${return.orders.amountPaid}" style="height:25px;border:1px solid #DDD;padding-left:7px;margin-right:5px;"/><#if unit??>${unit}</#if>
			 			<label for="refund" class="error" style="color:red; margin-left:5px;"></label>
			 		</td>
			 	</tr>
		</#if>
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
			<#if return.showCheckButton && adminView.authorities?seq_contains('admin:apply:check')>
			    <input type="button" id="confirmButton" class="button" value="审核通过" />
				<input type="button" id="cancelButton" class="button" value="审核不通过" />
			</#if>
			<#if return.showRefund && adminView.authorities?seq_contains('admin:refund:agree')>
				<input type="button" id="refundButton" class="button" value="同意退款" />
				<input type="button" id="refundFailButton" class="button" value="检测不通过" />
			</#if>
			<#if return.confirmRecieved && adminView.authorities?seq_contains('admin:product:recieved')>
				<input type="button" id="recievedButton" class="button" value="确认收货" />
			</#if>
				<input type="button" class="button" value="返回" onClick="location.href='${base}/admin/return/list.do'" />
			</td>
		</tr>
	</table>
	</#if>
</body>
</html>