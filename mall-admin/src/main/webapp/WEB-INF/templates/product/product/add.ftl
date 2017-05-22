<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="${base}/admin/js/js!product.do"></script>
<script type="text/javascript" src="${base}/admin/js/js!productAdd.do"></script>
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
				uploadJson:'${base}/admin/file/upload.do'
			});
		});
	}
	
	/*增加规格*/
	var $addSpecificationProduct = $("#addSpecificationProduct");
	var $specificationProductTable = $("#specificationProductTable");
	$addSpecificationProduct.click(function() {
		
		$specificationProductTable.find("tr:eq(1)").clone().show().appendTo($specificationProductTable);
		$specificationProductTable.find('.deleteSpecificationProduct').unbind().click(function(){
		    if($specificationProductTable.find('tr').length > 2){
				var $this = $(this);
				$this.closest("tr").remove();
			}
		});
	});
	/*删除规格*/
	var $deleteSpecificationProduct = $("a.deleteSpecificationProduct");
	$deleteSpecificationProduct.bind("click", function() {
		if($specificationProductTable.find('tr').length > 2){
			var $this = $(this);
			$this.closest("tr").remove();
		}
	});
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
	<div class="path">
		<a href="${base}/admin/index.do">首页</a> &raquo; 添加商品
	</div>
	<form id="inputForm" action="${base}/admin/product/add.do" method="post">
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
			<tr>
				<th>
					商品分类:
				</th>
				<td>
					<select id="productCategoryId" name="productCategoryId">
					<#list categoryList as category>
						<option value="${category.id}" ><#if category.parent??>&nbsp;&nbsp;</#if> ${category.name} </option>
					</#list>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品类型:
				</th>
				<td>
					<fieldset>
						<label>
							<input type="radio" name="type" value="1" checked="checked">普通商品
						</label>
						<label>
							<input type="radio" name="type" value="2">联通手机卡
						</label>
						<label>
							<input type="radio" name="type" value="3">联通上网卡
						</label>
						<label>
							<input type="radio" name="type" value="5">海淘商品
						</label>
					</fieldset>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>销售价:
				</th>
				<td>
					<input type="text" name="price" class="text" maxlength="16" />
				</td>
			</tr>
			<tr>
				<th>
					app专享价:
				</th>
				<td>
					<input type="text" name="app_price" class="text" maxlength="16" />
					<span>在Android, IOS客户端购买时的价格; 如未设置则跟销售价一致</span>
				</td>
			</tr>
			<tr>
				<th>
					成本价:
				</th>
				<td>
					<input type="text" name="cost" class="text" maxlength="16" title="前台不会显示，仅供后台使用"  />
				</td>
			</tr>
			<tr>
				<th>
					市场价:
				</th>
				<td>
					<input type="text" name="marketPrice" class="text" maxlength="16" title="若留空则由系统自动计算"  />
					<span>当市场价为0时, 无论是否开启市场价显示, 前台都不显示</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品主图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="images" class="text" maxlength="200" title="应用于首页、宣传页，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品中图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="mediumImage" class="text" maxlength="200" title="应用于首页、宣传页，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>商品缩略图:
				</th>
				<td>
					<span class="fieldSet">
						<input type="text" name="thumbnail" class="text" maxlength="200" title="应用于列表页、购物车, 订单，留空则由系统自动生成" />
						<input type="button" class="button browserButton" value="选择文件" />
						<a href="#" target="_blank">查看</a>
					</span>
				</td>
			</tr>
			<tr>
				<th>
					单位:
				</th>
				<td>
					<input type="text" name="unit" class="text" value="件" maxlength="200"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>重量:
				</th>
				<td>
					<input type="text" name="weight" class="text" maxlength="9" title="单位: 克"  />
					<span>单位: 克; 此值用于计算运费</span>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>库存:
				</th>
				<td>
					<input type="text" name="stock" class="text" value="0" maxlength="9" title="有库存商品才能购买" />
					初始库存
				</td>
			</tr>
			<tr>
				<th>
					赠送积分:
				</th>
				<td>
					<input type="text" name="point" class="text" value="0" maxlength="9" title="若留空则由系统自动计算" />
				</td>
			</tr>
			<tr>
				<th>
					品牌:
				</th>
				<td>
					<select name="brandId">
						<option value="">请选择...</option>
					<#list brandList as brand>
						<option value=${brand.id}>${brand.name}</option>
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
							<input type="checkbox" name="tagIds" value="${tag.id}" />${tag.name}
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
						<input type="checkbox" name="isMarketable" value="true" />是否上架
					</label>
					<label>
						<input type="checkbox" name="isList" value="true"  />是否列出
					</label>
				</#if>
					<label>
						<input type="checkbox" name="isTop" value="true"  />是否置顶
					</label>
					<label>
						<input type="checkbox" name="isGift" value="true" />是否为赠品
					</label>
					<label>
						<input type="checkbox" name="freePost" value="true" />包邮
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
						<option value="${key}">${warehouses[key]}</option>
					</#list>
					</select>
					<input type="text" name="memo" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					搜索关键词:
				</th>
				<td>
					<input type="text" name="keyword" class="text" maxlength="200" title="应用于前台商品搜索，多个关键字以(,)分隔" />
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面关键词:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200"  title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
		</table>
		<table class="input tabContent">
			<tr>
				<td>
					<textarea id="editor" name="description" class="editor" style="width: 100%;"><#if product??>${product.description}</#if></textarea>
				</td>
			</tr>
		</table>
		<!--增加图片-->
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
		</table>
		<!--商品参数-->
		<table id="parameterTable" class="input tabContent">
	<#if categoryList?has_content>
		<#assign ctg=categoryList[0]>
		<#list ctg.groups as group>
		<tr>
			<td style="text-align:right; padding-right:10px;">
				<strong>${group.name}:</strong>
			</td>
			<td>&nbsp;<td/>
		</tr>
			<#list group.parameters as param>
			<tr>
				<th>${param.name}</th>
				<td>
					<input type="hidden" name="paramId" value="${param.id}" />
					<input type="text" name="param" class="text" maxlength="200" />
				</td>
			</tr>
			</#list>
		</#list>		
	</#if>
		</table>
		<!--商品属性-->
		<table id="attributeTable" class="input tabContent">
		
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
						<#list specifications as specification>
							<ul>
								<li>
									<label>
										<input type="checkbox" name="specificationIds" value="${specification.id}" />${specification.name}
									</label>
								</li>
							</ul>
						</#list>
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
								<td>
									操作
								</td>
							</tr>
							<tr>
								<td width="60">
									当前规格
								</td>
								<td>
									<a href="javascript:;" class="deleteSpecificationProduct">删除</a>
								</td>
							</tr>
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
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/product/list.do'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>