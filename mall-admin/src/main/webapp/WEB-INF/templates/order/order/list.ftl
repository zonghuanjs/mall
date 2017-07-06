<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/css/list.css"/>
<script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${base}/resources/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/js/order_list_erp.js"></script>
</head>
<body>
	<#assign currency="cn.tekism.mall.freemarker.CurrencyMethod"?new()>
	<div class="path">
		<a href="${base}/home.do">首页</a> &raquo; 订单列表 <span>(共<span id="pageTotal">${pager.totalCount?c}</span>条记录)</span>
		<input type="hidden" id="basePath" value="${base}" />
	</div>
	<form id="listForm" action="" method="get">
		<input type="hidden" id="orderStatus" name="orderStatus" <#if orderStatus??>value="${orderStatus}" </#if>/>
		<input type="hidden" id="isInvoice" name="isInvoice" <#if isInvoice??>value="${isInvoice}" </#if>/>
		<input type="hidden" id="type" name="type" <#if type??>value="${type}" </#if>/>
		<input type="hidden" id="memo" name="memo" <#if memo??>value="${memo}" </#if>/>
		<div class="search_input">
			<label>
				<span>起始时间</span>
				<input type="text" class="text Wdate" id="beginDate" name="beginDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', maxDate: '#F{$dp.$D(\'endDate\')}'});" <#if beginDate?has_content>value="${beginDate}"</#if> />
			</label>
			<label>
				<span>结束时间</span>
				<input type="text" class="text Wdate" id="endDate" name="endDate" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'beginDate\')}'});" <#if endDate?has_content>value="${endDate}"</#if> />
			</label>
			<label>
				<span>字段名称</span>
				<select name="searchProperty">
					<option value="sn" <#if searchProperty??&&searchProperty == "sn">selected="true"</#if> >订单编号</option>
					<option value="consignee" <#if searchProperty??&&searchProperty == "consignee">selected="true"</#if> >收货人</option>
				</select>
			</label>
			<label>
				<span>关键字</span>
				<input type="text" class="text" name="searchValue" <#if searchValue?has_content>value="${searchValue}"</#if> />
			</label>
			<input id="searchButton" type="button" class="button" value="查询" />
		</div>
		<div class="bar">
			<div class="buttonWrap">
			<#if authorities?seq_contains('admin:order:delete')>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
			</#if>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<span id="erpButton" class="button disabled">录入ERP</span>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						订单筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="orderStatus" val="1" <#if orderStatus??&&orderStatus == "1"> class="checked"</#if>>未支付</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="2" <#if orderStatus??&&orderStatus == "2"> class="checked"</#if>>已支付</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="3" <#if orderStatus??&&orderStatus == "3"> class="checked"</#if>>待发货</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="4" <#if orderStatus??&&orderStatus == "4"> class="checked"</#if>>已发货</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="5" <#if orderStatus??&&orderStatus == "5"> class="checked"</#if>>待处理</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="6" <#if orderStatus??&&orderStatus == "6"> class="checked"</#if>>退货处理</a>
							</li>
							<li>
								<a href="javascript:;" name="orderStatus" val="0" <#if orderStatus??&&orderStatus == "0"> class="checked"</#if>>交易完成</a>
							</li>
							
							<li class="separator">
								<a href="javascript:;" name="isInvoice" val="true" <#if isInvoice??&&isInvoice == "true"> class="checked"</#if>>开发票</a>
							</li>	
							<li>
								<a href="javascript:;" name="isInvoice" val="false" <#if isInvoice??&&isInvoice == "false"> class="checked"</#if>>不开发票</a>
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
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="sn">订单编号</a>
				</th>
				<th>
					<span>订单金额</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="consignee">收货人</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="areaName">配送地区</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="inERP">已入ERP</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="orderStatus">订单状态</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">下单时间</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
		<#list orders as order>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${order.id}" />
					</td>
					<td>
						${order.sn}<#if order.type==3>&nbsp;<span class="red">抽奖</span></#if>
					</td>
					<td>
						${currency(order.amountPaid)}
					</td>
					<td>
						${order.member.username}
					</td>
					<td>
						${order.consignee}
					</td>
					<td>
						<#if order.areaName?has_content>${order.areaName}</#if>
					</td>
					<td>
						<span title="是否录入ERP">${order.inERP?string('已录入', '尚未录入')}</span>
					</td>
					<td>
						<#switch order.orderStatus>
                         <#case 1>
                                                                                                            未支付
                           <#break>
                         <#case 2>
                                                                                                            已支付 
                           <#break>
                         <#case 3>
                                                                                                           待发货
                           <#break>
                         <#case 4>
                                                                                                         已发货
                           <#break>
                         <#case 5>
                                                                                                         待处理
                           <#break>
                         <#case 6>退货处理<#break>
                         <#case 0>
                                                                                                         交易完成
                           <#break>
                         <#case -1>
                                                                                                         交易关闭
                           <#break>                          
                         <#default>
                                                                                                       处理中
                        </#switch>
						
					</td>			
					<td>
						<span title="${order.createDate}">${order.createDate}</span>
					</td>
						
					<td>
						<a href="${base}/order/view.do?id=${order.id}">[查看]</a>
					<#if authorities?seq_contains('admin:order:edit')>
						<#if order.orderStatus!=-1 && order.orderStatus!=0 >
						<a href="${base}/order/edit.do?id=${order.id}">[编辑]</a>
						</#if>
					</#if>
					</td>
				</tr>
		</#list>
		</table>
		<#include "../../common/pager.ftl">
	</form>
</body>
</html>