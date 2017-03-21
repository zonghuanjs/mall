<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>赠送优惠券</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $('#inputForm');
	$inputForm.validate({
		rules: {
			memberId: {
				required: true,
				digits:true,
			},
			couponId: {
				required: true,
				digits:true,
			},
			
		},
		
		messages: {
			memberId: {
				required: "必填",	
				digits:"必须为整数",			
			},						
			couponId: {
			    required: "必填",
				digits:"必须为整数",
			},
			
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 赠送优惠券
	</div>
	<form id="inputForm" action="coupon_give.do" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>会员ID:
				</th>
				<td>
					<input type="text" name="memberId" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>优惠券ID:
				</th>
				<td>
					<input type="text" name="couponId" class="text" maxlength="200" />
				</td>
			</tr>
				
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定"  />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>