$(function(){
	var $rulesTable = $('#rulesTable');
	var $addRule = $('#addRule');
	var methodId = $('#methodId').val();
	var ruleSaveUrl = 'add_rule.do';
	var cache = {};
	
	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
		},
		messages: {
			name: "必填",				
		}
	});
	
	$rulesTable.find('tr.parameterTr').each(function(){
		var $row = $(this);
		var $selectButton = $row.find('.select');
		var $deleteButton = $row.find('.delete');
		var $saveButton = $row.find('.save');
		
		$selectButton.click(function(){
			selectAreas($selectButton);
		});
		
		$deleteButton.click(function(){
			$.dialog({
				type: 'warn',
				content: '确定要删除所选记录?',
				ok: '确定',
				cancel: '取消',
				onOk: function(){
					deleteRule($deleteButton);
				}
			});
		});
		
		$saveButton.click(function(){
			saveRule($saveButton);
		});
	});
	
	$addRule.click(function(){
		var $ruleRow = $('<tr class="parameterTr"><td></td><td class="area"></td></tr>');
		var $firstWeightCell = $('<td></td>').appendTo($ruleRow);
		var $firstWeight = $('<input type="text" name="firstWeight" value="0" />').appendTo($firstWeightCell);
		var $firstPriceCell = $('<td></td>').appendTo($ruleRow);
		var $firstPrice = $('<input type="text" name="firstPrice" value="0" />').appendTo($firstPriceCell);
		var $continueWeightCell = $('<td></td>').appendTo($ruleRow);
		var $continueWeight = $('<input type="text" name="continueWeight" value="0" />').appendTo($continueWeightCell);
		var $continuePriceCell = $('<td></td>').appendTo($ruleRow);
		var $continuePrice = $('<input type="text" name="continuePrice" value="0" />').appendTo($continuePriceCell);
		var $buttonCell = $('<td></td>').appendTo($ruleRow);
		var $selectButton = $('<a href="javascript:;" class="button" >选择地区</a>').appendTo($buttonCell);
		var $saveButton = $('<a href="javascript:;" class="button" >保存</a>').appendTo($buttonCell);
		var $deleteButton = $('<a href="javascript:;" class="button" >删除</a>').appendTo($buttonCell);
		
		$ruleRow.appendTo($rulesTable);
		
		$deleteButton.click(function(){
			var $ruleId = $ruleRow.find('td').eq(0).find(':input');
			if($ruleId.length > 0){
				$.dialog({
					type: 'warn',
					content: '确定要删除所选记录?',
					ok: '确定',
					cancel: '取消',
					onOk: function(){
						deleteRule($deleteButton);
					}
				});
			}else{
				$ruleRow.remove();
			}
		});
		
		$selectButton.click(function(){
			selectAreas($selectButton);
		});
		
		$saveButton.click(function(){
			var $ruleId = $ruleRow.find('td').eq(0).find(':input');
			if($ruleId.length > 0){
				saveRule($saveButton);
			}else{
				var $checkboxs = $ruleRow.find('td').eq(1).find(':checkbox').filter(':checked');
				var areaIds = new Array();
				$.each($checkboxs, function(idx, cbx){
					areaIds[idx] = $(cbx).val();
				});
				
				$.ajax({
					url: ruleSaveUrl,
					data: {
						methodId: methodId,
						firstWeight: $firstWeight.val(),
						firstPrice: $firstPrice.val(),
						continueWeight: $continueWeight.val(),
						continuePrice: $continuePrice.val(),
						areas: areaIds.join(';')
					},
					type: 'post',
					dataType: 'json',
					success: function(data){
						if(data.errCode == 0){
							var $firstCell = $ruleRow.find('td').eq(0);
							var $ruleId = $('<input type="hidden" name="ruleId" />').appendTo($firstCell);
							$ruleId.val(data.ruleId);
							alert('保存成功!');
						}else{
							alert('保存失败!');
						}
					}
				});
			}
		});
	});
	
	//地区选择
	function selectAreas(button){
		var $thisButton = $(button);
		var $areaCell = $thisButton.closest('tr').find('td.area');
		
		var $areaContainer = $('<div></div>');
		var area_key = 'area_key';
		if(cache[area_key] == null){
			$.ajax({
				url: '../../common/areaQuery.do',
				data: {},
				type: 'post',
				dataType: 'json',
				success: function(data){
					cache[area_key] = data;
					showAreaList($areaContainer, cache[area_key]);
				}
			});
		}else{
			showAreaList($areaContainer, cache[area_key]);
		}
		
		$.dialog({
			title: '选择目标地区',
			content: $areaContainer,
			width: '500',
			ok: '完成',
			cancel: '取消',
			onOk: function(dialog){
				var $selectAreas = $areaContainer.find(':checkbox').filter(':checked');
				$areaCell.html('');
				
				$selectAreas.each(function(){
					var $this = $(this);
					var $label = $this.closest('label');
					$label.appendTo($areaCell);
				});
			}
		});
	}
	
	//显示地区列表
	function showAreaList(container, data){
		var $container = $(container);
		var $ul = $('<ul style="list-style: none; margin: 0px; padding: 0px"></ul>').appendTo($container);
		$.each(data, function(value, name){
			var $li = $('<li style="width: 150px; float: left"></li>').appendTo($ul);
			var $label = $('<label></label>').appendTo($li);
			var $checkbox = $('<input type="checkbox" name="area" />').appendTo($label);
			$checkbox.val(value);
			$label.append(name);
		});
		$container.append('<div style="clear: both; height: 1px"></div>');
	}
	
	//删除规则
	function deleteRule(button){
		var $thisButton = $(button);
		var ruleId = $thisButton.closest('tr').find('td').eq(0).find(':input').val();
		
		$.ajax({
			url: 'delete_rule.do',
			data: {ruleId: ruleId},
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data == true){
					$thisButton.closest('tr').remove();
				}else{
					alert('删除失败!');
				}
			}
		});
	}
	
	//保存规则
	function saveRule(button){
		var $thisButton = $(button);
		var $row = $thisButton.closest('tr');
		var $ruleId = $row.find('td').eq(0).find(':input');
		
		if($ruleId.length > 0){
			var $checkboxs = $row.find('td').eq(1).find(':checkbox').filter(':checked');
			var areaIds = new Array();
			$.each($checkboxs, function(idx, cbx){
				areaIds[idx] = $(cbx).val();
			});
			
			var $firstWeight = $row.find('td').eq(2).find(':input');
			var $firstPrice = $row.find('td').eq(3).find(':input');
			var $continueWeight = $row.find('td').eq(4).find(':input');
			var $continuePrice = $row.find('td').eq(5).find(':input');
			
			$.ajax({
				url: 'save_rule.do',
				data: {
					ruleId: $ruleId.val(),
					firstWeight: $firstWeight.val(),
					firstPrice: $firstPrice.val(),
					continueWeight: $continueWeight.val(),
					continuePrice: $continuePrice.val(),
					areas: areaIds.join(';')
				},
				type: 'post',
				dataType: 'json',
				success: function(data){
					if(data == true){
						alert('保存成功!');
					}else{
						alert('保存失败');
					}
				}
			});
		}
	}
});