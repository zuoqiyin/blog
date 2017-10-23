<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
		<script src="${pageContext.request.contextPath}/js/main.js"></script>	
	</head>
	<body>
		<div class="left">
			<div class="layui-carousel" id="carousel">
			    <div carousel-item>
				    <div><img src="${pageContext.request.contextPath}/img/carousel1.jpg" style="width: 100%;"></div>
				    <div><img src="${pageContext.request.contextPath}/img/carousel2.jpg" style="width: 100%;"></div>
				    <div><img src="${pageContext.request.contextPath}/img/carousel3.jpg" style="width: 100%;"></div>
				    <div><img src="${pageContext.request.contextPath}/img/carousel4.jpg" style="width: 100%;"></div>
				    <div><img src="${pageContext.request.contextPath}/img/carousel5.jpg" style="width: 100%;"></div>
			    </div>
			</div>
			<div class="recommend">文章推荐</div>
			<c:forEach items="${beans }" var="bean">
				<div class="hotspot">
					<img src="${bean.img }" width="160" height="132" style="margin-top: 12px;"/>
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
								<a href="${pageContext.request.contextPath}/bean/type.do?type=${bean.type }" style="color: green;">${bean.typeName }</a>
							</span>
							<span  class="hc_bg" style="background-position:0px -48px;">${bean.time }</span>
							<span class="hc_bg" style="margin-left:160px;background-position:0px -22px;">
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
</html>
