
// 返回顶部按钮显示隐藏效果
$(function(){
    $(window).scroll(function(){
        height = $(window).scrollTop();
        if(height > 60){
            $('#back_top').fadeIn();
        }else{
            $('#back_top').fadeOut();
        };
    });
});

//返回顶部跳转滚动效果
jQuery(document).ready(function($) {
	function a(x,y){
	    l = $('#back_top').offset().left;
	    w = $('#back_top').width();
	}
	$(function() {  
	    a(10,100);
	    $('#back_top').click(function(){ 
	        $('body,html').animate({
	            scrollTop: 0
	        },800);return false;  
	    })
	});
});