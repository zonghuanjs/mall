<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>售后申请单列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 售后申请单列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="type" name="type" <#if type??>value="${type}" </#if>/>
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
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
								<a href="javascript:;" name="type" val="1" <#if type??&&type == "1"> class="checked"</#if>>退货</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="2" <#if type??&&type == "2"> class="checked"</#if>>换货</a>
							</li>
							<li>
								<a href="javascript:;" name="type" val="3" <#if type??&&type == "3"> class="checked"</#if>>维修</a>
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
								<a href="javascript:;" val="100"<#if pager.pageSize==100>class="current"</#if>>100</a>
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
							<a href="javascript:;" val="sn" <#if searchProperty??&&searchProperty == "sn"> class="current"</#if>>编号</a>
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
					<a href="javascript:;" class="sort" name="createDate">申请会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">申请类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">申请状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="reason">申请理由</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">申请时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list applys as apply>
			<tr>
				<td>
					<input type="checkbox" name="ids" id="ids" value="${apply.id}"/>
				</td>
				<td>${apply.sn}</td>
				<td>${apply.member.username}</td>
				<td>${apply.typeView}</td>
				<td>${apply.statusView}</td>
				<td><#if apply.reason?length gt 20>${apply.reason[0..20]}...<#else>${apply.reason!''}</#if></td>
				<td>${apply.createDate}</td>
				<td>
					<a href="${base}/admin/apply/view!${apply.id}.do">[查看]</a>
				</td>
			</tr>
		</#list>
		</table>
<input type="hidden" id="pageSize" name="pageSize" value="${pager.pageSize}" />
<input type="hidden" id="searchProperty" name="searchProperty" <#if searchProperty??>value="${searchProperty}"</#if> />
<input type="hidden" id="orderProperty" name="orderProperty" value="" />
<input type="hidden" id="orderDirection" name="orderDirection" value="" />
<input type="hidden" id="pageCount" value="${pager.pageCount}" />
<div class="pagination">			
			<a class="firstPage" href="javascript: $.pageSkip(1);">&nbsp;</a>
		<#if pager.currentIdx gt 1>
			<a class="previousPage" href="javascript: $.pageSkip(${pager.currentIdx -1});">&nbsp;</a>
		</#if>
		<#if pager.prePageBreak>
			<span class="pageBreak">...</span>
		</#if>
		<#list pager.start .. pager.end as k>
			<#if k==pager.currentIdx>
				<span class="currentPage">${k}</span>
			<#else>
				<a href="javascript: $.pageSkip(${k});">${k}</a>
			</#if>
		</#list>
		<#if pager.end lt pager.pageCount>
			<span class="pageBreak">...</span>
		</#if>
		<#if pager.nextPageBreak>
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