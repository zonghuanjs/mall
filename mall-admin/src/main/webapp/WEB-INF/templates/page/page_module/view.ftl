<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>页面模块详情</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/page.js"></script>
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
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<input type="hidden" id="basePath" value="${base}" />
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 查看页面模块
	</div>
	<ul id="tab" class="tab">
		<li>
			<input type="button" value="页面模块信息" />
		</li>
		<li>
			<input type="button" value="模块元素信息" />
		</li>
	</ul>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="添加模块元素" onclick="location.href='${base}/admin/page/module_element/add.do?id=${pageModule.id}'"  />
			</td>
		</tr>
	</table>
	<!-- 订单信息 -->
	<table class="input tabContent">
		<tr>
			<th>
				页面模块名称:
			</th>
			<td width="360">
				${pageModule.name}
			</td>
			<th>
				显示顺序:
			</th>
			<td>
				${pageModule.order}
			</td>
		</tr>
		<tr>
			<th>
				页面类型:
			</th>
			<td>
				${pageModule.pageType}
			</td>
			<th>
				模块类型:
			</th>
			<td>
				${pageModule.type}
			</td>
		</tr>
		
	
		<tr>
			<th>
				显示内容数量:
			</th>
			<td>
				${pageModule.count}
			</td>
			<th>
				已付金额:
			</th>
			<td>
				
			</td>
		</tr>
		<tr>
			<td> 模块属性</td>
			<td> 属性值</td>
		</tr>
			<#if moduleRules?has_content>	
			<#list moduleRules as moduleRule>
			<#if pageModule?has_content && pageModule.pageModuleAttrs?has_content>
			<#assign map = pageModule.pageModuleAttrs>
		    <#assign value = map?values>	
			<#list map?keys as key>
			<#if key == moduleRule.rule()>
			<tr class="title">
				<td>
				   ${moduleRule.name()}				   
				</td>
				<td>
					${value[key_index]}
				</td>			
			</tr>
			</#if>
			</#list>
			</#if>	
			</#list>
			</#if>	
	</table>
	<!-- 商品信息 -->
	<table class="input tabContent">
		<tr class="title">
			<th>
				关联页面模块
			</th>
			<th>
				图片
			</th>
			<th>
				连接地址
			</th>
			<th>
				元素标题
			</th>
			<th>
				显示顺序
			</th>
			<th>
				操作
			</th>
		</tr>
		<#if pageModule.elements?has_content>
		<#list pageModule.elements as moduleElement>
			<tr>
				<td>
				    ${pageModule.name}
				</td>
				<td>
				    <a href="${mall_url(moduleElement.image, base)!''}" target="_blank">查看</a>
				</td>
				<td>
				    <a href="${mall_url(moduleElement.link, base)!''}" target="_blank">${moduleElement.link}</a>
					
				</td>
				<td>
					${moduleElement.title}
				</td>
				<td>
					${moduleElement.orders}
				</td>
				<td>
				    <input id="moduleElementId" type="hidden" value="${moduleElement.id}">					
					<a href="${base}/admin/page/module_element/edit.do?id=${moduleElement.id}">[编辑]</a>
					<a class="deleteElementButton">[删除]</a>
				</td>
			</tr>
		</#list>
		</#if>
	</table>
	<table class="input">
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
				<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/page/page_module/list.do'"  />
			</td>
		</tr>
	</table>
</body>
</html>