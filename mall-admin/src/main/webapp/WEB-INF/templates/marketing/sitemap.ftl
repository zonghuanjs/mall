<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; Sitemap管理
	</div>
	<form id="inputForm" action="${base}/admin/sitemap/update.do" method="post">
		<table class="input">
			<tr>
				<th>
					更新频率:
				</th>
				<td>
					<select name="frequence">
						<option value="hourly">每小时</option>	
						<option value="daily">每天</option>	
				  		<option selected value="weekly">每周</option>	
						<option value="monthly">每月</option>	
				    	<option value="yearly">每年</option>	
						<option value="never">从不</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					优先级:
				</th>
				<td>
					<select name="priority">
				        <option selected value="auto">自动</option>    	
						<option value="0.1">0.1</option>	  		
						<option value="0.2">0.2</option>	  		
						<option value="0.3">0.3</option>	  		
						<option value="0.4">0.4</option>	  		
						<option value="0.5">0.5</option>	  		
						<option value="0.6">0.6</option>	  		
						<option value="0.7">0.7</option>	  		
						<option value="0.8">0.8</option>	  		
						<option value="0.9">0.9</option>	  		
						<option value="1.0">1.0</option>
					</select>	
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
					<input type="submit" class="button" value="更&nbsp;&nbsp;新" <#if role?? && role.system>disabled="disabled"</#if> />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>