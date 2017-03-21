<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/page_setting.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/page.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			order:{
				required: true,
				digits: true,
			} ,
			count: "digits",
			attribute:"required",
		},
		messages: {
			name: "必填",	
			order:{
			    required: "必填",
				digits: "必须为整数",
			} ,
			count:"必须为整数"	,
			attribute:"必填",		
		}
	});
	
});
</script>
</head>
<body>
    <input type="hidden" id="basePath" value="${base}" />
	<form id="inputForm" action="${base}/admin/page/page_module/add.do"  method="post">
		<table id="attributeTable" class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>页面模块名称:
				</th>
				<td colspan="3">
					<input type="text" name="name" id="name" class="text" maxlength="200" title="必填项" /><span class="tip"></span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>显示顺序:
				</th>
				<td colspan="3">
					<input type="text" name="order" id="order" class="text" maxlength="9" value="0"/><span class="tip"></span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>页面类型:
				</th>
				<td colspan="3">
					<select id="page_type" name="page_type">					
							<option value="1">PC端首页</option>
							<option value="2">移动端首页</option>
					</select>					
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>模块类型:
				</th>
				<td colspan="3">
					<select id="type" name="type">					
							<option value="1">单图列表</option>
							<option value="2">分类模块</option>
							<option value="3">轮播</option>
							<option value="4">并排图</option>
							<option value="5">标题图片列表</option>
					</select>					
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>显示内容数量:
				</th>
				<td colspan="3">
					<input type="text" name="count" id="count" class="text" maxlength="9" value="0"/><span class="tip"></span>
				</td>
			</tr>
			<tr id="add_element_attr">
				<td> 模块属性</td>
				<td colspan="2">
					<a href="javascript:void(0)" id="addRuleButton" class="button">增加属性</a>
				</td>
			</tr>
			
			
		</table>
		<table class="input">
			<tr>
				<th>&nbsp;
					
				</th>
				<td>				    
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/page/page_module/list.do'" />
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>