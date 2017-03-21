<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加文章分类</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	

	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			order: "digits"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加文章分类
	</div>
	<form id="inputForm" action="<#if articlecategory??>${base}/admin/articlecategory/save!${articlecategory.id}.do<#else>${base}/admin/articlecategory/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" title="必填项，文章分类名称" <#if articlecategory??>value="${articlecategory.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					上级分类:
				</th>
				<td>
					<select name="parent" title="文章分类的上级分类">
						    <option value="0" >
						                          顶级分类
						    </option>
							<#list articlecategoryList as list>
							<option value="${list.id}" <#if articlecategory??&&articlecategory.parent==list.id>selected="selected"</#if>>
							${list.name}
							</option>
							</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if articlecategory??>value="${articlecategory.seoTitle}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					页面关键词:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if articlecategory??>value="${articlecategory.seoKeywords}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if articlecategory??>value="${articlecategory.seoDescription}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" title="必须为整数，若不填系统自动填为0"  <#if articlecategory??>value="${articlecategory.orders}"<#else>value="0"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回"  onclick="location.href='${base}/admin/articlecategory/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>