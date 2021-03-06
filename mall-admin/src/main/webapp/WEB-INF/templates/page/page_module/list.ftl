<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>页面模块列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
</head>
<body>
	
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 页面模块列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
		<input type="hidden" id="basePath" value="${base}" />
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="${base}/admin/page/page_module/add.do" class="iconButton">
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
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">模块名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">模块显示顺序</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">显示内容数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">模块类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">页面类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="">创建时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#if pageModules?has_content>
		<#list pageModules as pageModule>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${pageModule.id}" />
					</td>
					<td>
						${pageModule.name}
					</td>
					<td>
						${pageModule.order}
					</td>
					<td>
						${pageModule.count}
					</td>
					<td>
						${pageModule.type}
					</td>
					<td>
						${pageModule.pageType}
					</td>												
					<td>
						<span title="${pageModule.createDate}">${pageModule.createDate}</span>
					</td>
						
					<td>
					    <a href="${base}/admin/page/module_element/add.do?id=${pageModule.id}">[添加模块元素]</a>
						<a href="${base}/admin/page/page_module/view.do?id=${pageModule.id}">[查看]</a>
						<a href="${base}/admin/page/page_module/edit.do?id=${pageModule.id}">[编辑]</a>
					</td>
				</tr>
		</#list>
		</#if>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>