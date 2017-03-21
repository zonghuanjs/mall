<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
</head>
<body>

	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 问题反馈列表 <span>(共<span id="pageTotal">${feedbacks?size}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="type" name="type" <#if type?has_content>value="${type}"</#if>/>
	<input type="hidden" id="isViewed" name="isViewed" <#if isViewed?has_content>value="${isViewed}"</#if>/>
		<div class="bar">
			<div class="buttonWrap">
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
								<a href="javascript:;" name="type" val="1" <#if type?has_content&&type == "1"> class="checked"</#if>>功能异常</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="2" <#if type?has_content&&type == "2"> class="checked"</#if>>体验问题</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="3" <#if type?has_content&&type == "3"> class="checked"</#if>>优化建议</a>
							</li>	
							<li>
								<a href="javascript:;" name="type" val="99" <#if type?has_content&&type == "99"> class="checked"</#if>>其他问题</a>
							</li>	
							<li class="separator">
								<a href="javascript:;" name="isViewed" val="true" <#if isViewed?has_content&&isViewed == "true"> class="checked"</#if>>已查看</a>
							</li>	
							<li>
								<a href="javascript:;" name="isViewed" val="false" <#if isViewed?has_content&&isViewed == "false"> class="checked"</#if>>未查看</a>
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
							<a href="javascript:;" val="username" <#if searchProperty??&&searchProperty == "username"> class="current"</#if>>用户名</a>
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
					用户名
				</th>
				<th>
					类型
				</th>
				<th>
					是否查看
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list feedbacks as feedback>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${feedback.id}" />
					</td>
					<td>
						${feedback.member.username}
					</td>
					<td>
						${feedback.typeView!''}
					</td>
					<td>
					    <#if feedback.viewed>是<#else>否</#if>						
					</td>
					<td>
						<span title="${feedback.createDate}">${feedback.createDate}</span>
					</td>
					<td>
						<a href="${base}/admin/feedback/view.do?id=${feedback.id}">[查看处理]</a>
					</td>
				</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>