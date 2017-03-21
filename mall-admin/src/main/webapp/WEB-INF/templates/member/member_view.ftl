<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看会员</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/member_view.js"></script>
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
				<input type="button" value="芯豆获取信息" />
			</li>
			<li>
				<input type="button" value="芯豆使用信息" />
			</li>
		</ul>
		<table class="input tabContent">
		<tr>
			<th>
				用户名:
			</th>
			<td>
				${member.username!""}
				<input type="hidden" name="member" id="member" value="${member.id}" />
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
				<span id="checkMemberRank" class="button">校验</span>
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
					
					<td>
						领取时间
					</td>
					<td>
						领取类型
					</td>
					<td>
						领取数量
					</td>
					<td>
						是否被使用
					</td>
					<td>
						用完时间
					</td>
					<td>
						是否已经失效
					</td>
					<td>
						自然失效时间
					</td>
					<td>
						已使用数量
					</td>
					
				</tr>
				<#list beanRetains as beanRetain>
				<tr>
					
					<td>
						${beanRetain.createDate!""}
					</td>
					<td>
						${beanRetain.typeView}
					</td>
					<td>
						${beanRetain.optValue}
					</td>
					<td>
						${beanRetain.used?string('已使用', '未使用')}
					</td>
					<td>
						${beanRetain.usedTime!"-"}
					</td>
					<td>
					    ${beanRetain.isExpired?string('已过期', '未过期')}
					</td>
					<td>
						${beanRetain.expiredTime!"-"}
					</td>
					<td>
						${beanRetain.usedValue}
					</td>
				</tr>
			</#list>
		</table>
		
		<table class="input tabContent">
				<tr>
					
					<td>
						使用时间
					</td>
					<td>
						使用数量
					</td>
					<td>
						使用类型
					</td>
					<td>
						关联订单
					</td>
					<td>
						消费来源
					</td>
					
				</tr>
				<#list beanUseds as beanUsed>
				<tr>
					
					<td>
						${beanUsed.createDate!""}
					</td>
					<td>
						${beanUsed.optValue}
					</td>
					<td>
						${beanUsed.typeView}
					</td>
					<td>
					<#if beanUsed.order?has_content>
						${beanUsed.order.sn!'-'}
					<#else>
					    -
					</#if>
					</td>
					<td>
					    
					</td>
					
				</tr>
			</#list>
		</table>
	
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="javascript:history.go(-1);"/>
				</td>
			</tr>
		</table>
</body>
</html>