layui.use(['jquery','flow','form'], function () {
	var $ = layui.jquery;
	var type = "";
	var url = window.location.href.split("?")[1];
	if (url!=null && url!='') {
		 var args = url.split("&");
		 type = args[0].split("=")[1];
	}
    // 流加载
    var flow = layui.flow;
    flow.load({
    	elem: '.blog-main-left', //流加载容器
    	isAuto: false,
    	end: '没有更多的文章了~QAQ',
    	done: function(page,next) {
    		$.ajax({
        		type: 'GET',
        		data: {page:page,size:'5',type:type},
        		url: _contextPath+"/art/select.do",
        		success:function(result) {
        			if (result.code==1) {
        				var arts = result.item;
        				var lis = [];
        				for (var i=0; i<arts.length; i++) {
        					var head='';
        					if (page==1) {
        						head='<div class="article shadow animated fadeInLeft">';
        					} else {
            	        		head='<div class="article shadow">';
            	        	}
        					lis.push(head+
        	                          '<div class="article-left ">'+
        	                          '<img src="images'+arts[i].img+'" alt=\''+arts[i].title+'\'/>'+
        	                      '</div>'+
        	                      '<div class="article-right">'+
        	                          '<div class="article-title">'+
        	                              '<a href="detail.html?aid='+arts[i].aid+'">'+arts[i].title+'</a>'+
        	                          '</div>'+
        	                          '<div class="article-abstract">'+
        	                              arts[i].intro+
        	                          '</div>'+
        	                      '</div>'+
        	                      '<div class="clear"></div>'+
        	                      '<div class="article-footer">'+
        	                          '<span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;'+arts[i].time+'</span>'+
        	                          '<span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;'+arts[i].anthor+'</span>'+
        	                          '<span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">'+arts[i].typeName+'</a></span>'+
        	                          '<span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;'+arts[i].viewCount+'</span>'+
        	                          '<span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;'+arts[i].comCount+'</span>'+
        	                      '</div>'+
        	                  '</div>');
        				}	
        				if (page==1) {
        					next(lis.join(''), page < result.count);
        				} else {
        					setTimeout(function(){next(lis.join(''), page < result.count);},1000);
        				}
        			} else {
        				layer.msg(result.msg,{anim:6});
        			}
        		}
        	});	
    	}
    });
});

function go(type) {
	window.location.href = _contextPath+'/article.html?type='+type;
}