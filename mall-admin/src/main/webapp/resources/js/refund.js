
$(function() {

	var $refundsButton = $('#refundsButton');
	var $confirmButton = $('#confirmButton');
	var refund = $('#refundId').val();

	$refundsButton.click(function(){
		var $this = $(this);
		$.dialog({
			type: "warn",
			content: "确定要执行退款操作吗，是否继续?",
			onOk: function() {
				window.open($this.attr('data_url'));
			}
		});
	});
	
	$confirmButton.click(function(){
	$.dialog({
			type: "warn",
			content: "确定要执行同意退款操作吗?",
			onOk: function() {
				$.post(
						"confirm.do",
						{"id":$('#refundId').val()},
						function(data){
							if(data=="success" ){
								location.reload(true);
							}
						 }
				   );
			}
		});
	});
	
	if($refundsButton.length > 0){
		$.pusher({
			url: 'query_fund_status.do',
			data: {id: refund},
			onMessage: function(msg){
				if(msg.errCode == 0){
					location.reload(true);
				}
			}
		});
	}	
});