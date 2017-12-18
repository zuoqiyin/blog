var $;
layui.use(['jquery','form','util'], function () {
    $ = layui.jquery;
    var util = layui.util;
    //获取用户信息
    var user = MyLocalStorage.get("user");
    if (user!=null && user!='') {
    	user = JSON.parse(user);
    	$("#email").val(user.email);
    	$("#name").val(user.name);
    	$("#city").val(user.city);
    	$("#sign").val(user.sign);
    	$("#token").val(user.token);
    	$("#uid").val(user.uid);
    	$("#upload-img").attr('src',_contextPath + "/images"+user.img);
    	$(".layui-form-radio").get(user.sex==null || user.sex=='' || user.sex=='undefined' ? 0 : user.sex ).click();
    } else {
    	layer.msg('会话已过期,请先去登陆',{anim:6});
    	location.href = _contextPath + "/login.html";
    }
    
    // 我的消息-我的回复
    $.ajax({
		type: 'get',
		data: {"uid":user.uid},
		async:true,
		url: _contextPath+"/msg/getMyMsgs.do",
		success:function(result) {
			if (result.code==1) {
				
				var msgs = result.item.msgs;
				var revs = result.item.revs;
				var html = '';
				for (var i=0; i<msgs.length; i++) {
					var time = util.timeAgo(formatDate(""+msgs[i].time));
					if (msgs[i].aid=='0') {
						html +=
						'<li id='+msgs[i].mid+'> '+
							'<blockquote class="layui-elem-quote"><span>'+msgs[i].name+'</span>回复了你的评论<a href="about.html">【'+msgs[i].content+'】</a></blockquote>'+
							'<p><span>'+time+'</span>'+
							'<a href="javascript:msgDel(\''+msgs[i].mid+'\');" class="layui-btn layui-btn-sm layui-btn-danger">删除</a></p>'+
						'</li>';
					} else {
						html +=
							'<li id='+msgs[i].mid+'> '+
								'<blockquote class="layui-elem-quote"><span>'+msgs[i].name+'</span>回复了你的评论<a href="detail.html?aid='+msgs[i].aid+'">【'+msgs[i].content+'】</a></blockquote>'+
								'<p><span>'+time+'</span>'+
								'<a href="javascript:msgDel(\''+msgs[i].mid+'\');" class="layui-btn layui-btn-sm layui-btn-danger">删除</a></p>'+
							'</li>';
					}
				}
				$(".msgs").html(html);
				html = "";
				for (var i=0; i<revs.length; i++) {
					var time = util.timeAgo(formatDate(""+revs[i].time));
					if (revs[i].aid=='0') {
						html +=
							'<li>'+
								'<blockquote class="layui-elem-quote"><i style="color:#999">'+time+':&nbsp;</i>'+
								'在<span>留言墙</span>中的留言:<a href="about.html">【'+revs[i].content+'】</a><br></blockquote>'+
							'</li>';
				     
					} else {
						html +=
							'<li>'+
							'<blockquote class="layui-elem-quote"><i style="color:#999">'+time+':&nbsp;</i>'+
							'在<span>'+revs[i].title+'</span>中的回复:<a href="detail.html?aid='+revs[i].aid+'">【'+revs[i].content+'】</a><br></blockquote>'+
						'</li>';
					}
				}
				$(".revs").html(html);
			} else {
				layer.msg(result.msg,{anim:6});
			}
		}
	});
    
    var form = layui.form;
    // 修改个人资料
    form.on('submit(formInfo)', function(data){
    	data = data.field;
    	data.uid=$("#uid").val();
    	data.token=$("#token").val();
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/set/info.do",
    		success:function(result) {
    			if (result.code==1) {
    				MyLocalStorage.put("user", JSON.stringify(result.item), 360*24*3);
    				layer.msg("修改成功!",{icon:1});
    			} else {
    				layer.msg(result.msg,{anim:6});
    			}
    		}
    	});
    	return false;
    });
    
    // 上传照片
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
    		url: _contextPath+"/user/upload.do?token="+$("#token").val()+"&uid="+$("#uid").val(),
    		cache: false,  
            contentType: false,  
            processData: false,
    		success:function(result) {
    			if (result.code==1) {
    				MyLocalStorage.put("user", JSON.stringify(result.item), 360*24*3);
    				var user = result.item;
    				layer.close(index);
    				layer.msg("上传成功!",{icon:1});
    				$("#upload-img").attr("src",_contextPath+"/images"+user.img+"?"+Math.random());
    				$(".blog-user img").attr("src",_contextPath+"/images"+user.img+"?"+Math.random());
    			} else {
    				layer.msg(result.msg,{anim:6});
    				layer.close(index);
    			}
    		}
    	});
	});
	// 修改密码
    form.on('submit(formPwd)', function(data){
    	data = data.field;
    	if (data.pwd.length<6 || data.pwd.length>18) {
    		layer.msg("密码必须6到18个字符",{anim:6});
    		return false;
    	}
    	if (data.pwd!=data.repwd) {
    		layer.msg("两次密码输入不一致",{anim:6});
    		return false;
    	}
    	data.token = $("#token").val();
    	data.uid = $("#uid").val();
    	$.ajax({
    		type: 'post',
    		data: data,
    		async:true,
    		url: _contextPath+"/user/set/pwd.do",
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
    
    // 激活邮箱
	$("#activaEmailBtn").click(function(){
		$("#activeEmail").css('display','');
		$("#activeEmail").click();
		if (user.isActive=='Y') {
			$("#activeEmaliShow").append("<br />"+"<p>您的邮箱："+user.email+"&nbsp;<i style=\"color: #B1B1B1\">(已成功激活)</i></p><br/><br/><br/><br/>");
		} else {
			$("#activeEmaliShow").append("<br />"+
    						"<p>您的邮箱："+user.email+"&nbsp;<i style=\"color: red\">(尚未激活)</i></p>"+
							"<br /><br /><br /><br />"+
    						"<p>1.如果您未收到邮件，或激活链接失效，您可以<a class=\"link\" href=\"javascript:sendEmail()\">重新发送邮件</a>，或者<a href=\"javascript:updateEmail()\" class=\"link\">更换邮箱； </a></p>"+
    						"<br />"+
    						"<p>2. 如果您始终没有收到 zuoqy博客 发送的邮件，请注意查看您邮箱中的垃圾邮件；</p>"+
    						"<br />"+
    						"<p>3. 如果你实在无法激活邮件，您还可以联系：zuoqy@zuoqy.cn </p>"+
    						"<br/><br/><br/><br/>");
		}
	});
	
	// 清空全部消息
	$("#LAY_msg").click(function() {
		$(".msgs").empty();
	});
});

//删除消息
function msgDel(mid) {
	$.ajax({
		type: 'POST',
		data: {mid:mid},
		url: _contextPath+"/msg/del.do",
		success:function(result) {
			if (result.code==1) {
				$("#"+mid).remove();
			} else {
				layer.msg(result.msg,{anim:6,icon:5});
			}
		}
	});
}

//更换邮箱
function updateEmail() {
	location.href = _contextPath + "/user.html";
}

//发送邮件-激活邮箱
function sendEmail() {
	var user = JSON.parse(MyLocalStorage.get("user"));
	$.ajax({
		type: 'post',
		data: {uid:user.uid,token:user.token},
		async:true,
		url: _contextPath+"/usre/activeEmail/send.do",
		success:function(result) {
			if (result.code==1) {
				layer.alert('已将激活邮箱地址发至您的邮箱,请注意查收', {icon: 1,anim: 1});
			} else {
				layer.msg(result.msg,{anim:6});
			}
		}
	});
}