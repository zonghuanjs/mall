
$(function(){
	
	var $inputForm = $('#inputForm');
	var $mailTestButton = $('#mailTest');
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	$inputForm.validate({
		rules: {
			siteName: {
				required: true
			},
			siteUrl: {
				required: true,
				url: true
			},
			logo: 'required',
			email: {
				email: true
			},
			siteCloseMessage: 'required',
			largeProductImageWidth: {
				required: true,
				digits: true
			},
			largeProductImageHeight: {
				required: true,
				digits: true
			},
			mediumProductImageWidth: {
				required: true,
				digits: true
			},
			mediumProductImageHeight: {
				required: true,
				digits: true
			},
			thumbnailProductImageWidth: {
				required: true,
				digits: true
			},
			thumbnailProductImageHeight: {
				required: true,
				digits: true
			},
			defaultLargeProductImage: 'required',
			defaultMediumProductImage: 'required',
			defaultThumbnailProductImage: 'required',
			homeCategorySetting: {
				remote: 'category_pos_check.do'
			}
		},
		messages:{
			homeCategorySetting: {
				remote: '配置不正确'
			}		
		}
	});
	
	//邮件测试
	$mailTestButton.bind('click', function(){
		var $smtpHost = $('#smtpHost');
		var $smtpPort = $('#smtpPort');
		var $smtpUsername = $('#smtpUsername');
		var $smtpPassword = $('#smtpPassword');
		var $checkStatus = $('#mailTestStatus');
		
		if($.isEmptyObject($smtpHost) || $.isEmptyObject($smtpPort) || $.isEmptyObject($smtpUsername) || $.isEmptyObject($smtpPassword))
			return false;
		$.ajax({
			url: '${base}/common/checkMail.do',
			type: 'post',
			data: {host: $smtpHost.val(), port: $smtpPort.val(), username: $smtpUsername.val(), password: $smtpPassword.val()},
			dataType: 'json',
			beforeSend: function(){
				$checkStatus.text('正在测试...');
			},
			success: function(data){
				if(data){
					$checkStatus.text('测试成功!');
				}else{
					$checkStatus.text('测试失败!');
				}
			}
		});
	});
	
});