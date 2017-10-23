<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>日志分类</title>
		<link rel="stylesheet" href="../css/main.css" />
	</head>
	<body>
		<div class="left">
			<span class="layui-breadcrumb">
			  <a href="${pageContext.request.contextPath}/main.do">&nbsp;&nbsp;<i class="layui-icon">&#xe68e;</i></a>
			  <a><cite>${beans[0].typeName }</cite></a>
			</span>
			<c:forEach items="${beans }" var="bean">
				<div class="hotspot">
					<img src="../${bean.img }" width="160" height="132" style="margin-top: 12px;"/>
					<div class="hotspot_context">
						<a href="${pageContext.request.contextPath}/bean/getBean.do?id=${bean.id}">
							<span class="hc_title">${bean.title}</span>
						</a>
						<span class="hc_msg">
							${bean.msgSmall }
							<a href="${pageContext.request.contextPath}/bean/getBean.do?id=${bean.id}" style="color: green;">【详情】</a>
						</span>
						<span>
							<span class="hc_bg" style="background-position:0px 4px;display: inline-block;">
								<a href="#" style="color: green;">${bean.typeName }</a>
							</span>
							<span  class="hc_bg" style="background-position:0px -48px;">${bean.time }</span>
							<span class="hc_bg" style="margin-left:120px;background-position:0px -22px;">
								<a href="${pageContext.request.contextPath}/bean/getBean.do?id=${bean.id}&flag=comment" style="color: #8D8D8D;">评论(${bean.commentCount })</a>
							</span>
							<span class="hc_bg" style="background-position:0px -72px;">
								<a style="color: #8D8D8D;">浏览(${bean.viewCount })</a>
							</span>
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</body>
	<script>
		layui.use(['element']);
	</script>
</html>