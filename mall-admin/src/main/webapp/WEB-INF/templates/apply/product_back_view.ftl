<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>回寄商品信息 </title>
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
	<#if back??>
	<table class="input tabContent">
		<tr>
			<th>回寄编号:</th>
			<td>${back.sn!''}</td>
			<th>回寄日期:</th>
			<td>${back.modifyDate!''}</td>
		</tr>
		<tr>
			<th>收货人：</th>
			<td>${back.apply.contact}</td>
			<th>联系电话：</th>
			<td>${back.phone}</td>
		</tr>
		<tr>
			<th>收货地址：</th>
			<td>${back.area.fullName} ${back.address}</td>
			<th>邮编：</th> 
			<td>${back.zipCode}</td>
		</tr>
		<tr>
			<th>物流公司：</th>
			<td>${back.deliveryCorp.name}</td>
			<th>配送方式：</th>
			<td>${back.shippingMethod.name}</td>
		</tr>
		<tr>
			<th>操作员：</th>
			<td>${back.operator}</td>
			<th>发货人：</th>
			<td>${back.shipper}</td>
		</tr>
		<tr>
			<th>运单号：</th>
			<td>${back.trackingNo}</td>
			<th>运费：</th>
			<td>${back.freight}</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td>${back.memo}</td>
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
		<#list back.apply.items as item>
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
				<input type="button" id="backButton" class="button" value="返回" />
			</td>
		</tr>
	</table>
	</#if>
</body>
</html>