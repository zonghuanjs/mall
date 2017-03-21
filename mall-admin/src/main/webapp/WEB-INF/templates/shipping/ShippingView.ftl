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
	

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 发货详细信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="发货信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
	</ul>
	<table class="input tabContent">
		<tr>
			<th>
				流水号:
			</th>
			<td>
				${shipping.sn}
			</td>
			<th>
				创建日期:
			</th>
			<td>
				${shipping.createDate?datetime}
			</td>
		</tr>
		<tr>
			<th>
				配送方式:
			</th>
			<td>
				${shipping.shippingMethod.name}
			</td>
			<th>
				物流公司:
			</th>
			<td>
				${shipping.deliveryCorp}
			</td>
		</tr>
		<tr>
			<th>
				运单号:
			</th>
			<td>
				${(shipping.trackingNo)!"-"}
			</td>
			<th>
				运费:
			</th>
			<td>
				${(shipping.freight?string.currency)!"-"}
			</td>
		</tr>
		<tr>
			<th>
				收货人:
			</th>
			<td>
				${shipping.consignee}
			</td>
			<th>
				手机号码:
			</th>
			<td>
				${shipping.phone}
			</td>
		</tr>
		<tr>
			<th>
				地区:
			</th>
			<td>
				${shipping.area}
			</td>
			<th>
				地址:
			</th>
			<td>
				${shipping.address}
			</td>
		</tr>
		<tr>
			<th>
				邮编:
			</th>
			<td>
				${shipping.zipCode}
			</td>
			<th>
				订单编号:
			</th>
			<td>
				<a href="${base}/admin/order/view!${shipping.orders.id}.do" style="text-decoration:underline;color:#A26D6D">${shipping.orders.sn}</a>
			</td>
		</tr>
		<tr>
			
			<th>
				操作员:
			</th>
			<td>
				${shipping.operator}
			</td>
			<th>
				备注:
			</th>
			<td>
				${shipping.memo}
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
	<#list shipping.orders.items as item>
		<tr>
			<td><#if item.product??>
			<a href="${base}/product/product!${item.product.id}.do" style="text-decoration:underline;color:#A26D6D" target="_blank">${item.product.sn}</a>
			</#if></td>
			<td>${item.fullName} <#if item.specification??>[${item.specification}]</#if></td>
			<td>${item.price?string.currency}</td>
			<td><#if item.product??>${item.product.weight!0}</#if></td>
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
</body>
</html>