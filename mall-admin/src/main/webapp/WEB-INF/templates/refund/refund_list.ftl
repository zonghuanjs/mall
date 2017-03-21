<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>退款单列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/select.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/refund_batch.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 退款单列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
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
				<span id="batchButton" class="button disabled">批量退款(仅支持支付宝)</span>
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
								<a href="javascript:;" name="status" val="0" <#if status??&&status == 0> class="checked"</#if>>退款完成</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="1" <#if status??&&status == 1> class="checked"</#if>>退款处理中</a>
							</li>
							<li>
								<a href="javascript:;" name="status" val="-1" <#if status??&&status == -1> class="checked"</#if>>退款失败</a>
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
						<li>
							<a href="javascript:;" val="payee" <#if searchProperty??&&searchProperty == "payee"> class="current"</#if>>收款人</a>
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
					<a href="javascript:;" class="sort" name="type">退款类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="plugin">支付插件</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">订单编号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">退款金额</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="payee">收款人</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">退款状态</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#if refunds??>
			<#list refunds as refund>
				<tr>
				    <td>
						<input type="checkbox" name="ids" value="${refund.id}" />
					</td>
					<td>${refund.sn!""}</td>
					<td>
						<span title="退款类型">
							<#switch refund.type>
							  <#case 0>即时到账<#break>
							  <#case 1>担保交易<#break>
							</#switch>
						</span>
					</td>					
					<td>
						<span title="支付插件">${refund.order.payment.paymentPlugin.pluginName!""}</span>
					</td>
					<td>
						${refund.order.sn}
					</td>
					<td>${currency(refund.amount)}</td>
					<td>${refund.payee!""}</td>
					<td>${refund.createDate!""}</td>
					<td>
						<#switch refund.status>
                         <#case 0>退款完成<#break>
                         <#case 1>退款处理中<#break>
                         <#case -1>退款失败<#break>                                                 
                         <#default>处理中
                        </#switch>
                    </td>
					<td>
						<a href="${base}/admin/refund/view!${refund.id}.do">[查看]</a>
						
					</td>
				</tr>
			</#list>
		</#if>
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>