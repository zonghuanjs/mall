<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发货点列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {


});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 发货点列表 <span>(共<span id="pageTotal">1</span>条记录)</span>
	</div>
	<form id="listForm" action="${base}/admin/order/deliveryCenter.do" method="get">
		<div class="bar">
			<a href="${base}/admin/order/deliveryCenterAdd.do" class="iconButton">
				<span class="addIcon">&nbsp;</span>添加
			</a>
			<div class="buttonWrap">
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="pageSizeSelect" class="button">
						每页显示<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;" val="10">10</a>
							</li>
							<li>
								<a href="javascript:;" class="current" val="20">20</a>
							</li>
							<li>
								<a href="javascript:;" val="50">50</a>
							</li>
							<li>
								<a href="javascript:;" val="100">100</a>
							</li>
						</ul>
					</div>
				</div>	
			</div>
			<div class="menuWrap">
				<div class="search">
					<span id="searchPropertySelect" class="arrow">&nbsp;</span>
					<input type="text" id="searchValue" name="searchValue" value="" maxlength="200" />
					<button id="searchButton" type="button">&nbsp;</button>
				</div>
				<div class="popupMenu">
					<ul id="searchPropertyOption">
						<li>
							<a href="javascript:;" val="name">名称</a>
						</li>
						<li>
							<a href="javascript:;" val="contact">联系人</a>
						</li>
						<li>
							<a href="javascript:;" val="phone">电话</a>
						</li>
						<li>
							<a href="javascript:;" val="mobile">手机</a>
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
					<a href="javascript:;" class="sort" name="name">名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="contact">联系人</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="areaName">地区名称</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="address">地址</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="zipCode">邮编</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="phone">电话</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="mobile">手机</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="isDefault">是否默认</a>
				</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
				<tr>
					<td class="check">
						<input type="checkbox" name="ids" value="1"/>
					</td>
					<td>
						北京发货中心
					</td>
					<td>
						李小明
					</td>
					<td>
						北京市东城区
					</td>
					<td>
						<span title="新建安天坛东路888号">新建安天坛东路888号</span>
					</td>
					<td>
						100062
					</td>
					<td>
						010-88888888
					</td>
					<td>
						13888888888
					</td>
					<td>
						<span class="trueIcon">&nbsp;</span>
					</td>
					<td>
						<a href="edit.jhtml?id=1">[编辑]</a>
					</td>
				</tr>
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>