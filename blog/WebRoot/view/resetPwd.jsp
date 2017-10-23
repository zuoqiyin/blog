<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码</title>
<style type="text/css">
	.left {
		margin-top:20px;
		width:680px;
		float: left;
		font-family: '楷体';
		background: white;
	}
</style>
</head>
<body>
	<div class="left">
		<div class="layui-tab layui-tab-brief">
	  		<ul class="layui-tab-title">
	    	<li class="layui-this">重置密码</li>
	  		</ul>
		  	<div class="layui-tab-content">
		   		<div class="layui-tab-item layui-show">
		   			<blockquote class="layui-elem-quote">${name},请重置您的密码</blockquote>
		   			<form class="layui-form layui-form-pane" method="post">
		   				<input type="hidden" name="mid" value="${mid }">
			   			<div class="layui-form-item">
						    <label class="layui-form-label">新密码</label>
					    	<div class="layui-input-inline">
					        	<input type="password" name="pwd" required  lay-verify="required" autocomplete="off" class="layui-input">
					        </div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">确认密码</label>
					    	<div class="layui-input-inline">
					        	<input type="password" name="regpwd" required  lay-verify="required" autocomplete="off" class="layui-input">
					        </div>
					    </div>
					    <div class="layui-form-item">
						   <label class="layui-form-label">人类验证</label>
						   <div class="layui-input-inline">
						       <input type="text" name="verify" required lay-verify="required" autocomplete="off" placeholder="请回答后面问题" class="layui-input">
						   </div>
						   <div class="layui-form-mid">
						       <span style="color:#c00">${verify }</span>
						   </div>
						</div>
						<div class="layui-form-item">
				    		<button class="layui-btn" lay-submit lay-filter="resetForm">立即重置</button>
			   		 	</div>
					</form>
		   		</div>
		 	</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
	layui.use(['element','form'], function(){
	    var element = layui.element;
	    var form = layui.form;
	    //监听登陆
	    form.on('submit(resetForm)', function(data){
	    	data = data.field;
	    	if (data.pwd!=data.regpwd) {
	    		parent.layer.msg("两次密码输入不一致",{anim:6,offset:'200'});
	    		return false;
	    	}
	    	$.ajax({
	    		type: 'post',
	    		data: data,
	    		async:true,
	    		url: _contextPath+"/member/resetPwdDispose.do",
	    		success:function(result) {
	    			if (result.code==1) {	    				
	    				parent.layer.confirm('密码重置成功,快去重新登录吧!', {
						  btn: ['确定'],icon:1,offset:'200'
						}, function(){
						  location.href=_contextPath+"/main.do";
						});
	    			} else {
	    				parent.layer.msg(result.msg,{anim:6,offset:'200'});
	    			}
	    		}
	    	});
	    	return false;
	    });
	});
});
</script>
</html>