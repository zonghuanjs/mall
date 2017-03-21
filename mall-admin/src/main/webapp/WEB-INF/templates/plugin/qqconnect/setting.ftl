<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>QQ互联插件设置</title>
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
			appId: "required",
			appKey: "required",
			order: "digits",
			redirectUri: 'required'
		},
		messages: {
			pluginName: '必填',
			appId: '必填',
			appKey: '必填',
			redirectUri: '必填'
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; QQ互联插件设置
	</div>
	<form id="inputForm" action="update.do" method="post">
	<#if plugin??>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>插件名称:
				</th>
				<td>
					<input type="text" name="pluginName" class="text" style="width:250px;" value="${plugin.pluginName}" disabled="disabled" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>公司Appid:
				</th>
				<td>
					<input type="text" name="appId" class="text" style="width:250px;" value="${plugin.attributes['appId']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>公司Appkey:
				</th>
				<td>
					<input type="text" name="appKey" class="text" style="width:250px;" value="${plugin.attributes['appKey']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>QQlogo:
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
					<input type="text" name="order" class="text" style="width:250px;" value="${plugin.orders}" maxlength="9" />
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