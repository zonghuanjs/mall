<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>宝贝回家数据管理</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 宝贝数据列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="list.do" method="get">
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
					<button type="submit">&nbsp;</button>
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
					<a href="javascript:;" class="sort" name="name">失踪者姓名</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="gender">性别</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="birth">生日</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="heights">失踪时身高</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loseTime">失踪时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loseAddr">失踪地点</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list babys as baby>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${baby.id}" />
					</td>
					<td>
						${baby.name}
					</td>
					<td>
						<span title="性别">${baby.gender!'-'}</span>
					</td>
					<td>
						<span title="生日">${baby.birth!'-'}</span>
					</td>
					<td>
						<span title="失踪时身高">${baby.heights!'-'}</span>
					</td>
					<td>
						<span title="失踪时间">${baby.loseTime!'-'}</span>
					</td>
					<td>
						<span title="失踪地点">${baby.loseAddr!'-'}</span>
					</td>
					<td>
						<a href="edit.do?id=${baby.id}">[编辑]</a>
					</td>
				</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>