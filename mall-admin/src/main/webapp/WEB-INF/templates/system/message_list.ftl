<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div>
		<div class="path">
			<a href="${base}/admin/home.do">首页</a> &raquo;短信消息列表<span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
		</div>
		<form id="listForm" action="#" method="get">
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
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue"  maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;"  val="sn">流水号</a>
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
					<a href="javascript:;" class="sort">流水号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">发送状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">内容</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">发送号码</a>
				</th>
				<th>
					<a href="javascript:;" class="sort">描述</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list messageList as message>
			<tr>
				<td>
					<input type="checkbox" name="ids" value="${message.id}" />
				</td>
				<td>
					${message.sn}
				</td>
				<td>
					<#switch message.status>
                         <#case 1>
                                                                                                             发送中                                                                             
                           <#break>
                         <#case 0>
                                                                                                            发送成功 
                           <#break>
                         <#case -1>
                                                                                                           发送失败
                           <#break>                                                
                         <#default>
                                                                                                       处理中
                        </#switch>
				</td>
				<td>
					${message.content!""}
				</td>
				<td>
				<#list message.numbers as number>
				${number}
				</#list>
					
				</td>
				<td>
					${message.description!""}
				</td>
				
				<td>
					${message.createDate!""}
				</td>
				<td>
					<a href="${base}/admin/message/view!${message.id}.do">[查看]</a>
				</td>
			</tr>
		</#list>
		</table>
		<#include "../common/pager.ftl">
	</form>
   </div>
</body>
</html>