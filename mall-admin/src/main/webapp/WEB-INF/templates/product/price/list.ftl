<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>价格管理</title>
<link rel="stylesheet" type="text/css" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>

<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 价格管理 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="list.do" method="get">
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
					<a href="javascript:;" class="sort" name="specs">商品规格</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">销售价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="appPrice">APP销售价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="cost">成本价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="marketPrice">市场价</a>
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
							<#if product.specificationValues?has_content>
								[<#list product.specificationValues as sv>
									${sv.name}
									<#if sv_has_next>,</#if>
								</#list>]
							<#else>
								-
							</#if>
						</span>
					</td>
					<td>
						￥${product.price}
					</td>
					<td>
						<span title="app专享价格">￥${product.appPrice}</span>
					</td>
					<td>
						￥${product.cost}
					</td>
					<td>
						￥${product.marketPrice}
					</td>
					<td>
						<a href="edit.do?id=${product.id}">[编辑]</a>
					</td>
				</tr>
			</#list>
		</#if>
		</table>
		<input type="hidden" id="pageSize" name="pageSize" value="${pager.pageSize}" />
		<input type="hidden" id="searchProperty" name="searchProperty" <#if searchProperty??>value="${searchProperty}"</#if> />
		<input type="hidden" id="orderProperty" name="orderProperty" <#if orderProperty??>value="${orderProperty}"</#if>/>
		<input type="hidden" id="orderDirection" name="orderDirection" <#if orderDirection??>value="${orderDirection}"</#if>/>
		<input type="hidden" id="pageCount" value="${pager.pageCount}" />
		<div class="pagination">	
			<a class="firstPage" href="javascript: $.pageSkip(1);">&nbsp;</a>
		<#if pager.prePageBreak>
			<span class="pageBreak">...</span>
		</#if>
		<#if pager.currentIdx gt 1>
			<a class="previousPage" href="javascript: $.pageSkip(${pager.currentIdx -1});">&nbsp;</a>
		</#if>
		<#list pager.start .. pager.end as k>
			<#if k==pager.currentIdx>
				<span class="currentPage">${k}</span>
			<#else>
				<a href="javascript: $.pageSkip(${k});">${k}</a>
			</#if>
		</#list>
		<#if pager.nextPageBreak>
			<span class="pageBreak">...</span>
		</#if>
		<#if pager.currentIdx < pager.pageCount>
			<a class="nextPage" href="javascript: $.pageSkip(${pager.currentIdx + 1});">&nbsp;</a>
		</#if>
			<a class="lastPage" href="javascript: $.pageSkip(${pager.pageCount});">&nbsp;</a>
			<span class="pageSkip">
				共${pager.pageCount}页 到第<input id="pageNumber" name="pageNumber" value="${pager.currentIdx}" maxlength="9" onpaste="return false;" />页<button type="submit">&nbsp;</button>
			</span>
		</div>
	</form>
</body>
</html>