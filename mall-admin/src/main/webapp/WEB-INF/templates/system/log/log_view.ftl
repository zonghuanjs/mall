<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">首页</a> &raquo; 
	</div>
	
	<table class="input">
		<tr>
			<th>
				操作
			</th>
			<td>
				<#if log??&&log.operation??>${log.operation}</#if>
			</td>
		</tr>
		<tr>
			<th>
				操作员
			</th>
			<td>
			    <#if log??&&log.operator??>${log.operator}</#if>
			</td>
		</tr>
		<tr>
			<th>
				内容
			</th>
			<td>
			    <#if log??&&log.content??>${log.content}</#if>
			</td>
		</tr>
		<tr>
			<th>
				参数
			</th>
			<td>
			    <#if log??&&log.parameter??>${log.parameter}</#if>
			
			</td>
		</tr>
		<tr>
			<th>
				IP
			</th>
			<td>
				<#if log??&&log.ip??>${log.ip}</#if>				
			</td>
		</tr>
		<tr>
			<th>
				创建日期
			</th>
			<td>
				<#if log??&&log.createDate??>${log.createDate}</#if>				
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td>			
				<input type="button" id="backButton" class="button" value="返回" />
			</td>
		</tr>
	</table>

</body>
</html>