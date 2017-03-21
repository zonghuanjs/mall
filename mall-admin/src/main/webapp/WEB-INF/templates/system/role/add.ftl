<!DOCTYPE html>
<head>
<base href="${base}/"/>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="resources/css/list.css"/>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {

var $inputForm = $('#inputForm');
	var $authorities = $('.authorities');
	
	$inputForm.validate({
		rules: {
			name: "required",
			authorities: "required"
		},
		messages: {
			name: "必填",
			authorities: "*"
		}
	});
	
	$authorities.each(function(){
		var $group = $(this);
		var $selectAll = $group.find('.selectAll');
		
		$selectAll.bind('click', function(){
			var $options = $group.find(':checkbox');
			if($options.filter(':checked').size() > 0){
				$options.prop('checked', false);
			}else{
				$options.prop('checked', true);
			}
		});
	});
    var $inputForm = $("#inputForm");	
    var $groups = $inputForm.find(':checkbox').filter('.group');
    
    $groups.bind('change', function(){
    	var $this = $(this);
    	if($this.is(':checked')){
    		$this.closest('tr').find(':checkbox').prop('checked', true);
    	}else{
    		$this.closest('tr').find(':checkbox').prop('checked', false);
    	}
    });
    
});
</script>
</head>
<body>
	<div class="path">
		<a href="home.html">首页</a> &raquo; 添加角色
	</div>
	<form id="inputForm" action="<#if role??>role/save.html?id=${role.id}<#else>role/add.html</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if role??>value="${role.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" <#if role??>value="${role.description}"</#if> />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="role_authorities">
				<th>	
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:product" <#if role_authorities?? && role_authorities?seq_contains('admins:group:product')>checked="checked"</#if> />商品管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
					    <label>
							<input type="checkbox" name="role_authorities" value="admin:product:category" <#if role_authorities?? && role_authorities?seq_contains("admin:product:category")>checked="checked"</#if> />分类管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:category_add" <#if role_authorities?? && role_authorities?seq_contains("admin:product:category_add")>checked="checked"</#if> />分类添加
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:category_edit" <#if role_authorities?? && role_authorities?seq_contains("admin:product:category_edit")>checked="checked"</#if> />分类删除
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:category_delete" <#if role_authorities?? && role_authorities?seq_contains("admin:product:category_delete")>checked="checked"</#if> />分类删除
						</label><br/>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:list" <#if role_authorities?? && role_authorities?seq_contains("admin:product:list")>checked="checked"</#if> />商品管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:add" <#if role_authorities?? && role_authorities?seq_contains("admin:product:add")>checked="checked"</#if> />商品发布
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:edit" <#if role_authorities?? && role_authorities?seq_contains("admin:product:edit")>checked="checked"</#if> />商品编辑
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:product:delete")>checked="checked"</#if> />商品删除
						</label><br />
					    <label>
							<input type="checkbox" name="role_authorities" value="admin:product:stock" <#if role_authorities?? && role_authorities?seq_contains("admin:product:stock")>checked="checked"</#if> />库存管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:price" <#if role_authorities?? && role_authorities?seq_contains("admin:product:price")>checked="checked"</#if> />价格管理
						</label>						
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:review" <#if role_authorities?? && role_authorities?seq_contains("admin:product:review")>checked="checked"</#if> />上架审核
						</label>						
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:parameter" <#if role_authorities?? && role_authorities?seq_contains("admin:product:parameter")>checked="checked"</#if> />商品参数管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:attribute" <#if role_authorities?? && role_authorities?seq_contains("admin:product:attribute")>checked="checked"</#if> />商品属性管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:spec" <#if role_authorities?? && role_authorities?seq_contains("admin:product:spec")>checked="checked"</#if> />规格管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:brand" <#if role_authorities?? && role_authorities?seq_contains("admin:product:brand")>checked="checked"</#if> />品牌管理
						</label>						
					</span>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:order" <#if role_authorities?? && role_authorities?seq_contains('admins:group:order')>checked="checked"</#if> />订单管理
					</label>
				</th>
				<td>
					<div title="订单管理">
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:list" <#if role_authorities?? && role_authorities?seq_contains("admin:order:list")>checked="checked"</#if> />订单列表
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:check" <#if role_authorities?? && role_authorities?seq_contains("admin:order:check")>checked="checked"</#if> />付款到账确认
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:order:delete")>checked="checked"</#if> />订单删除
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:edit" <#if role_authorities?? && role_authorities?seq_contains("admin:order:edit")>checked="checked"</#if> />订单编辑
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:close" <#if role_authorities?? && role_authorities?seq_contains("admin:order:close")>checked="checked"</#if> />订单关闭
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:prepare" <#if role_authorities?? && role_authorities?seq_contains("admin:order:prepare")>checked="checked"</#if> />订单备货
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:order:shipping" <#if role_authorities?? && role_authorities?seq_contains("admin:order:shipping")>checked="checked"</#if> />订单发货
							</label>							
						</span>
					</div>
					<div title="支付管理">
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="role_authorities" value="admin:payment:list" <#if role_authorities?? && role_authorities?seq_contains("admin:payment:list")>checked="checked"</#if> />支付列表
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:payment:add" <#if role_authorities?? && role_authorities?seq_contains("admin:payment:add")>checked="checked"</#if> />支付丢单处理
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:payment:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:payment:delete")>checked="checked"</#if> />支付删除
							</label>
						</span>
					</div>
					<div title="退款管理">
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="role_authorities" value="admin:refund:list" <#if role_authorities?? && role_authorities?seq_contains("admin:refund:list")>checked="checked"</#if> />退款列表
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:refund:check" <#if role_authorities?? && role_authorities?seq_contains("admin:refund:check")>checked="checked"</#if> />退款审核
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:refund:do" <#if role_authorities?? && role_authorities?seq_contains("admin:refund:do")>checked="checked"</#if> />退款执行
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:refund:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:refund:delete")>checked="checked"</#if> />退款删除
							</label>
						</span>
					</div>
					<div>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="role_authorities" value="admin:shipping" <#if role_authorities?? && role_authorities?seq_contains("admin:shipping")>checked="checked"</#if> />发货管理
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:addTax" <#if role_authorities?? && role_authorities?seq_contains("admin:addTax")>checked="checked"</#if> />增值税发票审核
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:shipping:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:shipping:delete")>checked="checked"</#if> />发货删除
							</label>
							<label>
								<input type="checkbox" name="role_authorities" value="admin:addTax:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:addTax:delete")>checked="checked"</#if> />增税发票删除
							</label>
						</span>
					</div>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>				
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:apply" <#if role_authorities?? && role_authorities?seq_contains('admins:group:apply')>checked="checked"</#if> />售后管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:apply:check" <#if role_authorities?? && role_authorities?seq_contains("admin:apply:check")>checked="checked"</#if> />售后审核
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:return" <#if role_authorities?? && role_authorities?seq_contains("admin:return")>checked="checked"</#if> />退货管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:switch" <#if role_authorities?? && role_authorities?seq_contains("admin:switch")>checked="checked"</#if> />换货管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:repair" <#if role_authorities?? && role_authorities?seq_contains("admin:repair")>checked="checked"</#if> />维修管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:apply" <#if role_authorities?? && role_authorities?seq_contains("admin:apply")>checked="checked"</#if> />售后申请管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:check" <#if role_authorities?? && role_authorities?seq_contains("admin:product:check")>checked="checked"</#if> />故障商品检测
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:productback" <#if role_authorities?? && role_authorities?seq_contains("admin:productback")>checked="checked"</#if> />公司回寄商品
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:recieved" <#if role_authorities?? && role_authorities?seq_contains("admin:product:recieved")>checked="checked"</#if> />确认收货
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:refund:agree" <#if role_authorities?? && role_authorities?seq_contains("admin:refund:agree")>checked="checked"</#if> />同意退款
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:repair:start" <#if role_authorities?? && role_authorities?seq_contains("admin:repair:start")>checked="checked"</#if> />开始维修
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:repair:finished" <#if role_authorities?? && role_authorities?seq_contains("admin:repair:finished")>checked="checked"</#if> />维修完成
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:switch:agree" <#if role_authorities?? && role_authorities?seq_contains("admin:switch:agree")>checked="checked"</#if> />同意换货
						</label>	
						<label>
							<input type="checkbox" name="role_authorities" value="admin:product:return" <#if role_authorities?? && role_authorities?seq_contains("admin:product:return")>checked="checked"</#if> />客户寄回商品
						</label>	
						<label>
							<input type="checkbox" name="role_authorities" value="admin:apply:delete" <#if role_authorities?? && role_authorities?seq_contains("admin:apply:delete")>checked="checked"</#if> />售后申请删除
						</label>
					</span>
				</td>
			</tr>
			
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:member" <#if role_authorities?? && role_authorities?seq_contains('admins:group:member')>checked="checked"</#if> />会员管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
					   <label>
							<input type="checkbox" name="role_authorities" value="admin:member:statistics" <#if role_authorities?? && role_authorities?seq_contains("admin:member:statistics")>checked="checked"</#if> />会员统计
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:member" <#if role_authorities?? && role_authorities?seq_contains("admin:member")>checked="checked"</#if> />会员管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:memberRank" <#if role_authorities?? && role_authorities?seq_contains("admin:memberRank")>checked="checked"</#if> />会员等级管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:memberAttribute" <#if role_authorities?? && role_authorities?seq_contains("admin:memberAttribute")>checked="checked"</#if> />会员注册项管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:review" <#if role_authorities?? && role_authorities?seq_contains("admin:review")>checked="checked"</#if> />评论管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:consultation" <#if role_authorities?? && role_authorities?seq_contains("admin:consultation")>checked="checked"</#if> />咨询管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:content" <#if role_authorities?? && role_authorities?seq_contains('admins:group:content')>checked="checked"</#if> />内容管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:navigation" <#if role_authorities?? && role_authorities?seq_contains("admin:navigation")>checked="checked"</#if> />导航管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:article" <#if role_authorities?? && role_authorities?seq_contains("admin:article")>checked="checked"</#if> />文章管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:articleCategory" <#if role_authorities?? && role_authorities?seq_contains("admin:articleCategory")>checked="checked"</#if> />文章分类管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:tag" <#if role_authorities?? && role_authorities?seq_contains("admin:tag")>checked="checked"</#if> />标签管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:friendLink" <#if role_authorities?? && role_authorities?seq_contains("admin:friendLink")>checked="checked"</#if> />友情链接管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:adPosition" <#if role_authorities?? && role_authorities?seq_contains("admin:adPosition")>checked="checked"</#if> />广告位管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:ad" <#if role_authorities?? && role_authorities?seq_contains("admin:ad")>checked="checked"</#if> />广告管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:template" <#if role_authorities?? && role_authorities?seq_contains("admin:template")>checked="checked"</#if> />模板管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:cache" <#if role_authorities?? && role_authorities?seq_contains("admin:cache")>checked="checked"</#if> />缓存管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:static" <#if role_authorities?? && role_authorities?seq_contains("admin:static")>checked="checked"</#if> />静态化管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:index" <#if role_authorities?? && role_authorities?seq_contains("admin:index")>checked="checked"</#if> />索引管理
						</label>
					</span>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:promotion" <#if role_authorities?? && role_authorities?seq_contains('admins:group:promotion')>checked="checked"</#if> />营销管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:promotion" <#if role_authorities?? && role_authorities?seq_contains("admin:promotion")>checked="checked"</#if> />促销管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:coupon" <#if role_authorities?? && role_authorities?seq_contains("admin:coupon")>checked="checked"</#if> />优惠券管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:seo" <#if role_authorities?? && role_authorities?seq_contains("admin:seo")>checked="checked"</#if> />SEO设置
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:sitemap" <#if role_authorities?? && role_authorities?seq_contains("admin:sitemap")>checked="checked"</#if> />Sitemap管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:beanRetain" <#if role_authorities?? && role_authorities?seq_contains("admin:beanRetain")>checked="checked"</#if> />芯豆获取信息
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:beanUsed" <#if role_authorities?? && role_authorities?seq_contains("admin:beanUsed")>checked="checked"</#if> />芯豆使用信息
						</label>
					</span>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:statistics" <#if role_authorities?? && role_authorities?seq_contains('admins:group:statistics')>checked="checked"</#if> />统计管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:statistics" <#if role_authorities?? && role_authorities?seq_contains("admin:statistics")>checked="checked"</#if> />访问统计管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:sales" <#if role_authorities?? && role_authorities?seq_contains("admin:sales")>checked="checked"</#if> />销售统计
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:salesRanking" <#if role_authorities?? && role_authorities?seq_contains("admin:salesRanking")>checked="checked"</#if> />销售排行
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:purchaseRanking" <#if role_authorities?? && role_authorities?seq_contains("admin:purchaseRanking")>checked="checked"</#if> />消费排行
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:deposit" <#if role_authorities?? && role_authorities?seq_contains("admin:deposit")>checked="checked"</#if> />预存款
						</label>
					</span>
				</td>
			</tr>
			<tr class="role_authorities">
				<th>				
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:system" <#if role_authorities?? && role_authorities?seq_contains('admins:group:system')>checked="checked"</#if> />系统设置
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:setting" <#if role_authorities?? && role_authorities?seq_contains("admin:setting")>checked="checked"</#if> />系统设置
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:task" <#if role_authorities?? && role_authorities?seq_contains("admin:task")>checked="checked"</#if> />任务调度
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:taskdata" <#if role_authorities?? && role_authorities?seq_contains("admin:taskdata")>checked="checked"</#if> />任务数据
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:area" <#if role_authorities?? && role_authorities?seq_contains("admin:area")>checked="checked"</#if> />地区管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:paymentMethod" <#if role_authorities?? && role_authorities?seq_contains("admin:paymentMethod")>checked="checked"</#if> />支付方式管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:shippingMethod" <#if role_authorities?? && role_authorities?seq_contains("admin:shippingMethod")>checked="checked"</#if> />配送方式管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:deliveryCorp" <#if role_authorities?? && role_authorities?seq_contains("admin:deliveryCorp")>checked="checked"</#if> />物流公司管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:paymentPlugin" <#if role_authorities?? && role_authorities?seq_contains("admin:paymentPlugin")>checked="checked"</#if> />支付插件
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:storagePlugin" <#if role_authorities?? && role_authorities?seq_contains("admin:storagePlugin")>checked="checked"</#if> />存储插件
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:admin" <#if role_authorities?? && role_authorities?seq_contains("admin:admin")>checked="checked"</#if> />管理员管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:role" <#if role_authorities?? && role_authorities?seq_contains("admin:role")>checked="checked"</#if> />角色管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:message" <#if role_authorities?? && role_authorities?seq_contains("admin:message")>checked="checked"</#if> />消息管理
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:log" <#if role_authorities?? && role_authorities?seq_contains("admin:log")>checked="checked"</#if> />日志管理
						</label>
					</span>
				</td>
			</tr>
			
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:query" <#if role_authorities?? && role_authorities?seq_contains('admins:group:query')>checked="checked"</#if> />查询管理
					</label>
				</th>
				<td>
					<span class="fieldSet">
						<label>
							<input type="checkbox" name="role_authorities" value="admin:queryMember" <#if role_authorities?? && role_authorities?seq_contains("admin:queryMember")>checked="checked"</#if> />会员查询
						</label>
						<label>
							<input type="checkbox" name="role_authorities" value="admin:queryOrder" <#if role_authorities?? && role_authorities?seq_contains("admin:queryOrder")>checked="checked"</#if> />订单查询
						</label>
					</span>
				</td>
			</tr>
			
			<tr class="role_authorities">
				<th>
					<label>
						<input type="checkbox" class="group" name="role_authorities" value="admins:group:charity" <#if role_authorities?? && role_authorities?seq_contains('admins:group:charity')>checked="checked"</#if> />公益管理
					</label>
				</th>
				<td>
					
				</td>
			</tr>
			
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<!--<input type="submit" class="button" value="确&nbsp;&nbsp;定" <#if role?? && role.system>disabled="disabled"</#if> />-->
					<input type="submit" class="button" value="确&nbsp;&nbsp;定"/>
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>