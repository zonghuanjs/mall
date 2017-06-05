<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加文章</title>

<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css"/>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.tab.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/js/article.js"></script>
<script type="text/javascript" src="${base}/resources/editor/kindeditor.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 添加文章分类
	</div>
	<form id="inputForm" action="<#if article??>save.do?id=${article.id}<#else>add.do</#if>" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="基本信息" />
			</li>
			<li>
				<input type="button" value="文章资源" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>标题:
				</th>
				<td>
					<input type="text" name="title" class="text" maxlength="200" title="必填，文章标题" <#if article??>value="${article.title}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>文章分类:
				</th>
				<td>
					<select name="category" title="文章的所属分类">
					    <#list articleCategoryList as category>
					    <option value="${category.id}" <#if article??&&category.id==article.category.id>selected</#if>>
								${category.name}
						</option>
					    </#list>    						
					</select>
				</td>
			</tr>
			<tr>
				<th>
					作者:
				</th>
				<td>
					<input type="text" name="author" class="text" maxlength="200" <#if article??>value="${article.author!""}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					设置:
				</th>
				<td>
					<label>
						<input type="checkbox" name="publication" value="true" <#if article??&&article.publication==false><#else>checked="checked"</#if> />是否发布
						<input type="hidden" name="publication" value="false" />
					</label>
					<label>
						<input type="checkbox" name="top" value="true" <#if article??&&article.top==false><#else>checked="checked"</#if>/>是否置顶
						<input type="hidden" name="top" value="false" />
					</label>
				</td>
			</tr>
			<tr>
				<th>
					标签:
				</th>
				<td>
				      <#list tagList as tag>
						<label>
							<input type="checkbox" name="tagIds" value="${tag.id}" <#if article?? &&  article.tags?seq_contains(tag)>checked="checked"</#if>/>${tag.name}
						</label>
					  </#list>	
				</td>
			</tr>
			<tr>
				<th>
					内容:
				</th>
				<td>
					<textarea id="editor" name="content" class="editor"><#if article??>${article.content}</#if></textarea>
				</td>
			</tr>
			<tr>
				<th>
					页面标题:
				</th>
				<td>
					<input type="text" name="seoTitle" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if article??>value="${article.seoTitle}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					页面关键词:
				</th>
				<td>
					<input type="text" name="seoKeywords" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if article??>value="${article.seoKeywords}"</#if>/>
				</td>
			</tr>
			<tr>
				<th>
					页面描述:
				</th>
				<td>
					<input type="text" name="seoDescription" class="text" maxlength="200" title="应用于SEO搜索引擎优化" <#if article??>value="${article.seoDescription}"</#if>/>
				</td>
			</tr>
		</table>
		<!--文章资源-->
		<table id="articleResources" class="input tabContent">
			<tr>
				<td colspan="4">
				<p style="float:left;">上传文件类型：</span><select name="resourcesType" id="resourcesType" style="width:60px;line-height:25px;height:25px;">
						<option value="image">图片</option>
						<option value="file">文件</option>
				         </select>
				&nbsp;&nbsp;</p><a href="javascript:;" id="addProductResource" class="button">增加资源</a>
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
			<#if article??&&article.resources??>
			<#list article.resources?sort_by("orders") as resource>
			  <tr>
				<td><input type="hidden" name="resourceId" value="${resource.id}" />
				<input type="text" name="resourceUrl_${resource.id}" class="text" maxlength="200" value="${resource.url}"/>
				<input type="button" class="button browserButton" value="选择文件" /><a href="${base}${resource.url}" target="_blank">查看</a></td> 
				<td> <input type="text" name="resourceTitle_${resource.id}" class="text" maxlength="200" value="${resource.title!''}"/> </td> 
				<td> <input type="text" name="resourceOrder_${resource.id}" class="text productImageOrder" maxlength="9" style="width: 50px;" value="${resource.orders!'0'}"/> </td>
				<td> <a href="javascript:;" class="deleteResource">[删除]</a> </td> 
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
					<input type="submit" class="button" value="确&nbsp;&nbsp;定" />
					<input type="button" class="button" value="返&nbsp;&nbsp;回" onclick="location.href='javascript:history.go(-1)'" />
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>