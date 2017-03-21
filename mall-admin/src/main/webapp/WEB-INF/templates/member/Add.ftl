<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加会员</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	$areaId.lSelect({
		url: '${base}/common/areaQuery.do'
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: 2,
				maxlength: 20,
				remote: {
					url: "check_username.do<#if member??>?prevUsername=${member.username?url}</#if>",
					cache: false
				}
			},
			password: {
			<#if member??>
			<#else>
				required: true,
			</#if>
				pattern: /^[^\s&\"<>]+$/,
				minlength: 4,
				maxlength: 20
			},
			rePassword: {
			<#if member??>
			<#else>
				required: true,
			</#if>
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
					,remote: {
						url: "check_email.do<#if member??>?prevEmail=${member.email?url}</#if>",
						cache: false
					}
			},
			point: {
				required: true,
				digits: true
			},
			balance: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				}
			}
		},
		messages: {
			username: {
				pattern: "非法字符",
				remote: "用户名被禁用或已存在"
			},
			password: {
				pattern: "非法字符"
			}
			,email: {
				remote: "已存在"
			}
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; <#if member??>编辑<#else>添加</#if>会员
	</div>
	<form id="inputForm" <#if member??>action="${base}/admin/member/save!${member.id}.do"<#else>action="${base}/admin/member/add.do"</#if> method="post">
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
					<input type="text" name="username" class="text" maxlength="20" <#if member??>value="${member.username}"</#if> />
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
					<input type="text" name="email" class="text" maxlength="200" <#if member??>value="${member.email}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>积分:
				</th>
				<td>
					<input type="text" name="point" class="text" value="<#if member??>${member.point!0}<#else>0</#if>" maxlength="9" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>余额:
				</th>
				<td>
					<input type="text" name="balance" class="text" value="<#if member??>${member.balance!0}<#else>0</#if>" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					会员等级:
				</th>
				<td>
					<select name="memberRankId">
					<#list ranks as rank>
						<option value="${rank.id}" <#if member?? && member.memberRank.id==rank.id>selected="selected"<#elseif rank.isDefault>selected="selected"</#if> >${rank.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" <#if member?? && !member.enabled><#else>checked="checked"</#if> />是否启用
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
				</td>
			</tr>
		</table>
			<table class="input tabContent">
					<tr>
						<th>
							姓名:
						</th>
						<td>
							<input type="text" name="name" class="text" maxlength="200" <#if member??>value="${member.name!''}"</#if> />
						</td>
					</tr>
					<tr>
						<th>
							性别:
						</th>
						<td>
							<span class="fieldSet">
								<label>
									<input type="radio" name="gender" value="0" <#if member?? && member.gender==0><#else>checked="checked"</#if> />男
								</label>
								<label>
									<input type="radio" name="gender" value="1" <#if member?? && member.gender==0>checked="checked"</#if> />女
								</label>
							</span>
						</td>
					</tr>
					<tr>
						<th>
							出生日期:
						</th>
						<td>
							<input type="text" name="birth" class="text Wdate" onfocus="WdatePicker();" <#if member?? && member.birth??>value="${member.birth?date}"</#if> />
						</td>
					</tr>
					<tr>
						<th>
							地区:
						</th>
						<td>
							<span class="fieldSet">
								<input type="hidden" id="areaId" name="areaId" <#if member?? && member.area??> value="${member.area.id}" treePath="${member.area.treePath!''}"</#if> />
							</span>
						</td>
					</tr>
					<tr>
						<th>
							地址:
						</th>
						<td>
							<input type="text" name="address" class="text" maxlength="200" <#if member??>value="${member.address!''}"</#if> />
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