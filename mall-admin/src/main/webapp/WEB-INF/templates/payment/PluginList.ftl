<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 支付插件列表 <span>(共<span id="pageTotal">1</span>条记录)</span>
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
				<tr>
					<td>
						<a href="http://www.shopxx.net" target="_blank">支付宝即时交易</a>
					</td>
					<td>
						1.0
					</td>
					<td>
						SHOP++
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<a href="alipay_direct/setting.jhtml">[设置]</a>
						<a href="alipay_direct/uninstall.jhtml">[卸载]</a>
					</td>
				</tr>
		</table>
	</form>
</body>
</html>