<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加参数</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!category.do"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $parameterTable = $("#parameterTable");
	var $addParameter = $("#addParameter");
	var $deleteParameter = $("a.deleteParameter");
	var parameterIndex = 1;
	
	
	// 增加参数
	$addParameter.bind("click", function() {
		var $this = $(this);
		var trHtml = '<tr class="parameterTr"> <td> &nbsp; <\/td> <td> <input type="text" name="parameterName" class="text parametersName" maxlength="200" \/> <\/td> <td> <input type="text" name="parameterOrder" class="text parametersOrder" maxlength="9" style="width: 30px;" \/> <\/td> <td> <a href="javascript:;" class="deleteParameter">[删除]<\/a> <\/td> <\/tr>';		
		$parameterTable.append(trHtml);
		$parameterTable.find('.deleteParameter').unbind().click(function(){
			var $this = $(this);
			if ($parameterTable.find("tr.parameterTr").size() <= 1) {
				alert("必须至少保留一个参数");
			} else {
				$this.closest("tr").remove();
			}
		});
	});
	
	// 删除参数
	$deleteParameter.bind("click", function() {
		var $this = $(this);
		if ($parameterTable.find("tr.parameterTr").size() <= 1) {
			alert("必须至少保留一个参数");
		} else {
			$this.closest("tr").remove();
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if group??>编辑<#else>添加</#if>参数
	</div>
	<form id="inputForm" action="<#if group??>${base}/admin/parameter/save.do?groupId=${group.id}<#else>${base}/admin/parameter/add.do</#if>" method="POST">
		<table id="parameterTable" class="input">
			<tr>
				<th>
					绑定分类:
				</th>
				<td colspan="3">
					<select name="productCategoryId" title="绑定商品参数与商品分类">
						<option value="">请选择...</option>
					<#list categoryList as category>
						<option value="${category.id}" <#if group?? && category.id == group.productCategory.id>selected</#if> > ${category.name} </option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td colspan="3">
					<input type="text" name="name" class="text" id="name" maxlength="200" title="必填，商品参数名称"<#if group??>value=${group.name}</#if> /><span class="info"></span>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td colspan="3">
					<input type="text" name="order" class="text" id="order" maxlength="9" title="必须为整数，若不填系统自动填为0" <#if group??>value="${group.orders}"</#if>/><span class="tip"></span>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td colspan="3">
					<a href="javascript:;" id="addParameter" class="button">增加参数</a>
				</td>
			</tr>
			<tr class="title">
				<td>
					&nbsp;
				</td>
				<td>
					<span class="requiredField">*</span>名称
				</td>
				<td>
					排序
				</td>
				<td>
					删除
				</td>
			</tr>
			<#if group??>
			<#list paramList as param>
				<#if param??>
					<#if param.parameterGroup == group.id>
					<tr class="parameterTr">
						<td>
							&nbsp;
							<input type="hidden" name="parameterId" value="${param.id}" />
						</td>
						<td>
							<input type="text" name="parameterName_${param.id}" class="text parametersName" maxlength="200" value="${param.name}" />
						</td>
						<td>
							<input type="text" name="parameterOrder_${param.id}" class="text parametersOrder" maxlength="9" style="width: 30px;" value="${param.orders}" />
						</td>
						<td>
							<a href="javascript:;" class="deleteParameter">[删除]</a>
						</td>
					</tr>
					</#if>
				</#if>
			</#list>
			</#if>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/parameter/list.do'"  />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>