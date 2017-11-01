layui.use(['jquery','form'], function () {
    var $ = layui.jquery;

    var url = window.location.href.split("?")[1];
    var args = url.split("&");
    var token = args[0].split("=")[1];
    var uid = args[1].split("=")[1];
    var email = args[2].split("=")[1];
        
    $(function(){
    	$.ajax({
    		type: 'POST',
    		data: {token:token,uid:uid},
    		url: _contextPath+"/usre/activeEmail.do",
    		success:function(result) {
    			if (result.code==1) {
    				$("#activeEmaliShow").append("<br />"+"<p>您的邮箱："+email+"&nbsp;<i style=\"color: #B1B1B1\">(已成功激活)</i></p>" +
    						"<br/><br/><br/><br/><br/><br/>");
    			} else {
    				$("#activeEmaliShow").append("<i class=\"fa fa-frown-o\" style=\"font-size:360px\"></i><i>激活失败<i>");
    			}
    		}
    	});
    });
    
});