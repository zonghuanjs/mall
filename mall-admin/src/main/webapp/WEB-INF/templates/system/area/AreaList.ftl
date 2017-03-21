<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>地区列表</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>

</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 地区列表
	</div>
	<div class="bar">
		<a href="<#if parent??>${base}/admin/area/add!${parent.id}.do<#else>${base}/admin/area/add.do</#if>" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加
		</a>
		<a href="javascript:;" id="deleteButton" class="iconButton disabled">
		 <span class="deleteIcon">&nbsp;</span>删除
		</a>
		<#if parent??>
			<div class="pageBar">
				<#if parent.parent??>
					<a href="${base}/admin/area/list!${parent.parent.id}.do" class="iconButton">
						<span class="upIcon">&nbsp;</span>上级地区
					</a>
				<#else>
					<a href="${base}/admin/area/list.do" class="iconButton">
						<span class="upIcon">&nbsp;</span>上级地区
					</a>
				</#if>
			</div>
		</#if>
	</div>
	<table id="listTable" class="list">
		<tr>
			<th colspan="5" class="green" style="text-align: center;">
				<#if parent??>上级地区 - ${parent.name}<#else>顶级地区</#if>
			</th>
		</tr>
		<#list areaList?chunk(5, "") as row>
			<tr>
				<#list row as area>
					<#if area?has_content>
					    
						<td>
						    <input type="checkbox" name="ids"  value="${area.id}"/>										  
							<a href="${base}/admin/area/list!${area.id}.do" title="">${area.name}</a>
							<a href="${base}/admin/area/edit!${area.id}.do">编辑</a>
														
						</td>
					<#else>
						<td>
							&nbsp;
						</td>
					</#if>
				</#list>
			</tr>
		</#list>
			
					
	</table>
</body>
</html>