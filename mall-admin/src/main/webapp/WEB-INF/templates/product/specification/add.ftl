<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品规格</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $specificationTable = $("#specificationTable");
	var $type = $("#type");
	var $addSpecificationValueButton = $("#addSpecificationValueButton");
	var $deleteSpecificationValue = $("a.deleteSpecificationValue");
	
	$inputForm.validate({
		rules: {
			name: "required",		
			orders: "digits",
			valuename:"required"			
		},
		messages: {
			name: "必填",	
			orders:"必须为数字",
			valuename: "必填",		
		}
	});
	
	$.validator.addClassRules({
		specificationValuesName: {
			required: true
		},
		specificationValuesImage: {
			required: true
		},
		specificationValuesOrder: {
			digits: true
		}
	});
	
	// 修改规格类型
	$type.change(function() {
		if ($(this).val() == "1") {
			$("input.specificationValuesImage").val("").prop("disabled", true);
			$("input.browserButton").prop("disabled", true);
		} else {
			$("input.specificationValuesImage").prop("disabled", false);
			$("input.browserButton").prop("disabled", false);
		}
	});
		
	// 增加规格值
	$addSpecificationValueButton.click(function() {
		if ($type.val() == "1") {
var trHtml = '<tr class="specificationValueTr"> <td> &nbsp; <\/td> <td> <input type="text" name="valuename" maxlength="200" class="text specificationValuesName" \/> <\/td>  <td> <span class="fieldSet"> <input type="text" name="valueimage" maxlength="200" class="text specificationValuesImage" disabled="disabled" \/> <input type="button" class="button browserButton" value="选择文件" disabled="disabled" \/> <\/span> <\/td> <td> <input type="text" name="valueorders" maxlength="9" class="text specificationValuesOrders" style="width: 30px;" value="0"\/> <\/td> <td> <a href="javascript:;" class="deleteSpecificationValue">[删除]</a> <\/td> <\/tr>';		} else {
var trHtml = '<tr class="specificationValueTr"> <td> &nbsp; <\/td> <td> <input type="text" name="valuename" class="text specificationValuesName" maxlength="200" \/> <\/td> <td> <span class="fieldSet"> <input type="text" name="valueimage" class="text specificationValuesImage" maxlength="200" \/> <input type="button" class="button browserButton" value="选择文件" \/> <\/span> <\/td> <td> <input type="text" name="valueorders" class="text specificationValuesOrders" maxlength="9" style="width: 30px;" value="0"\/> <\/td> <td> <a href="javascript:;" class="deleteSpecificationValue">[删除]</a> <\/td> <\/tr>';		}
		$specificationTable.append(trHtml).find("input.browserButton:last").browseDialog();		
	});
	
	// 删除规格值
	$deleteSpecificationValue.bind("click", function() {
		var $this = $(this);
		if ($specificationTable.find("tr.specificationValueTr").size() <= 1) {
			$.message("warn", "必须至少保留一个规格值");
		} else {
			$this.closest("tr").remove();
		}
	});
	
	
});
</script>

</head>
<body>
	<div class="path">
		<a href="home.do">首页</a> &raquo; 添加规格
	</div>
	<form id="inputForm" action="<#if specification??>${base}/specification/save.do?id=${specification.id}<#else>${base}/specification/add.do</#if>" method="post">
		<table id="specificationTable" class="input">
			<tr class="titleTr">
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td colspan="4">
					<input type="text" name="name" class="text" maxlength="200" title="规格名称，必填" <#if specification??>value="${specification.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					类型:
				</th>
				<td colspan="4">
					<select id="type" name="type" <#if specification??>value="${specification.type}"</#if> title="选择规格类型">
							<option value="1">文本</option>
							<option value="2" <#if specification??&&specification.type==2>selected="selected"</#if>>图片</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td colspan="4">
					<input type="text" name="memo" class="text" maxlength="200" <#if specification??>value="${specification.memo}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td colspan="4">
					<input type="text" name="orders" class="text" maxlength="9" title="必须为整数，若不填系统自动填为0" <#if specification??>value="${specification.orders}"</#if>/>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td colspan="4">
					<a href="javascript:;" id="addSpecificationValueButton" class="button">增加规格值</a>
				</td>
			</tr>
			<tr class="title">
				<td>
					&nbsp;
				</td>
				<td>
					<span class="requiredField">*</span>规格值名称
				</td>
				<td>
					规格值图片
				</td>
				<td>
					规格值排序
				</td>
				<td>
					删除
				</td>
			</tr>
	 <#if specification??>
		<#list specification.values as value>
			<tr class="specificationValueTr">
				<td>
					&nbsp;<input type="hidden" name="ids" id="ids" value="${value.id}" />
				</td>
				<td>
					<input type="text" name="valuename" class="text specificationValuesName" maxlength="200" value="${value.name}"/>
				</td>
				<td>
					<span class="fieldSet">
						<input type="text" name="valueimage" class="text specificationValuesImage" maxlength="200" <#if specification.type==2>value="${value.image}"<#else> disabled="disabled"</#if> />
						<input type="button" class="button browserButton" value="选择文件" <#if specification.type!=2>disabled="disabled"</#if> />
						 <#if specification.type==2>
				           <a href="${base}${value.image}" target="_blank">查看</a>
				         </#if>
					</span>
				</td>
				<td>
					<input type="text" name="valueorders" class="text specificationValuesOrders" maxlength="9" style="width: 30px;" value="${value.orders}"/>
				</td>
				<td>
					<a href="javascript:;" class="deleteSpecificationValue">[删除]</a>
				</td>
			</tr>
			</#list>
		<#else>
			<tr class="specificationValueTr">
				<td>
					&nbsp;
				</td>
				<td>
					<input type="text" name="valuename" class="text specificationValuesName" maxlength="200" />
				</td>
				<td>
					<span class="fieldSet">
						<input type="text" name="valueimage" class="text specificationValuesImage" maxlength="200" disabled="disabled" />
						<input type="button" class="button browserButton" value="选择文件" disabled="disabled" />
					</span>
				</td>
				<td>
					<input type="text" name="valueorders" class="text specificationValuesOrders" value="0" maxlength="9" style="width: 30px;" value="0"/>
				</td>
				<td>
					<a href="javascript:;" class="deleteSpecificationValue">[删除]</a>
				</td>
			</tr>
		</#if>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="4">
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/specification/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>