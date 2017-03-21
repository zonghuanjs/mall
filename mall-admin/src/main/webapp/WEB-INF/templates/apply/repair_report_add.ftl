<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加维修报告</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $free = $inputForm.find('input').filter('[name=free]');
	var $amountRow = $('tr#amountRow');
	var $amount = $('#amount');
	var $amountError = $('tr#amountRow .error');
	
	$free.change(function(){
		var $this = $(this);
		if($this.is(':checked')){
			$amountRow.hide();
		}else{
			$amountRow.show();
		}
	});
	
	//维修金额输入限制
	$amount.keypress(function(event) {
		var key = event.keyCode ? event.keyCode : event.which;
		if ((key == 13 && $(this).val().length > 0) || (key >= 48 && key <= 57)) {
			return true;
		} else {
			return false;
		}
	});
	
	// 表单验证
	$inputForm.validate({
		errorPlacement: function(error, element){
			var $place = $(element).closest('td');
			error.appendTo($place).css({color: 'red'})
		},
		rules: {
			facade: "required",
			performance: 'required'
		},
		messages: {
			facade: "必填",
			performance: "必填"		
		},
		submitHandler:function(form) {
			if(!$free.is(':checked'))
			{
				if(isNaN($amount.val()))
				{
					$amountError.show();
					$amountError.text('只能为数字');
					$amount.focus();
					return false;
				}
				else if($.trim($amount.val()).length == 0)
				{
					$amountError.show();
					$amountError.text('金额不能为空');
					return false;
				}
			}
			form.submit();
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="/admin/common/index.jhtml">首页</a> &raquo; <#if repair.report??>编辑<#else>添加</#if>维修报告
	</div>
	<form id="inputForm" action="<#if repair.report??>${base}/admin/report/edit.do?id=${repair.id}<#else>${base}/admin/report/add.do?id=${repair.id}</#if>" method="post">
		<table class="input">  
			<tr>
				<th>
					<span class="requiredField">*</span>外观检测:
				</th>
				<td>
					<label>
						<input type="radio" name="facade" value="true" <#if repair.report?? && repair.report.facade>checked="checked"</#if> />有异常
					</label>
					<label>
						<input type="radio" name="facade" value="false" <#if repair.report?? && !repair.report.facade>checked="checked"</#if> />无异常
					</label>
				</td>
			</tr>	
			
			<tr>
				<th>
					问题描述:
				</th>
				<td>
					<textarea id="editor" name="facadeDescription" class="editor" style="height:200px;border:1px solid #DDD;"><#if repair.report??>${repair.report.facadeDescription!''}</#if></textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>性能检测:
				</th>
				<td>
					<label>
						<input type="radio" name="performance" value="true" <#if repair.report?? && repair.report.performance>checked="checked"</#if> />有异常
					</label>
					<label>
						<input type="radio" name="performance" value="false" <#if repair.report?? && !repair.report.performance>checked="checked"</#if> />无异常
					</label>
				</td>
			</tr>	
			
			<tr>
				<th>
					问题描述:
				</th>
				<td>
					<textarea id="editor" name="performanceDesc" class="editor" style="height:200px;border:1px solid #DDD;"><#if repair.report??>${repair.report.performanceDesc!''}</#if></textarea>
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="requiredField">*</span>是否免费维修:
				</th>
				<td>
					<label>
						<input type="checkbox" name="free" value="true" <#if repair.report??><#if repair.report.free>checked="checked"</#if><#else>checked="checked"</#if> />免费维修
					</label>					
				</td>				
			</tr>
			<tr id="amountRow" <#if repair.report??><#if repair.report.free>style="display: none;"</#if><#else>style="display: none"</#if> >
				<th>
					维修金额:
				</th>
				<td>
					<input type="text" name="amount" id="amount" class="text" maxlength="9" <#if repair.report?? && !repair.report.free>value="${repair.report.amount}"</#if> />元
					<label class="error" for="amount" style="color:red;"></label>
				</td>
			</tr>
			<tr>
				<th>
					是否检测完毕:
				</th>
				<td>
					<label>
						<input type="checkbox" name="finished" value="true" />已完毕
					</label>
				</td>
			</tr>					
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" id="backButton" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>