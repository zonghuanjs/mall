
$().ready( function() {

	var $listForm = $("#listForm");
	var $deleteButton = $("#deleteButton");
	var $refreshButton = $("#refreshButton");
	var $pageSizeSelect = $("#pageSizeSelect");
	var $pageSizeOption = $("#pageSizeOption a");
	var $moreOperation = $("#moreOperation");
	var $searchPropertySelect = $("#searchPropertySelect");
	var $searchPropertyOption = $("#searchPropertyOption a");
	var $searchValue = $("#searchValue");
	var $selectAll = $("#selectAll");
	var $ids = $("#listTable input[name='ids']");
	var $contentRow = $("#listTable tr:gt(0)");
	var $sort = $("#listTable a.sort");
	var $pageSize = $("#pageSize");
	var $searchProperty = $("#searchProperty");
	var $orderProperty = $("#orderProperty");
	var $orderDirection = $("#orderDirection");
	var $pageNumber = $("#pageNumber");
	var $searchButton = $("#searchButton");
	
	$searchButton.click(function(){
		$pageNumber.val("1");
		$listForm.submit();
		return false;
	});
	
	$deleteButton.click(function(){
		var $this = $(this);
		if($this.hasClass('disabled')){
			return false;
		}
		
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		$.dialog({
			type: 'warn',
			content: '确定要删除所选记录?',
			ok: '确定',
			cancel: '取消',
			onOk: function(){
				$.ajax({
					url: 'delete.do',
					type: 'post',
					data: $checkedIds.serialize(),
					dataType: 'json',
					cache: false,
					success: function(data){
						try{
							if(data.errCode == 0){
								window.location.reload(true);
							}else{
								alert('删除失败!');
							}
						}catch(e){
							alert(e);
						}
					}
				});
			}
		});
	});
	
	$refreshButton.click( function() {
		location.reload(true);
		return false;
	});
	
	$pageSizeSelect.mouseover( function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$pageSizeOption.click( function() {
		var $this = $(this);
		$pageSize.val($this.attr("val"));
		$pageNumber.val("1");
		$listForm.submit();
		return false;
	});
	
	$moreOperation.mouseover( function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$searchPropertySelect.mouseover( function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left - 1, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	$searchPropertyOption.click( function() {
		var $this = $(this);
		$searchProperty.val($this.attr("val"));
		$searchPropertyOption.removeClass("current");
		$this.addClass("current");
		return false;
	});
	
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		if ($this.prop("checked")) {
			$enabledIds.prop("checked", true);
			if ($enabledIds.filter(":checked").size() > 0) {
				$deleteButton.removeClass("disabled");
				$contentRow.addClass("selected");
			} else {
				$deleteButton.addClass("disabled");
			}
		} else {
			$enabledIds.prop("checked", false);
			$deleteButton.addClass("disabled");
			$contentRow.removeClass("selected");
		}
	});
	
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {
			$this.closest("tr").addClass("selected");
			$deleteButton.removeClass("disabled");
		} else {
			$this.closest("tr").removeClass("selected");
			if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
				$deleteButton.removeClass("disabled");
			} else {
				$deleteButton.addClass("disabled");
			}
		}
	});
	
	$sort.click( function() {
		var orderProperty = $(this).attr("name");
		if ($orderProperty.val() == orderProperty) {
			if ($orderDirection.val() == "asc") {
				$orderDirection.val("desc");
			} else {
				$orderDirection.val("asc");
			}
		} else {
			$orderProperty.val(orderProperty);
			$orderDirection.val("asc");
		}
		$pageNumber.val("1");
		$listForm.submit();
		return false;
	});
	
	if ($orderProperty.val() != "") {
		$sort = $("#listTable a[name='" + $orderProperty.val() + "']");
		if ($orderDirection.val() == "asc") {
			$sort.removeClass("desc").addClass("asc");
		} else {
			$sort.removeClass("asc").addClass("desc");
		}
	}
	
	// 页码输入
	$(document).on("blur","#pageNumber",function(){
		var total = $("#pageCount").val();
		
		var value = $(this).val();
		if(parseInt(value) > parseInt(total)){
			$(this).val(total);
		}
		if(parseInt(value) <= 0){
			$(this).val(1);
		}
			
	});
	
	$listForm.submit(function() {
		if (!/^\d*[1-9]\d*$/.test($pageNumber.val())) {
			$pageNumber.val("1");
		}
		if ($searchValue.size() > 0 && $searchValue.val() != "" && $searchProperty.val() == "") {
			$searchProperty.val($searchPropertyOption.eq(0).attr("val"));
		}
	});
	
	$.pageSkip = function(pageNumber) {
		$pageNumber.val(pageNumber);
		$listForm.submit();
		return false;
	};
	
	
	
	if (location.search != "") {
		//addCookie("listQuery", location.search);
	} else {
		//removeCookie("listQuery");
	}
	
	
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
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
		$pageNumber.val("1");
		$listForm.submit();
		return false;
	});
	

});