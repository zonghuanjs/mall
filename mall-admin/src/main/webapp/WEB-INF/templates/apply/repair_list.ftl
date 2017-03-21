<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>维修列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 维修申请列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="status" name="status" <#if status??>value="${status}" </#if>/>
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
								<a href="javascript:;" name="status" val="-1" <#if status??&&status == "-1"> class="checked"</#if>>关闭</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="0" <#if status??&&status == "0"> class="checked"</#if>>已完成</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="1" <#if status??&&status == "1"> class="checked"</#if>>未审核</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="2" <#if status??&&status == "2"> class="checked"</#if>>审核通过</a>
							</li>	
							<li>
								<a href="javascript:;" name="status" val="3" <#if status??&&status == "3"> class="checked"</#if>>审核不通过</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="4" <#if status??&&status == "4"> class="checked"</#if>>用户寄货</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="5" <#if status??&&status == "5"> class="checked"</#if>>确认收货</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="6" <#if status??&&status == "6"> class="checked"</#if>>故障检测</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="7" <#if status??&&status == "7"> class="checked"</#if>>维修</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="8" <#if status??&&status == "8"> class="checked"</#if>>发回用户</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="9" <#if status??&&status == "9"> class="checked"</#if>>用户确认收货</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="10" <#if status??&&status == "10"> class="checked"</#if>>物品检测</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="11" <#if status??&&status == "11"> class="checked"</#if>>物品检测完成</a>
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
					<a href="javascript:;" class="sort">申请会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">申请状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">维修状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">申请理由</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">申请日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list repairs as repair>
				<tr>
				<td>
					<input type="checkbox" name="ids" value="${repair.id}" />
				</td>
				<td>${repair.sn}</td>
				<td>${repair.member.username}</td>
				<td>${repair.statusView}</td>
				<td><#if repair.repair??>${repair.repair.statusView}<#else>-</#if></td>
				<td><#if repair.reason?length gt 20>${repair.reason[0..20]}...<#else>${repair.reason!''}</#if></td>
				<td>${repair.createDate}</td>
				<td>
					<a href="${base}/admin/repair/view!${repair.id}.do">[查看]</a>
				</td>
			</tr>
			</#list>
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>