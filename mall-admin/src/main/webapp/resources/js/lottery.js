$(function(){
	var $findButton = $('#searchButton');
	var $productContainer = $('#product_container');
	var queryUrl = site.base + '/admin/promotion/query_product.do';
	var $inputForm = $('#inputForm');
	
	$inputForm.validate({
		rules: {
			name: 'required',
			beginDate: 'required',
			endDate: 'required',
			number: {
				required: true,
				digits: true
			},
			price: {
				required: true,
				number: true
			}
		},
		messages: {
			name: '必填',
			beginDate: '必填',
			endDate: '必填',
			number: {
				required: '必填',
				digits: '请输入整数'
			},
			price: {
				required: '必填',
				number: '请输入数字'
			}
		}
	});
	
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
				
				$productContainer.find('#productName').html($this.html());
				$productContainer.find(':hidden').filter('[name=product]').val($this.attr('val'));
				$dialog.next(".dialogOverlay").andSelf().remove();
				$('#search').val('');
			});
			
		});
	}
});