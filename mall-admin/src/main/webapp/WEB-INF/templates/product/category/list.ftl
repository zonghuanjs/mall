<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品分类列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="home.do">首页</a> &raquo; 商品分类列表<span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
	</div>
	<table id="listTable" class="list">
		<tr>
			<th class="check">
				<input type="checkbox" id="selectAll" />
			</th>
			<th>
				<a href="javascript:;" class="sort" name="id">ID</a>
			</th>
			<th>
				<a href="javascript:;" class="sort" name="name">名称</a>
			</th>
			<th>
				<a href="javascript:;" class="sort" name="parent">上级分类</a>
			</th>
			<th>
				<a href="javascript:;" class="sort" name="orders">排序</a>
			</th>
			<th>
				<span>操作</span>
			</th>
		</tr>
		<#list categoryList as category>
			<tr>
				<td>
						<input type="checkbox" name="ids" value=${category.id} />
				</td>
				<td>
						${category.id}
				</td>
				<td>
					<span style="margin-left: 0px; color: #000000;">
						${category.name}
					</span>
				</td>
				<td>
					<#if category.parent??>
						${category.parent.name}
					<#else>
						顶层分类
					</#if>
				</td>
				<td>
					${category.orders}
				</td>
				<td>
					<a href="${base}/product/list.do?id=${category.id}" target="_blank">[查看]</a>
					<a href="edit.do?id=${category.id}">[编辑]</a>
				</td>
			</tr>
		</#list>
	</table>
	<#include "../../common/pager.ftl">
</form>
</body>
</html>