<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>芯豆获取信息</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 芯豆获取信息 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
						是否被使用:<input type="checkbox"  name="used" value="true" <#if used?? && used == "true">checked="checked"</#if>/>已被使用
					</td>
					<td>
						是否过期:<input type="checkbox"  name="expired" value="true" <#if expired?? && expired == "true">checked="checked"</#if>/>已过期
					</td>
					<td>
						&nbsp;
					</td>				
				</tr>
				<tr>
					<td>
						领取类型:<input type="checkbox"  name="type" value="1" <#if types?? && types?seq_contains("1")>checked="checked"</#if>/>购物回馈
					</td>
					<td>
						<input type="checkbox"  name="type" value="2" <#if types?? && types?seq_contains("2")>checked="checked"</#if>/>评价奖励
					</td>
					<td>
						<input type="checkbox"  name="type" value="3" <#if types?? && types?seq_contains("3")>checked="checked"</#if>/>晒单奖励
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
					<a href="javascript:;" class="sort" name="createDate">领取时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">领取类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="optValue">领取数量</a>
				</th>
				<th>
					是否被使用
				</th>
				<th>
					<a href="javascript:;" class="sort" name="usedTime">用完时间</a>
				</th>
				<th>
					是否已经失效
				</th>
				<th>
					<a href="javascript:;" class="sort" name="expiredTime">自然失效时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="usedValue">已使用数量</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list beanRetains as beanRetain>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${beanRetain.id}" />
					</td>
					<td>
						<span title="会员名称">${beanRetain.member.username}</span>
					</td>
					<td>
						<span title="领取时间">${beanRetain.createDate!""}</span>
					</td>					
					<td>
						<span title="领取类型">${beanRetain.typeView}</span>
					</td>
					<td>
						<span title="领取数量">${beanRetain.optValue}</span>
					</td>
					<td>
						<span title="是否被使用">${beanRetain.used?string('已使用', '未使用')}</span>
					</td>
					<td>
						<span title="使用时间">${beanRetain.usedTime!"-"}</span>
					</td>
					<td>
						<span title="是否过期">${beanRetain.isExpired?string('已过期', '未过期')}</span>
					</td>
					<td>
						<span title="过期时间">${beanRetain.expiredTime!"-"}</span>
					</td>
					<td>
						<span title="使用数量">${beanRetain.usedValue}</span>
					</td>
					<td>
						<a href="view.do?id=${beanRetain.id}">[查看]</a>
					</td>
				</tr>
			</#list>	
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>