<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="${base}/resources/shop/css/shopmain.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/error/css/maintain.css"/>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/index.js"></script>
<title>网站维护中</title>
</head>
<body>
	<div class="main">
		<#include "../shop/header/header.ftl">
	
		<div class="content">
        	<div class="tag leftFloat"><img src="${base}/resources/error/images/maintain.png"/></div>
            <div class="text leftFloat">
            	${config.siteClosedMessage}
            </div>
            <div class="clear"></div>
        </div>
	
		<#include "../shop/footer/footer.ftl">
	</div>
</body>
</html>