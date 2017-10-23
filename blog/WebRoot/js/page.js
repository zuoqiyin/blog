$(function(){
	layui.use(['code','element','layedit'],function(){
		layui.code({
			title: 'PL/SQL',
			skin:'notepad'
		});
		
		var layedit = layui.layedit;
		layedit.build('edit',{height:180,tool:['face','image','link','|','strong','italic','underline','del']}); //建立编辑器
		getStatus();//加载用户登陆信息
	});
});

function login() {
	  var url = _contextPath+"/member/loginPage.do" ;
	  parent.layer.open({
		  type: 2,
		  title: false,
		  anim: 4,
		  shadeClose: true,
		  offset: '50px',
		  area: ['720px', '520px'],
		  content:[url, 'no']
	  });
}

function reg() {
	  var url = _contextPath+"/member/regPage.do" ;
	  parent.layer.open({
		  type: 2,
		  title: false,
		  anim: 4,
		  shadeClose: true,
		  offset: '50px',
		  area: ['720px', '520px'],
		  content:[url, 'no']
	  });
}

function getUser() {
	var url = _contextPath+"/member/getUser.do" ;
	  parent.layer.open({
		  type: 2,
		  title: false,
		  anim: 4,
		  shadeClose: true,
		  offset: '50px',
		  area: ['720px', '520px'],
		  content:[url, 'no']
	  });
}

function exit(){
	$.ajax({
		async: true,
		url: _contextPath+"/member/exit.do",
		success: function(result) {
			if (result.code==1) {
				sessionStorage.removeItem("member");
				parent.layer.closeAll();
				parent.loadFixbar();
				parent.getStatus();
			}
		}
	});
}

function goBack() {$('html,body', parent.document).animate({"scrollTop":0},100,function(){history.back();});}

function dzan(img) {
	if ($(img).attr('value')==0) {
		$(img).attr('src','/Blog/img/zan_d.png');
		$(img).attr('value',1);
		$(img).next().text(parseInt($(img).next().text())+1);
	} else {
		$(img).attr('src','/Blog/img/zan.png');
		$(img).attr('value',0);
		$(img).next().text(parseInt($(img).next().text())-1);
	}
}

function huifu(){
	layer.msg('该功能还在开发中。。。', {icon: 5});
}

function getStatus() {
	var member = JSON.parse(sessionStorage.getItem('member'));
	var html = '';
	if (member!=null && member!='') {//登陆状态
		html = '<a class="unlogin" href="javascript:getUser()">						 '+
			   '	<img src="../img'+member.img+'" alt="'+member.name+'">		     '+
		       '</a>															     '+
		       '<a href="javascript:getUser()">'+member.name+'</a>				     '+
		       '<a class="unlogin" href="javascript:getUser()">                      '+
		       '	<img src="../img/setting.png" style="width: 20px;height: 20px">  '+
		       '</a>															     '+
		       '<a href="javascript:getUser()">设置</a>                               '+
		       '<a class="unlogin" href="javascript:exit()">						 '+
		       '	<img src="../img/exit.png" style="width: 20px;height: 20px">     '+
		       '</a>																 '+
		       '<a href="javascript:exit()">退了</a>								  ';
		
	} else {//非登陆状态
		html = '<a class="unlogin" href="javascript:login()">         '+
			   '	<img src="../img/login.png"></img>                '+
			   '</a>                                                  '+
			   '<span>                                                '+
			   '<a href="javascript:login()">登入</a>                  '+
			   '<a href="javascript:reg()">注册</a>                    '+
			   '<i>或者直接使用社交账号快捷注册</i>                      '+
			   '<a href="javascript:noSup();">                    '+
			   '	<img class="qq" src="../img/logo2.png" title="暂时不支持"></img>     '+
			   '</a>                                                  '+
			   '<a href="javascript:noSup();">                    '+
			   '	<img class="qq" src="../img/logo1.png" title="暂时不支持"></img>     '+
			   '</a>                                                  ';
	}
	$("#userStatus").html(html);
}

function noSup(){
	parent.layer.msg('暂不支持该方式',{icon: 5,anim:6,offset:'200'});
}