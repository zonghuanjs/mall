$().ready(function() {

	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			title: "required",
			order: "digits"
		}
	});
	
	$.tab({
		tabs: '.tab input',
		tabContents: '.tabContent'
	});
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				},
				filterMode: false,
				filePostName: 'file',
				uploadJson: site.base + '/admin/file/upload.do'
			});
		});
	}
	
	$articleResourcesTable = $("#articleResources");
	$addProductResource = $("#addProductResource");
	$addProductResource.click(function(){
		$resourcesType = $("#resourcesType").val();
		var options;
		if($resourcesType=='image'){
		   options = {type: 'image'};
		}else{
		   options = {type: 'file',uploadFileExtension:'doc,docx,txt,xls,xlsx,ppt,rar,pdf'};
		}
		
		var trHtml = '<tr> <td><input type="text" name="resourceUrl" class="text" maxlength="200"\/><input type="button" class="button browserButton" value="选择文件" \/><a href="" target="_blank">查看</a></td> <td> <input type="text" name="resourceTitle" class="text" maxlength="200" \/> <\/td> <td> <input type="text" name="resourceOrder" class="text productImageOrder" maxlength="9" value="0" style="width: 50px;" \/> <\/td> <td> <a href="javascript:;" class="deleteResource">[删除]<\/a> <\/td> <\/tr>';
		$articleResourcesTable.append(trHtml);
		$articleResourcesTable.find('.browserButton').unbind().browseDialog(options);
		$articleResourcesTable.find('.deleteResource').unbind().click(function(){
				var $this = $(this);
				$this.closest("tr").remove();
			});
	});
	
	var $deleteResource = $("a.deleteResource");
	$deleteResource.bind("click", function(){
		var $this = $(this);
		$this.closest("tr").remove();
	});
	
});
