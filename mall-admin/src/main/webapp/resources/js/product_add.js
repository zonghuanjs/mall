
$(function(){
	
	/*增加图片*/
	var $addProductImage = $("#addProductImage");
	var $productImageTable = $("#productImageTable");
	var $productCategoryId = $('#productCategoryId');
	var cache = {};
	
	//参数动态加载
	$productCategoryId.bind('change', function(){
		var $selected = $(this);
		var $parameterTable = $('#parameterTable');
		if($selected.val() > 0){
			var key = 'data_parameters_' + $selected.val();
			if(cache[key] == null){
				$.ajax({
					url: 'queryParamters.do',
					type: 'post',
					data: {id: $selected.val()},
					dataType: 'json',
					success: function(data){
						cache[key] = data;
						createParameterList(data);
					}
				});
			}else{
				createParameterList(cache[key]);
			}
		}else{
			$parameterTable.html('');
		}
		//创建参数列表
		function createParameterList(data){
			var paramContent = '';
			for(var i = 0; i < data.length; i++){
				var $groupName = '<tr><td style="text-align:right; padding-right:10px;"><strong>' + data[i].groupName + ':</strong></td><td>&nbsp;</td></tr>';
				var $parameters = '';
				var parameters = data[i].parameters;
				for(var k=0; k < parameters.length; k++){
					$parameters += '<tr><th>' + parameters[k].name + '</th>';
					$parameters += '<td><input type="hidden" name="paramId" value="' + parameters[k].id + '" /><input type="text" name="param" class="text" maxlength="200" /></td></tr>';
				}
				paramContent += $groupName + $parameters;
			}
			$parameterTable.html(paramContent);
		}
	});
	
	$addProductImage.click(function(){
		var trHtml = '<tr> <td> <input type="text" name="image" class="text" maxlength="200"\/><input type="button" class="button browserButton" value="选择文件" \/><a href="" target="_blank">查看</a></td> <td> <input type="text" name="imageTitle" class="text" maxlength="200" \/> <\/td> <td> <input type="text" name="imageOrder" class="text productImageOrder" value="0" maxlength="9" style="width: 50px;" \/> <\/td> <td> <a href="javascript:;" class="deleteProductImage">[删除]<\/a> <\/td> <\/tr>';
		$productImageTable.append(trHtml);
		$productImageTable.find('.browserButton').unbind().browseDialog();
		$productImageTable.find('.deleteProductImage').unbind().click(function(){
			var $this = $(this);
			$this.closest("tr").remove();
		});
	});
	
	/*删除图片*/
	var $deleteProductImage = $("a.deleteProductImage");
	$deleteProductImage.bind("click", function(){
		var $this = $(this);
		$this.closest("tr").remove();
	});
	
	//动态加载规格
	var $specificationIds = $('#specificationSelect').find('input[name=specificationIds]');
	$specificationIds.bind('change', function(){
		var $selectedValue = $(this);
		
		var $specificationProductTable = $('#specificationProductTable');
		var $specificationTitle = $specificationProductTable.find('tr.title');
		
		if($selectedValue.is(':checked')){
			var key = 'data_spes_' + $selectedValue.val();
			if(cache[key] == null){
				$.ajax({
					url: 'querySpecificationValue.do',
					type: 'post',
					data: {id: $selectedValue.val()},
					dataType: 'json',
					success: function(data){
						cache[key] = data;
						createSpecificationValueList(data);
					}
				});
			}else{
				createSpecificationValueList(cache[key]);
			}
			//创建规格
			function createSpecificationValueList(data){
				
				var $titleInsertPosition = $specificationTitle.find('td').eq(0);
				var $specificationName = $('<th class="spe" ' + 'specification_id="' + data.id + '" style="display:table-cell;">' + data.name + '</th>').insertAfter($titleInsertPosition);
				
				//添加规格值
				var $specificationInsertPosition;
				var $specification = $('<td class="specification"></td>');
				var $valueSelect = $('<select name="specification_' + data.id + '" class="valid"></select>').appendTo($specification);
				
				for(var i = 0; i < data.values.length; i++){
					var $option = $('<option value="' + data.values[i].id + '" >' + data.values[i].name + '</option>').appendTo($valueSelect);
				}
				var $rows = $specificationProductTable.find('tr:gt(0)');
				$rows.each(function(){
					var $row = $(this);
					var $insertPosition = $row.find('td').eq(0);
					$specification.clone().show().insertAfter($insertPosition);
				});
			}
		}else{
			//移出规格
			var $currentSpecification = $specificationTitle.find('th[specification_id=' + $selectedValue.val() + ']');
			var idx = $specificationTitle.find('th').index($currentSpecification) + 1;
			$currentSpecification.remove();
			$specificationProductTable.find('tr:gt(0)').each(function(){
				var $row = $(this);
				$row.find('td').eq(idx).remove();
			});
		}
	}); 
});
