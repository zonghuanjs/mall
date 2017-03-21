<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>安装包设置</title>
<meta name="author" content="普福软件研发部" />
<meta name="copyright" content="普福软件研发部" />
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $browserButton = $("#browserButton");
	
	
	$browserButton.browseDialog({
		type: 'file',
		uploadFileExtension: '${config.uploadFileExtension}'
	});
	
	// 表单验证
	$inputForm.validate({
		errorClass: "fieldError",
		ignoreTitle: true,
		rules: {
			versionCode: "required",
			versionName: "required",
			downloadUrl: "required",
			order: "digits",
			packageName:"required",

		},
		messages: {
			versionCode: '必填',
			versionName: '必填',
			downloadUrl: '必填',
			priCertPwd:"必填",
			packageName:"必填"
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 移动端安装包管理
	</div>
	<form id="inputForm" action="add.do" method="post">
		<table class="input">

			<tr>
				<th>
					<span class="requiredField">*</span>类型:
				</th>
				<td>
					<select name="appType" class="valid" style="width:120px;">
						<option value="1" >Android</option>
						<option value="2">iOS</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>版本代码:
				</th>
				<td>
					<input type="text" name="versionCode" class="text" style="width:300px;" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>版本名称:
				</th>
				<td>
					<input type="text" name="versionName" class="text" style="width:300px;" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>下载地址:
				</th>
				<td>
					<input type="text" name="downloadUrl" class="text" style="width:300px;" maxlength="100" />
					<input type="button" id="browserButton" class="button" value="选择文件" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>安装包名称:
				</th>
				<td>
					<input type="text" name="packageName" class="text" style="width:300px;"  maxlength="100" />
				</td>
			</tr>
			<tr>
				<th>
					更新内容描述:
				</th>
				<td>					
					<textarea name="updateContent" class="text"  rows="10" cols="80"></textarea>
				</td>
			</tr>

			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" class="text" value="0" style="width:300px;" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					启用:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" />
					</label>
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