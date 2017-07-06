$(function() {

	var $loginForm = $('#loginForm');
	var $validateCodeImg = $('#validateCodeImg');
	var $loginFrame = $('#loginFrame');
	var $errMsg = $loginForm.find('#errMsg').css({
		color : 'Red'
	});
	var $code = $loginForm.find(':input').filter('[name=validateCode]');

	$loginForm.validate({
		errorPlacement : function(error, element) {
			if (element.attr('name') == 'validateCode') {
				error.appendTo(element.parent().next());
			} else {
				error.appendTo(element.parent());
			}
		},
		rules : {
			username : {
				required : true,
				minlength : 3,
				maxlength : 20
			},
			password : {
				required : true,
				minlength : 6,
				maxlength : 20
			},
			validateCode : {
				required : true,
				minlength : 4,
				maxlength : 4
			}
		},
		messages : {
			username : '*',
			password : '*',
			validateCode : '*'
		}
	});

	$validateCodeImg.bind('click', function() {
		var t = new Date();
		$('#validateCodeImg').attr('src',
				'validateCode/code.do?' + t.getTime());
	});

	// 登录结果
	$loginFrame
			.load(function() {
				var text;
				var io = document.getElementById('loginFrame');
				if (io.contentWindow) {
					text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML
							: null;
				} else if (io.contentDocument) {
					text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML
							: null;
				}
				if ($.trim(text) != "") {
					var data;
					try {
						data = $.parseJSON(text);
					} catch (e) {
						var result = $(text).html();
						data = $.parseJSON(result);
					}
					try {
						if (data.errCode == 0) {
							top.location.href = 'index.do';
						} else {
							switch (data.errCode) {
							case 1310:
								$errMsg.html('验证码错误!');
								break;
							case 1320:
								$errMsg.html('用户名错误!');
								break;
							case 1330:
								$errMsg.html('密码错误!');
								break;
							case 1340:
								$errMsg.html('账号已锁定!');
								break;
							case 1350:
								$errMsg.html('账号未启用!');
								break;
							default:
								$errMsg.html('未知错误!');
							}
							$validateCodeImg.click();
							$code.val('');
						}
					} catch (e) {

					}
				}
			});
});