<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>到货通知列表</title>
<link type="text/css" rel="stylesheet" href="${base}/resources/admin/css/list.css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<script type="text/javascript">
$().ready(function() {
	
	var $listForm = $("#listForm");
	var $selectAll = $("#selectAll");
	var $ids = $("#listTable input[name='ids']");
	var $sendButton = $("#sendButton");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
	
	// 发送到货通知
	$sendButton.click(function() {
		if ($sendButton.hasClass("disabled")) {
			return false;
		}
		var $checkedIds = $ids.filter(":enabled:checked");
		$.dialog({
			type: "warn",
			content: "您确定要发送到货通知吗？",
			ok: "确&nbsp;&nbsp;定",
			cancel: "取&nbsp;&nbsp;消",
			onOk: function() {
				$.ajax({
					url: "send.jhtml",
					type: "POST",
					data: $checkedIds.serialize(),
					dataType: "json",
					cache: false,
					success: function(message) {
						$.message(message);
						if (message.type == "success") {
							$checkedIds.closest("td").siblings(".hasSent").html('<span title="已发送" class="trueIcon">&nbsp;<\/span>');
						}
					}
				});
			}
		});
	});
	
	// 全选
	$selectAll.click(function() {
		var $this = $(this);
		var $enabledIds = $ids.filter(":enabled");
		if ($this.prop("checked")) {
			if ($enabledIds.filter(":checked").size() > 0) {
				$sendButton.removeClass("disabled");
			} else {
				$sendButton.addClass("disabled");
			}
		} else {
			$sendButton.addClass("disabled");
		}
	});
	
	// 选择
	$ids.click(function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$sendButton.removeClass("disabled");
		} else {
			if ($ids.filter(":enabled:checked").size() > 0) {
				$sendButton.removeClass("disabled");
			} else {
				$sendButton.addClass("disabled");
			}
		}
	});
	
	// 到货通知筛选
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
	
	// 到货通知筛选
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
		<a href="/admin/common/index.jhtml">首页</a> &raquo; 到货通知列表 <span>(共<span id="pageTotal">132</span>条记录)</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<input type="hidden" id="isMarketable" name="isMarketable" value="" />
		<input type="hidden" id="isOutOfStock" name="isOutOfStock" value="" />
		<input type="hidden" id="hasSent" name="hasSent" value="" />
		<div class="bar">
			<div class="buttonWrap">
				<a href="javascript:;" id="sendButton" class="button disabled">
					发送到货通知
				</a>
				<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>删除
				</a>
				<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>刷新
				</a>
				<div class="menuWrap">
					<a href="javascript:;" id="filterSelect" class="button">
						到货通知筛选<span class="arrow">&nbsp;</span>
					</a>
					<div class="popupMenu">
						<ul id="filterOption" class="check">
							<li>
								<a href="javascript:;" name="isMarketable"  val="true">已上架</a>
							</li>
							<li>
								<a href="javascript:;" name="isMarketable"  val="false">未上架</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="isOutOfStock"  val="true">缺货</a>
							</li>
							<li>
								<a href="javascript:;" name="isOutOfStock"  val="false">有货</a>
							</li>
							<li class="separator">
								<a href="javascript:;" name="hasSent"  val="true">已发送</a>
							</li>
							<li>
								<a href="javascript:;" name="hasSent"  val="false">未发送</a>
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
							<a href="javascript:;" val="email">E-mail</a>
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
					<span>商品名称</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="member">会员</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="email">E-mail</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">登记日期</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="modifyDate">通知日期</a>
				</th>
				<th>
					<span>状态</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="hasSent">已发送</a>
				</th>
			</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="140" />
					</td>
					<td>
						<a href="/product/content/201306/30.html" title="梵希蔓 2013夏装新款女装女裙子长款雪纺百褶连衣裙韩版修身裙子[绿色 S]" target="_blank">梵希蔓 2013夏装新款女装女裙子长款雪纺百褶连衣裙韩版...</a>
					</td>
					<td>
							-
					</td>
					<td>
						miaoshuidong@163.com
					</td>
					<td>
						<span title="2014-09-23 17:26:08">2014-09-23</span>
					</td>
					<td>
							-
					</td>
					<td>
							<span class="green">已上架</span>
							缺货
					</td>
					<td class="hasSent">
						<span title="未发送" class="falseIcon">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="139" />
					</td>
					<td>
						<a href="/product/content/201306/30.html" title="梵希蔓 2013夏装新款女装女裙子长款雪纺百褶连衣裙韩版修身裙子[绿色 S]" target="_blank">梵希蔓 2013夏装新款女装女裙子长款雪纺百褶连衣裙韩版...</a>
					</td>
					<td>
							-
					</td>
					<td>
						657418802@77.com
					</td>
					<td>
						<span title="2014-09-23 15:12:58">2014-09-23</span>
					</td>
					<td>
							-
					</td>
					<td>
							<span class="green">已上架</span>
							缺货
					</td>
					<td class="hasSent">
						<span title="未发送" class="falseIcon">&nbsp;</span>
					</td>
				</tr>
				
		
		</table>
        <#include "../common/pager.ftl">
	</form>
</body>
</html>