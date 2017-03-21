<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>消息管理</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 消息管理列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="${base}/admin/salesmessage/add.do" class="iconButton">
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
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "title"> class="current"</#if> val="title">标题</a>
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
					<a href="javascript:;" class="sort" name="type">消息类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="contentType">消息内容类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="beginDate">消息推送开始时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="endDate">消息推送结束时间</a>
				</th>
				<th style="width: 40%;">
					<a href="javascript:;" class="sort" name="content" >消息标题</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list messages as message>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${message.id}" />
					</td>
					<td>
						<span>
						<#switch message.type>
						<#case 1>系统消息<#break>
						<#case 2>优惠券消息<#break>
						<#case 3>促销消息<#break>
						</#switch>
						</span>
					</td>
					<td>
						<span>
						<#switch message.contentType>
						<#case 1>文本<#break>
						<#case 2>图片<#break>
						</#switch>
						</span>
					</td>
					<td>
						<span>${message.beginDate}</span>
					</td>
					<td>
						<span>${message.endDate}</span>
					</td>
					<td>
						<span>${message.title}</span>
					</td>
					<td>
						<a href="${base}/admin/salesmessage/edit!${message.id}.do">[编辑]</a>
						<#--<a href="${activity.url}" target="_blank">[查看]</a>-->
					</td>
				</tr>
			</#list>	
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>