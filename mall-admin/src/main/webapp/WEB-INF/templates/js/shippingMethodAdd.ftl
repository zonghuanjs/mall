$(function(){
	
	var $inputForm = $('#inputForm');
	
	$inputForm.validate({
		rules: {
			name: {
				required: true
			},
			firstWeight: {
				required: true,
				number: true
			},
			continueWeight: {
				required: true,
				number: true
			},
			firstPrice: {
				required: true,
				number: true
			},
			continuePrice: {
				required: true,
				number: true
			}
		},
		
		messages: {
			name: "必填",
			firstWeight: "必填",
			continueWeight: "必填",
			firstPrice: "必填",
			continuePrice: "必填"
		}
		
	});
	
});