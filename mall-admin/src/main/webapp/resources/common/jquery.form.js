
(function($){
	$.formsubmit = function(selector, options){
		var settings = {
			action: null,
			method: 'post',
			dataType: 'json',
			callback: null
		};
		
		$.extend(settings, options);
		
		var $form = $(selector);
		settings.action = $form.attr('action');
		settings.method = $form.attr('method');
		
		if(settings.action == null){
			return;
		}
		
		var data = {};
		var $texts = $form.find(':input').filter('[type=text]');
		$texts.each(function(){
			var $this = $(this);
			var key = $this.attr('name');
			var value = $this.val();
			data[key] = value;
		});
		
		var $hiddens = $form.find(':input').filter('[type=hidden]');
		$hiddens.each(function(){
			var $this = $(this);
			var key = $this.attr('name');
			data[key] = $this.val();
		});
		
		var $radios = $form.find(':radio').filter(':checked');
		$radios.each(function(){
			var $this = $(this);
			var key = $this.attr('name');
			data[key] = $this.val();
		});
		
		var $textAreas = $form.find('textarea');
		$textAreas.each(function(){
			var $this = $(this);
			var key = $this.attr('name');
			data[key] = $this.val();
		});
		
		$.ajax({
			url: settings.action,
			type: settings.method,
			data: data,
			dataType: settings.dataType,
			success: function(data){
				if(settings.callback != null && typeof settings.callback == 'function'){
					settings.callback(data);
				}
			}
		});
		
		return $form;
	};
})(jQuery);