<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客户寄货处理通知-${config.siteName}</title>
<style type="text/css">
body{
	padding:0px;
	margin:0px;
	font:12px "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
	background:#FDFDFD;
}
.clear
{
	height: 1px;
	clear: both;
	overflow: hidden;
}
a
{
	color:#1D79B6;
	text-decoration:none;
}
a:hover
{
	color:#186496;
}
div.line
{
	width:100%;
	height:1px;
	border-top: 1px solid #DDD;
	overflow: hidden;
}
.leftFloat
{
	float:left;
}
img
{
	border:none;
}

div.header
{
	height:105px;
	background:url(${config.siteUrl}/resources/mail/images/mailHeader.jpg) no-repeat;
}
div.header .logo
{
	width:931px;
	margin:0px auto;
	padding:35px;
}
div.header .logo img
{
	width:247px;
	height:34px;
}
div.content
{
	width:900px;
	margin:30px auto;
	text-align:justify;
	text-justify:inter-ideograph;
}
div.content h4
{
	font-size:14px;
}
div.content p
{
	font-size:12px;
	line-height:20px;
}


/* 结尾 */
div.end
{
	text-align:center;
	margin:10px auto;
}
div.end img
{
	width:247px;
	height:34px;
}
</style>
</head>
<body>
	<div class="main">
    	<div class="header">
        	<div class="logo"><a href="${config.siteUrl}"><img src="${config.siteUrl}${config.logo}"/></a></div>
        </div>
        <div class="content">
	        <h4>尊敬的管理员您好：</h4>
            <p>${config.siteName}（<a href="${config.siteUrl}/admin/" target="_blank" >${config.siteUrl}</a>有新的寄货已产生, 请登录${config.siteName}后台管理系统:</p>
            <p><a href="${config.siteUrl}/admin" target="_blank">${config.siteUrl}/admin/login.do</a></p>
            
            <#--订单详情-->
            <p>
            	申请单编号:${returns.apply.sn}<br />
            	申请时间:${returns.apply.createDate?datetime}<br />
            	运单号:${returns.trackingNo}<br />
            	物流公司:${returns.deliveryCorp.name}<br />
            	寄出时间:${returns.createDate?datetime}<br />
            </p>

            <#--结尾logo-->
            <div class="end" style="margin-top:20px;"><a href="${config.siteUrl}"><img src="${config.siteUrl}${config.logo}"/></a></div>
            <div class="end">&copy;${config.certtext}</div>
        </div>
    </div>
</body>
</html>