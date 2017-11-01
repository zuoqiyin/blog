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
				layer.msg(result.msg,{anim:6});
			}
		}
	});
    
    var layedit = layui.layedit;

    //评论和留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
    });
    //评论和留言的编辑器的验证
    layui.form().verify({
        content: function (value) {
            value = $.trim(layedit.getText(editIndex));
            if (value == "") return "自少得有一个字吧";
            layedit.sync(editIndex);
        }
    });

    //监听评论提交
    form.on('submit(formRemark)', function (data) {
        var index = layer.load(1);
        //模拟评论提交
        setTimeout(function () {
            layer.close(index);
            var content = data.field.editorContent;
            var html = '<li><div class="comment-parent"><img src="../images/Absolutely.jpg"alt="absolutely"/><div class="info"><span class="username">Absolutely</span><span class="time">2017-03-18 18:46:06</span></div><div class="content">' + content + '</div></div></li>';
            $('.blog-comment').append(html);
            $('#remarkEditor').val('');
            editIndex = layui.layedit.build('remarkEditor', {
                height: 150,
                tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
            });
            layer.msg("评论成功", { icon: 1 });
        }, 500);
        return false;
    });
});

function go(type) {
	window.location.href = _contextPath+'/article.html?type='+type;
}