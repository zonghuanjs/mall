<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>商品管理</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<style type="text/css">
.moreTable th {
	width: 80px;
	line-height: 25px;
	padding: 5px 10px 5px 0px;
	text-align: right;
	font-weight: normal;
	color: #333333;
	background-color: #f8fbff;
}

.moreTable td {
	line-height: 25px;
	padding: 5px;
	color: #666666;
}

.promotion {
	color: #cccccc;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $moreButton = $("#moreButton");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
	// 商品筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 商品列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/admin/product/list.do" method="get">
		<input type="hidden" id="productCategoryId" name="productCategoryId" <#if productCategoryId??>value="${productCategoryId}" </#if> />
		<input type="hidden" id="brandId" name="brandId" <#if brandId??>value="${brandId}" </#if> />
		<input type="hidden" id="promotionId" name="promotionId"<#if promotionId??>value="${promotionId}" </#if> />
		<input type="hidden" id="tagId" name="tagId" <#if tagId??>value="${tagId}" </#if> />
		<input type="hidden" id="marketable" name="marketable" <#if marketable??>value="${marketable}" </#if> />
		<input type="hidden" id="list" name="list" <#if list??>value="${list}" </#if> />
		<input type="hidden" id="top" name="top" <#if top??>value="${top}" </#if> />
		<input type="hidden" id="gift" name="gift" <#if gift??>value="${gift}" </#if> />
		<input type="hidden" id="stock" name="stock" <#if stock??>value="${stock}" </#if> />
		<div class="bar">
		<#if authorities?seq_contains('admin:product:add')>
			<a href="${base}/admin/product/add.do" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
		</#if>
			<div class="buttonWrap">
			<#if authorities?seq_contains('admin:product:delete')>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
			</#if>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						商品筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="marketable" val="true" <#if marketable??&&marketable=="true"> class="checked"</#if>>已上架</a>
							</li>
							<li>
								<a href="javascript:;" name="marketable" val="false" <#if marketable??&&marketable=="false"> class="checked"</#if>>未上架</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="list" val="true" <#if list??&&list=="true"> class="checked"</#if>>已列出</a>
							</li>
							<li>
								<a href="javascript:;" name="list" val="false" <#if list??&&list=="false"> class="checked"</#if>>未列出</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="top" val="true" <#if top??&&top=="true"> class="checked"</#if>>已置顶</a>
							</li>
							<li>
								<a href="javascript:;" name="top" val="false" <#if top??&&top=="false"> class="checked"</#if>>未置顶</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="gift" val="true" <#if gift??&&gift=="true"> class="checked"</#if>>赠品</a>
							</li>
							<li>
								<a href="javascript:;" name="gift" val="false" <#if gift??&&gift=="false"> class="checked"</#if>>非赠品</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="stock" val="0" <#if stock??&&stock=="0"> class="checked"</#if>>缺货</a>
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
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if> >10</a>
							</li>
							<li>
								<a href="javascript:;" val="20" <#if pager.pageSize==20>class="current"</#if> >20</a>
							</li>
							<li>
								<a href="javascript:;" val="50" <#if pager.pageSize==50>class="current"</#if> >50</a>
							</li>
							<li>
								<a href="javascript:;" val="100" <#if pager.pageSize==100>class="current"</#if> >100</a>
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
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "sn"> class="current"</#if> val="sn">编号</a>
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
					<a href="javascript:;" class="sort" name="sn">编号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="productCategory">商品分类</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">销售价</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="stock">库存</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="list" >是否列出</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" >是否上架</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list productList as product>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${product.id}" />
					</td>
					<td>
						${product.sn!''}
					</td>
					<td>
						<#if product.name?length gt 25>${product.name[0..25]}...<#else>${product.name}</#if>
						<#if product.specificationValues?has_content>[<#list product.specificationValues as value>${value.name}<#if value_has_next>&nbsp;</#if></#list>]</#if>
					</td>
					<td>
						<#if product.productCategory??>${product.productCategory.name}</#if>
					</td>
					<td>
						￥${product.price?string('0.00')}
					</td>
					<td>
						${product.stock}
					</td>
					<td>
						<#if product.list><span class="trueIcon">&nbsp;</span><#else><span class="falseIcon"></#if>
					</td>
					<td>
						<#if product.marketable><span class="trueIcon">&nbsp;</span><#else><span class="falseIcon"></#if>
					</td>
					<td>
						<span title="${product.createDate!'-'}">${product.createDate!'-'}</span>
					</td>
					<td>
					<#if authorities?seq_contains('admin:product:edit')>
						<a href="${base}/admin/product/edit.do?id=${product.id}">[编辑]</a>
					</#if>
						<a href="${base}/product/product!${product.id}.do" target="_blank">[查看]</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "../common/pager.ftl">
	</form>
	
</body>
</html>