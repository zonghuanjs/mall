<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>批量退款单列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 批量退款单列表 
		<span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
		<span style="color: red">【此页只显示支付插件为支付宝，且为批量退款的操作记录】</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="status" name="status" <#if status??>value="${status}" </#if>/>
		<div class="bar">
			<div class="buttonWrap">
			<#if adminView.authorities?seq_contains('admin:refund:delete')>	
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
			</#if>				
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						退款筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="status" val="0" <#if status??&&status == "0"> class="checked"</#if>>退款完成</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="1" <#if status??&&status == "1"> class="checked"</#if>>退款处理中</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="-1" <#if status??&&status == "-1"> class="checked"</#if>>退款失败</a>
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
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if> >10</a>
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
					<a href="javascript:;" class="sort" name="createDate">退款时间</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">退款总金额</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="payee">退款笔数</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">退款状态</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#if batches?has_content>
			<#list batches as batch>
				<tr>
				    <td>
						<input type="checkbox" name="ids" value="${batch.id}" />
					</td>
					<td>
						<span title="退款批次号">${batch.sn}</span>
					</td>
					<td>
						<span title="退款时间">${batch.createDate?datetime}</span>
					</td>
					<td>
						<span title="退款总金额"></span>
					</td>
					<td>
						<span title="退款笔数">${batch.refunds?size}</span>
					</td>
					<td>
						<#switch batch.status>
                         <#case 0>退款完成<#break>
                         <#case 1>等待退款<#break>
                         <#case 2>退款失败<#break>
                         <#case -1>退款关闭<#break>
                        </#switch>
                    </td>
					<td>
						<a href="${base}/admin/refund/batch_view.do?id=${batch.id}">[查看]</a>
					</td>
				</tr>
			</#list>
		</#if>
		</table>
		<#include "../common/pager.ftl">
	</form>
</body>
</html>