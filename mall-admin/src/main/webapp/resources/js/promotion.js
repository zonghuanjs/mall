
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
	
	$inputForm.validate({
		rules: {
			name: 'required',
			title: 'required',
			beginDate: 'required',
			endDate: 'required',
			minNum: {
				required: true,
				digits: true
			},
			maxNum: {
				required: true,
				digits: true
			},
			startPrice: 'number',
			endPrice: 'number',
			priceOperator: {
				required: true,
				maxlength: 1
			},
			pointOperator: {
				required: true,
				maxlength: 1
			},
			priceValue: {
				required: true,
				number: true
			},
			pointValue: {
				required: true,
				digits: true
			}
		},
		messages: {
			name: '必填',
			title: '必填',
			beginDate: '必填',
			endDate: '必填',
			minNum: '*',
			maxNum: '*',
			startPrice: '格式不对',
			endPrice: '格式不对',
			priceOperator: '选择1项',
			priceValue: '*',
			pointOperator: '选择1项',
			pointValue: {
				required: '必填',
				digits: '格式不对,整数'
			}
		},
		errorPlacement: function(error, element){
			if(element.is(':radio')){
				error.appendTo(element.closest('fieldset'));
			}
			else
				error.appendTo(element.parent());				
		}
	});
});