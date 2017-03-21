<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品品牌</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript">
$().ready(function(){
	var $backButton = $('#backButton');
	var $inputForm = $('#inputForm');
	
	$backButton.bind('click', function(){
		window.location.href = '${base}/admin/product/BrandList.do';
		                        
	});
	
	$inputForm.validate({
		rules: {
			name: "required",
			logo: "required",
			url: "url",
			orders: "digits"			
		},
		messages: {
			name: "必填",	
			logo: "必填",	
			url: "请输入合法的网址",
			orders:"必须为数字"		
		}
	});
	
	
	var $type = $("#type");
	var $logo = $("#logo");
	var $browserButton = $("#browserButton");
		
	$type.change(function() {
		if ($(this).val() == "1") {
			$logo.val("").prop("disabled", true);
			$browserButton.prop("disabled", true);
		} else {
			$logo.prop("disabled", false);
			$browserButton.prop("disabled", false);
		}
	});
	
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
});



</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if brand??>编辑<#else>添加</#if>品牌
	</div>
	<form id="inputForm" action="<#if brand??>${base}/admin/brand/save.do?id=${brand.id}<#else>${base}/admin/brand/add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200"  <#if brand??>value="${brand.name}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					类型:
				</th>
				<td>
					<select id="type" name="type">
					
							<option value="1" >文本</option>
							<option value="2" <#if brand??&&brand.type==2>selected="selected"</#if>>图片</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					logo:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" id="logo" name="logo" class="text" maxlength="200"  <#if brand??&&brand.type==2>value="${brand.logo}"<#else> disabled="disabled"</#if>/>
						<input type="button" id="browserButton" name="browserButton" class="browserButton button" value="选择文件"  <#if !brand??||brand.type!=2>disabled="disabled"</#if>/>
						
						 <#if brand??&&brand.type==2>
				           <a href="${base}${brand.logo}" target="_blank">查看</a>
				         </#if>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					网址:
				</th>
				<td>
					<input type="text" name="url" class="text" maxlength="200" <#if brand??>value="${brand.url}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="orders" class="text" maxlength="9" <#if brand??>value="${brand.orders}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					介绍:
				</th>
				<td>
					<textarea id="editor" name="description" class="editor"><#if brand??>${brand.description}</#if></textarea>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/brand/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>