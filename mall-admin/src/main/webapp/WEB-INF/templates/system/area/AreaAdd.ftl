<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加地区 </title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			orders: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo;  添加地区
	</div>
	<form id="inputForm" action="<#if area??>${base}/admin/area/save!${area.id}.do<#else>${base}/admin/area/add.do</#if>" method="post">
		<#if parent??>
			<input type="hidden" name="parent" value="${parent.id}" />
		<#else>
		    <input type="hidden" name="parent" value="" />
		</#if>
		<table class="input">
			<tr>
				<th>
					上级地区:
				</th>
				<td>
					<#if parent??>${parent.name}<#else>顶级地区</#if>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="100" <#if area??>value="${area.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" <#if area??>value="${area.orders}" <#else> value="0"</#if>/>
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
</body>
</html>