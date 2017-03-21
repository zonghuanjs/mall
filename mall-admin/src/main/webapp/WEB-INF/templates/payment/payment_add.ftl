<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>丢单处理 -${config.siteName}</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("#browserButton");
	
	
	$browserButton.browseDialog();
	
	// 表单验证
	$inputForm.validate({
		errorClass: "fieldError",
		ignoreTitle: true,
		rules: {
			paymentSn: "required",
			partner: "required",
			bank: "required",
			amount: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				}
			},
			account: "required",
			payer: 'required'
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 丢单处理
	</div>
<#if orderView?has_content>
	<form id="inputForm" action="${base}/admin/payment/add.do?id=${orderView.order.id}" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>订单编号:
				</th>
				<td>
					<input type="text" name="orderSn" class="text" value="${orderView.order.sn}" disabled="disabled" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>支付插件:
				</th>
				<td>
					<select name="plugins">
					<#list plugins as plugin>
						<option value="${plugin.id}">${plugin.pluginName}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>支付交易号:
				</th>
				<td>
					<input type="text" name="paymentSn" class="text" value="0" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>支付金额:
				</th>
				<td>
					<input type="text" name="amount" class="text" value="${orderView.order.amountPaid}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>付款银行:
				</th>
				<td>
					<input type="text" name="bank" class="text" value="" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>付款账号:
				</th>
				<td>
					<input type="text" name="account" class="text" value="" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>付款人:
				</th>
				<td>
					<input type="text" name="payer" class="text" value="${orderView.order.member.username}" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</#if>
</body>
</html>