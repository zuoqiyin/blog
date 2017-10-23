<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人主页</title>
		<link rel="stylesheet" href="../css/user.css" />
		<script src="../js/member.js"></script>
	</head>
	<body>
	<div class="con">
		<div id="exit">
			<a href="javascript:exit()"><img title="退了" alt="退了" src="../img/exit.png"></a>
		</div>
		<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		    <ul class="layui-tab-title">
		    	<li class="layui-this" id="myInfo">我的资料</li>
		    	<li>头像</li>
		    	<li>密码</li>
		    	<li id="activeEmail" style="display: none">激活邮箱</li>
		    </ul>
		    <div class="layui-tab-content">
		    	<!-- 我的资料 -->
		    	<div class="layui-tab-item layui-show">
			    	<form class="layui-form layui-form-pane" method="post">
			    		<div class="layui-form-item">
						    <label class="layui-form-label">邮箱</label>
					    	<div class="layui-input-inline">
					        	<input type="text" name="email" required  lay-verify="email" autocomplete="off" class="layui-input" value="${member.email }">
					        </div>
					        <div class="layui-form-mid layui-word-aux">如果您在邮箱已激活的情况下,变更了邮箱,需要<a class="verify" id="verify">重新激活邮箱。</a></div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">昵称</label>
					    	<div class="layui-input-inline">
					        	<input type="text" name="name" required  lay-verify="required" autocomplete="off" class="layui-input" value="${member.name }">
					        </div>
				            <div class="layui-input-inline">
						    	<input type="radio" name="sex" value="1" title="男" <c:if test="${member.sex==1 }">checked</c:if> >
						    	<input type="radio" name="sex" value="2" title="女" <c:if test="${member.sex==2 }">checked</c:if>>
						    </div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">城市</label>
					    	<div class="layui-input-inline">
					        	<input type="text" name="city" required  lay-verify="required" autocomplete="off" class="layui-input" value="${member.city }">
					        </div>
					    </div>
					    <div class="layui-form-item layui-form-text">
						    <label class="layui-form-label">个性签名</label>
						    <div class="layui-input-block">
						    	<textarea name="sign" placeholder="随便写些什么刷下存在感" class="layui-textarea" style="max-height: 200px" >${member.sign }</textarea>
						    </div>
					   	</div>
					   	<div class="layui-form-item">
						   	<div class="layui-input-inline">
								<button class="layui-btn" lay-submit lay-filter="formInfo">确认修改</button>
							</div>
					   	</div>
					</form>
		    	</div>
		    	<!-- 头像 -->
    			<div class="layui-tab-item">
    				<div class="pic">
    					<p>建议尺寸168*168,支持jpg,png,gif,最大不能超过50kb</p>
    					<button class="layui-btn upload-img" id="upload"><i class="layui-icon">&#xe67c;</i>上传图像</button>
    					<form enctype="multipart/form-data" id="upload-form">
    						<input class="layui-upload-file" name="file" type="file" id="upload-file">
    					</form>
    					<img id="upload-img" src="${pageContext.request.contextPath}/img/${member.img}">
    				</div>
    			</div>
    			<!-- 密码 -->
    			<div class="layui-tab-item">
    				<form class="layui-form layui-form-pane" method="post">
    					<div class="layui-form-item">
						    <label class="layui-form-label">新密码</label>
					    	<div class="layui-input-inline">
					        	<input type="password" name="pwd" required  lay-verify="required" autocomplete="off" class="layui-input">
					        </div>
					        <div class="layui-form-mid layui-word-aux">尽量复杂,对自己负责</div>
					    </div>
					    <div class="layui-form-item">
						    <label class="layui-form-label">确认密码</label>
					    	<div class="layui-input-inline">
					        	<input type="password" name="repwd" required  lay-verify="required" autocomplete="off" class="layui-input">
					        </div>
					        <div class="layui-form-mid layui-word-aux"></div>
					    </div>
					    <div class="layui-form-item">
						   	<div class="layui-input-inline">
								<button class="layui-btn" lay-submit lay-filter="formPwd">确认修改</button>
							</div>
					   	</div>
    				</form>
    			</div>
    			<!-- 激活邮箱 -->
    			<div class="layui-tab-item">
    				<c:choose>
    					<c:when test="${member.isActivate=='Y' }">
    						<br />
    						<p>您的邮箱：${member.email }&nbsp;<i style="color: #B1B1B1">(已成功激活)</i></p>
    					</c:when>
    					<c:otherwise>
    						<br />
    						<p>您的邮箱：${member.email }&nbsp;<i style="color: red">(尚未激活)</i></p>
							<br /><br />
    						<p>1.如果您未收到邮件，或激活链接失效，您可以<a class="sendMail">重新发送邮件</a>，或者<a class="updateEmail">更换邮箱； </a></p>
    						<br />
    						<p>2. 如果您始终没有收到 zuoqy博客 发送的邮件，请注意查看您邮箱中的垃圾邮件；</p>
    						<br />
    						<p>3. 如果你实在无法激活邮件，您还可以联系：zuoqy@zuoqy.cn </p>

    					</c:otherwise>
    				</c:choose>
    			</div>
		    </div>
		</div>	
	</div>
	</body>
</html>