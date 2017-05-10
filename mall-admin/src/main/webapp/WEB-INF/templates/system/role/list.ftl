<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources//css/list.css"/>
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
</head>
<body>
	<div>
		<div class="path">
			<a href="home.do">首页</a> &raquo;角色列表<span>(共<span id="pageTotal">${roleList?size}</span>条记录)</span>
		</div>
		<form id="listForm" action="" method="get">
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
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isSystem">是否内置</a>
				</th>
				<th>
					<span>描述</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list roleList as role>
			<tr>
				<td>
					<input type="checkbox" name="ids" title="系统内置角色不允许删除" <#if role.system>disabled="disabled"<#else>value="${role.id}"</#if> />
				</td>
				<td>
					${role.name}
				</td>
				<td>
					${role.system?string("是", "否")}
				</td>
				<td>
					<span title="${role.description}">${role.description}</span>
				</td>
				<td>
					<span title="${role.createDate}">${role.createDate}</span>
				</td>
				<td>
					<a href="edit.do?id=${role.id}">[编辑]</a>
				</td>
			</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
   </div>
</body>
</html>