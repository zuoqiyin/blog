var _skin = localStorage.getItem("_skin")=='null'?0:localStorage.getItem("_skin");//皮肤
$(function(){
	//右侧回到顶部
	layui.util.fixbar({
		bar1:'&#xe612',
	    top: true
	});
	
	$("#log").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src","bean/class/log.html");
		
	});
	
	$("#note").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src","bean/class/note.html");
	});
	
	$("#chat").click(function(){
		$("html,body").animate({"scrollTop":0},300);
		$("#view").attr("src","bean/class/chat.html");
	});
	
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
  			content:'<img class="skin_btn" src="img/skin7.jpg" width="320px" height="100px"  onclick="skin(\'7\')" title="女神高圆圆")>'+
  					'<img class="skin_btn" src="img/skin6.jpg" width="160px" height="100px"  onclick="skin(\'6\')" title="全景图片")>'+
  					'<img class="skin_btn" src="img/skin5.jpg" width="160px" height="100px"  onclick="skin(\'5\')" title="")>'+
		  			'<img class="skin_btn" src="img/skin4.jpg" width="160px" height="100px"  onclick="skin(\'4\')" title="I love you")>'+
		  			'<img class="skin_btn" src="img/skin3.jpg" width="160px" height="100px"  onclick="skin(\'3\')" title="女神林心如")>'+
		  			'<img class="skin_btn" src="img/skin2.jpg" width="160px" height="100px"  onclick="skin(\'2\')" title="一品芝麻狐")>'+
		  			'<img class="skin_btn" src="img/skin1.jpg" width="160px" height="100px"  onclick="skin(\'1\')" title="微微一笑很倾城")>'+
		  			'<img class="skin_btn" src="img/skin0.jpg" width="160px" height="100px"  onclick="skin(\'0\')" title="默认样式")>'
		}); 
	});
	
});

function over(num) {
	$("#logo"+num).attr('src','img/logo'+num+'_h.png');
}
function out(num) {
	$("#logo"+num).attr('src','img/logo'+num+'.png');
}
function skin(skin) {
	_skin = skin;
	localStorage.setItem("_skin",skin)
	if (skin=='0') {$('#skin').css('background','#FFFFFF');$('.right,.left').css('opacity','1');$('#view').css('opacity','1');}
	else {
		var url = 'url(img/skin'+skin+'.jpg)'
		$('#skin').css('background',url);$('.right,.left').css('opacity','0.8');$('#view').css('opacity','0.8');
	}
	layer.closeAll();
}

function weixin() {
	layer.open({
		type: 1,
		title: false,
		shadeClose: true,
		closeBtn: true,
		area: ['512px', '512px'],
		content: '<img src="img/weixin.jpg">'
	});
}
function noExploit() {
	layer.msg('该模块还在开发中。。。', {icon: 5});
}

