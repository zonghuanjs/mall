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
		<a href="${base}/admin/index.do">首页</a> &raquo; 会员列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">			
			<div class="buttonWrap">			
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
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "username"> class="current"</#if> val="username">用户名</a>
						</li>
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "mobile"> class="current"</#if> val="mobile">手机号</a>
						</li>
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "email"> class="current"</#if> val="email">E-mail</a>
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
					<a href="javascript:;" class="sort" name="memberRank">会员等级</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="mobile">注册手机</a>
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
						<span title="注册手机">
						<#if member.mobile?has_content>${member.mobile}</#if>
						</span>
					</td>
					<td>
						${member.email!'-'}
					</td>
					<td>
						<span title="注册IP">${member.registerIP!'-'}</span>
					</td>
					<td>
						<span title="最后登录"><#if member.loginDate?has_content>${member.loginDate?datetime}<#else>-</#if></span>
					</td>
					<td>
						<span title="${member.createDate}">${member.createDate}</span>
					</td>
					<td>
						<span class="green">${(member.enabled && !member.locked)?string('正常', '停用或锁定')}</span>
					</td>
					<td>
						<a href="view!${member.id}.do">[查看]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>