$(function(){
	var $listForm = $("#listForm");
	var $batchButton = $("#batchButton");
	var $ids = $("#listTable input[name='ids']");
	var $selectAll = $("#selectAll");
	
	$batchButton.click(function(){
		var $this = $(this);
		if($this.hasClass('disabled')){
			return false;
		}
		
		var $checkedIds = $("#listTable input[name='ids']:enabled:checked");
		$.dialog({
			type: 'warn',
			content: '确定要为所选退款记录执行批量退款操作?',
			ok: '确定',
			cancel: '取消',
			onOk: function(){
				$.ajax({
					url: 'batch_refund.do',
					type: 'post',
					data: $checkedIds.serialize(),
					dataType: 'json',
					cache: false,
					success: function(data){
						if(data == true){
							location.href = 'alipay_list.do';
						}else{
							alert('执行批量退款失败!');
						}
					}
				});
			}
		});
	});
	
	$ids.click( function() {
		var $this = $(this);
		if ($this.prop("checked")) {			
			$batchButton.removeClass("disabled");
		} else {
			if ($("#listTable input[name='ids']:enabled:checked").size() > 0) {
				$batchButton.removeClass("disabled");
			} else {
				$batchButton.addClass("disabled");
			}
		}
	});
	
	$selectAll.click( function() {
		var $this = $(this);
		var $enabledIds = $("#listTable input[name='ids']:enabled");
		if ($this.prop("checked")) {
			if ($enabledIds.filter(":checked").size() > 0) {
				$batchButton.removeClass("disabled");
			} else {
				$batchButton.addClass("disabled");
			}
		} else {
			$batchButton.addClass("disabled");
		}
	});
});