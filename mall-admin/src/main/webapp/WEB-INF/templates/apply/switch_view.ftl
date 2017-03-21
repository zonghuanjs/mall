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
    var $finishProductCheckingButton = $("#finishProductCheckingButton");
    var $shippingButton = $('#shippingButton');
    var $confirmRecievedButton = $("#confirmRecievedButton");
    var $agreeSwitchButton = $("#agreeSwitchButton");
    var $disagreeSwitchButton = $("#disagreeSwitchButton");
<#if switch.showCheckButton>
	$confirmButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/switch/confirm.do',
					data: {id: ${switch.id}},
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
					url: '${base}/admin/switch/cancel.do',
					data: {id: ${switch.id}},
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
   <#if switch.showConformRecievedButton>	
	// 确认收货
	$confirmRecievedButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定收货吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/product_return/confirm.do',
					type: 'post',
					data: {id: ${switch.returns.id}},
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
  </#if>
  
  <#if switch.finishProductCheckingButton>
	// 检测
	$finishProductCheckingButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要完成检测?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/apply/switch.do',
					data: {id: ${switch.id}},
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
  //同意换货
	$agreeSwitchButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定同意换货?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/switch/agree.do',
					data: {id: ${switch.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
  
  //不同意换货
	$disagreeSwitchButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定不同意换货?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/switch/disagree.do',
					data: {id: ${switch.id}},
					type: 'post',
					dataType: 'json',
					success: function(){
						location.reload(true);
					}
				});
			}
		});
	});
  	
  <#if switch.showShippingButton>
	$shippingButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过寄回给用户吗?",
			onOk: function() {
				location.href='${base}/admin/product_back/add.do?id=${switch.id}';
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
		<a href="${base}/admin/home.do">首页</a> &raquo; 换货详细信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="换货申请信息" />
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
	<#if switch??>
	<table class="input tabContent">
		<tr>
			<th>
				流水号:
			</th>
			<td>
				${switch.sn!''}
			</td>
			<th>
				申请日期:
			</th>
			<td>
				${switch.createDate!''}
			</td>
		</tr>
		<tr>
		    <th>
				联系人:
			</th>
			<td>
				${switch.contact!''}
			</td>
			<th>
				手机:
			</th>
			<td>
				${switch.phone!''}
			</td>			
		</tr>
		<tr>
			<th>
				地址:
			</th>
			<td>
				${switch.area.fullName!''}&nbsp;&nbsp;${switch.address!''}
			</td>
			<th>
				邮编:
			</th>
			<td>
				${switch.zipCode!''}
			</td>
		</tr>		
		<tr>
			<th>
				购买日期:
			</th>
			<td>
				${switch.buyDate!''}
			</td>
			<th>
				所属会员:
			</th>
			<td>
				${switch.member.username!""}
			</td>
		</tr>
		<tr>
			<th>
				关联订单:
			</th>
			<td>
				<a href="${base}/admin/order/view!${switch.orders.id}.do" title="点击查看详情" >${switch.orders.sn}</a>
			</td>
			<th>
				
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
			<th>
				是否审核通过:
			</th>
			<td>
				<#if switch.checked>是<#else>否</#if>
			</td>
			<th>
				申请状态:
			</th>
			<td>
				 ${switch.statusView}
			</td>
		</tr>
		<tr>			
			<th>
				发票照片:
			</th>
			<td>	
				<#if switch.invoiceImage?has_content>
					<a href="${mall_url(switch.invoiceImage, base)}" target="_blank">查看</a>
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
				<#else>
					无
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				换货理由:
			</th>
			<td colspan="3">
				${switch.reason!''}
			</td>
		</tr>
	</table>
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
		<#list switch.items as item>
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
	
	<table class="input tabContent">
	<#if switch.returns??>
			<tr>
				<th>寄货编号:</th>
				<td>${switch.returns.sn!''}</td>
				<th>寄货日期:</th>
				<td>${switch.returns.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${switch.returns.apply.contact}</td>
				<th>联系电话：</th>
				<td>${switch.returns.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${switch.returns.area.fullName} ${switch.returns.address}</td>
				<th>邮编：</th> 
				<td>${switch.returns.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if switch.returns.deliveryCorp??>${switch.returns.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if switch.returns.shippingMethod??>${switch.returns.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${switch.returns.trackingNo}</td>
				<th>运费：</th>
				<td>${switch.returns.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${switch.returns.operator}</td>
				<th>发货人：</th>
				<td>${switch.returns.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${switch.returns.memo}</td>
				<th>是否到货：</th>
				<td></td>
			</tr>
			</#if>
		</table>	
	<table class="input tabContent">
		<#if switch.back??>
		<tr>
				<th>寄货编号:</th>
				<td>${switch.back.sn!''}</td>
				<th>寄货日期:</th>
				<td>${switch.back.createDate!''}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${switch.back.apply.contact}</td>
				<th>联系电话：</th>
				<td>${switch.back.phone}</td>
			</tr>
			<tr>
				<th>发货地址：</th>
				<td>${switch.back.area.fullName} ${switch.back.address}</td>
				<th>邮编：</th> 
				<td>${switch.back.zipCode}</td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td><#if switch.back.deliveryCorp??>${switch.back.deliveryCorp.name}<#else>-</#if></td>
				<th>配送方式：</th>
				<td><#if switch.back.shippingMethod??>${switch.back.shippingMethod.name}<#else>-</#if></td>
			</tr>
			<tr>
				<th>运单号：</th>
				<td>${switch.back.trackingNo}</td>
				<th>运费：</th>
				<td>${switch.back.freight}</td>
			</tr>
			<tr>
				<th>操作员：</th>
				<td>${switch.back.operator}</td>
				<th>发货人：</th>
				<td>${switch.back.apply.contact}</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>${switch.back.memo}</td>
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
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
			    <#if switch.showCheckButton && adminView.authorities?seq_contains('admin:apply:check')>
			    <input type="button" id="confirmButton" class="button" value="审核通过" />
				<input type="button" id="cancelButton" class="button" value="审核不通过" />
				</#if>
				<#if switch.showConformRecievedButton && adminView.authorities?seq_contains('admin:product:recieved')>
				<a href="javascript:;" id="confirmRecievedButton" class="button" >确认收货</a>
			    </#if>
			    <#if switch.finishProductCheckingButton && adminView.authorities?seq_contains('admin:product:check')>
					<input type="button" id="finishProductCheckingButton" class="button" value="完成物品检测"/>
				</#if>
				<#if switch.showProductSwitchButton && adminView.authorities?seq_contains('admin:switch:agree')>    
				    <input type="button" id="agreeSwitchButton" class="button" value="同意换货" />
				    <input type="button" id="disagreeSwitchButton" class="button" value="不同意换货" />
				</#if>
				<#if switch.showShippingButton && adminView.authorities?seq_contains('admin:product:return')>
					<input type="button" id="shippingButton" class="button" value="回寄给用户" />
				</#if>
				<input type="button" class="button" value="返回" onClick="location.href='${base}/admin/switch/list.do'" />
			</td>
		</tr>
	</table>
	</#if>
</body>
</html>