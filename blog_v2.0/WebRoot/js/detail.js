//prettyPrint();
layui.use(['form', 'layedit','jquery'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    
    //获取数据
    var aid = "";
	var url = window.location.href.split("?")[1];
	if (url!=null && url!='') {
		 var args = url.split("&");
		 aid = args[0].split("=")[1];
	}
	$.ajax({
		type: 'GET',
		data: {aid:aid},
		url: _contextPath+"/art/get.do",
		success:function(result) {
			if (result.code==1) {
				var art = result.item;
				$(".title").append(art.title);
				$(".article-detail-info").append('<span>编辑时间：'+art.time+'</span>'+
                            '<span>作者：'+art.anthor+'</span>'+
                            '<span>浏览量：'+art.viewCount+'</span>');
				$(".article-detail-content").append(art.content);
			} else {
				layer.msg(result.msg,{anim:6,icon:5});
			}
		}
	});
});

function go(type) {
	window.location.href = _contextPath+'/article.html?type='+type;
}