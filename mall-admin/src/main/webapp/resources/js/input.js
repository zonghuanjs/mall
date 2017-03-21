/**
 * input.js
 * 
 */

$(function(){
	
	var $browserButton = $('.browserButton');
	var $backButton = $('#backButton');
	
	$browserButton.each(function(){
		var $button = $(this);
		$button.browseDialog({
			
			callback: function(url){
				var $link = $button.next('a');
				$link.attr('href', url);
			}
		});
	});
	
	$backButton.click(function(){
		location.href=document.referrer;
		return false;
	});
	
});