var pics = {
  "data": [
      {"src": "images/pic/0.jpg",   "alt": "还在制作"},
      {"src": "images/pic/1.jpg",   "alt": "盘中餐",},
      {"src": "images/pic/2.jpg",   "alt": "向日葵"},
      {"src": "images/pic/3.jpg",   "alt": "日出"},
      {"src": "images/pic/4.jpg",   "alt": "水果平盘"},
      {"src": "images/pic/5.jpg",   "alt": "花园"},
      {"src": "images/pic/6.jpg",   "alt": "红花"},
      {"src": "images/pic/7.jpg",   "alt": "快出锅青椒肉丝"},
      {"src": "images/pic/8.jpg",   "alt": "林荫大道"}
   ]};
layui.use(['jquery','flow'], function () {
	var $ = layui.jquery;
	// 流加载 图片
    var flow = layui.flow;
    var count = pics.data.length-1;
    flow.load({
    	elem: '.mixed-main', //流加载容器
    	isAuto: false,
    	done: function(page,next) {
    		var lis = [];
    		for (var i=0; i<8; i++) {
    			if (count < 0) break;
    			lis.push('<div class="mixed shadow animated zoomIn">'+
                    '<div class="mixed-pic">'+
                        '<a href="javascript:view('+count+')"><img src="'+pics.data[count].src+'" alt="'+pics.data[count].alt+'" /></a>'+
                    '</div>'+
                    '<div class="mixed-info">'+pics.data[count].alt+'</div>'+
                    '<div class="mixed-footer">'+
                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="javascript:view('+count+')"><i class="fa fa-eye fa-fw"></i>查看</a>'+
                        '<a class="layui-btn layui-btn-small layui-btn-primary" href="pic/down.do?path='+pics.data[count].src+'"><i class="fa fa-download fa-fw"></i>下载</a>'+
                    '</div>',
                '</div>');
    			count--;
    		}
    		next(lis.join(''), page < pics.data.length/8);
    	}
    });
});
function view(start) {
	pics.start = start-1;
	pics.data.splice(0,1);
	layer.photos({photos: pics });	
}
