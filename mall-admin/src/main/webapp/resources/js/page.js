$(function(){
	var base = $("#basePath").val();
	$('#addButton').click(function(){
		window.dialog = $("#dialog").clone().dialog({
		        title: "添加页面模块",
		        width : '90%',
		        height : 800,
		        modal : true
		    });
            $("iframe",dialog).attr("scrolling","no");
		    $("iframe",dialog).attr("frameborder","0");
		    $("iframe",dialog).attr("height","100%");
		    $("iframe",dialog).attr("width","100%");
		    $("iframe",dialog).attr("src",base+"/admin/page/page_module/add.do");
	});
	$('#listButton').click(function(){
		window.dialog = $("#dialog").clone().dialog({
	        title: "页面模块列表",
	        width : '90%',
	        height : 800,
	        modal : true
	    });
        $("iframe",dialog).attr("scrolling","no");
	    $("iframe",dialog).attr("frameborder","0");
	    $("iframe",dialog).attr("height","100%");
	    $("iframe",dialog).attr("width","100%");
	    $("iframe",dialog).attr("src",base+"/admin/page/page_module/list.do");

	});
	
	var $add_element_attr = $('#add_element_attr');
	$("#addRuleButton").bind('click', function(){
		$.ajax({
			url: base+'/admin/page/add_element_attr.do',
			type: 'post',
			dataType: 'json',
			success: function(data){
				showProductList(data);
			}
		});
	});
	
	function showProductList(data)
	{
		var $div = $('<div></div>');
		$div.css({
			'padding': '10px',
			'max-height': '200px',
			'overflow': 'scroll'
		});
		
		var liCss = {
			'margin': '5px 0px',
			'cursor': 'pointer'	
		};
		
		var $ul = $('<ul></ul>').appendTo($div);
		for(var i = 0; i < data.length; i++){
			var $li = $('<li></li>').appendTo($ul);
			$li.html(data[i].name);
			$li.attr('value', data[i].value);
			$li.css(liCss);
		}
		
		var $dialog = $.dialog({
			title: '属性列表',
			content: $div,
			ok: null,
			cancel: null
		});
		
		$ul.find('li').each(function(){
			var $this = $(this);
			$this.click(function(){
				var $tr = $('<tr class="title"></tr>');
				$tr.html("");
				var $td1 = $('<td>'+$this.html()+'</td>');
				var $td2 = $('<td> <input type="text" name="attribute" class="text" /></td>');
				var $td3 = $('<td> <a href="javascript:void(0)" class="deldeteRuleButton">清除</a></td>');	
				var $text1=$('<input type="hidden" name="rules" class="text"/>');
				$text1.attr('value',$this.attr('value'));
				$td1.appendTo($tr);
				$td2.appendTo($tr);
				$td3.appendTo($tr);
				$text1.appendTo($td1);
				$add_element_attr.after($tr);
				$dialog.next(".dialogOverlay").andSelf().remove();
			});
			
		});
	}
	
	/**
	 * 删除
	 */
	$(document).on("click","a.deldeteRuleButton",function() {
		var $this = $(this);
		$this.closest("tr").remove();
	});	
	
	
	$("a.deleteElementButton").click(function(){
		var $this = $(this);
		$.dialog({
			type: 'warn',
			content: '确定要删除所选记录?',
			ok: '确定',
			cancel: '取消',
			onOk: function(){
				$.ajax({
					url: base+'/admin/page/module_element/delete.do',
					type: 'post',
					data: {ids:$("#moduleElementId").val()},
					dataType: 'json',
					cache: false,
					success: function(data){
						try{
							if(data.errCode == 0){
								window.location.reload(true);
							}else{
								alert('删除失败!');
							}
						}catch(e){
							alert(e);
						}
					}
				});
			}
		});
	});
	
});