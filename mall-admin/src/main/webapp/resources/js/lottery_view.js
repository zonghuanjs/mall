
$(function(){
	var $prizeButton = $('#prizingButton');
	var $endPrizingButton = $('#endPrizingButton');
	var lottery = $prizeButton.find(':hidden[name=lottery]').val();
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	$prizeButton.click(function(){
		var $div = $('<div>订单编号:</div>');
		var $order = $('<input type="text" class="text" name="order" />').appendTo($div);
		$div.css({
			'padding': '15px'
		});
		
		$.dialog({
			title: '抽奖',
			content: $div,
			onOk: function(dialog){
				if($order.val().length > 0){
					$.ajax({
						url: 'prizing.do',
						type: 'post',
						data: {
							lottery: lottery,
							sn: $order.val()
						},
						dataType: 'json',
						success: function(data){
							if(data.errCode==0){
								alert('成功!');
								location.reload(true);
							}
						}
					});
				}else{
					alert('订单编号不能为空!');
				}
			}
		});
	});
	
	$endPrizingButton.click(function(){
		var $div = $('<div>确定要结束抽奖活动吗？</div>');
		$div.css({'padding': '15px'});
		$.dialog({
			title: '结束抽奖活动',
			content: $div,
			onOk: function(dialog){
				$.ajax({
					url: 'end_prizing.do',
					type: 'post',
					data: {id: lottery},
					dataType: 'json',
					success: function(data){
						if(data.errCode == 0){
							alert('抽奖活动已开奖完毕!');
						}
					}
				});
			}
		});
	});
});