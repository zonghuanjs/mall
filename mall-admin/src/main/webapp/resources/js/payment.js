
$(function(){
	var $shippingButton = $('#apiShipping');
	var paymentId = $('#paymentId').val();
	
	$shippingButton.click(function(){
		$.ajax({
			url: 'api_shipping.do',
			type: 'post',
			data: {id: paymentId},
			dataType: 'json',
			success: function(data){
				if(data.errCode == 0){
					alert('接口发货成功!');
					location.reload(true);
				}else{
					alert('发货出错: ' + data.errCode);
				}
			}
		});
	});
});