<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发行抽奖活动</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/lottery.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 发行抽奖活动
	</div>
	<form id="inputForm" <#if lottery?has_content>action="edit.do?id=${lottery.id}"<#else>action="add.do"</#if> method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" <#if lottery?has_content>value="${lottery.name}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					描述:
				</th>
				<td>
					<input type="text" name="description" class="text" maxlength="200" <#if lottery?has_content>value="${lottery.description}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>起始日期:
				</th>
				<td colspan="2">
					<input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if lottery?has_content>value="${lottery.beginDate?datetime}"</#if> />
					抽奖开始时间
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>结束日期:
				</th>
				<td colspan="2">
					<input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if lottery?has_content>value="${lottery.endDate?datetime}"</#if> />
					抽奖活动结束时间
				</td>
			</tr>
			<tr>
				<th>
					是否启用:
				</th>
				<td>
					<input type="checkbox" name="enabled" value="true" <#if lottery?has_content && lottery.enabled>checked="checked"</#if>
					<#if !adminView.authorities?seq_contains('admin:coupon')>disabled="disabled"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>参与数量:
				</th>
				<td>
					<input type="text" name="number" class="text" maxlength="20" <#if lottery?has_content>value="${lottery.number!0}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>抽奖单价:
				</th>
				<td>
					<input type="text" name="price" class="text" maxlength="200" <#if lottery?has_content>value="${lottery.price?string('0.00')}"</#if> />
					参与抽奖活动需要支付的费用
				</td>
			</tr>
			<tr>
				<th>
					抽奖商品:
				</th>
				<td>
					<input type="text" id="search" name="search" class="text" placeholder="商品关键字" maxlength="200" />&nbsp;<span id="searchButton" class="button">查询</span>
					<div id="product_container">
						<label>
							<span id="productName"><#if lottery?has_content && lottery.product?has_content>${lottery.product.name}</#if></span>
							<input type="hidden" name="product" <#if lottery?has_content && lottery.product?has_content>value="${lottery.product.id}"</#if> />
						</label>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定"  />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>