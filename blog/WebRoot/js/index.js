var _skin = localStorage.getItem("_skin")=='null'?0:localStorage.getItem("_skin");//皮肤
$(function(){
	layui.use(['element','layer','util'], function(){
		  var element = layui.element;
		  var layer = layui.layer;
		  layui.util.fixbar({
			  bar1:'&#xe612',
		      top: true,
		      click:function(type){
		          if(type === 'bar1'){
		        	  var member = sessionStorage.getItem('member');
		        	  var url = '';
		        	  if (member==null || member=='') url = _contextPath+"/member/loginPage.do";
		        	  else url = _contextPath+"/member/getUser.do";
		        	  
		        	  layer.open({
		        		  type: 2,
		        		  title: false,
		        		  anim: 4,
		        		  shadeClose: true,
		        		  offset: '50px',
		        		  area: ['720px', '520px'],
		        		  content:[url, 'no']
		        	  });   
		          }
		      }
		  });
		  loadFixbar();//右下角登陆图片
	});
	
	
	//文章分类按钮
	$("#log").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src",_contextPath+"/bean/type.do?type=1");
		
	});
	
	$("#note").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src",_contextPath+"/bean/type.do?type=2");
	});
	
	$("#chat").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src",_contextPath+"/bean/type.do?type=3");
	});
	
	//换肤
	$("#skin_a").click(function(e){
		x = e.pageX;
		layer.open({
			title: '',
			resize:false,
			type: 1,
			closeBtn: 0,
			shadeClose: true,
			offset:[0,x-480],
			area: ['480px','300px'],
			anim: 3,
  			content:'<img class="skin_btn" src="'+_contextPath+'/img/skin7.jpg" width="320px" height="100px"  onclick="skin(\'7\')" title="女神高圆圆")>'+
  					'<img class="skin_btn" src="'+_contextPath+'/img/skin6.jpg" width="160px" height="100px"  onclick="skin(\'6\')" title="全景图片")>'+
  					'<img class="skin_btn" src="'+_contextPath+'/img/skin5.jpg" width="160px" height="100px"  onclick="skin(\'5\')" title="")>'+
		  			'<img class="skin_btn" src="'+_contextPath+'/img/skin4.jpg" width="160px" height="100px"  onclick="skin(\'4\')" title="I love you")>'+
		  			'<img class="skin_btn" src="'+_contextPath+'/img/skin3.jpg" width="160px" height="100px"  onclick="skin(\'3\')" title="女神林心如")>'+
		  			'<img class="skin_btn" src="'+_contextPath+'/img/skin2.jpg" width="160px" height="100px"  onclick="skin(\'2\')" title="一品芝麻狐")>'+
		  			'<img class="skin_btn" src="'+_contextPath+'/img/skin1.jpg" width="160px" height="100px"  onclick="skin(\'1\')" title="微微一笑很倾城")>'+
		  			'<img class="skin_btn" src="'+_contextPath+'/img/skin0.jpg" width="160px" height="100px"  onclick="skin(\'0\')" title="默认样式")>'
		}); 
	});
	skin(_skin);
});

/*设置iframe高度*/
function setIframeHeight() {
	var iframe = document.getElementById("view");
	if (iframe) {
		var iframeWin = iframe.contentWindow;
		if (iframeWin.document.body) {
			iframe.height = Math.max(iframeWin.document.documentElement.offsetHeight,iframeWin.document.documentElement.scrollHeight, iframeWin.document.body.scrollHeight);
		}
	}
};

function over(num) {
	$("#logo"+num).attr('src',_contextPath+'/img/logo'+num+'_h.png');
}
function out(num) {
	$("#logo"+num).attr('src',_contextPath+'/img/logo'+num+'.png');
}
function skin(skin) {
	_skin = skin;
	localStorage.setItem("_skin",skin);
	if (skin=='0') {$('#skin').css('background','#FFFFFF');$('.right,.left').css('opacity','1');$('#view').css('opacity','1');}
	else {
		var url = 'url('+_contextPath+'/img/skin'+skin+'.jpg)';
		$('#skin').css('background',url);$('.right,.left').css('opacity','0.8');$('#view').css('opacity','0.8');
	}
	layer.closeAll();
}

function weixin() {
	layer.open({
		type: 1,
		offset: '100px',
		title: false,
		shadeClose: true,
		closeBtn: true,
		area: ['512px', '512px'],
		content: '<img src="'+_contextPath+'/img/weixin.jpg">'
	});
}

function loadFixbar() {
	var member = JSON.parse(sessionStorage.getItem('member'));
	if (member!=null && member!='')
		$(".layui-fixbar li:first").html('<img src="'+_contextPath+'/img'+member.img+'" width="36px" height="36px" style="border-radius:50%;")>');
	else 
		$(".layui-fixbar li:first").html('&#xe612;');
}

function getStatus() {
	document.getElementById('view').contentWindow.getStatus();
}

function noExploit() {
	layer.msg('该模块还在开发中。。。', {icon: 5});
}

