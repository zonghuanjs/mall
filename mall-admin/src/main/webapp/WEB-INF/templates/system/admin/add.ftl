<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="resources/css/list.css"/>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/jquery.tab.js"></script>
<script type="text/javascript" src="resources/js/input.js"></script>
<script type="text/javascript">
$(function(){
$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	var $inputForm = $('#inputForm');
	
	$inputForm.validate({
		rules: {
			username: {
			<#if !admin??>
				remote: 'checkAdminName.do',
			</#if>
				required: true,
				minlength: 3
			},
	<#if admin??>
			password: {
				minlength: 6
			},
			rePassword: {
				equalTo: '#password',
				minlength: 6
			},
	<#else>
			password: {
				required: true,
				minlength: 6
			},
			rePassword: {
				required: true,
				equalTo: '#password',
				minlength: 6
			},
	</#if>
			email: {
				required: true,
				email: true
			},
			roleIds: {
				required: true,
				minlength: 1,
				maxlength: 1
			}
		},
		messages: {
			username: {
				required: '请输入用户名',
				minlength: '用户名最小长度为6',
				remote: '账号已存在'
			},
			password: {
				required: '请输入密码',
				minlength: '密码最小长度为6'
			},
			rePassword: {
				required: '请再次输入密码',
				minlength: '密码最小长度为6',
				equalTo: '两次输入的密码不一致'
			},
			email: {
				required: '请输入email地址',
				email: '请输入有效的email地址'
			},
			roleIds: {
				required: '*'
			}
		}
	});
});
</script>
</head>
<body>
	<div class="path">
		<a href="home.html">首页</a> &raquo; 添加管理员
	</div>
	<form id="inputForm" <#if admin??> action="save!${admin.id}.do" <#else> action="add.do" </#if> method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="个人资料" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>用户名:
				</th>
				<td>
				<#if admin??>
					${admin.username}
				<#else>
					<input type="text" name="username" class="text" maxlength="20" />
				</#if>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>密码:
				</th>
				<td>
					<input type="password" id="password" name="password" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>确认密码:
				</th>
				<td>
					<input type="password" name="rePassword" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>E-mail:
				</th>
				<td>
					<input type="text" name="email" class="text" maxlength="200" <#if admin??>value="${admin.email}"</#if> />
				</td>
			</tr>
			<tr class="roles">
				<th>
					<span class="requiredField">*</span>角色:
				</th>
				<td>
					<span class="fieldSet">
					<#list roles as role>
						<label>
							<input type="checkbox" name="roleIds" value="${role.id}" <#if admin?? && admin.roles?seq_contains(role)> checked="checked"</#if> />${role.name}
						</label>
					</#list>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true"<#if admin?? && !admin.enabled> <#else> checked="checked" </#if> />是否启用
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<th>
					部门:
				</th>
				<td>
					<input type="text" name="department" class="text" maxlength="200" <#if admin??> value="${admin.department}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					姓名:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if admin??> value="${admin.name}" </#if> />
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