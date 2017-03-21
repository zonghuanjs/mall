$(function(){
	var base = $("#basePath").val();
	
	$.ajax({
		url: base+'/admin/page/preview1.do',
		type: 'post',
		dataType: 'json',
		cache: false,
		success: function(data){
			var $row = $(".row");
			if(data.pageModules != null && data.pageModules.length > 0){
				
				$.each(data.pageModules, function(index, pageModule){
					if(pageModule.elements != null && pageModule.elements.length > 0){
						
						if(pageModule.type == 1){
							if(pageModule.count == 1){							
								$.each(pageModule.elements, function(index, element){
									if(pageModule.count == -1 || index < pageModule.count){
										var $div = $("<div class='images-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding'></div>").appendTo($row);
										var $a = $("<a href='"+element.link+"'></a>").appendTo($div);
										var $img = $("<img src='"+element.image+"'>").appendTo($a);
									}						
								});	
							}
							/*else{
								var $div = $("<div class='images-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12'></div>").appendTo($row);
								$.each(pageModule.elements, function(index, element){
									if(pageModule.count == -1 || index < pageModule.count){
										var $div1 = $("<div class='images-wrap01 col-xs-6 col-sm-6 col-md-6 col-lg-6'></div>").appendTo($div);
										var $a = $("<a href='"+element.link+"'></a>").appendTo($div1);
										var $img = $("<img src='"+element.image+"'>").appendTo($a);
									}						
								});	
							}*/
							
						}
						else if(pageModule.type == 2){
							var $div_category = $("<div class='category-menu-wrap col-xs-12 col-sm-12 col-md-12 col-lg-12'></div>").appendTo($row);
							$.each(pageModule.elements, function(index, element){
								if(pageModule.count == -1 || index < pageModule.count){
									var $div = $("<div class='category-btn col-xs-3 col-sm-3 col-md-3 col-lg-3'></div>").appendTo($div_category);
									var $a = $("<a href='"+element.link+"'></a>").appendTo($div);
									var $img = $("<img src='"+element.image+"'><p>"+element.title+"</p>").appendTo($a);
								}						
							});	
						}
						else if(pageModule.type == 3){
							var $slideShow =$("<div id='carousel-example-generic' class='carousel slide' data-ride='carousel'></div>").appendTo($row);
							var $ol = $("<ol class='carousel-indicators'></div>").appendTo($slideShow);
							$.each(pageModule.elements, function(index, element){
								if(pageModule.count == -1 || index < pageModule.count){
									var $li = $("<li data-target='#carousel-example-generic' data-slide-to='"+index+"'></li>").appendTo($ol);
									if(index == 0){
										$li.addClass("active");
									}						
								}						
							});	
							
							var $div = $("<div class='carousel-inner' role='listbox'></div>").appendTo($slideShow);
							$.each(pageModule.elements, function(index, element){
								if(pageModule.count == -1 || index < pageModule.count){
									var $item = $("<div class='item'></div>").appendTo($div);								
									var $href = $("<a href='"+element.link+"'><img src='"+element.image+"' alt=''></a><div class='carousel-caption'></div>").appendTo($item)
								
									if(index == 0){
										$item.addClass("active");
									}						
								}						
							});	
						}
						else if(pageModule.type == 4){
							var $div = $("<div class='images-wrap03 col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding'></div>").appendTo($row);
							var $div1 = $("<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12 inside-shop'></div>").appendTo($div);
							$.each(pageModule.elements, function(index, element){
								if(pageModule.count == -1 || index < pageModule.count){
									var $div2 = $("<div class='col-xs-6 col-sm-6 col-md-6 col-lg-6 inside-shop01'></div>").appendTo($div1);								
									var $a = $("<a href='"+element.link+"'></a>").appendTo($div2);
									var $img = $("<img src='"+element.image+"'>").appendTo($a);
								}						
							});	
						}
						else if(pageModule.type == 5){
							$.each(pageModule.elements, function(index, element){
								if(pageModule.count == -1 || index < pageModule.count){
									var $div = $("<div class='images-wrap02 col-xs-12 col-sm-12 col-md-12 col-lg-12 no-padding'></div>").appendTo($row);				
									var $a = $("<a href='"+element.link+"'></a>").appendTo($div);
									var $img = $("<img src='"+element.image+"'>").appendTo($a);
								}						
							});	
						}
					}
					
				});	
			}	
		}
	});
});