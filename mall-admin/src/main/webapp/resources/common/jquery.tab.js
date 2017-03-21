/**
 * jquery.tab.js
 * Chen Mingcai
 * 2014-10-06
 * 
 * @param $
 */

(function($){
	
	$.tab = function(options){
		
		var settings = {
			tabs: null,
			tabContents: null,
			tabCurrent: 'current'
		};
		
		$.extend(settings, options);
		
		if(settings.tabs == null || settings.tabContents == null){
			return false;
		}
		
		var $tabs = $(settings.tabs);
		var $contents = $(settings.tabContents);
		
		$.each($tabs, function(idx, self){
			var $self = $(self);
			$self.bind('click', function(){
				$contents.hide().eq(idx).show();
				$tabs.removeClass(settings.tabCurrent).eq(idx).addClass(settings.tabCurrent);
			});
		});
		
		//initialize
		$tabs.eq(0).addClass(settings.tabCurrent);
		$contents.hide().eq(0).show();
	};
	
})(jQuery);