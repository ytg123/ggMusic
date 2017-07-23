//模板加载
function getTemplates(){
	var periodId = $("#periodId").val(),
		pageNo = 0,
		pageSize=5;
	$("#comment-list").load("/nocomment/template/"+periodId+"/"+pageNo+"/"+pageSize,function(data){
		if(isEmpty(data)){
			$(this).html("<h1>暂无评论...</h1>");
		}
	});
}
window.onload = function(){
	//加载评论模板
	getTemplates();
	$("body").click(function(e){
		$("#tm_login_dialog").fadeOut("slow",function(){
			$(this).remove();
		});
		stopBubble(e);
	});
};
//音乐操作
Gon(window,"load",function(){
	//音轨解析
	var ggM = {
		mark:false,
		
		init:function(){//ie11以上的浏览器才支持 
			//1:音频上下文===html5+ajax+audioContext   html5+audio+audioContext  
			window.AudioContext = window.AudioContext || window.webkitAudioContext || window.mozAudioContext || window.msAudioContext;
			/*动画执行的兼容写法*/
			window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.msRequestAnimationFrame;
			//2:初始化音轨对象
			var audioContext = new window.AudioContext();
			return audioContext;
		},
		
		parse:function(audioContext,audioDom,callback){
			try{
				//拿到播放器去解析你音乐文件
				var audioBufferSouceNode = audioContext.createMediaElementSource(audioDom);
				//创建解析对象
				var analyser = audioContext.createAnalyser();
				//将source与分析器连接
				audioBufferSouceNode.connect(analyser); 
				//将分析器与destination连接，这样才能形成到达扬声器的通路
				analyser.connect(audioContext.destination);
				//调用解析音频的方法
				ggM.data(analyser,callback);
			}catch(e){
				
			}
		},
		
		data:function(analyser,callback){
			if(ggM.mark){
				//讲音频转换一个数组
				var array = new Uint8Array(analyser.frequencyBinCount);
				analyser.getByteFrequencyData(array);
				//通过回调函数返回
				if(callback)callback(array);
				requestAnimationFrame(function(){
					ggM.data(analyser,callback);
				});
			}
		}
	};
	//分栏
	var subfield = {
		arr:[],
		mw:3,//每栏的宽度
		init:function(){
			//获取wrapbox对象
			var wrapDom = $("#wrapbox");
			//宽度
			var w = wrapDom.width();
			//列数
			var cells = Math.floor(w / this.mw);
			for(var i = 0;i<=cells;i++){
				var wbDom = $("<div class='myitem' style='width:"+this.mw+"px;left:"+(i * this.mw)+"px;'></div>");
				wrapDom.append(wbDom);
				subfield.arr.push(wbDom);
			}
		}
	};
	//页面加载播放第一首歌
	var gli = $("#ul").find("li").eq(0);
	$("#musicbjF").attr("src","/"+gli.data("img"));
	$(".m_art1").text(gli.data("article"));
	$(".m_author1").text(gli.data("singer"));
	$(".m_title1").text(gli.data("title"));
	$("#indexM").val(1);
	var music = new Music("audio",gli.data("src"),gli.data("title"),gli.data("singer"));
	//音乐列表循环点击
	gEach(dom("ul"),function(){
		Gon(this,"click",function(index){
			//获取本对象的data-src的值
			//var src = this.dataset.src;
			//索引
			var index= this.index;
			//获取本对象的文本值
			var img = $(this).data("img"),
				title = $(this).data("title"),
				article = $(this).data("article"),
				singer = $(this).data("singer"),
				src = $(this).data("src");
			//赋值
			$(".m_art1").text(article);
			$(".m_author1").text(singer);
			$("#musicbjF").attr("src","/"+img);
			$(".m_title1").text(title);
			$("#audio").attr("src",src);
			$("#indexM").val(index+1);
			//调用播放函数
			playS();
		});
	});
	//音乐初始化
	music.init(function(n){
		//初始化音量
		var volmn = n * 100 + "%";
		dom("pcs2").style.width = volmn;
		dom("dot2").style.left = (n-0.04) * 100 + "%";
	});
	//时间
	music.time(function(merk){
		if(merk == "time"){
			//总时长
			dom("ztime").innerHTML = this.fztime;
		}else if(merk == "current"){
			//当前播放时间
			dom("current").innerHTML = this.fcurrent;
			//进度条
			dom("pcs").style.width = (this.percent) + "%";
			//小圆点
			var maxwd = dom("dot").parentElement.offsetWidth - dom("dot").offsetWidth;
			dom("dot").style.left = ((this.percent - 1)*maxwd)/100 + "px";						
		}
		
	});
	//进度条拖拽
	music.drag(dom("dot"),function(bit){
		if(this.played){
			this.pause();
			dom("playStop").className = "glyphicon glyphicon-play";
		}
		//拖拽改变音乐
		this.currentTime = this.duration * bit;
	},function(){
		if(this.paused){
			this.play();
			dom("playStop").className = "glyphicon glyphicon-pause";
		}
	});
	//音量
	music.drag(dom("dot2"),function(bit){
		//拖拽改变音乐
		dom("pcs2").style.width = bit * 100 + "%";
		this.volume = bit;
	});
	//播放音乐
	playS();
	//播放暂停
	dom("playStop").onclick = playS;
	function playS(){
		//获取audio对象
		var audioDom = music.audioDom;
		//解析对象
		var audioContext = ggM.init();
		//切换按钮样式
		$(this).toggleClass("glyphicon-play  glyphicon-pause");
		//ggAudio[$(this).hasClass("glyphicon-pause")?"play":"stop"]()
		if(music.audioDom.paused){
			music.play();
			dom("playStop").className = "glyphicon glyphicon-pause";
			ggM.mark = true;
			ggM.parse(audioContext,audioDom,function(dataArr){
				for(var i = 0,len = $("#wrapbox").children().length;i<len;i++){
					subfield.arr[i].css({"height":""+dataArr[i]+"px"});
				}
			});
		}else{
			music.stop();
			dom("playStop").className = "glyphicon glyphicon-play";
			//gg.mark = false;
		}
	};
	//静音
	dom("mut").onclick = function(){
		var $this = this;
		music.muted(function(mark){
			if(mark){
				$this.className = "fr glyphicon glyphicon-volume-off";
			}else{
				$this.className = "fr glyphicon glyphicon-volume-up";
			}
		});
	};
	//下一首
	var gindex = $("#indexM").val();
	Gon(dom("next"),"click",function(){
		gindex++;
		if(gindex > $("#ul").children().length)gindex=1;
		var nli = $("#ul").children().eq(gindex-1);
		$(".m_art1").text(nli.data("article"));
		$(".m_author1").text(nli.data("singer"));
		$("#musicbjF").attr("src","/"+nli.data("img"));
		$(".m_title1").text(nli.data("title"));
		$("#audio").attr("src",nli.data("src"));
		$("#indexM").val(gindex+1);
		//调用播放功能
		playS();
	});
	//上一首
	Gon(dom("prev"),"click",function(){
		gindex--;
		if(gindex <= 0)gindex=$("#ul").children().length;
		var nli = $("#ul").children().eq(gindex-1);
		$(".m_art1").text(nli.data("article"));
		$(".m_author1").text(nli.data("singer"));
		$("#musicbjF").attr("src","/"+nli.data("img"));
		$(".m_title1").text(nli.data("title"));
		$("#audio").attr("src",nli.data("src"));
		$("#indexM").val(gindex+1);
		//调用播放功能
		playS();
	});

	subfield.init();
	$("#wrapbox").find(".myitem").css("background","linear-gradient(to right,"+getRandomColor()+"10%,#8EC480 50%,pink 40%,#6FACDD 20%)");
});
//保存评论
function tz_savePl(obj,e){
	stopBubble(e);
	var user = getInfoUser();
	if(isEmpty(user.headerPic)){
		tm_loginDialog(obj);
		return;
	}
	var periodId = $("#periodId").val(),
		content = $("#commentEditor").val();
	if(isEmpty(periodId)){
		//加载动画
		loading("没能找到对应的期刊!",3);
		return false;
	}
	if(isEmpty(content)){
		loading("请输入评论!",3);
		$("#commentEditor").select();
		return false;
	}
	//防止重复提交
	$(obj).text("提交中...").removeAttr("onclick");
	$.ajax({
		type:"post",
		url:"/comment/save/"+periodId,
		data:{
			content:content
		},
		beforeSend:function(){
			loading("请稍后,数据提交中...");
		},
		error:function(){
			loading("remove");
			$(obj).text("提交").attr("onclick","tz_savePl(this)");
		},
		success:function(data){
			if(data == "success"){
				$(obj).text("提交").attr("onclick","tz_savePl(this)");
				loading("保存成功!",2);
				$("#commentEditor").val("");
				var user = getInfoUser();
				$("#comment-list").prepend("<div class='item animated rotateInUpLeft'>"+
								"<a href='javascript:;' class='avatar-wrapper' >"+
						"<img src='"+user.headerPic+"' alt='BLACK8' class='avatar rounded'>"+
					"</a>"+
					"<div class='item-wrapper'>"+
						"<p class='helper'>"+
							"<a href='javascript:;' class='username' target='_blank'>"+user.username+"</a>"+
						"</p>"+
						"<div class='comment-ct'>"+
							"<p class='the-comment'>"+content+"</p>"+
						"</div>"+
						"<div class='helper clearfix'>"+
							"1秒钟前"+
							"&nbsp;&nbsp;"+
							"来自:"+user.address+""+
							"&nbsp;&nbsp"+
							"<a href='javascript:;' class='btn-reply btn-action-reply'>回复</a>&nbsp;&nbsp;"+
							"<a class='btn-vote btn-action-vote' href='javascript:;'><span>赞</span></a>"+
						"</div>"+
					"</div>"+
				"</div>");
			}else if(data == "fail"){
				loading("保存失败",2);
			}else{
				//location.href = "/login";
				tm_loginDialog(obj);
			}
		}
		
	});
}
/*弹出登陆框*/
function tm_loginDialog(obj){
	var lefts = $(obj).offset().left ;
	var tops = $(obj).offset().top + $(obj).height()+20;
	$("#tm_login_dialog").remove();
	$("body").append("<div class='login_dialog' style='left:"+lefts+"px;top:"+tops+"px' id='tm_login_dialog'>"+
	"	<span class='corn'></span>"+
	"	<div class='msg_header'>"+
	"		<h1>广广音乐登录</h1>"+
	"	</div>"+
	"	<div class='msg_box'>"+
	"		<p><input type='text' maxLength='80' autoFocus='autoFocus' id='d_account' class='inp ua' placeholder='请输入账号...'></p>"+
	"		<p><input type='password' maxLength='20' id='d_password' class='inp' placeholder='请输入密码...'></p>"+
	"		<p style='padding-top: 20px;'><a href='javascript:void(0);' onclick='tm_dialog_login(this)' class='btnlogin'>登录</a></p>"+
	"		<p class='erromessage hide' id='d_message'>请输入账号和密码!!!</p>"+
	"	</div>	"+
	"</div>");
};
/*弹出登陆业务逻辑*/
function tm_dialog_login(obj){
	var account = $("#d_account").val();
	var password = $("#d_password").val();
	if(isEmpty(account)){
		 $("#d_account").focus();
		 showmessage("请输入账号!");
		 return false;
	}
	
	if(isEmpty(password)){
		 $("#d_password").focus();
		 showmessage("请输入密码!");
		 return false;
	}
	
	$.ajax({
		type:"post",
		beforeSend:function(){$(obj).off("click").css("padding","8px 101px").text("登陆中...");},
		error:function(){$(obj).on("click",tm_dialog_login).css("padding","8px 100px").text("登陆");},
		url:"/logined/"+account+"/"+password,
		success:function(data){
			if(data){
				//关闭登录框，提示登录成功
				$("#tm_login_dialog").fadeOut("slow",function(){
					$(this).remove();
				});
				tm_loginsuccess(data);//登录成功返回头像信息
			}else{
				showmessage("请正确输入账号和密码 ！");
				$("#d_account").select();
				$("#d_password").val("");
				$(obj).on("click",tm_dialog_login).css("padding","8px 100px").text("登陆");
			}
		}
	});
};
//登录成功
function tm_loginsuccess(user){
	$("#tz_usermessagebox").html("<span class='sgl_items'>"+
	"		<img src='"+user.headerPic+"' data-address='"+user.address+"'  id='userinfo' title='"+user.username+"' width='30' height='30' style='border-radius:50%;'>"+
	"		<a href='${basePath}/period/addMusic'>【"+user.username+"】</a><a href='javascript:void(0);' onclick='tm_logout(this)'>【退出】</a>"+
	"</span>");
};
//获取用户信息
function getInfoUser(){
	var img = $("#infoUser").attr("src"),
		username = $("#infoUser").attr("alt"),
		address = $("#infoUser").data("address");
	var data = {
			headerPic:img,
			username:username,
			address:address
	};
	return data;
}

//分页
var pno=0;//分页的数量
var psize = 5;//每页显示的数量
var periodId = $("#periodId").val();//获取期刊id
//加载更多
function next(){
	var totalCount = $(".item").eq(0).data("itemcount");//获取总数13
	pno++;
	if(pno >= Math.ceil(totalCount/psize)){
		loading("数据已经没了...",1);
	}
	pageF(periodId,pno,psize);
}
//加载数据
function pageF(periodId,pno,psize){
	$.ajax({
   		type:"post",
   		url:"/nocomment/template/"+periodId+"/"+(pno * psize)+"/"+psize,
   		error:function(){loaded=true;loading("remove");},
   		success:function(jdata){
   			loading("评论加载成功...",1);
   			loaded = true;
   			$("#comment-list").append(jdata);
   		}
    });
}