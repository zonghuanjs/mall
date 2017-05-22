<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加配送方式</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!shippingMethodAdd.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/shipping_method.js"></script>
<style type="text/css">
.parameterTr input[type=text]
{
	width: 40px;
}
</style>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 编辑配送方式
	</div>
	<form id="inputForm" action="<#if method??>${base}/admin/shippingMethod/save!${method.id}.do<#else>${base}/admin/shippingMethod/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if method??>value="${method.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					默认物流公司:
				</th>
				<td>
					<select name="defaultDeliveryCorpId">
						<option value="">请选择...</option>
					<#list deliveryCorps as deliveryCorp>
						<option value="${deliveryCorp.id}" <#if method.defaultDeliveryCorp?has_content && method.defaultDeliveryCorp == deliveryCorp>selected</#if> >
								${deliveryCorp.name}
						</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					图标:
				</th>
				<td>
					<input type="text" name="icon" class="text" maxlength="200" <#if method??>value="${method.icon!''}"</#if> />
					<input type="button" class="button browserButton" value="选择文件" />
					<a <#if method??>href="${base}${method.icon}"</#if> target="_blank">查看</a>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
						<input type="text" name="order" class="text" maxlength="9" value="${method.orders!0}" />
				</td>
			</tr>
			<tr>
				<th>
					介绍:
				</th>
				<td>
					<input type="text" name="description" class="text" value="${method.description!''}" />
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
				
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<a href="javascript:;" id="addRule" class="button">增加规则</a>
				</td>
			</tr>
		</table>
	</form>
	<table class="input" id="rulesTable">
		<tr class="title">
			<td>
				&nbsp;
				<input type="hidden" name="methodId" id="methodId" value="${method.id}" />
			</td>
			<td>
				<span class="requiredField">*</span>目标地区
			</td>
			<td>
				首重
			</td>
			<td>
				首重价格
			</td>
			<td>
				续重
			</td>
			<td>
				续重价格
			</td>
			<td>
				操作
			</td>
		</tr>
	<#if method.rules?has_content>
	  <#list method.rules as rule>
		<tr class="parameterTr">
			<td>
				<input type="hidden" name="ruleId" value="${rule.id}" />
			</td>
			<td class="area">
			<#if rule.areas?has_content>
			  <#list rule.areas as area>
				<label>
					<input type="checkbox" name="areas" value="${area.id}" checked="checked">${area.name}
				</label>
			  </#list>
			</#if>
			</td>
			<td>
				<input type="text" name="firstWeight" value="${rule.firstWeight}" />
			</td>
			<td>
				<input type="text" name="firstPrice" value="${rule.firstPrice}" />
			</td>
			<td>
				<input type="text" name="continueWeight" value="${rule.continueWeight}" />
			</td>
			<td>
				<input type="text" name="continuePrice" value="${rule.continuePrice}" />
			</td>
			<td>
				<a href="javacript:;" class="button select" >选择地区</a>
				<a href="javacript:;" class="button save" >保存</a>
				<a href="javacript:;" class="button delete" >删除</a>
			</td>
		</tr>
	  </#list>
	</#if>
	</table>
</body>
</html>