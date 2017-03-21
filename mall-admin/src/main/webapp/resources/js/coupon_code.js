
function showObtainLinks(id){
	var baseUrl = site.base + '/coupon/obtain.do?cid=' + id;
	var $div = $('<div></div>');
	$div.append(baseUrl);
	$div.css({
		'padding': '10px 15px'
	});
	
	$.dialog({
		width: 400,
		title: '优惠券领取链接',
		content: $div,
		ok: '确定',
		cancel: null
	});
}