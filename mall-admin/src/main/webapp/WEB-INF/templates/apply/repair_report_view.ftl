<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>回寄商品信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 故障商品检测
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="检测信息" />
		</li>
		<li>
			<input type="button" value="检测报告" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
	</ul>
	
	<form id="refundForm" action="#" method="post">
		<table class="input tabContent">
		<#if repair??>
			<tr>
				<th>申请单号:</th>
				<td>${repair.apply.sn}</td>				
			</tr>
			<tr>
				<th>申请会员:</th>
				<td>${repair.apply.member.username}</td>				
			</tr>
			<tr>
				<th>申请时间:</th>
				<td>${repair.createDate}</td>				
			</tr>
			<tr>
				<th>操作员:</th>
				<td>${repair.operator}</td>				
			</tr>
			<tr>
				<th>状态:</th>
				<td>${repair.statusView}</td>				
			</tr>
		</#if>	
		</table>
		<table class="input tabContent">
		<#if report??>
			<tr>
				<th>创建日期:</th>
				<td>${report.createDate!""}</td>
				<th>修改日期:</th>
				<td>${report.modifyDate!""}</td>
			</tr>			
			<tr>
				<th>
					外观检测:
				</th>
				<td>
					<#if report.facade>有问题<#else>无问题</#if>
				</td>
			</tr>	
			
			<tr>
				<th>
					问题描述:
				</th>
				<td colspan="3">
					${report.facadeDescription!""}
				</td>
			</tr>
			<tr>
				<th>
					性能检测:
				</th>
				<td>
					<#if report.performance>有异常<#else>无异常</#if>
				</td>
			</tr>	
			
			<tr>
				<th>
					问题描述:
				</th>
				<td colspan="3">
					${report.performanceDesc!""}
				</td>
			</tr>
			
			<tr>
				<th>
					是否免费维修:
				</th>
				<td>
					<#if report.free>免费维修<#else>付费维修</#if>    
				</td>
			</tr>
			<#if !report.free>
			<tr>				
				<th>
					维修金额:
				</th>
				<td>
					 ${report.amount?string.currency!""}
				</td>
			</tr>							
			</#if>
			<tr>
				<th>
					操作员:
				</th>
				<td>
					 ${report.operator!""}
				</td>
			</tr>							
			<tr>
		</#if>	
		</table>
		<#--商品信息-->
		<table class="input tabContent">
			<tr class="title">
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品单价</th>
				<th>商品重量</th>
				<th>数量</th>
			</tr>
			<#list repair.apply.items as item>
				<tr>
					<td>${item.sn}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.weight}</td>
					<td>${item.quantity}</td>
				</tr>
			</#list>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" id="backButton" class="button" value="返回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>