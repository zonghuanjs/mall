$(function(){
	var $findButton = $('#findProductButton');
	var $productContainer = $('#product_container');
	var queryUrl = site.base + '/admin/promotion/query_product.do';
	var $inputForm = $('#inputForm');
	
	$findButton.bind('click', function(){
		var searchKey = $('#search').val();
		if(searchKey.length > 0){
			$.ajax({
				url: queryUrl,
				type: 'post',
				data: {search: searchKey},
				dataType: 'json',
				success: function(data){
					showProductList(data);
				}
			});
		}
	});
	
	function showProductList(data)
	{
		var $div = $('<div></div>');
		$div.css({
			'padding': '10px',
			'max-height': '200px',
			'overflow': 'scroll'
		});
		
		var liCss = {
			'margin': '5px 0px',
			'cursor': 'pointer'	
		};
		
		var $ul = $('<ul></ul>').appendTo($div);
		for(var i = 0; i < data.length; i++){
			var $li = $('<li></li>').appendTo($ul);
			$li.html(data[i].name);
			$li.attr('val', data[i].id);
			$li.css(liCss);
		}
		
		var $dialog = $.dialog({
			title: '商品搜索结果',
			content: $div,
			ok: null,
			cancel: null
		});
		
		$ul.find('li').each(function(){
			var $this = $(this);
			$this.click(function(){
				var $label = $('<label></label>');
				var $checkbox = $('<input type="checkbox" name="productIds" checked="checked" />').appendTo($label);
				$checkbox.attr('value', $this.attr('val'));
				$label.append($this.html());
				$label.appendTo($productContainer);
				$dialog.next(".dialogOverlay").andSelf().remove();
				$('#search').val('');
			});
			
		});
	}
});
