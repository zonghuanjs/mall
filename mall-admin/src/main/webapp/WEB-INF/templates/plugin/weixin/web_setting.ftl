<!DOCTYPE html">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>微信支付插件设置 -${config.siteName}</title>
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
		
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 支付宝即时交易设置
	</div>
	<form id="inputForm" action="update.do" method="post">
	<#if plugin??>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>支付方式名称:
				</th>
				<td>
					<input type="text" name="pluginName" class="text" value="${plugin.pluginName}" disabled="disabled" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>公众账号ID:
				</th>
				<td>
					<input type="text" name="appid" class="text" value="${plugin.attributes['appid']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商户ID:
				</th>
				<td>
					<input type="text" name="mchid" class="text" value="${plugin.attributes['mchid']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>API安全密钥:
				</th>
				<td>
					<input type="text" name="apikey" class="text" value="${plugin.attributes['apikey']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>加密证书名称:
				</th>
				<td>
					<input type="text" name="certPath" class="text" value="${plugin.attributes['cert_path']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					logo:
				</th>
				<td>
					<input type="text" name="logo" class="text" value="${plugin.attributes['logo']!''}" maxlength="200" />
					<input type="button" id="browserButton" class="button" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${plugin.orders!0}" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					启用:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" <#if plugin.enabled>checked="checked"</#if> />
					</label>
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
		</#if>
		</table>
	</form>
</body>
</html>