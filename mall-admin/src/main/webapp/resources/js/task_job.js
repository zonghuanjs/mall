
function doTask(id){
	if(!id){
		return;
	}
	
	$.dialog({
		type: 'warn',
		content: '确定要立即执行该任务？',
		ok: '执行',
		cancel: '不执行',
		onOk: function(dialog){
			$.ajax({
				url: 'do_task.do',
				type: 'post',
				data: {id: id},
				dataType: 'json',
				success: function(){
					alert('执行完毕!');
				}
			});
		}
	});
}

function startTask(id){
	if(!id){
		return;
	}
	
	$.dialog({
		type: 'warn',
		content: '确定要立即启动该任务？',
		ok: '启动',
		cancel: '不启动',
		onOk: function(dialog){
			$.ajax({
				url: 'start_task.do',
				type: 'post',
				data: {id: id},
				dataType: 'json',
				success: function(){
					location.reload(true);
				}
			});
		}
	});
}

function stopTask(id){
	if(!id){
		return;
	}
	
	$.dialog({
		type: 'warn',
		content: '确定要立即停用该任务？',
		ok: '停用',
		cancel: '不停用',
		onOk: function(dialog){
			$.ajax({
				url: 'stop_task.do',
				type: 'post',
				data: {id: id},
				dataType: 'json',
				success: function(){
					location.reload(true);
				}
			});
		}
	});
}