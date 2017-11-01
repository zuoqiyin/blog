layui.use(['jquery','form'], function () {
    var $ = layui.jquery;

    var url = window.location.href.split("?")[1];
    var args = url.split("&");
    var token = args[0].split("=")[1];
    var uid = args[1].split("=")[1];
    var name = args[2].split("=")[1];
    
    $("#cite").text(name+',请重置您的密码');
    
    $(function(){
    	$.ajax({
    		type: 'GET',
    		url: _contextPath+"/verify.do",
    		success:function(result) {
    			if (result.code==1) {
    				$(".verift-text").text(result.item);
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    });
    
    // 修改密码
    var form = layui.form;
    form.on('submit(resetForm)', function(data){
    	data = data.field;
    	if (data.pwd.length<6 || data.pwd.length>18) {
    		layer.msg("密码必须6到18个字符",{anim:6});
    		return false;
    	}
    	if (data.pwd!=data.regpwd) {
    		parent.layer.msg("两次密码输入不一致",{anim:6,offset:'200'});
    		return false;
    	}
    	data.token = token;
    	data.uid = uid;
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/set/pwd.do",
    		success:function(result) {
    			if (result.code==1) {	    				
    				parent.layer.confirm('密码重置成功,快去重新登录吧!', {
					  btn: ['确定'],icon:1,offset:'200'
					}, function(){
					  location.href=_contextPath+"/login.html";
					});
    			} else {
    				parent.layer.msg(result.msg,{anim:6,offset:'200'});
    			}
    		}
    	});
    	return false;
    });
});