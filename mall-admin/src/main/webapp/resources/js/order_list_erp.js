$(function(){
	var $listForm = $('#listForm');
	var $erpButton = $('#erpButton');
	var $ids = $('#listTable input[name="ids"]');
	var $selectAll = $('#selectAll');
	var basePath = $('#basePath').val();
	var erpUrl = basePath + '/admin/erp/add_order.do';
	
	$erpButton.click(function(){
		var $this = $(this);
		if($this.hasClass('disabled')){
			return false;
		}
		
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		$.dialog({
			type: 'warn',
			content: '确定要将所选订单记录录入ERP订单?',
			ok: '确定',
			cancel: '取消',
			onOk: function(){
				$.ajax({
					url: erpUrl,
					type: 'post',
					data: $checkedIds.serialize(),
					dataType: 'json',
					cache: false,
					success: function(data){
						switch(data){
						case 0:
							{
								$.dialog({
									type: 'success',
									content: '录入ERP成功!',
									ok: '返回',
									onOk: function(){
										location.reload(true);
									}
								});
								break;
							}
						case 1:
							{
								$.dialog({
									type: 'error',
									content: '包含已录入ERP订单!'
								});
								break;
							}
						default:
							{
							$.dialog({
								type: 'error',
								content: 'ERP操作失败!'
							});
							}
						}
					}
				});
			}
		});
	});
	
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {			
			$erpButton.removeClass("disabled");
		} else {
			if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
				$erpButton.removeClass("disabled");
			} else {
				$erpButton.addClass("disabled");
			}
		}
	});
	
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		if ($this.prop("checked")) {
			if ($enabledIds.filter(":checked").size() > 0) {
				$erpButton.removeClass("disabled");
			} else {
				$erpButton.addClass("disabled");
			}
		} else {
			$erpButton.addClass("disabled");
		}
	});
	
});