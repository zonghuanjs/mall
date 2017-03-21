<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>优惠券管理</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/coupon_code.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 优惠券管理 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
					<a href="javascript:;" class="sort" name="name">优惠名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="begin_date">起始日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="end_date">结束日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="number">总发放数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="day_number">日发放数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="num">领取数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="create_date">创建时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list coupons as coupon>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${coupon.id}" />
					</td>
					<td>
						<span title="优惠信息">${coupon.name}</span>
					</td>
					<td>
						<span title="${coupon.beginDate?datetime}">${coupon.beginDate?datetime}</span>
					</td>
					<td>
						<span title="${coupon.endDate?datetime}">${coupon.endDate?datetime}</span>
					</td>
					<td>
						<span title="总发放数量">${coupon.number}</span>
					</td>
					<td>
						<span title="日发放数量">${coupon.dayNumber}</span>
					</td>
					<td>
						<span title="领取数量">${coupon.num}</span>
					</td>
					<td>
						<span title="${coupon.createDate?datetime}">${coupon.createDate?datetime}</span>
					</td>
					<td>
						<a href="edit.do?id=${coupon.id}">[编辑]</a>
						<span onclick="showObtainLinks(${coupon.id})" class="button">查看</span>
					</td>
				</tr>
			</#list>	
		</table>
		<#include "../common/pager.ftl">
	</form>
</body>
</html>