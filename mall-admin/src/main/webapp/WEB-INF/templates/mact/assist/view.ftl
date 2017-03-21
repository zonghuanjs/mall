<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看访问详情</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 访问详情
	</div>
		
	<table class="input">
	<#if invite?has_content>
		<tr>
			<th>
				微信昵称:
			</th>
			<td>
				<span title="微信昵称">${invite.wxNickname}</span>
			</td>
		</tr>
		<tr>
			<th>
				首次访问时间:
			</th>
			<td>
				<span>${invite.createDate?datetime}</span>
			</td>
		</tr>
		<tr>
			<th>
				微信openid:
			</th>
			<td>
				<span title="微信openid">${invite.wxOpenid}</span>
			</td>
		</tr>
		<tr>
			<th>
				所在国家:
			</th>
			<td>
				<span>${invite.wxCountry!'-'}</span>
			</td>
		</tr>
		<tr>
			<th>
				所在城市:
			</th>
			<td>
				<span>${invite.wxCity!'-'}</span>
			</td>
		</tr>
		<tr>
			<th>
				参与手机号:
			</th>
			<td>
				<span>${invite.mobile!'-'}</span>
			</td>
		</tr>
		<tr>
			<th>
				成功推荐人数:
			</th>
			<td>
				<span>${invite.strength!0}</span>
			</td>
		</tr>
		<tr>
			<th>
				剩余抽奖次数:
			</th>
			<td>
				<span>${invite.lottery!0}</span>
			</td>
		</tr>
	</#if>
	</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
</body>
</html>