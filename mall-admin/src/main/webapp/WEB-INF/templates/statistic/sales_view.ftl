<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-销售统计</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
 
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>

</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 销售统计
	</div>
	
	
	
	<form id="inputForm" action="" method="get">
	
		<table class="input">
		<tr>
			<th>
				订单总数:
			</th>
			<td>
				${statisticMap["orderCount"]}
			</td>
			<th>
				订单总金额:
			</th>
			<td>
				${statisticMap["orderSalseCount"]?string.currency!"-"}
			</td>
		</tr>
		
		<tr>
			<th>
				已完成订单:
			</th>
			<td>
				${statisticMap["orderFinishedCount"]}
			</td>
			<th>
				已完成订单金额:
			</th>
			<td>
				${statisticMap["orderFinishedSalseCount"]?string.currency!"-"}
			</td>
		</tr>
		<tr>
			<th>
				交易中订单:
			</th>
			<td>
				${statisticMap["orderPaidCount"]}
			</td>
			<th>
				交易中金额:
			</th>
			<td>
				${statisticMap["orderPaidSalseCount"]?string.currency!"-"}
			</td>
		</tr>
		<tr>
			<th>
				未支付订单:
			</th>
			<td>
				${statisticMap["orderUnpaidCount"]}
			</td>
			<th>
				未支付金额:
			</th>
			<td>
				${statisticMap["orderUnpaidSalseCount"]?string.currency!"-"}
			</td>
		</tr>
		<tr>
			<th>
				退款订单:
			</th>
			<td>
				${statisticMap["orderRefundCount"]}
			</td>
			<th>
				退款金额:
			</th>
			<td>
				${statisticMap["orderRefundSalseCount"]?string.currency!"-"}
			</td>
		</tr>
		
		</table>		
	</form>
	<form id="form" action="${base}/admin/salse_statistic_chart.do" target="iframe1" method="get">
		<div class="bar">		
		<label>	
			起始日期:<input type="text" class="text Wdate" id="beginDate" name="beginDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if beginDate?has_content>value="${beginDate?date}"</#if> />
                                   结束日期:<input type="text" class="text Wdate" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if endDate?has_content>value="${endDate?date}"</#if> />
			<span>按天or按月</span>
			<select name="xvalue">
			    <option value="">请选择</option>
				<option value="0">按天</option>
				<option value="1">按月</option>
			</select>	
			<span>订单数or金额</span>
			<select name="yvalue">
			    <option value="">请选择</option>
				<option value="0">订单数</option>
				<option value="1">金额</option>
			</select>
			
			<input id="submitButton" type="submit" class="button" value="确&nbsp;&nbsp;定" />
		</div>	
	</form>
	<div id="jchart" style="min-width:850px;height:650px">
	<iframe id="iframe1" style="min-width:850px;height:650px" name="iframe1" frameborder="0"></iframe>
	</div>
</body>
</html>