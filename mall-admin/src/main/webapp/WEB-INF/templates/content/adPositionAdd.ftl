<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加广告位 </title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			width: {
				required: true,
				min: 1
			},
			height: {
				required: true,
				min: 1
			},
			template: "required"
		},
		messages: {
			name: "必填",	
			width: {
			    required: "必填",
				min: "最小值为1",
			},	
			height:{
			    required: "必填",
				min: "最小值为1",
			},
			template:"必填"		
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加广告位
	</div>
	<form id="inputForm" action="<#if adPosition??>${base}/admin/adPosition/save!${adPosition.id}.do<#else>${base}/admin/adPosition/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if adPosition??>value="${adPosition.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>宽度:
				</th>
				<td>
					<input type="text" name="width" class="text" maxlength="9" title="必填，最小值为1，单位: 像素" <#if adPosition??>value="${adPosition.width}"</#if>/>
					<label>（任意的数字，不作要求）</label>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>高度:
				</th>
				<td>
					<input type="text" name="height" class="text" maxlength="9" title="必填，最小值为1，单位: 像素" <#if adPosition??>value="${adPosition.height}"</#if>/>
					<label>（任意的数字，不作要求）</label>
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" <#if adPosition??>value="${adPosition.description}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>模板:
				</th>
				<td>					
					<textarea name="template" class="text" style="width: 98%; height: 300px;"><#if adPosition??>${adPosition.template}</#if></textarea>
					<br />
					<label>说明：<br />格式行:name=value<br />#开始表示注释说明</label>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/adPosition/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>