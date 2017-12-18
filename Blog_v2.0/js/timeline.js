var data = [
	{'year':'2017','month':[
	    {'title':'11月','day':[
	        {'time':'11月25日 12:20','content':'修复页面加载等待时间页面没反应,和分享页面跳转bug.'},
	        {'time':'11月11日 00:00','content':'<i class="fa fa-paper-plane-o"></i><i class="fa fa-paper-plane-o"></i><i class="fa fa-paper-plane-o"></i>&nbsp;zuoqy博客v2.0新项目发布!'},
           	{'time':'11月01日 10:28','content':'<i class="fa fa fa-rocket"></i><i class="fa fa fa-rocket"></i><i class="fa fa fa-rocket"></i>&nbsp;zuoqy博客v2.0新项目正式构建!'}
        ]},
		{'title':'10月','day':[
		    {'time':'10月26日 13:52','content':'准备重新改版一个版本,心疼1.0版3秒钟,周末还是一如既往的折腾&nbsp;<i class="fa fa-hand-stop-o"></i>'},
		    {'time':'10月25日 11:22','content':'感觉自己写的样式太丑了,兼容性不好,发现各种问题&nbsp;<i class="fa fa-frown-o"></i>'},
		    {'time':'10月24日 19:00','content':'zuoqy博客v.01基本功能全部完善!也成功接入搜狗联盟,关于QQ登陆，和微博登陆还在研究中'}
		]},
		{'title':'9月','day':[
  		    {'time':'09月16日 06:00','content':'<i class="fa fa-paper-plane-o"></i>&nbsp;zuoqy博客v1.0发布!'}
  		]},
		{'title':'8月','day':[
   		    {'time':'','content':'zuoqy博客项目启动!'}
   		]}
	]}
];

layui.use('jquery', function () {
	var $ = layui.jquery;

	for (var y=0; y<data.length; y++) {
		var html = '<div class="timeline-year"><h2><a class="yearToggle" href="javascript:;">'+data[y].year+'<i class="fa fa-caret-down fa-fw"></i></a></h2>';
		var month = data[y].month;
		for (var m=0; m<month.length; m++) {
			html += '<div class="timeline-month"><h3 class=" animated fadeInLeft">'+
            '<a class="monthToggle" href="javascript:;">'+month[m].title+'<i class="fa fa-caret-down fa-fw"></i></a></h3><ul>';
			var day = month[m].day;
			for (var d=0; d<day.length; d++) {
				html += '<li>'+
                		'<div class="h4  animated fadeInLeft"><p class="date">'+day[d].time+'</p></div>'+
                		'<p class="dot-circle animated "><i class="fa fa-dot-circle-o"></i></p>'+
                		'<div class="content animated fadeInRight">'+day[d].content+'</div><div class="clear"></div>'+
                		'</li>';
			}
			html += '</ul></div>';
		}
		html +='</div>';
		$(".timeline-data").append(html);
	}
	
	$(function () {
	    $('.monthToggle').click(function () {
	        $(this).parent('h3').siblings('ul').slideToggle('slow');
	        $(this).children('i').toggleClass('fa-caret-down fa-caret-right');
	    });
	    $('.yearToggle').click(function () {
	        $(this).parent('h2').siblings('.timeline-month').slideToggle('slow');
	        $(this).children('i').toggleClass('fa-caret-down fa-caret-right');
	    });
	});
});
