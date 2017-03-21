<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

    var $inputForm = $("#inputForm");	
    
    
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 增加任务
	</div>
<#if td?has_content>
	<form id="inputForm" action="save.do?id=${td.id}" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>起始时间:
				</th>
				<td>
					<input type="text" name="startDate" id="startDate" class="text" maxlength="200" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" value="${td.startDate?datetime}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>结束时间:
				</th>
				<td>
					<input type="text" name="endDate" id="endDate" class="text" maxlength="200" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'startDate\')}'});" value="${td.endDate?datetime}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品ID:
				</th>
				<td>
					<input type="text" name="product" class="text" maxlength="200" value="${td.product}"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>库存改变量:
				</th>
				<td>
					<input type="text" name="amount" class="text" maxlength="200" value="${td.amount}" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
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
</#if>
</body>
</html>