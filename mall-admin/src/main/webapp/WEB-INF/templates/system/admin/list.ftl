<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="resources/css/list.css"/>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="resources/js/list.js"></script>
</head>
<body>

	<div class="path">
		<a href="home.html">首页</a> &raquo; 管理员列表 <span>(共<span id="pageTotal">${adminUserList?size}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="admin/add.html" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
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
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if>>10</a>
							</li>
							<li>
								<a href="javascript:;" val="20" <#if pager.pageSize==20>class="current"</#if>>20</a>
							</li>
							<li>
								<a href="javascript:;" val="50" <#if pager.pageSize==50>class="current"</#if>>50</a>
							</li>
							<li>
								<a href="javascript:;" val="100" <#if pager.pageSize==100>class="current"</#if>>100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
	
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" <#if searchValue??>value="${searchValue}"</#if> maxlength="200" />
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="username" <#if searchProperty??&&searchProperty == "username"> class="current"</#if>>用户名</a>
						</li>
						<li>
							<a href="javascript:;" val="email" <#if searchProperty??&&searchProperty == "email"> class="current"</#if>>E-mail</a>
						</li>
						<li>
							<a href="javascript:;" val="name" <#if searchProperty??&&searchProperty == "name"> class="current"</#if>>姓名</a>
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
					<a href="javascript:;" class="sort" name="username">用户名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="email">E-mail</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="role">角色</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">姓名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="department">部门</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginDate">最后登录日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginIp">最后登录IP</a>
				</th>
				<th>
					<span>状态</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list adminUserList as admin>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${admin.id}" />
					</td>
					<td>
						${admin.username}
					</td>
					<td>
						${admin.email!''}
					</td>
					<td>
					<#if admin.roles?has_content>
						<#list admin.roles as role>
							${role.name}
							<#if role_has_next>;</#if>
						</#list>
					</#if>
					</td>
					<td>
						${admin.name!''}
					</td>
					<td>
						${admin.department!''}
					</td>
					<td>
						<span title="${admin.loginDate!'-'}">${admin.loginDate!'-'}</span>
					</td>
					<td>
						${admin.loginIP!'-'}
					</td>
					<td>
						<span class="green">${(admin.enabled && !admin.locked)?string('正常', '锁定')}</span>
					</td>
					<td>
						<span title="${admin.createDate}">${admin.createDate}</span>
					</td>
					<td>
						<a href="edit!${admin.id}.do">[编辑]</a>
					</td>
				</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>