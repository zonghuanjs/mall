<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看会员</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript">
$().ready(function() {
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 查看会员
	</div>
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="个人资料" />
			</li>
			<li>
				<input type="button" value="购买订单" />
			</li>
			<li>
				<input type="button" value="收货地址" />
			</li>			
		</ul>
		<table class="input tabContent">
		<tr>
			<th>
				用户名:
			</th>
			<td>
				${member.username!""}
			</td>
		</tr>
		<tr>
			<th>
				E-mail:
			</th>
			<td>
				${member.email!""}
				<#if memberView.mailValidated>
					(已验证)
				<#else>
					(未验证)
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				手机:
			</th>
			<td>
			<#if member.mobile?has_content>
				${member.mobile!""}
				<#if memberView.mobileValidated>
					(已绑定)
				<#else>
					(未绑定)
				</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<th>
				会员等级:
			</th>
			<td>
				${member.memberRank.name!""}
			</td>
		</tr>
		<tr>
			<th>
				状态:
			</th>
			<td><#if (member.enabled && !member.locked)>
					<span class="green">正常</span>
				<#else>
				    <span class="red">停用或锁定</span>
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				积分:
			</th>
			<td>
				${member.point!"0"}
			</td>
		</tr>
		<tr>
			<th>
				余额:
			</th>
			<td>
				${member.balance?string.currency!""}
			</td>
		</tr>
		<tr>
			<th>
				消费金额:
			</th>
			<td>
				${member.amount?string.currency!""}
			</td>
		</tr>
		<tr>
			<th>
				创建日期:
			</th>
			<td>
				${member.createDate!""}
			</td>
		</tr>
		<tr>
			<th>
				最后登录日期:
			</th>
			<td>
				${member.loginDate!""}
			</td>
		</tr>
		<tr>
			<th>
				注册IP:
			</th>
			<td>
				${member.registerIP!""}
			</td>
		</tr>
		<tr>
			<th>
				最后登录IP:
			</th>
			<td>
				${member.loginIP!""}
			</td>
		</tr>
		<tr>
			<th>
				评论数:
			</th>
			<td>
				${member.comments?size!""}
			</td>
		</tr>
		<tr>
			<th>
				咨询数:
			</th>
			<td>
				0
			</td>
		</tr>
		<tr>
			<th>
				收藏商品数:
			</th>
			<td>
				${member.favoriteProducts?size!""}
			</td>
		</tr>
	</table>
		
	<table class="input tabContent">
				<tr>
					<th>
						姓名:
					</th>
					<td>
						${member.name!""}	
					</td>
				</tr>
				<tr>
					<th>
						性别:
					</th>
					<td>
					<#if member.gender==0>
						男
					<#elseif member.gender==1>
					    女
					</#if>
					</td>
				</tr>
				<tr>
					<th>
						出生日期:
					</th>
					<td>
						${member.birth!""}	
					</td>
				</tr>
				<tr>
					<th>
						地区:
					</th>
					<td>
						<#if member.area??>${member.area.fullName!""}</#if>	
					</td>
				</tr>
				<tr>
					<th>
						地址:
					</th>
					<td>
						${member.address!""}		
					</td>
				</tr>
		</table>
	    
	    <table class="input tabContent">
			<tr>				
				<td>订单编号</td>
				<td>订单金额</td>
				<td>会员</td>
				<td>收货人</td>
				<td>配送方式</td>
				<td>配送地区</td>
				<td>订单状态</td>
				<td>下单时间</td>				
			</tr>
		<#list orders as order>
				<tr>					
					<td>
						<a href="${base}/admin/query/order/view!${order.id}.do">${order.sn}</a><#if order.type==3>&nbsp;<span class="red">抽奖</span></#if>
					</td>
					<td>
						${order.amountPaid?string.currency}
					</td>
					<td>
						${order.member.username}
					</td>
					<td>
						${order.consignee}
					</td>
					<td>
						<#if order.shippingMethod??>${order.shippingMethod.name}</#if>
					</td>
					<td>
						<#if order.areaName?has_content>${order.areaName}</#if>
					</td>
					<td>
						<#switch order.orderStatus>
                         <#case 1>
                                                                                                            未支付
                           <#break>
                         <#case 2>
                                                                                                            已支付 
                           <#break>
                         <#case 3>
                                                                                                           待发货
                           <#break>
                         <#case 4>
                                                                                                         已发货
                           <#break>
                         <#case 5>
                                                                                                         待处理
                           <#break>
                         <#case 6>退货处理<#break>
                         <#case 0>
                                                                                                         交易完成
                           <#break>
                         <#case -1>
                                                                                                         交易关闭
                           <#break>                          
                         <#default>
                                                                                                       处理中
                        </#switch>
						
					</td>			
					<td>
						<span title="${order.createDate}">${order.createDate}</span>
					</td>
											
				</tr>
		</#list>
		</table>
	
	     <table class="input tabContent">
			<tr>				
				<td>名称</td>
				<td>地区名称</td>
				<td>地址</td>
				<td>收件人</td>
				<td>电话</td>
				<td>邮政编码</td>
				<td>是否默认</td>
				<td>创建时间</td>				
			</tr>
		<#list addresses as address>
			<tr>					
				<td>${address.name}</td>
				<td>${address.areaName.fullName}</td>
				<td>${address.address}</td>
				<td>${address.consignee}</td>
				<td>${address.phone}</td>
				<td>${address.zipCode}</td>
				<td><#if address.dafault>是<#else>否</#if></td>			
				<td>${address.createDate}</td>											
			</tr>
		</#list>
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.back(-1);"/>
				</td>
			</tr>
		</table>
</body>
</html>