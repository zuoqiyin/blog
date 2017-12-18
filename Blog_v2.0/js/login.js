layui.use(['jquery','form'], function () {
    var $ = layui.jquery;
    var form = layui.form;
    //获取验证码
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
    //监听登陆
    form.on('submit(loginForm)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/login.do",
    		success:function(result) {
    			if (result.code==1) {
    				MyLocalStorage.put("user", JSON.stringify(result.item), 360*24*3);
    				if (document.referrer.split('?')[0].lastIndexOf('resetPwd.html')>0) {
    					location.href = _contextPath+'/index.html';
    				} else {    					
    					window.history.go(-1);
    				}
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
    //监听注册
    form.on('submit(regForm)', function(data){
    	data = data.field;
    	if (data.pwd.length<6 || data.pwd.length>18) {
    		layer.msg("密码必须6到18个字符",{anim:6});
    		return false;
    	}
    	if (data.pwd!=data.repwd) {
    		layer.msg("两次密码输入不一致",{anim:6});
    		return false;
    	}
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/register.do",
    		success:function(result) {
    			if (result.code==1) {
    				MyLocalStorage.put("user", JSON.stringify(result.item), 360*24*3);
    				window.location = _contextPath+'/user.html';
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
    
    /*忘记密码*/
    $("#forgetPwd").click(function(){
    	$("#forgetPwdTit").css('display','');
    	$("#forgetPwdTit").click();
    });
    //监听提交忘记密码
    form.on('submit(resetPwd)', function(data){
    	data = data.field;
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/resetPwd/send.do",
    		success:function(result) {
    			if (result.code==1) {
    				layer.alert('已将密码重置地址发至您的邮箱,请注意查收', {icon: 1,anim: 1});
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
});

