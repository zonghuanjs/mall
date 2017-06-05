<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加友情链接 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $type = $("#type");
	var $logo = $("#logo");
	var $browserButton = $("#browserButton");
	
	
    $browserButton.browseDialog();
	
	
	$type.change(function() {
		if ($(this).val() == "text") {
			$logo.val("").prop("disabled", true);
			$browserButton.prop("disabled", true);
		} else {
			$logo.prop("disabled", false);
			$browserButton.prop("disabled", false);
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			logo: "required",
			url: "url",
			orders: "digits"			
		},
		messages: {
			name: "必填",	
			logo: "必填",	
			url: "请输入合法的网址",
			orders:"必须为数字"		
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 添加友情链接
	</div>
	<form id="inputForm" action="<#if friendlink??>save.do?id=${friendlink.id}<#else>add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" title="必填项，链接名称" <#if friendlink??>value="${friendlink.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					类型:
				</th>
				<td>
					<select id="type" name="type" title="选择链接的类型">
							<option value="1">文本</option>
							<option value="2" <#if friendlink??&&friendlink.type==2>selected="selected"</#if>>图片</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					logo:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="logo" name="logo" class="text" maxlength="200"  <#if friendlink??&&friendlink.type==2>value="${friendlink.logo}"<#else> disabled="disabled"</#if>/>
						<input type="button" id="browserButton" class="button" value="选择文件"   <#if !friendlink??||friendlink.type!=2>disabled="disabled"</#if>/>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>链接地址:
				</th>
				<td>
					<input type="text" name="url" class="text" maxlength="200" title="填写链接指定的地址" <#if friendlink??>value="${friendlink.url}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" title="必须为整数，若为空系统自动填为0" <#if friendlink??>value="${friendlink.orders}"<#else>value="0"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1)'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>