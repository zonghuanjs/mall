<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发货信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	var $confirmButton = $("#confirmButton");
	var $cancelButton = $("#cancelButton");
	var $repairButton = $("#repairButton");
	var $confirmRecievedButton = $('#confirmRecievedButton');
	var $shippingButton = $('#shippingButton');

<#if repair.showCheckButton>
	$confirmButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/repair/confirm.do',
					data: {id: ${repair.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
	
	$cancelButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定不通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/repair/cancel.do',
					data: {id: ${repair.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>	
<#if repair.showConformRecievedButton>
	$confirmRecievedButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要确认收货?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/product_return/confirm.do',
					data: {id: ${repair.returns.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>
	
<#if repair.showProductCheckButtion>
	// 取消
	$repairButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要开始检测?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/repair/add.do',
					data: {id: ${repair.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>

<#if repair.showRepairButton>
	var $repairStartButton = $('#repairStart');
	$repairStartButton.click(function(){
		$.dialog({
			type: "warn",
			content: "确定开始维修了吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/repair/start.do',
					type: 'post',
					data: {id: ${repair.repair.id} },
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>

<#if repair.showRepairFinishButton>
	var $finishRepairButton = $('#finishRepair');
	$finishRepairButton.click(function(){
		$.dialog({
			type: "warn",
			content: "确定维修完成了吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/repair/finish.do',
					type: 'post',
					data: {id: ${repair.repair.id} },
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
</#if>

<#if repair.showShippingButton>
	$shippingButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过寄回给用户吗?",
			onOk: function() {
				location.href='${base}/admin/product_back/add.do?id=${repair.id}';
			}
		});
	});
</#if>
});
</script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 维修申请详细信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="维修申请信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
		<li>
			<input type="button" value="客户寄回信息" />
		</li>
		<li>
			<input type="button" value="公司回寄信息" />
		</li>
		<li>
			<input type="button" value="处理日志" />
		</li>
	</ul>
	<#if repair??>
	<!-- 维修申请信息 -->
	<table class="input tabContent">
		<tr>
			<th>
				流水号:
			</th>
			<td>
				${repair.sn!''}
			</td>
			<th>
				申请日期:
			</th>
			<td>
				${repair.createDate!''}
			</td>
		</tr>
		<tr>
			<th>
				购买日期:
			</th>
			<td>
				${repair.buyDate!''}
			</td>
			<th>
				所属会员:
			</th>
			<td>
				${repair.member.username!""}
			</td>
		</tr>
		<tr>
			<th>
				关联订单:
			</th>
			<td>
				<a href="${base}/admin/order/view!${repair.orders.id}.do" title="点击查看详情" >${repair.orders.sn}</a>
			</td>
			<th>
				联系人:
			</th>
			<td>
				${repair.contact!''}
			</td>
		</tr>
		<tr>
			<th>
				理由:
			</th>
			<td colspan="3">
				${repair.reason!''}
			</td>
		</tr>
		<tr>			
			<th>
				手机:
			</th>
			<td>
				${repair.phone!''}
			</td>
			<th>
				邮编:
			</th>
			<td>
				${repair.zipCode!''}
			</td>
		</tr>
		<tr>
			<th>
				区域:
			</th>
			<td>
				${repair.area.fullName!''}
			</td>
			<th>
				地址:
			</th>
			<td>
				${repair.address!''}
			</td>
		</tr>
		<tr>
			<th>
				是否审核通过:
			</th>
			<td>
				<#if repair.checked>是<#else>否</#if>
			</td>
			<th>
				申请状态:
			</th>
			<td>
				 ${repair.statusView}
			</td>
		</tr>
	<#if repair.repair??>
		<tr>
			<th>
				维修状态:
			</th>
			<td>
				${repair.repair.statusView}
			</td>
			<th>
				维修费用:
			</th>
			<td>
				 <#if repair.repair.report?? &&  repair.repair.report.free>
				 	免费
				 <#else>
				 	付费 ${(repair.repair.amount!0)?string.currency}
				 </#if>
			</td>
		</tr>
	</#if>
		<tr>			
			<th>
				发票照片:
			</th>
			<td>	
			<#if repair.invoiceImage?has_content>
				<a href="${mall_url(repair.invoiceImage, base)}" target="_blank">查看</a>
			<#else>
				无
			</#if>
			</td>
			<th>
				产品照片:
			</th>
			<td>
			<#if imgs?exists>
				<#list imgs as img>
					<a href="${mall_url(img, base)}" target="_blank">查看图片${img_index+1}</a>&nbsp;&nbsp;
				</#list>
			</#if>
			</td>
		</tr>
	</table>
	<!-- 商品信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				商品编号
			</th>
			<th>
				商品名称
			</th>
			<th>
				商品单价
			</th>
			<th>
				商品重量
			</th>
			<th>
				数量
			</th>
		</tr>
		<#list repair.items as item>
		<tr>
			<td>
				${item.sn!""}
			</td>
			<td>
				${item.name!""}
			</td>
			<td>
				${item.price!""}
			</td>
			<td>
				${item.weight!""}
			</td>
			<td>
				${item.quantity!""}
			</td>
		</tr>
		</#list>
	</table>
	<!-- 客户寄回信息 -->
	<table class="input tabContent">
	<#if repair.returns??>
			<tr>
				<th>寄货编号:</th>
				<td>${repair.returns.sn!''}</td>
				<th>寄货日期:</th>
				<td>${repair.returns.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${repair.returns.apply.contact}</td>
				<th>联系电话：</th>
				<td>${repair.returns.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${repair.returns.area.fullName} ${repair.returns.address}</td>
				<th>邮编：</th> 
				<td>${repair.returns.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if repair.returns.deliveryCorp??>${repair.returns.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if repair.returns.shippingMethod??>${repair.returns.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${repair.returns.trackingNo}</td>
				<th>运费：</th>
				<td>${repair.returns.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${repair.returns.operator}</td>
				<th>发货人：</th>
				<td>${repair.returns.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${repair.returns.memo}</td>
				<th>是否到货：</th>
				<td>
					
				</td>
			</tr>
			</#if>
		</table>
		<!-- 公司寄回信息 -->
	<table class="input tabContent">
		<#if repair.back??>
		<tr>
				<th>寄货编号:</th>
				<td>${repair.back.sn!''}</td>
				<th>寄货日期:</th>
				<td>${repair.back.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${repair.back.apply.contact}</td>
				<th>联系电话：</th>
				<td>${repair.back.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${repair.back.area.fullName} ${repair.back.address}</td>
				<th>邮编：</th> 
				<td>${repair.back.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if repair.back.deliveryCorp??>${repair.back.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if repair.back.shippingMethod??>${repair.back.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${repair.back.trackingNo}</td>
				<th>运费：</th>
				<td>${repair.back.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${repair.back.operator}</td>
				<th>发货人：</th>
				<td>${repair.back.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${repair.back.memo}</td>
				<th>是否到货：</th>
				<td></td>
			</tr>
		</#if>
	</table>
	<table class="input tabContent">
	<#if applyLogs??>
		<tr class="title">
			<th>
				操作员
			</th>
			<th>
				IP
			</th>
			<th>
				操作类型
			</th>
			<th>
				操作内容
			</th>
			<th>
				创建日期
			</th>
		</tr>
		<#list applyLogs as apply>
		<tr>
			<td>${apply.operator}</td>
			
			<td>${apply.ip!""}</td>
			<td>
				${apply.typeView}
			</td>
			<td>${apply.content!""}</td>
			<td>${apply.createDate!""}</td>
		</tr>
		</#list>
	</#if>
	</table>
	<!-- 操作-->
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
			    <#if repair.showCheckButton && adminView.authorities?seq_contains('admin:apply:check')>
			    	<input type="button" id="confirmButton" class="button" value="审核通过" />
			    	<input type="button" id="cancelButton" class="button" value="审核不通过" />
			    </#if>
			    <#if repair.showConformRecievedButton && adminView.authorities?seq_contains('admin:product:recieved')>
			    	<a href="javascript:;" id="confirmRecievedButton" class="button" >确认收货</a>
			    </#if>
				<#if repair.showProductCheckButtion && adminView.authorities?seq_contains('admin:product:check')>
					<input type="button" id="repairButton" class="button" value="开始检测"/>
				</#if>
				<#if repair.showFillReportButton && adminView.authorities?seq_contains('admin:product:check')>
					<a href="${base}/admin/report/add.do?id=${repair.repair.id}" class="button" >填写检测报告</a>
				</#if>
				<#if repair.showRepairButton && adminView.authorities?seq_contains('admin:repair:start')>
					<a href="#" id="repairStart" class="button">开始维修</a>
				</#if>
				<#if repair.showRepairFinishButton && adminView.authorities?seq_contains('admin:repair:finished')>
					<a href="#" id="finishRepair" class="button">维修完成</a>
				</#if>
				<#if repair.showShippingButton && adminView.authorities?seq_contains('admin:product:return')>
					<input type="button" id="shippingButton" class="button" value="回寄给用户" />
				</#if>
				<input type="button" class="button" value="返回" onClick="location.href='${base}/admin/repair/list.do'" />
			</td>
		</tr>
	</table>
	</#if>
</body>
</html>