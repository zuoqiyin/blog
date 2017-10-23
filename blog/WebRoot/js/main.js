$(function() {
    layui.use('carousel', function(){
    	var carousel = layui.carousel;
    	carousel.render({
    		elem: '#carousel',
    		width: '100%',
    		arrow: 'always'
    	});
    });
});