
$(function(){
	var $inputForm = $('#inputForm');
	
	$inputForm.validate({
		rules: {
			name: "required",
			url: "url",
			orders: "digits"
		},
		messages: {
			name: "必填",
			url: "必须是网址",
			orders: "必须是数字"
		}
	});
});