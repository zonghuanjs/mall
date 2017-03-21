<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看芯豆获取信息</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 查看芯豆获取信息
	</div>
		<ul id="tab" class="tab">			
			<li>
				<input type="button" value="芯豆获取信息" />
			</li>			
		</ul>
		
		
	<table class="input tabContent">
				<tr>
					<th>
						会员名称:
					</th>
					<td>
						${beanRetain.member.username}
					</td>
				</tr>
				<tr>
					<th>
						领取时间:
					</th>
					<td>
						${beanRetain.createDate!""}
					</td>
				</tr>
				<tr>
					<th>
						领取类型:
					</th>
					<td>
						${beanRetain.typeView}
					</td>
				</tr>
				<tr>
					<th>
						领取数量:
					</th>
					<td>
						${beanRetain.optValue}
					</td>
				</tr>
				<tr>
					<th>
						是否被使用:
					</th>
					<td>
						${beanRetain.used?string('已使用', '未使用')}
					</td>
				</tr>
				<tr>
					<th>
						用完时间:
					</th>
					<td>
						${beanRetain.usedTime!"-"}
					</td>
				</tr>
				<tr>
					<th>
						是否已经失效:
					</th>
					<td>
						${beanRetain.isExpired?string('已过期', '未过期')}
					</td>
				</tr>
				<tr>
					<th>
						自然失效时间:
					</th>
					<td>
						${beanRetain.expiredTime!"-"}
					</td>
				</tr>
				<tr>
					<th>
						已使用数量:
					</th>
					<td>
						${beanRetain.usedValue}
					</td>
				</tr>
				<tr>
					<th>
						备注信息:
					</th>
					<td>
						${beanRetain.memo}
					</td>
				</tr>
		</table>

		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.go(-1);"/>
				</td>
			</tr>
		</table>
</body>
</html>