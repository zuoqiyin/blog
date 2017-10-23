$(function(){
	layui.use(['element','form'], function(){
	    var element = layui.element;
	    var form = layui.form;
	    
	    // 修改个人资料
	    form.on('submit(formInfo)', function(data){
	    	data = data.field;
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/updataInfo.do",
	    		success:function(result) {
	    			if (result.code==1) {
	    				sessionStorage.setItem('member',JSON.stringify(result.item));
	    				layer.msg("修改成功!",{icon:1});
	    			} else {
	    				layer.msg(result.msg,{anim:6});
	    			}
	    		}
	    	});
	    	return false;
	    });
	    
	    // 修改密码
	    form.on('submit(formPwd)', function(data){
	    	data = data.field;
	    	if (data.pwd!=data.repwd) {
	    		layer.msg("两次密码输入不一致",{anim:6});
	    		return false;
	    	}
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/updataPwd.do",
	    		success:function(result) {
	    			if (result.code==1) {
	    				layer.msg("修改成功!",{icon:1});
	    			} else {
	    				layer.msg(result.msg,{anim:6});
	    			}
	    		}
	    	});
	    	return false;
	    });
	});
	
	// 上传文件
	$("#upload").click(function(){
		$("#upload-file").click();
	});
	$("#upload-file").change(function(){
		var index = layer.load(1, {shade: [0.1,'#fff'] });
		var data = new FormData($("#upload-form")[0]);
		$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/member/upload.do",
    		cache: false,  
            contentType: false,  
            processData: false,
    		success:function(result) {
    			if (result.code==1) {
    				sessionStorage.setItem('member',JSON.stringify(result.item));
    				var member = result.item;
    				layer.close(index);
    				layer.msg("上传成功!",{icon:1});
    				$("#upload-img").attr("src",_contextPath+"/img"+member.img+"?"+Math.random());
    			} else {
    				layer.msg(result.msg,{anim:6});
    				layer.close(index);
    			}
    		}
    	});
	});
	
	// 激活邮箱
	$("#verify").click(function(){
		$("#activeEmail").css('display','');
		$("#activeEmail").click();
	});
	
	// 更换邮箱
	$(".updateEmail").click(function(){
		$("#myInfo").click();
	});
	
	// 发送邮件验证邮箱
	$(".sendMail").click(function(){
		$.ajax({
    		type: 'post',
    		async:true,
    		url: _contextPath+"/member/activateEmail.do",
    		success:function(result) {
    			if (result.code==1) {
    				layer.alert('已成功将激活链接发送到了您的邮箱，接受可能会稍有延迟，请注意查收。', {icon: 1,anim: 1});
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
	});
});

function exit(){
	$.ajax({
		async: true,
		url: _contextPath+"/member/exit.do",
		success: function(result) {
			if (result.code==1) {
				sessionStorage.removeItem("member");
				parent.layer.closeAll();
				parent.loadFixbar();
				parent.getStatus();
			}
		}
	});
}