<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看增值发票信息</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $confirmButton = $("#confirmButton");
	var $cancelButton = $("#cancelButton");
	var $reason = $("#reason");	

	// 确认
	$confirmButton.click(function() {
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要通过审核吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/addTax/confirm.do',
					type: 'post',
					data: {id: ${addTax.id}},
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
	
		
	// 取消
	$cancelButton.click(function() {
		var $this = $(this);		
		$.dialog({
			type: "warn",
			content: "确定要审核不通过吗?",
			onOk: function() {
				$.ajax({
					url: '${base}/admin/addTax/cancel.do',
					type: 'post',
					data: {id: ${addTax.id},reason:$reason.val()},
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
		<a href="${base}/admin/home.do">首页</a> &raquo; 查看增至发票信息
	</div>
	<table  class="input">								
			<tr>
			    <th>
					纳税人识别号:
				</th>
				<td>
					${addTax.idNumber!"-"}
				</td>	
				<th>
					单位名称:
				</th>
				<td>
					${addTax.company!"-"}
				</td>
								
			</tr>		
			<tr>
			    <th>
					注册地区:
				</th>
				<td>
					${addTax.registerArea.fullName!"-"}
				</td>
				<th>
					注册地址:
				</th>
				<td>
					${addTax.registerAddress!"-"}
				</td>				
			</tr>
			<tr>
				<th>
					注册电话:
				</th>
				<td>
					${addTax.registerTel!"-"}
				</td>
				<th>
					注册银行:
				</th>
				<td>
					${addTax.registerBank!"-"}
				</td>				
			</tr>
			
			<tr>
				<th>
					注册账户:
				</th>
				<td>
					${addTax.registerAccount!"-"}
				</td>	
				<th>
					创建时间
				</th>
				<td>
					${addTax.createDate!"-"}
				</td>			
			</tr>	
			<tr>
				<th>
					关联用户:
				</th>
				<td>
					${addTax.member.username!"-"}
				</td>	
				<th>
					当前审核状态：
				</th>
				<td>
					${addTax.currentStatus}
				</td>			
			</tr>	
		<tr>
			<th>
				开票资料:
			</th>
			<td colspan="3">
			<#if addTax.taxData?has_content>				
				<a href="${mall_url(addTax.taxData, base)!''}" target="_blank" style="color:#00F">查看</a>
			<#else>
				无
			</#if>
			</td>
		</tr>				
		<tr>
			<th>
				税务登记证:
			</th>
			<td colspan="3">
			<#if addTax.registerCertificate?has_content>				
				<a href="${mall_url(addTax.registerCertificate, base)!''}" target="_blank" style="color:#00F">查看</a>
			<#else>
				无
			</#if>
			</td>
		</tr>
		<tr>
			<th>
				纳税人证书:
			</th>
			<td colspan="3">
			<#if addTax.taxCertificate?has_content>				
				<a href="${mall_url(addTax.taxCertificate, base)!''}" target="_blank" style="color:#00F">查看</a>
			<#else>
				无
			</#if>
			</td>
		</tr>
		<tr>
			<th>
				替代资料:
			</th>
			<td colspan="3">
			<#if addTax.otherData?has_content>				
				<a href="${mall_url(addTax.otherData, base)!''}" target="_blank" style="color:#00F">查看</a>
			<#else>
				无
			</#if>
			</td>
		</tr>
	</table>
	<table class="input">		
		<tr>
		    <th>
				审核不通过原因:
			</th>
			<td>
				<textarea id="reason" name="reason" style="width: 60%;height:80px" maxlength="250">${addTax.reason!''}</textarea>
			</td>
			<th>
				
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
		    <th>
				操作:
			</th>
			<td>
				<#if addTax.checked == true>
				<#else>
				<input type="button" id="confirmButton" class="button" value="审核通过" />	
				<input type="button" id="cancelButton" class="button" value="审核不通过" />	
				</#if>
				<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/addTax/list.do'" />
			</td>
			<th>
				
			</th>
			<td>
				
			</td>
		</tr>
	</table>			
	
</body>
</html>