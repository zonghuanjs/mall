<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心</title>
<link type="text/css" rel="stylesheet" href="resources/css/list.css"/>
<link type="text/css" rel="stylesheet" href="resources/css/index.css"/>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
</head>
<body>
<table class="main">
		<tr>
			<th class="logo">
				<a href="${base}" target="_blank">
					<img src="resources/images/logo.png" />
				</a>
			</th>
			<th class="navTop">
				<div id="nav" class="nav">
					<ul>
							<li>
								<a href="#product">商品</a>
							</li>
							<li>
								<a href="#order">订单</a>
							</li>
					        <li>
								<a href="#apply">售后</a>
							</li>					    
							<li>
								<a href="#member">会员</a>
							</li>
					   
							<li>
								<a href="#content">内容</a>
							</li>
								
							<li>
								<a href="#marketing">营销</a>
							</li>
						
							<li>
								<a href="#charity">公益</a>
							</li>
								
							<li>
								<a href="#statistics">统计</a>
							</li>
								
							<li>
								<a href="#system">系统</a>
							</li>
							
						    <li>
								<a href="#query">查询</a>
							</li>
						
						<li>
							<a href="../" target="_blank">首页</a>
						</li>
					</ul>
				</div>
				<div class="link">
					<a href="http://www.tekism.cn" target="_blank">官方网站</a>|
					<a href="http://www.tekism.cn/article-54.jhtml" target="_blank">关于我们</a>
				</div>
				<div class="link">
					<strong></strong>
					您好
					<#if admin??>您好, ${admin.username}!
					<#if authorities?seq_contains('admin:admin')>
						<a href="${base}/admin/adminUser/edit!${admin.id}.do" target="iframe">[账号设置]</a>
					</#if>
						<a href="${base}/admin/logout.do" target="_top">[注销]</a>
					</#if>
					
				</div>
				
                <div class="clear"></div>
			</th>
		</tr>
		<tr class="mainPane">
			<td id="menu" class="menu">
			
				<dl id="product" class="default">
					<dt>商品管理</dt>
					
						<dd>
							<a href="${base}/admin/stock/list.do" target="iframe">库存管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/price/list.do" target="iframe">价格管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/product/list.do" target="iframe">商品管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/productCategory/list.do" target="iframe">商品分类</a>
						</dd>  
					
						<dd>
							<a href="${base}/admin/parameter/list.do" target="iframe">商品参数</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/attribute/list.do" target="iframe">商品属性</a>
						</dd>
						
						<dd>
							<a href="${base}/admin/specification/list.do" target="iframe">规格管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/brand/list.do" target="iframe">品牌管理</a>
						</dd>
					
				</dl>
			
				<dl id="order">
					<dt>订单管理</dt>
					
						<dd>
							<a href="${base}/admin/order/list.do?type=1" target="iframe">普通订单管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/order/list.do?type=2" target="iframe">售后订单管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/order/list.do?type=3" target="iframe">抽奖订单管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/order/reserve/list.do?type=5" target="iframe">预定订单管理</a>
						</dd>						
						<dd>
							<a href="${base}/admin/order/list.do?memo=SD" target="iframe">其他订单管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/order/closed/list.do?orderStatus=-1" target="iframe">关闭订单管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/payment/list.do" target="iframe">收款管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/refund/list.do" target="iframe">退款管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/refund/alipay_list.do" target="iframe">
								退款管理<span class="red">(支付宝)</span>
							</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/shipping/list.do" target="iframe">发货管理</a>
						</dd>
						
						<dd>
							<a href="${base}/admin/home.do" target="iframe">发货点管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/addTax/list.do" target="iframe">增值税发票审核</a>
						</dd>
					
				</dl>
			
			    <dl id="apply">
					<dt>售后管理</dt>
					
						<dd>
							<a href="${base}/admin/apply/list.do" target="iframe">售后申请审核</a>
						</dd>
										
						<dd>
							<a href="${base}/admin/return/list.do" target="iframe">退货管理</a>
						</dd>
															
						<dd>
							<a href="${base}/admin/switch/list.do" target="iframe">换货管理</a>
						</dd>
														
						<dd>
							<a href="${base}/admin/repair/list.do" target="iframe">维修管理</a>
						</dd>
											
						<dd>
							<a href="${base}/admin/product_return/list.do" target="iframe">客户寄回商品</a>
						</dd>
						
						<dd>
							<a href="${base}/admin/repair_report/list.do" target="iframe">故障商品检测</a>
						</dd>
						
						<dd>
							<a href="${base}/admin/product_back/list.do" target="iframe">公司回寄商品</a>
						</dd>
									
				</dl>
			
				<dl id="member">
					<dt>会员管理</dt>
					
					    <dd>
							<a href="${base}/admin/statistics/memberCount.do" target="iframe">会员统计</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/member/list.do" target="iframe">会员管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/memberRank/list.do" target="iframe">会员等级</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/comment/list.do" target="iframe">评论管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/mobile_login/list.do" target="iframe">移动端登录</a>
						</dd>
					
				</dl>
			
				<dl id="content">
					<dt>内容管理</dt>
					
						<dd>
							<a href="${base}/admin/navigation/list.do" target="iframe">导航管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/article/list.do" target="iframe">文章管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/articlecategory/list.do" target="iframe">文章分类</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/tag/list.do" target="iframe">标签管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/friendlink/list.do" target="iframe">友情链接</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/adPosition/list.do" target="iframe">广告位</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/ad/list.do" target="iframe">广告管理</a>
						</dd>
					
						
						<dd>
							<a href="${base}/admin/page/settings.do?page=mobileHome" target="iframe">客户端首页</a>
						</dd>
				</dl>
			
				<dl id="marketing">
					<dt>营销管理</dt>
					
						<dd>
							<a href="${base}/admin/promotion/list.do" target="iframe">促销管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/coupon/list.do" target="iframe">优惠券管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/lottery/list.do" target="iframe">抽奖管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/home.do" target="iframe">SEO设置</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/sitemap/sitemap.do" target="iframe">Sitemap管理</a>
						</dd>
					
					<dd>
						<a href="${base}/admin/filter/list.do" target="iframe"> 过滤管理</a>
					</dd>
					
						<dd>
							<a href="${base}/admin/activity/list.do" target="iframe">商城历次活动管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/coupon/coupon_give.do" target="iframe">赠送优惠券</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/salesmessage/list.do" target="iframe">消息推送管理</a>
						</dd>
						<dd>
							<a href="http://192.168.1.49:8080/rob-admin/activity/list.jhtml" target="iframe">预订抢购</a>
						</dd>
						<dd>
							<a href="http://192.168.1.49:8080/rob-admin/lottery_activity/list.jhtml" target="iframe">抽奖活动</a>
						</dd>
						<dd>
							<a href="http://192.168.1.49:8080/rob-admin/weixin_redpack/list.jhtml" target="iframe">红包发放记录</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/mact/index.do" target="iframe">芯助力活动</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/bean_retain/list.do" target="iframe">芯豆获取信息</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/bean_used/list.do" target="iframe">芯豆消费信息</a>
						</dd>
					
				</dl>
			
				<dl id="charity">
					<dt>特科芯公益</dt>
					<dd>
						<a href="${base}/admin/baby/list.do" target="iframe">宝贝回家</a>
					</dd>
				</dl>
			
				<dl id="statistics">
					<dt>统计报表</dt>
						<dd>
							<a href="${base}/admin/actdevice/list.do" target="iframe">手机终端统计</a>
						</dd>
						<dd>
							<a href="${base}/admin/statistic/setting.do" target="iframe">统计设置</a>
						</dd>
						<dd>
							<a href="${base}/admin/sales/view.do" target="iframe">销售统计</a>
						</dd>
						<dd>
							<a href="${base}/admin/sales_ranking.do" target="iframe">销售排行</a>
						</dd>
						<dd>
							<a href="${base}/admin/export.do" target="iframe">数据导出</a>
						</dd>
				</dl>
			
				<dl id="system">
					<dt>系统设置</dt>
					
						<dd>
							<a href="system/config.html" target="iframe">系统设置</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/task/list.do" target="iframe">任务调度</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/task_data/list.do" target="iframe">任务数据</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/area/list.do" target="iframe">地区管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/paymentMethod/list.do" target="iframe">支付方式</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/shippingMethod/list.do" target="iframe">配送方式</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/deliveryCorp/list.do" target="iframe">物流公司</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/plugin/plugin_list.do" target="iframe">插件配置</a>
						</dd>
					
						<dd>
							<a href="admin/list.html" target="iframe">管理员</a>
						</dd>
					
						<dd>
							<a href="role/list.html" target="iframe">角色管理</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/message/list.do" target="iframe">短信消息</a>
						</dd>
					
						<dd>
							<a href="${base}/admin/system/ad_admin.do" target="iframe">广告位设置</a>
						</dd>
						<dd>
							<a href="${base}/admin/system/mobile_home.do" target="iframe">手机广告位设置</a>
						</dd>
						<dd>
							<a href="${base}/admin/log/list.do" target="iframe">日志管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/app/list.do" target="iframe">安装包管理</a>
						</dd>
						<dd>
							<a href="${base}/admin/feedback/list.do" target="iframe">问题反馈管理</a>
						</dd>
				</dl>
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="${base}/home.html" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
<script type="text/javascript">
	$(function(){
		
		var $groups = $('dl.default');
		if($groups.length == 0){
			var $menuGroups = $('#menu').find('dl');
			if($menuGroups.length > 0){
				$menuGroups.eq(0).addClass('default');
			}
		}
		
		function UISetting(){
			var winHeight = $(window).height();
			var thHeight = $('th.logo').height();
			var $mainPane = $('td.menu');
			//set ui
			$mainPane.css({
				height: winHeight - thHeight + 'px'
			});
			
			$('#iframe').css({
				height: winHeight - thHeight + 'px'
			});
		}
		
		$(window).bind('resize', function(){
			UISetting();
		});
		
		UISetting();
	});
</script>
</body>
</html>
