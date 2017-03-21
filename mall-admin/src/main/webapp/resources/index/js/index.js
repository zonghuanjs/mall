
// 触屏滑动轮播图效果
$('#carousel-example-generic').on('swipe', function (event) {
    switch(event.direction){
        case "left":
            $(this).carousel("next");
            break;

        case "right":
            $(this).carousel("prev");
            break;
    }
});

// 轮播广告图效果
var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        paginationClickable: true,
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });

// 按钮选中状态切换
function switchSlider(target){
    var li = $(target).parent();
    $(li).siblings().removeClass("current");
    li.addClass("current");
};