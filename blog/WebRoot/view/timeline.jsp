<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>时间轴</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page.css" />
	</head>
	<body>
		<div class="left">
			<span class="layui-breadcrumb">
			  <a href="${pageContext.request.contextPath}/main.do">&nbsp;<i class="layui-icon">&#xe68e;</i></a>
			  <a><cite>时光轴</cite></a>
			</span>
			<br /><br /><br />
			<ul class="layui-timeline" style="margin-left: 3px;">
			  <li class="layui-timeline-item">
			    <i class="layui-icon layui-timeline-axis">&#xe756;</i>
			    <div class="layui-timeline-content layui-text">
			      <h3 class="layui-timeline-title">2017年9月16日，清晨，发布。。。</h3>
			    </div>
			  </li>
			  <li class="layui-timeline-item">
			    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
			    <div class="layui-timeline-content layui-text">
			      <h3 class="layui-timeline-title">2017年8月，zuoqy博客 孵化。。。</h3>
			    </div>
			  </li>
			  <li class="layui-timeline-item">
			    <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis">&#xe63e;</i>
			    <div class="layui-timeline-content layui-text">
			      <h3 class="layui-timeline-title">更久前。。。</h3>
			    </div>
			  </li>
			</ul>
			<i class="layui-icon return" onclick="$('html,body', parent.document).animate({'scrollTop':0},100,function(){history.back();});">&#xe65c;</i>
  		</div>
	</body>
	<script>
		layui.use(['element']);
	</script>
</html>