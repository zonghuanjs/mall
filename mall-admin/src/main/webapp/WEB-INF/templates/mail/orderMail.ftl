<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单邮件</title>
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
	background:url(../images/mailHeader.jpg) no-repeat;
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
        	<div class="logo"><a href="${base}"><img src="${base}${config.logo}"/></a></div>
        </div>
        <div class="content">
	        <h4>尊敬的张茗泰您好：</h4>
            <p>感谢您在特科芯商城（<a href="${base}">mall.tekism.cn</a>）购物<br/>
        	您的订单【12232】已确认收到，您选择的是在线支付配送方式，我们已收到款项，订单信息已<a href="${base}/account/order/list.do">“我的订单”</a>页面显示为准，您也可以随时进入页面对订单进行修改等操作。</p>
            
            <#--订单详情-->
            <h4>订单详情</h4>
            <div class="table">
            	<div class="tableTitle">
                	<div class="orderNo leftFloat" style="text-decoration:underline; color:#1D79B6; padding-left:13px;">订单编号：sadff</div>
                    <div class="consignee leftFloat">收货人</div>
                    <div class="amount leftFloat">订单金额</div>
                    <div class="orderTime leftFloat" style="border-right:none;">下单时间</div>
                    <div class="clear"></div>
                </div>
                <div class="orderList">
                	<div class="orderItem">
                    	<div class="orderNo leftFloat" id="orderNo">
                        	<div class="productItem">
                            	<div class="productImg leftFloat"><img src=""/></div>
                                <div class="productName leftFloat">nisinigsig</div>
                                <div class="clear"></div>
                            </div>
                            <div class="line"></div>
                            <div class="productItem">
                            	<div class="productImg leftFloat"><img src=""/></div>
                                <div class="productName leftFloat">nisinigsig</div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="consignee leftFloat height" id="nameHeight">李俊</div>
                        <div class="amount leftFloat height" id="priceHeight">231</div>
                        <div class="orderTime leftFloat height" id="timeHeight" style="border-right:none;">2014-12-3 23:00:00</div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
            <h4>重要说明</h4>
            <p>政策利好的环境下，不少开发商意欲抓紧年前的最佳购房时机，据统计，苏州12月约有25盘将进行最后的冲刺，住宅纯新盘占比约三成，共有八..政策利好的环境下，不少开发商意欲抓紧年前的最佳购房时机，据统计，苏州12月约有25盘将进行最后的冲刺，住宅纯新盘占比约三成，共有八...政策利好的环境下，不少开发商意欲抓紧年前的最佳购房时机，据统计，苏州12月约有25盘将进行最后的冲刺，抓紧年前的最佳购房时机，据统计，苏州12月约有25盘将进行最后的冲刺
            </p>
            
            <#--结尾说明-->
            <p>您之所以受到这封邮件，是因为您曾经注册成为特科芯的用户。<br/>
            本邮件由特科芯系统自动发出，请勿直接回复！<br/>
            在购物中遇到任何问题，请点击<a href="#">帮助中心</a>。<br/>
            如果您有任何疑问或建议，请<a href="#">联系我们</a>。<br/>
            特科芯是综合性商城。什么等等。。。</p>
            <#--结尾logo-->
            <div class="end" style="margin-top:20px;"><a href="${base}"><img src="${base}${config.logo}"/></a></div>
            <div class="end">&copy;苏州普福斯信息科技有限公司 版权所有 苏ICP备14027943号-1</div>
        </div>
    </div>
</body>
</html>