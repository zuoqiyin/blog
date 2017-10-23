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
	    	<li class="layui-this">激活邮箱</li>
	  		</ul>
		  	<div class="layui-tab-content">
		   		<div class="layui-tab-item layui-show">
		   			<br />
    				<p>您的邮箱：${member.email }&nbsp;<i style="color: #B1B1B1">(已成功激活)</i></p>
		   		</div>
		 	</div>
		</div>
	</div>
</body>
</html>