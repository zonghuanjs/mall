<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员数据统计-${config.siteName}</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 会员统计
	</div>
	<table class="input tabContent">
		<tr>
			<th>
				会员总数:
			</th>
			<td>
				${memberCount!"0"}
			</td>
			<th>
				绑定邮箱数量:
			</th>
			<td>
				${mailCount!"0"}
			</td>
			<th>
				绑定手机数量:
			</th>
			<td>
				${phoneCount!"0"}
			</td>	
		</tr>
		<tr>
			<th>
				今日登录人数:
			</th>
			<td>
				${todayLoginCount!"0"}
			</td>
			<th>
				7天内登录人数:
			</th>
			<td>
				${notLogin7!"0"}
			</td>	
			<th>
				30天内登录人数:
			</th>
			<td>
				${notLogin30!"0"}
			</td>	
		</tr>
		<tr>
			<th>
				超过2个月未登录人数:
			</th>
			<td>
				${logout60!"0"}
			</td>
			<th>
				超过6个月未登录人数:
			</th>
			<td>
				${logout180!"0"}
			</td>	
			<th>
				超过1年未登录人数:
			</th>
			<td>
				${logout365!"0"}
			</td>	
		</tr>	
		<tr>
			<th>
				注册统计图:
			</th>
			<td colspan="5">
				
			</td>			
		</tr>
		
	</table>
	<form id="listForm" action="${base}/admin/member_statistic_chart.do" target="iframe1" method="get">
		<div class="bar">
			起始日期: <input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endDate\')}'});"/>
			结束日期: <input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
		</div>	
	</form>
	<div id="memberCount" style="min-width:850px;height:650px">
	<iframe id="iframe1" style="min-width:850px;height:650px" name="iframe1" frameborder="0"></iframe>
	</div>
</body>
</html>