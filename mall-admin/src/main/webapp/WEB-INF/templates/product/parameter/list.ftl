<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品参数</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript">
$(function(){
	$('table').tablesorter({
		widgets        : ['zebra', 'columns'],
		usNumberFormat : false,
		sortReset      : true,
		sortRestart    : true
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="/home.do">首页</a> &raquo; 参数列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
					<a href="javascript:;" value="name" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" value="product_category" name="productCategory">绑定分类</a>
				</th>
				<th>
					<span>参数</span>
				</th>
				<th>
					<a href="javascript:;" value="orders" name="order" >排序</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			
			<#list groupList as group>
			<#if group??>
				<tr>
					<td>
						<input type="checkbox" name="ids" value=${group.id} />
					</td>
					<td>
						${group.name}
					</td>
					<td>
						<#if group.productCategory??>
							${group.productCategory.name}
						</#if>
					</td>
					<td>
					<#if group.parameters?has_content> 
						<#list group.parameters as param>
							${param.name}&nbsp;
							<#if param_index gt 6>
							...<#break>
							</#if>
						</#list>
					</#if>
					</td>
					<td>
						<#if group.orders??>${group.orders}</#if>
					</td>
					<td>
						<a href="edit.do?groupId=${group.id}">[编辑]</a>
					</td>
				</tr>
			</#if>
			</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>