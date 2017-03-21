<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-特科芯商城</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!config.do"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 系统广告设置
	</div>
	<form id="inputForm" action="ad_admin.do" method="post" >
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="首页广告" />
			</li>
		</ul>
		<!-- 基本设置 -->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>首页顶部循环滚动的广告位:
				</th>
				<td>
					<select name="homeAdPosition1" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition1?? && config.homeAdPosition1==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页大banner右侧的广告位:
				</th>
				<td>
					<select name="homeAdPosition2" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition2?? && config.homeAdPosition2==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页大banner下左一:
				</th>
				<td>
					<select name="homeAdPosition3" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition3?? && config.homeAdPosition3==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span> 首页大banner下左二:
				</th>
				<td>
					<select name="homeAdPosition4" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition4?? && config.homeAdPosition4==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页大banner下左三:
				</th>
				<td>
					<select name="homeAdPosition5" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition5?? && config.homeAdPosition5==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页大banner下左四:
				</th>
				<td>
					<select name="homeAdPosition6" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition6?? && config.homeAdPosition6==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>个人中心右上:
				</th>
				<td>
					<select name="homeAdPosition7" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition7?? && config.homeAdPosition7==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>个人中心右下:
				</th>
				<td>
					<select name="homeAdPosition8" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition8?? && config.homeAdPosition8==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品详情页左下:
				</th>
				<td>
					<select name="homeAdPosition9" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeAdPosition9?? && config.homeAdPosition9==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品页新品1号广告位:
				</th>
				<td>
					<select name="productAdPosition1" >
					<#list positions as pos>
						<option value="${pos.id}" <#if config.productAdPosition1?? && config.productAdPosition1==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>个人中心页热门商品
				</th>
				<td>
					<select name="hotAdPosition">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.hotAdPosition?? && config.hotAdPosition == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页最顶部的广告位:
				</th>
				<td>
					<select name="homeADTopmost">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.homeADTopmost?? && config.homeADTopmost == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页固态硬盘广告位:
				</th>
				<td>
					<select name="solidStateDisk">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.solidStateDisk?? && config.solidStateDisk == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页内存条广告位:
				</th>
				<td>
					<select name="memoryBank">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.memoryBank?? && config.memoryBank == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页移动存储广告位:
				</th>
				<td>
					<select name="removableStorage">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.removableStorage?? && config.removableStorage == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页数码配件广告位:
				</th>
				<td>
					<select name="digitalAccessories">
					<#list positions as pos>
						<option value="${pos.id}" <#if config.digitalAccessories?? && config.digitalAccessories == pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			
		</table>
		
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" id="backButton" class="button" value="返&nbsp;&nbsp;回" />
				</td>
			</tr>
		</table>
	</form>

</body>
</html>