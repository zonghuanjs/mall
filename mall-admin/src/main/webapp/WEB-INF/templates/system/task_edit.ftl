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
<#if task?has_content>
	<form id="inputForm" action="save.do?id=${task.id}" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<span>${task.name}</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>任务分组:
				</th>
				<td>
					<span>${task.group}</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>时间表达式:
				</th>
				<td>
					<input type="text" name="cronExpression" class="text" maxlength="200" value="${task.cronExpression}" />
					quartz cron表达式
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>任务实现类:
				</th>
				<td>
					<input type="text" name="jobClass" class="text" maxlength="200" value="${task.jobClass}" />
					任务实现类
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" value="${task.description}" />
				</td>
			</tr>
			<tr>
				<th>
					启用:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="status" value="1" <#if task.status==1>checked="checked"</#if> />启用
						</label>
						<label>
							<input type="radio" name="status" value="0" <#if task.status==0>checked="checked"</#if> />停用
						</label>
					</fieldset>
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