<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>移动端安装包列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 移动端安装包列表 <span>(共<span id="pageTotal">${packages?size}</span>条记录)</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="add.do" class="iconButton">
					<span class="addIcon">&nbsp;</span>添加
				</a>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>类型</span>
				</th>
				<th>
					<span>版本代码</span>
				</th>
				<th>
					<span>版本名称</span>
				</th>
				<th>
					<span>安装包名称</span>
				</th>
				<th>
					<span>下载地址</span>
				</th>
				<th>
					<span>启用</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list packages as package>
				<tr>
					<td>
						<input type="checkbox" name="ids" id="ids" value="${package.id}"/>
					</td>
					<td>
						<#switch package.type>
							<#case 1>Android<#break>
							<#case 2>iOS<#break>
						</#switch>
					</td>
					<td>
						${package.versionCode}
					</td>
					<td>
						${package.versionName}
					</td>
					<td>
						${package.packageName}
					</td>
					<td>
						${package.downloadUrl}
					</td>
					<td>
						<span class="${package.enabled?string('true', 'false')}Icon">&nbsp;</span>
					</td>
					<td>
						<a href="${base}/admin/app/get.do?id=${package.id}">[设置]</a>
					</td>
				</tr>
			</#list>	
		</table>
	</form>
</body>
</html>