
$(function(){
	var $addParameter = $('#addParameter'),
		$paramTable = $('#parameters');
	
	$addParameter.click(function(){
		var $row = $('<tr></tr>');
		$row.append([
		             '<td><input type="text" name="parameter" /></td>',
		             '<td><span class="button opt">删除</span></td>'
		             ].join(''));
		$paramTable.append($row);
		
		$row.find('span.opt').click(function(){
			var $this = $(this);
			$this.closest('tr').remove();
		});
	});
	
	$paramTable.find('span.opt').click(function(){
		var $this = $(this);
		$this.closest('tr').remove();
	});
});

var $chooseSpec = null;
$().ready(function() {

	//select 新增筛选条件
	$chooseSpec = $("#chooseSpec");
	$chooseSpec.change(function(){
		
		var specid = $(this).children("option:selected").val();
		
		$("div.container[specid="+specid+"]").show();
		
	})

	//显示与隐藏功能按钮
	var $btnShow = $(".container input.showOther");
	$btnShow.each(function(){
		
		var $hiddenSpans = $(this).parent().find("span:hidden");
		if($hiddenSpans.length==0){
			 $(this).hide();
		}
		
	})
	
	//显示与隐藏功能按钮
	var $btnbrands = $(".brands input.showOther");
	$btnbrands.each(function(){
		
		var $hiddenlabels = $(this).parent().find("label:hidden");
		if($hiddenlabels.length==0){
			 $(this).hide();
		}
	})
	
});

//品牌管理
function showOther(obj){
	
	var $this = $(obj);
	var $lis = $this.parent().find("label");
	var $hiddenlabels = $this.parent().find("label:hidden");
	
	if($this.val()=="添加其他"){
		
		if($hiddenlabels.length==0){
			retrun;
		}
		
		$this.val("隐藏");
		$lis.show();
	}else{
		
		var $labels = $this.parent().find("label :checkbox:checked").parent();
		if($labels.length==0){
			alert("请至少选择一个品牌");
			return;
		}
		
		$this.val("添加其他");
		$lis.hide();
		$labels.show();
		
	}
}


//其他筛选规格
function showOtherCategory(obj){
	
	var $this = $(obj);
	var $spans = $this.parent().find("span");
	var $hiddenSpans = $this.parent().find("span:hidden");
	
	if($this.val()=="添加其他"){
		
		if($hiddenSpans.length==0){
			return;
		}
		
		$this.val("隐藏");
		$spans.show();
	}else{
		
		var $checkedspans = $this.parent().find("span :checkbox:checked").parent();
		if($checkedspans.length == 0){
			$this.parents("div.container").hide();
		}else{
			
			$this.val("添加其他");
			$spans.hide();
			$checkedspans.show();
		}
	}
	
}




