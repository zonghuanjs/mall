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
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			link: "required",
			image: "required",
			order:{
				required: true,
				digits: true,
			} ,
			attribute:"required",
		},
		messages: {
			title: "必填",	
			link: "必填",
			image:"必填",	
			orders:"必须为整数"	,
			attribute:"必填",		
		}
	});
	
});
</script>
</head>
<body>
    <input type="hidden" id="basePath" value="${base}" />
	<form id="inputForm" action="${base}/admin/page/module_element/add.do"  method="post">
		<table id="attributeTable" class="input">
		    <tr>
				<th>
					<span class="requiredField">*</span>关联页面模块:
				</th>
				<td colspan="3">
					<input type="hidden" name="moduleId" id="moduleId" value="${pageModule.id}"/>
					${pageModule.name}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>图片地址:
				</th>
				<td colspan="3">
					<input type="text" name="image" id="image" class="text" maxlength="200" title="必填项" />

					<input type="button" id="browserButton" class="button browserButton" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>连接地址:
				</th>
				<td colspan="3">
					<input type="text" name="link" id="link" class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>元素标题:
				</th>
				<td colspan="3">
					<input type="text" name="title" id="title" class="text"/>		
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>显示顺序:
				</th>
				<td colspan="3">
					<input type="text" name="orders" id="orders" class="text" maxlength="9" value="0"/>
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
					<input type="button" class="button" value="返回" onclick="javascript:history.back(-1);" />
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>