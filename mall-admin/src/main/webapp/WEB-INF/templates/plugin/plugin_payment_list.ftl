<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>支付插件列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 系统插件列表 <span>(共<span id="pageTotal">1</span>条记录)</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<span>名称</span>
				</th>
				<th>
					<span>类型</span>
				</th>
				<th>
					<span>版本</span>
				</th>
				<th>
					<span>作者</span>
				</th>
				<th>
					<span>启用</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list plugins as plugin>
				<tr>
					<td>
						<a href="${base}${plugin.settingUrl}">${plugin.pluginName}</a>
					</td>
					<td>
						<#switch plugin.type>
							<#case 1>支付插件<#break>
							<#case 2>登录插件<#break>
							<#case 3>短信插件<#break>
							<#case 4>移动支付<#break>
						</#switch>
					</td>
					<td>
						${plugin.version}
					</td>
					<td>
						${plugin.author}
					</td>
					<td>
						<span class="${plugin.enabled?string('true', 'false')}Icon">&nbsp;</span>
					</td>
					<td>
						<a href="${base}${plugin.settingUrl}">[设置]</a>
					</td>
				</tr>
			</#list>	
		</table>
	</form>
</body>
</html>