<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>标签列表 </title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
</head>
<body>
    <#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 标签列表 <span>(共<span id="pageTotal">4</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="type" name="type" <#if type??>value="${type}" </#if>/>
		<div class="bar">
			<a href="${base}/admin/tag/add.do" class="iconButton">
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
							<li>
								<a href="javascript:;" name="type" val="1" <#if type??&&type == "1"> class="checked"</#if>>文章标签</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="2" <#if type??&&type == "2"> class="checked"</#if>>商品标签</a>
							</li>														
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
					<a href="javascript:;" class="sort" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="icon">图标</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">排序</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list tagList as tag>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${tag.id}" />
					</td>
					<td>
						<#if tag??&&tag.name??>
						${tag.name}
						</#if>
					</td>
					<td>
						<#if tag??&&tag.type==1>
						文章标签
						<#else>
						商品标签
						</#if>
					</td>
					<td>
					    <#if tag?has_content && tag.type==2 && tag.icon?has_content>
							<a href="${mall_url(tag.icon, base)}" target="_blank">查看</a>
						<#else>
						    -
						</#if>	
					</td>
					<td>
						<#if tag??&&tag.orders??>
						${tag.orders}
						<#else>
						-
						</#if>
					</td>
					<td>
						<#if tag??&&tag.createDate??>
						${tag.createDate}
						<#else>
						-
						</#if>
					</td>
					<td>
						<a href="${base}/admin/tag/edit!${tag.id}.do">[编辑]</a>
					</td>
				</tr>
				
			</#list>	
				
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>