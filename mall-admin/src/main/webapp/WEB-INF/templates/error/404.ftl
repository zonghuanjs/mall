<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${base}/resources/error/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/error/css/404.css"/>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.min.js"></script>
<title>出错了</title>
</head>
<body>
	<div class="main">
		<div class="header">
        	<div class="logo"><a href="${base}/"><img src="${base}${config.logo}"/></a></div>
        </div>
		
		<div class="content">
        	<div class="image leftFloat">
            	<img src="${base}/resources/error/images/404.png"/>
            </div>
            <div class="errorTip leftFloat">
            	<div class="text1">出错了！</div>
                <div class="text2">您要找的页面不见了！</div>
                <div class="button">
                	<a href="javascript:history.go(-1);">返回</a>
                	<a href="${base}/">回到首页</a>
                	<div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
		
		<div class="footer">&copy;${config.certtext}</div>
	</div>
</body>
</html>