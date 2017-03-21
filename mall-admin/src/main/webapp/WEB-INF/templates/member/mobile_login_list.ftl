<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>移动端登录列表-${config.siteName}</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 移动端登录列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="list.do" method="get">
		<input type="hidden" id="type" name="type" value="" />
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10">10</a>
							</li>
							<li>
								<a href="javascript:;" class="current" val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" val="100">100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="" maxlength="200" />
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="content">内容</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="devices">登录设备</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="username">账号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginDate">登录时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginIp">登录IP</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="expired">过期时间</a>
				</th>
			</tr>
			<#list logins as login>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${login.id}" />
					</td>
					<td>
						<span title="登录设备">${login.devices}</span>
					</td>
					<td>
						<span title="账号"><#if login.member?has_content>${login.member.username}<#else>-</#if></span>
					</td>
					<td>
						<span title="登录时间">${login.loginDate?datetime}</span>
					</td>
					<td>
						<span title="登录IP">${login.loginIP}</span>
					</td>
					<td>
						<span title="过期时间">${login.expired?datetime}</span>
					</td>
				</tr>
			</#list>
		</table>
		
		<#include "../common/pager.ftl">
	</form>
</body>
</html>