<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/home.css"/>
</head>

<body>
<div class="main">
	<div class="row color" style="padding-left:6px;">管理中心首页</div>
    <div class="row">
    	<div class="item1 color">系统名称：</div>
        <div class="item2">${config.siteName}</div>
        <div class="item3 color">系统版本：</div>
        <div class="item4">${config.version}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
    	<div class="item1 color">官方中文网站：</div>
        <div class="item2"><a href="http://www.tekism.cn" target="_blank">http://www.tekism.cn</a></div>
        <div class="item3 color">题库管理系统：</div>
        <div class="item4"><a href="http://mall.tekism.cn/question/wewe/" target="_blank">http://mall.tekism.cn/question/wewe/</a></div>
        <div class="clear"></div>
    </div>
    <div class="row">
    	<div class="item1 color">众筹管理系统：</div>
        <div class="item2"><a href="http://fund-admin.tekism.cn" target="_blank">http://fund-admin.tekism.cn</a></div>
        <div class="item3 color">收银台管理系统：</div>
        <div class="item4"><a href="http://cashier-admin.tekism.cn" target="_blank">http://cashier-admin.tekism.cn/</a></div>
        <div class="clear"></div>
    </div>
    <div class="row"></div>
    <div class="row">
    	<div class="item1 color">服务器名称：</div>
        <div class="item2">${serverName}</div>
        <div class="item3 color">服务器端口：</div>
        <div class="item4">${serverPort}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
    	<div class="item1 color">服务器版本：</div>
        <div class="item2">${serverVersion}</div>
        <div class="item3 color">Servlet版本：</div>
        <div class="item4">${majorVersion}.${minorVersion}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
    	<div class="item1 color">系统分配内存：</div>
        <div class="item2">${totalMemory?c}MB</div>
        <div class="item3 color">可用处理器个数：</div>
        <div class="item4">${availableProcessors?c}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
    	<div class="item1 color">最大可用内存：</div>
        <div class="item2">${maxMemory?c}MB</div>
        <div class="item3 color">当前可用内存：</div>
        <div class="item4">${freeMemory?c}MB</div>
        <div class="clear"></div>
    </div>
    <div class="row"></div>
    <div class="row">
    	<div class="item1 color">在线人数：</div>
        <div class="item2">${onlineCount?c}</div>
        <div class="item3 color">访问记数：</div>
        <div class="item4">${visitTotal?c}</div>
        <div class="clear"></div>
    </div>
    <div class="row"></div>
    <div class="row">
    	<div class="item1 color">上架商品数：</div>
        <div class="item2">${marketProductCount}</div>
        <div class="item3 color">下架商品数：</div>
        <div class="item4">${unMarketProductCount}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
        <div class="item1 color">商品库存报警数：</div>
        <div class="item2">${alertProductCount?c}</div>
        <div class="item3 color">缺货商品数：</div>
        <div class="item4">${lackProductCount?c}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
        <div class="item1 color">等待付款订单数：</div>
        <div class="item2">${waitForPayCount}</div>
        <div class="item3 color">等待发货订单数：</div>
        <div class="item4">${waitForShipping}</div>
        <div class="clear"></div>
    </div>
    <div class="row">
        <div class="item1 color">会员总数：</div>
        <div class="item2">${memberCount}</div>
        <div class="item3 color">管理员总数：</div>
        <div class="item4">${adminCount}</div>
        <div class="clear"></div>
    </div>
    <div class="row flat">&copy;2013-2014 苏州普福斯信息科技有限公司 版权所有 苏ICP备14027943号-1</div>
</div>
</body>
</html>