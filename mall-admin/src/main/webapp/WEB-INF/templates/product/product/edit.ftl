<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.copy.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/resources/js/product_edit.js"></script>
<script type="text/javascript">
$(function(){
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				},
				filterMode: false,
				filePostName: 'file',
				uploadJson:'${base}/file/upload.do'
			});
		});
	}	
});
</script>

<style type="text/css">
	.specificationSelect {
		height: 100px;
		padding: 5px;
		overflow-y: scroll;
		border: 1px solid #cccccc;
	}
	
	.specificationSelect li {
		float: left;
		min-width: 150px;
		_width: 200px;
	}
</style>
</head>
<body>
	<#assign mall_url="cn.tekism.mall.freemarker.URLMethod"?new()>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; <#if product??>编辑<#else>添加</#if>商品
	</div>
<#if product??>
	<form id="inputForm" action="${base}/admin/product/save.do?id=${product.id}" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="商品介绍" />
			</li>
			<li>
				<input type="button" value="商品图片" />
			</li>
			<li>
				<input type="button" value="商品资源" />
			</li>
			<li>
				<input type="button" value="商品参数" />
			</li>
			<li>
				<input type="button" value="商品属性" />
			</li>
			<li>
				<input type="button" value="商品规格" />
			</li>
		</ul>
		<table class="input tabContent">
		<#if product.specificationValues?size gt 0>	
			<tr>
				<th>商品规格</th>
				<td>
					<#list product.specificationValues as value>${value.name}<#if value_has_next>&nbsp;</#if></#list>
				</td>
			</tr>
		</#if>
			<tr>
				<th>
					商品分类:
				</th>
				<td>
					<select id="productCategoryId" name="productCategoryId">
					<#list categoryList as category>
						<option value="${category.id}" <#if product.productCategory?? && product.productCategory.id == category.id>selected="selected"</#if> ><#if category.parent??>&nbsp;&nbsp;</#if> ${category.name} </option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					编号:
				</th>
				<td>
					<span>${product.sn}</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" value="${product.name}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品类型:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="type" value="1" <#if product.type==1>checked="checked"</#if> >普通商品
						</label>
						<label>
							<input type="radio" name="type" value="2" <#if product.type==2>checked="checked"</#if> >联通手机卡
						</label>
						<label>
							<input type="radio" name="type" value="3" <#if product.type==3>checked="checked"</#if> >联通上网卡
						</label>
						<label>
							<input type="radio" name="type" value="5" <#if product.type==5>checked="checked"</#if> >海淘商品
						</label>
					</fieldset>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>销售价:
				</th>
				<td>
					<input type="text" name="price" class="text" maxlength="16" value="${product.price!0}" />
				</td>
			</tr>
			<tr>
				<th>
					app专享价:
				</th>
				<td>
					<input type="text" name="app_price" class="text" maxlength="16" value="${product.appPrice!0}" />
					<span>在Android, IOS客户端购买时的价格; 如未设置则跟销售价一致</span>
				</td>
			</tr>
			<tr>
				<th>
					成本价:
				</th>
				<td>
					<input type="text" name="cost" class="text" maxlength="16" title="前台不会显示，仅供后台使用" value="${product.cost!0}" />
				</td>
			</tr>
			<tr>
				<th>
					市场价:
				</th>
				<td>
					<input type="text" name="marketPrice" class="text" maxlength="16" title="若留空则由系统自动计算" value="${product.marketPrice}" />
					<span>当市场价为0时, 无论是否开启市场价显示, 前台都不显示</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品主图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="images" class="text" value="${product.image!''}" maxlength="200" title="应用于首页、宣传页，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(product.image, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品中图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="mediumImage" class="text" value="${product.mediumImage!''}" maxlength="200" title="应用于首页、宣传页，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(product.mediumImage, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品缩略图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="thumbnail" class="text" maxlength="200" value="${product.thumbnail!''}" title="应用于列表页、购物车, 订单，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="${mall_url(product.thumbnail, base)!''}" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					单位:
				</th>
				<td>
					<input type="text" name="unit" class="text" maxlength="200" value="${product.unit}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>重量:
				</th>
				<td>
					<input type="text" name="weight" class="text" maxlength="9" title="单位: 克" value="${product.weight}" />
					<span>单位: 克; 此值用于计算运费</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>库存:
				</th>
				<td>
					<input type="text" name="stock" class="text" maxlength="9" title="留空表示不计库存" value="${product.stock}" readonly="true" />
					库存请到“库存管理”下修改
				</td>
			</tr>
			<tr>
				<th>
					赠送积分:
				</th>
				<td>
					<input type="text" name="point" class="text" maxlength="9" title="若留空则由系统自动计算" <#if product.point??>value="${product.point}"</#if> />
				</td>
			</tr>
			<tr>
				<th>
					品牌:
				</th>
				<td>
					<select name="brandId">
						<option value="">请选择...</option>
					<#list brands as brand>
						<option value=${brand.id} <#if product.brand?? && brand.id == product.brand.id>selected="selected"</#if> >${brand.name}</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					标签:
				</th>
				<td>
				<#list tags as tag>
						<label>
							<input type="checkbox" name="tagIds" value="${tag.id}" <#if product.tags?seq_contains(tag)>checked="checked"</#if> />${tag.name}
						</label>
				</#list>
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
				<#if authorities?? && authorities?seq_contains("admin:product:review")>
					<label>
						<input type="checkbox" name="isMarketable" value="true" <#if product.marketable>checked="checked"</#if> />是否上架
					</label>
					<label>
						<input type="checkbox" name="isList" value="true" <#if product.list>checked="checked"</#if> />是否列出
					</label>
				</#if>
					<label>
						<input type="checkbox" name="isTop" value="true" <#if product.top>checked="checked"</#if> />是否置顶
					</label>
					<label>
						<input type="checkbox" name="isGift" value="true" <#if product.gift>checked="checked"</#if> />是否为赠品
					</label>
					<label>
						<input type="checkbox" name="freePost" value="true" <#if product.freePost>checked="checked"</#if> />包邮
					</label>
				</td>
			</tr>
			<tr>
				<th>
					ERP品号:
				</th>
				<td>
					<select name="warehouse">
					<#list warehouses?keys as key>
						<option value="${key}" <#if cWarehouse?has_content && cWarehouse == key>selected="selected"</#if> >${warehouses[key]}</option>
					</#list>
					</select>
					<input type="text" name="memo" class="text" maxlength="200" value="${product.memo}" />
				</td>
			</tr>
			<tr>
				<th>
					搜索关键词:
				</th>
				<td>
					<input type="text" name="keyword" class="text" maxlength="200" title="应用于前台商品搜索，多个关键字以(,)分隔" value="${product.keyword}" />
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" value="${product.seoTitle}" title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面关键词:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" value="${product.seoKeywords}" title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" value="${product.seoDescription}" title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
		</table>
		<!-- 商品详情 -->
		<table class="input tabContent">
			<tr>
				<td>
					<textarea id="editor" name="description" class="editor" style="width: 100%;">${product.description!''}</textarea>
				</td>
			</tr>
		</table>
		<!--商品图片-->
		<table id="productImageTable" class="input tabContent">
			<tr>
				<td colspan="4">
					<a href="javascript:;" id="addProductImage" class="button">增加图片</a>
				</td>
			</tr>
			<tr class="title">
				<td>
					文件
				</td>
				<td>
					标题
				</td>
				<td>
					排序
				</td>
				<td>
					删除
				</td>
			</tr>
		<#if product.images??>
		  <#list product.images as image>
			<tr>
				<td>
					<input type="hidden" name="imageId" value="${image.id}" />
					<input type="text" name="image_${image.id}" class="text" maxlength="200" value="${image.image}"/> 
					<input type="button" class="button browserButton" value="选择文件"/>
					<a href="${mall_url(image.image, base)}" target="_blank">查看</a>
				</td>
				<td><input type="text" name="imageTitle_${image.id}" class="text" maxlength="200" value="${image.title!''}"></td>
				<td><input type="text" name="imageOrder_${image.id}" class="text productImageOrder" style="width: 50px;" maxlength="9" value="${image.orders!''}"/></td>
				<td><a href="javascript:;" class="deleteProductImage">[删除]</td>
			</tr>
		  </#list>
		</#if>
		</table>
		<!--商品资源-->
		<table id="productResources" class="input tabContent">
			<tr>				
				<td colspan="4">
					<select name="resourcesType" id="resourcesType">
						<option value="image">图片</option>
						<option value="file">文件</option>
					</select>
					<a href="javascript:;" id="addProductResource" class="button">增加资源</a>
				</td>
			</tr>
			<tr class="title">
				<td>
					文件
				</td>
				<td>
					标题
				</td>
				<td>
					排序
				</td>
				<td>
					删除
				</td>
			</tr>
		<#list product.goods.resources as resource>
			<tr>
				<td>
					<input type="hidden" name="resourceId" value="${resource.id}" />
					<input type="text" name="resourceUrl_${resource.id}" class="text" maxlength="200" value="${resource.url}"/>
					<a href="${mall_url(resource.url, base)}" target="_blank">查看</a>
					<a href="javascript:;" class="copy">复制</a>
				</td>
				<td><input type="text" name="resourceTitle_${resource.id}" class="text" maxlength="200" value="${resource.title!''}"></td>
				<td><input type="text" name="resourceOrder_${resource.id}" class="text resourceOrder" style="width: 50px;" maxlength="9" value="${resource.orders!''}"/></td>
				<td><a href="javascript:;" class="deleteProductResource">[删除]</td>
			</tr>
		</#list>			
		</table>
		<!--商品参数-->
		<table id="parameterTable" class="input tabContent">
		<tr>
			<td colspan="2">
				上传文件：<input type="file" id="file" name="file"/>
		        <input type="button" class="button" id="fileUpload" value="导入">
			</td>			
		</tr>
		
	<#list productParameters as group>
		<tr>
			<td style="text-align:right; padding-right:10px;">
				<strong>${group.groupName}:</strong>
			</td>
			<td>&nbsp;<td/>
		</tr>
		<#list group.params as param>
			<tr>
				<th name="paramName">${param.name}</th>
				<td>
					<input type="hidden" name="paramId" value="${param.id}" />
					<input type="text" name="param" class="text" maxlength="200" value="${param.value!''}" />
				</td>
			</tr>
		</#list>
	</#list>
		</table>
		<!--商品属性-->
		<table id="attributeTable" class="input tabContent">
	<#list attributes as attribute>
			<tr>
				<th>${attribute.name}</th>
				<td>
					<select name="option" class="valid">
						<option value>请选择...</option>
						<option value=""></option>
					</select>
				</td>
			</tr>
	</#list>
		</table>
		<!--规格管理-->
		<table class="input tabContent">
			<tr class="title">
				<th>
					请选择商品规格:
				</th>
			</tr>
			<tr>
				<td>
					<div id="specificationSelect" class="specificationSelect">
						<ul>
						<#list specifications as specification>
							<li>
								<label>
									<input type="checkbox" name="specificationIds" value="${specification.id}" <#if product.specifications?seq_contains(specification)>checked="checked"</#if> />${specification.name}
								</label>
							</li>
						</#list>
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<a href="javascript:;" id="addSpecificationProduct" class="button">增加规格商品</a>
				</td>
			</tr>
			<tr>
				<td>
					<table id="specificationProductTable" class="input">
						<tbody>
							<tr class="title">
								<td width="60">
									&nbsp;
								</td>
							<#list specifications as specification>
								<#if product.specifications?seq_contains(specification)>
									<th specification_id="${specification.id}">
										${specification.name}
									</th>
								</#if>
							</#list>
								<td>
									操作
								</td>
							</tr>
					   	<tr product_id="${product.id}">
					   		<td width="60">
								当前规格
								<input type="hidden" name="product_${product.id}" value="${product.id}" />
							</td>
						<#list specifications as specification>
							<#if product.specifications?seq_contains(specification)>
								<td>
									<select name="specification_${product.id}_${specification.id}">
									<#list specificationValues as value>
										<#if value.specification==specification>
											<option value="${value.id}" <#if product.specificationValues?seq_contains(value)>selected="selected"</#if> >${value.name}</option>
										</#if>
									</#list>
									</select>
								</td>
							</#if>
						</#list>
							<td>
								-
							</td>
					   	</tr>
						<!-- 参数值 -->
						<#list others as row>
							<tr product_id="${row.id}">
								<td width="60">
									<input type="hidden" name="product_${row.id}" value="${row.id}" />
								</td>
							<#list specifications as specification>
							<#if product.specifications?seq_contains(specification)>
								<td>
									<select name="specification_${row.id}_${specification.id}">
										<#list specificationValues as value>
											<#if value.specification==specification>
											<option value="${value.id}" <#if row.specificationValues?seq_contains(value)>selected="selected"</#if> >${value.name}</option>
											</#if>
										</#list>
									</select>
								</td>
							</#if>
							</#list>
								<td>
									<a href="${base}/admin/product/edit.do?id=${row.id}">[编辑]</a>
									<a href="javascript:;" class="deleteSpecificationProduct">删除</a>
								</td>
							</tr>
						</#list>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" id="sub" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1)'" />
				</td>
			</tr>
		</table>
	</form>
</#if> 
</body>
</html>