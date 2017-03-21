<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>抽奖活动管理</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 抽奖活动管理 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "name"> class="current"</#if> val="name">名称</a>
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
					<a href="javascript:;" class="sort" name="name">抽奖名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="enabled">是否启用</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">抽奖状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="beginDate">开始日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="endDate">结束日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="number">可参与数量</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="product">抽奖商品</a>
				</th>	
				<th>
					<a href="javascript:;" class="sort" name="price">抽奖价格</a>
				</th>				
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list lotterys as lottery>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${lottery.id}" />
					</td>
					<td>
						<span title="抽奖名称">${lottery.name}</span>
					</td>
					<td>
						<#if lottery.enabled>
							<span class="green" title="是否启用">已启用</span>
						<#else>
							<span class="red" title="是否启用">未启用</span>
						</#if>
						
					</td>
					<td>
						<span title="抽奖状态">
						  <#switch lottery.status>
						  	<#case 0>已开奖<#break>
						  	<#case 1>等待开奖<#break>
						  </#switch>
						</span>
					</td>
					<td>
						<span title="开始时间">${lottery.beginDate?datetime}</span>
					</td>
					<td>
						<span title="结束时间">${lottery.endDate?datetime}</span>
					</td>
					<td>
						<span title="可参与数量">${lottery.number}</span>
					</td>
					<td>
						<span title="抽奖奖品"><#if lottery.product?has_content>${lottery.product.name}</#if></span>
					</td>
					<td>
						<span title="抽奖价格">${config.currencySign}${lottery.price?string('0.00')}</span>
					</td>
					<td>
						<span title="创建时间">${lottery.createDate?datetime}</span>
					</td>
					<td>
						<a href="edit.do?id=${lottery.id}">[编辑]</a>
						<a href="view.do?id=${lottery.id}">[详情]</a>
						<a href="${base}/lottery/reward-${lottery.id}.do" target="_blank">[预览]</a>
					</td>
				</tr>
			</#list>	
		</table>
		
		<#include "../common/pager.ftl" >
	</form>
</body>
</html>