<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>zuoqy博客</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
		<script src="${pageContext.request.contextPath}/js/index.js"></script>
	</head>
	<body>
		<div id="skin" class="skin"></div>
		
		<div class="head">
			<div style="margin: 0 auto;width: 1000px;">
				<i class="layui-icon" style="font-size: 18px;color: red;">&#xe60c;</i>
				<i style="font-size: 10px;line-height: 20px;">您好亲,欢迎光临zuoqy博客！</i>
				<a id="skin_a" title="换肤"><i class="layui-icon" style="font-size: 18px;float:right;margin-right: 20px;">&#xe61b;</i></a>
			</div>
		</div>
		
		<div class="title">
			<img src="${pageContext.request.contextPath}/img/log.png"/>
			<div class="log">
				zuoqy博客
			</div>
			
			<div class="search-form">
        		<div class="search-hd">
	            	<div class="search-bg"></div>
	            	<input type="text" class="search-input" onfocus="$('.s1').hide();" onblur="$('.s1').show();">
	            	<span class="s1 pholder">zuoqy博客发布了</span>
	            	<button id="submit" class="btn-search" value="搜索" onclick="noExploit()">搜索</button>
        		</div>
    		</div>
		</div>
		
		<div class="menu">
			<ul class="nav-menu clearfix unstyled">
			    <li>
			        <a href="${pageContext.request.contextPath}" class="three-d active">
			            Home
			            <div class="three-d-box">
			                <span class="front">首页</span>
			                <span class="back">首页</span>
			            </div>
			        </a>
			    </li>
			    <li>
	                <a href="${pageContext.request.contextPath}/bean/type.do?type=1" target="view" class="three-d">
	                	lo g 
	                    <div class="three-d-box">
	                        <span class="front">日志</span>
	                        <span class="back">日志</span>
	                    </div>
	                </a>
                </li>
                <li>
	                <a href="${pageContext.request.contextPath}/bean/type.do?type=3" target="view" class="three-d">
	                	chat
	                    <div class="three-d-box">
	                        <span class="front">闲谈</span>
	                        <span class="back">闲谈</span>
	                    </div>
	                </a>
                </li>
                <li>
	                <a href="${pageContext.request.contextPath}/bean/type.do?type=2" target="view" class="three-d">
	                	note
	                    <div class="three-d-box">
	                        <span class="front">笔记</span>
	                        <span class="back">笔记</span>
	                    </div>
	                </a>
                </li>
			    <li>
			    	<a href="${pageContext.request.contextPath}/view/about.jsp" target="view" class="three-d">
			    		about&nbsp;
			    		<div class="three-d-box">
			                <span class="front">关于</span>
			                <span class="back">关于</span>
			            </div>
			    	</a>
			    </li>
			    <li>
			    	<a href="${pageContext.request.contextPath}/view/timeline.jsp" target="view" class="three-d">
			    		timeline
			    		<div class="three-d-box">
			                <span class="front">时间轴</span>
			                <span class="back">时间轴</span>
			            </div>
			    	</a>
			    </li>
			    <li>
			    	<a href="${pageContext.request.contextPath}/view/message.jsp" target="view" class="three-d">
			    		Feedback
			    		<div class="three-d-box">
			                <span class="front">留言反馈</span>
			                <span class="back">留言反馈</span>
			            </div>
			    	</a>
			    </li>
			    <li style="float:right;margin-right: -20px;">
			    	<a href="javascript:weixin()" class="three-d">
			    	<i class="layui-icon" style="font-size: 20px;">&#xe63a;</i>
			    	小编微信
			    	</a>
			    </li>
			</ul>
		</div>
		
		<div class="context">
			<div style="width:700px;float: left;">
				<c:if test="${code==1 }">
				<input type="hidden" id="email" value="${email }">
				<iframe id="view" name="view" src="${pageContext.request.contextPath}/member/resetPwdPage.do?email=${email}&token=${token}" style="width: 100%;border: 0px;" marginheight="0" marginwidth="0" scrolling="no" 
					onload="var h =$(this).contents().find('html,body').height();$(this).height(h>1580?h+30:h+800);skin(_skin);"></iframe>
				</c:if>
				<c:if test="${code==2 }">
				<iframe id="view" name="view" src="${pageContext.request.contextPath}/member/activateSuccess.do?email=${email}" style="width: 100%;border: 0px;" marginheight="0" marginwidth="0" scrolling="no" 
					onload="var h =$(this).contents().find('html,body').height();$(this).height(h>1580?h+30:h+800);skin(_skin);"></iframe>
				</c:if>
			</div>
			<div class="right">
				<div class="sTitle">关注我</div>
				<div class="attention">
					<div style="height: 18px;"></div>
					<a href="http://weibo.com/zuoqiyin" target="_blank" title="新浪微博" onmouseover="over(1)" onmouseout="out(1)">
						<img id="logo1" src="${pageContext.request.contextPath}/img/logo1.png" width="50px" height="50px" style="margin-left: 5px; ">
					</a>
					<a href="http://shang.qq.com/email/stop/email_stop.html?qq=2065820975" target="_blank" title="腾讯QQ" onmouseover="over(2)" onmouseout="out(2)">
						<img id="logo2" src="${pageContext.request.contextPath}/img/logo2.png" width="50px" height="50px" style="margin-left: 20px;">
					</a>
					<a href="https://github.com/zuoqiyin" target="_blank" title="GitHub" onmouseover="over(3)" onmouseout="out(3)">
						<img id="logo3" src="${pageContext.request.contextPath}/img/logo3.png" width="50px" height="50px" style="margin-left: 20px;">
					</a>
					<a href="mailto:zuoqy@zuoqy.cn" target="_blank" title="邮箱" onmouseover="over(4)" onmouseout="out(4)">
						<img id="logo4" src="${pageContext.request.contextPath}/img/logo4.png" width="50px" height="50px" style="margin-left: 20px;">
					</a>
					<a href="http://weibo.com/zuoqiyin" target="_blank"><span class="logoFont" style="margin-left: 8px;">新浪微博</span></a>
					<a href="http://shang.qq.com/email/stop/email_stop.html?qq=2065820975" target="_blank"><span class="logoFont" style="margin-left: 34px;">腾讯QQ</span></a>
					<a href="https://github.com/zuoqiyin" target="_blank"><span class="logoFont" style="margin-left: 36px;">GitHub</span></a>
					<a href="mailto:zuoqiyin@163.com"><span class="logoFont" style="margin-left: 42px;">邮箱</span></a>
				</div>
				
				<img src="${pageContext.request.contextPath}/img/gg1.jpg" width="280px" height="130px"/>
				
				<div class="tab">
					<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					  <ul class="layui-tab-title">
					    <li class="layui-this">站长推荐</li>
					    <li>点击排行</li>
					  </ul>
					  <div class="layui-tab-content">
					  	    <div class="layui-tab-item layui-show">
					  	    	<div style="border-bottom: 2px solid #E7E7E7; height: 22px;margin-top: 10px;">
					  	    		<span class="layui-badge-rim layui-bg-red">1</span>&nbsp;
					  	    		<a href="http://www.layui.com/" target="_blank">经典模块化前端框架-layui</a>
					  	    	</div>
					  	    	<div style="border-bottom: 2px solid #E7E7E7; height: 22px;margin-top: 10px;">
					  	    		<span class="layui-badge-rim layui-bg-orange">2</span>&nbsp;
					  	    		<a href="${pageContext.request.contextPath}/bean/getBean.do?id=0001" target="view"><i class="layui-icon">&#xe609;</i>终于等来了,zuoqy博客v0.1！</a>
					  	    	</div>
					  	    	<div style="border-bottom: 2px solid #E7E7E7; height: 22px;margin-top: 10px;">
					  	    		<span class="layui-badge-rim layui-bg-green">3</span>&nbsp;
					  	    		<a href="${pageContext.request.contextPath}/bean/getBean.do?id=0002" target="view">PL/SQL入门</a>
					  	    	</div>
					  	    </div>
    						<div class="layui-tab-item">
    							<div style="border-bottom: 2px solid #E7E7E7; height: 22px;margin-top: 10px;">
					  	    		<span class="layui-badge-rim layui-bg-red">1</span>&nbsp;
					  	    		<a href="${pageContext.request.contextPath}/bean/getBean.do?id=0002" target="view">PL/SQL入门</a>
					  	    	</div>
					  	    	<div style="border-bottom: 2px solid #E7E7E7; height: 22px;margin-top: 10px;">
					  	    		<span class="layui-badge-rim layui-bg-orange">2</span>&nbsp;
					  	    		<a href="${pageContext.request.contextPath}/bean/getBean.do?id=0001" target="view"><i class="layui-icon">&#xe609;</i>终于等来了,zuoqy博客v0.1！</a>
					  	    	</div>
    						</div>
					  </div>
					</div>      
				</div>
				
				<div class="sTitle">文章分类</div>
				<div class="attention" style="100px">
					<div style="height: 10px;"></div>
					<button id="log" class="layui-btn layui-btn-radius layui-btn-danger layui-btn-small" style="float: left;">发布日志</button>
					<button id="chat" class="layui-btn layui-btn-radius layui-btn-normal layui-btn-small" style="float: left;">闲言碎语</button>
					<button id="note" class="layui-btn layui-btn-radius layui-btn-warm layui-btn-small" style="float: left;">技术笔记</button>
				</div>
				
				<img src="${pageContext.request.contextPath}/img/gg1.jpg" width="280px" height="110px"/>
			</div>
		</div>
		
		<div class="foot">
			<div style="margin: 0 auto;width: 1000px;text-align: center;color: #92B8B1;">
				Copyright&nbsp;©2017&nbsp;&nbsp;&nbsp;zuoqiyin&nbsp;&nbsp;&nbsp;京ICP备17058348号
			</div>
		</div>
	</body>
</html>
