<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加配送方式</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/shipping_method.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加配送方式
	</div>
	<form id="inputForm" action="add.do" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					默认物流公司:
				</th>
				<td>
					<select name="defaultDeliveryCorpId">
						<option value="">请选择...</option>
					<#list deliveryCorps as deliveryCorp>
						<option value="${deliveryCorp.id}">${deliveryCorp.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					图标:
				</th>
				<td>
					<input type="text" name="icon" class="text" maxlength="200" />
					<input type="button" class="button browserButton" value="选择文件" />
					<a href="#" target="_blank">查看</a>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					介绍:
				</th>
				<td>
					<input type="text" name="description" class="text" />
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
	
</body>
</html>