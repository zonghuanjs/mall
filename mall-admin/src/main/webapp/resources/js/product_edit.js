$(function(){
	
	/*增加图片*/
	var $addProductImage = $("#addProductImage");
	var $productImageTable = $("#productImageTable");
	var $productCategoryId = $('#productCategoryId');
	var cache = {};
	var parameterCache = {};
	var parameterValueCache = new Array();
	$("input[name=param]").each(function(index, val){
		parameterValueCache[index] = $(val).val();
	});
	$("th[name=paramName]").each(function(index, val){
		var val=$(val).html();
		parameterCache[val] = parameterValueCache[index];
	});
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
			var $fileUpload = '<tr><td colspan="2">上传文件：<input type="file" id="file" name="file"/><input type="button" class="button" id="fileUpload" value="导入"></td></tr>';
			paramContent += $fileUpload;
			for(var i = 0; i < data.length; i++){
				
				var $groupName = '<tr><td style="text-align:right; padding-right:10px;"><strong>' + data[i].groupName + ':</strong></td><td>&nbsp;</td></tr>';
				var $parameters = '';
				var parameters = data[i].parameters;
				for(var k=0; k < parameters.length; k++){
					var a =parameterCache[parameters[k].name];
					if(a == null || a==undefined){
						a="";
					}
						
					$parameters += '<tr><th name="paramName">' + parameters[k].name + '</th>';
					$parameters += '<td><input type="hidden" name="paramId" value="' + parameters[k].id + '" /><input type="text" name="param" class="text" maxlength="200" value="' + a + '"/></td></tr>';
				}
				paramContent += $groupName + $parameters;
			}
			$parameterTable.html(paramContent);
		}
	});
	
	$addProductImage.click(function(){
		var trHtml = '<tr> <td> <input type="text" name="image" class="text" maxlength="200"\/><input type="button" class="button browserButton" value="选择文件" \/><a href="" target="_blank">查看</a></td> <td> <input type="text" name="imageTitle" class="text" maxlength="200" \/> <\/td> <td> <input type="text" name="imageOrder" class="text productImageOrder" maxlength="9" value="0" style="width: 50px;" \/> <\/td> <td> <a href="javascript:;" class="deleteProductImage">[删除]<\/a> <\/td> <\/tr>';
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
				var $specificationName = $('<th ' + 'specification_id="' + data.id + '" style="display:table-cell;">' + data.name + '</th>').insertAfter($titleInsertPosition);
				
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
					var $productId = $row.attr('product_id');
					if($productId){
						$valueSelect.attr('name', 'specification_' + $productId + '_' + data.id);
					}
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
	
	/*增加规格*/
	var $addSpecificationProduct = $("#addSpecificationProduct");
	var $specificationProductTable = $("#specificationProductTable");
	$addSpecificationProduct.click(function() {
		
		var $newRow = $specificationProductTable.find("tr:eq(1)").clone().show().appendTo($specificationProductTable);
		$newRow.removeAttr("product_id");
		$newRow.find('td').eq(0).html('');
		$newRow.find('td:last').html('<a href="javascript:;" class="deleteSpecificationProduct">删除</a>');
		$newRow.find('select').each(function(){
			var $this = $(this);
			var selectId = $this.attr('name');
			var idx = selectId.lastIndexOf('_');
			selectId = 'specification_' + selectId.substring(idx + 1);
			$this.attr('name', selectId);
		});
		$specificationProductTable.find('.deleteSpecificationProduct').unbind().click(function(){
		    if($specificationProductTable.find('tr').length > 2){
				var $this = $(this);
				$this.closest("tr").remove();
			}
		});
	});
	/*删除规格*/
	var $deleteSpecificationProduct = $("a.deleteSpecificationProduct");
	$deleteSpecificationProduct.bind("click", function() {
		var $deleteSelfButton= $(this);
		$.dialog({
			type: 'warn',
			content: '确定要删除记录?',
			ok: '确定',
			cancel: '取消',
			onOk: function(){
				if($specificationProductTable.find('tr').length > 2){
					$deleteSelfButton.closest("tr").remove();
				}
			}
		});
	});
	
	var fileCache = {};
	$(document).on('click','#fileUpload',function(){
		var formData = new FormData();
		formData.append('file', $('#file')[0].files[0]);
		$.ajax({
		      type: "POST",
		      url: "addFile.do",
		      data: formData,
		      dataType: 'JSON', 
	          contentType: false,
	          cache: false,
	          processData: false,
		      success: function (data) {
		        if(data.errCode == 0){
		        	$.each(data.maps, function (index, obj) {
		        		fileCache[obj.key] = obj.value;
		            });
		        	$("input[name=param]").each(function(index, val){
		        		var $this = $(this);
		        		var paramName = $this.closest("tr").find("th[name=paramName]").html();
		        		$this.val(fileCache[paramName]);
		        	});
		        	alert("导入成功！");
		        }
		        else{
		        	alert("导入失败！");
		        }
		        	
		      }
		    });
	});
	
});