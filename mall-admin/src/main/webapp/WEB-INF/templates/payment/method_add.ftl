<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript">
$(function(){
	var $inputForm = $('#inputForm');
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				}
			});
		});
	}
	
	$inputForm.validate({
		rules: {
			name: 'required',
			timeout: 'digits',
			order: 'digits'
		},
		messages: {
			name: '必填',
			timeout: '请输入整数',
			order: '请输入整数'
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if method??>编辑<#else>添加</#if>支付方式
	</div>
	<form id="inputForm" <#if method??>action="save!${method.id}.do"<#else>action="add.do"</#if> method="post">
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
					类型:
				</th>
				<td>
					<select name="type">
					<#if method??>
						<option value="0" <#if method.type == 0>selected</#if> >在线支付</option>
						<option value="1" <#if method.type == 1>selected</#if> >线下支付</option>
					<#else>
						<option value="0">在线支付</option>
						<option value="1">线下支付</option>
					</#if>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					超时时间:
				</th>
				<td>
					<input type="text" name="timeout" class="text" maxlength="9" title="单位: 分钟，留空表示永久有效" <#if method??>value="${method.timeout!0}"</#if> />
				</td>
			</tr>
			<tr class="shippingMethods">
				<th>
					支持配送方式:
				</th>
				<td>
				<#list shippingMethods as shippingMethod>
					<label>
						<input type="checkbox" name="shippingMethodIds" value="${shippingMethod.id}" <#if method?? &&  method.shippingMethods?seq_contains(shippingMethod)>checked="checked"</#if> />${shippingMethod.name}
					</label>
				</#list>
				</td>
			</tr>
			<tr>
				<th>
					图标:
				</th>
				<td>
					<input type="text" name="icon" class="text" maxlength="200" <#if method??>value="${method.icon!''}"</#if> />
					<input type="button" id="browserButton" class="browserButton button" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					介绍:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" <#if method??>value="${method.description!''}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" maxlength="9" <#if method??>value="${method.orders!0}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					内容:
				</th>
				<td>
					<textarea id="editor" name="content" class="editor"><#if method??>${method.content!''}</#if></textarea>
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
		</table>
	</form>
</body>
</html>