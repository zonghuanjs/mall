<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>友情链接列表 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>

</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 导航列表
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<a href="add.do" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
			</div>
		</div>
		<table id="listTable" class="list">
		
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<span>名称</span>
				</th>
				<th>
					<span>位置</span>
				</th>
				<th>
					<span>是否新窗口打开</span>
				</th>
				<th>
					<span>排序</span>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
						
		<#list topnavigationList as navigation>
		   <tr>
		        <td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
			    </td>
			    <td>
						${navigation.name}
				</td>
				<td>
						顶部
				</td>
				<td>
				<#if navigation.blankTarget>
						是
				<#else>
				                           否
				</#if>
				</td>
				<td>
						${navigation.orders}
				</td>
				<td>
					<a href="edit.do?id=${navigation.id}">[编辑]</a>
				</td>
		   </tr>
		</#list>
		<#if topnavigationList?has_content>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
		</#if>
		<#list middlenavigationList as navigation>
		   <tr>
		        <td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
			    </td>
			    <td>
						${navigation.name}
				</td>
				<td>
						中部
				</td>
				<td>
				<#if navigation.blankTarget>
						是
				<#else>
				                           否
				</#if>
				</td>
				<td>
						${navigation.orders}
				</td>
				<td>
					<a href="edit.do?id=${navigation.id}">[编辑]</a>
				</td>
		   </tr>
		</#list>	
		<#if middlenavigationList?has_content>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
		</#if>
		<#list bottomnavigationList as navigation>
		   <tr>
		        <td>
						<input type="checkbox" name="ids" value="${navigation.id}" />
			    </td>
			    <td>
						${navigation.name}
				</td>
				<td>
						底部
				</td>
				<td>
				<#if navigation.blankTarget>
						是
				<#else>
				                           否
				</#if>
				</td>
				<td>
						${navigation.orders}
				</td>
				<td>
					<a href="edit.do?id=${navigation.id}">[编辑]</a>
				</td>
		   </tr>
		</#list>			
		</table>
	</form>

</body>
</html>