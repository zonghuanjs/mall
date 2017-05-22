<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发货</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("#browserButton");
	
	
	$browserButton.browseDialog();
	
	// 表单验证
	$inputForm.validate({
		rules: {
		    freight: { 
                required: true, 
                number: true 
            }, 			
			trackingNo:"required",
		},
		messages: {
			trackingNo: "必填",	
			freight: {
				number: "必须为数字",
				required: "必填"
			}					
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加发货信息
	</div>
	<form id="inputForm" action="${base}/admin/shipping/add.do" method="post">
	<input type="hidden" name="id" <#if order??>value="${order.id}" </#if>/>	
	<table class="input tabContent">
		
		<tr>
			<th>
				收货人:
			</th>
			<td>
				${order.consignee}<input type="hidden" name="consignee" <#if order??>value="${order.consignee}" </#if>/>
			</td>
			<th>
				手机号码:
			</th>
			<td>
				${order.phone}<input type="hidden" name="phone" <#if order??>value="${order.phone}" </#if>/>
			</td>
		</tr>
		<tr>
			<th>
				地区:
			</th>
			<td>
				${order.areaName}<input type="hidden" name="areaName" <#if order??>value="${order.areaName}" </#if>/>
			</td>
			<th>
				地址:
			</th>
			<td>
				${order.address}<input type="hidden" name="address" <#if order??>value="${order.address}" </#if>/>
			</td>
		</tr>
		<tr>
			<th>
				邮编:
			</th>
			<td>
				${order.zipCode}<input type="hidden" name="zipCode" <#if order??>value="${order.zipCode}"</#if>/>
			</td>
			<th>
				订单编号:
			</th>
			<td>
				${order.sn}<input type="hidden" name="sn" <#if order??>value="${order.sn}"</#if>/>
			</td>
		</tr>		
		<tr>
			<th>
				物流公司:
			</th>
			<td>
			   <select name="deliveryCorpId">
						<option value="">请选择...</option>
						<#list deliveryCorps as deliveryCorp>
							<option value="${deliveryCorp.id}" <#if deliveryCorp == order.shippingMethod.defaultDeliveryCorp>selected="selected"</#if> >${deliveryCorp.name}</option>
						</#list>
				</select>				
			</td>
			<th>
				配送方式:
			</th>
			<td>
				<select name="shippingMethodId">
						<option value="">请选择...</option>
						<#list shippingMethods as shippingMethod>
							<option value="${shippingMethod.id}" <#if shippingMethod == order.shippingMethod>selected="selected"</#if> >${shippingMethod.name}</option>
						</#list>
				</select>
			</td>
		</tr>
		<tr>
			<th>
				<span class="requiredField">*</span>运单号:
			</th>
			<td>
				<input type="text" name="trackingNo" class="text" maxlength="200" />
			</td>
			<th>
				<span class="requiredField">*</span>运费:
			</th>
			<td>
				<input type="text" name="freight" class="text" maxlength="200" />
			</td>
		</tr>
		<tr>
			
			<th>
				备注:
			</th>
			<td>
				<input type="text" name="memo" class="text" maxlength="200" />
			</td>
		</tr>
	
					
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/shipping/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>