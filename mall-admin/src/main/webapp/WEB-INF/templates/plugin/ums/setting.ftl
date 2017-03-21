<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>一信通插件设置 - Powered By SHOP++</title>
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
	var $number = $('#searchNumber');
	
	
	$browserButton.browseDialog();
	
	// 表单验证
	$inputForm.validate({
		errorClass: "fieldError",
		ignoreTitle: true,
		rules: {
			spCode: "required",
			loginName: "required",
			order: "digits",
			password: 'required'
		},
		messages: {
			spCode: '企业编号不能为空',
			loginName: '必填',
			password: '必填'
		}
	});
	
	$.ajax({
		url: 'query.do',
		type: 'post',
		dataType: 'text',
		beforeSend: function(){
			$number.html('正在查询...');
		},
		success: function(text){
			$number.html(text);
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 一信通插件设置
	</div>
	<form id="inputForm" action="setting.do" method="post">
	<#if plugin??>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>插件名称:
				</th>
				<td>
					<input type="text" name="pluginName" class="text" value="${plugin.pluginName}" disabled="disabled" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>企业编号:
				</th>
				<td>
					<input type="text" name="spCode" class="text" value="${plugin.attributes['spCode']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>账号:
				</th>
				<td>
					<input type="text" name="loginName" class="text" value="${plugin.attributes['loginName']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密码:
				</th>
				<td>
					<input type="password" name="password" class="text" value="${plugin.attributes['password']!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" value="${plugin.orders}" maxlength="9" />
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
					套餐剩余量:
				</th>
				<td>
					<span id="searchNumber"></span>
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