$().ready(function() {

	var $browserButton = $("#browserButton");
	$browserButton.browseDialog();
	
	//editor
	if(typeof(KindEditor) != 'undefined'){
		KindEditor.ready(function(K){
			editor = K.create('#editor', {
				height: '350px',
				syncType: 'form',
				afterChange: function(){
					this.sync();
				},
				filterMode: false
			});
		});
	}
	
	var $inputForm = $("#inputForm");
	$inputForm.validate({
		rules: {
			beginDate: 'required',
			endDate: 'required',
			title: 'required'
		},
		messages: {
			beginDate: '必填',
			endDate: '必填',
			title:  '必填'
		},
		errorPlacement: function(error, element){
			if(element.is(':radio')){
				error.appendTo(element.closest('fieldset'));
			}else{
				error.appendTo(element.parent());
			}				
		}
	});
	
	$inputForm.submit(function(e){
	
		var value1 = $("#type").children("option:selected").val();
		if(0==parseInt(value1)){
			 e.preventDefault();
			$("#type").after('<label for="type" class="error">必填</label>'); 
		}
		
		
		var value3 = $("#contentType").children("option:selected").val();
		if(0==parseInt(value3)){
			 e.preventDefault();
			$("#contentType").after('<label for="type" class="error">必填</label>'); 
		}else if(2==parseInt(value3)){
		
			if($("#imgURL").val()==""){
				e.preventDefault();
				$("#imgURL").after('<label for="type" class="error">必填</label>'); 
			}
		}else if(1==parseInt(value3)){
			if($("#content").val()==""){
				e.preventDefault();
				$("#content").after('<label for="type" class="error">必填</label>'); 
			}
		}
		
		var $checkboxs = $(":checkbox:checked");
		if($checkboxs.length==0){
		 	e.preventDefault();
		 	$(":checkbox[name=ranks]").parent().append('<label for="type" class="error">必填</label>'); 
		}
		
	});
	
	$("#contentType").change(function(){
	
		var value = $("#contentType").children("option:selected").val();
		if(1==parseInt(value)){//wenben
			$("#trImgURL").hide();
			$("#trContent").show();
		}else if(2==parseInt(value)){
			$("#trImgURL").show();
			$("#trContent").hide();
		}
	
	});
	
});
