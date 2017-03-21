<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 增加失踪宝贝
	</div>
	<form id="inputForm" action="add.do" method="post" >
		<!-- 基本设置 -->
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>寻亲类型:
				</th>
				<td>
					<select name="type" >
						<option value="家庭寻亲" selected="selected">家庭寻亲</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>寻亲编号:
				</th>
				<td>
					<input type="text" name="sn" class="text" value="${sn}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>失踪者姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>性别:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="gender" value="男" checked="checked" />男
						</label>
						<label>
							<input type="radio" name="gender" value="女" />女
						</label>
					</fieldset>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>失踪者生日:
				</th>
				<td>
					<input type="text" name="birth" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>失踪者身高:
				</th>
				<td>
					<input type="text" name="heights" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>失踪时间:
				</th>
				<td>
					<input type="text" name="loseTime" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>失踪地址:
				</th>
				<td>
					<input type="text" name="loseAddr" class="text longtext" />
				</td>
			</tr>
			<tr>
				<th>
					失踪者特征:
				</th>
				<td>
					<input type="text" name="features" class="text longtext" />
				</td>
			</tr>
			<tr>
				<th>
					其他描述:
				</th>
				<td>
					<textarea name="otherDescription" class="text"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					登记时间:
				</th>
				<td>
					<input type="text" name="features" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					联系方式:
				</th>
				<td>
					<input type="text" name="contact" class="text" />
				</td>
			</tr>
			<tr>
				<th>
					失踪者照片:
				</th>
				<td>
					<input type="text" name="image" class="text" />
					<input type="button" class="button browserButton" value="选择文件" />
				</td>
			</tr>
		</table>
		
		<table class="input">
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