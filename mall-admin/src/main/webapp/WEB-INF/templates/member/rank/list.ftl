<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员等级列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {


});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/index.do">首页</a> &raquo; 会员等级列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/member_rank/list.do" method="get">
		<div class="bar">
			<a href="${base}/member_rank/add.do" class="iconButton">
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
								<a href="javascript:;" <#if pager.pageSize==10>class="current"</#if> val="10">10</a>
							</li>
							<li>
								<a href="javascript:;" <#if pager.pageSize==20>class="current"</#if> val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" <#if pager.pageSize==50>class="current"</#if> val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" <#if pager.pageSize==100>class="current"</#if> val="100">100</a>
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
							<a href="javascript:;" <#if searchValue??&&searchValue=="name">class="current"</#if> val="name">名称</a>
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
					<a href="javascript:;" class="sort" name="name">等级</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="scale">优惠比例</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">消费金额</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isDefault">是否默认</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list ranks?sort_by("level") as rank>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${rank.id}" />
					</td>
					<td>
						${rank.name}
					</td>
					<td>
						${rank.level}
					</td>
					<td>
						${rank.scale}
					</td>
					<td>
						${rank.amount}
					</td>
					<td>
						${rank.isDefault?string('是','否')}
					</td>
					<td>
						<a href="edit.do?id=${rank.id}">[编辑]</a>
					</td>
				</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>