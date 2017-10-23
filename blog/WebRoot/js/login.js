$(function(){
	layui.use(['element','form'], function(){
	    var element = layui.element;
	    var form = layui.form;
	    //监听登陆
	    form.on('submit(loginForm)', function(data){
	    	data = data.field;
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/login.do",
	    		success:function(result) {
	    			if (result.code==1) {
	    				sessionStorage.setItem('member',JSON.stringify(result.item));
	    				parent.layer.closeAll();
	    				parent.loadFixbar();
	    				parent.getStatus();
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
	    	if (data.pwd!=data.repwd) {
	    		layer.msg("两次密码输入不一致",{anim:6});
	    		return false;
	    	}
	    	
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/reg.do",
	    		success:function(result) {
	    			if (result.code==1) {
	    				sessionStorage.setItem('member',JSON.stringify(result.item));
	    				layer.msg('恭喜您,'+result.item.email+'注册成功', {
	    					icon: 1,
	    					time: 500,
	    					anim: 1
	    				}, function(){
	    					parent.layer.closeAll();
		    				parent.loadFixbar();
		    				parent.getStatus();
	    				});   
	    			} else {
	    				layer.msg(result.msg,{anim:6});
	    			}
	    		}
	    	});
	    	return false;
	    });
	    //监听提交忘记密码
	    form.on('submit(resetPwd)', function(data){
	    	data = data.field;
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/resetPwd.do",
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
});

/*忘记密码*/
function forgetPwd(){
	$("#forgetPwdTit").css('display','');
	$("#forgetPwdTit").click();
}