<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
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
	<form id="inputForm" action="add.do" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>任务分组:
				</th>
				<td>
					<select name="group">
						<option value="system">system</option>
						<option value="user">user</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>任务实现类:
				</th>
				<td>
					<input type="text" name="jobClass" class="text" maxlength="200" />
					任务实现类
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间表达式:
				</th>
				<td>
					<input type="text" name="cronExpression" class="text" maxlength="200" />
					quartz cron表达式
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" />
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
</body>
</html>