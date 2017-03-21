
$(function(){
	var $inputForm = $('#inputForm');
	
	$inputForm.validate({
		rules: {
			name: {
				required: true
				/*remote: {
					url: "validate.do",
					type: "POST",
					dataType: "json"
				}*/
			},
			price: "required",
			order: {
				digits:true
			},
			parameterName: "required",
			parameterOrder: {
				digits:true
			},
			options: "required"
		},
		messages: {
			name: {
				required: "必填"
				/*remote: "名称已存在"*/
			},
			price: "必填",
			parameterName: "必填",
			options: "必填",
			order: "必须为数字",
			parameterOrder: "必须为数字"
		}
	});
	
	var $cbheader = $(".cbheader");
	
	$cbheader.bind("change",function(){
	
		var $this = $(this);
		var $checkboxs = $this.parent().next("span").find(":checkbox");
		
		if($this.is(":checked")){
			$checkboxs.prop("disabled",false);
		}else{
			$checkboxs.prop("checked",false);
			$checkboxs.prop("disabled",true);
		}
	});
	
});

$(document).ready(function(){
	$('#btn').click(function(){
		var name = $('#name');
		if(name.length == 0){
			$('.info').html('必填');
			return;
		}
	});
});


function check(){
	
	var b = true;
	var $cbheader = $(".cbheader");
	$cbheader.each(function(index,item){
		
		var $this = $(item);
		if($this.is(":checked")){
			
			var $checkboxs = $this.parent().next("span").find(":checkbox:checked");
			
			if($checkboxs.length ==0){
			
				var name = $this.parent().text();
				name = name.replace("----", "");
				alert("请选择 " + name + "对应的内容！！");
				
				b = false;
				return false;
			}
		}
	});
	
	return b;
}
