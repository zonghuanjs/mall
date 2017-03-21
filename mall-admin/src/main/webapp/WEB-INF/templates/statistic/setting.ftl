<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-统计设置</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 统计设置
	</div>
	<form action="${base}/admin/statistic/setting.do" method="post">
		<table class="input">
			<tr>
				<th>
					启用访问统计:
				</th>
				<td>
					<input type="checkbox" name="isEnabled" value="true" <#if config.statisticsEnable>checked="checked"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					统计授权Key:
				</th>
				<td>
					<input type="text" name="appKey" class="text" value="${config.statisticAppKey!''}" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1);'" />
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>