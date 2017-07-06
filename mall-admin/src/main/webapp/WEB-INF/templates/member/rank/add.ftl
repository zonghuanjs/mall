<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加会员等级</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $amount = $("#amount");
	var $isSpecial = $("#isSpecial");
	
	$("input.browserButton").browseDialog();
	
	// 特殊会员等级修改
	$isSpecial.click(function() {
		if ($(this).prop("checked")) {
			$amount.val("").prop("disabled", true);
		} else {
			$amount.prop("disabled", false);
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: {
				required: true,
				remote: {
					url: "check_name.do<#if rank??>?prevName=${rank.name?url}</#if>",
					cache: false
				}
			},
			scale: {
				required: true,
				min: 0,
				decimal: {
					integer: 3,
					fraction: 3
				}
			},
			amount: {
				required: true,
				min: 0,
				decimal: {
					integer: 12,
					fraction: 2
				},
				remote: {
					url: "check_amount.do<#if rank??>?prevAmount=${rank.amount?url}</#if>",
					cache: false
				}
			},
			level: {
				required: true,
				min: 1,
				number:true,
				remote: {
					url: "check_level.do<#if rank??>?prevLevel=${rank.level?url}</#if>",
					cache: false
				}
			}
		},
		messages: {
			name: {
				remote: "已存在"
			},
			amount: {
				remote: "已存在"
			},
			level: {
				required: "必填",
				min:"至少为1",
				number:"只能是数字",
				remote:"已存在"
			}
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 添加会员等级
	</div>
	<form id="inputForm" <#if rank??> action="save!${rank.id}.do" <#else>action="add.do"</#if> method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="100" <#if rank??>value="${rank.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>优惠比例:
				</th>
				<td>
					<input type="text" name="scale" class="text" <#if rank??>value="${rank.scale!1}"<#else>value="1"</#if> maxlength="7" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>消费金额:
				</th>
				<td>
					<input type="text" id="amount" name="amount" class="text" maxlength="16" <#if rank??>value="${rank.amount}"</#if> />
				</td>
			</tr>
			<#--<tr>
				<th>
					<span class="requiredField">*</span>徽章图片:
				</th>
				<td>
					<input type="text" name="img" class="text" maxlength="200" <#if rank??>value="${rank.img}"</#if> />
					<input type="button" class="button browserButton" value="选择文件" />
					<#if rank?? && rank.img??><a href="${base}${rank.img}" target="_blank">查看</a></#if>
				</td>
			</tr>-->
			<tr>
				<th>
					<span class="requiredField">*</span>等级:
				</th>
				<td>
					<input type="text" id="level" name="level" class="text" maxlength="4" <#if rank??>value="${rank.level}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isDefault" value="true" <#if rank?? && rank.isDefault> checked="checked" </#if> />是否默认
						<input type="hidden" name="_isDefault" value="false" />
					</label>
					<label title="特殊会员等级不随消费金额变化">
						<input type="checkbox" id="isSpecial" name="isSpecial" value="true" <#if rank?? && rank.special> checked="checked" </#if> />是否特殊
						<input type="hidden" name="_isSpecial" value="false" />
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