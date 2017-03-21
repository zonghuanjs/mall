<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>芯豆消费信息</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 芯豆消费信息 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	   <div class="search_input">
			<table class="input">
						
				<tr>
					<td width="33%">
						会员名称:<input type="text" class="text" name="username" <#if username?has_content>value="${username}"</#if> />
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>				
				</tr>
				<tr>
					<td>
						使用类型:<input type="checkbox"  name="type" value="1" <#if types?? && types?seq_contains("1")>checked="checked"</#if>/>订单消费
					</td>
					<td>
						<input type="checkbox"  name="type" value="2" <#if types?? && types?seq_contains("2")>checked="checked"</#if>/>过期清理
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>				
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<input id="searchButton" type="button" class="button" value="筛选" />
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>				
				</tr>
		
		</table>
			
		</div>
	
	
		<div class="bar">			
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
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					会员名称
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">使用时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">使用类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="optValue">使用数量</a>
				</th>
				<th>
					关联订单
				</th>						
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list beanUseds as beanUsed>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${beanUsed.id}" />
					</td>
					<td>
						<span title="会员名称">${beanUsed.member.username}</span>
					</td>
					<td>
						<span title="使用时间">${beanUsed.createDate!""}</span>
					</td>					
					<td>
						<span title="使用类型">${beanUsed.typeView}</span>
					</td>
					<td>
						<span title="使用数量">${beanUsed.optValue}</span>
					</td>
					<td>
						<span title="关联订单"><#if beanUsed.order?has_content>${beanUsed.order.sn}<#else>-</#if></span>
					</td>
								
					<td>
						<a href="view.do?id=${beanUsed.id}">[查看]</a>
					</td>
				</tr>
			</#list>	
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>