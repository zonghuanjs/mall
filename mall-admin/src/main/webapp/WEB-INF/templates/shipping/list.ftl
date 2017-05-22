<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发货单列表 </title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript">
$().ready(function() {

	

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 发货单列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<div class="buttonWrap">
			<#if adminView.authorities?seq_contains('admin:shipping:delete')>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
			</#if>
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
					<input type="text" id="searchValue" name="searchValue"  <#if searchValue??>value="${searchValue}"</#if> maxlength="200"/>
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="sn" <#if searchProperty??&&searchProperty == "sn"> class="current"</#if>>编号</a>
						</li>
						<li>
							<a href="javascript:;" val="trackingNo" <#if searchProperty??&&searchProperty == "trackingNo"> class="current"</#if>>运单号</a>
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
					<a href="javascript:;" class="sort" name="shippingMethod">配送方式</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="deliveryCorp">物流公司</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="trackingNo">运单号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="consignee">收货人</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list shippingList as shipping>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${shipping.id}" />
					</td>
					<td>
						${shipping.sn}
					</td>
					<td>
						${shipping.shippingMethod.name}
					</td>
					<td>
						${shipping.deliveryCorp}
					</td>
					<td>
						${shipping.trackingNo}
					</td>
					<td>
						${shipping.consignee}
					</td>
					<td>
						<span title="${shipping.createDate?string("yyyy-MM-dd HH:mm:ss")}">${shipping.createDate}</span>
					</td>
					<td>
						<a href="${base}/admin/shipping/view!${shipping.id}.do">[查看]</a>
						<a href="${base}/admin/shipping/edit!${shipping.id}.do">[编辑]</a>
					</td>
				</tr>
			</#list>
		</table>
        <#include "../common/pager.ftl">	
</form>
</body>
</html>