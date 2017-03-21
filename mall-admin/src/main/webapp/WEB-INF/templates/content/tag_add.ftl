<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加标签 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("#browserButton");
	
	
	$browserButton.browseDialog();
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加标签
	</div>
	<form id="inputForm" action="<#if tag??>${base}/admin/tag/save!${tag.id}.do<#else>${base}/admin/tag/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" title="必填项，标签名称" <#if tag??&&tag.name??>value="${tag.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					类型:
				</th>
				<td>
					<select name="type" title="标签类型" title="标签类型">
							<option value="1" <#if tag??&&tag.type==1>selected</#if>>文章标签</option>
							<option value="2" <#if tag??&&tag.type==2>selected</#if>>商品标签</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					图标:
				</th>
				<td>
					<input type="text" name="icon" class="text" maxlength="200" <#if tag??&&tag.icon??>value="${tag.icon}"</#if>/>
					<input type="button" id="browserButton" class="button" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td colspan="4">
					<input type="text" name="memo" class="text" maxlength="200" <#if tag??&&tag.memo??>value="${tag.memo}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" title="必须为整数，若不填系统自动填为0" <#if tag??&&tag.orders??>value="${tag.orders}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/tag/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>