<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>问题反馈详情</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $confirmButton = $("#confirmButton");
	var $editButton = $("#editButton");
	var $response = $("#response");	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});

	$confirmButton.click(function() {
		var $this = $(this);		
		$.dialog({
			type: "warn",
			content: "确定要提交处理反馈吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/feedback/edit.do',
					type: 'post',
					data: {id: ${feedback.id},response:$response.val()},
					dataType: 'json',
					success: function(data){
						if(data.errCode == 0){
						    location.reload(true);
						    alert("操作成功");
						}else
						{
						    location.reload(true);
						    alert("操作失败");
						}
					}
				});
			}
		});
	});
	
	$editButton.click(function() {
		var $this = $(this);		
		$.dialog({
			type: "warn",
			content: "确定要修改吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/feedback/edit.do',
					type: 'post',
					data: {id: ${feedback.id},response:$response.val()},
					dataType: 'json',
					success: function(data){
						if(data.errCode == 0){
						    location.reload(true);
						    alert("操作成功");
						}else
						{
						    location.reload(true);
						    alert("操作失败");
						}
					}
				});
			}
		});
	});

});
</script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 查看订单
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="订单信息" />
		</li>
		<li>
			<input type="button" value="图片信息" />
		</li>
	</ul>
	<!-- 订单信息 -->
	<table class="input tabContent">
		<tr>
			<th>
				用户名:
			</th>
			<td width="360">
				${feedback.member.username!"-"}
			</td>
			<th>
				创建日期:
			</th>
			<td>
				${feedback.createDate!'-'}
			</td>
		</tr>
		
		<tr>
			<th>
				修改日期:
			</th>
			<td>
				${feedback.createDate!'-'}
			</td>
			<th>
				类型:
			</th>
			<td>
				${feedback.typeView!""}
			</td>
		</tr>
		<tr>
			<th>
				是否已查看:
			</th>
			<td>
				<#if feedback.viewed>是<#else>否</#if>			
			</td>
			<th>
				
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
			<th>
				详细描述:
			</th>
			<td colspan="3" rowspan="3" >
				${feedback.detail!'-'}
			</td>
		</tr>
	</table>
	
	<table class="input">
	    <#if images?has_content>
	    <#list images as image>
		<tr>
			<th>
				图片:
			</th>
			<td>	
				<a href="${mall_url(image, base)!''}" target="_blank">查看</a>
			</td>
		</tr>
		</#list>
		<#else>
		<tr>
			<th>
			
			</th>
			<td>	
				无图片
			</td>
		</tr>
		</#if>
	</table>

	<table class="input">
	    <tr>
		    <th>
				填写处理反馈:
			</th>			
			<td>
				<textarea id="response" name="response" style="width: 60%;height:80px" maxlength="250">${feedback.response!""}</textarea>
			</td>
	
		</tr>
		<tr>
		    <th>
				操作:
			</th>			
			<td>
				<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);" />
				<#if !feedback.viewed>
				  <input id="confirmButton" type="button" class="button" value="提交处理反馈"/>
				</#if>
				<#if feedback.viewed>
				  <input id="editButton" type="button" class="button" value="修改"/>
				</#if>	
			</td>
	
		</tr>
	</table>
</body>
</html>