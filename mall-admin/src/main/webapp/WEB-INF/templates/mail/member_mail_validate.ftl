<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>邮箱验证-${config.siteName}</title>
<script type="text/javascript">
window.onload = function(){
	var $nameHeight = document.getElementById("nameHeight");
	var $priceHeight = document.getElementById("priceHeight");
	var $timeHeight = document.getElementById("timeHeight");
	var $order = document.getElementById("orderNo");
	$nameHeight.style.height = $order.offsetHeight - 20 + "px";
	$priceHeight.style.height = $order.offsetHeight - 20 + "px";
	$timeHeight.style.height = $order.offsetHeight - 20 + "px";
}
</script>
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
        	<div class="logo"><a href="${config.siteUrl}" target="_blank"><img src="${config.siteUrl}${config.logo}"/></a></div>
        </div>
        <div class="content">
	        <h4>尊敬的${member.username}您好：</h4>
            <p>您正在进行邮箱验证操作, 请点击下面的验证链接进行验证:</p>
            <p><a href="${config.siteUrl}/account/mail_validate.do?uid=${member.id}&key=${member.mailValidateToken}" target="_blank">${config.siteUrl}/account/mail_validate.do?uid=${member.id}&key=${member.mailValidateToken}</a></p>
            <p>如果点击上面的连接无效,请将其手动复制到浏览器地址栏打开. 该链接包含您的个人信息, 请不要泄露给他人.</p>
            <p>如果您从未进行过此类操作, 请忽略此邮件, 并注意您的密码安全.<br/>
            <#--结尾logo-->
            <div class="end" style="margin-top:20px;"><a href="${config.siteUrl}"><img src="${config.siteUrl}${config.logo}"/></a></div>
            <div class="end">&copy;${config.certtext}</div>
        </div>
    </div>
</body>
</html>