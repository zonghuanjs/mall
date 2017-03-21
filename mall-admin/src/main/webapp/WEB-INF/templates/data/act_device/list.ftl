<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>终端设备列表-${config.siteName}</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 终端设备列表<span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>

	<form id="listForm" action="list.do" method="get">
		<div class="search_input">
			<label>
				<span>起始时间</span>
				<input type="text" class="text Wdate" id="startDate" name="startDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if startDate?has_content>value="${startDate?date}"</#if> />
			</label>
			<label>
				<span>结束时间</span>
				<input type="text" class="text Wdate" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'startDate\')}'});" <#if endDate?has_content>value="${endDate?date}"</#if> />
			</label>
			
			<input id="searchButton" type="button" class="button" value="查询" />
		</div>
		<div class="bar">
			<div class="buttonWrap">
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
				<th>
					<span>设备标识</span>
				</th>
				<th>
					<span>登录会员</span>
				</th>
				<th>
					<span>客户端版本</span>
				</th>
				<th>
					<span>接口版本</span>
				</th>
				<th>
					<span>终端系统版本</span>
				</th>
				<th>
					<span>终端手机型号</span>
				</th>
				<th>
					<span>最近登录时间</span>
				</th>
				<th>
					<span>最近登录IP</span>
				</th>
			</tr>
		<#if devices?has_content>
			<#list devices as device>
			<tr>
				<td>
					${device.device}
				</td>
				<td>
					${device.member!'-'}
				</td>
				<td>					
				    ${device.client}
				</td>
				<td>
				   ${device.server}
				</td>
				<td>
					${device.os}
				</td>
				<td>
					${device.devModel}
				</td>
				<td>
					${device.lastLogin?datetime}
				</td>
				<td>
					${device.ip}
				</td>
			</tr>
			</#list>
		</#if>
		</table>
		
		<#include "../../common/pager.ftl">
	</form>
	
	
</body>
</html>