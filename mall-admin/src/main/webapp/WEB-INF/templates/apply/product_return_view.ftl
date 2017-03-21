<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>回寄商品信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
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
	var $error = $('label.error');
	// 确认收货
	$confirmButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定收货吗?",
			onOk: function() {
				$.ajax({
					url: 'confirm.do',
					type: 'post',
					data: {id: ${return.id}},
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
	
	
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 回寄详细信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="回寄信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
	</ul>
<#if return??>
		<table class="input tabContent">
			<tr>
				<th>寄货编号:</th>
				<td>${return.sn!''}</td>
				<th>寄货日期:</th>
				<td>${return.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${return.apply.contact}</td>
				<th>联系电话：</th>
				<td>${return.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${return.area.fullName} ${return.address}</td>
				<th>邮编：</th> 
				<td>${return.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if return.deliveryCorp??>${return.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if return.shippingMethod??>${return.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${return.trackingNo}</td>
				<th>运费：</th>
				<td>${return.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${return.operator}</td>
				<th>发货人：</th>
				<td>${return.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${return.memo}</td>
				<th>到货状态：</th>
				<td>${return.apply.statusView}</td>
			</tr>
			<tr>
				<th>寄货原因：</th>
				<td>${return.apply.typeView}</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr class="title">
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品单价</th>
				<th>商品重量</th>
				<th>数量</th>
			</tr>
			<#list return.apply.items as item>
				<tr>
					<td>${item.sn}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.weight}</td>
					<td>${item.quantity}</td>
				</tr>
			</#list>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<#if return.apply.confirmRecieved>
						<input type="button" id="confirmButton" class="button" value="确认收货" />
					</#if>
					<input type="button" class="button" onClick="location.href='${base}/admin/product_return/list.do'" value="返回" />
				</td>
			</tr>
		</table>
</#if>
</body>
</html>