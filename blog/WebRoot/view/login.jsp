<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆注册</title>
		<link rel="stylesheet" href="../css/user.css" />
		<script src="../js/login.js"></script>
	</head>
	<body>
	<div class="con">
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		    <ul class="layui-tab-title">
		    	<li <c:if test="${flag=='login' }">class="layui-this"</c:if> >登陆</li>
		    	<li <c:if test="${flag=='reg' }">class="layui-this"</c:if>>注册</li>
		    	<li id="forgetPwdTit" style="display: none">忘记密码</li>
		    </ul>
		    <div class="layui-tab-content">
		    	<div class="layui-tab-item <c:if test="${flag=='login' }">layui-show</c:if>">
			    	<form class="layui-form layui-form-pane" method="post">
			    		<div class="layui-form-item">
						    <label class="layui-form-label">邮箱</label>
					    	<div class="layui-input-inline">
					        	<input type="text" name="email" required  lay-verify="email" autocomplete="off" class="layui-input">
					        </div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">密码框</label>
						    <div class="layui-input-inline">
						      <input type="password" name="pwd" required lay-verify="required" autocomplete="off" class="layui-input">
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
						    <button class="layui-btn" lay-submit lay-filter="loginForm">立即登陆</button>
						    <span style="padding-left: 20px">
						    	<a href="javascript:forgetPwd()">忘记密码?</a>
						    </span>
					    </div>
					    <div class="layui-form-item" style="padding-top: 20px;">
						    <span>或者使用社交账号登陆</span>
						    <a href="#" onclick="layer.msg('暂时不支持该操作', {icon:2})">
								<img class="login-app" src="../img/logo2.png"></img>
							</a>
							<a href="#" login-app" onclick="layer.msg('暂时不支持该操作', {icon:2})">
								<img class="login-app"src="../img/logo1.png"></img>
							</a>
					    </div>
					</form>
		    	</div>
    			<div class="layui-tab-item <c:if test="${flag=='reg' }">layui-show</c:if>">
    				<form class="layui-form layui-form-pane" method="post">
			    		<div class="layui-form-item">
						    <label class="layui-form-label">邮箱</label>
					    	<div class="layui-input-inline">
					        	<input type="email" name="email" required  lay-verify="email" autocomplete="off" class="layui-input">
					        </div>
					        <div class="layui-form-mid layui-word-aux">将会成为您唯一的登陆名</div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">昵称</label>
						    <div class="layui-input-inline">
						      <input type="text" name="name" required lay-verify="required" autocomplete="off" class="layui-input">
						    </div>
					   </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">密码</label>
						    <div class="layui-input-inline">
						      <input type="password" name="pwd" required lay-verify="required" autocomplete="off" class="layui-input">
						    </div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">确认密码</label>
						    <div class="layui-input-inline">
						      <input type="password" name="repwd" required lay-verify="required" autocomplete="off" class="layui-input">
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
						    <button class="layui-btn" lay-submit lay-filter="regForm">立即注册</button>
					    </div>
					    <div class="layui-form-item" style="padding-top: 20px;">
						    <span>或者使用社交账号快捷注册</span>
						    <a href="#" onclick="layer.msg('暂时不支持该操作', {icon:2})">
								<img class="login-app" src="../img/logo2.png"></img>
							</a>
							<a href="#" login-app" onclick="layer.msg('暂时不支持该操作', {icon:2})">
								<img class="login-app"src="../img/logo1.png"></img>
							</a>
					    </div>
					</form>
    			</div>
    			<div class="layui-tab-item">
    				<form class="layui-form layui-form-pane" method="post">
    					<div class="layui-form-item">
					    	<label class="layui-form-label">邮箱</label>
				    		<div class="layui-input-inline">
				        		<input type="email" name="email" required  lay-verify="email" autocomplete="off" class="layui-input">
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
				   		<button class="layui-btn" lay-submit lay-filter="resetPwd">提交</button>
				  	 	</div>
					</form>
    			</div>
		    </div>
		</div>	
	</div>
	</body>
</html>