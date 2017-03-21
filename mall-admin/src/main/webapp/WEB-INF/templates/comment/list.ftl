<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>评论列表 </title>

<link rel="stylesheet" type="text/css" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 评论列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="#" method="get">
		<input type="hidden" id="type" name="type" <#if type??>value="${type}" </#if>/>
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						类型<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
								<li>
									<a href="javascript:;" name="type" val="1" <#if type??&&type == 1> class="checked"</#if>>好评</a>
								</li>
								<li>
									<a href="javascript:;" name="type" val="2" <#if type??&&type == 2> class="checked"</#if>>中评</a>
								</li>
								<li>
									<a href="javascript:;" name="type" val="3" <#if type??&&type == 3> class="checked"</#if>>差评</a>
								</li>
						</ul>
					</div>
				</div>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10" <#if pager.pageSize==10>class="current"</#if>>10</a>
							</li>
							<li>
								<a href="javascript:;" val="20" <#if pager.pageSize==20>class="current"</#if>>20</a>
							</li>
							<li>
								<a href="javascript:;" val="50" <#if pager.pageSize==50>class="current"</#if>>50</a>
							</li>
							<li>
								<a href="javascript:;" val="100" <#if pager.pageSize==100>class="current"</#if>>100</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" <#if searchValue??>value="${searchValue}"</#if> maxlength="200" />
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="content" <#if searchProperty??&&searchProperty == "content"> class="current"</#if>>内容</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="product">商品</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="score">评分</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="content">内容</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isShow">是否显示</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">创建日期</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#if comments?has_content>
			<#list comments as comment>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${comment.id}" />
					</td>
					<td>
						<#if comment.orderItem.product?has_content>
							<#assign product=comment.orderItem.product.id>
						</#if>
						<a href="${base}/product/product!${product!''}.do#comments" title="${comment.orderItem.fullName}" target="_blank">
							<#if comment.orderItem.fullName?length gt 20>${comment.orderItem.fullName[0..20]}...<#else>${comment.orderItem.fullName}</#if>
						</a>
					</td>
					<td>
						${comment.score!''}
					</td>
					<td>
						<span title="${comment.content}">
							<#if comment.content?length gt 20>${comment.content[0..20]}...<#else>${comment.content}</#if>
						</span>
					</td>
					<td>
							${comment.member.username}
					</td>
					<td>
						<#if !comment.deleted><span class="trueIcon"><#else><span class="falseIcon"></#if>&nbsp;</span>
					</td>
					<td>
						<span title="${comment.createDate}">${comment.createDate}</span>
					</td>
					<td>
						<a href="${base}/admin/comment/edit!${comment.id}.do">[编辑]</a>
					</td>
				</tr>
			</#list>	
			</#if>											
		</table>
		<#include "../common/pager.ftl">
	</form>
</body>
</html>