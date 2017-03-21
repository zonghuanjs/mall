$(function(){
	var $location = $('#location'),
		messageId = $location.attr('ip');
	
	$.ajax({
		url: 'send_locate.do',
		type: 'post',
		data: {id: messageId},
		dataType: 'json',
		beforeSend: function(){
			$location.html('查询中...');
		},
		success: function(data){
			if('false' == data){
				$location.html('未查询到信息');
			}else{
				var html = [data.area, data.isp].join(',');
				$location.html(html);
			}
		}
	});
	
});