<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>地区列表</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>

</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 地区列表
	</div>
	<div class="bar">
		<a href="<#if parent??>${base}/area/add.do?id=${parent.id}<#else>${base}/area/add.do</#if>" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加
		</a>
		<a href="javascript:;" id="deleteButton" class="iconButton disabled">
		 <span class="deleteIcon">&nbsp;</span>删除
		</a>
		<#if parent??>
			<div class="pageBar">
				<#if parent.parent??>
					<a href="${base}/area/list.do?id=${parent.parent.id}" class="iconButton">
						<span class="upIcon">&nbsp;</span>上级地区
					</a>
				<#else>
					<a href="${base}/area/list.do" class="iconButton">
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
							<a href="${base}/area/list.do?id=${area.id}" title="">${area.name}</a>
							<a href="${base}/area/edit.do?id=${area.id}">编辑</a>
														
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