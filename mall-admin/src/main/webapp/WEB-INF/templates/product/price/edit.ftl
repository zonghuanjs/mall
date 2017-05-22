<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>价格编辑</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	     
	// 表单验证
	$inputForm.validate({
		rules: {

			price: {
				required: true,
				number: true
			},
			cost: {
				number: true,
			},
			marketPrice: {
				number: true,
			},
		},
		messages: {
			price: {
				required: '必填',
				number: '格式不对'
			},
			cost: {
				number: '格式不对',
			},
			marketPrice: {
				number: '格式不对',
			},
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 价格编辑
	</div>
<#if product?has_content>
	<form id="inputForm" action="save.do?id=${product.id}" method="post">
		<table class="input">
			<tr>
				<th>
					商品编号:
				</th>
				<td>
					<span>${product.sn}</span>
				</td>
			</tr>
			<tr>
				<th>
					商品名称:
				</th>
				<td>
					<span>${product.name}</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>销售价:
				</th>
				<td>
					<input type="text" name="price" class="text" maxlength="16" value="${product.price!0}" />
				</td>
			</tr>
			<tr>
				<th>
					app专享价:
				</th>
				<td>
					<input type="text" name="app_price" class="text" maxlength="16" value="${product.appPrice!0}" />
					<span>在Android, IOS客户端购买时的价格; 如未设置则跟销售价一致</span>
				</td>
			</tr>
			<tr>
				<th>
					成本价:
				</th>
				<td>
					<input type="text" name="cost" class="text" maxlength="16" title="前台不会显示，仅供后台使用" value="${product.cost!0}" />
				</td>
			</tr>
			<tr>
				<th>
					市场价:
				</th>
				<td>
					<input type="text" name="marketPrice" class="text" maxlength="16" title="若留空则由系统自动计算" value="${product.marketPrice}" />
					<span>当市场价为0时, 无论是否开启市场价显示, 前台都不显示</span>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</#if>
</body>
</html>