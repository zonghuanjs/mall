<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>日志列表</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	var $clearButton = $("#clearButton");
	var $resultRow = $("#listTable tr:gt(0)");
	
	
	$clearButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "您确定要清空吗？",
			onOk: function() {
				$.ajax({
					url: "deleteall.do",
					type: "POST",
					dataType: "json",
					cache: false,
					success: function(data){
						try{
							if(data.errCode == 0){
								window.location.reload(true);
							}else{
								alert('删除失败!');
							}
						}catch(e){
							alert(e);
						}
					}
				});
			}
		});
		return false;
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 日志列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="clearButton" class="iconButton">
					<span class="clearIcon">&nbsp;</span>清空
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if>>10</a>
							</li>
							<li>
								<a href="javascript:;"  val="20" <#if pager.pageSize==20>class="current"</#if>>20</a>
							</li>
							<li>
								<a href="javascript:;" val="50" <#if pager.pageSize==50>class="current"</#if>>50</a>
							</li>
							<li>
								<a href="javascript:;" val="100" <#if pager.pageSize==100>class="current"</#if>>100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" <#if searchValue??>value="${searchValue}"</#if> maxlength="200" />
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "operation"> class="current"</#if> val="operation">操作</a>
						</li>
						<li>
							<a href="javascript:;" <#if searchProperty??&&searchProperty == "operator"> class="current"</#if> val="operator">操作员</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="operation">操作</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="operator">操作员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="ip">IP</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="content">内容</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list logList as log>
			<tr>
			<td>
			<input type="checkbox" name="ids" value="${log.id}"/>
			</td>
			<td><#if log.operation??>${log.operation}</#if></td>
			<td><#if log.operator??>${log.operator}</#if></td>
			<td><#if log.ip??>${log.ip}</#if></td>
			<td><#if log.content??>${log.content}</#if></td>
			<td><#if log.createDate??>${log.createDate}</#if></td>
			<td><a href="${base}/log/edit.do?id=${log.id}">[查看]</a></td>
			</tr>
			</#list>
		</table>
		<#include "../common/pager.ftl">
	</form>
</body>
</html>