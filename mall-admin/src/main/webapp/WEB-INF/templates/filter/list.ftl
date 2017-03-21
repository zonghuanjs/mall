<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员列表</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>

<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 过滤列表 <span>(共<span id="pageTotal">${members?size}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<div class="buttonWrap">
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
					<a href="javascript:;" class="sort" name="username">用户名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="memberRank">会员等级</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="email">E-mail</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="registerIP">注册IP</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginDate">最后登录</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">注册日期</a>
				</th>
				<th>
					<span>状态</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list members as member>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${member.id}" />
					</td>
					<td>
						${member.username}
					</td>
					<td>
						${member.memberRank.name}
					</td>
					<td>
						${member.email}
					</td>
					<td>
						<span title="注册IP">${member.registerIP}</span>
					</td>
					<td>
						<span title="最后登录">${member.loginDate?datetime}</span>
					</td>
					<td>
						<span title="${member.createDate}">${member.createDate}</span>
					</td>
					<td>
						<span class="green">${(member.enabled && !member.locked)?string('正常', '停用或锁定')}</span>
					</td>
					<td>
						<a href="view!${member.id}.do">[查看]</a>
						<a href="edit!${member.id}.do">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
		
	</form>
</body>
</html>