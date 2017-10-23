<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page.css" />
		<script src="${pageContext.request.contextPath}/js/page.js"></script>
	</head>
	<body>
		<div class="left">
			<span class="layui-breadcrumb">
			  <a href="${pageContext.request.contextPath}/main.do">&nbsp;<i class="layui-icon">&#xe68e;</i></a>
			  <a href="${pageContext.request.contextPath}/bean/type.do?type=${bean.type }">${bean.typeName }</a>
			  <a><cite>${bean.title }</cite></a>
			</span>
			<div class="title">${bean.title }</div>
			<div class="msg">
				${bean.msg }
				<br /><br />
				<span style="color: red;">如若转载请标明出处</span>
			</div>
			<div class="author">
				<br /><br />
				<span style="color: #8D8D8D;">${bean.time }</span>
				<br />
				——${bean.anthor }&nbsp;&nbsp;
			</div>
			<fieldset class="layui-elem-field layui-field-title" id="comment">
  				<legend>畅言一下</legend>
			</fieldset>
			<fieldset class="layui-elem-field">
  				<legend><img src="../img/log.png" width="30px" height="30px"/>zuoqy</legend>
				    <div class="layui-field-box">
				  		说了一堆<br /><br />
				  		<div class="field_bottom">
				  			<i class="layui-icon">&#xe60e;2017-09-19 08:36:22</i>
				  			<a><img src="../img/zan.png" onclick="dzan(this)" value="0"/>&nbsp;<span>0</span></a>
				  			&nbsp;
				  			<a href="javascript:huifu()"><img src="../img/huifu.png"/><span>回复</span></a>
				  			
				  		</div>
				    </div>
			</fieldset>
			<div id="userStatus" class="nav-user"></div>
			
			<div style="width: 660px;margin-left: 10px;">
				<textarea id="edit" style="display: none;"></textarea>
			</div>
			<button class="layui-btn">提交回复</button>
			<i class="layui-icon return" onclick="goBack()">&#xe65c;</i>
		</div>
	</body>
	<c:if test="${flag=='comment'}">
		<script type="text/javascript">
			$('html,body', parent.document).animate({"scrollTop":$('#comment').offset().top+150},300);
			
		</script>
	</c:if>
</html>
