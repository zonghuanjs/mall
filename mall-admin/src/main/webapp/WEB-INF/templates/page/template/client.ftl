<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=0" name="viewport">
	<meta charset="UTF-8">
	<title>特科芯商城首页</title>
	<link rel="stylesheet" href="${base}/resources/index/css/bootstrap.min.css">
	<link rel="stylesheet" href="${base}/resources/index/css/swiper.min.css">
	<link rel="stylesheet" href="${base}/resources/index/font-awesome-4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${base}/resources/index/font-icon/iconfont.css">
	<link rel="stylesheet" href="${base}/resources/index/css/common.css">
	<link rel="stylesheet" href="${base}/resources/index/css/index.css">
	<!-- 脚本部分 -->
	<script src="${base}/resources/index/js/jquery.min.js"></script>
	<script src="${base}/resources/index/js/bootstrap.min.js"></script>
	<script src="${base}/resources/index/js/toe.min.js"></script>
	<script src="${base}/resources/index/js/swiper.min.js"></script>
	<script src="${base}/resources/index/js/common.js"></script>
	<script src="${base}/resources/index/js/index.js"></script>
</head>
<body>
<!-- code begin -->
    <input type="hidden" id="basePath" value="${base}" />
    <#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="container-fulid no-padding">
		<div class="container">
			<div class="row">
				
				<!-- 搜索菜单部分 -->
				<div class="search-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<!-- 扫码 -->
					<div class="pull-left img-col"><img src="${base}/resources/index/images/scan.png" alt=""></div>
					<!-- 搜索栏部分 -->
					<form class="input-group" action="">
					   	<!-- 搜索按钮 -->
					   	<span class="input-group-btn">
					       	<button class="btn btn-default search-btn" type="button">
					       		<i class="fa fa-search light-gray-font"></i>
					       	</button>
					   	</span>
					   	<!-- 搜索框 -->
					   	<input type="search" class="form-control01" placeholder="特科芯固态硬盘" required="required">
					   	<!-- 清除按钮 -->
					   	<input class="del-input" type="reset" value="×">
					</form>
					<!-- 消息 -->
					<div class="pull-right img-col"><img src="${base}/resources/index/images/message.png" alt=""></div>
				</div>

				<!-- 顶部防遮挡层 -->
				<!-- <div class="no-hover-top col-xs-12 col-sm-12 col-md-12 col-lg-12"></div> -->

				<#if pageModules?has_content>
				<#list pageModules as pageModule>
				<#if pageModule.elements?has_content>
				<#if pageModule.type.type() == 1>
                    <#if pageModule.count == 1>
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
					
					<!--单图列表  -->
					<div class="images-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding">				
						<a href="${mall_url(element.link, base)!''}">
							<img src="${mall_url(element.image, base)!''}" alt="">
						</a>
					</div>					
				    </#if>
					</#list>
					
					<#else>
					<div class="images-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
						<div class="images-wrap01 col-xs-6 col-sm-6 col-md-6 col-lg-6">				
							<a href="${mall_url(element.link, base)!''}">							
								<img src="${mall_url(element.image, base)!''}" alt="">
							</a>
						</div>
					</#if>
					</#list>
					</div>
					
					
					</#if>

				
				<#elseif pageModule.type.type() == 2>

				<!-- 分类菜单 -->
				<div class="category-menu-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12">
				    <#list pageModule.elements as element>
				    <#if pageModule.count == -1 || element_index lt pageModule.count>
					<!-- 菜单按钮 -->
					<div class="category-btn col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<a href="${mall_url(element.link, base)!''}">
							<img src="${mall_url(element.image, base)!''}" alt="固态硬盘">
							<p>${element.title!""}</p>
						</a>
					</div>
					</#if>
					</#list>
					
				</div>
				
				<#elseif pageModule.type.type() == 3>

				<!-- 轮播图 -->
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<!-- 指示点 -->
					<ol class="carousel-indicators">
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
					<#if element_index == 0>
					    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<#else>
					    <li data-target="#carousel-example-generic" data-slide-to="${element_index}"></li>
					</#if>
					</#if>					    
					</#list>								
					</ol>
					<!-- 图片容器 -->
					<div class="carousel-inner" role="listbox">
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
					<#if element_index == 0>
					    <div class="item active">
					<#else>
					    <div class="item">
					</#if>
					      	<a href="${mall_url(element.link, base)!''}">
					      		<img src="${mall_url(element.image, base)!''}" alt="轮播图">
					      	</a>
					      	<div class="carousel-caption"></div>
					    </div>
					</#if>    
					</#list>				
					</div>
				</div>
				
				<#elseif pageModule.type.type() == 4>
				
				<!--并排图  -->
				<div class="images-wrap03 col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 inside-shop">
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 inside-shop01">		
							<a href="${mall_url(element.link, base)!''}">							
								<img src="${mall_url(element.image, base)!''}" alt="">
							</a>
						</div>					
				    </#if>
					</#list>
					</div>
				</div>
								
				<#elseif pageModule.type.type() == 5>
				
					<#list pageModule.elements as element>
					<#if pageModule.count == -1 || element_index lt pageModule.count>
					<!--标题图片列表  -->
					<div class="images-wrap02 col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding">				
						<a href="${mall_url(element.link, base)!''}">
							<img src="${mall_url(element.image, base)!''}" alt="">
						</a>
					</div>
				    </#if>
					</#list>
				</#if>

				</#if>
				</#list>
				</#if>	
					
				<!-- 防遮挡层 -->
				<div class="no-hover col-xs-12 col-sm-12 col-md-12 col-lg-12"></div>

				<!-- 返回顶部层 -->
				<div id="back_top" class="back-top-wrap">
					<div class="back-top-btn">
						<i class="fa fa-angle-double-up fa-lg"></i>
					</div>
				</div>

			</div>
		</div>
	</div>
<!-- code end -->
</body>
</html>