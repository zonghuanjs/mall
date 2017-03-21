
(function($){
	
	$.pusher = function(options){
		
		var settings = {
			url: null,
			data: null,
			onMessage: null,
			fixedRate: 5000,
			method: 'post',
			dataType: 'json'
		};
		
		$.extend(settings, options);
		
		if(settings.url == null){
			return;
		}
		
		var timer = setInterval(function(){
			var data = {};
			$.extend(data, settings.data);
			
			$.ajax({
				url: settings.url,
				data: data,
				type: settings.method,
				dataType: settings.dataType,
				success: function(message){
					if(settings.onMessage != null && typeof settings.onMessage == 'function'){
						settings.onMessage(message);
					}
					try{
						if(message.errCode == 0){
							clearInterval(timer);
						}
					}catch(e){};
				}
			});
		}, settings.fixedRate);
	};
	
})(jQuery);