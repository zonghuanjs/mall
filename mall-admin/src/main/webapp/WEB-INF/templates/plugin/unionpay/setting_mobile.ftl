<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>中国银联在线支付插件设置</title>
<meta name="author" content="普福软件研发部" />
<meta name="copyright" content="普福软件研发部" />
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
			pluginName: "required",
			certId: "required",
			merId: "required",
			order: "digits",
			priCertPwd:"required",
			priCertName:"required",
			logo:"required",
			pubCertName:"required"
		},
		messages: {
			pluginName: '必填',
			certId: '必填',
			merId: '必填',
			priCertPwd:"必填",
			logo:"必填"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 中国银联手机控件支付插件设置
	</div>
	<form id="inputForm" action="update_mobile.do" method="post">
	<#if plugin??>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>插件名称:
				</th>
				<td>
					<input type="text" name="pluginName" class="text" style="width:300px;" value="${plugin.pluginName}" disabled="disabled" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商户代码:
				</th>
				<td>
					<input type="text" name="merId" class="text" style="width:300px;" value="${plugin.attributes['merId']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商户私钥证书名:
				</th>
				<td>
					<input type="text" name="priCertName" class="text" style="width:300px;" value="${plugin.attributes['priCertName']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商户私钥证书序列号:
				</th>
				<td>
					<input type="text" name="certId" class="text" style="width:300px;" value="${plugin.attributes['certId']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商户私钥证书密码:
				</th>
				<td>
					<input type="text" name="priCertPwd" class="text" style="width:300px;" value="${plugin.attributes['priCertPwd']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					加密证书名:
				</th>
				<td>
					<input type="text" name="encryptCertName" class="text" style="width:300px;" value="${plugin.attributes['encryptCertName']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>银联公钥证书名:
				</th>
				<td>
					<input type="text" name="pubCertName" class="text" style="width:300px;" value="${plugin.attributes['pubCertName']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>银联logo:
				</th>
				<td>
					<input type="text" name="logo" class="text" style="width:300px;" value="${plugin.attributes['logo']!''}" maxlength="300" />
					<input type="button" id="browserButton" class="button" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" style="width:300px;" value="${plugin.orders}" maxlength="9" />
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