<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>库存管理</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
</head>

<body>
	<div class="path">
		<a href="${base}/index.do">首页</a> &raquo; 库存管理 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "name"> class="current"</#if> val="name">商品名称</a>
						</li>
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "sn">class="current"</#if> val="sn">商品编号</a>
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
					<a href="javascript:;" class="sort" name="sn">商品编号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">商品名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="spec">商品规格</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="marketable">上架状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="members">收藏数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="stock">库存</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="sales">销量</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#if products?has_content>
			<#list products as product>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${product.id}" />
					</td>
					<td>
						${product.sn}
					</td>
					<td>
						<#if product.name?length gt 50>${product.name[0..49]}...<#else>${product.name}</#if>						
					</td>
					<td>
						<span title="商品规格">
							${product.specs!'-'}
						</span>
					</td>
					<td>
						<#if product.marketable><span class="trueIcon">&nbsp;</span><#else><span class="falseIcon"></#if>
					</td>
					<td>
						${product.num!0}
					</td>
					<td>
						${product.stock!0}
					</td>
					<td>
						${product.sales!0}
					</td>
					<td>
						<a href="edit.do?id=${product.id}">[编辑]</a>
					</td>
				</tr>
			</#list>
		</#if>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>