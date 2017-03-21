<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/task_job.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 任务列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="add.do" class="iconButton">
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
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "Name"> class="current"</#if> val="Name">名称</a>
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
					<a href="javascript:;" class="sort" name="name">任务名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">任务状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="cronExpression">时间表达式</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>描述</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list tasks as task>
			<tr>
				<td>
					<input type="checkbox" name="ids" title="系统内置角色不允许删除" value="${task.id}" />
				</td>
				<td>
					${task.name}
				</td>
				<td>
					<#switch task.status>
						<#case 1>已启用<#break>
						<#case 0>未启用<#break>
					</#switch>
				</td>
				<td>
					<span title="时间表达式">${task.cronExpression}</span>
				</td>
				<td>
					<span title="${task.createDate?datetime}">${task.createDate?datetime}</span>
				</td>
				<td>
					${task.description}
				</td>
				<td>
					<a href="edit.do?id=${task.id}">[编辑]</a>
				<#if task.status==1>
					<a href="javascript:doTask(${task.id})">[立即执行]</a>
					<a href="javascript:stopTask(${task.id})">[停用]</a>
				<#else>
					<a href="javascript:startTask(${task.id})">[启动任务]</a>
				</#if>
				</td>
			</tr>
		</#list>
		</table>
		<#include "../common/pager.ftl">
	</form>
   </div>
</body>
</html>