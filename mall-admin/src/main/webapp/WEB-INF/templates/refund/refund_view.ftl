<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>退款信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/common/jquery.pusher.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/refund.js"></script>
<script type="text/javascript" src="${base}/resources/account/js/cookieUtil.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 退款详细信息
	</div>
		
	<table class="input">
		<tr>
			<th>
				编号:
			</th>
			<td>
				${refund.sn!"-"}
				<input type="hidden" value= "${refund.id}" id="refundId"/>
			</td>
			<th>
				创建时间:
			</th>
			<td>
				${refund.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
		<tr>
			<th>
				退款类型:
			</th>
			<td>
				<#switch refund.type>
				  <#case 0>即时到账<#break>
				  <#case 1>担保交易<#break>
				</#switch>
			</td>
			<th>
				支付插件:
			</th>
			<td>
				${refund.order.payment.paymentPlugin.pluginName}
			</td>
		</tr>
		<tr>
			<th>
				退款银行:
			</th>
			<td>
				${refund.bank!"-"}
			</td>
			<th>
				退款账户:
			</th>
			<td>
				${refund.account!"-"}
			</td>
		</tr>
		<tr>
			<th>
				退款人:
			</th>
			<td>
				${refund.payee!"-"}
			</td>
			<th>
				退款金额
			</th>
			<td>
				${currency(refund.amount)}
			</td>
		</tr>
		<tr>
			<th>
				订单编号:
			</th>
			<td>
				<#if refund.order.sn ??>
				<a href="${base}/admin/order/view!${refund.order.id}.do" style="text-decoration:underline;color:#A26D6D">${refund.order.sn}</a>
				<#else>"-"</#if>
			</td>
			<th>
				操作员:
			</th>
			<td>
				${refund.operator!"-"}
			</td>
		</tr>
		<tr>
		    <th>
				退款状态:
			</th>
			<td >
				<#switch refund.status>
                 <#case 0>
                                                                                                    退款完成
                   <#break>
                 <#case 1>
                                                                                                   退款处理中 
                   <#break>
                 <#case -1>
                                                                                                   退款失败
                   <#break>
                                          
                 <#default>
                                                                                               处理中
                </#switch>
			</td>
			<th>
				备注:
			</th>
			<td>
				${refund.memo!"-"}
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td colspan="3">
			<#if adminView.authorities?seq_contains('admin:refund:check') && !confirmed>			
				<#if refund.status ==1><a href="javascript:;" class="button" id="confirmButton">同意退款</a></#if>
			</#if>
			<#if adminView.authorities?seq_contains('admin:refund:do') && confirmed>				
				<#if refund.status ==1><a href="javascript:;" data_url="${base}/refund/refund.do?sn=${refund.order.sn}" target="_blank" class="button" id="refundsButton">退款</a></#if>
			</#if>
				<input type="button" class="button" value="返回" onclick="javascript:history.back(-1);" />
			</td>
		</tr>
	</table>
</body>
</html>