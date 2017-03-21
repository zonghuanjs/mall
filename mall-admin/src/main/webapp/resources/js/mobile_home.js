$(function(){
	
	var $category = $('#category');
	var $container = $('#category_c');
	var $select = $('#categorylist');
	var $catetoryAdd = $('#categoryAddButton');
	
	var categoryManager = {
		ids: [],
		container: null,
		select: null,
		init: function(data, c, s){
			if(data){
				categoryManager.ids = data.split(',');
			}
			
			categoryManager.select = $(s);
			categoryManager.container = $(c);
			categoryManager.show();
		},
		add: function(id){			
			if(!categoryManager.ids || categoryManager.ids.length < 8){
				var need = true;
				
				for(var i = 0; i < categoryManager.ids.length; i++){
					if(id == categoryManager.ids[i]){
						need = false;
						break;
					}
				}
				
				if(need){
					var idx = categoryManager.ids.length;
					categoryManager.ids[idx] = id;
					categoryManager.addItemShow(id);
					$category.val(categoryManager.ids.join(','));
				}				
			}
		},
		del: function(id){
			for(var i = 0; i < categoryManager.ids.length; i++){
				if(id == categoryManager.ids[i]){
					categoryManager.ids.splice(i, 1);
					categoryManager.container.find(':checkbox').filter('[value=' + id + ']').closest('label').remove();
					$category.val(categoryManager.ids.join(','));
					break;
				}
			}
		},
		show: function(){
			if(categoryManager.ids && categoryManager.ids.length > 0){
				categoryManager.container.html('');
				for(var i = 0; i < categoryManager.ids.length; i++){
					categoryManager.addItemShow(categoryManager.ids[i]);
				}
			}
		},
		addItemShow: function(id){
			var $label = $('<label></label>');
			var $cbx = $('<input type="checkbox" checked="checked" />').appendTo($label);
			$cbx.val(id);
			
			var $option = categoryManager.select.find('option').filter('[value=' + id + ']');
			$label.append($option.html());
			
			$label.appendTo(categoryManager.container);
			
			$cbx.change(function(){
				if(!$cbx.is(':checked')){
					categoryManager.del($cbx.val());
				}
			});
		}
	};
	
	categoryManager.init($category.val(), $container, $select);
	
	$catetoryAdd.click(function(){
		if(!categoryManager.ids || categoryManager.ids.length < 8){
			var id = $select.val();
			categoryManager.add(id);
		}
	});
});