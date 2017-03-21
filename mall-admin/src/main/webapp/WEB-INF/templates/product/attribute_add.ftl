<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品属性</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!category.do"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $attributeTable = $("#attributeTable");
	var $addOption = $("#addOption");
	var $deleteOption = $("a.deleteOption");
	var $name = $("#name").val();
	
	// 增加可选项
	$addOption.bind("click", function() {
		var $this = $(this);
		var trHtml = '<tr class="optionTr"> <td> &nbsp; <\/td> <td> <input type="text" name="options" class="text" maxlength="200" \/> <\/td> <td> <a href="javascript:;" class="deleteOption">[删除]<\/a> <\/td> <\/tr>';		
		$attributeTable.append(trHtml);
		$attributeTable.find('.deleteOption').unbind().click(function(){
			var $this = $(this);
			if ($attributeTable.find("tr.optionTr").size() <= 1) {
				alert("必须至少保留一个可选项");
			} else {
				$this.closest("tr").remove();
			}
		});
	});
	
	// 删除可选项
	$deleteOption.bind("click", function() {
		var $this = $(this);
		if ($attributeTable.find("tr.optionTr").size() <= 1) {
			alert("必须至少保留一个可选项");
		} else {
			$this.closest("tr").remove();
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if attribute??>编辑<#else>添加</#if>属性
	</div>
	<form id="inputForm" action="<#if attribute??>${base}/admin/attribute/save!${attribute.id}.do<#else>${base}/admin/attribute/add.do</#if>"  method="post">
		<table id="attributeTable" class="input">
			<tr>
				<th>
					绑定分类:
				</th>
				<td colspan="3">
					<select name="productCategoryId" title="绑定商品属性与商品分类">
						<option value="">请选择...</option>
					<#list categoryList as category>
						<option value="${category.id}" <#if attribute?? && category.id == attribute.productCategory>selected</#if> > ${category.name} </option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td colspan="3">
					<input type="text" name="name" id="name" class="text" maxlength="200" title="必填项，商品属性名称" <#if attribute??>value="${attribute.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td colspan="3">
					<input type="text" name="order" id="order" class="text" maxlength="9" <#if attribute??>value="${attribute.orders}"</#if>/><span class="tip"></span>
				</td>
			</tr>
			<tr>
				<td> &nbsp;</td>
				<td colspan="2">
					<a href="javascript:;" id="addOption" class="button">增加可选项</a>
				</td>
			</tr>
			
			<tr class="title">
				<td>&nbsp;
					
				</td>
				<td>
					<span class="requiredField">*</span>可选项
				</td>
				<td>
					删除
				</td>
			</tr>
			<#if attribute??>
				<#list optionsList as option>
					<#if option??>
					<#if option.attribute == attribute>
					<tr class="optionTr">
						<td>&nbsp;
							<input type="hidden" name="optionId" value="${option.id}" />
						</td>
						<td>
							<input type="text" name="options_${option.id}" class="text" maxlength="200" value="${option.options}"/>
						</td>
						<td>
							<a href="javascript:;" class="deleteOption">[删除]</a>
						</td>
					</tr>
					</#if>
					</#if>
				</#list>
			</#if>
		</table>
		<table class="input">
			<tr>
				<th>&nbsp;
					
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/attribute/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>