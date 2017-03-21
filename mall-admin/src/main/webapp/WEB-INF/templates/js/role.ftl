
$(function(){

	var $inputForm = $('#inputForm');
	var $authorities = $('.authorities');
	
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		},
		messages: {
			name: "必填",
			authorities: "*"
		}
	});
	
	$authorities.each(function(){
		var $group = $(this);
		var $selectAll = $group.find('.selectAll');
		
		$selectAll.bind('click', function(){
			var $options = $group.find(':checkbox');
			if($options.filter(':checked').size() > 0){
				$options.prop('checked', false);
			}else{
				$options.prop('checked', true);
			}
		});
	});
	
});