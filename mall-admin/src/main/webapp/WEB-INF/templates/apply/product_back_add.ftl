<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑回寄商品信息 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $areaId = $("#areaId");
	var $backForm = $('#backForm');
	
	$areaId.lSelect({
		url: '${base}/common/areaQuery.do'
	});
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	//必填验证
	$backForm.validate({
		rules: {
			trackingNo: 'required',
			freight: {
				required: true,
				number: true,
				min: 0
			}
		},
		messages: {
			trackingNo: '必填',
			freight: {
				required: '必填',
				number: '运费只能为数字',
				min: '运费不能为负数'
			}
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加回寄信息
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="回寄信息" />
		</li>
		<li>
			<input type="button" value="商品信息" />
		</li>
	</ul>
	<#if apply??>
	<form id="backForm" action="${base}/admin/product_back/save!${apply.id}.do" method="post">
		<table class="input tabContent">
			<tr>
				<th>回寄编号:</th>
				<td>${apply.sn!''}</td>
				<th>售后类型:</th>
				<td>${apply.typeView}</td>
			</tr>
			<tr>
				<th>收货人：</th>
				<td>${apply.contact}</td>
				<th>联系电话：</th>
				<td>${apply.phone}</td>
			</tr>
			<tr>
				<th>地区：</th>
				<td><#if apply.area??>${apply.area.fullName!''}</#if></td>
				<th>地址：</th>
				<td>${apply.address}</td>
			</tr>
			<tr>
				<th>邮编：</th> 
				<td>${apply.zipCode}</td>
				<th><span class="requiredField">*</span>运费：</th>
				<td><input type="text" class="text" name="freight" id="freight" value="${apply.freight!''}"/></td>
			</tr>
			<tr>
				<th>物流公司：</th>
				<td>
					<select name="deliveryCorp">
						<#list deliveryCorps as corp>
							<option value="${corp.id}">${corp.name}</option>
						</#list>
					</select>
				</td>
				<th>配送方式：</th>
				<td>
					<select name="shippingMethod">
						<#list shippingMethods as method>
							<option value="${method.id}">${method.name}</option>
						</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th><span class="requiredField">*</span>运单号：</th>
				<td><input type="text" class="text" name="trackingNo" id="trackingNo"/></td>
				<th>备注：</th>
				<td><input type="text" class="text" name="memo" id="memo" value="${apply.memo!''}"/></td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr class="title">
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品单价</th>
				<th>商品重量</th>
				<th>数量</th>
			</tr>
			<#list apply.items as item>
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
					<input type="submit" id="confirmButton" class="button" value="确定" />
					<input type="button" id="backButton" class="button" value="返回" />
				</td>
			</tr>
		</table>
	</form>
	</#if>
</body>
</html>