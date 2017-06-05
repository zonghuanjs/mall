<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>文章分类列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/select.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 文章列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="">
	<input type="hidden" id="category" name="category" <#if category??>value="${category}" </#if>/>
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
					<a href="javascript:;" id="filterSelect" class="button">
						筛选条件<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
						<#list articlecategorys as articlecategory>
							<li>
								<a href="javascript:;" name="category" val="${articlecategory.id}" <#if category??&&category == "${articlecategory.id}"> class="checked"</#if>>${articlecategory.name}</a>
							</li>
						</#list>																
						</ul>
					</div>
				</div>
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
							<a href="javascript:;" <#if searchProperty??&&searchProperty=="title">class="current"</#if> val="title">标题</a>
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
					<a href="javascript:;" class="sort" name="title">标题</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="articleCategory">文章分类</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isPublication">是否发布</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
        <#list articleList as article>
		<tr>
					<td>
						<input type="checkbox" name="ids" value="${article.id}"/>
					</td>
					<td>
						${article.title}
					</td>
					<td>
					   ${article.category.name}
					</td>
					<td>
					  <#if article.publication>
						<span class="trueIcon">&nbsp;</span>
					  <#else>
					    <span class="falseIcon">&nbsp;</span>
					  </#if>
					</td>
					<td>
						${article.createDate}
					</td>
					<td>
						<a href="edit.do?id=${article.id}">[编辑]</a>
							<a href="${base}/help/article.do?id=${article.id}" target="_blank">[查看]</a>
					</td>
				</tr>
		</#list>
	</table>

	<#include "../../common/pager.ftl">
</form>
	
</body>
</html>