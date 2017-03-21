<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>批量退款详细信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/account/js/cookieUtil.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 批量退款详细信息
	</div>
		
	<table class="input">
		<tr>
			<th>
				编号:
			</th>
			<td>
				${batch.sn!"-"}
				<input type="hidden" value= "${batch.id}" id="refundId"/>
			</td>
			<th>
				创建时间:
			</th>
			<td>
				${batch.createDate?datetime}
			</td>
		</tr>
		<tr>
			<th>
				退款状态:
			</th>
			<td>
				<#switch batch.status>
                  <#case 0>退款完成<#break>
                  <#case 1>等待退款<#break>
                  <#case 2>退款失败<#break>
                  <#case -1>退款关闭<#break>
                </#switch>
			</td>
			<th>
				退款笔数:
			</th>
			<td>
				${batch.refunds?size}
			</td>
		</tr>
		<tr>
			<th>
				退款总金额:
			</th>
			<td>
				
			</td>
			<th>
				退款账户:
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td colspan="3">
			<#if batch.status==1>
				<a href="${base}/refund//batch_refund.do?bid=${batch.id}" target="_blank" class="button">执行退款</a>
			</#if>		
				<input type="button" class="button" value="返回" onclick="javascript:history.back(-1);" />
			</td>
		</tr>
	</table>
	<table class="input">
		<tr class="title">
			<th>
				退款编号
			</th>
			<th>
				退款类型
			</th>
			<th>
				退款订单
			</th>
			<th>
				退款金额
			</th>
			<th>
				创建日期
			</th>
		</tr>
	<#list batch.refunds as refund>
		<tr>
			<td>${refund.sn}</td>			
			<td>
				<#switch refund.type>
					<#case 0>即时到账<#break>
					<#case 1>担保交易<#break>
				</#switch>
			</td>
			<td>${refund.order.sn}</td>
			<td>${currency(refund.amount)}</td>
			<td>${refund.createDate?datetime}</td>
		</tr>
	</#list>
	</table>
</body>
</html>