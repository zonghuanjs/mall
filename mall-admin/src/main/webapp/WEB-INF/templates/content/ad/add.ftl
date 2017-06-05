<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加广告</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $type = $("#type");	
	var $pathTr = $("#pathTr");
	var $path = $("#path");
	
	// "类型"修改
	$type.change(function() {
		if ($type.val() == "1") {
	
			$pathTr.hide();
			$path.prop("disabled", true)
		} else {	
			$pathTr.show();
			$path.prop("disabled", false)
		}
	});	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			adPositionId: "required",
			path: "required",
			beginDate: "required",
			endDate: "required",
			orders: "digits"
		},
		messages: {
			title: "必填",	
			adPositionId: "必填",
			path:"必填",	
			beginDate: "必填",
			endDate: "必填",
			orders:"必须为整数"			
		}
	});
	
});
</script>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 添加广告
	</div>
	<form id="inputForm" action="<#if ad??>save.do?id=${ad.id}<#else>add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>标题:
				</th>
				<td>
					<input type="text" name="title" class="text" maxlength="200" <#if ad??>value="${ad.title}"</#if> title="必填选项"/>
				</td>
			</tr>
			<tr>
				<th>
					类型:
				</th>
				<td>
					<select id="type" name="type" title="选择广告的类型，有文本，图片，flash">
						<option value="1" <#if ad??&&ad.type==1>selected</#if> >文本</option>
						<option value="2" <#if ad??&&ad.type==2>selected</#if> >图片</option>
						<option value="3" <#if ad??&&ad.type==3>selected</#if> >flash</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					广告位:
				</th>
				<td>
					<select name="position" title="选择广告所处的位置">
					<#list adPositionList as adPosition>
						<option value="${adPosition.id}" <#if ad?? && adPosition==ad.position> selected="selected"</#if> >${adPosition.name}</option>						
					</#list>
					</select>
				</td>
			</tr>
			<tr id="contentTr" >
				<th>
					内容:
				</th>
				<td>
					<label>（类型选中图片或者flash时，此处输入不要换行，各句之间以";"分割，且各个语句字数控制在9字以内为好.例如：Tekism固态硬盘；新学期 新的装备）</label>
					<textarea id="editor" name="content" class="editor" style="height:200px;border:1px solid #DDD;"><#if ad??>${ad.content}</#if></textarea>
				</td>
			</tr>
			<tr id="pathTr" <#if ad??&&ad.type != 2&&ad.type != 3||!ad??> class="hidden"</#if>>
				<th>
					<span class="requiredField">*</span>路径:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="path" name="path" class="text" maxlength="200" <#if ad??&&ad.path??>value="${ad.path}"<#else>disabled="disabled" </#if>/>
						<input type="button" id="browserButton" class="button browserButton" value="选择文件" />
						<a href="<#if ad??>${mall_url(ad.path, base)!''}</#if>" target="_blank" >查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					起始日期:
				</th>
				<td>
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});"  <#if ad??>value="${ad.beginDate}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					结束日期:
				</th>
				<td>
					<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if ad??>value="${ad.endDate}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					链接地址:
				</th>
				<td>
					<input type="text" name="url" class="text" maxlength="200" <#if ad??>value="${ad.url}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" <#if ad??>value="${ad.orders}"<#else>value="0"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1)'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>