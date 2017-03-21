
$(function(){
	
	var $inputForm = $('#inputForm');
	var $addProductResource = $('#addProductResource');
	var $resourcesType = $('#resourcesType');
	var $productResourcesTable = $('#productResources');
	var $copyButtons = $('a.copy');
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	$addProductResource.click(function(){
		var $resType = $resourcesType.val();
		var trHtml = '<tr> <td> <input type="text" name="resource" resType="' + $resType + '" class="text" maxlength="200"\/><input type="button" class="button browserButton" value="选择文件" \/><a href="" target="_blank">查看</a></td> <td> <input type="text" name="resourceTitle" class="text" maxlength="200" \/> <\/td> <td> <input type="text" name="resourceOrder" class="text productImageOrder" maxlength="9" value="0" style="width: 50px;" \/> <\/td> <td> <a href="javascript:;" class="deleteProductResource">[删除]<\/a> <\/td> <\/tr>';
		$productResourcesTable.append(trHtml);
		$productResourcesTable.find('.browserButton').each(function(){
			var $this = $(this);
			$this.unbind().browseDialog({
				type: $this.prev(':input').attr('resType'),
				uploadImageExtension: '${config.uploadImageExtension}',
				uploadFileExtension: '${config.uploadFileExtension}'
			});
		});
		$productResourcesTable.find('.deleteProductResource').unbind().click(function(){
			var $this = $(this);
			$this.closest("tr").remove();
		});
	});
	
	$copyButtons.each(function(){
		var $this = $(this);
		$this.click(function(){
			var text = $this.closest('td').find(':input').filter('[type=text]').val();
			$.copy(text);
		});
	});
	
	$productResourcesTable.find('.deleteProductResource').unbind().click(function(){
		var $this = $(this);
		$this.closest("tr").remove();
	});
	
	$inputForm.validate({
		errorPlacement: function(error, element){
			var $place = $(element).closest('td');
			error.appendTo($place);
		},
		rules: {
			name: {
				required: true
			},
			price: {
				required: true,
				number: true
			},
			authorities: {
				required: true
			},
			cost: {
				number: true
			},
			marketPrice: {
				number: true
			},
			images: {
				required: true
			},
			mediumImage:{
				required: true
			},
			thumbnail: {
				required: true
			},
			weight: {
				required: true,
				number: true
			},
			stock: {
				required: true,
				digits: true			
			},
			point: {
				digits: true
			},
			image: {
				required: true
			},
			imageOrder: {
				digits: true
			}
		},
		
		messages: {
			name: {
				required: "必填"
			},
			price: "必填",
			authorities: "必填",
			image: {
				required: "必填"
			},
			images: {
				required: "必填"
			},
			mediumImage: {
				required: "必填"
			},
			thumbnail: {
				required: "必填"
			},
			stock:{
				required: "必填",
				digits: "格式不对"
			},
			weight:{
				required: "必填",
				number: "格式不对"
			}
		}
	});
	
});