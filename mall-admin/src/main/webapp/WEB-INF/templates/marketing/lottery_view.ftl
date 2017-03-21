<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>抽奖活动详情</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/lottery_view.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<#assign prizeCheck="cn.tekism.mall.freemarker.lottery.OrderPrizeCheckMethod"?new()>
	<#assign statusText= "cn.tekism.mall.freemarker.OrderStatusMethod"?new()> 
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 抽奖活动数据
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="基本信息" />
		</li>
		<li>
			<input type="button" value="参与订单" />
		</li>
	</ul>
	<div class="tabContent">
		<table class="input">
			<tr>
				<th>
					活动名称:
				</th>
				<td>
					${lottery.name}
				</td>
			</tr>
			<tr>
				<th>
					当前状态:
				</th>
				<td>
					<#switch lottery.status>
					  <#case 1>等待开奖<#break>
					  <#case 0>已开奖<#break>
					</#switch>
				</td>
			</tr>
			<tr>
				<th>
					活动描述:
				</th>
				<td>
					${lottery.description}
				</td>
			</tr>
			<tr>
				<th>
					起始日期:
				</th>
				<td colspan="2">
					${lottery.beginDate?datetime}
				</td>
			</tr>
			<tr>
				<th>
					结束日期:
				</th>
				<td colspan="2">
					${lottery.endDate?datetime}
				</td>
			</tr>
			<tr>
				<th>
					是否启用:
				</th>
				<td>
					${lottery.enabled?string('已启用', '未启用')}
				</td>
			</tr>
			<tr>
				<th>
					允许参与数量:
				</th>
				<td>
					${lottery.number!0}
				</td>
			</tr>
			<tr>
				<th>
					抽奖单价:
				</th>
				<td>
					${currency(lottery.price)}
				</td>
			</tr>
			<tr>
				<th>
					抽奖商品:
				</th>
				<td>
					${lottery.product.name}
				</td>
			</tr>
			<tr>
				<th>
					已参与人数:
				</th>
				<td>
					${number}
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
				<#if lottery.number==number>
					<span id="prizingButton" class="button">自动抽奖<input type="hidden" name="lottery" value="${lottery.id}" /></span>
					<span id="endPrizingButton" class="button">结束抽奖</span>
				</#if>
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</div>
	<div class="tabContent">
		<table class="list">
			<tr>
				<th>订单编号</th>
				<th>订单状态</th>
				<th>中奖状态</th>
				<th>订单金额</th>
				<th>购买数量</th>
				<th>参与会员</th>
				<th>收货人</th>
			</tr>
		<#list lottery.items as item>
		  <#if item.orders.orderStatus != -1>
			<tr>
				<td>
					${item.orders.sn}
				</td>
				<td>
					${statusText(item.orders.orderStatus)}
				</td>
				<td>
					<#if lottery.status==1>
						<span class="red">等待开奖</span>
					<#elseif prizeCheck(item.orders.sn)>
						<span class="green">已中奖</span>
					<#else>
						<span class="red">未中奖</span>
					</#if>
				</td>
				<td>
					${currency(item.orders.amountPaid)}
				</td>
				<td>
					${item.quantity}
				</td>
				<td>
					${item.orders.member.username}
				</td>
				<td>
					${item.orders.consignee}
				</td>
			</tr>
		  </#if>
		</#list>
		</table>
	</div>
</body>
</html>