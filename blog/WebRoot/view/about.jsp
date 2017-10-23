<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/page.css" />
	</head>
	<body>
		<div class="left">
			<span class="layui-breadcrumb">
			  <a href="${pageContext.request.contextPath}/main.do">&nbsp;<i class="layui-icon">&#xe68e;</i></a>
			  <a><cite>关于</cite></a>
			</span>
			<div class="title"><i class="layui-icon">&#xe636;</i>一枚90后java程序猿。。。</div>
			<div class="msg">
简介<br/><br/>
zuoqy博客是记录博主学习与成长，加一些闲言碎语，目前发布第一个版本。<br /><br /><br/>

博主<br/><br/>
普普通通双子座地球猿，没有太多华丽的背景，从事javaweb工作，崇拜自由工作者，却逃不掉早九晚五。<br /><br /><br/>

未来<br/><br/>
希望摆脱早九晚五，成为一个自由工作者，游遍中华大地。<br /><br /><br/>

须知<br />
<span style="color: red;">网站中的所有内容均系原创，如若转载请标明出处。</span>
			</div>
			<div class="author">
				<br /><br />
				<span style="color: #8D8D8D;">2017-09-14 12:12:12</span>
				<br />
				——zuoqy·北京&nbsp;&nbsp;
			</div>
			<i class="layui-icon return" onclick="$('html,body', parent.document).animate({'scrollTop':0},100,function(){history.back();});">&#xe65c;</i>
		</div>
	</body>
	<script>
		layui.use(['element']);
	</script>
</html>