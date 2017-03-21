<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看收款单</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/common/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/payment.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 查看收款单
	</div>
	<table class="input">
		<tr>
			<th>
				编号:<input type="hidden" name="paymentId" id="paymentId" value="${payment.id}" />
			</th>
			<td>
				${payment.sn}
			</td>
			<th>
				创建日期:
			</th>
			<td>
				${payment.createDate}
			</td>
		</tr>
		<tr>
			<th>
				状态:
			</th>
			<td>
				<#switch payment.status>
                   <#case 0>支付完成<#break>
                   <#case 1>等待支付<#break>                         
                   <#case 2>等待发货<#break>
                   <#case 3>等待收货<#break>
                   <#case -1>支付失败<#break>
                </#switch>
			</td>
			<th>
				付款日期:
			</th>
			<td>
				${payment.paymentDate!'-'}
			</td>
		</tr>
		<tr>
			<th>
				类型:
			</th>
			<td>
				<#switch payment.type>
					<#case 0>即时到账<#break>
					<#case 1>担保交易<#break>
				</#switch>
			</td>
			<th>
				方式:
			</th>
			<td>
				${payment.paymentMethod.name!''}
			</td>
		</tr>
		<tr>
			<th>
				支付插件:
			</th>
			<td>
				${payment.paymentPlugin.pluginName!'-'}
			</td>
			<th>
				操作员:
			</th>
			<td>
				${payment.operator!'-'}
			</td>
		</tr>
		<tr>
			<th>
				付款银行:
			</th>
			<td>
				${payment.bank!'-'}
			</td>
			<th>
				付款账号:
			</th>
			<td>
				${payment.account!'-'}
			</td>
		</tr>
		<tr>
			<th>
				付款人:
			</th>
			<td>
				${payment.payer!'-'}
			</td>
			<th>
				付款金额:
			</th>
			<td>
				￥${payment.amount!'-'}
			</td>
		</tr>
		<tr>
			<th>
				会员:
			</th>
			<td>
				${payment.member.username!'-'}
			</td>
			<th>
				订单:
			</th>
			<td>
				<a href="${base}/admin/order/view!${payment.orders.id}.do" style="text-decoration:underline;color:#A26D6D">${payment.orders.sn}</a>
			</td>
		</tr>
		<tr>
			<th>
				备注:
			</th>
			<td colspan="3">
				${payment.memo!'-'}
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td colspan="3">
				<#if payment.type == 1>
					<#if payment.orders.orderStatus==4 && payment.status==2>
						<span id="apiShipping" class="button">接口发货</span>
					</#if>
				</#if>
				<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);" />
			</td>
		</tr>
	</table>
</body>
</html>