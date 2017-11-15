$(function(){
	layui.use(['code','element','layedit'],function(){
		layui.code({
			title: 'PL/SQL',
			skin:'notepad'
		});
		
		var layedit = layui.layedit;
		layedit.build('edit',{height:180,tool:['face','image','link','|','strong','italic','underline','del']}); //建立编辑器
	});
});

function dzan(img) {
	if ($(img).attr('value')==0) {
		$(img).attr('src','/Blog/img/zan_d.png')
		$(img).attr('value',1);
		$(img).next().text(parseInt($(img).next().text())+1)
	} else {
		$(img).attr('src','/Blog/img/zan.png')
		$(img).attr('value',0);
		$(img).next().text(parseInt($(img).next().text())-1)
	}
}

function huifu(){
	layer.msg('该功能还在开发中。。。', {icon: 5});
}
