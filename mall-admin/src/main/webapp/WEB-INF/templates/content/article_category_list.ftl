<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>文章分类列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 文章分类列表 <span>(共<span id="pageTotal">${articlecategoryList?size}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="${base}/admin/articlecategory/add.do" class="iconButton">
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
					<input type="text" id="searchValue" name="searchValue" <#if searchValue??> value="${searchValue}" </#if> maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "name"> class="current"</#if> val="name">名称</a>
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
				<span>名称</span>
			</th>
			<th>
				<span>排序</span>
			</th>
			<th>
				<span>操作</span>
			</th>
		</tr>
		<#if articlecategoryList??>
		<#list articlecategoryList as articlecategory>
			<tr>
			    <td>
					<input type="checkbox" name="ids"  value="${articlecategory.id}"/>
				</td>
				<td>
					<span style="margin-left: 0px; color: #000000;">
						${articlecategory.name}
					</span>
				</td>
				<td>
					${articlecategory.orders}
				</td>
				<td>
					<a href="${base}/admin/articlecategory/edit!${articlecategory.id}.do">[编辑]</a>					
				</td>
			</tr>
		</#list>	
		</#if>
	</table>
    <#include "../common/pager.ftl">
	</form>
	
	
</body>
</html>