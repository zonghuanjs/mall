<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加友情链接 </title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $systemUrl = $("#systemUrl");
	var $url = $("#url");
	

	// 将选择的系统内容地址填充至链接地址中
	$systemUrl.change(function() {
		$url.val($systemUrl.val());
	});
	
	// 链接地址内容修改时,系统内容选择框修改为不选择任何项目
	$url.keypress(function() {
		$systemUrl.val("");
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			url: "required",
			order: "digits"
		},
		messages: {
			name: "必填",				
			url: "必填",
			orders:"必须为数字"		
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加导航
	</div>
	<form id="inputForm" action="<#if navigation??>${base}/admin/navigation/save!${navigation.id}.do<#else>${base}/admin/navigation/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" <#if navigation??>value="${navigation.name}"</#if> title="必填"/>	 
				</td>
			</tr>
			<tr>
				<th>
					系统内容:
				</th>
				<td>
					<select id="systemUrl" title="导航所置顶的内容">
						<option value="">------------</option>
						<option value="${base}/" >首页</option>
						<option value="${base}/product/list.do" >商品分类</option>
						<option value="${base}/center/index.do" >会员中心</option>				
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>链接地址:
				</th>
				<td>
					<input type="text" id="url" name="url" class="text" maxlength="200" <#if navigation??>value="${navigation.url}"</#if> title="必填，填写所链接的网址货链接"/>
				</td>
			</tr>
			<tr>
				<th>
					位置:
				</th>
				<td>
					<select name="position" title="导航所在的位置，分为上，中，下">
							<option value="1" <#if navigation??&&navigation.position==1>selected</#if>>顶部</option>
							<option value="2" <#if navigation??&&navigation.position==2>selected</#if>>中间</option>
							<option value="3" <#if navigation??&&navigation.position==3>selected</#if>>底部</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
					<label>
						<input type="checkbox" name="blankTarget" value="true" <#if navigation??&&navigation.blankTarget>checked="checked"</#if>/>是否新窗口打开
						<input type="hidden" name="blankTarget" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" <#if navigation??>value="${navigation.orders}"<#else>value="0"</#if> title="若留空则由系统自动填为0，必须为整数"/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/navigation/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>