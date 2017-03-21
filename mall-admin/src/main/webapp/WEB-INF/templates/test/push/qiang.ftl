<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${config.siteName}</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/shop/css/shopmain.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/shop/css/index.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/shop/css/flexslider.css"/>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.queue.js"></script>
<script type="text/javascript">
$(function(){
	$.queue({
		url: '${base}/promotion/queue.do',
		data: {pid: 29},
		onMessage: function(message){
			$('#count').html(message.count);
		}
	});	
});
</script>
</head>

<body>
	<h2>推送测试页面</h2>
	<div>
		当前连接数:<span id="count"></span>
	</div>
</body>
</html>