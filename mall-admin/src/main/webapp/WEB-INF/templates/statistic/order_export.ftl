<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心-订单导出</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css"/>
<script type="text/javascript" src="${base}/admin/js/js!settings.do"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
    var $exportButton = $("#exportButton");
	


	
	// 订单筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});
	
	
	

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/home.do">首页</a> &raquo; 订单导出
	</div>
	<div class="bar">
		<div class="buttonWrap">
			<form action="${base}/admin/export_order.do" method="get" target="_blank">
				起始日期: <input type="text" id="beginDate" name="beginDate" class="text Wdate" onfocus="WdatePicker();" />
				结束日期: <input type="text" id="endDate" name="endDate" class="text Wdate" onfocus="WdatePicker();" />
				<input type="submit" class="button" value="导&nbsp;&nbsp;出" />
			</form>
		</div>
	</div>
	<div>
		<h3>注意事项</h3>
		<p>1.起始日期与结束日期都没选择将导出最近20天的数据.</p>
		<p>2.只选择起始日期但未选择结束日期将导出起始日期到当前时间的订单数据.</p>
		<p>3.只选择结束日期但未选择起始日期将导出结束日期前20天的数据.</p>		
	</div>	
</body>
</html>