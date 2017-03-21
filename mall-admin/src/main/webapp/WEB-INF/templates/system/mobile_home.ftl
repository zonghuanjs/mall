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
<script type="text/javascript" src="${base}/resources/admin/js/mobile_home.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 手机端首页广告设置
	</div>
	<form id="inputForm" action="mobile_home.do" method="post" >
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="首页广告" />
			</li>
		</ul>
		<!-- 基本设置 -->
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>首页1号轮播广告位:
				</th>
				<td>
					<select name="home1" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home1?has_content && homeConfig.home1==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>2号广告位:
				</th>
				<td>
					<select name="home2" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home2?has_content && homeConfig.home2==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>3号广告位(大图):
				</th>
				<td>
					<select name="home31" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home31?has_content && homeConfig.home31==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span> 3号广告位(小图):
				</th>
				<td>
					<select name="home32" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home32?has_content && homeConfig.home32==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>4号广告位(大图):
				</th>
				<td>
					<select name="home41" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home41?has_content && homeConfig.home41==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>4号广告位(小图):
				</th>
				<td>
					<select name="home42" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.home42?has_content && homeConfig.home42==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>链接广告位:
				</th>
				<td>
					<select name="linkPos" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.linkPos?has_content && homeConfig.linkPos==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>横向广告位:
				</th>
				<td>
					<select name="crossPos" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.crossPos?has_content && homeConfig.crossPos==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>启动广告位:
				</th>
				<td>
					<select name="startPos" >
					<#list positions as pos>
						<option value="${pos.id}" <#if homeConfig.startPos?has_content && homeConfig.startPos==pos.id>selected="selected"</#if> >${pos.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>首页分类数据(8个):
				</th>
				<td>
					<input type="hidden" name="category" id="category" value="${homeConfig.category!''}" />
					<select id="categorylist" >
					<#list categorys as category>
						<option value="${category.id}" >${category.name}</option>
					</#list>
					</select>
					<input type="button" id="categoryAddButton" class="button" value="增加" />
					<div id="category_c">
					
					</div>
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