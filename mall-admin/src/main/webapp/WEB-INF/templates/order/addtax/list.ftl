<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>增值税发票列表 </title>

<link type="text/css" rel="stylesheet" href="${base}/resources/css/main.css" />
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css" />
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/select.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 增值税发票列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
	</div>
	<form id="listForm" action="" method="get">
	<input type="hidden" id="checkStatus" name="checkStatus" <#if checkStatus??>value="${checkStatus}" </#if>/>

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
						筛选条件<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">						
							<li>
								<a href="javascript:;" name="checkStatus" val="1" <#if checkStatus??&&checkStatus == "1"> class="checked"</#if>>审核通过</a>
							</li>
							<li>
								<a href="javascript:;" name="checkStatus" val="2" <#if checkStatus??&&checkStatus == "2"> class="checked"</#if>>审核不通过</a>
							</li>
							<li>
								<a href="javascript:;" name="checkStatus" val="0" <#if checkStatus??&&checkStatus == "0"> class="checked"</#if>>待审核</a>
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
							<a href="javascript:;" val="idNumber" <#if searchProperty??&&searchProperty == "idNumber"> class="current"</#if>>纳税人识别号</a>
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
					<a href="javascript:;" class="sort" name="title">纳税人识别号</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="adPosition">公司</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="type">地区</a>
				</th>				
				<th>
					<a href="javascript:;" class="sort" name="order">是否审核</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="order">审核状态</a>
				</th>				
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list addTaxList as addTax>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${addTax.id}" />
					</td>
					<td>
						${addTax.idNumber!""}
					</td>
					<td>
						${addTax.company!""}
					</td>
					<td>
						${addTax.registerArea.fullName!""}${addTax.registerAddress!""}
					</td>					
					
					<td>
					<#if addTax.checked>
						是
					<#else>
					             否
					</#if>
					</td>
					<td>
						<#switch addTax.checkStatus>
						<#case 1>
                                                                                                            审核通过
                           <#break>
                         <#case 2>
                                                                                                           审核不通过
                           <#break>
                         <#case 0>
                                                                                                           未审核
                           <#break>                                                
                         <#default>
                                                                                                       处理中
						</#switch>
					</td>
					<td>
						<a href="${base}/addTax/view.do?id=${addTax.id}">[查看]</a>				
					</td>
			</#list>	
				
		</table>
        <#include "../../common/pager.ftl">
	</form>
</body>
</html>