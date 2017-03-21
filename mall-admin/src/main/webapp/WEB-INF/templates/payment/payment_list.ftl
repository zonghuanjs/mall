<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>收款单列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 收款单列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/admin/payment/list.do" method="get">
		<div class="bar">
			<div class="buttonWrap">
			<#if authorities?seq_contains('admin:payment:delete')>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
			</#if>				
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
								<a href="javascript:;" val="100"<#if pager.pageSize==100>class="current"</#if>>100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue"  maxlength="200" <#if searchValue??>value="${searchValue}"</#if>/>
					<button type="submit">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="sn" <#if searchProperty??&&searchProperty == "sn"> class="current"</#if>>编号</a>
						</li>
						<li>
							<a href="javascript:;" val="payer" <#if searchProperty??&&searchProperty == "payer"> class="current"</#if>>付款人</a>
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
					<a href="javascript:;" class="sort" name="plugin">支付插件</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">支付类型</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="status">状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">付款金额</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="amount">运费</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">订单</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="invoice">发票抬头</a>
				</th>				
				<th>
					<a href="javascript:;" class="sort" name="paymentDate">付款日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list payments as payment>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${payment.id}" />
					</td>
					<td>
						${payment.sn}
					</td>
					<td>
						${payment.paymentPlugin.pluginName}
					</td>
					<td>
						<#switch payment.type>
							<#case 0>即时到账<#break>
							<#case 1>担保交易<#break>
						</#switch>
					</td>
					<td>						
						<#switch payment.status>
                         <#case 0>支付完成<#break>
                         <#case 1>等待支付<#break>                         
                         <#case 2>等待发货<#break>
                         <#case 3>等待收货<#break>
                         <#case -1>支付失败<#break>
                        </#switch>
					</td>
					<td>
						￥${payment.amount?string('0.00')}
					</td>
					<td>
						￥${payment.orders.freight?string('0.00')}
					</td>
					<td>
						${payment.member.username}
					</td>
					<td>
						${payment.orders.sn}
					</td>
					<td>
						<#if payment.orders.isInvoice>
							${payment.orders.invoiceTitle}
						<#else>
							无
						</#if>
					</td>
					<td>
						${payment.paymentDate}
					</td>
					<td>
						<a href="${base}/admin/payment/view.do?id=${payment.id}">[查看]</a>
					</td>
				</tr>
			</#list>
		</table>
	<#include "../common/pager.ftl">
	</form>
</body>
</html>