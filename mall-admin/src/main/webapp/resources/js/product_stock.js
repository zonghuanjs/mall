$(function(){
	var $inputForm = $("#inputForm"),
		$erpStock = $('#erpStock'),
		$stock = $('#stock'),
		$radios = $inputForm.find(':radio'),
		$stockInput = $inputForm.find(':input').filter('[name=stock]');
	
    
	// 表单验证
	$inputForm.validate({
		rules: {
			stockOperation: 'required',
			stock: {
				required: true,
				digits: true
			}
		},
		messages: {
			stockOperation: '必选',
			stock: {
				required: '必填',
				digits: '格式不对'
			}
		}
	});
	
	$.ajax({
		url: 'erp_stock.do',
		data: {id: $stock.attr('pid')},
		type: 'post',
		dataType: 'json',
		beforeSend: function(){
			$erpStock.html('正在查询...');
		},
		success: function(data){
			$erpStock.html(data);
			$erpStock.attr('stock', data);
			var stock = parseInt($stock.attr('stock'));
			if(data < stock){
				$radios.filter('[value=substract]').prop('checked', 'checked');
			}else{
				$radios.filter('[value=add]').prop('checked', 'checked');
			}
			
			$stockInput.val(Math.abs(data - stock));
		}
	});
});