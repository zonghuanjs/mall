<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退款处理通知-${config.siteName}</title>
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

/* 订单信息 */
div.content .table
{
	color:#888;
	margin:0px auto;
	text-align:center;
}
div.table .tableTitle
{
	height:42px;
	line-height:42px;
	background:#ECECEC;
	border:1px solid #DDD;
}
div.table .leftFloat
{
	border-right:1px solid #DDD;
}
div.table .orderNo
{
	width:450px;
	text-align:left;
}
div.table .consignee
{
	width:130px;
}
div.table .amount
{
	width:100px;
}
div.table .orderTime
{
	width:170px;
}
div.orderList
{
	margin-bottom:30px;
}
div.orderItem
{
	border:1px solid #DDD;
	border-top:none;
}
div.orderItem .orderNo
{
	width:463px;
}
div.orderItem .productItem
{
	margin:13px;
}
div.orderItem .productImg, div.orderItem .productName
{
	border-right:none;
}
div.orderItem .productImg img
{
	width:65px;
	height:65px;
	border:1px solid #DDD;
}
div.orderItem .productName
{
	width:345px;
	margin:10px auto 0px 10px;
}
div.orderItem .height
{
	padding-top:20px;
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
	        <h4>尊敬的订单管理员您好：</h4>
            <p>${config.siteName}（<a href="${config.siteUrl}/admin/" target="_blank" >${config.siteUrl}</a>有一笔退款需要处理, 退款申请时间:${refund.createDate?datetime}, 请登录${config.siteName}后台管理系统:</p>
            <p><a href="${config.siteUrl}/admin" target="_blank">${config.siteUrl}/admin/login.do</a></p>
            
            <#--退款详情-->
            <h4>退款详情</h4>
            <div class="table">
            	<div class="tableTitle">
                	<div class="orderNo leftFloat" style="text-decoration:underline; color:#1D79B6; padding-left:13px;">退款编号：${refund.sn}</div>
                    <div class="consignee leftFloat">收货人</div>
                    <div class="amount leftFloat">订单金额</div>
                    <div class="orderTime leftFloat" style="border-right:none;">下单时间</div>
                    <div class="clear"></div>
                </div>
                <div class="orderList">
                	<div class="orderItem">
                    	<div class="orderNo leftFloat" id="orderNo">
                    	<#list refund.order.items as item>
                        	<div class="productItem">
                            	<div class="productImg leftFloat"><img src="${config.siteUrl}${item.thumbnail}"/></div>
                                <div class="productName leftFloat">${item.fullName}</div>
                                <div class="clear"></div>
                            </div>
                            <#if item_has_next>
                            <div class="line"></div>
                            </#if>
                        </#list>
                        </div>
                        <div class="consignee leftFloat height" id="nameHeight">${refund.order.consignee}</div>
                        <div class="amount leftFloat height" id="priceHeight">${refund.order.amountPaid?string.currency}</div>
                        <div class="orderTime leftFloat height" id="timeHeight" style="border-right:none;">${refund.order.createDate?datetime}</div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            
            <#--结尾logo-->
            <div class="end" style="margin-top:20px;"><a href="${config.siteUrl}"><img src="${config.siteUrl}${config.logo}"/></a></div>
            <div class="end">&copy;${config.certtext}</div>
        </div>
    </div>
</body>
</html>