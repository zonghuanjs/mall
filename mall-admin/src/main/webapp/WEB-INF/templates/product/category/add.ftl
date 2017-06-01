<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加商品分类</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css"/>
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/category.js"></script>
</head>
<body>
<div class="path">
		<a href="home.do">首页</a> &raquo; <#if category??>编辑<#else>添加</#if>商品分类
	</div>
	<form id="inputForm" action="<#if category??>save.do?id=${category.id}<#else>add.do</#if>" method="post">
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>名称:
				</th>
				<td>
					<input type="text" id="name" name="name" class="text" maxlength="200" <#if category??>value="${category.name}"</#if> title="必填项，名称"/>
				</td>
			</tr>
			
			<tr>
				<th>
					上级分类:
				</th>
				<td>
					<select name="parentId" title="商品分类的上级分类">
						<option value="">顶级分类</option>
					<#list categoryList as categorys>
						<option value="${categorys.id}" <#if category?? && category.parent?? && categorys == category.parent>selected</#if> > ${categorys.name} </option>
					</#list>
					</select>
				</td>
			</tr>
			
			<tr class="brands">
				<th>
					筛选品牌:
				</th>
				<td>
				<#if brands??>
					<#list brands as brand>
						<#if category?? && category.brands?seq_contains(brand)>
							<label>
								<input type="checkbox" name="brandIds" value="${brand.id}" checked/>${brand.name}
							</label>
						<#else>
							<label  style="display:none;">
								<input type="checkbox" name="brandIds" value="${brand.id}" />${brand.name}
							</label>
						</#if>
					</#list>
				</#if>
				<input type="button" class="showOther" onclick="showOther(this)" style="margin-left:50px;" value="添加其他"/>
				</td>
			</tr>
		
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" <#if category??>value="${category.seoTitle}"</#if> title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面关键词:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" <#if category??>value="${category.seoKeywords}"</#if> title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" <#if category??>value="${category.seoDescription}"</#if> title="应用于SEO搜索引擎优化"/>
				</td>
			</tr>
			<tr>
				<th>
					排序:
				</th>
				<td>
					<input type="text" name="order" id="order" class="text" maxlength="9" <#if category??>value="${category.orders}"</#if> title="排序，必须为整数"/><span class="tip"></span>
				</td>
			</tr>
			<tr>
				<th>
					筛选相关规格:
				</th>
				<td>
					<#list listSpec?sort_by("orders") as spec>
					    <#if category??&&category.specs?seq_contains(spec)>
					    	<div class="container" specid="${spec.id}">
						    	<span><input type="checkbox" name="specId" value="${spec.id}" class="cbheader" checked>${spec.name}&nbsp;----&nbsp;</span>
								<span>
									<#if spec.values??>
									(<#list spec.values?sort_by("orders") as value> 
										<#if category.specValues??&&category.specValues?seq_contains(value)>
											<span><input type="checkbox" name="specId_${spec.id}" value="${value.id}" checked>${value.name}</span>
										<#else>
											<span style="display:none;"><input type="checkbox" name="specId_${spec.id}" value="${value.id}">${value.name}</span>
										</#if>
									</#list>)
									<input type="button" class="showOther" onclick="showOtherCategory(this)" style="margin-left:20px;" value="添加其他"/></#if>
								</span>
							</div>
						<#else>	
							<div class="container" style="display:none;" specid="${spec.id}">
								<span><input type="checkbox" name="specId" value="${spec.id}" class="cbheader">${spec.name}&nbsp;----&nbsp;</span>
								<span>
									<#if spec.values??>
									(<#list spec.values?sort_by("orders") as value> 
										<span ><input type="checkbox" name="specId_${spec.id}" value="${value.id}">${value.name}</span>
									</#list>)
									<input type="button" class="showOther" onclick="showOtherCategory(this)" style="margin-left:20px;" value="添加其他"/></#if>
								</span>
								<br>
							</div>	
					    </#if>
					</#list>
					<br>
					<label><strong>新增筛选条件：</strong></label>
					<select title="规格列表" id="chooseSpec">
						<option value="" selected>--选择规格--</option>
						<#list listSpec?sort_by("orders") as spec>
							<option value="${spec.id}">&nbsp;${spec.name}</option>
						</#list>
					</select>
					
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='${base}/admin/productCategory/list.do'" />
				</td>
			</tr>
			<tr>
				<th>
					筛选参数
				</th>
				<td>
					<input type="button" id="addParameter" class="button" value="增加参数" />
				</td>
			</tr>
		</table>
		<table id="parameters" class="input">
			<tr>
				<th>参数名称</th>
				<th>操作</th>
			</tr>
		<#if category?has_content>
			<#list category.filterParameter as param>
			<tr>
				<td>
					<input type="text" name="parameter" value="${param}" />
				</td>
				<td>
					<span class="button opt">删除</span>
				</td>
			</tr>
			</#list>
		</#if>
		</table>
	</form>

</body>
</html>