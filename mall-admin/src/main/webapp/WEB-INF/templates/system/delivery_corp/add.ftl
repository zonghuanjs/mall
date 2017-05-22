<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加物流公司</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/delivery_corp.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if deliveryCorp??>添加<#else>编辑</#if>物流公司
	</div>
	<form id="inputForm" action="<#if deliveryCorp??>${base}/delivery_corp/save.do?id=${deliveryCorp.id}<#else>${base}/delivery_corp/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" <#if deliveryCorp??>value="${deliveryCorp.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					网址:
				</th>
				<td>
					<input type="text" id="url" name="url" class="text" maxlength="200" <#if deliveryCorp??>value="${deliveryCorp.url!''}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					代码:
				</th>
				<td>
					<input type="text" id="code" name="code" class="text" maxlength="200" title="用于物流动态查询" <#if deliveryCorp??>value="${deliveryCorp.code!''}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" <#if deliveryCorp??>value="${deliveryCorp.orders!''}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/delivery_corp/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>